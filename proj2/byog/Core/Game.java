package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 60;
    public static final int HEIGHT = 40;
    private Random rand;

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
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
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().
        rand = new Random(Integer.parseInt(input.substring(1, input.length()-1)));
        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        initialize(finalWorldFrame);


        Room room = new Room(finalWorldFrame, rand, 20, 6);

        List<Integer> room_door1 = room.doorOpen1(finalWorldFrame);
        Hallway room_hallway = new Hallway(finalWorldFrame, room_door1.get(0), room_door1.get(1), room_door1.get(2), rand);
        List<Integer> room_hallway_door2 = room_hallway.doorOpen2(finalWorldFrame);

        List<Integer> room_door2 = room.doorOpen2(finalWorldFrame);
        Hallway room_hallway2 = new Hallway(finalWorldFrame, room_door2.get(0), room_door2.get(1), room_door2.get(2), rand);
        List<Integer> room_hallway2_door2 = room_hallway2.doorOpen2(finalWorldFrame);


        HallwayToRoom roomToroom2 = new HallwayToRoom(finalWorldFrame, room_hallway_door2, rand);
        Room room2 = roomToroom2.room;
        List<Integer> room2_door2 = room2.doorOpen2(finalWorldFrame);
        Hallway room2_hallway = new Hallway(finalWorldFrame, room2_door2.get(0), room2_door2.get(1), room2_door2.get(2), rand);
        List<Integer> room2_hallway_door2 = room2_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom roomToroom3 = new HallwayToRoom(finalWorldFrame, room_hallway2_door2, rand);
        Room room3 = roomToroom3.room;
        List<Integer> room3_door2 = room3.doorOpen2(finalWorldFrame);
        Hallway room3_hallway = new Hallway(finalWorldFrame, room3_door2.get(0), room3_door2.get(1), room3_door2.get(2), rand);
        List<Integer> room3_hallway_door2 = room3_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room2Toroom4 = new HallwayToRoom(finalWorldFrame, room2_hallway_door2, rand);
        Room room4 = room2Toroom4.room;
        List<Integer> room4_door2 = room4.doorOpen2(finalWorldFrame);
        Hallway room4_hallway = new Hallway(finalWorldFrame, room4_door2.get(0), room4_door2.get(1), room4_door2.get(2), rand);
        List<Integer> room4_hallway_door2 = room4_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room3Toroom5 = new HallwayToRoom(finalWorldFrame, room3_hallway_door2, rand);
        Room room5 = room3Toroom5.room;
        List<Integer> room5_door2 = room5.doorOpen2(finalWorldFrame);
        Hallway room5_hallway = new Hallway(finalWorldFrame, room5_door2.get(0), room5_door2.get(1), room5_door2.get(2), rand);
        List<Integer> room5_hallway_door2 = room5_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room4Toroom6 = new HallwayToRoom(finalWorldFrame, room4_hallway_door2, rand);
        Room room6 = room4Toroom6.room;
        List<Integer> room6_door2 = room6.doorOpen2(finalWorldFrame);
        Hallway room6_hallway = new Hallway(finalWorldFrame, room6_door2.get(0), room6_door2.get(1), room6_door2.get(2), rand);
        List<Integer> room6_hallway_door2 = room6_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room5Toroom7 = new HallwayToRoom(finalWorldFrame, room5_hallway_door2, rand);
        Room room7 = room5Toroom7.room;
        List<Integer> room7_door2 = room7.doorOpen2(finalWorldFrame);
        Hallway room7_hallway = new Hallway(finalWorldFrame, room7_door2.get(0), room7_door2.get(1), room7_door2.get(2), rand);
        //List<Integer> room7_hallway_door2 = room7_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room6Toroom8 = new HallwayToRoom(finalWorldFrame, room6_hallway_door2, rand);
        Room room8 = room6Toroom8.room;
        List<Integer> room8_door2 = room8.doorOpen2(finalWorldFrame);
        Hallway room8_hallway = new Hallway(finalWorldFrame, room8_door2.get(0), room8_door2.get(1), room8_door2.get(2), rand);
        List<Integer> room8_hallway_door2 = room8_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room8Toroom9 = new HallwayToRoom(finalWorldFrame, room8_hallway_door2, rand);
        Room room9 = room8Toroom9.room;
        List<Integer> room9_door2 = room9.doorOpen2(finalWorldFrame);
        Hallway room9_hallway = new Hallway(finalWorldFrame, room9_door2.get(0), room9_door2.get(1), room9_door2.get(2), rand);
        List<Integer> room9_hallway_door2 = room9_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room9Toroom10 = new HallwayToRoom(finalWorldFrame, room9_hallway_door2, rand);
        Room room10 = room9Toroom10.room;
        List<Integer> room10_door2 = room10.doorOpen2(finalWorldFrame);
        Hallway room10_hallway = new Hallway(finalWorldFrame, room10_door2.get(0), room10_door2.get(1), room10_door2.get(2), rand);
        List<Integer> room10_hallway_door2 = room10_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room10Toroom11 = new HallwayToRoom(finalWorldFrame, room10_hallway_door2, rand);
        Room room11 = room10Toroom11.room;
        List<Integer> room11_door2 = room11.doorOpen2(finalWorldFrame);
        Hallway room11_hallway = new Hallway(finalWorldFrame, room11_door2.get(0), room11_door2.get(1), room11_door2.get(2), rand);
        List<Integer> room11_hallway_door2 = room11_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room11Toroom12 = new HallwayToRoom(finalWorldFrame, room11_hallway_door2, rand);
        Room room12 = room11Toroom12.room;
        List<Integer> room12_door2 = room12.doorOpen2(finalWorldFrame);
        Hallway room12_hallway = new Hallway(finalWorldFrame, room12_door2.get(0), room12_door2.get(1), room12_door2.get(2), rand);
        List<Integer> room12_hallway_door2 = room12_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room12Toroom13 = new HallwayToRoom(finalWorldFrame, room12_hallway_door2, rand);
        Room room13 = room12Toroom13.room;
        List<Integer> room13_door2 = room13.doorOpen2(finalWorldFrame);
        Hallway room13_hallway = new Hallway(finalWorldFrame, room13_door2.get(0), room13_door2.get(1), room13_door2.get(2), rand);
        List<Integer> room13_hallway_door2 = room13_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room13Toroom14 = new HallwayToRoom(finalWorldFrame, room13_hallway_door2, rand);
        Room room14 = room13Toroom14.room;
        List<Integer> room14_door2 = room14.doorOpen2(finalWorldFrame);
        Hallway room14_hallway = new Hallway(finalWorldFrame, room14_door2.get(0), room14_door2.get(1), room14_door2.get(2), rand);
        List<Integer> room14_hallway_door2 = room14_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room14Toroom15 = new HallwayToRoom(finalWorldFrame, room14_hallway_door2, rand);
        Room room15 = room14Toroom15.room;
        List<Integer> room15_door2 = room15.doorOpen2(finalWorldFrame);
        Hallway room15_hallway = new Hallway(finalWorldFrame, room15_door2.get(0), room15_door2.get(1), room15_door2.get(2), rand);
        List<Integer> room15_hallway_door2 = room15_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room15Toroom16 = new HallwayToRoom(finalWorldFrame, room15_hallway_door2, rand);
        Room room16 = room15Toroom16.room;
        List<Integer> room16_door2 = room16.doorOpen2(finalWorldFrame);
        Hallway room16_hallway = new Hallway(finalWorldFrame, room16_door2.get(0), room16_door2.get(1), room16_door2.get(2), rand);
        List<Integer> room16_hallway_door2 = room16_hallway.doorOpen2(finalWorldFrame);

        HallwayToRoom room16Toroom17 = new HallwayToRoom(finalWorldFrame, room16_hallway_door2, rand);
        Room room17 = room16Toroom17.room;
        List<Integer> room17_door2 = room17.doorOpen2(finalWorldFrame);
        Hallway room17_hallway = new Hallway(finalWorldFrame, room17_door2.get(0), room17_door2.get(1), room17_door2.get(2), rand);
        List<Integer> room17_hallway_door2 = room17_hallway.doorOpen2(finalWorldFrame);



        return finalWorldFrame;
    }

    private void initialize(TETile[][] world) {
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }
    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        Game game = new Game();
        TETile[][] worldState = game.playWithInputString(args[0]);
        ter.renderFrame(worldState);
    }
}

