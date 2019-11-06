import com.company.board.TaxSquare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestTaxSquare {
    TaxSquare tax = new TaxSquare("Eray",0,10);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSetTaxtAmount() {
        tax.setTaxtAmount(20);
        assertEquals(20,tax.getTaxtAmount());
    }

    @Test
    public void testGetTaxtAmount() {
        assertEquals(10,tax.getTaxtAmount());
    }

    @Test
    public void testSetName() {
        tax.setName("abc");
        assertEquals("abc",tax.getName());
    }

    @Test
    public void testSetIndex() {
        tax.setIndex(2);
        assertEquals(2,tax.getIndex());
    }

    @Test
    public void testGetName() {
        assertEquals( "Eray",tax.getName());
    }

    @Test
    public void testGetIndex() {
        assertEquals(0,tax.getIndex());
    }
}