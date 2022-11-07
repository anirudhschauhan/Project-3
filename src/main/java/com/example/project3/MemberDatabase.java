package com.example.project3;

import java.text.DecimalFormat;

/**
 * Array database to put members in.
 * Includes functions to find, add, remove members
 * Includes print functions to print the database in different ways
 * Sorting algorythm included (quicksort)
 * @author Anirudh Chauhan, Matthew Calora
 */

public class MemberDatabase {
    private Member[] mlist;
    private int size;

    private final int INTIAL_CAPACITY = 4;
    private final int NOT_FOUND = -1;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * creates initial memberdatabase with initial size of 4
     */
    public MemberDatabase() {
        this.mlist = new Member[INTIAL_CAPACITY];
        this.size = 0;
    }

    /**
     * finds the member in the database and returns
     * the index where they are located
     * @param member - member
     * @return index
     */
    private int find(Member member) {
        for (int index = 0; index < size; index++) {
            if (member.equals(mlist[index])) {
                return index;
            }
        }
        return NOT_FOUND;
    }

    /**
     * grows array if the array has reached its maximum size
     */
    private void grow() {
        Member[] oldlist = this.mlist;
        int increaselength = oldlist.length + 4;
        Member[] newlist = new Member[increaselength];

        for (int index = 0; index < oldlist.length; index++) {
            newlist[index] = oldlist[index];
        }
        this.mlist = newlist;
    }

    /**
     * getter to retrieve the size of the array
     * @return array size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * adds member to memberdatabase
     * @param member to be added
     */
    public boolean add(Member member) {
        if (size == mlist.length) {
            grow();
        }
        //if (find(member) != NOT_FOUND){
           // return false;
       // }
        for (Member value : mlist) {
            if (member.equals(value)) {
                return false;
            }
        }
        mlist[size] = (member);
        size++;
        return true;
    }

    /**
     * removes member from database and decreases
     * memberdatabase size by one
     * @param member
     */
    public boolean remove(Member member) {
        int result = find(member);
        Member[] currentmember = this.mlist;

        if (result != NOT_FOUND) {
            for (int k = result; k < size - 1; k++) {
                mlist[k] = mlist[k + 1];
            }
            size--;
            return true;
        }
        return false;
    }

    /**
     * prints out array as is
     */
    public String print() {
        if (size == 0) {
            return ("Member Database is empty!");

        }
        String str = "";
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                str += mlist[i].toString()+"\n";
            }
        }
        return(str);
    }

    /**
     * prints out database by County
     */
    public String printByCounty() {
        if (size == 0) {
            return("Member Database is empty!");

        }

        quickSortLocation(0, size - 1);
        String str = "";
        str+= "-list of members sorted by county and zipcode-\n";
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                str+=mlist[i].toString()+"\n";
            }
        }
        str+= "-end of list-\n";
        return str;

    } //sort by county and then zipcode

    /**
     * prints out database by expiration data
     */
    public String printByExpirationDate() {
        if (size == 0) {
            return("Member Database is empty!\n");

        }
        quickSortExpire(0, size - 1);
        String str="";
        str+="-list of members sorted by membership expiration date-\n";
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                str+=mlist[i].toString()+"\n";
            }
        }
        str+="-end of list-\n";
        return str;
    }

    /**
     * prints out database by member name
     * sort by last name and then first name
     */
    public String printByName() {
        if (size == 0) {
            return("Member Database is empty!\n");

        }
        quickSortName(0, size - 1);
        String str="";
        str+="-list of members sorted by last name, and first name-\n";
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                str+=mlist[i].toString()+"\n";
            }
        }
        str+="-end of list-\n";
            return str;
    }

    /**
     * prints all membership fees
     */
    public String printMemFees(){
        if (size == 0) {
            return("Member Database is empty!\n");

        }
        String str="";
        str+="-list of members with membership fees-\n";
        for (int i = 0; i < size; i++) {
            if (mlist[i] != null) {
                str+=mlist[i].toString()+"\n";
            }

            str+= ", Membership fee: $" + df.format(mlist[i].membershipFee())+"\n";

        }
        str+="-end of list-\n";
        return str;
    }

    /**
     * quicksort algorithm
     */
    private void quickSortName(int low, int high) {
        if (low < high) {
            int i = partitionName(low, high);
            quickSortName(low, i - 1);
            quickSortName(i + 1, high);
        }
    }

    private int partitionName(int low, int high) {
        Member point = mlist[high];
        int lpointer = (low - 1); // index of smaller element
        for (int i = low; i < high; i++) {
            // If current element is smaller than or
            // equal to pivot
            if (mlist[i].compareTo(point) <= 0) {  //if (mlist[i].compareLocation(point)<=0){
                lpointer++;                      // if mlist[i].getExpire().compareTo(point.getExpire())<=0
                // swap arr[lpointer] and arr[i]
                Member temp = mlist[lpointer];
                mlist[lpointer] = mlist[i];
                mlist[i] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Member temp = mlist[lpointer + 1];
        mlist[lpointer + 1] = mlist[high];
        mlist[high] = temp;

        return lpointer + 1;
    }

    private void quickSortExpire(int low, int high) {
        if (low < high) {
            int i = partitionExpire(low, high);
            quickSortExpire(low, i - 1);
            quickSortExpire(i + 1, high);
        }
    }

    private int partitionExpire(int low, int high) {
        Member point = mlist[high];
        int lpointer = (low - 1); // index of smaller element
        for (int i = low; i < high; i++) {
            // If current element is smaller than or
            // equal to pivot
            if (mlist[i].getExpire().compareTo(point.getExpire()) <= 0) {  //if (mlist[i].compareLocation(point)<=0){
                lpointer++;                      // if mlist[i].getExpire().compareTo(point.getExpire())<=0
                // swap arr[lpointer] and arr[i]
                Member temp = mlist[lpointer];
                mlist[lpointer] = mlist[i];
                mlist[i] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Member temp = mlist[lpointer + 1];
        mlist[lpointer + 1] = mlist[high];
        mlist[high] = temp;

        return lpointer + 1;
    }

    private void quickSortLocation(int low, int high) {
        if (low < high) {
            int i = partitionLocation(low, high);
            quickSortLocation(low, i - 1);
            quickSortLocation(i + 1, high);
        }
    }

    private int partitionLocation(int low, int high) {
        Member point = mlist[high];
        int lpointer = (low - 1); // index of smaller element
        for (int i = low; i < high; i++) {
            // If current element is smaller than or
            // equal to pivot
            if (mlist[i].compareLocation(point) <= 0) {  //if (mlist[i].compareLocation(point)<=0){
                lpointer++;                      // if mlist[i].getExpire().compareTo(point.getExpire())<=0
                // swap arr[lpointer] and arr[i]
                Member temp = mlist[lpointer];
                mlist[lpointer] = mlist[i];
                mlist[i] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        Member temp = mlist[lpointer + 1];
        mlist[lpointer + 1] = mlist[high];
        mlist[high] = temp;

        return lpointer + 1;
    }
    public boolean isEmpty() {
        if (size != 0) {
            return false;
        }
        return true;
    }

    /**
     * returns the member's location
     * in the array
     * @param member - member
     * @return index
     */
    public Member getMember(Member member) {
        for (int index = 0; index < size; index++) {
            if (member.equals(mlist[index])) {
                return mlist[index];
            }
        }
    return null;
    }

}
