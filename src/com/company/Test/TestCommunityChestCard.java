import com.company.Player_die.Player;
import com.company.board.CommunityChestCard;
import com.company.board.Square;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCommunityChestCard {
    CommunityChestCard chest=new CommunityChestCard("Eray",5);
    Square square = new Square("Eray",0) {
        @Override
        public void action(Player player) {

        }
    };
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testSetName(){
        chest.setName("Cat");
        assertEquals("Cat",chest.getName());
    }
    @Test
    public void testSetIndex() {
        chest.setIndex(1);
        assertEquals(1,chest.getIndex());
    }
    @Test
    public void testGetIndex() {
        assertEquals(5,chest.getIndex());
    }
    @Test
    public void testGetName() {
        assertEquals("Eray",chest.getName());
    }
}