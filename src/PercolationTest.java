import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class PercolationTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsWhenZero() {
        new IncorrectPercolation(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsWhenNegativeInteger() {
        new IncorrectPercolation(-1);
    }

    @Test
    public void testOpen() {
        IncorrectPercolation percolation = new IncorrectPercolation(1);
        percolation.open(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentExceptionOnColumn() {
        int n = 10;
        IncorrectPercolation percolation = new IncorrectPercolation(n);
        percolation.open(1, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentExceptionOnRow() {
        int n = 10;
        IncorrectPercolation percolation = new IncorrectPercolation(n);
        percolation.open(11, 1);
    }

    @Test
    public void testIsOpen() {
        IncorrectPercolation percolation = new IncorrectPercolation(1);
        assertFalse(percolation.isOpen(1, 1));

        percolation = new IncorrectPercolation(2);
        assertFalse(percolation.isOpen(1, 1));
        assertFalse(percolation.isOpen(1, 2));
        assertFalse(percolation.isOpen(2, 1));
        assertFalse(percolation.isOpen(2, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionColumn() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isOpen(1, 11);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionColumnZero() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isOpen(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionColumnNegative() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isOpen(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionRow() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isOpen(11, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionRowZero() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isOpen(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsOpenThrowsIllegalArgumentExceptionNegative() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isOpen(-1, 1);
    }

    @Test
    public void testIsFull() {
        IncorrectPercolation percolation = new IncorrectPercolation(1);
        percolation.isFull(1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFullThrowsIllegalArgumentExceptionColumn() {
        IncorrectPercolation percolation = new IncorrectPercolation(5);
        percolation.isFull(5, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFullThrowsIlleagalArgumentExceptionRow() {
        IncorrectPercolation percolation = new IncorrectPercolation(10);
        percolation.isFull(11, 1);
    }

    @Test
    public void testNumberOfOpenSites() {
        IncorrectPercolation percolation = new IncorrectPercolation(1);
        assertEquals(0, percolation.numberOfOpenSites());
    }

    @Test
    public void testPercolates1() {
        Percolation percolation = new Percolation(1);
        percolation.open(0, 0);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testPercolates2() {
        Percolation percolation = new Percolation(2);
        percolation.open(0, 0);
        assertFalse(percolation.percolates());
        percolation.open(0, 1);
        assertFalse(percolation.percolates());
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
        percolation.open(1, 0);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testPercolates4() {
        Percolation percolation = new Percolation(4);
        percolation.open(0, 0);
        assertFalse(percolation.percolates());
        percolation.open(1, 0);
        assertFalse(percolation.percolates());
        percolation.open(2, 0);
        assertFalse(percolation.percolates());
        percolation.open(3, 0);
        assertTrue(percolation.percolates());

        percolation = new Percolation(4);
        percolation.open(0, 1);
        assertFalse(percolation.percolates());
        percolation.open(1, 0);
        assertFalse(percolation.percolates());
        percolation.open(2, 0);
        assertFalse(percolation.percolates());
        percolation.open(3, 0);
        assertFalse(percolation.percolates());
    }

    @Test
    public void testPercolatesWithLargeNumber() {
        int gridSize = 100;
        Percolation percolation = new Percolation(gridSize);
        boolean percolates = false;
        while (!percolates) {
            percolation.open(PercolationTest.getRandomNumberInRange(gridSize), PercolationTest.getRandomNumberInRange(gridSize));
            percolates = percolation.percolates();
        }
        assertTrue(percolates);
    }

    private static int getRandomNumberInRange(int range) {
        Random random = new Random();
        return random.nextInt(range);
    }
}
