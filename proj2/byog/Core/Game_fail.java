package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game_fail {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 40;
    private TETile[][] finalWorldFrame;
    private String direction;
    private Random rand;
    private TETile floorTile = Tileset.FLOOR;
    private TETile wallTile = Tileset.WALL;
    private int hallway_x;
    private int hallway_y;
    private boolean is_hallway_stop = false;
    private int next_x;
    private int next_y;
    private boolean is_turn;

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



        finalWorldFrame = new TETile[WIDTH][HEIGHT];



        rand = new Random(Integer.parseInt(input.substring(1, input.length()-1)));
        randomStartTile();
        randomGoTile();
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
        hallway_x = x;
        hallway_y = y;
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
        hallway_x = x;
        hallway_y = y;
    }

    private void goLeft(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x][y-1] = floor;
        finalWorldFrame[x][y-2] = wall;
        direction =  "left";
        hallway_x = x;
        hallway_y = y;
    }

    private void goRight(int x, int y, TETile floor, TETile wall) {
        finalWorldFrame[x][y] = wall;
        finalWorldFrame[x][y-1] = floor;
        finalWorldFrame[x][y-2] = wall;
        direction = "right";
        hallway_x = x;
        hallway_y = y;
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
                hallway_x = x + 2;
                hallway_y = y;
                break;
            case "left" :
                direction = "down";
                hallway_x = x;
                hallway_y = y - 2;
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
                hallway_x = x;
                hallway_y = y;
                break;
            case "right" :
                direction = "down";
                hallway_x = x;
                hallway_y = y - 2;
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
                hallway_x = x + 2;
                hallway_y = y;
                break;
            case "left" :
                direction = "up";
                hallway_x = x;
                hallway_y = y;
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
                hallway_x = x;
                hallway_y = y;
                break;
            case "right" :
                direction = "up";
                hallway_x = x;
                hallway_y = y;
                break;
        }
    }
    private void randomStartTile(){

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                finalWorldFrame[x][y] = Tileset.NOTHING;
            }
        }

        int x = rand.nextInt(2, WIDTH-2);
        int y = rand.nextInt(2, HEIGHT-2);

        if (x < WIDTH / 2 && y < HEIGHT / 2) {
            switch (rand.nextInt(2)) {
                case 0:
                    goUp(x, y, floorTile, wallTile);
                    break;
                case 1:
                    goRight(x, y, floorTile, wallTile);
                    break;
            }
        } else if (x >= WIDTH / 2 && y < HEIGHT / 2) {
            switch (rand.nextInt(2)) {
                case 0:
                    goUp(x, y, floorTile, wallTile);
                    break;
                case 1:
                    goLeft(x, y, floorTile, wallTile);
                    break;
            }
        } else if (x < WIDTH / 2 && y >= HEIGHT / 2) {
            switch (rand.nextInt(2)) {
                case 0:
                    goRight(x, y, floorTile, wallTile);
                    break;
                case 1:
                    goDown(x, y, floorTile, wallTile);
                    break;
            }
        } else {
            switch (rand.nextInt(2)) {
                case 0:
                    goLeft(x, y, floorTile, wallTile);
                    break;
                case 1:
                    goDown(x, y, floorTile, wallTile);
                    break;
            }
        }
    }

    private boolean is_empty(int x_coordinate, int y_coordinate){
        int x = x_coordinate;
        int y = y_coordinate;
        if (x >= WIDTH - 3 || x < 3 || y >= HEIGHT - 3 || y < 3) return false;
        if (direction.equals("up")) {
            boolean a = finalWorldFrame[x][y+1].equals(Tileset.NOTHING) && finalWorldFrame[x+1][y+1].equals(Tileset.NOTHING) && finalWorldFrame[x+2][y+1].equals(Tileset.NOTHING);
            boolean b = finalWorldFrame[x][y+2].equals(Tileset.NOTHING) && finalWorldFrame[x+1][y+2].equals(Tileset.NOTHING) && finalWorldFrame[x+2][y+2].equals(Tileset.NOTHING);
            boolean c = finalWorldFrame[x][y+3].equals(Tileset.NOTHING) && finalWorldFrame[x+1][y+3].equals(Tileset.NOTHING) && finalWorldFrame[x+2][y+3].equals(Tileset.NOTHING);
            return a && b && c;
        } else if (direction.equals("down")) {
            boolean a = finalWorldFrame[x][y-3].equals(Tileset.NOTHING) && finalWorldFrame[x+1][y-3].equals(Tileset.NOTHING) && finalWorldFrame[x+2][y-3].equals(Tileset.NOTHING);
            boolean b = finalWorldFrame[x][y-4].equals(Tileset.NOTHING) && finalWorldFrame[x+1][y-4].equals(Tileset.NOTHING) && finalWorldFrame[x+2][y-4].equals(Tileset.NOTHING);
            boolean c = finalWorldFrame[x][y-5].equals(Tileset.NOTHING) && finalWorldFrame[x+1][y-5].equals(Tileset.NOTHING) && finalWorldFrame[x+2][y-5].equals(Tileset.NOTHING);
            return a && b && c;
        } else if (direction.equals("left")) {
            boolean a = finalWorldFrame[x-1][y].equals(Tileset.NOTHING) && finalWorldFrame[x-2][y].equals(Tileset.NOTHING) && finalWorldFrame[x-3][y].equals(Tileset.NOTHING);
            boolean b = finalWorldFrame[x-1][y-1].equals(Tileset.NOTHING) && finalWorldFrame[x-2][y-1].equals(Tileset.NOTHING) && finalWorldFrame[x-3][y-1].equals(Tileset.NOTHING);
            boolean c = finalWorldFrame[x-1][y-2].equals(Tileset.NOTHING) && finalWorldFrame[x-2][y-2].equals(Tileset.NOTHING) && finalWorldFrame[x-3][y-2].equals(Tileset.NOTHING);
            return a && b && c;
        } else if (direction.equals("right")){
            boolean a = finalWorldFrame[x+3][y].equals(Tileset.NOTHING) && finalWorldFrame[x+4][y].equals(Tileset.NOTHING) && finalWorldFrame[x+5][y].equals(Tileset.NOTHING);
            boolean b = finalWorldFrame[x+3][y-1].equals(Tileset.NOTHING) && finalWorldFrame[x+4][y-1].equals(Tileset.NOTHING) && finalWorldFrame[x+5][y-1].equals(Tileset.NOTHING);
            boolean c = finalWorldFrame[x+3][y-2].equals(Tileset.NOTHING) && finalWorldFrame[x+4][y-2].equals(Tileset.NOTHING) && finalWorldFrame[x+5][y-2].equals(Tileset.NOTHING);
            return a && b && c;
        } else return false;
    }

    private void randomGoTile() {

    }

    /**private void randomGoTile() {

        if (is_hallway_stop) return;

        if (is_empty(hallway_x, hallway_y)) {
            switch (rand.nextInt(3)) {
                case 0:
                    is_turn = true;
                    break;
                case 1:
                    is_turn = false;
                    break;
                case 2:
                    is_turn = false;
                    break;
            }
        } else {return;}

        if (!is_turn) {
            switch (direction) {
                case "up": {
                    if (!finalWorldFrame[hallway_x][hallway_y + 1].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x + 1][hallway_y + 1].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x + 2][hallway_y + 1].equals(Tileset.NOTHING)) {
                        is_hallway_stop = true;
                        return;
                    }
                    goUp(hallway_x, hallway_y + 1, floorTile, wallTile);
                    break;
                }
                case "down": {
                    if (!finalWorldFrame[hallway_x][hallway_y - 1].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x + 1][hallway_y - 1].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x + 2][hallway_y - 1].equals(Tileset.NOTHING)) {
                        is_hallway_stop = true;
                        return;
                    }
                    goDown(hallway_x, hallway_y - 1, floorTile, wallTile);
                    break;
                }
                case "left": {
                    if (!finalWorldFrame[hallway_x - 1][hallway_y].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x - 1][hallway_y - 1].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x - 1][hallway_y - 2].equals(Tileset.NOTHING)) {
                        is_hallway_stop = true;
                        return;
                    }
                    goLeft(hallway_x - 1, hallway_y, floorTile, wallTile);
                    break;
                }
                case "right": {
                    if (!finalWorldFrame[hallway_x + 1][hallway_y].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x + 1][hallway_y - 1].equals(Tileset.NOTHING) || !finalWorldFrame[hallway_x + 1][hallway_y - 2].equals(Tileset.NOTHING)) {
                        is_hallway_stop = true;
                        return;
                    }
                    goRight(hallway_x + 1, hallway_y, floorTile, wallTile);
                    break;
                }
            }
            randomGoTile();
            return;

        } else {
            if (direction.equals("up")) {
                switch (rand.nextInt(2)) {
                    case 0: {
                        // turn left
                        next_x = hallway_x;
                        next_y = hallway_y + 3;
                        down_left(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                    case 1: {
                        // turn right
                        next_x = hallway_x;
                        next_y = hallway_y + 3;
                        down_right(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                }
            } else if (direction.equals("down")) {
                switch (rand.nextInt(2)) {
                    case 0: {
                        // turn left
                        next_x = hallway_x;
                        next_y = hallway_y - 1;
                        up_left(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                    case 1: {
                        // turn right
                        next_x = hallway_x;
                        next_y = hallway_y - 1;
                        up_right(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                }
            } else if (direction.equals("left")) {
                switch (rand.nextInt(2)) {
                    case 0: {
                        // turn up
                        next_x = hallway_x - 3;
                        next_y = hallway_y;
                        up_right(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                    case 1: {
                        // turn down
                        next_x = hallway_x - 3;
                        next_y = hallway_y;
                        down_right(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                }
            } else {
                switch (rand.nextInt(2)) {
                    case 0: {
                        // turn up
                        next_x = hallway_x + 1;
                        next_y = hallway_y;
                        up_left(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                    case 1: {
                        // turn down
                        next_x = hallway_x + 1;
                        next_y = hallway_y;
                        down_left(next_x, next_y, floorTile, wallTile);
                        break;
                    }
                }
            }
            randomGoTile();
            return;
        }
    } */

    public static void main(String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        Game game = new Game();
        TETile[][] worldState = game.playWithInputString(args[0]);
        ter.renderFrame(worldState);

        //System.out.println(TETile.toString(worldState));
    }
}
