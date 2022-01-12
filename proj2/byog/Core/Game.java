package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;
    private TETile[][] finalWorldFrame;
    private String direction;
    private Random rand;
    private TETile floorTile = Tileset.FLOOR;
    private TETile wallTile = Tileset.WALL;
    private int hallway_x;
    private int hallway_y;
    private boolean is_hallway_stop = false;

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

        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        rand = new Random(Integer.parseInt(input.substring(1, input.length()-1)));

        return finalWorldFrame;
    }

    /**
     * a set of combination tiles that go up.
     *    #         ·         #
     *  [x][y]  [x+1][y]  [x+2][y]
     * @param x x-coordinate of the leftmost tile of this set of combination tiles.
     * @param y y-coordinate of the leftmost tile of this set of combination tiles.
     * @param floor the specified TETile that represent floor.
     * @param wall the specified TETile that represent wall.
     */
    private void goUp(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x+1][y] = floor;
        finalWorldFrame[x+2][y] = wall;
        direction = "up";
    }

    /**
     * a set of combination tiles that go down.
     *    #         ·         #
     *  [x][y]  [x+1][y]  [x+2][y]
     * @param x x-coordinate of the leftmost tile of this set of combination tiles.
     * @param y y-coordinate of the leftmost tile of this set of combination tiles.
     * @param floor the specified TETile that represent floor.
     * @param wall the specified TETile that represent wall.
     */
    private void goDown(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x+1][y] = floor;
        finalWorldFrame[x+2][y] = wall;
        direction = "down";
    }

    private void goLeft(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x][y-1] = floor;
        finalWorldFrame[x][y-2] = wall;
        direction =  "left";
    }

    private void goRight(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x][y-1] = floor;
        finalWorldFrame[x][y-2] = wall;
        direction = "right";
    }

    /**
     * a set of combination tiles that connect below and right, as a corner.
     * if direction == "up", change to "right"
     * if direction == "left", change to "down"
     *   [x][y]    [x+1][y]   [x+2][y]
     *     #         #          #
     *   [x][y-1]  [x+1][y-1] [x+2][y-1]
     *     #         ·          ·
     *   [x][y-2]  [x+1][y-2] [x+2][y-2]
     *     #         ·          #
     * @param x x-coordinate of the top-left tile of this set of combination tiles.
     * @param y y-coordinate of the top-left tile of this set of combination tiles.
     * @param floor the specified TETile that represent floor.
     * @param wall the specified TETile that represent wall.
     */
    private void down_right(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x + 1][y] = wall;
        finalWorldFrame[x + 2][y] = wall;
        finalWorldFrame[x][y - 1] = wall;
        finalWorldFrame[x + 1][y - 1] = floor;
        finalWorldFrame[x + 2][y - 1] = floor;
        finalWorldFrame[x][y - 2] = wall;
        finalWorldFrame[x + 1][y - 2] = floor;
        finalWorldFrame[x + 2][y - 2] = wall;

        switch (direction) {
            case "up" :
                direction = "right";
                break;
            case "left" :
                direction = "down";
                break;
        }
    }

    private void down_left(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x + 1][y] = wall;
        finalWorldFrame[x + 2][y] = wall;
        finalWorldFrame[x][y - 1] = floor;
        finalWorldFrame[x + 1][y - 1] = floor;
        finalWorldFrame[x + 2][y - 1] = wall;
        finalWorldFrame[x][y - 2] = wall;
        finalWorldFrame[x + 1][y - 2] = floor;
        finalWorldFrame[x + 2][y - 2] = wall;

        switch (direction) {
            case "up" :
                direction = "left";
                break;
            case "right" :
                direction = "down";
                break;
        }
    }

    private void up_right(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x + 1][y] = floor;
        finalWorldFrame[x + 2][y] = wall;
        finalWorldFrame[x][y - 1] = wall;
        finalWorldFrame[x + 1][y - 1] = floor;
        finalWorldFrame[x + 2][y - 1] = floor;
        finalWorldFrame[x][y - 2] = wall;
        finalWorldFrame[x + 1][y - 2] = wall;
        finalWorldFrame[x + 2][y - 2] = wall;

        switch (direction) {
            case "down" :
                direction = "right";
                break;
            case "left" :
                direction = "up";
                break;
        }
    }

    private void up_left(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x + 1][y] = floor;
        finalWorldFrame[x + 2][y] = wall;
        finalWorldFrame[x][y - 1] = floor;
        finalWorldFrame[x + 1][y - 1] = floor;
        finalWorldFrame[x + 2][y - 1] = wall;
        finalWorldFrame[x][y - 2] = wall;
        finalWorldFrame[x + 1][y - 2] = wall;
        finalWorldFrame[x + 2][y - 2] = wall;

        switch (direction) {
            case "down" :
                direction = "left";
                break;
            case "right" :
                direction = "up";
                break;
        }
    }
    private void randomStartTile(int seed){
        int x = rand.nextInt(WIDTH);
        int y = rand.nextInt(HEIGHT);

        switch (rand.nextInt(4)) {
            case 0: goUp(x, y, floorTile, wallTile);
            case 1: goDown(x, y, floorTile, wallTile);
            case 2: goLeft(x, y, floorTile, wallTile);
            case 3: goRight(x, y, floorTile, wallTile);
        }
        hallway_x = x;
        hallway_y = y;
    }

    private boolean is_empty(int x_coordinate, int y_coordinate){
        int x = x_coordinate;
        int y = y_coordinate;
        if (direction.equals("up")) {
            boolean a = finalWorldFrame[x][y+1] == Tileset.NOTHING && finalWorldFrame[x+1][y+1] == Tileset.NOTHING && finalWorldFrame[x+2][y+1] == Tileset.NOTHING;
            boolean b = finalWorldFrame[x][y+2] == Tileset.NOTHING && finalWorldFrame[x+1][y+2] == Tileset.NOTHING && finalWorldFrame[x+2][y+2] == Tileset.NOTHING;
            boolean c = finalWorldFrame[x][y+3] == Tileset.NOTHING && finalWorldFrame[x+1][y+3] == Tileset.NOTHING && finalWorldFrame[x+2][y+3] == Tileset.NOTHING;
            return a && b && c;
        } else if (direction.equals("down")) {
            boolean a = finalWorldFrame[x][y-3] == Tileset.NOTHING && finalWorldFrame[x+1][y-3] == Tileset.NOTHING && finalWorldFrame[x+2][y-3] == Tileset.NOTHING;
            boolean b = finalWorldFrame[x][y-4] == Tileset.NOTHING && finalWorldFrame[x+1][y-4] == Tileset.NOTHING && finalWorldFrame[x+2][y-4] == Tileset.NOTHING;
            boolean c = finalWorldFrame[x][y-5] == Tileset.NOTHING && finalWorldFrame[x+1][y-5] == Tileset.NOTHING && finalWorldFrame[x+2][y-5] == Tileset.NOTHING;
            return a && b && c;
        } else if (direction.equals("left")) {
            boolean a = finalWorldFrame[x-1][y] == Tileset.NOTHING && finalWorldFrame[x-2][y] == Tileset.NOTHING && finalWorldFrame[x-3][y] == Tileset.NOTHING;
            boolean b = finalWorldFrame[x-1][y-1] == Tileset.NOTHING && finalWorldFrame[x-2][y-1] == Tileset.NOTHING && finalWorldFrame[x-3][y-1] == Tileset.NOTHING;
            boolean c = finalWorldFrame[x-1][y-2] == Tileset.NOTHING && finalWorldFrame[x-2][y-2] == Tileset.NOTHING && finalWorldFrame[x-3][y-2] == Tileset.NOTHING;
            return a && b && c;
        } else if (direction.equals("right")){
            boolean a = finalWorldFrame[x+3][y] == Tileset.NOTHING && finalWorldFrame[x+4][y] == Tileset.NOTHING && finalWorldFrame[x+5][y] == Tileset.NOTHING;
            boolean b = finalWorldFrame[x+3][y-1] == Tileset.NOTHING && finalWorldFrame[x+4][y-1] == Tileset.NOTHING && finalWorldFrame[x+5][y-1] == Tileset.NOTHING;
            boolean c = finalWorldFrame[x+3][y-2] == Tileset.NOTHING && finalWorldFrame[x+4][y-2] == Tileset.NOTHING && finalWorldFrame[x+5][y-2] == Tileset.NOTHING;
            return a && b && c;
        } else return false;
    }
    private void randomGoTile() {
        boolean is_turn = false;

        if (is_hallway_stop) return;

        if (is_empty(hallway_x, hallway_y)) {
            switch (rand.nextInt(2)) {
                case 0: is_turn = true;
                case 1: is_turn = false;
            }
        } else is_turn = true;

        if (!is_turn) {
            switch (direction) {
                case "up": {
                    if (finalWorldFrame[hallway_x][hallway_y + 1]!=Tileset.NOTHING) {
                        is_hallway_stop = true;
                        return;
                    }
                    goUp(hallway_x, hallway_y + 1, floorTile, wallTile);
                    hallway_y++;
                }
                case "down": {
                    if (finalWorldFrame[hallway_x][hallway_y - 1]!=Tileset.NOTHING) {
                        is_hallway_stop = true;
                        return;
                    }
                    goDown(hallway_x, hallway_y - 1, floorTile, wallTile);
                    hallway_y--;
                }
                case "left": {
                    if (finalWorldFrame[hallway_x - 1][hallway_y]!=Tileset.NOTHING) {
                        is_hallway_stop = true;
                        return;
                    }
                    goLeft(hallway_x - 1, hallway_y, floorTile, wallTile);
                    hallway_x--;
                }
                case "right": {

                    if (finalWorldFrame[hallway_x + 1][hallway_y] != Tileset.NOTHING) {
                        is_hallway_stop = true;
                        return;
                    }
                    goRight(hallway_x + 1, hallway_y, floorTile, wallTile);
                    hallway_x++;
                }
            }
        }

    }

    public static void main(String[] args){
    }
}
