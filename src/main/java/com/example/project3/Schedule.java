package com.example.project3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class that stores schedule information from
 * the text file given
 * @author Anirudh Chauhan, Matthew Calora
 */

public class Schedule {
        private FitnessClass[] fitClass;
        private int numClass;
        public static final int CAPACITY = 3;
        public static final int GROWTH_RATE = 3;

    /**
     * constructor for intial schedule
     */
        public Schedule() {
            numClass = 0;
            fitClass = new FitnessClass[CAPACITY];
        }

    /**
     * grows the schedule if maximum
     * capacity is reached
     */
        private void grow() {
            FitnessClass[] newSchedule = new FitnessClass[fitClass.length + GROWTH_RATE];
            for (int i = 0; i < numClass; i++) {
                newSchedule[i] = fitClass[i];
            }
            fitClass = newSchedule;
        }

    /**
     * adds class to schedule
     * @param fit
     */
        public void addClass(FitnessClass fit) {
            fitClass[numClass] = fit;
            numClass++;
            if (numClass == fitClass.length) {
                grow();
            }
        }

    /**
     * returns the number of classes in
     * the schedule
     * @return fitclass
     */
        public int getNumClasses() {
            return numClass;
        }
        public FitnessClass getFitnessClass(int index) {
            if (index >= numClass || index < 0) {
                return null;
            }
            return fitClass[index];
        }

    /**
     * returns the fitness class name
     * @param fclass
     * @return fitclass
     */
        public FitnessClass getFitnessClass(FitnessClass fclass) {
            for (int i = 0; i < numClass; i++) {
                if (fclass.equals(fitClass[i])) {
                    return fitClass[i];
                }
            }
            return null;
        }

    /**
     * checks if the schedule array is
     * empty or not
     * @return true or false
     */
    public String[] readLines(String filename) {
        try {
            String [] output = new String[numLines("classSchedule.txt")];
            File file = new File("classSchedule.txt");
            Scanner sc = new Scanner(file);
            String line = "";
            int i = 0;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                output[i++] = line;
            }
            sc.close();
            return output;
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            String[] error = new String[1];
            error[0] = "File not found";
            return error;
        }
    }
    public int numLines(String filename){
        try{
            File file = new File("classSchedule.txt");
            Scanner sc = new Scanner(file);
            int i = 0;

            while (sc.hasNextLine()) {
                sc.nextLine();
                i++;
            }
            sc.close();
            return i;
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return 0;
        }

    }
}
