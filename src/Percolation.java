public class Percolation {
    private final int gridSize;
    private final int topVirtualPoint;
    private final int bottomVirtualPoint;
    private final boolean[][] grid;
    private int openSiteAmount;
    private final int[] integers;
    private final int[] sizes;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.gridSize = n;
        this.grid = new boolean[n][n];
        this.integers = new int[n * n + 2];
        this.sizes = new int[integers.length];

        int cellName = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.integers[cellName] = cellName;
                this.sizes[cellName] = 1;
                this.grid[i][j] = false;
                cellName++;
            }
        }

        this.topVirtualPoint = n * n;
        this.bottomVirtualPoint = n * n + 1;
        this.integers[this.topVirtualPoint] = this.topVirtualPoint;
        this.integers[this.bottomVirtualPoint] = this.bottomVirtualPoint;
    }

    public void open(int row, int col) {
        if (this.isOpen(row, col)) return;
        this.grid[row - 1][col - 1] = true;
        this.connectNearbyOpenCells(row, col);
        this.openSiteAmount++;
    }

    public boolean isOpen(int row, int col) {
        this.validate(row, col);
        return this.grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        this.validate(row, col);
        return connected(this.topVirtualPoint, this.getItemName(row - 1, col - 1));
    }

    public int numberOfOpenSites() {
        return this.openSiteAmount;
    }

    public boolean percolates() {
        return connected(this.bottomVirtualPoint, this.topVirtualPoint);
    }

    private void connectNearbyOpenCells(int row, int col) {
        if (row == this.gridSize) {
            this.union(this.bottomVirtualPoint, this.getItemName(row - 1, col - 1));
        } else {
            if (this.isOpen(row + 1, col)) {
                this.union(this.getItemName(row,col - 1), this.getItemName(row - 1, col - 1));
            }
        }

        if (row - 2 < 0) {
            this.union(this.topVirtualPoint, this.getItemName(row - 1, col - 1));
        } else {
            if (this.isOpen(row - 1, col)) {
                this.union(this.getItemName(row - 2,col - 1), this.getItemName(row - 1, col - 1));
            }
        }

        if (col < this.gridSize && this.isOpen(row, col + 1)) {
            this.union(this.getItemName(row - 1, col), this.getItemName(row - 1, col - 1));
        }

        if (col - 2 >= 0 && this.isOpen(row, col - 1)) {
            this.union(this.getItemName(row - 1, col - 2), this.getItemName(row - 1, col - 1));
        }
    }

    private int getItemName(int row, int col) {
        return this.gridSize * row + col;
    }

    private boolean connected(int a, int b) {
        return findRoot(a) == findRoot(b);
    }

    private void union(int a, int b) {
        int rootA = findRoot(a);
        int rootB = findRoot(b);

        if (rootA == rootB) {
            return;
        }

        if (this.sizes[rootA] < this.sizes[rootB]) {
            this.integers[rootA] = rootB;
            this.sizes[rootB] += this.sizes[rootA];
        } else {
            this.integers[rootB] = rootA;
            this.sizes[rootA] += this.sizes[rootB];
        }
    }

    private int findRoot(int i) {
        while (i != this.integers[i]) {
            i = this.integers[i];
        }
        return i;
    }

    private void validate(int row, int col) {
        if (row > this.gridSize || row < 1 || col > this.gridSize || col < 1) {
            throw new IllegalArgumentException();
        }
    }
}
