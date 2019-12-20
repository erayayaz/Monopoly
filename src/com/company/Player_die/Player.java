package com.company.Player_die;

import com.company.board.PropertiesSquare;
import com.company.board.PurchasableSquare;
import com.company.board.Square;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    //Property of our Player
    private String name;
    private int money;
    private int doubleCounter;
    private boolean isBankrupt;
    private int turn;
    private Piece piece;
    private ArrayList<PurchasableSquare> properties;

    public Player(String name, int money,String pieceName) {
        setPiece(pieceName);
        this.name = name;
        this.money = money;
        this.isBankrupt = false;
        properties = new ArrayList<PurchasableSquare>();
    }

    public void increaseMoney(int money) {
        this.money += money;
    }

    public int decreaseMoney(int money) {
        if(money > getMoney()){
            money = getMoney();
            setMoney(0);
        }
        else {
            setMoney(getMoney() - money);
        }
        return money;
    }

    public int rollDice(Dice die1) {
        die1.setFaceValue();
        return die1.getFaceValue();
    }

    public void setPropertiesFree(){
        ArrayList<PurchasableSquare> remove = new ArrayList<PurchasableSquare>();
        int size = this.getProperties().size();
        if(size == 0 ){
            this.setBankrupt(true);
        }else{
            this.setBankrupt(false);
        }
        for (int i = 0; i < size; i++) {
            this.getProperties().get(i).setOwned(false);
            this.increaseMoney(getProperties().get(i).getPrice() / 2);
            this.getProperties().get(i).setPlayer(null);
            remove.add(this.getProperties().get(i));
        }
        this.getProperties().removeAll(remove);
    }

    public boolean getNumberOfColor(String color){
        int count = 0;
        int size = getProperties().size();
        for (int i = 0; i < size; i++) {
            if(getProperties().get(i) instanceof PropertiesSquare){
                if(((PropertiesSquare) getProperties().get(i)).getColor() == color){
                    count++;
                }
            }
        }
        if(count == 3){
            return true;
        }
        else{
            return false;
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

    public void setPiece(String name) {
        piece = new Piece(name);
    }

    public ArrayList<PurchasableSquare> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<PurchasableSquare> properties) {
        this.properties = properties;
    }
}
