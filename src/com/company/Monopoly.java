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

    public static ArrayList<String> names;
    public static ArrayList<Player> players;

    Board board;

    Scanner scan = new Scanner(System.in);
    private static ArrayList<String> pieces = new ArrayList<>(Arrays.asList("dog", "hat", "thimble", "boot", "whellbarrow", "cat", "car", "battleship"));

    public Monopoly(){
        names = new ArrayList<String>();
        players = new ArrayList<Player>();
    }

    public Monopoly(ArrayList<String> names, int goSquareMoney, int tax, int numberOfTaxSquares){
        this.names = names;
        this.goSquareMoney = goSquareMoney;
        this.tax = tax;
        this.numberOfPlayers = names.size();
        this.numberOfTaxSquares = numberOfTaxSquares;
    }

    public void play(){
        readText();
        initializePlayers(getNumberOfPlayers(), getStartMoney());
        assignPiece();
        board = new Board(getNumberOfTaxSquares(), getTax(), getGoSquareMoney());
        determineTurns(players);
        Dice dice1 = new Dice();
        Dice dice2 = new Dice();
        simulateGame(players,board,dice1,dice2);
    }

    public void simulateGame(ArrayList<Player> players, Board board, Dice dice1, Dice dice2) {
        int playersInGame = numberOfPlayers;
        int numberOfBanktruptPlayer = 0;
        setCycleNumber(0);

        while (!checkBankrupts(players)) {
            printCycle();
            increaseCycleNumber();

            for (int i = 0; i < playersInGame; i++) {
                dice1.setFaceValue();
                dice2.setFaceValue();
                int totalDice = dice1.getFaceValue() + dice2.getFaceValue();
                int currentPlayer = findPlayer(players,i);

                if( !players.get(currentPlayer).isBankrupt() && players.get(currentPlayer).isInJail() ){
                    // System.out.println("test");
                    players.get(currentPlayer).increaseJailCounter(players.get(currentPlayer));
                    players.get(currentPlayer).setFree(players.get(currentPlayer));
                }

                else if ( !players.get(currentPlayer).isBankrupt() && !players.get(currentPlayer).isInJail() ) {
                    players.get(currentPlayer).getPiece().moveTo(totalDice, board);

                    if(players.get(currentPlayer).getPiece().getSquare() instanceof ArrestedSquare){
                        board.getSquaresOnBoard().get(players.get(currentPlayer).getPiece().getLocation()).action(players.get(currentPlayer));
                    } else if (players.get(currentPlayer).getPiece().getSquare() instanceof TaxSquare) {
                        board.getSquaresOnBoard().get(players.get(currentPlayer).getPiece().getLocation()).action(players.get(currentPlayer));
                    } else if (players.get(currentPlayer).getPiece().getSquare() instanceof GoSquare) {
                        board.getSquaresOnBoard().get(players.get(currentPlayer).getPiece().getLocation()).action(players.get(currentPlayer));
                    }

                    if (players.get(currentPlayer).isBankrupt() == true) {
                        numberOfBanktruptPlayer += 1;
                        changeTurn(players.get(currentPlayer), players);
                        playersInGame--;
                    }
                }

                if (numberOfBanktruptPlayer == numberOfPlayers - 1) {
                    System.out.println("Game Over");
                    printWinner(players);
                    System.exit(1);
                }
                printIteration(players.get(currentPlayer),totalDice);
            }
        }
    }


    public int findPlayer(ArrayList<Player> players, int i){
        for (int j = 0; j < numberOfPlayers ; j++) {
            if (players.get(j).getTurn() == i) {
                return j;
            }
        }
        return 0;
    }
    public void changeTurn(Player bankrupted, ArrayList<Player> players){
        int a = bankrupted.getTurn();
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players.get(i).getTurn() > a){
                players.get(i).setTurn(players.get(i).getTurn() - 1);
            }
        }
        bankrupted.setTurn(numberOfPlayers + 1);
    }

    private void printCycle() {
        if(getCycleNumber() == 0){
            System.out.println("Game is started");
            System.out.println("The players : ");
            players.forEach(element -> {
                System.out.println(element.getName() + " choose the " + element.getPiece().getName());
            });
        }
        System.out.println("\n------------CYCLE " + getCycleNumber() + " ----------------------\n");
    }
    public void increaseCycleNumber(){
        setCycleNumber(getCycleNumber() + 1);
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

    public void printWinner(ArrayList<Player> players){
        String name = "";
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players.get(i).isBankrupt() == false){
                name = players.get(i).getName();
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

    public void initializePlayers(int numberOfPlayers, int startMoney){
        names.forEach(element -> {
            Player player = new Player(element, getStartMoney());
            players.add(player);
        });
    }

    public boolean gameFinished(){
        if(board.getPieces().size() == 1){
            System.exit(1);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkBankrupts(ArrayList<Player> players){
        int numberOfBankruptedPlayers = 0;
        for (int i = 0; i < numberOfPlayers; i++) {
            if(players.get(i).isBankrupt() == true){
                numberOfBankruptedPlayers++;
            }
        }
        if(numberOfPlayers - numberOfBankruptedPlayers == 1){
            return true;
        }
        else{
            return false;
        }
    }


    public void assignPiece(){
        int a = 8;
        for (int i = 0; i < numberOfPlayers; i++) {
            String temp;
            Random rand = new Random();
            int random = rand.nextInt(a);
            temp = pieces.get(random);
            a--;
            Piece piece = new Piece(temp);
            players.get(i).setPiece(piece);
            pieces.remove(random);
        }
    }

    public void determineTurns(ArrayList<Player> players){
        for (int i = 0; i < numberOfPlayers; i++) {
            players.get(i).setTurn(i);
        }
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
