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
        public boolean isEmpty() {
            if(numClass == 0) {
                return true;
            }
            return false;
        }


    /**
     * prints schedule as is
     */
        public void printSchedule() {
            if(isEmpty()) {
                System.out.println("Fitness class schedule is empty.");
            }
            else {
                System.out.println("-Fitness classes-");
                for (int i = 0; i < numClass; i++) {
                    System.out.println(fitClass[i].toString());
                }
                System.out.println("-end of class list-");
            }
        }
}
