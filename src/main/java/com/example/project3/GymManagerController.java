package com.example.project3;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * This class allows the user to store and load information
 * about a member through the javafx GUI
 * @author Anirudh Schauhan, Matthew Calora
 */
public class GymManagerController {

    private MemberDatabase memData;
    private Schedule schedule;
    public GymManagerController() {
        memData = new MemberDatabase();
        schedule = new Schedule();
    }


    @FXML
    private TextField fname;
    @FXML
    private TextField classFname;
    @FXML
    private TextField lname;
    @FXML
    private TextField classLname;
    @FXML
    private DatePicker DOB;
    @FXML
    private DatePicker classDOB;
    @FXML
    private TextField gymLocation;
    @FXML
    private TextField classLocation;
    @FXML
    private TextField classInstructor;
    @FXML
    private RadioButton pilat, spinn, card;
    @FXML
    private RadioButton standardMem, familyMem, premiumMem;
    @FXML
    private RadioButton scheduletxt, membertxt;
    @FXML
    private RadioButton guestCheck;
    @FXML
    private TextArea outputTextArea;


    /**
     * This method adds a member to the database.
     * Output to database depends on the member's
     * membership type.
     * @author Anirudh Schauhan, Matthew Calora
     */
    public void addMember(){
        if(fname.getText().equals("") || lname.getText().equals("")){
            outputTextArea.appendText("Please enter your name.\n");
            return;
        }
        if(DOB.getValue().equals("")){
            outputTextArea.appendText("Please enter your date of birth.\n");
            return;
        }
        if(gymLocation.getText().equals("")){
            outputTextArea.appendText("Please enter your location.\n");
            return;
        }
        if (standardMem.isSelected()) {
            addStandMem();
        }
        if(familyMem.isSelected()){
            addFam();
        }
        if(premiumMem.isSelected()){
            addPre();
        }


    }
    /**
     * This method removes a member from the database
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void removeMember(){
        removeMem();
    }

    /**
     * This method adds a member with a Standard
     * membership to the database.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void addStandMem() {
        Member mem = new Member();

        mem.setFname(fname.getText());
        mem.setLname(lname.getText());
        Date dob = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);
        Date expire = new Date();
        expire.getMonthsLater(expire);
        mem.setExpire(expire);

        String locate = String.valueOf(gymLocation.getText());
        for (Location locatet : Location.values()) {
            if (locate.toUpperCase().equals(locatet.name())) {
                mem.setLocation(locatet);
            }
        }
        if (validation(mem, locate)) {
            if (!memData.add(mem)) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " is already in the database.\n");
            }
            else {
                outputTextArea.appendText(mem.getFname() + " "+ mem.getLname() + ", DOB: " + mem.getDOB() + ", Membership expires " + mem.getExpire() + ", Location: " + mem.getLocation()+"\n");
            }
        }
    }

    /**
     * This method adds a member with a Family
     * membership to the database.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void addFam() {
        Family fam = new Family();

        fam.setFname(fname.getText());
        fam.setLname(lname.getText());
        Date dob = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        fam.setDOB(dob);
        Date expire = new Date();
        expire.getMonthsLater(expire);
        fam.setExpire(expire);

        String locate = String.valueOf(gymLocation.getText());
        for (Location locatet : Location.values()) {
            if (locate.toUpperCase().equals(locatet.name())) {
                fam.setLocation(locatet);
            }
        }
        if (validation(fam, locate)) {
            if (!memData.add(fam)) {
                outputTextArea.appendText(fam.getFname() + " " + fam.getLname() + " is already in the database.\n");
            }
            else {
                outputTextArea.appendText(fam.getFname() + " " + fam.getLname() + " added.\n");
            }
        }
    }

    /**
     * This method adds a member with a Premium
     * membership to the database.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void addPre() {
        Premium pre = new Premium();

        pre.setFname(fname.getText());
        pre.setLname(lname.getText());
        Date dob = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        pre.setDOB(dob);
        Date expire = new Date();
        expire.getMonthsLater(expire);
        pre.setExpire(expire);

        String locate = String.valueOf(gymLocation.getText());
        for (Location locatet : Location.values()) {
            if (locate.toUpperCase().equals(locatet.name())) {
                pre.setLocation(locatet);
            }
        }
        if (validation(pre, locate)) {
            if (!memData.add(pre)) {
                outputTextArea.appendText(pre.getFname() + " " + pre.getLname() + " is already in the database.\n");
            }
            else {
                outputTextArea.appendText(pre.getFname() + " " + pre.getLname() + " added.\n");
            }
        }
    }

    /**
     * This method removes a member from the
     * database.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void removeMem() {
        Member mem = new Member();

        mem.setFname(fname.getText());
        mem.setLname(lname.getText());
        Date dob = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);
        if (!memData.remove(mem)) {
            outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " is not in the database.\n");
        }
        else {
            outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " removed.\n");
        }
    }
    private boolean validation(Member mem, String location) {
        if (!mem.isDOBValid()) {
            outputTextArea.appendText("DOB " + mem.getDOB().toString() + ": invalid calendar date!\n");
            return false;
        }

        if (!mem.isExpirationValid()) {
            outputTextArea.appendText("Expiration date " + mem.getExpire().toString() + ": invalid calendar date!\n");
            return false;
        }

        if (!mem.isDOBPast()) {
            outputTextArea.appendText("DOB " + mem.getDOB().toString() + ": cannot be today or a future date!\n");
            return false;
        }

        if(!mem.isAbove18()) {
            outputTextArea.appendText("DOB " + mem.getDOB().toString() + ": must be 18 or older to join!\n");
            return false;
        }

        if(!mem.isLocationValid()) {
            outputTextArea.appendText(location + ": invalid location!\n");
            return false;
        }

        return true;
    }

    /**
     * This method prints the database
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void printMem() {
        if (memData.getSize() == 0) {
            outputTextArea.appendText("Member database is empty!\n");
            return;
        }
        outputTextArea.appendText("-list of members-\n");
        outputTextArea.appendText(memData.print());
        outputTextArea.appendText("-end of list-\n");
    }

    /**
     * This method prints the database by County
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void printCounty(){
        outputTextArea.appendText(memData.printByCounty());
    }

    /**
     * This method prints the database by Name
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void printName(){
        outputTextArea.appendText(memData.printByName());
    }

    /**
     * This method prints the database by Expiration Date
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void printExp(){
        outputTextArea.appendText(memData.printByExpirationDate());
    }

    /**
     * This method prints the membership Fees
     * of the members in the database
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void printFees(){
        outputTextArea.appendText(memData.printMemFees());
    }

    /**
     * This method adds a pre-made classSchedule.txt file
     * to the database and prints it out on the GUI
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    protected void addFile(){
        if(scheduletxt.isSelected()){
            try {
                File file = new File("classSchedule.txt");
                Scanner scanner = new Scanner(file);
                String line = "";
                outputTextArea.appendText("-Fitness classes loaded-\n");
                while(scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    String[] words = line.split(" ");
                    int count = 0;
                    FitnessClass fitness = new FitnessClass();
                    String className = words[count++];
                    Classes fclass = findClass(className);
                    fitness.setClass(fclass);
                    String instructorName = words[count++];
                    Instructor instructor = findInstructor(instructorName);
                    fitness.setInstructor(instructor);
                    String timeStr = words[count++];
                    Time time = findTime(timeStr);
                    fitness.setTime(time);
                    String locationName = words[count];
                    Location location = findLocation(locationName);
                    fitness.setLocation(location);
                    schedule.addClass(fitness);
                   outputTextArea.appendText(fitness.toString()+"\n");
                }
                outputTextArea.appendText("-end of class list-\n");
                scanner.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else if(membertxt.isSelected()){
            try {
                File file = new File("memberList.txt");
                Scanner sc = new Scanner(file);
                String line = "";

                outputTextArea.appendText("-list of members loaded-\n");

                while(sc.hasNextLine()) {
                    line = sc.nextLine();
                    String[] stuff = line.split("\\s+");
                    addFileMem(stuff);
                }

                sc.close();
                outputTextArea.appendText("-end of list-\n");
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * This method adds a pre-made memberList.txt file
     * to the database and prints it out on the GUI
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void addFileMem(String[] info) {
        Member mem = new Member();
        int c = 0;
        mem.setFname(info[c++]);
        mem.setLname(info[c++]);
        Date dob = new Date(info[c++]);
        mem.setDOB(dob);
        Date expire = new Date(info[c++]);
        mem.setExpire(expire);
        String location = info[c];
        for (Location locate : Location.values()) {
            if (location.toUpperCase().equals(locate.name())) {
                mem.setLocation(locate);
            }
        }
        if (validation(mem, location)) {
            if (!memData.add(mem)) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " is already in the database.\n");
            } else {
                outputTextArea.appendText(mem.toString()+"\n");
            }
        }
    }

    /**
     * This method finds a class and returns it
     * @author Anirudh Schauhan, Matthew Calora
     */
    private Classes findClass(String className) {
        Classes fitness = null;
        for(Classes classes : Classes.values()) {
            if(className.toUpperCase().equals(classes.name())) {
                fitness = classes;
            }
        }
        return fitness;
    }

    /**
     * This method finds an instructor and returns it
     * @author Anirudh Schauhan, Matthew Calora
     */
    private Instructor findInstructor(String instructorName) {
        Instructor instructor = null;
        for(Instructor i : Instructor.values()) {
            if(instructorName.toUpperCase().equals(i.name())) {
                instructor = i;
            }
        }
        return instructor;
    }

    private Location findLocation(String locationName) {
        Location location = null;
        for(Location l : Location.values()) {
            if(locationName.toUpperCase().equals(l.name())) {
                location = l;
            }
        }
        return location;
    }

    /**
     * This method finds a time and returns it
     * @author Anirudh Schauhan, Matthew Calora
     */

    private Time findTime(String timeStr) {
        Time time = null;
        for(Time t : Time.values()) {
            if(timeStr.toUpperCase().equals(t.name())) {
                time = t;
            }
        }
        return time;
    }

    /**
     * This method adds an individual to a fitness class.
     * Checks in a member or guest if selected.
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    private void checkInPerson(){
        if(guestCheck.isSelected()){
            checkInGuest();
        }
        else{
            checkInMem();
        }

    }

    /**
     * This method drops a member or guest from
     * their fitness class
     * @author Anirudh Schauhan, Matthew Calora
     */
    @FXML
    private void dropPerson(){
        if(guestCheck.isSelected()){
            dropClassGuest();
        }
        else{
            dropClass();
        }

    }

    /**
     * This method adds a member to a specific fitness class
     * depending on selection given
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void checkInMem() {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        String className="";

        if(pilat.isSelected()){
            className = "PILATES";
        }
        if(spinn.isSelected()){
            className = "SPINNING";
        }
        if(card.isSelected()){
            className = "CARDIO";
        }
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = classInstructor.getText();
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = String.valueOf(classLocation.getText());
        Location location = findLocation(locationName);

        fitness.setLocation(location);
        mem.setFname(classFname.getText());
        mem.setLname(classLname.getText());

        Date dob = new Date(classDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);

        if(validCheckIn(mem, className, instructorName, locationName, fitness)) {

            if (!schedule.getFitnessClass(fitness).checkIn(memData.getMember(mem))) {
               outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " already checked in.\n");
            }
            else {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " checked in " + schedule.getFitnessClass(fitness).toString()+"\n");
            }
        }
    }

    /**
     * This method adds a guest to a specific fitness class
     * depending on selection given
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void checkInGuest() {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        String className="";

        if(pilat.isSelected()){
            className = "PILATES";
        }
        if(spinn.isSelected()){
            className = "SPINNING";
        }
        if(card.isSelected()){
            className = "CARDIO";
        }
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = classInstructor.getText();
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = String.valueOf(classLocation.getText());
        Location location = findLocation(locationName);

        fitness.setLocation(location);
        mem.setFname(classFname.getText());
        mem.setLname(classLname.getText());
        Date dob = new Date(classDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);
        if(validGuestCheckIn(mem, className, instructorName, locationName, fitness)) {
            if (!schedule.getFitnessClass(fitness).checkInGuest((Family) memData.getMember(mem))) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " ran out of guest passes.\n");
            }
            else {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " (guest) checked in " +
                        schedule.getFitnessClass(fitness).toString()+"\n");
            }
        }
    }

    /**
     * This method drops a member from a specific
     * fitness class depending on selection
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void dropClass() {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        String className="";


        if(pilat.isSelected()){
            className = "PILATES";
        }
        if(spinn.isSelected()){
            className = "SPINNING";
        }
        if(card.isSelected()){
            className = "CARDIO";
        }
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = classInstructor.getText();
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = String.valueOf(classLocation.getText());
        Location location = findLocation(locationName);

        fitness.setLocation(location);
        mem.setFname(classFname.getText());
        mem.setLname(classLname.getText());
        Date dob = new Date(classDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);
        if(validConditions(mem, className, instructorName, locationName, fitness)) {
            if(!schedule.getFitnessClass(fitness).drop(mem)) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " did not check in.\n");
            }
            else {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " done with the class.\n");
            }
        }
    }

    /**
     * This method drops a guest from a specific
     * fitness class depending on selection
     * @author Anirudh Schauhan, Matthew Calora
     */
    private void dropClassGuest() {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        String className="";


        if(pilat.isSelected()){
            className = "PILATES";
        }
        if(spinn.isSelected()){
            className = "SPINNING";
        }
        if(card.isSelected()){
            className = "CARDIO";
        }
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = classInstructor.getText();
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = String.valueOf(classLocation.getText());
        Location location = findLocation(locationName);

        fitness.setLocation(location);
        mem.setFname(classFname.getText());
        mem.setLname(classLname.getText());
        Date dob = new Date(classDOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);
        if(validConditions(mem, className, instructorName, locationName, fitness)) {
            if (!schedule.getFitnessClass(fitness).dropGuest((Family) memData.getMember(mem))) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " has no guests checked in\n");
            }
            else {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " Guest done with the class.\n");
            }
        }

    }

    /**
     * This method checks a guest's membership rank.
     * Checks for membership rank restrictions, membership expiration,
     * and time conflicts.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private boolean validGuestCheckIn(Member mem, String className, String instructorName,
                                      String locationName, FitnessClass fitness) {
        Date today = new Date();
        if(!(memData.getMember(mem) instanceof Family)) {
            outputTextArea.appendText("Standard membership - guest check-in is not allowed.\n");
            return false;
        }
        if(!validConditions(mem, className, instructorName, locationName, fitness)) {
            return false;
        }
        if(memData.getMember(mem).getExpire().compareTo(today) < 0) {
            outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " " + mem.getDOB() + " membership expired.\n");
            return false;
        }
        if(!memData.getMember(mem).getLocation().equals(fitness.getLocation())) {
            outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " Guest checking in " +
                    fitness.getLocation().toString() + " - guest location restriction.\n");
            return false;
        }
        return true;
    }

    /**
     * This method checks a member's membership rank.
     * Checks for membership rank restrictions, membership expiration,
     * and time conflicts.
     * @author Anirudh Schauhan, Matthew Calora
     */

    private boolean validCheckIn(Member mem, String className, String instructorName,
                                 String locationName, FitnessClass fitness) {
        Date today = new Date();

        if(validConditions(mem, className, instructorName, locationName, fitness)) {

            if (!(memData.getMember(mem) instanceof Family)) {
                if(!memData.getMember(mem).getLocation().equals(fitness.getLocation())) {
                    outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " checking in " +
                            fitness.getLocation().toString() + " - standard membership location restriction.\n");
                    return false;
                }
            }
            if(memData.getMember(mem).getExpire().compareTo(today) < 0) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " " + mem.getDOB() + " membership expired.\n");
                return false;
            }
            FitnessClass classConflict= checkTimeConflict(mem, schedule.getFitnessClass(fitness));
            if(classConflict != null) {
                outputTextArea.appendText("Time conflict - " + classConflict.getFitClass().name() + " - " +
                        classConflict.getInstructor().name() + ", " + classConflict.getTime().toString() + ", " +
                        classConflict.getLocation().toString()+"\n");
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * This method runs checks on member information given.
     * Checks if DOB is a valid date, the member is in the database,
     * the fitness class exists, the instructor exists, the location exists,
     * and if the instructor exists at a given location.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private boolean validConditions(Member mem, String className, String instructorName,
                                    String locationName, FitnessClass fitness) {

        if(!mem.getDOB().isValid()) {
            outputTextArea.appendText("DOB " + mem.getDOB() + ": invalid calendar date!\n ");
            return false;

        }
        if(memData.getMember(mem) == null) {
            outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " " + mem.getDOB() + " is not in the database.\n");

            return false;
        }
        if(fitness.getFitClass() == null) {
            outputTextArea.appendText(className + " - class does not exist.\n");

            return false;
        }
        if(fitness.getInstructor() == null) {
            outputTextArea.appendText(instructorName + " - instructor does not exist.\n");

            return false;
        }
        if(fitness.getLocation() == null) {
            outputTextArea.appendText(locationName + " - invalid location.\n");

            return false;
        }
        if(schedule.getFitnessClass(fitness) == null) {
            Location falseLocation = locationCheck(fitness);

            if(falseLocation != null) {

                outputTextArea.appendText(fitness.getFitClass().getClassName() + " by " +
                        fitness.getInstructor().toString() + " does not exist at " + falseLocation.name()+"\n");
            }

            return false;
        }

        return true;
    }

    /**
     * This method checks if the location is a valid location
     * @author Anirudh Schauhan, Matthew Calora
     */
    private Location locationCheck(FitnessClass fclass) {
        for(int i = 0; i < schedule.getNumClasses(); i++) {
            FitnessClass fitness = schedule.getFitnessClass(i);
            for(int j = 0; j < fitness.getLength(); j++) {
                if(fclass.getFitClass().equals(fitness.getFitClass()) &&
                        fclass.getInstructor().equals(fitness.getInstructor()) &&
                        !fclass.getLocation().equals(fitness.getLocation())) {
                    return fclass.getLocation();
                }
            }
        }
        return null;
    }

    /**
     * This method checks if the member is registered in
     * multiple classes with the same start time.
     * @author Anirudh Schauhan, Matthew Calora
     */
    private FitnessClass checkTimeConflict(Member m, FitnessClass fclass) {
        Time time = fclass.getTime();
        for(int i = 0; i < schedule.getNumClasses(); i++) {
            FitnessClass fitness = schedule.getFitnessClass(i);
            for(int j = 0; j < fitness.getLength(); j++) {
                if(!fclass.equals(fitness) && time.equals(fitness.getTime()) &&
                        fitness.memberCheck(m)) {
                    return fitness;
                }
            }
        }

        return null;
    }

}
