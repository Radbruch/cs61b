package lab11.graphs;


import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;

    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> fringe = new Queue();
        fringe.enqueue(s);
        marked[s] = true;
        announce();
        while (!fringe.isEmpty()) {
            int v = fringe.dequeue();
            for (int w: maze.adj(v)) {
                if (targetFound == true) {
                    return;
                }
                if (!marked[w]) {
                    fringe.enqueue(w);
                    marked[w] = true;
                    announce();
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();

                    if (w == t){
                        targetFound = true;
                    }
                }
            }
        }

    }


    @Override
    public void solve() {
        bfs();
    }
}

