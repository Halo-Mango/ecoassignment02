package com.example;
public class Carnivore extends Animal {

    public Carnivore(int x, int y) {
        super(x, y, Simulation.INITIAL_CARNIVORE_ENERGY);
    }

    @Override
    public boolean canEat(Organism o) {
        return o instanceof Herbivore;
    }

    @Override
    public int getEnergyGain() {
        return Simulation.HERBIVORE_ENERGY_GAIN;
    }

    @Override
    public char getSymbol() {
        return 'C';
    }
}