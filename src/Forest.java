/**
 * Forest containing tree objects.
 * @author Matt Pokorny
 */

import java.io.*;
import java.util.ArrayList;

public class Forest implements Serializable {

    // Arraylist of tree objects in forest.
    ArrayList<Tree> trees;

    /**
     * Default constructor.
     */
    public Forest() {
        trees = new ArrayList<>();
    } // end of default constructor

    /**
     * Displays information about the forest, including trees and average tree height.
     * @param myForestName Name of the forest being displayed.
     */
    public void displayForest(String myForestName) {
        System.out.println(">>> Forest name: " + myForestName);
        double totalHeight = 0;
        for (int index = 0; index < trees.size(); index++) { // Each loop adds height of each tree to total
            System.out.println("    " + index + ": " + trees.get(index));
            totalHeight += trees.get(index).getFeet();
        }
        System.out.println(">>> There are " + trees.size() + " trees, with an average height of " + String.format("%.2f", totalHeight/trees.size()));
    } // end of displayForest method

    /**
     * Adds new randomized tree to end of the forest.
     */
    public void addTree() {
        trees.add(new Tree());
    } // end of addTree method

    /**
     * Overloaded method;
     * adds new specified tree to end of the forest.
     * @param species Species classification of tree.
     * @param date Year of tree's initial planting.
     * @param feet Height of tree measured in feet.
     * @param growth Percent rate of tree growth in year.
     */
    public void addTree(String species, int date, double feet, double growth) {
        trees.add(new Tree(species, date, feet, growth));
    } // end of addTree method

    /**
     * Removes specified tree from the forest.
     * @param index Index of specified tree.
     * @return Outcome of method as boolean.
     */
    public boolean cutTree(int index) {
        if (index >= 0 && index < trees.size()) { // If tree exists in index, cut it
            trees.remove(index);
            return true;
        } else {
            return false;
        }
    } // end of cutTree method

    /**
     * Simulates year of growth for all trees in the forest.
     */
    public void growTrees() {
        for (Tree myTree : trees) {
            myTree.grow();
        }
    } // end of growTrees method

    /**
     * Replaces all trees above specified height with new randomized tree.
     * @param reapHeight Maximum height of tree to not be reaped.
     */
    public void reapTrees(double reapHeight) {
        for (int index = 0; index < trees.size(); index++) {
            if (trees.get(index).getFeet() > reapHeight) { // If tree is above reap height, reap it
                trees.set(index, new Tree());
            }
        }
    } // end of reapHeight method
}
