package com.company.board;

import com.company.Player_die.Piece;

import java.util.ArrayList;

public class Board {

    private final int NUMBER_OF_SQUARES = 39;
    private ArrayList<Square> squaresOnBoard;
    private ArrayList<Piece> pieces;
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

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public Board initializeBoard(int numberOfTaxSquare, int taxAmount, int goSquareMoney) {

        Board board = new Board();
        Square goSquare = new GoSquare("goSquare", 0, goSquareMoney);
        board.getSquaresOnBoard().add(goSquare);

        int randomIndexNumber = 0;
        Square jail = new JailSquare("Jail", 19);
        board.getSquaresOnBoard().add(jail.getIndex(), jail);

        Square gotojail =  new ArrestedSquare("Arrest", 29);
        board.getSquaresOnBoard().add(gotojail.getIndex(),gotojail);


        for (int i = 0; i < numberOfTaxSquare;) {

            randomIndexNumber = (int) ((Math.random() * 38) + 1);

            if( board.getSquaresOnBoard().get(randomIndexNumber) ==  null ){
                Square taxSquare = new TaxSquare("Tax", randomIndexNumber, taxAmount);
                i++;
            }
        }


        for (int i = 0; i < NUMBER_OF_SQUARES - 1  ; i++) {
            if ( board.getSquaresOnBoard().get(i) == null ){
                Square regularSquare =  new RegularSquare("Regular Square", i);
            }
        }

        return board;

    }


}
