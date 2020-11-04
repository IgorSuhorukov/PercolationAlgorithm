import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double CONST = 1.96;
    private final double[] threshold;
    private final double trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        this.trials = trials;
        this.threshold = new double[trials];
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.threshold);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.threshold);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - (CONST * this.stddev()) / Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + (CONST * this.stddev()) / Math.sqrt(this.trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            boolean percolates = false;
            while (!percolates) {
                int current = percolation.numberOfOpenSites();
                int openSites = current;

                while (current == openSites) {
                    percolation.open(getRandomNumber(n), getRandomNumber(n));
                    openSites = percolation.numberOfOpenSites();
                }

                percolates = percolation.percolates();
            }
            int openSites = percolation.numberOfOpenSites();
            percolationStats.updateThreshold(i, (double) openSites / (n * n));
        }

        System.out.printf("%-30s = %s%n", "mean", percolationStats.mean());
        System.out.printf("%-30s = %s%n", "stddev", percolationStats.stddev());
        System.out.printf(
                "%-30s = [%s,%s]%n", "95% confidence interval",
                percolationStats.confidenceHi(),
                percolationStats.confidenceLo()
        );
    }

    private void updateThreshold(int i, double v) {
        this.threshold[i] = v;
    }

    private static int getRandomNumber(int max) {
        if (max == 1) {
            return 1;
        }
        return StdRandom.uniform(1, max + 1);
    }
}
