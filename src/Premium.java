/**
 * Class that stores information about a member
 * with a premium membership
 * @author Anirudh Chauhan, Matthew Calora
 */
public class Premium extends Family {
    private final int  NUM_GUEST_PASS = 3;
    private int guestPass;
    private final int ANNUAL_MONTHS = 11;
    private final double MEMBERSHIP_FEE = 59.99;

    /**
     * default premium member constructor
     */
    public Premium() {
        super();
        guestPass = NUM_GUEST_PASS;
    }


    /**
     * constructor for the premium member
     * when information is given
     * @param fname
     * @param lname
     * @param dob
     * @param expire
     * @param location
     * @param numGuestPasses
     */
    public Premium(String fname, String lname, Date dob, Date expire, Location location, int numGuestPasses) {
        super(fname, lname, dob, expire, location, numGuestPasses);
    }

    /**
     * class that returns the number of guest
     * passes according to the member
     * @return number of guest passes
     */
    @Override
    public int getNumGuestPass() {
        return this.guestPass;
    }

    /**
     * sets the number of guest passes
     * to the member
     * @param numGuestPass
     */
    public void setNumGuestPass(int numGuestPass) {
        this.guestPass = numGuestPass;
    }

    /**
     * class the returns the cost of
     * the member's membership fee
     * @return membershipfee
     */
    @Override
    public double membershipFee() {
        return (ANNUAL_MONTHS * MEMBERSHIP_FEE);//1 free month
    }

    /**
     * returns the number of guest passes
     * the premium member has in the form
     * of a string
     * @return out
     */
    @Override
    public String toString() {
        String out = super.toString();
        out = out + ", (Premium) guest-pass remaining: " + getNumGuestPass();
        return out;
    }
}