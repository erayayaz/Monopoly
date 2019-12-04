import com.company.Player_die.Player;
import com.company.board.LuckCard;
import com.company.board.Square;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLuckCard {
    LuckCard luck=new LuckCard("Eray",5);
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
        luck.setName("Cat");
        assertEquals("Cat",luck.getName());
    }
    @Test
    public void testSetIndex() {
        luck.setIndex(1);
        assertEquals(1,luck.getIndex());
    }
    @Test
    public void testGetIndex() {
        assertEquals(5,luck.getIndex());
    }
    @Test
    public void testGetName() {
        assertEquals("Eray",luck.getName());
    }
}