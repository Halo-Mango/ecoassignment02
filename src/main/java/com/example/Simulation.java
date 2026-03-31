package com.example;
import java.util.*;

public class Simulation {

    //CONSTANTS
    public static final int INITIAL_HERBIVORE_ENERGY = 10;
    public static final int INITIAL_CARNIVORE_ENERGY = 20;
    public static final int PLANT_ENERGY_GAIN = 5;
    public static final int HERBIVORE_ENERGY_GAIN = 10;

    public static void main(String[] args) {

        
        if (args.length != 7) {
            System.out.println("Usage: java Simulation width height ticks seed plants herbivores carnivores");
            return;
        }

        int width = Integer.parseInt(args[0]);
        int height = Integer.parseInt(args[1]);
        int ticks = Integer.parseInt(args[2]);
        long seed = Long.parseLong(args[3]);
        int plants = Integer.parseInt(args[4]);
        int herbivores = Integer.parseInt(args[5]);
        int carnivores = Integer.parseInt(args[6]);

        if (plants + herbivores + carnivores > width * height) {
            System.out.println("Too many organisms for the grid.");
            return;
        }

        Random rng = new Random(seed);
        World world = new World(width, height);

        //PLACE ORGANISMS
        placeRandom(world, plants, herbivores, carnivores, rng);

        // PRINT INITIAL STATE 
        System.out.println("Tick 0");
        world.print();
        printCounts(world);

        // RUN SIMULATION 
        for (int t = 1; t <= ticks; t++) {

            List<Animal> animals = world.getAnimals();

            for (Animal a : animals) {
                if (!a.isDead()) {
                    a.act(world, rng);
                }
            }

            world.removeDead();

            System.out.println("\nTick " + t);
            world.print();
            printCounts(world);
        }
    }

    // RANDOM PLACEMENT
    private static void placeRandom(World world,
                                    int plants,
                                    int herbivores,
                                    int carnivores,
                                    Random rng) {

        int width = world.getWidth();
        int height = world.getHeight();

       
        for (int i = 0; i < plants; i++) {
            placeOne(world, new Plant(0, 0), rng, width, height);
        }

        
        for (int i = 0; i < herbivores; i++) {
            placeOne(world, new Herbivore(0, 0), rng, width, height);
        }

       
        for (int i = 0; i < carnivores; i++) {
            placeOne(world, new Carnivore(0, 0), rng, width, height);
        }
    }

    private static void placeOne(World world,
                                 Organism o,
                                 Random rng,
                                 int width,
                                 int height) {

        while (true) {
            int x = rng.nextInt(width);
            int y = rng.nextInt(height);

            if (world.get(x, y) == null) {
                o.setPosition(x, y);
                world.add(o);
                break;
            }
        }
    }

    //  POPULATION COUNTS
    private static void printCounts(World world) {

        int plants = 0;
        int herbivores = 0;
        int carnivores = 0;

        for (int y = 0; y < world.getHeight(); y++) {
            for (int x = 0; x < world.getWidth(); x++) {

                Organism o = world.get(x, y);

                if (o instanceof Plant) plants++;
                else if (o instanceof Herbivore) herbivores++;
                else if (o instanceof Carnivore) carnivores++;
            }
        }

        System.out.println("Plants: " + plants +
                "  Herbivores: " + herbivores +
                "  Carnivores: " + carnivores);
    }
}