import java.util.ArrayList;

/**
 * A class that stores information about a fitness class
 * this includes ways to check in and drop members
 * from their class
 * @author Anirudh Chauhan, Matthew Calora
 */
public class FitnessClass {
    private Classes className;
    private Instructor instructor;
    private Time time;
    private Location location;
    private ArrayList<Member> fitClass;
    private ArrayList<Member> listGuest;

    /**
     * constructor for fitness class
     */
    public FitnessClass() {
        className = null;
        instructor = null;
        time = null;
        location = null;
        fitClass = new ArrayList<Member>();
        listGuest = new ArrayList<Member>();
    }

    /**
     * member check in method
     * @param member - member
     * @return boolean
     */
    public boolean checkIn(Member member) {
        if(fitClass.contains(member)) {
            return false;
        }
        else {
            fitClass.add(member);
            return true;
        }
    }

    /**
     * check in method for guests
     * @param member - guest
     * @return boolean
     */
    public boolean checkInGuest(Family member) {
        if(member.useGuestPass()) {
            listGuest.add(member);
            return true;
        }
        return false;
    }

    /**
     * drop guest from class method
     * @param member - guest
     * @return boolean
     */
    public boolean dropGuest(Family member) {
        if(listGuest.contains(member)) {
            member.addGuestPass();
            listGuest.remove(member);
            return true;
        }
        return false;
    }

    /**
     * drop member from class method
     * @param member - member
     * @return boolean
     */
    public boolean drop(Member member) {
        if (!fitClass.contains(member)) {
            return false;
        }
        else {
            fitClass.remove(member);
            return true;
        }
    }

    /**
     * sets class name
     * @param name
     */
    public void setClass(Classes name) {
        this.className = name;
    }

    /**
     * sets instructor name
     * @param instructor
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * sets location
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * sets time
     * @param time
     */
    public void setTime(Time time) {
        this.time = time;
    }

    /**
     * gets fitness class
     * @return classname
     */
    public Classes getFitClass() {
        return className;
    }

    /**
     * gets the instructor of the
     * fitness class
     * @return instructor
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * gets location of the
     * fitness class
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * gets time of the class
     * @return time
     */
    public Time getTime() {
        return time;
    }

    /**
     * gets the amount of members
     * registered for a fitness class
     * @return fitness class size
     */
    public int getLength() {
        return fitClass.size();
    }

    /**
     * checks if the member is registered
     * for the fitness class
     * @param member - member
     * @return boolean
     */
    public boolean memberCheck(Member member) {
        return fitClass.contains(member);
    }

    /**
     * checks if the input is equal to
     * the information stored in the database and
     * returns a boolean
     * @param obj - obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FitnessClass) {
            FitnessClass fitClass = (FitnessClass) obj;
            if(fitClass.getFitClass().equals(this.getFitClass()) &&
                    fitClass.getInstructor().equals(this.getInstructor()) &&
                    fitClass.getLocation().equals(this.getLocation())) {
                return true;
            }
        }
        return false;
    }

    /**
     * prints out participants of a
     * fitness class
     * @return string
     */
    public String toString() {
        String str = "";
        str = getFitClass().name() + " - " + getInstructor().name() + ", "
                + getTime().toString() + ", " + getLocation().name();
        if(!fitClass.isEmpty()) {
            str += "\n- Participants -";
            for(Member m : fitClass) {
                str += "\n\t";
                str += m.toString();
            }
        }
        if(!listGuest.isEmpty()) {
            str += "\n- Guests -";
            for(Member g : listGuest) {
                str += "\n\t";
                str += g.toString();
            }
        }
        return str;
    }
}