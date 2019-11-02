package Player_die;
import java.util.Random;

public class Dice {

    //Property of Dice
    private int faceValue;
    private Random rand;
    public Dice(){
        rand = new Random();
        faceValue = rand.nextInt(6) + 1;
    }
    //This method setting the dice values
    public void setFaceValue() {
        faceValue = rand.nextInt(6) + 1;
    }

    //returns face value of the dice
    public int getFaceValue() {
        return faceValue;
    }
}
