package com.company.board;

import com.company.Player_die.Dice;
import com.company.Player_die.Player;

public class PropertiesSquare extends PurchasableSquare {

   private int houseNumber;
   private int rent;
   private String color;
   private int costOfBuild;
    public PropertiesSquare(String name, int index, int price, int rent, String color, int costOfBuild) {
        super(name, index, price);
        this.rent = rent;
        this.color = color;
        this.costOfBuild = costOfBuild;
        houseNumber = 0;
    }

    @Override
    public void buy(Player player) {
        if (player.getMoney() >= this.getPrice() && player.rollDice(new Dice()) + player.rollDice(new Dice()) >= 8 && !this.isOwned()) {
            player.decreaseMoney(this.getPrice());
            this.setPlayer(player);
            this.setOwned(true);

            player.getProperties().add(this);
        }
    }

    @Override
    public void rent(Player player, int dice) {
        if (this.isOwned()) {
            this.getPlayer().increaseMoney(player.decreaseMoney(this.rent));
            if (player.getMoney() <= 0)
                player.setBankrupt(true);
        }
    }

    public void build(Player player){

        if(houseNumber <= 4) {
            if (this.isOwned() && this.getPlayer() == player && player.getNumberOfColor(this.color)) {
                if (player.getMoney() >= this.costOfBuild && player.rollDice(new Dice()) + player.rollDice(new Dice()) >= 2) {
                    player.decreaseMoney(this.costOfBuild);
                    this.setHouseNumber(this.getHouseNumber() + 1);
                    this.setCostOfBuild(this.getCostOfBuild() + 10);
                    this.setRent(this.getRent() + (this.getCostOfBuild() / 10));
                }
            }
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getCostOfBuild() {
        return costOfBuild;
    }

    public void setCostOfBuild(int costOfBuild) {
        this.costOfBuild = costOfBuild;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}