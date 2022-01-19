package lab11.graphs;

import java.util.LinkedList;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        t = maze.xyTo1D(m.N(), m.N());
        distTo[s] = 0;
    }

    @Override
    public void solve() {
        cyclesCheckByDFS(0);

    }

    // Helper methods go here

    private void cyclesCheckByDFS(int v) {
        LinkedList<Integer> stack = new LinkedList<>();

        marked[v] = true;
        announce();

        stack.addLast(v);
        Integer curNode;
        Integer parentNode = -1;
        int flag = 0;
        while (!stack.isEmpty()) {
            flag = 0;
            curNode = stack.peekLast();
            if (curNode == t) {
                return;
            }
            for (int w : maze.adj(curNode)) {

                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[curNode] + 1;
                    announce();
                    parentNode = curNode;
                    int cycleStart = checkCycles(w, parentNode);
                    if (cycleStart != -1) {
                        int node;
                        int cur = w;
                        while ((node = stack.removeLast()) != cycleStart) {
                            edgeTo[cur] = node;
                            announce();
                            cur = node;
                        }
                        edgeTo[cur] = node;
                        edgeTo[cycleStart] = w;
                        announce();
                        return;
                    }
                    stack.addLast(w);
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                stack.removeLast();
            }
        }
    }

    private int checkCycles(int curNode, Integer parentNode) {
        for (int w : maze.adj(curNode)) {
            if (w != parentNode && marked[w]) {
                return w;
            }
        }
        return -1;
    }
}

