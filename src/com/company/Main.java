package com.company;

import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Monopoly Game.\n Please enter number of players");
        Scanner scan = new Scanner(System.in);
        int numberOfPlayer = scan.nextInt();
        while(numberOfPlayer < 2 || numberOfPlayer > 8){
            System.out.println("Number of players should be between 2 and 8");
            numberOfPlayer = scan.nextInt();
        }
        ArrayList<String> names = new ArrayList<String>();
        System.out.println("Please enter the names of players");
        for (int i = 0; i < numberOfPlayer; i++) {
            names.add(scan.next());
        }
        System.out.println("Please enter the money will be given to players when lands Go Square");
        int goSquareMoney = scan.nextInt();
        System.out.println("Please enter the number of tax squares");
        int numberOfTaxSquares = scan.nextInt();
        System.out.println("Please enter the tax rate");
        int tax = scan.nextInt();

        Monopoly monopoly = new Monopoly(names, goSquareMoney, tax, numberOfTaxSquares);
        monopoly.play();
    }
}
