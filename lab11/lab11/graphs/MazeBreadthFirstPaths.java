package lab11.graphs;

import java.util.LinkedList;

/**
 * @author Josh Hug
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
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /**
     * Conducts a breadth first search of the maze starting at the source.
     */
    private void bfs() {
        LinkedList<Integer> queue = new LinkedList<>();
        marked[s] = true;
        announce();
        queue.addLast(s);
        Integer curNode = s;

        while (!queue.isEmpty()) {
            curNode = queue.peekFirst();
            queue.pop();
            marked[curNode] = true;
            announce();
            if (curNode == t) {
                break;
            }
            for (int w : maze.adj(curNode)) {
                if (!marked[w]) {
                    edgeTo[w] = curNode;
                    announce();
                    distTo[w] = distTo[curNode] + 1;
                    queue.addLast(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

