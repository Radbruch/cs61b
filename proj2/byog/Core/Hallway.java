package byog.Core;

import byog.TileEngine.TETile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hallway {
    List<Integer> left_up;
    List<Integer> right_down;
    List<Integer> door1 = new ArrayList<>(3);
    List<Integer> door2 = new ArrayList<>(3);
    int width;
    int height;
    private Random rand;
    private String door1_direction = "";


    public Hallway(TETile[][] world, int door1_x, int door1_y, int direction, Random random) {
        left_up = new ArrayList<>(2);
        right_down = new ArrayList<>(2);

        door1.add(door1_x);
        door1.add(door1_y);

        switch (direction) {
            case 0:
                door1_direction = "bottom";
                door1.add(1);
                break;
            case 1:
                door1_direction = "top";
                door1.add(0);
                break;
            case 2:
                door1_direction = "right";
                door1.add(3);
                break;
            case 3:
                door1_direction = "left";
                door1.add(2);
                break;
        }

        rand = random;
    }

    private void generateRandomSize(TETile[][] world) {
        int length = rand.nextInt(1, 5);
        switch (rand.nextInt(2)) {
            case 0: // vertical
                width = 1;
                height = length;
                break;
            case 1: // horizontal
                width = length;
                height = 1;
                break;
        }
    }

    private List<Integer> openDoor1() {
        if (door1_direction.equals("top")) {

        }
        else if (door1_direction.equals("down")) {

        }
        else if (door1_direction.equals("left")) {}
        else {}
    }
    private boolean isOverride(TETile[][] world) {

    }
}
