import com.company.board.Building;
import com.company.board.PropertiesSquare;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestBuilding {
    PropertiesSquare pro = new PropertiesSquare("James",500,500,500,"Black",500);
    Building buil = new Building(500,pro);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testSetCostOfBuild() {
        buil.setCostOfBuild(500);
        assertEquals(500,buil.getCostOfBuild());
    }
    @Test
    public void testGetCostOfBuild() {
        assertEquals(500,buil.getCostOfBuild());
    }
    }
