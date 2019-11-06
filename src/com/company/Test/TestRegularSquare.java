import com.company.board.RegularSquare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRegularSquare {
    RegularSquare reg = new RegularSquare("Eray",0);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetName() {
        reg.setName("abc");
        assertEquals("abc",reg.getName());
    }

    @Test
    public void testSetIndex() {
        reg.setIndex(1);
        assertEquals(1,reg.getIndex());
    }

    @Test
    public void testGetName() {
        assertEquals("Eray",reg.getName());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0,reg.getIndex());
    }
}