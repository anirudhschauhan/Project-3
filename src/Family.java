/**
 * Class that stores information about a member
 * with a family membership
 * @author Anirudh Chauhan, Matthew Calora
 */
public class Family extends Member{
        private final int  NUM_GUEST_PASS = 1;
        private int guestPass;
        private final double ONE_TIME_FEE =29.99;
        private final int QUARTERLY = 3;
        private final double MEMBERSHIP_FEE = 59.99;

    /**
     * default family mmeber constructor
     */
        public Family() {
            super();
            guestPass = NUM_GUEST_PASS;
        }

    /**
     * family member constructor when
     * information is given
     * @param fname
     * @param lname
     * @param dob
     * @param expire
     * @param location
     * @param numGuestPass
     */
        public Family(String fname, String lname, Date dob, Date expire, Location location, int numGuestPass) {
            super(fname, lname, dob, expire, location);
            this.guestPass = numGuestPass;
        }

    /**
     * sets number of guest passes
     * @param numGuestPass
     */
        public void setNumGuestPass(int numGuestPass) {
            this.guestPass = numGuestPass;
        }

    /**
     * subtracts number of guest passes from a member
     * cannot be lower than 0
     * @return boolean
     */
        public boolean useGuestPass() {
            if (this.getNumGuestPass() != 0) {
                this.setNumGuestPass(this.getNumGuestPass() - 1); //guest passes are always used 1 at a time
                return true;
            }
            return false;
        }

    /**
     * adds guest pass to member
     */
        public void addGuestPass() {
            this.guestPass++;
        }

    /**
     * gets the number of guest passes
     * a member has left
     * @return int
     */
        public int getNumGuestPass() {
            return this.guestPass;
        }

    /**
     * calculates and returns the membership
     * fee for a family membersdhip
     * @return double
     */
        @Override
        public double membershipFee() {
            return ONE_TIME_FEE + (QUARTERLY* MEMBERSHIP_FEE);
        }

    /**
     * returns the number of guest passes
     * a family member has left
     * @return string
     */
        @Override
        public String toString() {
            String out = super.toString();
            if (!(this instanceof Premium)) {
                out = out + ", (Family) guest-pass remaining: " + getNumGuestPass();
            }
            return out;
        }

    }
