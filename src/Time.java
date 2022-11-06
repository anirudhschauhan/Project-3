import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * class that stores the time
 * @author Anirudh Chauhan, Matthew Calora
 */

public enum Time {
        MORNING(9, 30),
        AFTERNOON(14, 0),
        EVENING(18, 30);

        private final int hour;
        private final int minute;

        /**
         * Creates a Time object.
         * @param hour of the specified time.
         * @param minute of the specified time.
         */
        Time (int hour, int minute) {
            this.hour = hour;
            this.minute = minute;
        }

        /**
         * Converts the Time object into a string.
         * Digital time format; if the minute of the time is 0, it will be formatted as 00 instead of 0.
         * @return String formatted as hour:minute.
         */
        public String toString() {
            if(minute == 0) {
                return hour + ":" + minute + "0";
            }
            return hour + ":" + minute;
        }
    }
