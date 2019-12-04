package com.company.board;

import com.company.Player_die.Dice;
import com.company.Player_die.Player;

public class PropertiesSquare extends PurchasableSquare {

   private int houseNumber;
   private int rent;

    public PropertiesSquare(String name, int index, int price, int rent) {
        super(name, index, price);
        this.rent = rent;
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
            this.getPlayer().increaseMoney(player.decreaseMoney(this.getRent()));
            if (player.getMoney() <= 0)
                player.setBankrupt(true);
        }

    }
}