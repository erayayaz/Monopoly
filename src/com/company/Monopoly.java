package com.company;
import com.company.Player_die.Dice;
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
    private Dice dice[] = new Dice[2];

    public Monopoly(){
        names = new ArrayList<String>();
        players = new ArrayList<Player>();
        pieces = new ArrayList<>(Arrays.asList("dog", "hat", "thimble", "boot", "whellbarrow", "cat", "car", "battleship"));
        createDice(dice);
    }

    public void play(){
        readText();
        initializePlayers();
        assignPiece();
        setBoard(new Board(getNumberOfTaxSquares(), getTax(), getGoSquareMoney()));
        determineTurns();
        simulateGame();
    }

    public void simulateGame() {
        int numberOfActivePlayers = getNumberOfPlayers();
        setCycleNumber(1);

        while (!checkBankrupts()) {
            printCycle();
            setCycleNumber(getCycleNumber() + 1);

            for (int i = 0; i < getNumberOfPlayers(); i++) {
                if (!getPlayers().get(i).isBankrupt()){
                    //dice1.setFaceValue();
                    //dice2.setFaceValue();

                    //int totalDice = dice1.getFaceValue() + dice2.getFaceValue();
                    int totalDice = getPlayers().get(i).rollDice(getDice()[0], getDice()[1]);
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
                        else if(initialSquare instanceof PurchasableSquare){
                            ((PurchasableSquare)getBoard().getSquaresOnBoard().get(getPlayers().get(i).getPiece().getLocation())).buy(getPlayers().get(i));
                            ((PurchasableSquare)getBoard().getSquaresOnBoard().get(getPlayers().get(i).getPiece().getLocation())).rent(getPlayers().get(i), totalDice);
                        }else if(initialSquare instanceof LuckCard){
                            getBoard().getSquaresOnBoard().get(getPlayers().get(i).getPiece().getLocation()).action(getPlayers().get(i));
                        }

                        if (getPlayers().get(i).isBankrupt()) {
                            numberOfActivePlayers--;
                            getPlayers().get(i).setPiece(null);
                            getPlayers().get(i).setPropertiesFree();
                        }
                    }
                    printIteration(getPlayers().get(i), totalDice);
               }
            }
            sortPlayers();
            if (numberOfActivePlayers == 1) {
                System.out.println("Game Over!");
                printWinner();
                System.exit(1);
            }
        }
    }

    public void sortPlayers(){
        Player[] sortPlayers = new Player[getNumberOfPlayers()];
        for (int i = 0 ; i < getNumberOfPlayers() ; i++){
            sortPlayers[i] = getPlayers().get(i);
        }
        Arrays.sort(sortPlayers, Comparator.comparing(Player::getMoney));
        System.out.println("\nWealth ranking: ");
        for (int i = (getNumberOfPlayers() - 1); i >= 0; i--){
            System.out.println(sortPlayers[i].getName() + " " + sortPlayers[i].getMoney());
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
                int size = player.getProperties().size();
                if(size > 0){
                    for (int i = 0; i < size; i++) {
                        System.out.println(player.getName() + " has " + player.getProperties().get(i));
                    }
                }
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
        System.out.println("Players roll the dices to determine the order.");
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < numberOfPlayers; i++){
            order(getNames());
        }
    }
    public void order(ArrayList<String> names){
        ArrayList<String> temp = new ArrayList<>();
        int max = -1 , faceValue = -1 , index = 0;
        for (int i = 0; i < names.size() ; i++){
            faceValue = dice[0].getFaceValue() + dice[1].getFaceValue();
            System.out.println(names.get(i) + " rolls " + faceValue);
            if (faceValue > max){
                max = faceValue;
                temp.clear();
                temp.add(names.get(i));
                index = i;
            }
            else if(faceValue == max){
                temp.add(names.get(i));
            }
        }
        if (temp.size() == 1){
            System.out.println(temp.get(0) + " rolls the max face value!");
            System.out.println("-----------------------------------");
            String pieceName = assignPiece();
            getPlayers().add(new Player(names.get(index),getStartMoney() , pieceName));
            getNames().remove(names.get(index));
            return;
        }
        else if (temp.size() > 1){
            for (String name : temp){
                System.out.print(name + ", ");
            }
            System.out.println("rolls the same face value");
            System.out.println("Now, They will rolls the dice again!");
            order(temp);
        }
        return;
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

    public String assignPiece(){
        Random rand = new Random();
        int value = rand.nextInt(getPieces().size());
        String temp = getPieces().get(value);
        getPieces().remove(value);
        return temp;
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
    public Dice[] getDice() {
        return dice;
    }

    public void createDice(Dice[] dice) {
        dice[0] = new Dice();
        dice[1] = new Dice();
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
}