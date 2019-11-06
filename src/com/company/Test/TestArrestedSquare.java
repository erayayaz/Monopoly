import com.company.board.ArrestedSquare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrestedSquare {
    ArrestedSquare arrested = new ArrestedSquare("Eray",0);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testSetName() {
        arrested.setName("abc");
        assertEquals("abc",arrested.getName());
    }
    @Test
    public void testSetIndex() {
        arrested.setIndex(1);
        assertEquals(1,arrested.getIndex());
    }
    @Test
    public void testGetName() {
        assertEquals("Eray",arrested.getName());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0,arrested.getIndex());
    }
}