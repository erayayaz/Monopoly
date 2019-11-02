package com.company;

public class Piece {
    Shape shape;
    Square square;
    int location;

    public Piece(){
        //default object
    }

    public Piece(Square square, Shape shape){
        this.shape = shape;
        this.square = square;
        location = 0;
    }
    public Shape getShape(){
        return this.shape;
    }
    public Square getSquare(){
        return this.square;
    }

    public void moveTo(int dice_Value){
        location += dice_Value;
        this.square = Board.getSquare(location);
    }

}
