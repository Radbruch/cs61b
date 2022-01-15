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
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
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


        Room room = new Room(finalWorldFrame, rand, 28, 18);
        List<Integer> door1 = room.doorOpen1(finalWorldFrame);
        Hallway hallway = new Hallway(finalWorldFrame, door1.get(0), door1.get(1), door1.get(2), rand);
        hallway.doorOpen2(finalWorldFrame);
        List<Integer> door2 = room.doorOpen2(finalWorldFrame);
        Hallway hallway2 = new Hallway(finalWorldFrame, door2.get(0), door2.get(1), door2.get(2), rand);
        hallway2.doorOpen2(finalWorldFrame);
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

