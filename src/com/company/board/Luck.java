package com.company.board;

import com.company.Player_die.Player;

public class Luck {

    static int current = 0;
    public Luck() {

    }
    public static void pickCard(Player player){

        if(current==0){//para 100 artsın
            System.out.println("Take 100 ₺ from safe.");
            player.increaseMoney(100);
            current++;
        }else if(current==1){
            System.out.println("Take 200 ₺ from safe.");
            player.increaseMoney(200);
            current++;
        }else if(current==2){
            player.getPiece().setLocation(player.getPiece().getLocation()+3);
            System.out.println("Go 3 more steps on the board.");
            current++;
        }else if(current==3){
            player.getPiece().setLocation(player.getPiece().getLocation()-3);
            System.out.println("Back 3 more steps on the board");
            current++;
        }else if(current==4){
            System.out.println("Pay 100 ₺ for real estate tax.");
           // player.setMoney(player.getMoney()- 100);
            player.decreaseMoney(100);
            current++;
        }else if(current==5){
            System.out.println("You have a traffic penalty and You will pay the 200 ₺.");
            //player.setMoney(player.getMoney()-200);
            player.decreaseMoney(200);
            current=0;
        }
    }
}
