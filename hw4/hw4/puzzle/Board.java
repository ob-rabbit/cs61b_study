package hw4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Board implements WorldState {

    private static final int BLANK = 0;
    private static final int[][] next = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    private int N;

    private int[][] tiles;

    public Board(int[][] tiles) {
        this.tiles = new int[tiles.length][tiles.length];
        this.N = tiles.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (!isLegal(i, j)) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }

    public int size() {
        return N;
    }

    /**
     * The number of tiles in the wrong position
     */
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == BLANK) {
                    continue;
                }
                if (tiles[i][j] != xyTo1D(i, j)) {
                    sum++;
                }
            }

        }
        return sum;
    }

    private int xyTo1D(int i, int j) {
        return i * N + j + 1;
    }

    /**
     * The sum of the Manhattan distances (sum of the vertical and horizontal
     * distance) from the tiles to their goal positions
     *
     * @return
     */
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == BLANK) {
                    continue;
                }
                if (tiles[i][j] != xyTo1D(i, j)) {
                    int x = (tiles[i][j] - 1) / N;
                    int y = (tiles[i][j] - 1) % N;

                    sum += Math.abs(x - i) + Math.abs(y - j);
                }
            }
        }

        return sum;
    }

    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }
        Board other = ((Board) y);
        if (other.size() != this.N) {
            return false;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (other.tileAt(i, j) != this.tileAt(i, j)) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public Iterable<WorldState> neighbors() {
        List<WorldState> neighbors = new ArrayList<>();
        int[][] newTiles = new int[N][N];
        int i = 0, j = 0;
        for (int i1 = 0; i1 < N; i1++) {
            for (int j1 = 0; j1 < N; j1++) {
                if (tiles[i1][j1] == BLANK) {
                    i = i1;
                    j = j1;
                }
                newTiles[i1][j1] = tiles[i1][j1];
            }
        }

        for (int k = 0; k < 4; k++) {
            int newi = i + next[k][0];
            int newj = j + next[k][1];
            if (isLegal(newi, newj)) {
                swap(newTiles, i, j, newi, newj);
                neighbors.add(new Board(newTiles));
                swap(newTiles, i, j, newi, newj);
            }
        }

        return neighbors;

    }


    private void swap(int[][] newTiles, int i, int j, int newi, int newj) {
        int temp = newTiles[i][j];
        newTiles[i][j] = newTiles[newi][newj];
        newTiles[newi][newj] = temp;
    }

    private boolean isLegal(int newi, int newj) {
        if (newi < 0 || newj >= N || newj < 0 || newi >= N) {
            return false;
        }
        return true;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }


}
