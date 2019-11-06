import com.company.board.GoSquare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGoSquare {
    GoSquare go = new GoSquare("Eray",0,200);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getGoSquareMoney() {
        assertEquals(200,go.getGoSquareMoney());
    }

    @Test
    public void setGoSquareMoney() {
        go.setGoSquareMoney(100);
        assertEquals(100,go.getGoSquareMoney());
    }
    @Test
    public void testSetName() {
        go.setName("abc");
        assertEquals("abc",go.getName());
    }

    @Test
    public void testSetIndex() {
        go.setIndex(1);
        assertEquals(1,go.getIndex());
    }

    @Test
    public void testGetName() {
        assertEquals("Eray",go.getName());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0,go.getIndex());
    }
}