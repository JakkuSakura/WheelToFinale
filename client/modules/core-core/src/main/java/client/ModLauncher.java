package client;


import base.reactor.Reactor;
import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                System.out.println("loaded " + gameModule.getName() + " version " + gameModule.getVersion());

                gameModules.add(gameModule);
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

    public GameModule loadModuleInfo(String moduleName) throws FileNotFoundException {
        String confPath = joinPath(GAME_MOD_PATH, moduleName, moduleName + ".json");
        FileReader reader = new FileReader(confPath);
        Gson gson = new Gson();
        return gson.fromJson(reader, GameModule.class);
    }

    public Class loadClass(GameModule gameModule) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, MalformedURLException {

            String jarPath = joinPath(GAME_MOD_PATH, gameModule.getName(), gameModule.getJarPath());
            File jarFile = new File(jarPath);
            URL jarURL = jarFile.toURI().toURL();
            URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            add.setAccessible(true);
            add.invoke(urlClassLoader, jarURL);
            return Class.forName(gameModule.getMainClass());
    }

    public ModuleBase loadMod(GameModule gameModule) {
        if (!gameModule.isEnabled())
            return null;
        try {
            Class loadClass = loadClass(gameModule);
            ModuleBase instance = (ModuleBase) loadClass.getDeclaredConstructor().newInstance();
            enabledModules.put(loadClass, instance);
            instance.init(rootReactor);
            return instance;

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | MalformedURLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
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

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ModLauncher modLauncher = new ModLauncher();
        modLauncher.generateDefaultJson();
        modLauncher.loadModulesList();
        List<GameModule> list = modLauncher.getGameModules();
        list.forEach(modLauncher::loadMod);
    }
}

