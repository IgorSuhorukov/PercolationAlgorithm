public class Percolation {
    private final int[][] grid;
    private final int gridSize;
    private int numberOfOpenSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0 ) {
            throw new IllegalArgumentException();
        }

        int cellName = 0;
        this.gridSize = n;
        this.grid = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.grid[i][j] = cellName;
                cellName++;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row = row - 1;
        col = col - 1;
        this.validate(row, col);
        this.numberOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row = row - 1;
        col = col - 1;
        this.validate(row, col);
        return this.grid[row][col] != this.gridSize * row + col;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row = row - 1;
        col = col - 1;
        this.validate(row, col);
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {

        return false;
    }

    private void validate(int row, int col) {
        if (row >= this.gridSize || row < 0 || col >= this.gridSize || col < 0) {
            throw new IllegalArgumentException();
        }
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
