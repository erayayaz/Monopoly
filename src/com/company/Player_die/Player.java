package com.company.Player_die;

import com.company.board.Square;

public class Player {

    //Property of our Player
    private int jailCounter = 0;
    private String name;
    private double money;
    private int doubleCounter;
    private boolean inJail = false;
    private boolean isBankrupt;
    private int turn;
    private Piece piece;
    private Square currentSquare;


    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.isBankrupt = false;
    }

    public void increaseJailCounter() {
        setJailCounter(getJailCounter() + 1);
    }

    public void resetJailCounter() {
        setJailCounter(0);
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

    public void setFree(){
        if(getJailCounter() == 2){
            setInJail(false);
            resetJailCounter();
        }

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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }


    public boolean isInJail() {

        return inJail;
    }

    public void setInJail(boolean inJail) {
        this.inJail = inJail;
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


    public int getJailCounter() {
        return jailCounter;
    }

    public void setJailCounter(int jailCounter) {
        this.jailCounter = jailCounter;
    }

    public void setDoubleCounter(int doubleCounter) {
        this.doubleCounter = doubleCounter;
    }

    public Square getCurrentSquare() {
        return currentSquare;
    }

    public void setCurrentSquare(Square currentSquare) {
        this.currentSquare = currentSquare;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
