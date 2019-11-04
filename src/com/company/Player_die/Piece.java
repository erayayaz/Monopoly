package com.company.Player_die;

import com.company.board.Board;
import com.company.board.Square;


public class Piece {
    Square square;
    int location;
    String name;

    public Piece(){
        //default object
    }

    public Piece(String name){
        this.name = name;
        location = 0;
    }

    public Square getSquare(){
        return this.square;
    }

    public void moveTo(int dice_Value){
        location += dice_Value;
        //  this.square = Board.getSquareObject(location);
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
