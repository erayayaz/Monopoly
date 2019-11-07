package com.company;
import com.company.Player_die.Dice;
import com.company.Player_die.Piece;
import com.company.Player_die.Player;
import com.company.board.*;
import java.io.*;
import java.util.*;

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
        int playersInGame = getNumberOfPlayers();
        int numberOfBanktruptPlayer = 0;
        setCycleNumber(1);

        while (!checkBankrupts()) {
            printCycle();
            setCycleNumber(getCycleNumber() + 1);

            for (int i = 0; i < playersInGame; i++) {
                dice1.setFaceValue();
                dice2.setFaceValue();
                int totalDice = dice1.getFaceValue() + dice2.getFaceValue();
                int currentPlayer = findPlayer(i);

                if( !getPlayers().get(currentPlayer).isBankrupt() && getPlayers().get(currentPlayer).isInJail() ){
                    // System.out.println("test");
                    getPlayers().get(currentPlayer).increaseJailCounter();
                    getPlayers().get(currentPlayer).setFree();
                }

                else if ( !getPlayers().get(currentPlayer).isBankrupt() && !getPlayers().get(currentPlayer).isInJail() ) {
                    getPlayers().get(currentPlayer).getPiece().moveTo(totalDice, getBoard());

                    if(getPlayers().get(currentPlayer).getPiece().getSquare() instanceof ArrestedSquare){
                        getBoard().getSquaresOnBoard().get(getPlayers().get(currentPlayer).getPiece().getLocation()).action(getPlayers().get(currentPlayer));
                    } else if (getPlayers().get(currentPlayer).getPiece().getSquare() instanceof TaxSquare) {
                        getBoard().getSquaresOnBoard().get(getPlayers().get(currentPlayer).getPiece().getLocation()).action(getPlayers().get(currentPlayer));
                    } else if (getPlayers().get(currentPlayer).getPiece().getSquare() instanceof GoSquare) {
                        getBoard().getSquaresOnBoard().get(getPlayers().get(currentPlayer).getPiece().getLocation()).action(getPlayers().get(currentPlayer));
                    }

                    if (players.get(currentPlayer).isBankrupt() == true) {
                        numberOfBanktruptPlayer += 1;
                        changeTurn(getPlayers().get(currentPlayer));
                        playersInGame--;
                    }
                }

                if (numberOfBanktruptPlayer == getNumberOfPlayers() - 1) {
                    System.out.println("Game Over");
                    printWinner();
                    System.exit(1);
                }
                printIteration(getPlayers().get(currentPlayer),totalDice);
            }
        }
    }

    public int findPlayer(int i){
        for (int j = 0; j < getNumberOfPlayers() ; j++) {
            if (getPlayers().get(j).getTurn() == i) {
                return j;
            }
        }
        return 0;
    }

    public void changeTurn(Player bankrupted){
        int a = bankrupted.getTurn();
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            if(getPlayers().get(i).getTurn() > a){
                getPlayers().get(i).setTurn(getPlayers().get(i).getTurn() - 1);
            }
        }
        bankrupted.setTurn(getNumberOfPlayers() + 1);
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

    public void printIteration(Player player, int moveNumber){
        if((player.isInJail() == true)){
            System.out.println(player.getName() + " is now in jail.");
            System.out.println("After " + (2 - player.getJailCounter()) + " turns player will be freed");
        }
        else{
            System.out.println(player.getName() + " rools " + moveNumber);
            System.out.println(player.getName() + " moved to " + player.getPiece().getLocation() + " with " + player.getPiece().getName());
            System.out.println(player.getName() + " has " + player.getMoney());
        }
        System.out.println("---------------------------");
    }

    public void printWinner(){
        String name = "";
        for (int i = 0; i < getNumberOfPlayers(); i++) {
            if(getPlayers().get(i).isBankrupt() == false){
                name = getPlayers().get(i).getName();
            }
        }
        System.out.println("Winner is " + name);
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