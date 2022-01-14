package hw2;


import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {

    private int N;
    private int T;
    private double[] openSites;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        openSites = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (percolation.isOpen(x, y));
                percolation.open(x, y);
            }
            openSites[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }

    /**
     * 计算平均值
     * @return
     */
    public double mean() {
        return StdStats.mean(openSites);

    }

    /**
     * 计算样本偏差
     * @return
     */
    public double stddev() {
        return StdStats.stddev(openSites);

    }

    /*
    置信区间
     */
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }


}
