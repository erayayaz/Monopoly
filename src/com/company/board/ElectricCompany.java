package com.company.board;

import com.company.Player_die.Dice;
import com.company.Player_die.Player;

public class ElectricCompany extends PurchasableSquare {

    public ElectricCompany(String name, int index, int cost) {
        super(name, index,cost);
    }

    @Override
    public void rent(Player player, int dice) {
        if (this.isOwned()){
            int modifier = 0;
            if(hasWaterWorks(this.getPlayer())){
                modifier = 10;
            }
            else{
                modifier = 4;
            }
            int money = modifier * dice;
            player.decreaseMoney(money);
            this.getPlayer().increaseMoney(money);
        }
        if ( player.getMoney() <= 0)
            player.setBankrupt(true);
    }

    @Override
    public void buy(Player player) {
        if (player.getMoney() >= this.getPrice() && player.rollDice(new Dice()) + player.rollDice(new Dice()) >= 8 && !this.isOwned()){
            this.setPlayer(player);
            this.setOwned(true);
            player.getProperties().add(this);
        }
    }

    @Override
    public void action(Player player){

    }

    public boolean hasWaterWorks(Player player){
        for (PurchasableSquare square: this.getPlayer().getProperties()){
                if(square instanceof WaterWorks){
                    return true;
                }
        }
        return false;
    }
}
