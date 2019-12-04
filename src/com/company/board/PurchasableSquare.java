package com.company.board;

import com.company.Player_die.Dice;
import com.company.Player_die.Player;

public abstract class PurchasableSquare extends Square {

    private int price;
    private boolean isOwned;
    private Player player;

    public PurchasableSquare(String name, int index, int price) {
        super(name, index);
        this.price = price;
    }



    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public abstract void buy(Player player);
    public abstract void rent(Player player, int dice);

    @Override
    public void action(Player player) {

    }
}
