package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Hallway {
    private List<Integer> left_up;
    private List<Integer> right_down;
    private List<Integer> door1 = new ArrayList<>(3);
    private List<Integer> door2 = new ArrayList<>(3);
    int width;
    int height;
    private Random rand;
    private String door1_direction = "";
    private String shape = "";
    ArrayList<Integer> top;
    ArrayList<Integer> left;


    public Hallway(TETile[][] world, int door1_x, int door1_y, int direction, Random random) {
        left_up = new ArrayList<>(2);
        right_down = new ArrayList<>(2);



        switch (direction) {
            case 0:
                door1_direction = "bottom";
                door1.add(door1_x);
                door1.add(door1_y + 1);
                door1.add(1);
                break;
            case 1:
                door1_direction = "top";
                door1.add(door1_x);
                door1.add(door1_y - 1);
                door1.add(0);
                break;
            case 2:
                door1_direction = "right";
                door1.add(door1_x - 1);
                door1.add(door1_y);
                door1.add(3);
                break;
            case 3:
                door1_direction = "left";
                door1.add(door1_x + 1);
                door1.add(door1_y);
                door1.add(2);
                break;
        }

        rand = random;
        generateRandomSize(world);
        openDoor1();
        PaintHelper paint = new PaintHelper();
        paint.top(world, left_up.get(0), left_up.get(1), width);
        paint.top(world, left_up.get(0), right_down.get(1), width);
        for (int h = right_down.get(1) + 1; h < left_up.get(1); h++){
            paint.middle(world, left_up.get(0), h, width);
        }
        world[door1.get(0)][door1.get(1)] = Tileset.FLOOR;
    }

    private void generateRandomSize(TETile[][] world) {
        int length = rand.nextInt(1, 4);
        switch (rand.nextInt(2)) {
            case 0: // vertical
                width = 1;
                height = length;
                shape = "vertical";
                break;
            case 1: // horizontal
                width = length;
                height = 1;
                shape = "horizontal";
                break;
        }
    }

    private List<Integer> openDoor1() {
        if (door1_direction.equals("top")) {
            switch (shape) {
                case "vertical":
                    left_up.add(door1.get(0) - 1);
                    left_up.add(door1.get(1));
                    right_down.add(door1.get(0) + 1);
                    right_down.add(door1.get(1) - height - 1);
                    break;
                case "horizontal":
                    int dooropen = rand.nextInt(1, width + 1);
                    left_up.add(door1.get(0) - dooropen);
                    left_up.add(door1.get(1));
                    right_down.add(door1.get(0) + width - dooropen + 1);
                    right_down.add(door1.get(1) - 2);
                    break;
            }
        }
        else if (door1_direction.equals("bottom")) {
            switch (shape) {
                case "vertical":
                    left_up.add(door1.get(0) - 1);
                    left_up.add(door1.get(1) + height + 1);
                    right_down.add(door1.get(0) + 1);
                    right_down.add(door1.get(1));
                    break;
                case "horizontal":
                    int dooropen = rand.nextInt(1, width + 1);
                    left_up.add(door1.get(0) - dooropen);
                    left_up.add(door1.get(1) + 2);
                    right_down.add(door1.get(0)+ width - dooropen + 1);
                    right_down.add(door1.get(1));
                    break;
            }
        }
        else if (door1_direction.equals("left")) {
            switch (shape) {
                case "vertical":
                    int dooropen = rand.nextInt(1, height + 1);
                    left_up.add(door1.get(0));
                    left_up.add(door1.get(1) + dooropen);
                    right_down.add(door1.get(0) + 2);
                    right_down.add(door1.get(1) - (height - dooropen) - 1);
                    break;
                case "horizontal":
                    left_up.add(door1.get(0));
                    left_up.add(door1.get(1) + 1);
                    right_down.add(door1.get(0) + width + 1);
                    right_down.add(door1.get(1) - 1);
                    break;
            }
        }
        else {
            switch (shape) {
                case "vertical":
                    int dooropen = rand.nextInt(1, height + 1);
                    left_up.add(door1.get(0) - 2);
                    left_up.add(door1.get(1) + dooropen);
                    right_down.add(door1.get(0));
                    right_down.add(door1.get(1) - (height - dooropen) - 1);
                    break;
                case "horizontal":
                    left_up.add(door1.get(0) - width - 1);
                    left_up.add(door1.get(1) + 1);
                    right_down.add(door1.get(0));
                    right_down.add(door1.get(1) - 1);
                    break;
            }
        }
        return door1;
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

        switch (door1.get(2)) {
            case 0:
                direction = "bottom";
                break;
            case 1:
                direction = "top";
                break;
            case 2:
                direction = "right";
                break;
            case 3:
                direction = "left";
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

    public List<Integer> doorOpen2(TETile[][] world) {
        door2 = selectDoorOpen();
        world[door2.get(0)][door2.get(1)] = Tileset.FLOOR;
        return door2;
    }


}
