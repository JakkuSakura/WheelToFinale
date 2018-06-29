package client.display;

import client.core.ModLauncher;
import com.jme3.asset.*;
import com.jme3.asset.plugins.ClasspathLocator;
import com.jme3.asset.plugins.UrlAssetInfo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class ModulesClasspathLoader extends ClasspathLocator {

    public AssetInfo locate(AssetManager manager, AssetKey key) {
        String name = key.getName();

        URL url = ModLauncher.getLoader().getResource(name);

        if (url == null) {
            final List<ClassLoader> classLoaders = manager.getClassLoaders();
            for (final ClassLoader classLoader : classLoaders) {
                url = classLoader.getResource(name);
                if (url != null) {
                    break;
                }
            }
        }

        if (url == null)
            return null;

        if (url.getProtocol().equals("file")) {
            try {
                String path = new File(url.toURI()).getCanonicalPath();

                // convert to / for windows
                if (File.separatorChar == '\\') {
                    path = path.replace('\\', '/');
                }

                // compare path
                if (!path.endsWith(name)) {
                    throw new AssetNotFoundException("Asset name doesn't match requirements.\n" +
                            "\"" + path + "\" doesn't match \"" + name + "\"");
                }
            } catch (URISyntaxException ex) {
                throw new AssetLoadException("Error converting URL to URI", ex);
            } catch (IOException ex) {
                throw new AssetLoadException("Failed to get canonical path for " + url, ex);
            }
        }

        try {
            return UrlAssetInfo.create(manager, key, url);
        } catch (IOException ex) {
            // This is different handling than URL locator
            // since classpath locating would return null at the getResource()
            // call, otherwise there's a more critical error...
            throw new AssetLoadException("Failed to read URL " + url, ex);
        }
    }
}
