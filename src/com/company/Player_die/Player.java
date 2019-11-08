package com.company.Player_die;

import com.company.board.Square;

public class Player {

    //Property of our Player
    private String name;
    private int money;
    private int doubleCounter;
    private boolean isBankrupt;
    private int turn;
    private Piece piece;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.isBankrupt = false;
    }


    public void increaseMoney(double money) {
        this.money += money;
    }

    public void decreaseMoney(double money) {
        this.money -= money;

    }

    public int rollDice(Dice die1, Dice die2) {
        die1.setFaceValue();
        die2.setFaceValue();
        return die1.getFaceValue() + die2.getFaceValue();
    }


    public void setBankrupt(boolean bankrupt) {
        this.isBankrupt = bankrupt;
    }

    public boolean isBankrupt() {
        return isBankrupt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }



    public void incrementDoubleCounter() {
        doubleCounter++;
    }

    public int getDoubleCounter() {
        return doubleCounter;
    }

    public void resetDoubleCounter() {
        doubleCounter = 0;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setDoubleCounter(int doubleCounter) {
        this.doubleCounter = doubleCounter;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
