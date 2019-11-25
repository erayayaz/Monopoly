package com.company.board;

import com.company.Player_die.Player;

public class GoSquare extends Square{

    private int goSquareMoney;

    public GoSquare(String name, int index, int goSquareMoney) {
        super(name, index);
        this.goSquareMoney = goSquareMoney;
    }

    public int getGoSquareMoney() {
        return goSquareMoney;
    }

    public void setGoSquareMoney(int goSquareMoney) {
        this.goSquareMoney = goSquareMoney;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void setIndex(int index) {
        super.setIndex(index);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getIndex() {
        return super.getIndex();
    }

    @Override
    public void action(Player player) {
        player.increaseMoney(this.goSquareMoney);
    }

    @Override
    public void buy(Player player) {

    }

    @Override
    public void rent(Player player, int totalDice) {

    }


}
