package hw4.puzzle;

import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Bunny
 * @create 2022-01-20 14:29
 */
public class Solver {

    private WorldState puzzle;

    /**
     * the minimum number of moves to solve the puzzle
     */
    private int minMoves;

    /**
     * a sequence of WorldStates from the initial WorldState
     * to the solution
     */
    private Deque<WorldState> path;

    private PriorityQueue<Node> queue;

    private Map<WorldState, Integer> priorityMap;

    private class Node {
        private WorldState value;

        private Integer priority;

        private Integer moves;

        private Node parent;

        public Node(WorldState value, Integer priority, Integer e, Node parent) {
            this.priority = priority;
            this.value = value;
            this.moves = e;
            this.parent = parent;
        }
    }

    public Solver(WorldState initial) {
        this.puzzle = initial;
        this.minMoves = 0;
        path = new LinkedList<>();
        priorityMap = new HashMap<>();
        queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.priority.compareTo(o2.priority);
            }
        });

        aStartSearch();

    }

    private void aStartSearch() {
        //记录访问过的状态
        Map<WorldState, Boolean> visited = new HashMap<>();
        //开始状态入队
        queue.add(new Node(puzzle, 0, 0, null));

        while (!queue.isEmpty()) {
            //优先级最高的状态出队
            Node curNode = queue.remove();
            visited.put(curNode.value, true);
            //如果当前状态是目标状态
            if (curNode.value.isGoal()) {
                minMoves = curNode.moves;
                while (curNode != null) {
                    path.addFirst(curNode.value);
                    curNode = curNode.parent;
                }
                break;
            }
            int distanceToStart = 1 + curNode.moves;
            //将当前状态的相邻状态入队
            for (WorldState neighbor : curNode.value.neighbors()) {
                if (!visited.containsKey(neighbor)) {
                    visited.put(neighbor, true);
                    int priority = calPriority(neighbor, distanceToStart);
                    queue.add(new Node(neighbor, priority, distanceToStart, curNode));
                }
            }
        }
    }

    private int calPriority(WorldState state, int moves) {
        if (priorityMap.containsKey(state)) {
            return priorityMap.get(state);
        }
        int temp = moves + state.estimatedDistanceToGoal();
        priorityMap.put(state, temp);
        return temp;
    }


    public int moves() {
        return minMoves;

    }

    public Iterable<WorldState> solution() {
        return path;
    }

}
