/**
 * Class that stores and retrieves
 * instructor names
 * @author Anirudh Chauhan, Matthew Calora
 */

public enum Instructor {
    JENNIFER,
    KIM,
    DENISE,
    DAVIS,
    EMMA;

    /**
     * returns the instructor's name
     * as a string
     * @return name as string
     */
    public String toString() {
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }
}
