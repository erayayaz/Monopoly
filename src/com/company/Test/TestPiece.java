
import com.company.Player_die.Piece;
import com.company.Player_die.Player;
import com.company.board.Square;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestPiece {
    Piece piece=new Piece("Eray");
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
    public void testGetLocation(){
        assertEquals(0,piece.getLocation());
    }
    @Test
    public void testSetLocation(){
        piece.setLocation(2);
        assertEquals(2,piece.getLocation());
    }
    @Test
    public void testSetName(){
        piece.setName("Cat");
        assertEquals("Cat",piece.getName());
    }
}