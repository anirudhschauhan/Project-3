package com.example.project3;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * A class that stores the date based of a string input
 * The string input is broken down in month, day, and year
 * and saved to their respective variables
 * @author Anirudh Chauhan, Matthew Calora
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;
    public static final int FEBURARY_LEAP_YEAR = 29;
    public static final int FEBRUARY_NONLEAP_YEAR = 28;
    public static final int END_OF_MONTH = 31;
    private final int QUARTERLY = 3;
    private final int ANNUALLY = 1;

    private final int MONTHS_IN_YEAR = 12;


    /**
     * creates default date object that represents the current date
     */
    public Date() {
        Calendar currentDay = Calendar.getInstance();
        this.month = currentDay.get(Calendar.MONTH) + 1;
        this.day = currentDay.get(Calendar.DAY_OF_MONTH);
        this.year = currentDay.get(Calendar.YEAR);
    } //create an object with today’s date (see Calendar class)

    /**
     * creates date object from string input
     * @param date - a string in the form of mm/dd/yyyy
     */
    public Date(String date) {
        StringTokenizer string = new StringTokenizer(date, "/");
        this.month = Integer.parseInt(string.nextToken());
        this.day = Integer.parseInt(string.nextToken());
        this.year = Integer.parseInt(string.nextToken());

    }

    /**
     * compares dates
     * @param date the object to be compared.
     * @return int
     */
    @Override
    public int compareTo(Date date) {
        if (this.year > date.getYear()){
            return 1;
        }
        if (this.year < date.getYear()){
            return -1;
        }
        if(this.month > date.getMonth()){
            return 1;
        }
        if(this.month < date.getMonth()){
            return -1;
        }
        if(this.day > date.getDay()){
            return 1;
        }
        if (this.day < date.getDay()){
            return -1;
        }
        return 0;
    }

    /**
     * prints out input date to string
     * @return string
     */
    public String toString(){
        return "" + month + "/" + day + "/" + year + "";
    }

    /**
     * checks if date is a valid date
     * @return boolean
     */
    public boolean isValid() {
        boolean beforeCurrentDay = !this.afterCurrentDay();
        boolean validateDay = this.validateDate();

        return beforeCurrentDay && validateDay;
    } //check if a date is a valid calendar date

    /**
     * gets year
     * @return int
     */
    public int getYear(){
        return this.year;
    }

    /**
     * gets month
     * @return int
     */
    public int getMonth(){
        return this.month;
    }

    /**
     * gets month
     * @return int
     */
    public int getDay(){
        return this.day;
    }

    /**
     * checks if the input date is beyond the current date
     * In other words, checks if the date is in the future
     * @return boolean
     */
    private boolean afterCurrentDay(){
        Date currentDay = new Date();

        if(this.year > currentDay.year){
            return true;
        }
        else {
            if(this.year == currentDay.year){
                if(this.month > currentDay.month){
                    return true;
                }
                else {
                    if(this.month == currentDay.month){
                        return this.day > currentDay.day;
                    }
                    else {
                        return false;
                    }
                }
            }
            else {
                return false;
            }
        }
    }

    /**
     * checks if the date is a valid date by checking
     * if its month and day are within a certain range
     * @return boolean
     */
    public boolean validateDate() {
        int month = this.month;
        int day = this.day;
        boolean day31 = false;

        if(day > END_OF_MONTH || day < 1) {
            return false;
        }
        switch(month) {
            case Calendar.JANUARY + 1:
            case Calendar.MARCH + 1:
            case Calendar.MAY+ 1:
            case Calendar.OCTOBER + 1:
            case Calendar.JULY + 1:
            case Calendar.AUGUST+ 1:
            case Calendar.DECEMBER + 1:
                day31 = true;
                break;
            case Calendar.FEBRUARY + 1:
                if(this.LeapYear()== false && this.day >= FEBRUARY_NONLEAP_YEAR) {
                    return false;
                }
                if(this.LeapYear() == true && this.day == FEBURARY_LEAP_YEAR){
                    return true;
                }
                return (this.day < FEBURARY_LEAP_YEAR);
            case Calendar.APRIL + 1:
            case Calendar.JUNE + 1:
            case Calendar.SEPTEMBER + 1:
            case Calendar.NOVEMBER + 1:
                break;
            default:
                return false;
        }
        return day31 || this.day != END_OF_MONTH;
    }

    /**
     * checks if the input year is a leap year
     * @return boolean
     */
    public void getMonthsLater(Date expire){
        this.month = expire.getMonth();
        this.year = expire.getYear();
        this.month += QUARTERLY;
        if(month > MONTHS_IN_YEAR) {
            this.month = expire.getMonth() - MONTHS_IN_YEAR;
            this.year += ANNUALLY;
        }
    }

    /**
     * calcualtes and gets the expiration
     * date a year after it is
     * input into the database
     * @param expire
     */
    public void getYearLater(Date expire){
        this.year = expire.getYear();
        this.year += ANNUALLY;
    }

    /**
     * checks if the year is a leap year
     * @return boolean
     */
    private boolean LeapYear() {
        if(this.year % QUADRENNIAL == 0) {
            if(this.year % CENTENNIAL == 0) {
                if (this.year % QUATERCENTENNIAL == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * testbed main() to test cases are valid or invalid
     * @param args - date
     */
    public static void main(String[] args){
        //Testing the isValid() method
        System.out.println("Test case 1a");
        Date test1a = new Date("12/3/2025");
        if(test1a.isValid()){
            System.out.println("test case 1a is valid");
        }
        else{
            System.out.println("test case 1a is invalid");
        }

        System.out.println("Test case 1b");
        Date test1b = new Date("12/3/1990");
        if(test1b.isValid()){
            System.out.println("test case 1b is valid");
        }
        else{
            System.out.println("test case 1b is invalid");
        }

        System.out.println("Test case 2a");
        Date test2a = new Date("2/29/2018");
        if(test2a.isValid()){
            System.out.println("test case 2a is valid");
        }
        else{
            System.out.println("test case 2a is invalid");
        }

        System.out.println("Test case 2b");
        Date test2b = new Date("2/15/2018");
        if(test2b.isValid()){
            System.out.println("test case 2b is valid");
        }
        else{
            System.out.println("test case 2b is invalid");
        }

        System.out.println("Test case 3a");
        Date test3a = new Date("13/17/2021");
        if(test3a.isValid()){
            System.out.println("test case 3a is valid");
        }
        else{
            System.out.println("test case 3a is invalid");
        }

        System.out.println("Test case 3b");
        Date test3b = new Date("1/17/2021");
        if(test3b.isValid()){
            System.out.println("test case 3b is valid");
        }
        else{
            System.out.println("test case 3b is invalid");
        }

        System.out.println("Test case 4a");
        Date test4a = new Date("1/45/2020");
        if(test4a.isValid()){
            System.out.println("test case 4a is valid");
        }
        else{
            System.out.println("test case 4a is invalid");
        }

        System.out.println("Test case 4b");
        Date test4b = new Date("1/4/2020");
        if(test4b.isValid()){
            System.out.println("test case 4b is valid");
        }
        else{
            System.out.println("test case 4b is invalid");
        }
    }
}