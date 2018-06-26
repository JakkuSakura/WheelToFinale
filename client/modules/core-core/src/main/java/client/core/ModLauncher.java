package client.core;


import base.reactor.Reactor;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModLauncher implements ModuleBase {
    private List<GameModule> gameModules = new ArrayList<>();
    private Map<Class, ModuleBase> enabledModules = new HashMap<>();
    private static String GAME_MOD_PATH = "modules";
    private Reactor rootReactor = new Reactor();

    public void generateDefaultJson() throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("modules/default.json");
        gson.toJson(new GameModule(), writer);
        writer.close();

    }

    public void loadModulesList() throws IOException {
        File file = new File(GAME_MOD_PATH);
        String[] modules = file.list();
        for (String e : modules != null ? modules : new String[0]) {
            if (!new File(joinPath(GAME_MOD_PATH, e)).isDirectory()) //simply ignore files in modules path
                continue;
            GameModule gameModule = loadModuleInfo(e);
            if (gameModule != null) {
                gameModules.add(gameModule);
                System.out.println("loaded info of " + gameModule.getName() + " version " + gameModule.getVersion());
            }

        }
    }

    static public String joinPath(String... paths) {
        if (paths.length == 0) {
            return ".";
        } else if (paths.length == 1) {
            return paths[0];
        } else {
            StringBuilder path = new StringBuilder(paths[0]);
            for (int i = 1; i < paths.length; i++) {
                String apath = paths[i];
                if (!apath.endsWith("/"))
                    path.append(File.separator);
                path.append(apath);
            }
            return path.toString();
        }

    }

    public static GameModule loadModuleInfo(String moduleName) throws FileNotFoundException {
        String confPath = joinPath(GAME_MOD_PATH, moduleName, moduleName + ".json");
        FileReader reader = new FileReader(confPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, GameModule.class);
    }

    public static Class<?> loadClass(GameModule gameModule) {
        String jarPath = joinPath(GAME_MOD_PATH, gameModule.getName(), gameModule.getJarPath());
        return loadClassInJar(jarPath, gameModule.getMainClass());
    }


    public static Class<?> loadClassInJar(String fileName, String processorName) {
        URL jarUrl;
        try {
            jarUrl = new URL(fileName);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
            return null;
        }
        URLClassLoader loader = new URLClassLoader(new URL[]{jarUrl}, Thread.currentThread().getContextClassLoader());
//        URLClassLoader loader = new URLClassLoader(new URL[] { url });
        try {
            return loader.loadClass(processorName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ModuleBase loadMod(GameModule gameModule) {
        if (!gameModule.isEnabled())
            return null;
        Class<?> loadClass;
        try {
            loadClass = Class.forName(gameModule.getMainClass());
        } catch (ClassNotFoundException e) {
            loadClass = loadClass(gameModule);
        }
        System.out.println(loadClass);

        ModuleBase instance = enabledModules.get(loadClass);
        if (loadClass != null && instance == null) {
            try {
                instance = (ModuleBase) loadClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
                return null;
            }
            enabledModules.put(loadClass, instance);
            instance.init(rootReactor);
        }
        return instance;
    }


    public List<GameModule> getGameModules() {
        return gameModules;
    }

    public Map<Class, ModuleBase> getEnabledModules() {
        return enabledModules;
    }

    public Reactor getRootReactor() {
        return rootReactor;
    }

    @Override
    public void init(Reactor reactor) {

        // nothing to do
    }

    public void loadAllModules() throws IOException {
        loadModulesList();
        List<GameModule> list = getGameModules();
        enabledModules.put(ModLauncher.class, this);
        list.forEach(this::loadMod);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ModLauncher modLauncher = new ModLauncher();
        modLauncher.loadAllModules();
    }
}
