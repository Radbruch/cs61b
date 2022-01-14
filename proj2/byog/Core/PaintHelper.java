package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class PaintHelper {
    public final void top(TETile[][] world, int x, int y, int width) {
        for (int i = x; i <= x + width + 1; i++) {
            world[i][y] = Tileset.WALL;
        }
    }

    public final void middle(TETile[][] world, int x, int y, int length) {
        world[x][y] = Tileset.WALL;
        world[x + length + 1][y] = Tileset.WALL;
        for (int i = x + 1; i <= x + length; i++) {
            world[i][y] = Tileset.FLOOR;
        }
    }
}
