package com.example;
import java.util.*;

public abstract class Animal extends Organism {

    protected int energy;

    public Animal(int x, int y, int energy) {
        super(x, y);
        this.energy = energy;
    }

    public abstract boolean canEat(Organism o);
    public abstract int getEnergyGain();

    public void act(World world, Random rng) {

        List<int[]> neighbors = world.getNeighbors(x, y);
        Collections.shuffle(neighbors, rng);

        // Try to eat
        for (int[] pos : neighbors) {
            Organism o = world.get(pos[0], pos[1]);
            if (o != null && canEat(o)) {
                world.remove(o);
                world.move(this, pos[0], pos[1]);
                energy += getEnergyGain();
                energy--;
                return;
            }
        }

        // Try to move
        for (int[] pos : neighbors) {
            if (world.get(pos[0], pos[1]) == null) {
                world.move(this, pos[0], pos[1]);
                energy--;
                return;
            }
        }

        // Stay
        energy--;
    }

    public boolean isDead() {
        return energy <= 0;
    }
}