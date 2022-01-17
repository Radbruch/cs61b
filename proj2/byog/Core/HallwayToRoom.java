package byog.Core;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HallwayToRoom {
    private List<Integer> doorOfHallway;
    private List<Integer> doorOfRoom;
    private Random rand;
    private List<Integer> left_up;
    private int width;
    private int height;
    public Room room;

    public HallwayToRoom(TETile[][] world, List<Integer> hallwayDoor, Random random) {

        doorOfHallway = hallwayDoor;
        doorOfRoom = new ArrayList<>(3);
        rand = random;
        left_up = new ArrayList<>(2);

        generateRandomSize(world);
        room = new Room(world, left_up.get(0), left_up.get(1), width, height, doorOfRoom.get(0), doorOfRoom.get(1), doorOfRoom.get(2), rand);
        world[doorOfRoom.get(0)][doorOfRoom.get(1)] = Tileset.FLOOR;

    }

    private void generateRandomSize(TETile[][] world) {
        int w = rand.nextInt(2, 8);
        int h = rand.nextInt(1,7);

        if (doorOfHallway.get(2) == 0) {
            doorOfRoom.add(doorOfHallway.get(0));
            doorOfRoom.add(doorOfHallway.get(1) + 1);
            doorOfRoom.add(1);
            int i = rand.nextInt(1, w + 1);
            left_up.add(doorOfRoom.get(0) - i);
            left_up.add(doorOfRoom.get(1) + h + 1);

        }
        else if (doorOfHallway.get(2) == 1) {
            doorOfRoom.add(doorOfHallway.get(0));
            doorOfRoom.add(doorOfHallway.get(1) - 1);
            doorOfRoom.add(0);
            int i = rand.nextInt(1, w + 1);
            left_up.add(doorOfRoom.get(0) - i);
            left_up.add(doorOfRoom.get(1));
        }
        else if (doorOfHallway.get(2) == 2) {
            doorOfRoom.add(doorOfHallway.get(0) - 1);
            doorOfRoom.add(doorOfHallway.get(1));
            doorOfRoom.add(3);
            int i = rand.nextInt(1, h + 1);
            left_up.add(doorOfRoom.get(0) - w - 1);
            left_up.add(doorOfRoom.get(1) + i);
        }
        else {
            doorOfRoom.add(doorOfHallway.get(0) + 1);
            doorOfRoom.add(doorOfHallway.get(1));
            doorOfRoom.add(2);
            int i = rand.nextInt(1, h + 1);
            left_up.add(doorOfRoom.get(0));
            left_up.add(doorOfRoom.get(1) + h);
        }

        while (isOverride(world, w, h)) {
            generateRandomSize(world);
        }
        width = w;
        height = h;

    }
    private boolean isOverride(TETile[][] world, int w, int h) {
        for (int x = left_up.get(0); x <= w + 1; x++) {
            for (int y = left_up.get(1) - h - 1; y <= left_up.get(1); y++) {
                if (!world[x][y].equals(Tileset.NOTHING)) return true;
            }
        }
        return false;
    }
}
