package byog.Core;


import byog.TileEngine.TERenderer;
import TileEngine.TETile;
import TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        drawStartUI();


    }

    private void drawStartUI() {
        initBackGround();

        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "CS61B: THE GAME");
        StdDraw.text(WIDTH / 2, 2 * HEIGHT / 4 + 2, "New Game (N)");
        StdDraw.text(WIDTH / 2, 2 * HEIGHT / 4, "Load Game (L)");
        StdDraw.text(WIDTH / 2, 2 * HEIGHT / 4 - 2, "Quit (Q)");

        StdDraw.show();
        char ch;
        while (true) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            ch = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (ch == 'n' || ch == 'l' || ch == 'q') {
                break;
            }
        }
        if (ch == 'n') {
            newGame();
        } else if (ch == 'l') {
            loadOldGame();
        } else {
            System.exit(0);
        }
    }

    private void newGame() {
        long seed = getSeed();
        TETile[][] world = generateWorld(seed);
        ter.renderFrame(world);
    }

    private TETile[][] generateWorld(long seed) {
        ter.initialize(WIDTH, HEIGHT);
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        //初始化背景
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Random random = new Random(seed);
        List<Room> rooms = generateRooms(world, random, 10);
        generateHall(world,random);

        return world;
    }

    /**
     * bfs创建墙b
     * @param world
     * @param random
     */
    private void generateHall(TETile[][] world, Random random) {
        Deque<Position> queue = new ArrayDeque<>();
        Position start = selectStartPosition(world,random);
        start.drawTile(world,Tileset.FLOOR);
        queue.push(start);
        while (!queue.isEmpty()){
            Position p = queue.getLast();

        }
    }

    private Position selectStartPosition(TETile[][] world, Random random) {
        Position position = new Position();
        //随机选择上下左右四个方向
        int selector = RandomUtils.uniform(random, 0,4);
        switch (selector){
            case 0:
                position.setX(1);
                do {
                    position.setY(generateInt(random, 1, HEIGHT - 1));
                } while (position.isTile(world, Tileset.ROOM));
                break;
            case 1:
                position.setY(1);
                do{
                    position.setX(generateInt(random,1,WIDTH-1));
                }while (position.isTile(world,Tileset.ROOM));
                break;
            case 2:
                position.setX(WIDTH-2);
                do{
                    position.setX(generateInt(random,1,HEIGHT-1));
                }while (position.isTile(world,Tileset.ROOM));
                break;
            default:
                position.setY(HEIGHT-2);
                do{
                    position.setY(generateInt(random,1,WIDTH-1));
                }while (position.isTile(world,Tileset.ROOM));
                break;
        }
        return position;
    }

    private List<Room> generateRooms(TETile[][] world, Random random, int roomNums) {
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < roomNums;) {
            Room newRoom;
            //随机生成房间坐标
            while(true){
                Position downLeft = new Position(generateInt(random, 1, WIDTH - 4),
                        generateInt(random, 1, HEIGHT - 4));
                Position upRight = new Position(generateInt(random,downLeft.getX()+1,WIDTH-1),
                        generateInt(random,downLeft.getY()+1,HEIGHT-1));

                if (Room.isLegal(downLeft,upRight)){
                    newRoom = new Room(downLeft, upRight);
                    break;
                }
            }
            if(!newRoom.isOverlapped(rooms)){
                rooms.add(newRoom);
                i++;
                newRoom.drowRoom(world,Tileset.ROOM);
            }
        }

        return rooms;
    }

    //随机生成房间坐标x/y
    private int generateInt(Random random, int left, int right) {
        int nums = RandomUtils.uniform(random, left, right);
        if (RandomUtils.bernoulli(random)) {
            nums++;
        } else {
            nums--;
        }
        return nums;
    }

    private long getSeed() {
        StdDraw.clear(Color.BLACK);
        StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "Please enter a random seed,enter s to stop");
        StdDraw.show();
        StringBuilder sb = new StringBuilder();
        char ch;
        while (true) {
            StdDraw.clear(Color.BLACK);
            StdDraw.text(WIDTH / 2, 3 * HEIGHT / 4, "Please enter a random seed,enter s to stop");
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            ch = Character.toLowerCase(StdDraw.nextKeyTyped());
            if (ch != 's') {
                if (Character.isDigit(ch)) {
                    sb.append(ch);
                    StdDraw.text(WIDTH / 2, HEIGHT / 2, sb.toString());
                    StdDraw.show();
                }
            } else {
                break;
            }
        }
        return Long.valueOf(sb.toString());

    }

    private void loadOldGame() {
        //todo
        System.out.println("加载游戏");
    }

    private void initBackGround() {
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        StdDraw.setFont(font);
        StdDraw.setPenColor(Color.white);
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
