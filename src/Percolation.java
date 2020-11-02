import java.util.Arrays;

public class Percolation {
    private final int gridSize;
    private final int topVirtualPoint;
    private final int bottomVirtualPoint;
    private final int[][] grid;
    private int openSiteAmount;
    private final int[] integers;
    private final int[] sizes;

    public Percolation(int n) {
        this.gridSize = n;
        this.grid = new int[n][n];
        this.integers = new int[n * n + 2];
        this.sizes = new int[integers.length];
        Arrays.fill(this.sizes, 1);

        int cellName = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.integers[cellName] = cellName;
                this.grid[i][j] = -1;
                cellName++;
            }
        }

        this.topVirtualPoint = n * n;
        this.bottomVirtualPoint = n * n + 1;
        this.integers[this.topVirtualPoint] = this.topVirtualPoint;
        this.integers[this.bottomVirtualPoint] = this.bottomVirtualPoint;
    }

    public void open(int row, int col) {
        if (this.isOpen(row, col)) {
            return;
        }
        this.grid[row][col] = row * this.gridSize + col;
        this.connectNearbyOpenCells(row, col);
        this.openSiteAmount++;
    }

    public boolean isOpen(int row, int col) {
        return this.grid[row][col] != -1;
    }

    public boolean isFull(int row, int col) {
        return this.grid[row][col] == -1;
    }

    public int numberOfOpenSites() {
        return this.openSiteAmount;
    }

    public boolean percolates() {
        return connected(this.bottomVirtualPoint, this.topVirtualPoint);
    }

    private void connectNearbyOpenCells(int row, int col) {
        if (row + 1 >= this.gridSize) {
            this.union(this.bottomVirtualPoint, this.grid[row][col]);
        } else {
            if (this.isOpen(row + 1, col)) {
                this.union(this.grid[row + 1][col], this.grid[row][col]);
            }
        }

        if (row - 1 < 0) {
            this.union(this.topVirtualPoint, this.grid[row][col]);
        } else {
            if (this.isOpen(row - 1, col)) {
                this.union(this.grid[row - 1][col], this.grid[row][col]);
            }
        }

        if (col + 1 < this.gridSize && this.isOpen(row, col + 1)) {
            this.union(this.grid[row][col + 1], this.grid[row][col]);
        }

        if (col - 1 >= 0 && this.isOpen(row, col - 1)) {
            this.union(this.grid[row][col - 1], this.grid[row][col]);
        }
    }

    private boolean connected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    private int[] union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);

        if (rootA == rootB) {
            return this.integers;
        }

        if (this.sizes[rootA] < this.sizes[rootB]) {
            this.integers[rootA] = rootB;
            this.sizes[rootB] += this.sizes[rootA];
        } else {
            this.integers[rootB] = rootA;
            this.sizes[rootA] += this.sizes[rootB];
        }

        return this.integers;
    }

    private int findRoot(int i) {
        while (i != this.integers[i]) {
            i = this.integers[i];
        }
        return i;
    }

    public static void main(String[] args) {

    }
}
