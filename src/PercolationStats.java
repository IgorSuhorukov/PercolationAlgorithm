import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static double[] threshold;
    private final int n;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.n = n;
        this.trials = trials;
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (1.96 * this.stddev()) / Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (1.96 * this.stddev()) / Math.sqrt(this.trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        threshold = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(n);
            boolean percolates = false;
            while (!percolates) {
                percolation.open(getRandomNumber(n), getRandomNumber(n));
                percolates = percolation.percolates();
            }
            int openSites = percolation.numberOfOpenSites();
            threshold[i] = (double) openSites / (n * n);
        }

        PercolationStats percolationStats = new PercolationStats(n, T);
        System.out.printf("%-30s = %s%n", "mean", percolationStats.mean());
        System.out.printf("%-30s = %s%n", "stddev", percolationStats.stddev());
        System.out.printf("%-30s = [%s,%s]%n", "95% confidence interval", percolationStats.confidenceHi(), percolationStats.confidenceLo());
    }

    private static int getRandomNumber(int max) {
        if (max == 1) {
            return 1;
        }
        return StdRandom.uniform(1, max + 1);
    }
}
