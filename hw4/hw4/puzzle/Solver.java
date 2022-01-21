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

        aStartSearch();

    }

    private void aStartSearch() {
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.priority - o2.priority;
            }
        });

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
            //将当前状态的相邻状态入队
            for (WorldState neighbor : curNode.value.neighbors()) {
                if (visited.containsKey(neighbor)) {
                    continue;
                }
                if (curNode.parent != null && neighbor.equals(curNode.parent.value)) {
                    continue;
                }

                int priority = calPriority(neighbor, curNode.moves + 1);
                queue.add(new Node(neighbor, priority, curNode.moves + 1, curNode));
            }
        }
    }

    private int calPriority(WorldState state, int moves) {
        if (!priorityMap.containsKey(state)) {
            priorityMap.put(state, state.estimatedDistanceToGoal());
        }

        return priorityMap.get(state) + moves;
    }


    public int moves() {
        return minMoves;

    }

    public Iterable<WorldState> solution() {
        return path;
    }

}
