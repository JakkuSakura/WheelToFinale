package top.zhuoxinsocial.server.map;

public class GameMap {
    private String mapname;
    private Citys citys;
    public GameMap(String mapname) {
        this.mapname = mapname;
        citys = new Citys();
//        Try to load a map after search the path of map
    }
}
