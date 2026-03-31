package com.example;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.util.Random;

public class SimulationTest {
    // This test is to check if a herbivore eats a plant if the plant is next to it
    @Test
    public void testHerbivoreEatsPlant() {
        //creates a 2x1 world
        World world = new World(2, 1);
        // herb location at axis (0,0) and plants
        Herbivore herbivore = new Herbivore(0, 0);
        Plant plant = new Plant(1, 0);
        // adds them to the world
        world.add(herbivore);
        world.add(plant);

        Random rng = new Random(1);
        herbivore.act(world, rng);

        // After eating the plant should be gone
        assertNull(world.get(0, 0));
        assertSame(herbivore, world.get(1, 0));
    }
    // This test will check if a herbivore will eat another herbivore
    @Test
    public void testHerbivoreDoesNotEatHerbivore() {
        World world = new World(2, 2);
        Herbivore h1 = new Herbivore(0, 0);
        Herbivore h2 = new Herbivore(0, 1);

        world.add(h1);
        world.add(h2);

        Random rng = new Random(1);
        h1.act(world, rng);

        // this to see if the other herbivore still lives
        assertNotNull(world.get(h2.getX(), h2.getY()));
    }
    // Checks that a herbivore will die once its energy has reached zero
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
    
    // Will check if a carnivore will die after its energy has reached zero.
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
