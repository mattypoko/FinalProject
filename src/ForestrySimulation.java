/**
 * Simulates forests in order as specified on the command line;
 * support for saving/loading forests from file.
 * @author Matt Pokorny
 */

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ForestrySimulation {
    private static final Scanner scnr = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("\n>>> WELCOME TO THE FORESTRY SIMULATION!");
        System.out.println(">>> Current directory is: " + new File("").getAbsoluteFile()); // Path directory
        System.out.println("---------------------------------------");

        if (args.length == 0) { // Checks if command line has input (required)
            System.out.println(">>> No command line arguments found.");
        }

        String userChoice = " ";
        for (String myForestName : args) {
            Forest myForest = new Forest();

            if (!(userChoice.equals("X")) && ForestryFileHandler.nextForest(myForestName, myForest)) { // If program hasn't quit and next tree loaded successfully
                do {
                    System.out.print("\n* (P)rint, (A)dd, (C)ut, (G)row, (R)eap, (S)ave, (L)oad, (N)ext, e(X)it: ");
                    userChoice = (scnr.next()).toUpperCase();

                    switch (userChoice) {
                        case "P": // Prints forest information to user
                            myForest.displayForest(myForestName);
                            break;

                        case "A":
                            myForest.addTree(); // Add a new randomized tree to the forest
                            System.out.println(">>> A random tree has been planted!");
                            break;

                        case "C": // Cut down a specified tree by index
                            System.out.print("* Enter tree # to cut down: ");
                            try {
                                int treeNumber = scnr.nextInt();
                                if (myForest.cutTree(treeNumber)) {
                                    System.out.println(">>> Tree " + treeNumber + " has been cut down!");
                                } else {
                                    System.out.println(">>> Tree " + treeNumber + " does not exist.");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println(">>> Invalid input entered.");
                                scnr.nextLine();
                            } break;

                        case "G": // For all trees, simulate a years worth of growth
                            myForest.growTrees();
                            System.out.println(">>> A year's worth of growth has occurred!");
                            break;

                        case "R": // Reap all trees above a specified height
                            System.out.print("* Enter height to reap from: ");
                            try {
                                double reapHeight = scnr.nextDouble();
                                myForest.reapTrees(reapHeight);
                                System.out.println(">>> Trees above " + reapHeight + " feet have been reaped!");
                            } catch (InputMismatchException e) {
                                System.out.println(">>> Invalid input entered.");
                                scnr.nextLine();
                            } break;

                        case "S": // Save forest information to .db file named after forest
                            if (ForestryFileHandler.saveForest(myForestName, myForest)) {
                                System.out.println(">>> File saved!");
                            } break;

                        case "L": // Load forest information from .db file
                            System.out.print("* Enter forest name: ");
                            String forestName = scnr.next();

                            if (ForestryFileHandler.loadForest(forestName, myForest)) {
                                System.out.println(">>> File loaded!");
                            } else {
                                System.out.println(">>> Old forest retained.");
                            } break;

                        case "N": // Load next forest from command line input
                            System.out.println(">>> Proceeding to next forest...");
                            break;

                        case "X": // Exit program, skipping all remaining forests.
                            System.out.println(">>> Exiting program...");
                            break;

                        default:
                            System.out.println(">>> Invalid input '" + userChoice + "' entered.");
                            break;
                    }
                } while (!(userChoice.equals("X") || userChoice.equals("N")));
            }
        }
        System.out.println(">>> Forestry Simulation is complete.");
    }
}