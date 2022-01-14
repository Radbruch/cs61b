package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.awt.print.PrinterGraphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private List<Integer> left_up;
    private List<Integer> right_down;
    private List<Integer> door_1;
    private List<Integer> door_2;
    private Random rand;
    private int width;
    private int height;
    ArrayList<Integer> top;
    ArrayList<Integer> left;


    public Room(TETile[][] world, Random random, int x, int y) {

        rand = random;
        left_up = new ArrayList<>(2);
        right_down = new ArrayList<>(2);
        left_up.add(x);
        left_up.add(y);

        generateRandomSize(world);
        PaintHelper paint = new PaintHelper();
        paint.top(world, left_up.get(0), left_up.get(1), width);
        paint.top(world, left_up.get(0), right_down.get(1), width);
        for (int h = right_down.get(1) + 1; h < left_up.get(1); h++){
            paint.middle(world, left_up.get(0), h, width);
        }

    }

    private void generateRandomSize(TETile[][] world) {
        int distanceToHeight = Game.WIDTH - left_up.get(0) + 1;
        int boundToRight = Math.min(distanceToHeight, 7);
        int w = rand.nextInt(1, boundToRight);
        int h = rand.nextInt(1, 7);
        width = w;
        height = h;
        right_down.add(left_up.get(0) + width + 1);
        right_down.add(left_up.get(1) - height - 1);
        while (isOverride(world)) {
            generateRandomSize(world);
        }
    }

    private boolean isOverride(TETile[][] world) {
        for (int x = left_up.get(0); x <= right_down.get(0); x++) {
            for (int y = right_down.get(1); y <= left_up.get(1); y++) {
                if (!world[x][y].equals(Tileset.NOTHING)) return true;
            }
        }
        return false;
    }

    private List<Integer> selectDoorOpen() {
        List<Integer> door = new ArrayList<>(3);
        top = new ArrayList<>(width);
        left = new ArrayList<>(height);
        String direction = "";

        switch (rand.nextInt(4)) {
            case 0:
                direction = "top";
                break;
            case 1:
                direction = "bottom";
                break;
            case 2:
                direction = "left";
                break;
            case 3:
                direction = "right";
                break;
        }
        if (direction.equals("top")) {
            int i = rand.nextInt(1, width + 1);
            door.add(left_up.get(0) + i);
            door.add(left_up.get(1));
            door.add(0);
        } else if (direction.equals("bottom")) {
            int i = rand.nextInt(1, width + 1);
            door.add(left_up.get(0) + i);
            door.add(right_down.get(1));
            door.add(1);
        } else if (direction.equals("left")) {
            int i = rand.nextInt(1, height + 1);
            door.add(left_up.get(0));
            door.add(right_down.get(1) + i);
            door.add(2);
        } else {
            int i = rand.nextInt(1, height + 1);
            door.add(right_down.get(0));
            door.add(right_down.get(1) + i);
            door.add(3);
        }
        return door;
    }
    public List<Integer> doorOpen1(TETile[][] world) {
        door_1 = selectDoorOpen();
        world[door_1.get(0)][door_1.get(1)] = Tileset.FLOOR;

        return door_1;
    }

    public List<Integer> doorOpen2(TETile[][] world) {
        door_2 = selectDoorOpen();
        while (door_2.equals(door_1)) {
            door_2 = selectDoorOpen();
        }

        world[door_2.get(0)][door_2.get(1)] = Tileset.FLOOR;
        return door_2;
    }
}
