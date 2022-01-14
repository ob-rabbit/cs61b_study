package byog.Core;

import byog.TileEngine.TETile;
import sun.awt.geom.AreaOp;

import java.util.List;

/**
 * @author Bunny
 * @create 2022-01-09 17:14
 */
public class Room {

    private static int maxW = 6;
    private static int maxH = 6;


    public Position getDownLeft() {
        return downLeft;
    }

    public Position getUpRight() {
        return upRight;
    }

    //定位房间
    //左下坐标
    private Position downLeft;
    //右上坐标
    private Position upRight;

    public Room(Position downLeft, Position upRight) {
        this.downLeft = downLeft;
        this.upRight = upRight;
    }

    public int getWidth() {
        return upRight.getX() - downLeft.getX();
    }

    public int getHeight() {
        return upRight.getY() - downLeft.getY();
    }

    /**
     * 判断房间坐标合法
     * 宽度和高度都小于最大值
     *
     * @param downLeft
     * @param upRight
     * @return
     */
    public static boolean isLegal(Position downLeft, Position upRight) {
        boolean widthLegal = (upRight.getX() - downLeft.getX()) > 0 &&
                (upRight.getX() - downLeft.getX()) < maxW;
        boolean heightLegal = (upRight.getY() - downLeft.getY()) > 0 &&
                (upRight.getY() - downLeft.getY()) < maxH;

        return widthLegal && heightLegal;
    }

    /**
     * 判断房间是否重叠
     * @param rooms
     * @return
     */
    public boolean isOverlapped(List<Room> rooms) {
        if (rooms.isEmpty()) {
            return false;
        }
        int centerX = (upRight.getX() - downLeft.getX()) / 2;
        int centerY = (upRight.getY() - downLeft.getY()) / 2;
        int curRoomW = getWidth();
        int curRoomH = getHeight();
        //判断房间是否重叠
        for (Room room : rooms) {
            int room_center_x = (room.getUpRight().getX() - room.getDownLeft().getX()) / 2;
            int room_center_y = (room.getUpRight().getY() - room.getDownLeft().getY()) / 2;
            int roomW = room.getWidth();
            int roomH = room.getHeight();
            //让房间相对隔开，房间距离至少为2
            if (Math.abs(room_center_x - centerX) < (roomW + curRoomW) / 2 + 2) {
                return true;
            }

            if (Math.abs(room_center_y - centerY) < (roomH + curRoomH) / 2 + 2) {
                return true;
            }
        }

        return false;
    }

    public void drowRoom(TETile[][] world, TETile type) {
        for (int i = downLeft.getX(); i <= upRight.getX(); i++) {
            for (int j = downLeft.getY(); j <= upRight.getY(); j++) {
                world[i][j] = type;
            }
        }
    }
}
