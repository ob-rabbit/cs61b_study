package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private int xt;
    private int yt;

    private static Comparator<Node> nodeComparator = new Comparator<Node>() {
        public int compare(Node o1, Node o2) {
            return o1.priority.compareTo(o2.priority);
        }

    };

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        xt = targetX;
        yt = targetY;
    }

    /**
     * Estimate of the distance from v to the target.
     */
    private int h(int v) {
        int x = maze.toX(v);
        int y = maze.toY(v);
        return Math.abs(xt - x) + Math.abs(yt - y);
    }

    /**
     * Performs an A star search from vertex s.
     */
    private void astar(int s) {
        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(nodeComparator);
        marked[s] = true;
        announce();
        priorityQueue.add(new Node(0,s));

        Node curNode;
        while (!priorityQueue.isEmpty()){
            curNode = priorityQueue.peek();
            priorityQueue.remove();
            if (curNode.v == t){
                break;
            }

            for (int w:maze.adj(curNode.v)){
                if (!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = curNode.v;
                    distTo[w] = distTo[curNode.v] + 1;
                    announce();
                    int priority = calculatePriority(w);
                    priorityQueue.add(new Node(priority,w));
                }
            }
        }

    }

    private int calculatePriority(int v) {
        return distTo[v]+h(v);
    }

    @Override
    public void solve() {
        astar(s);
    }

    private static class Node {
        public Integer priority;
        public Integer v;

        public Node(Integer priority, Integer v) {
            this.priority = priority;
            this.v = v;
        }
    }

}

