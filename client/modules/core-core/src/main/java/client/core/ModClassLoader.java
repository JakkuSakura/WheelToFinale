package client.core;

import java.net.URL;
import java.net.URLClassLoader;

public class ModClassLoader extends URLClassLoader {

    public ModClassLoader() {
        super(new URL[0], getSystemClassLoader());
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }
}