package com.company;
import com.company.Player_die.Piece;
import com.company.Player_die.Player;
import com.company.board.Board;

import java.io.*;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Monopoly {

    private int numberOfPlayers;
    private int goSquareMoney;
    private int tax;
    private int startMoney;
    private int tourNumber;
    private int numberOfTaxSquares;
    private boolean gameFinished = false;

    public static ArrayList<String> names;

    public static ArrayList<Player> players;

    Board board = new Board();
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
        System.out.println(players.get(0).getName());
        assignPiece();
        this.board = board.initializeBoard(getNumberOfTaxSquares(), getTax(), getGoSquareMoney());
        test();
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

    public void printIteration(){

    }

    public void assignPiece(){
        int a = 8;
        for (int i = 0; i < numberOfPlayers; i++) {
            String temp;
            Random rand = new Random();
            int random;
            random = rand.nextInt(a);
            temp = pieces.get(random);
            a = a - 1;
            Piece piece = new Piece(temp);
            players.get(i).setPiece(piece);
            pieces.set(random, null);
        }
    }

    public void test(){
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.printf("%s %s", players.get(i).getName(), players.get(i).getPiece().getName());
            System.out.println();
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

    public int getTourNumber() {
        return tourNumber;
    }

    public void setTourNumber(int tourNumber) {
        this.tourNumber = tourNumber;
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
