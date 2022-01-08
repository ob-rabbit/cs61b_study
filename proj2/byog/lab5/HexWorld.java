package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    //世界的长宽
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = System.currentTimeMillis();
    private static final Random RANDOM = new Random(SEED);


    /**
     * @param n 六边形边长
     */
    public void addHexagon(int n, TETile[][] world) {
        //计算最上面的六边形起始坐标点
        int avgHeight = (HEIGHT-n*2*5)/2;
        int y = HEIGHT-avgHeight;
        int x = WIDTH/2-n/2;
        for(int i = 0;i < 3;i++){
            int startx = x+(2*n-1)*i;
            int starty = y-n*i;
            int startlx = x-(2*n-1)*i;
            for(int j=0;j<5-i;j++){
                //右边
                fillWithRandomTiles(world,startx,starty-2*n*j,n);
                //左边
                if (i!=0){
                    fillWithRandomTiles(world,startlx,starty-2*n*j,n);
                }
            }

        }



    }

    /**
     * 随机选择六边形填充样式
     *
     * @return TETile六边形填充样式
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(9);
        switch (tileNum) {
            case 0:
                return Tileset.WALL;
            case 1:
                return Tileset.FLOWER;
            case 2:
                return Tileset.TREE;
            case 3:
                return Tileset.FLOOR;
            case 4:
                return Tileset.MOUNTAIN;
            case 5:
                return Tileset.GRASS;
            case 6:
                return Tileset.PLAYER;
            case 7:
                return Tileset.SAND;
            case 8:
                return Tileset.LOCKED_DOOR;
            default:
                return Tileset.NOTHING;
        }
    }

    /**
     * 选择一种样式填充六边形
     *
     * @param tiles 地图数组
     * @param x     六边形起始坐标
     * @param y     六边形起始坐标，map[y][x]
     * @param n     六边形边长
     */
    public static void fillWithRandomTiles(TETile[][] tiles, int x, int y, int n) {
        TETile teTile = randomTile();
        int mapWidth = tiles[0].length;
        int mapHeight = tiles.length;

        int i, j;
        //上半部分
        for (i = y; i > y - n; i--) {
            if (i >= 0 && i < mapHeight) {
                for (j = x - (y - i); j < x + n + y - i; j++) {
                    if (j >= 0 && j < mapWidth) {
                        tiles[j][i] = teTile;
                    }
                }
            }
        }
        //下半部分
        for (i = y - n; i > y - 2 * n; i--) {
            if (i >= 0 && i < mapHeight) {
                for (j = x - (2 * n - y + i - 1); j < x + n + (2 * n - y + i - 1); j++) {
                    if (j >= 0 && j < mapWidth) {
                        tiles[j][i] = teTile;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        //初始化背景
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        new HexWorld().addHexagon(2,world);


        ter.renderFrame(world);
    }
}
