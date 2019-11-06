import com.company.board.JailSquare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestJailSquare {
    JailSquare jail = new JailSquare("Eray",0);


    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetName() {
        jail.setName("abc");
        assertEquals("abc",jail.getName());
    }

    @Test
    public void testSetIndex() {
        jail.setIndex(1);
        assertEquals(1,jail.getIndex());
    }

    @Test
    public void testGetName() {
        assertEquals("Eray",jail.getName());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0,jail.getIndex());
    }
}