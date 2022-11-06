/**
 * Class that stores and retrieves information about
 * the name of a fitness class
 * @author Anirudh Chauhan, Matthew Calora
 */

public enum Classes {
    PILATES,
    SPINNING,
    CARDIO;

    /**
     * getter that retrieves the fitness
     * class name as a string
     * @return fitness class name as string
     */
    public String getClassName() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }
}
