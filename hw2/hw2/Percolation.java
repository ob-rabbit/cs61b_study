package hw2;


public class Percolation {

    private int[][] map;
    private int N;
    private int openSite;
    private MyUnion topSites;
    private int top;
    private int bottom;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = 0;
            }
        }
        this.openSite = 0;
        this.N = N;
        topSites = new MyUnion(N * N + 2);
        top = N * N;
        bottom = N * N + 1;
        for (int i = 0; i < N; i++) {
            topSites.union(top, xyToIndex(0, i));
            topSites.union(bottom, xyToIndex(N - 1, i));
        }


    }

    public void open(int row, int col) {
        if (map[row][col] == 1) {
            return;
        }
        map[row][col] = 1;
        openSite++;
        unionNext(row, col, row, col + 1);
        unionNext(row, col, row + 1, col);
        unionNext(row, col, row, col - 1);
        unionNext(row, col, row - 1, col);

    }

    private void unionNext(int x, int y, int next_x, int next_y) {
        if (next_x < 0 || next_x >= N || next_y < 0 || next_y >= N) {
            return;
        }
        if (map[next_x][next_y] == 1) {
            topSites.union(xyToIndex(x, y), xyToIndex(next_x, next_y));
        }
    }

    public boolean isOpen(int row, int col) {
        return map[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }

        return topSites.isUnion(xyToIndex(row, col), top);
    }

    public int numberOfOpenSites() {
        return openSite;
    }

    public boolean percolates() {
        if (openSite == 0) {
            return false;
        }
        return topSites.isUnion(top, bottom);
    }

    private int xyToIndex(int x, int y) {
        return x * N + y;
    }


}
