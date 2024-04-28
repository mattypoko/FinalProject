/**
 * Generic tree.
 * @author Matt Pokorny
 */

import java.io.*;
import java.util.Random;

public class Tree implements Serializable {

    private static final Random rand = new Random();

    // Enum containing tree species names.
    enum Species {
        Birch, Maple, Fir
    }

    // Constants
    public static final int MINIMUM_DATE = 2000, MAXIMUM_DATE = 2024;
    public static final double MINIMUM_HEIGHT_GROWTH = 10.0, MAXIMUM_HEIGHT_GROWTH = 20.0;

    // Variables for species, date of planting, height and growth rate.
    private final String species;
    private final int date;
    private double feet;
    private final double growth;

    /**
     * Default constructor;
     * Gets random values for each variables.
     */
    public Tree() {
        species = Species.values()[rand.nextInt(Species.values().length)].name(); // random species between Birch, Maple and Fir
        date = rand.nextInt(MINIMUM_DATE,MAXIMUM_DATE + 1); // random date between 2000 and 2024
        feet = rand.nextDouble(MINIMUM_HEIGHT_GROWTH, MAXIMUM_HEIGHT_GROWTH); // random height between 10.00' and 19.99'
        growth = rand.nextDouble(MINIMUM_HEIGHT_GROWTH, MAXIMUM_HEIGHT_GROWTH); // random height between 10.0 and 19.9%
    } // end of default constructor

    /**
     * Initial value constructor.
     * @param species Species classification of tree.
     * @param date Year of tree's initial planting.
     * @param feet Height of tree measured in feet.
     * @param growth Percent rate of tree growth in year.
     */
    public Tree(String species, int date, double feet, double growth) {
        this.species = species;
        this.date = date;
        this.feet = feet;
        this.growth = growth;
    } // end of initial value constructor

    /**
     * Produce printable information about the tree.
     * @return String with tree's species, date of planting, height and growth rate.
     */
    public String toString() {
        return species + "  " + date + "    " + String.format("%.2f", feet) + "'    " + String.format("%.1f", growth) + "%";
    } // end of toString method

    /**
     * Get current height of the tree.
     * @return Height of tree measured in feet.
     */
    public double getFeet() {
        return feet;
    } // end of getFeet method

    /**
     * Increase the tree's height by simulating year of growth.
     */
    public void grow() {
        feet += feet * (growth / 100);
    } // end of grow method
}