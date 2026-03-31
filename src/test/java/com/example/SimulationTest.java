package com.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Random;

public class SimulationTest {

    @Test
    public void testHerbivoreEatsPlant() {
        World world = new World(2, 1);
        Herbivore herbivore = new Herbivore(0, 0);
        Plant plant = new Plant(1, 0);

        world.add(herbivore);
        world.add(plant);

        Random rng = new Random(1);
        herbivore.act(world, rng);

        // After eating, plant should be gone
        assertNull(world.get(0, 0));
        assertSame(herbivore, world.get(1, 0));
    }

    @Test
    public void testHerbivoreDoesNotEatHerbivore() {
        World world = new World(2, 2);
        Herbivore h1 = new Herbivore(0, 0);
        Herbivore h2 = new Herbivore(0, 1);

        world.add(h1);
        world.add(h2);

        Random rng = new Random(1);
        h1.act(world, rng);

        // Second herbivore should still exist
        assertNotNull(world.get(h2.getX(), h2.getY()));
    }

    @Test
    public void testHerbivoreDiesAfterEnergyZero() {
        World world = new World(1, 1);
        Herbivore herbivore = new Herbivore(0, 0);
        world.add(herbivore);

        Random rng = new Random(1);

        // Herbivore starts with energy 10
        for (int i = 0; i < 10; i++) {
            herbivore.act(world, rng);
        }

        assertTrue(herbivore.isDead());
    }

    @Test
    public void testCarnivoreDiesAfterEnergyZero() {
        World world = new World(1, 1);
        Carnivore carnivore = new Carnivore(0, 0);
        world.add(carnivore);

        Random rng = new Random(1);

        // Carnivore starts with energy 20
        for (int i = 0; i < 20; i++) {
            carnivore.act(world, rng);
        }

        assertTrue(carnivore.isDead());
    }
}