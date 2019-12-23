package com.company.board;

public class Building {

    private int costOfBuild;
    private PropertiesSquare square;

    public Building(int costOfBuild, PropertiesSquare square){
        this.costOfBuild = costOfBuild;
        this.square = square;
    }

    public int getCostOfBuild() {
        return costOfBuild;
    }

    public void setCostOfBuild(int costOfBuild) {
        this.costOfBuild = costOfBuild;
    }

    public PropertiesSquare getSquare() {
        return square;
    }

    public void setSquare(PropertiesSquare square) {
        this.square = square;
    }
}
