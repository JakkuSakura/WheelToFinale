package client.mod;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;


public class ModLauncher {

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

    public void loadMod() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, MalformedURLException {
        String filename = "";
        File file = new File(filename);
        URL url = file.toURI().toURL();//将File类型转为URL类型，file为jar包路径
        //得到系统类加载器
        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        //因为URLClassLoader中的addURL方法的权限为protected所以只能采用反射的方法调用addURL方法
        Method add = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        add.setAccessible(true);
        add.invoke(urlClassLoader, url);
        Class c = Class.forName("类名");
    }

    public static void main(String[] args) throws IOException {
        File file = new File("modules");
        System.out.println(file.getAbsolutePath());
        String[] modules = file.list();
        System.out.println(Arrays.toString(modules));
        JsonParser parser = new JsonParser();

        for (String e : modules != null ? modules : new String[0]) {
            String[] spt = e.split(File.pathSeparator);
            String  p = joinPath("modules", spt[spt.length - 1], spt[spt.length - 1] + ".json");
            JsonObject obj = (JsonObject) parser.parse(new FileReader(p));
            GameModule module = new GameModule();


        }
    }
}

