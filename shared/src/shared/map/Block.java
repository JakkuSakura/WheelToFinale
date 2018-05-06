package shared.map;


public class Block {
    private LandType type;

    public Block(LandType type) {
        setType(type);
    }

    public Block() {
        setType(LandType.NULL);
    }

    public LandType getType() {
        return type;
    }

    public Block setType(LandType type) {
        this.type = type;
        return this;
    }

    public enum LandType {NULL, BUILDING, LAND, SEA, ROCK, MOUNTAIN}
}
