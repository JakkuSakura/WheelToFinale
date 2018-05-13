package shared.element;

public class Landscape {
    private Block[][] blokcs;
    private int width;
    private int height;

    public Block[][] getBlokcs() {
        return blokcs;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void add(String name, Object obj) {
        String s = (String) obj;
        String[] rows = s.split("\n");
        blokcs = new Block[height][];
        for (int i = 0; i < height; i++) {
            String row = rows[i].trim();
            Block[] b = new Block[row.length()];
            for (int j = 0; j < width; j++) {
                b[j] = new Block(row.charAt(j) == '0' ? Block.LandType.LAND : Block.LandType.SEA);
            }
            blokcs[i] = b;
        }

    }
}
