package client.mod;


import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;


public class ModLauncher {
    private List<GameModule> gameModules = new ArrayList<>();
    private static String GAME_MOD_PATH = "modules";

    public void generateDefaultJson() throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("modules/default.json");
        gson.toJson(new GameModule(), writer);
        writer.close();

    }

    public void loadModulesList() throws IOException, ClassNotFoundException {
        File file = new File(GAME_MOD_PATH);
        String[] modules = file.list();
        for (String e : modules != null ? modules : new String[0]) {
            if (!new File(joinPath(GAME_MOD_PATH, e)).isDirectory()) //simply ignore files in modules path
                continue;
            GameModule gameModule = loadModuleInfo(e);
            if (gameModule != null)
                System.out.println("loaded " + gameModule.getName() + " version " + gameModule.getVersion());
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
        GameModule gameModule = gson.fromJson(reader, GameModule.class);
        gameModules.add(gameModule);
        return gameModule;
    }

    public Class loadMod(GameModule gameModule) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, MalformedURLException, InstantiationException {
        String jarPath = joinPath(GAME_MOD_PATH, gameModule.getName(), gameModule.getJarPath());
        File jarFile = new File(jarPath);
        URL jarURL = jarFile.toURI().toURL();

        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        add.setAccessible(true);
        add.invoke(urlClassLoader, jarURL);

        Class clazz = Class.forName(gameModule.getMainClass());

        Method method = clazz.getDeclaredMethod("onload");
        method.invoke(clazz.getDeclaredConstructor().newInstance());

        return clazz;
    }


    public List<GameModule> getGameModules() {
        return gameModules;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ModLauncher modLauncher = new ModLauncher();
        modLauncher.generateDefaultJson();
        modLauncher.loadModulesList();
    }
}

