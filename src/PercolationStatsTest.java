import org.junit.Test;

public class PercolationStatsTest {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrows1() {
        new PercolationStats(-1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrows2() {
        new PercolationStats(0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrows3() {
        new PercolationStats(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrows4() {
        new PercolationStats(1, 0);
    }
    @Test
    public void testStddev() {

    }

    @Test
    public void testConfidenceLo() {

    }

    @Test
    public void testConfidenceHi() {

    }
}
