package com.company.board;

import com.company.Player_die.Dice;
import com.company.Player_die.Player;

public class RailRoad extends PurchasableSquare {

    public RailRoad(String name, int index, int price) {
        super(name, index, price);
    }

    @Override
    public void rent(Player player, int dice) {
        int number = numberOfRailRoads(this.getPlayer());
        if(this.isOwned()){
            player.decreaseMoney(25 * Math.pow(2,number));
            this.getPlayer().increaseMoney(25 * Math.pow(2, number));
        }
        if ( player.getMoney() <= 0)
            player.setBankrupt(true);
    }

    @Override
    public void buy(Player player) {
        if (player.getMoney() >= this.getPrice() && player.rollDice(new Dice(), new Dice()) >= 8 && !this.isOwned()){
            this.setPlayer(player);
            this.setOwned(true);
            player.getProperties().add(this);
        }
    }

    @Override
    public void action(Player player){

    }

    public int numberOfRailRoads(Player player){
        int number = 0;
        for (PurchasableSquare square: this.getPlayer().getProperties()
        ) {
            if(square instanceof RailRoad){
                number += 1;
            }
        }
        return number;
    }
}
