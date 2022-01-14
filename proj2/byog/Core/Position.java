package byog.Core;

import byog.TileEngine.TETile;

/**
 * @author Bunny
 * @create 2022-01-09 17:33
 */
public class Position {

    private int x;
    private int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isTile(TETile[][] world, TETile type) {
        return world[x][y].equals(type);
    }

    public void drawTile(TETile[][] world, TETile type) {
        world[x][y] = type;
    }
}
