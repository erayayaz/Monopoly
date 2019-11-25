

import com.company.Player_die.Dice;
import com.company.Player_die.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestDice {
    Dice die1,die2;
    Player gamer;

    @Before
    public void setUp() throws Exception {
        die1= new Dice();
        die2= new Dice();
        gamer = new Player("Eray",500, "cat");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetFaceValue() throws Exception{
        for(int k=0;k<100;k++){
            gamer.rollDice(die1,die2);
            Assert.assertTrue("There is a problem inside the Dice", (!(die1.getFaceValue() < 1 || die1.getFaceValue() > 6)) &&
                    (!(die2.getFaceValue() < 1 || die2.getFaceValue() > 6)));
        }
    }

    @Test
    public void testRollDice() throws Exception {
        for(int i=0; i<100; i++) {
            int total = gamer.rollDice(die1, die2);
            Assert.assertTrue("Dice values are false", total == die1.getFaceValue() + die2.getFaceValue());
        }
    }
}