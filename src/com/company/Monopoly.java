package com.company;
import com.company.Player_die.Piece;
import com.company.Player_die.Player;
import com.company.board.Board;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Monopoly {

    int numberOfPlayers;
    public static ArrayList<String> names;
    int goSquareMoney;
    int tax;
    static boolean gameFinished = false;
    static int tourNumber = 0;
    int numberOfTaxSquares;
    public static ArrayList<Player> players = new ArrayList<>();
    int startMoney;
    Board board;
    Scanner scan = new Scanner(System.in);
    private static ArrayList<String> pieces = new ArrayList<>(Arrays.asList("dog", "hat", "thimble", "boot", "whellbarrow", "cat", "car", "battleship"));
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
        System.out.println("Welcome to Monopoly Game.\n Please enter number of players");
        this.numberOfPlayers = scan.nextInt();
        while(numberOfPlayers < 2 || numberOfPlayers > 8){
            System.out.println("Number of players should be between 2 and 8");
            this.numberOfPlayers = scan.nextInt();
        }
        System.out.println("Please enter the start money");
        this.startMoney = scan.nextInt();
        initializePlayers(numberOfPlayers, startMoney);
        System.out.println("Please enter the money will be given to players when lands Go Square");
        this.goSquareMoney = scan.nextInt();
        System.out.println("Please enter the number of tax squares");
        this.numberOfTaxSquares = scan.nextInt();
        System.out.println("Please enter the tax rate");
        this.tax = scan.nextInt();
        assignPiece();

        test();
    }

    public void initializePlayers(int numberOfPlayers, int startMoney){
        String name = "";
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Plase enter the name of player");
            name = scan.next();
            Player player = new Player(name, startMoney);
            players.add(i, player);
        }
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

}
