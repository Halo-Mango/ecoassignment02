package com.example;
public abstract class Organism {

    protected int x;
    protected int y;

    public Organism(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract char getSymbol();

    public int getX() { return x; }
    public int getY() { return y; }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}