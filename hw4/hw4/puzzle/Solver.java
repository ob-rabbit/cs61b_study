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

    private Map<String, Integer> priorityMap;

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
        Map<String, Boolean> visited = new HashMap<>();
        queue.add(new Node(puzzle, 0, 0, null));
        Word word;
        while (!queue.isEmpty()) {
            Node curNode = queue.remove();
            word = (Word) curNode.value;
            visited.put(word.getWord(), true);
            if (curNode.value.isGoal()) {
                minMoves = curNode.estimatedDistanceToStart;
                while (curNode != null) {
                    path.addFirst(curNode.value);
                    curNode = curNode.parent;
                }
                break;
            }
            int distanceToStart = 1 + curNode.estimatedDistanceToStart;
            for (WorldState neighbor : curNode.value.neighbors()) {
                word = (Word) neighbor;
                if (!visited.containsKey(word.getWord())) {
                    visited.put(word.getWord(), true);
                    int priority = calPriority(word, distanceToStart);
                    queue.add(new Node(word, priority, distanceToStart, curNode));
                }
            }
        }
    }

    private int calPriority(Word state, int f) {
        if (priorityMap.containsKey(state.getWord())) {
            return priorityMap.get(state.getWord());
        }
        int temp = f + state.estimatedDistanceToGoal();
        priorityMap.put(state.getWord(), temp);
        return temp;
    }


    public int moves() {

        return minMoves;

    }

    public Iterable<WorldState> solution() {
        return path;
    }

    private class Node {
        public WorldState value;

        private Integer priority;

        private Integer estimatedDistanceToStart;

        private Node parent;

        public Node(WorldState value, Integer priority, Integer e, Node parent) {
            this.priority = priority;
            this.value = value;
            this.estimatedDistanceToStart = e;
            this.parent = parent;
        }
    }

}
