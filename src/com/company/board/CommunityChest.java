package com.company.board;

import com.company.Player_die.Player;

public class CommunityChest {

    static int current = 0;
    public CommunityChest(String name, int index) {
        int[] cardQueque  = new int[10] ;
        for(int i =0; i<10;i++){
            cardQueque[i]=i;
        }
        if(cardQueque[0]==0){//para ekle

        }
    }
    public static void pickCard(Player player){
        if(current==0){//para 100 artsın
            System.out.println("Take 300 ₺ from safe.");
            player.increaseMoney(300);
            current++;
        }else if(current==1){
            System.out.println("You did a crime last night.Go to the jail with the policemen.");
            player.getPiece().setInJail(true);
            current++;
        }else if(current==2){
            System.out.println("You will go to the go square place and take the money.");
            player.getPiece().setLocation(0);
            current++;
        }else if(current==3){
            System.out.println("Thanks for your ambition. Bye");
            player.setBankrupt(true);
            current++;
        }
        else if(current==4){
            System.out.println("Give 100 ₺ to safe.");
            player.decreaseMoney(100);
            current = 0;
        }


    }

}
