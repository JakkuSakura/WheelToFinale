package client.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameModuleInfo implements Comparable<GameModuleInfo>{
    private String version = "";
    private String name = "";
    private String describe = "";
    private String jarPath = "";
    private String mainClass = "";
    private int loadLevel = 1000;
    private boolean enabled = true;
    private boolean hidden = false;
    private List<String> dependencies = new ArrayList<>();

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String jarPath) {
        this.jarPath = jarPath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public int getLoadLevel() {
        return loadLevel;
    }

    public void setLoadLevel(int loadLevel) {
        this.loadLevel = loadLevel;
    }

    @Override
    public int compareTo(GameModuleInfo o) {
        return Integer.compare(loadLevel, o.loadLevel);
    }

    public List<String> getDependencies() {
        return Collections.unmodifiableList(dependencies);
    }

    public void addDep(String depedModules) {
        dependencies.add(depedModules);
    }

    @Override
    public String toString() {
        return "GameModuleInfo{" +
                "version='" + version + '\'' +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", jarPath='" + jarPath + '\'' +
                ", mainClass='" + mainClass + '\'' +
                ", loadLevel=" + loadLevel +
                ", enabled=" + enabled +
                ", hidden=" + hidden +
                ", dependencies=" + dependencies +
                '}';
    }
}

