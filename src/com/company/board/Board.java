package com.company.board;

import java.util.ArrayList;

public class Board {

    private ArrayList<Square> squaresOnBoard;

    public Board() {

        this.squaresOnBoard = new ArrayList<>();

    }

    public ArrayList<Square> getSquaresOnBoard() {
        return squaresOnBoard;
    }

    public void setSquaresOnBoard(ArrayList<Square> squaresOnBoard) {
        this.squaresOnBoard = squaresOnBoard;
    }


    public Square getSquareObject(Board board, int index) {
        return board.getSquaresOnBoard().get(index);
    }




    public Board initializeBoard() {

        Board board = new Board();
        Square goSquare = new GoSquare("goSquare", 0, 200);
        board.getSquaresOnBoard().add(goSquare);

        



        return board;

    }


}
