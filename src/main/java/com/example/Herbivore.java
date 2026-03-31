package com.example;
public class Herbivore extends Animal {

    public Herbivore(int x, int y) {
        super(x, y, Simulation.INITIAL_HERBIVORE_ENERGY);
    }

    @Override
    public boolean canEat(Organism o) {
        return o instanceof Plant;
    }

    @Override
    public int getEnergyGain() {
        return Simulation.PLANT_ENERGY_GAIN;
    }

    @Override
    public char getSymbol() {
        return 'H';
    }
}