package com.company;

public class Player {

    //Property of our Player
    private String name;
    private double money;
    private int doubleCounter;
    private boolean inJail=false;
    private boolean isBankrupt=false;
    //private Piece piece;
    //private Square currentSquare;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
    }
    public void setBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
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
    public void increaseMoney(double money){
        this.money+=money;

    }
    public void decreaseMoney(double money){
        this.money-=money;
        if(money<=0)
            this.isBankrupt=true;
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

}
