import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

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
    public void testOpenThrowsIllegalArgumentException1() {
        Percolation percolation = new Percolation(2);
        percolation.open(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentException2() {
        Percolation percolation = new Percolation(2);
        percolation.open(3, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentException3() {
        Percolation percolation = new Percolation(1);
        percolation.open(1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentException4() {
        Percolation percolation = new Percolation(1);
        percolation.open(1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentException5() {
        Percolation percolation = new Percolation(1);
        percolation.open(2, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOpenThrowsIllegalArgumentException6() {
        Percolation percolation = new Percolation(1);
        percolation.open(0, 1);
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
        assertFalse(percolation.isFull(1, 1));
    }

    @Test
    public void testIsFull2() {
        Percolation percolation = new Percolation(10);
        percolation.open(1, 1);
        assertTrue(percolation.isFull(1, 1));

        percolation.open(1, 2);
        assertTrue(percolation.isFull(1, 2));

        percolation.open(2, 2);
        assertTrue(percolation.isFull(2, 2));

        percolation.open(3, 2);
        assertTrue(percolation.isFull(3, 2));

        percolation.open(4, 2);
        assertTrue(percolation.isFull(4, 2));

        percolation.open(5, 3);
        assertFalse(percolation.isFull(5, 3));

        percolation.open(5, 2);
        assertTrue(percolation.isFull(5, 2));
        assertTrue(percolation.isFull(5, 3));

        percolation.open(5, 5);
        assertFalse(percolation.isFull(5, 5));

        percolation.open(4, 5);
        assertFalse(percolation.isFull(4, 5));

        percolation.open(3, 5);
        assertFalse(percolation.isFull(3, 5));

        percolation.open(2, 5);
        assertFalse(percolation.isFull(2, 5));

        percolation.open(1, 5);
        assertTrue(percolation.isFull(1, 5));
        assertTrue(percolation.isFull(2, 5));
        assertTrue(percolation.isFull(3, 5));
        assertTrue(percolation.isFull(4, 5));
        assertTrue(percolation.isFull(5, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFullThrowsIllegalArgumentExceptionColumn() {
        Percolation percolation = new Percolation(5);
        percolation.isFull(5, 6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsFullThrowsIlleagalArgumentExceptionRow() {
        Percolation percolation = new Percolation(10);
        percolation.isFull(11, 1);
    }

    @Test
    public void testIsFullIsOpen() {
        Percolation percolation = new Percolation(2);
        assertFalse(percolation.isFull(1, 1));
        assertFalse(percolation.isOpen(1, 1));
    }

    @Test
    public void testNumberOfOpenSites() {
        Percolation percolation = new Percolation(1);
        assertEquals(0, percolation.numberOfOpenSites());
    }

    @Test
    public void testPercolates1() {
        Percolation percolation = new Percolation(1);
        percolation.open(1, 1);
        assertTrue(percolation.percolates());
    }

    @Test
    public void testPercolates2() {
        Percolation percolation = new Percolation(2);
        percolation.open(1, 1);
        assertFalse(percolation.percolates());
        percolation.open(1, 2);
        assertFalse(percolation.percolates());
        percolation.open(2, 2);
        assertTrue(percolation.percolates());
        percolation.open(2, 1);
        assertTrue(percolation.percolates());
        assertEquals(4, percolation.numberOfOpenSites());
    }

    @Test
    public void testPercolates4() {
        // Case 1
        Percolation percolation = new Percolation(4);
        percolation.open(1, 1);
        assertFalse(percolation.percolates());
        assertEquals(1, percolation.numberOfOpenSites());

        percolation.open(2, 1);
        assertFalse(percolation.percolates());
        assertEquals(2, percolation.numberOfOpenSites());

        percolation.open(3, 1);
        assertFalse(percolation.percolates());
        assertEquals(3, percolation.numberOfOpenSites());

        percolation.open(4, 1);
        assertTrue(percolation.percolates());
        assertEquals(4, percolation.numberOfOpenSites());

        // Case 2
        percolation = new Percolation(4);
        percolation.open(1, 2);
        assertFalse(percolation.percolates());
        assertEquals(1, percolation.numberOfOpenSites());

        percolation.open(2, 1);
        assertFalse(percolation.percolates());
        assertEquals(2, percolation.numberOfOpenSites());

        percolation.open(3, 1);
        assertFalse(percolation.percolates());
        assertEquals(3, percolation.numberOfOpenSites());

        percolation.open(4, 1);
        assertFalse(percolation.percolates());
        assertEquals(4, percolation.numberOfOpenSites());

        // Case 3
        percolation = new Percolation(4);
        percolation.open(4, 1);
        assertFalse(percolation.percolates());
        assertEquals(1, percolation.numberOfOpenSites());

        percolation.open(3, 4);
        assertFalse(percolation.percolates());
        assertEquals(2, percolation.numberOfOpenSites());

        percolation.open(2, 4);
        assertFalse(percolation.percolates());
        assertEquals(3, percolation.numberOfOpenSites());

        percolation.open(3, 2);
        assertFalse(percolation.percolates());
        assertEquals(4, percolation.numberOfOpenSites());

        percolation.open(2, 3);
        assertFalse(percolation.percolates());
        assertEquals(5, percolation.numberOfOpenSites());

        percolation.open(1, 4);
        assertFalse(percolation.percolates());
        assertEquals(6, percolation.numberOfOpenSites());

        percolation.open(4, 3);
        assertFalse(percolation.percolates());
        assertEquals(7, percolation.numberOfOpenSites());

        percolation.open(2, 3); // duplicate
        assertFalse(percolation.percolates());
        assertEquals(7, percolation.numberOfOpenSites());

        percolation.open(2, 2);
        assertFalse(percolation.percolates());
        assertEquals(8, percolation.numberOfOpenSites());

        percolation.open(3, 1);
        assertTrue(percolation.percolates());
        assertEquals(9, percolation.numberOfOpenSites());
    }

    @Test
    public void testPercolates5() {
        // Case 1
        Percolation percolation = new Percolation(4);
        percolation.open(1, 4);
        assertFalse(percolation.percolates());
        assertEquals(1, percolation.numberOfOpenSites());

        percolation.open(2, 4);
        assertFalse(percolation.percolates());
        assertEquals(2, percolation.numberOfOpenSites());

        percolation.open(3, 4);
        assertFalse(percolation.percolates());
        assertEquals(3, percolation.numberOfOpenSites());

        percolation.open(4, 4);
        assertTrue(percolation.percolates());
        assertEquals(4, percolation.numberOfOpenSites());
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

    @Test
    public void testPercolatesWithLargeNumber2() {
        int gridSize = 20;
        Percolation percolation = new Percolation(gridSize);
        boolean percolates = false;

        while (!percolates) {
            int current = percolation.numberOfOpenSites();
            int openSites = current;

            while (current == openSites) {
                percolation.open(PercolationTest.getRandomNumberInRange(gridSize), PercolationTest.getRandomNumberInRange(gridSize));
                openSites = percolation.numberOfOpenSites();
            }
            percolates = percolation.percolates();
        }
        assertTrue(percolates);
    }

    private static int getRandomNumberInRange(int range) {
        Random random = new Random();
        return random.nextInt(range) + 1;
    }
}
