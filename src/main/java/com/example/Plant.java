package com.example;
public class Plant extends Organism {

    public Plant(int x, int y) {
        super(x, y);
    }

    @Override
    public char getSymbol() {
        return '*';
    }
}