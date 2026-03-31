package com.example;
import java.util.*;

public class World {

    private Organism[][] grid;
    private int width;
    private int height;

    public World(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new Organism[height][width];
    }

    public Organism get(int x, int y) {
        return grid[y][x];
    }

    public void add(Organism o) {
        grid[o.getY()][o.getX()] = o;
    }

    public void remove(Organism o) {
        grid[o.getY()][o.getX()] = null;
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }

    public void move(Organism o, int newX, int newY) {
        grid[o.getY()][o.getX()] = null;
        o.setPosition(newX, newY);
        grid[newY][newX] = o;
    }

    public List<int[]> getNeighbors(int x, int y) {
        List<int[]> neighbors = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {

                if (dx == 0 && dy == 0) continue;

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < width && ny >= 0 && ny < height) {
                    neighbors.add(new int[]{nx, ny});
                }
            }
        }

        return neighbors;
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] instanceof Animal) {
                    animals.add((Animal) grid[y][x]);
                }
            }
        }

        return animals;
    }

    public void removeDead() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] instanceof Animal) {
                    Animal a = (Animal) grid[y][x];
                    if (a.isDead()) {
                        grid[y][x] = null;
                    }
                }
            }
        }
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x] == null) {
                    System.out.print(".");
                } else {
                    System.out.print(grid[y][x].getSymbol());
                }
            }
            System.out.println();
        }
    }
}