import com.company.Player_die.Dice;
import com.company.Player_die.Piece;
import com.company.Player_die.Player;
import com.company.board.Square;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPlayer {

    Piece piece = new Piece();
    Player gamer = new Player("Eray",500);
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
    public void testSetBanktrupt(){
        gamer.setBankrupt(true);
        assertEquals(true,gamer.isBankrupt());
    }
    @Test
    public void testİsBanktrupt(){
        assertEquals(false,gamer.isBankrupt());
    }
    @Test
    public void testGetName(){
        assertEquals("Eray",gamer.getName());
    }
    @Test
    public void testSetName(){
        gamer.setName("Rahim");
        assertEquals("Rahim",gamer.getName());
    }
    @Test
    public void testGetMoney(){
        assertEquals(500,gamer.getMoney());
    }
    @Test
    public void testSetMoney(){
        gamer.setMoney(300);
        assertEquals(300,gamer.getMoney());
    }
    @Test
    public void testGetTurn(){ //It also test the set turn method.
        gamer.setTurn(2);
        assertEquals(2,gamer.getTurn());
    }
    @Test
    public void testIncreaseJailCounter(){
        gamer.increaseJailCounter();
        assertEquals(1,gamer.getJailCounter());
    }
    @Test
    public void testResetJailCounter(){
        gamer.resetJailCounter();
        assertEquals(0,gamer.getJailCounter());
    }
    @Test
    public void testSetJailCounter(){
        gamer.setJailCounter(2);
        assertEquals(2,gamer.getJailCounter());
    }
    @Test
    public void testIsInJail(){ //also it is testing the set in jail mehod
        gamer.setInJail(true);
        assertEquals(true,gamer.isInJail());
    }
    @Test
    public void testIncrementandResetDoubleCounter(){
        gamer.incrementDoubleCounter();
        assertEquals(1,gamer.getDoubleCounter());
        gamer.resetDoubleCounter();
        assertEquals(0,gamer.getDoubleCounter());
    }
    @Test
    public void testSetDoubleCounter(){
        gamer.setDoubleCounter(2);
        assertEquals(2,gamer.getDoubleCounter());
    }
    @Test
    public void testCurrentSquare(){
        gamer.setCurrentSquare(square);
        assertEquals(square,gamer.getCurrentSquare());
    }
    @Test
    public void testPiece(){
        gamer.setPiece(piece);
        assertEquals(piece,gamer.getPiece());
    }

}