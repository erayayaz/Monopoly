package com.company.board;

import com.company.Player_die.Piece;

import java.util.ArrayList;

public class Board {

    private final int NUMBER_OF_SQUARES = 40;
    private int numberOfTaxSquares;
    private int taxAmount;
    private int goSquareMoney;
    private ArrayList<Square> squaresOnBoard;
    private ArrayList<Piece> pieces;

    public Board(int numberOfTaxSquares, int taxAmount, int goSquareMoney) {
        this.squaresOnBoard = new ArrayList<>();
        setNumberOfTaxSquares(numberOfTaxSquares);
        setTaxAmount(taxAmount);
        setGoSquareMoney(goSquareMoney);
        initializeBoard();
    }

    public void initializeBoard() {

        String[] namesOfSquares = {"Üsküdar","Zümrütevler","Çamlıca","Fındıklı","Kozyatağı","Beylikdüzü","Göztepe","Fikirtepe",
                                      "Ataşehir","Sarıyer","Fatih", "Mecidiyeköy","Çemenzar","Kuyubaşı","Şişli","Beşiktaş","Fenerbahçe",
                                      "Koşuyolu","Erenköy","Bağdat Street","Haliç","Eyüp","Maslak","Bebek","Acıbadem","Ünalan","Kuyubaşı","Aslantepe","Ziverbey",
                                    "Küçükçekmece","Göktürk","Sarıgöl", "Sümer", "GOP", "Silivri","Tarlabaşı","Okmeydanı","Taksim","Etiler","Kavacık"};

        for (int i = 0; i < NUMBER_OF_SQUARES; i++) {
            this.getSquaresOnBoard().add(null);
        }
        Square luckSquare = new LuckCard("luckSqaure",30);
        getSquaresOnBoard().set(luckSquare.getIndex(),luckSquare);

        Square communityChestSquare = new CommunityChestCard("luckSqaure",32);
        getSquaresOnBoard().set(communityChestSquare.getIndex(),communityChestSquare);

        Square goSquare = new GoSquare("goSquare", 0, getGoSquareMoney());
        getSquaresOnBoard().set(goSquare.getIndex(),goSquare);

        int randomIndexNumber = 0;

        Square electric = new ElectricCompany("ElectricCompany", 10,150);
        getSquaresOnBoard().set(electric.getIndex(), electric);

        Square water = new WaterWorks("WaterCompany", 20,150);
        getSquaresOnBoard().set(water.getIndex(), water);
        int a = 6;
        for (int i = 0; i < 4; i++) {
            Square railRoad = new RailRoad("RailRoad", a, 200);
            getSquaresOnBoard().set(railRoad.getIndex(), railRoad);
            a += 10;
        }

        Square jail = new JailSquare("Jail", 19);
        getSquaresOnBoard().set(jail.getIndex(), jail);

        Square gotojail =  new ArrestedSquare("Arrest", 29);
        getSquaresOnBoard().set(gotojail.getIndex(),gotojail);

        for (int i = 0; i < getNumberOfTaxSquares(); ) {
            randomIndexNumber = (int) ((Math.random() * 38) + 1);

            if( getSquaresOnBoard().get(randomIndexNumber) ==  null ){
                Square taxSquare = new TaxSquare("Tax", randomIndexNumber, taxAmount);
                getSquaresOnBoard().set(randomIndexNumber,taxSquare);
                i++;
            }
        }
        int price = 50;
        for (int i = 0; i < NUMBER_OF_SQUARES   ; i++) {
            if ( getSquaresOnBoard().get(i) == null ){
                Square propertieSquare =  new PropertiesSquare(namesOfSquares[i],i,price,price / 4);
                getSquaresOnBoard().set(i,propertieSquare);
                price += 10;
            }
        }
    }

    public ArrayList<Square> getSquaresOnBoard() {
        return squaresOnBoard;
    }

    public void setSquaresOnBoard(ArrayList<Square> squaresOnBoard) {
        this.squaresOnBoard = squaresOnBoard;
    }

    public int getNumberOfTaxSquares() {
        return numberOfTaxSquares;
    }

    public void setNumberOfTaxSquares(int numberOfTaxSquares) {
        this.numberOfTaxSquares = numberOfTaxSquares;
    }

    public int getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(int taxAmount) {
        this.taxAmount = taxAmount;
    }

    public int getGoSquareMoney() {
        return goSquareMoney;
    }

    public void setGoSquareMoney(int goSquareMoney) {
        this.goSquareMoney = goSquareMoney;
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
}
