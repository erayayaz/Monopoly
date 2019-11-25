/*
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
    @Test
    public void testDecreaseJailCounter(){
        piece.decreaseJailCounter();
        assertEquals(-1,piece.getJailCounter());
    }
    @Test
    public void testResetJailCounter(){
        piece.resetJailCounter();
        assertEquals(0,piece.getJailCounter());
    }
    @Test
    public void testSetJailCounter(){
        piece.setJailCounter(2);
        assertEquals(2,piece.getJailCounter());
    }
    @Test
    public void testIsInJail(){ //also it is testing the set in jail mehod
        piece.setInJail(true);
        assertEquals(true,piece.isInJail());
    }
}*/