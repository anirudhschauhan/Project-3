import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * This class stores information about a single member
 * Stores the first name, last name, date of birth,
 * expiration date of membership, gym location.
 * @author Anirudh Schauhan, Matthew Calora
 */

public class Member implements Comparable<Member> {
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;
    private int guestPasses;
    private final int QUARTERLY = 3;
    private final double ONE_TIME_FEE = 29.99;
    private final double MEMBERSHIP_FEE = 39.99;
    private final int NUMBER_OF_GUESTS = 0;

    /**
     * Default constructor for member class
     */
    public Member() {
        this.fname = "";
        this.lname = "";
        this.dob = null;
        this.expire = null;
        this.location = null;
    }

    /**
     * Constructor that creates an instance of the member class.
     *
     * @param fname - member first name
     * @param lname - member last name
     * @param dob - member date of birth
     * @param expire - member gym expiration date
     * @param location - gym location where member has enrolled
     */
    public Member(String fname, String lname, Date dob, Date expire, Location location) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     * class that calculates and returns the
     * membership fee according to the member's
     * membership type
     * @return membershipFee
     */
    public double membershipFee(){
        return (ONE_TIME_FEE + (QUARTERLY * MEMBERSHIP_FEE));
    }

    /**
     * prints out member information
     * @return the member's information
     */
    @Override
    public String toString() { //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX
        //need to figure out date class
        return (this.fname + " "+ this.lname + ", DOB: " + this.dob + ", Membership expires " + this.expire + ", Location: " + this.location);

    }

    /**
     * checks if member is already in the database
     * @param obj - member to be searched for
     * @return true or false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member equalmember = (Member) obj;
            if (this.fname.equals(equalmember.fname) && (this.lname.equals(equalmember.lname)) && (this.dob.compareTo(equalmember.dob)) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Compares members
     * @param member the object to be compared.
     * @return 1, -1, or 0
     */
    @Override
    public int compareTo(Member member) {
        int compareLast = this.getLname().toUpperCase().compareTo(member.getLname().toUpperCase());
        int compareFirst = this.getFname().toUpperCase().compareTo(member.getFname().toUpperCase());
        if (compareLast > 0) {
            return 1; // current member's name is after the other
        }
        if (compareLast < 0) {
            return -1; // current member's name is before the other
        }

        if (compareFirst > 0) {
            return 1; // current member's name is after the other
        }
        if (compareFirst < 0) {
            return -1; // current member's name is before the other
        }
        return 0; // the names are the same
    }

    /**
     * compares member location
     * @param member - member to be compared
     * @return 1, -1, or 0
     */
    public int compareLocation(Member member) {
        int compareCounty = this.getLocation().getCounty().compareTo(member.getLocation().getCounty());
        int compareZipcode = this.getLocation().getZipCode().compareTo(member.getLocation().getZipCode());
        if (compareCounty > 0) {
            return 1; // current member's county is after the other
        }
        if (compareCounty < 0) {
            return -1; // current member's county is before the other
        }

        if (compareZipcode > 0) {
            return 1; // current member's zipcode is after the other
        }
        if (compareZipcode < 0) {
            return -1; // current member's zipcode is before the other
        }
        return 0; // the members are the same
    }

    /**
     * sets first name of the member
     * @param fname
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * returns the first name of the member
     * @return fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * sets last name of the member
     * @param lname
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * gets last name of the member
     * @return lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * returns the membership type
     * of the member
     * @return membership type
     */
    public String memberType(){
        return "Standard";
    }

    /**
     * sets date of birth of the member
     * @param dob
     */
    public void setDOB(Date dob) {
        this.dob = dob;
    }

    /**
     * gets the date of birth of the member
     * @return dob
     */
    public Date getDOB() {
        return dob;
    }

    /**
     * sets expiration date of the member
     * @param expire
     */
    public void setExpire(Date expire) {
        this.expire = expire;
    }

    /**
     * gets the expiration date of the member
     * @return expire
     */
    public Date getExpire() {
        return expire;
    }

    /**
     * sets the location of the member
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * gets the location of the member
     * @return loaction
     */
    public Location getLocation() {
        return location;
    }

    /**
     * checks if the date of birth is
     * a valid date
     * @return true or false
     */
    public boolean isDOBValid() {
        return dob.validateDate();
    }

    /**
     * checks if the expiration date
     * is a valid date
     * @return true or false
     */
    public boolean isExpirationValid () {
        return expire.validateDate();
    }

    /**
     * checks if the member age is above 18
     * @return true or false
     */
    public boolean isAbove18 () {
        Date today = new Date();
        if (today.getYear() - dob.getYear() < 18) {
            return false;
        }
        if (today.getYear() - dob.getYear() == 18) {
            if (today.getMonth() < dob.getMonth()) {
                return false;
            } else if (today.getMonth() == dob.getMonth()) {
                if (today.getDay() < dob.getDay()) {
                    return false;
                }
            }
        }
            return true;
    }

    /**
     * checks if the member's date of birth is valid
     * @return true or false
     */
    public boolean isDOBPast () {
        Date today = new Date();
        if (dob.getYear() > today.getYear()) {
            return false;
        }
        if (dob.getYear() == today.getYear()) {
            if (dob.getMonth() > today.getMonth()) {
                return false;
            } else if (dob.getMonth() == today.getMonth()) {
                if (dob.getDay() >= today.getDay()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checks if the member's location is a valid location
     * @return true or false
     */
    public boolean isLocationValid() {
        if (location == null) {
            return false;
        }
        return true;
    }

    /**
     * test bed for compareTo() method
     * @param args - input
     */
    public static void main(String[] args){
        //Testing the compareTo() method
        System.out.println("Test case 1");
        Member member = new Member("John", "Doe", new Date("1/20/1990"), new Date("1/17/2023"), Location.BRIDGEWATER);
        Member testmember = new Member("John", "Doe", new Date("1/20/1990"), new Date("1/17/2023"), Location.BRIDGEWATER);
        if(member.compareTo(testmember) == 0){
            System.out.println("Member exists");
        if(member.compareTo(testmember) != 0){
            System.out.println("Member does not exist");
            }
        }
    }

}