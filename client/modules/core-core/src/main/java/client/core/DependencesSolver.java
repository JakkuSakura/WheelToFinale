package client.core;

import java.util.*;
//todo add this into mod launcher today 6/26
public class DependencesSolver {
    private GameModuleInfo[] gameModuleInfoList;
    private Map<String, GameModuleInfo> nameMapping = new HashMap<>();

    public DependencesSolver(List<GameModuleInfo> gameModuleInfos) {
        gameModuleInfoList = gameModuleInfos.toArray(new GameModuleInfo[0]);
        for (GameModuleInfo info : gameModuleInfoList) {
            nameMapping.put(info.getName(), info);
        }
    }

    public GameModuleInfo getDep(String depname) {
        return nameMapping.get(depname);
    }

    public static void makeSureInList(List<GameModuleInfo> list, GameModuleInfo gameModuleInfo) {
        if (!list.contains(gameModuleInfo))
            list.add(gameModuleInfo);
    }

    public void getDepList(List<GameModuleInfo> list, GameModuleInfo info) {
        for (String modName : info.getDependencies()) {
            GameModuleInfo mod = getDep(modName);
            makeSureInList(list, mod);
        }
        makeSureInList(list, info);
    }


    public List<GameModuleInfo> getSorted() {
        Arrays.sort(gameModuleInfoList);
        List<GameModuleInfo> list = new ArrayList<>();
        for (GameModuleInfo info : gameModuleInfoList) {
            getDepList(list, info);
        }
        return list;
    }


}
