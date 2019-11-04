package com.company.board;

public abstract class Square {

    private String name;
    private int index;

    public Square(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return "Square{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }





}
