import java.util.ArrayList;

public class Monopoly {

    int numberOfPlayers;
    ArrayList<String> names;
    int goSquareMoney;
    int tax;
    static boolean gameFinished = false;
    static int tourNumber = 0;
    int numberOfTaxSquares;

    public Monopoly(){

    }

    public Monopoly(ArrayList<String> names, int goSquareMoney, int tax, int numberOfTaxSquares){
        this.names = names;
        this.goSquareMoney = goSquareMoney;
        this.tax = tax;
        this.numberOfPlayers = names.size();
        this.numberOfTaxSquares = numberOfTaxSquares;
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public int getGoSquareMoney() {
        return goSquareMoney;
    }

    public void setGoSquareMoney(int goSquareMoney) {
        this.goSquareMoney = goSquareMoney;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public static int getTourNumber() {
        return tourNumber;
    }

    public static void setTourNumber(int tourNumber) {
        Monopoly.tourNumber = tourNumber;
    }

    public int getNumberOfTaxSquares() {
        return numberOfTaxSquares;
    }

    public void setNumberOfTaxSquares(int numberOfTaxSquares) {
        this.numberOfTaxSquares = numberOfTaxSquares;
    }

    public void play(){
        //initializePlayers
        //initializeBoard
    }

    public void gameFinished(){
        // oyunda kaç tane oyuncu kaldığını bulup ona göre doğru yanlış döndürecek
    }

    public void printIteration(){
        //oyuncunun adı ve piece ile kaç kare ilerlediği yazılacak
        //
    }

}
