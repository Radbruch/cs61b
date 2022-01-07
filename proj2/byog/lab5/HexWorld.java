package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final long SEED = 2873356;
    private static final Random RANDOM = new Random(SEED);

    /**
     * Create a size-s hexagon at specified position p in world.
     * @param world the 2D TETile[][] array to render.
     * @param p position to place the upper-left corner of the hexagon.
     * @param s size of a hexagon, must greater than or equal to 2.
     * @param t a TETile object.
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t){
        int upperLeftX = p.x;
        int upperLeftY = p.y;

        for (int i = 0; i < s; i++) {
            for(int j = -i; j < s + i; j++) {
                world[upperLeftX + j][upperLeftY - i] = t;
            }
        }

        int anotherUpperY = upperLeftY - 2 * s + 1;

        for (int i = 0; i < s; i++) {
            for(int j = -i; j < s + i; j++) {
                world[upperLeftX + j][anotherUpperY + i] = t;
            }
        }
    }

    /**
     * specified a position with x-coordinate and y-coordinate.
     */
    public static class Position {
        private int x;
        private int y;

        public Position(int x_coordinate, int y_coordinate){
            x = x_coordinate;
            y = y_coordinate;
        }
    }

    /** Picks a RANDOM tile with a 20% change of being
     *  a tree, 20% change of being a grass, 20% change
     *  of being a mountain, 20% change of being a flower,
     *  and 20% change of being a sand space.
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(5);
        switch (tileNum) {
            case 0: return Tileset.TREE;
            case 1: return Tileset.GRASS;
            case 2: return Tileset.MOUNTAIN;
            case 3: return Tileset.FLOWER;
            case 4: return Tileset.SAND;
            default: return Tileset.NOTHING;
        }
    }


    public static void TesselationOfHexagons(TETile[][] world, Position topPosition, int s) {

        topHexagon(world, topPosition, s);
        mostLeftHexagon(world, topPosition, s);
        mostRightHexagon(world, topPosition, s);
        bottemHexagon(world, topPosition, s);
        middleHexagon(world, topPosition, s);
    }

    private static void topHexagon(TETile[][] world, Position topPosition, int s) {
        int top_x = topPosition.x;
        int top_y = topPosition.y;
        Position top = new Position(top_x, top_y);
        addHexagon(world, top, s, randomTile());

        int bottemLeft_x = top_x - 2 * s + 1;
        int bottemLeft_y = top_y - s;
        Position bottemLeft = new Position(bottemLeft_x, bottemLeft_y);
        addHexagon(world, bottemLeft, s, randomTile());

        int bottem_x = top_x;
        int bottem_y = top_y - 2 * s;
        Position bottem = new Position(bottem_x, bottem_y);
        addHexagon(world, bottem, s, randomTile());

        int bottemRight_x = top_x + 2 * s - 1;
        int bottemRight_y = top_y - s;
        Position bottemRight = new Position(bottemRight_x, bottemRight_y);
        addHexagon(world, bottemRight, s, randomTile());
    }
    private static void mostLeftHexagon(TETile[][] world, Position topPosition, int s) {
        // position of the top hex, other position of hex all based on this.
        int top_x = topPosition.x;
        int top_y = topPosition.y;

        int mostLeft_x = top_x - 4 * s + 2;
        int mostLeft_y = top_y - 4 * s;
        Position mostLeft = new Position(mostLeft_x, mostLeft_y);
        addHexagon(world, mostLeft, s, randomTile());

        int upper_x = top_x - 4 * s + 2;
        int upper_y = top_y - 2 * s;
        Position upper = new Position(upper_x, upper_y);
        addHexagon(world, upper, s, randomTile());

        int upperRight_x = top_x - 2 * s + 1;
        int upperRight_y = top_y - 3 * s;
        Position upperRight = new Position(upperRight_x, upperRight_y);
        addHexagon(world, upperRight, s, randomTile());

        int bottemRight_x = top_x - 2 * s + 1;
        int bottemRight_y = top_y - 5 * s;
        Position bottemRight = new Position(bottemRight_x, bottemRight_y);
        addHexagon(world, bottemRight, s, randomTile());

        int bottem_x = top_x - 4 * s + 2;
        int bottem_y = top_y - 6 * s;
        Position bottem = new Position(bottem_x, bottem_y);
        addHexagon(world, bottem, s, randomTile());
    }

    private static void mostRightHexagon(TETile[][] world, Position topPosition, int s) {
        int top_x = topPosition.x;
        int top_y = topPosition.y;

        int mostRight_x = top_x + 4 * s - 2;
        int mostRight_y = top_y - 4 * s;
        Position mostRight = new Position(mostRight_x, mostRight_y);
        addHexagon(world, mostRight, s, randomTile());

        int upper_x = top_x + 4 * s - 2;
        int upper_y = top_y - 2 * s;
        Position upper = new Position(upper_x, upper_y);
        addHexagon(world, upper, s, randomTile());

        int upperLeft_x = top_x + 2 * s - 1;
        int upperLeft_y = top_y - 3 * s;
        Position upperLeft = new Position(upperLeft_x, upperLeft_y);
        addHexagon(world, upperLeft, s, randomTile());

        int bottemLeft_x = top_x + 2 * s - 1;
        int bottemLeft_y = top_y - 5 * s;
        Position bottemLeft = new Position(bottemLeft_x, bottemLeft_y);
        addHexagon(world, bottemLeft, s, randomTile());

        int bottem_x = top_x + 4 * s - 2;
        int bottem_y = top_y - 6 * s;
        Position bottem = new Position(bottem_x, bottem_y);
        addHexagon(world, bottem, s, randomTile());
    }


    private static void bottemHexagon(TETile[][] world, Position topPosition, int s) {
        int top_x = topPosition.x;
        int top_y = topPosition.y;

        int bottem_x = top_x;
        int bottem_y = top_y - 8 * s;
        Position bottem = new Position(bottem_x, bottem_y);
        addHexagon(world, bottem, s, randomTile());

        int upperLeft_x = top_x - 2 * s + 1;
        int upperLeft_y = top_y - 7 * s;
        Position upperLeft = new Position(upperLeft_x, upperLeft_y);
        addHexagon(world, upperLeft, s, randomTile());

        int upper_x = top_x;
        int upper_y = top_y - 6 * s;
        Position upper = new Position(upper_x, upper_y);
        addHexagon(world, upper, s, randomTile());

        int upperRight_x = top_x + 2 * s - 1;
        int upperRight_y = top_y - 7 * s;
        Position upperRight = new Position(upperRight_x, upperRight_y);
        addHexagon(world, upperRight, s, randomTile());
    }

    private static void middleHexagon(TETile[][] world, Position topPosition, int s) {
        int top_x = topPosition.x;
        int top_y = topPosition.y;

        int middle_x = top_x;
        int middle_y = top_y - 4 * s;
        Position middle = new Position(middle_x, middle_y);
        addHexagon(world, middle, s, randomTile());
    }



    public static void main(String[] args) {
        int WIDTH = 85;
        int HEIGHT = 48;

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        Position p = new Position(30, 44);
        TesselationOfHexagons(world, p, 4);


        ter.renderFrame(world);
    }
}
