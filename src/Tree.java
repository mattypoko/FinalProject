/**
 * Generic tree.
 * @author Matt Pokorny
 */

import java.io.*;

public class Tree implements Serializable {

    // Enum containing tree species names.
    enum Species {
        Birch, Maple, Fir
    }

    // Variables for species, date of planting, height and growth rate.
    private final String species;
    private final int date;
    private double feet;
    private final double growth;

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