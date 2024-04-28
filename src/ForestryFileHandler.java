/**
 * Methods used by "ForestrySimulation.java" for reading .cvs/.db and writing to .db
 * @author Matt Pokorny
 * @see ForestrySimulation
 */

import java.io.*;

public class ForestryFileHandler {

    // Constants
    public static final String NEXT_EXTENSION = ".csv", SAVE_LOAD_EXTENSION = ".db";

    /**
     * Loads tree data from .csv file to Forest object.
     * @param myForestName Name of the forest to load.
     * @param myForest Forest object in which data is loaded into.
     * @return Outcome of method as boolean.
     */
    public static boolean nextForest(String myForestName, Forest myForest) {
        boolean next = true;

        BufferedReader myReader = null;
        try {
            myReader = new BufferedReader(new FileReader(myForestName + NEXT_EXTENSION));
            String currentLine = myReader.readLine();
            while (currentLine != null) {
                String[] currentTree = currentLine.split(",");
                myForest.addTree(currentTree[0], Integer.parseInt(currentTree[1]), Double.parseDouble(currentTree[2]), Double.parseDouble(currentTree[3]));
                currentLine = myReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(">>> File not found: " + e.getMessage());
            next = false;
        } catch (IOException e) {
            System.out.println(">>> Something went wrong: " + e.getMessage());
            next = false;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(">>> File format is incorrect: " + e.getMessage());
            next = false;
        } finally {
            if (myReader != null) {
                try {
                    myReader.close();
                    System.out.println(">>> Forest loaded: " + myForestName);
                } catch (IOException e) {
                    System.out.println(">>> Could not close file: " + e.getMessage());
                    next = false;
                }
            }
        }
        return next;
    } // end of nextForest method

    /**
     * Saves object data from a Forest object to new .db file.
     * @param myForestName Name of the forest to save as.
     * @param myForest Forest object in which object data is written from.
     * @return Outcome of method as boolean.
     */
    public static boolean saveForest(String myForestName, Forest myForest) {
        boolean save = true;

        ObjectOutputStream toStream = null;
        try {
            toStream = new ObjectOutputStream(new FileOutputStream(myForestName + SAVE_LOAD_EXTENSION));
            for (int index = 0; index < myForest.trees.size(); index++) {
                if (myForest.trees.get(index) != null) {
                    toStream.writeObject(myForest.trees.get(index));
                }
            }
        } catch (IOException e) {
            System.out.println(">>> Could not save forest: " + e.getMessage());
            save = false;
        } finally {
            if (toStream != null) {
                try {
                    toStream.close();
                } catch (IOException e) {
                    System.out.println(">>> Could not close file: " + e.getMessage());
                    save = false;
                }
            }
        }
        return save;
    } // end of saveForest method

    /**
     * Loads tree data from a .db file to new "back-up" Forest object;
     * if successful, Forest object in params points to backed-up Forest.
     * @param myForestName Name of the forest to load.
     * @param myForest Forest object in which data is loaded into from backup.
     * @return Outcome of method as boolean.
     */
    public static boolean loadForest(String myForestName, Forest myForest) {
        boolean load = true;
        Forest myForestBackup = new Forest();

        ObjectInputStream fromStream = null;
        try {
            fromStream = new ObjectInputStream(new FileInputStream(myForestName + SAVE_LOAD_EXTENSION));
            Tree myTree = (Tree)fromStream.readObject();
            while (myTree != null) {
                myForestBackup.trees.add(myTree);
                myTree = (Tree)fromStream.readObject();
            }
        } catch (EOFException e) {
            // end of file reached
        } catch (IOException e) {
            System.out.println(">>> Error loading forest: " + e.getMessage());
            load = false;
        } catch (ClassNotFoundException e) {
            System.out.println(">>> File not found: " + e.getMessage());
            load = false;
        } finally {
            if (fromStream != null) {
                try {
                    fromStream.close();
                    myForest.trees = myForestBackup.trees;
                } catch (IOException e) {
                    System.out.println(">>> Could not close file: " + e.getMessage());
                    load = false;
                }
            }
        }
        return load;
    } // end of loadForest method
}
