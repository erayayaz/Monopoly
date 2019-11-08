package com.company;
import com.company.Player_die.Dice;
import com.company.Player_die.Piece;
import com.company.Player_die.Player;
import com.company.board.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Monopoly {

    private int numberOfPlayers;
    private int goSquareMoney;
    private int tax;
    private int startMoney;
    private int cycleNumber;
    private int numberOfTaxSquares;
    private boolean gameFinished = false;
    private Board board;
    private ArrayList<String> names;
    private ArrayList<Player> players;
    private static ArrayList<String> pieces;

    public Monopoly(){
        names = new ArrayList<String>();
        players = new ArrayList<Player>();
        pieces = new ArrayList<>(Arrays.asList("dog", "hat", "thimble", "boot", "whellbarrow", "cat", "car", "battleship"));
    }

    public void play(){
        readText();
        initializePlayers();
        assignPiece();
        setBoard(new Board(getNumberOfTaxSquares(), getTax(), getGoSquareMoney()));
        determineTurns();
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        simulateGame(dice1,dice2);
    }

    public void simulateGame(Dice dice1, Dice dice2) {
        int numberOfActivePlayers = getNumberOfPlayers();
        setCycleNumber(1);

        while (!checkBankrupts()) {
            printCycle();
            setCycleNumber(getCycleNumber() + 1);

            for (int i = 0; i < getNumberOfPlayers(); i++) {
                if (!getPlayers().get(i).isBankrupt()){
                    dice1.setFaceValue();
                    dice2.setFaceValue();
                    int totalDice = dice1.getFaceValue() + dice2.getFaceValue();

                    if (getPlayers().get(i).getPiece().isInJail()) {
                        // System.out.println("test");
                        getPlayers().get(i).getPiece().decreaseJailCounter();
                        getPlayers().get(i).getPiece().setFree();
                    }
                    if (!getPlayers().get(i).getPiece().isInJail()) {
                        getPlayers().get(i).getPiece().moveTo(totalDice, getBoard());

                        Square initialSquare = getPlayers().get(i).getPiece().getSquare();
                        if (initialSquare instanceof ArrestedSquare
                                || initialSquare instanceof  TaxSquare
                                    || initialSquare instanceof GoSquare) {
                            getBoard().getSquaresOnBoard().get(getPlayers().get(i).getPiece().getLocation()).action(getPlayers().get(i));
                        }

                        if (getPlayers().get(i).isBankrupt()) {
                            numberOfActivePlayers--;
                            getPlayers().get(i).setPiece(null);
                        }
                    }
                    printIteration(getPlayers().get(i), totalDice);
               }

            }
            printCycle(players,numberOfPlayers,numberOfActivePlayers);
            if (numberOfActivePlayers == 1) {
                System.out.println("Game Over");
                printWinner();
                System.exit(1);
            }
        }
    }

    public void printCycle() {
        if(getCycleNumber() == 1){
            System.out.println("Game is started");
            System.out.println("The players : ");
            getPlayers().forEach(element -> {
                System.out.println(element.getName() + " choose the " + element.getPiece().getName());
            });
        }
        System.out.println("\n------------CYCLE " + getCycleNumber() + " ----------------------\n");
    }

    public void printCycle(ArrayList<Player> players, int numberOfPlayers, int playersInGame){
        int[] balances = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players.get(i).isBankrupt() == false){
                balances[i] = players.get(i).getMoney();
            }
        }
        int[] copy = new int[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            copy[i] = balances[i];
        }
        bubbleSorat(balances);
        System.out.println("At the end of the tour : ");
        for (int i = 0; i < playersInGame; i++) {
            int money = balances[i];

            if(players.get(i).isBankrupt() == false){
                int j = find(money,copy);
                System.out.println(players.get(j).getName() + " is on " + players.get(j).getPiece().getLocation() + " and has " + players.get(j).getMoney());
            }
        }
    }

    public int find(int balance, int[] copy){
        for (int i = 0; i < numberOfPlayers; i++) {
            if(balance == copy[i]){
                copy[i] = 0;
                return i;
            }
        }
        return 0;
    }

    public void bubbleSort(int[] array) {
        boolean swapped = true;
        int j = 0;
        int tmp;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < array.length - j; i++) {
                if (array[i] > array[i + 1]) {
                    tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;
                    swapped = true;
                }
            }
        }
    }
    private static void bubbleSorat(int[] intArray) {
        int n = intArray.length;
        int temp = 0;

        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){

                if(intArray[j-1] < intArray[j]){
                    //swap the elements!
                    temp = intArray[j-1];
                    intArray[j-1] = intArray[j];
                    intArray[j] = temp;
                }

            }
        }

    }
    public void printIteration(Player player, int moveNumber) {
        if (!player.isBankrupt()) {
            if (player.getPiece().getJailCounter() == 3){
                System.out.println(player.getName() + " rools " + moveNumber);
                System.out.println(player.getName() + " moved to 29 with " + player.getPiece().getName());
                System.out.println(player.getName() + " arrested");
                System.out.println(player.getName() + " was exiled to the Jail Square(19)");
            }
            if ((player.getPiece().isInJail() == true)) {
                System.out.println(player.getName() + " is now in jail.");
                System.out.println(player.getName() + " will be free after " + player.getPiece().getJailCounter() + " turn(s)");
            } else {
                System.out.println(player.getName() + " rools " + moveNumber);
                System.out.println(player.getName() + " moved to " + player.getPiece().getLocation() + " with " + player.getPiece().getName());
                System.out.println(player.getName() + " has " + player.getMoney());
            }
        }
        else{
            System.out.println(player.getName() + " went bankrupt.");
        }
        System.out.println("---------------------------");
    }

    public void printWinner(){
        AtomicReference<String> name = new AtomicReference<>("");
        getPlayers().forEach(element -> {
            if(!element.isBankrupt())
                name.set(element.getName());
        });
        System.out.println("\nWinner is " + name);
    }

    public void readText(){
        try {
            File file = new File("test.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            while ((st = br.readLine()) != null) {
                String[] lines = st.split(" ");
                if (lines[0].equals("numberOfPlayers"))
                    setNumberOfPlayers(Integer.parseInt(lines[1]));
                else if (lines[0].equals("startMoney"))
                    setStartMoney(Integer.parseInt(lines[1]));
                else if (lines[0].equals("goSquareMoney"))
                    setGoSquareMoney(Integer.parseInt(lines[1]));
                else if (lines[0].equals("numberOfTaxSquares"))
                    setNumberOfTaxSquares(Integer.parseInt(lines[1]));
                else if (lines[0].equals("taxRate"))
                    setTax(Integer.parseInt(lines[1]));
                else {
                    for (String element : lines) {
                        names.add(element);
                    }
                }
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void initializePlayers(){
        getNames().forEach(element -> {
            Player player = new Player(element, getStartMoney());
            getPlayers().add(player);
        });
    }

    public boolean gameFinished(){
        if(getBoard().getPieces().size() == 1){
            System.exit(1);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkBankrupts(){
        int numberOfBankruptedPlayers = 0;
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            if(getPlayers().get(i).isBankrupt() == true){
                numberOfBankruptedPlayers++;
            }
        }
        if(getNumberOfPlayers() - numberOfBankruptedPlayers == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public void assignPiece(){
        int a = 8;
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            String temp;
            Random rand = new Random();
            int random = rand.nextInt(a);
            temp = getPieces().get(random);
            a--;
            Piece piece = new Piece(temp);
            getPlayers().get(i).setPiece(piece);
            getPieces().remove(random);
        }
    }

    public void determineTurns(){
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            getPlayers().get(i).setTurn(i);
        }
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public static ArrayList<String> getPieces() {
        return pieces;
    }

    public static void setPieces(ArrayList<String> pieces) {
        Monopoly.pieces = pieces;
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

    public int getCycleNumber() {
        return cycleNumber;
    }

    public void setCycleNumber(int cycleNumber) {
        this.cycleNumber = cycleNumber;
    }

    public int getNumberOfTaxSquares() {
        return numberOfTaxSquares;
    }

    public void setNumberOfTaxSquares(int numberOfTaxSquares) {
        this.numberOfTaxSquares = numberOfTaxSquares;
    }

    public int getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(int startMoney) {
        this.startMoney = startMoney;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}