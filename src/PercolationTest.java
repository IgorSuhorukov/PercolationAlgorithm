import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class PercolationTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsWhenZero() {
        new Percolation(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsWhenNegativeInteger() {
        new Percolation(-1);
    }

    @Test
    public void testOpen() {
        Percolation percolation = new Percolation(1);
        percolation.open(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentExceptionOnColumn() {
        int n = 10;
        Percolation percolation = new Percolation(n);
        percolation.open(1, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentExceptionOnRow() {
        int n = 10;
        Percolation percolation = new Percolation(n);
        percolation.open(11, 1);
    }

    @Test
    public void testIsOpen() {
        Percolation percolation = new Percolation(1);
        assertFalse(percolation.isOpen(1, 1));

        percolation = new Percolation(2);
        assertFalse(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(1, 2));
        assertFalse(percolation.isOpen(2, 1));
        assertFalse(percolation.isOpen(2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionColumn() {
        Percolation percolation = new Percolation(10);
        percolation.isOpen(1, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionColumnZero() {
        Percolation percolation = new Percolation(10);
        percolation.isOpen(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionColumnNegative() {
        Percolation percolation = new Percolation(10);
        percolation.isOpen(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionRow() {
        Percolation percolation = new Percolation(10);
        percolation.isOpen(11, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionRowZero() {
        Percolation percolation = new Percolation(10);
        percolation.isOpen(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionNegative() {
        Percolation percolation = new Percolation(10);
        percolation.isOpen(-1, 1);
    }

    @Test
    public void testIsFull() {
        Percolation percolation = new Percolation(1);
        percolation.isFull(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFullThrowsIlleagalArgumentExceptionColumn() {
        Percolation percolation = new Percolation(5);
        percolation.isFull(5, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFullThrowsIlleagalArgumentExceptionRow() {
        Percolation percolation = new Percolation(10);
        percolation.isFull(11, 1);
    }

    @Test
    public void testNumberOfOpenSites() {
        Percolation percolation = new Percolation(1);
        percolation.numberOfOpenSites();
    }

    @Test
    public void testPercolates() {
        Percolation percolation = new Percolation(1);
        percolation.percolates();
    }
}
