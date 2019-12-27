package com.company;
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

    public static void main(String[] args) {
        try {
            Monopoly monopoly = new Monopoly();
            monopoly.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
