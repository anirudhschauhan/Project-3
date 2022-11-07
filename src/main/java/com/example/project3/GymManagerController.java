package com.example.project3;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class GymManagerController {

    private MemberDatabase memData;
    private Schedule schedule;
    public GymManagerController() {
        memData = new MemberDatabase();
        schedule = new Schedule();
    }

    @FXML
    private Label welcomeText;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private DatePicker DOB;
    @FXML
    private TextField gymLocation;
    @FXML
    private Label StdMem;
    @FXML
    private RadioButton standardMem, familyMem, premiumMem;
    @FXML
    private Label FamMem;
    @FXML
    private Label PreMem;
    @FXML
    private Label added;
    @FXML
    private Label removed;
    @FXML
    private Button quit;
    @FXML
    private TextArea outputTextArea;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
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
    @FXML
    protected void removeMember(){
        removeMem();
    }
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
            } else {
                outputTextArea.appendText(fam.getFname() + " " + fam.getLname() + " added.\n");
            }
        }
    }
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
            } else {
                outputTextArea.appendText(pre.getFname() + " " + pre.getLname() + " added.\n");
            }
        }
    }
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

}
/*
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.io.File;
/**
 First, a single, very descriptive sentence describing the class.
 Then, a couple more sentences of description to elaborate.
 @author Anirudh Chauhan, Matthew Calora
 */
/*
public class GymManager {
    private MemberDatabase memData;

    private Schedule schedule;


    public GymManager() {
        memData = new MemberDatabase();
        schedule = new Schedule();
    }

    public void run() {
        Scanner scan = new Scanner(System.in);
        String pass = "";

        boolean running = true;
        System.out.println("Gym Manager is running...");
        while (running) {
            pass = scan.nextLine();
            String[] lines = pass.split(" ");
            running = command(lines);
        }
        scan.close();
        System.out.println();
    }


    private boolean command(String[] lines) {
        switch (lines[0]) {
            case "A"://done
                addStandMem(lines);
                break;
            case "R"://done
                removeMem(lines);
                break;
            case "P"://done
                printMem();
                break;
            case "PC":
                memData.printByCounty();
                //display the list of members in the database ordered by the county names and then the zip codes;
                //that is, if the locations are in the same county, ordered by the zip codes.
                break;
            case "PN":
                memData.printByName();
                //display the list of members in the database ordered by the members’ last names and then first names;
                //that is, if two members have the same last name, ordered by the first name.
                break;
            case "PD":
                memData.printByExpirationDate();
                //display the list of members in the database ordered by the expiration dates. If two expiration dates
                //are the same, their order doesn't matter.
                break;
            case "S":
                //display the fitness class schedule. A fitness class shall include the fitness class name, instructor’s
                //name, the time of the class, and the list of members who have already checked in today. For simplicity, assuming
                //the schedule is for “today” only, you do not need to handle a multiple-day schedule.
                schedule.printSchedule();
                break;
            case "C":
                //for members check-in, for example, the command line to check in Pilates.
                //C Pilates Mary Lindsey 12/1/1989
                checkInMem(lines);
                break;
            case "D":
                dropClass(lines);
                break;
            //to drop the fitness classes after the member checked in to a class, for example,
            //D Pilates Mary Lindsey 12/1/1989
            case "LS":
                //load the fitness class schedule from the file classSchedule.txt to the class schedule to the software system.
                try {
                    File file = new File("classSchedule.txt");
                    Scanner scanner = new Scanner(file);
                    String line = "";
                    System.out.println("-Fitness classes loaded-");
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
                        System.out.println(fitness.toString());
                    }
                    System.out.println("-end of class list-");
                    scanner.close();
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "LM":
                //There is a historical member information needed to be imported to the database.
                //This is a new command to lead a list of members from the file memberList.txt to the member database.
                try {
                    File file = new File("memberList.txt");
                    Scanner sc = new Scanner(file);
                    String line = "";

                    System.out.println("-list of members loaded-");

                    while(sc.hasNextLine()) {
                        line = sc.nextLine();
                        String[] stuff = line.split("\\s+");
                        addFileMem(stuff);
                    }

                    sc.close();
                    System.out.println("-end of list-");
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case "AF":
                addFam(lines);
                break;
            //to add a member with the family membership to the member database. The expiration date should
            //set to expire 3 months later.
            case "AP":
                addPre(lines);
                //to add a member with the premium membership to the member database. The expiration date should
                //set to expire one year later.
                break;
            case "PF":
                memData.printMemFees();
                //a new command to print the list of members with the membership fees. Let’s assume the
                //membership fees are for the next billing statement, regardless of the current membership expiration dates.
                break;
            case "CG":
                //family guest check-in for a fitness class; must keep track of the remaining number of guest passes.
                checkInGuest(lines);
                break;
            case "DG":
                //a guest is done with a fitness class, and checking out; must keep track of the remaining number of
                //guest passes.
                dropClassGuest(lines);
                break;
            case "Q":
                System.out.println("Gym Manager terminated.");
                return false;
            case "":
                break;
            default:
                System.out.println(lines[0] + " is an invalid command!");
                break;
        }
        return true;
    }

    private void addMem(String[] info) {
        Member mem = new Member();
        int c = 1;
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
                System.out.println(mem.getFname() + " " + mem.getLname() + " is already in the database.");
            } else {
                System.out.println();
            }
        }
    }
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
                System.out.println(mem.getFname() + " " + mem.getLname() + " is already in the database.");
            } else {
                System.out.println(mem.toString());
            }
        }
    }

    private void addStandMem(String[] info) {
        Member mem = new Member();
        int c = 1;
        mem.setFname(info[c++]);
        mem.setLname(info[c++]);
        Date dob = new Date(info[c++]);
        mem.setDOB(dob);
        Date expire = new Date();

        expire.getMonthsLater(expire);
        mem.setExpire(expire);

        String location = info[c];
        for (Location locate : Location.values()) {
            if (location.toUpperCase().equals(locate.name())) {
                mem.setLocation(locate);
            }
        }
        if (validation(mem, location)) {
            if (!memData.add(mem)) {
                System.out.println(mem.getFname() + " " + mem.getLname() + " is already in the database.");
            }
            else {
                System.out.println(mem.getFname() + " "+ mem.getLname() + ", DOB: " + mem.getDOB() + ", Membership expires " + mem.getExpire() + ", Location: " + mem.getLocation());
            }
        }
    }
    // edit these to fit with Family and Premium each
    private void addFam(String[] info) {
        Family fam = new Family();
        int c = 1;
        fam.setFname(info[c++]);
        fam.setLname(info[c++]);
        Date dob = new Date(info[c++]);
        fam.setDOB(dob);
        Date expire = new Date();

        expire.getMonthsLater(expire);
        fam.setExpire(expire);

        String location = info[c];
        for (Location locate : Location.values()) {
            if (location.toUpperCase().equals(locate.name())) {
                fam.setLocation(locate);
            }
        }
        if (validation(fam, location)) {
            if (!memData.add(fam)) {
                System.out.println(fam.getFname() + " " + fam.getLname() + " is already in the database.");
            } else {
                System.out.println(fam.getFname() + " " + fam.getLname() + " added.");
            }
        }
    }
    private void addPre(String[] info) {
        Premium pre = new Premium();
        int c = 1;

        pre.setFname(info[c++]);
        pre.setLname(info[c++]);
        Date dob = new Date(info[c++]);
        pre.setDOB(dob);
        Date expire = new Date();

        expire.getYearLater(expire);
        pre.setExpire(expire);
        String location = info[c];
        for (Location locate : Location.values()) {
            if (location.toUpperCase().equals(locate.name())) {
                pre.setLocation(locate);
            }
        }
        if (validation(pre, location)) {
            if (!memData.add(pre)) {
                System.out.println(pre.getFname() + " " + pre.getLname() + " is already in the database.");
            } else {
                System.out.println(pre.getFname() + " " + pre.getLname() + " added.");
            }
        }
    }
    private void removeMem(String[] info) {
        Member mem = new Member();
        int c = 1;
        mem.setFname(info[c++]);
        mem.setLname(info[c++]);
        Date dob = new Date(info[c]);
        mem.setDOB(dob);
        if (!memData.remove(mem)) {
            System.out.println(mem.getFname() + " " + mem.getLname() + " is not in the database.");
        } else {
            System.out.println(mem.getFname() + " " + mem.getLname() + " removed.");
        }
    }
    private boolean validation(Member mem, String location) {
        if (!mem.isDOBValid()) {
            System.out.println("DOB " + mem.getDOB().toString() + ": invalid calendar date!");
            return false;
        }

        if (!mem.isExpirationValid()) {
            System.out.println("Expiration date " + mem.getExpire().toString() + ": invalid calendar date!");
            return false;
        }

        if (!mem.isDOBPast()) {
            System.out.println("DOB " + mem.getDOB().toString() + ": cannot be today or a future date!");
            return false;
        }

        if(!mem.isAbove18()) {
            System.out.println("DOB " + mem.getDOB().toString() + ": must be 18 or older to join!");
            return false;
        }

        if(!mem.isLocationValid()) {
            System.out.println(location + ": invalid location!");
            return false;
        }

        return true;
    }

    private void printMem() {
        if (memData.getSize() == 0) {
            System.out.println("Member database is empty!");
            return;
        }
        System.out.println("-list of members-");
        memData.print();
        System.out.println("-end of list-");
    }
    private void checkInMem(String[] info) {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        int c = 0;
        String className = info[++c];
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = info[++c];
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = info[++c];
        Location location = findLocation(locationName);
        fitness.setLocation(location);
        mem.setFname(info[++c]);
        mem.setLname(info[++c]);
        Date dob = new Date(info[++c]);
        mem.setDOB(dob);
        if(validCheckIn(mem, className, instructorName, locationName, fitness)) {
            if (!schedule.getFitnessClass(fitness).checkIn(memData.getMember(mem))) {
                System.out.println(mem.getFname() + " " + mem.getLname() + " already checked in.");
            }
            else {
                System.out.println(mem.getFname() + " " + mem.getLname() + " checked in " + schedule.getFitnessClass(fitness).toString());
            }
        }
    }
    private void checkInGuest(String[] info) {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        int c = 0;
        String className = info[++c];
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = info[++c];
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = info[++c];
        Location location = findLocation(locationName);
        fitness.setLocation(location);
        mem.setFname(info[++c]);
        mem.setLname(info[++c]);
        Date dob = new Date(info[++c]);
        mem.setDOB(dob);
        if(validGuestCheckIn(mem, className, instructorName, locationName, fitness)) {
            if (!schedule.getFitnessClass(fitness).checkInGuest((Family) memData.getMember(mem))) {
                System.out.println(mem.getFname() + " " + mem.getLname() + " ran out of guest passes.");
            }
            else {
                System.out.println(mem.getFname() + " " + mem.getLname() + " (guest) checked in " +
                        schedule.getFitnessClass(fitness).toString());
            }
        }
    }
    private Classes findClass(String className) {
        Classes fitness = null;
        for(Classes classes : Classes.values()) {
            if(className.toUpperCase().equals(classes.name())) {
                fitness = classes;
            }
        }
        return fitness;
    }
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

    private Time findTime(String timeStr) {
        Time time = null;
        for(Time t : Time.values()) {
            if(timeStr.toUpperCase().equals(t.name())) {
                time = t;
            }
        }
        return time;
    }
    private boolean validConditions(Member mem, String className, String instructorName,
                                    String locationName, FitnessClass fitness) {
        if(!mem.getDOB().isValid()) {
            System.out.println("DOB " + mem.getDOB() + ": invalid calendar date!");
            return false;
        }
        if(memData.getMember(mem) == null) {
            System.out.println(mem.getFname() + " " + mem.getLname() + " " + mem.getDOB() + " is not in the database.");
            return false;
        }
        if(fitness.getFitClass() == null) {
            System.out.println(className + " - class does not exist.");
            return false;
        }
        if(fitness.getInstructor() == null) {
            System.out.println(instructorName + " - instructor does not exist.");
            return false;
        }
        if(fitness.getLocation() == null) {
            System.out.println(locationName + " - invalid location.");
            return false;
        }
        if(schedule.getFitnessClass(fitness) == null) {
            Location falseLocation = locationCheck(fitness);
            if(falseLocation != null) {
                System.out.println(fitness.getFitClass().getClassName() + " by " +
                        fitness.getInstructor().toString() + " does not exist at " + falseLocation.name());
            }
            return false;
        }
        return true;
    }
    private boolean validCheckIn(Member mem, String className, String instructorName,
                                 String locationName, FitnessClass fitness) {
        Date today = new Date();
        if(validConditions(mem, className, instructorName, locationName, fitness)) {
            if (!(memData.getMember(mem) instanceof Family)) {
                if(!memData.getMember(mem).getLocation().equals(fitness.getLocation())) {
                    System.out.println(mem.getFname() + " " + mem.getLname() + " checking in " +
                            fitness.getLocation().toString() + " - standard membership location restriction.");
                    return false;
                }
            }
            if(memData.getMember(mem).getExpire().compareTo(today) < 0) {
                System.out.println(mem.getFname() + " " + mem.getLname() + " " + mem.getDOB() + " membership expired.");
                return false;
            }
            FitnessClass classConflict= checkTimeConflict(mem, schedule.getFitnessClass(fitness));
            if(classConflict != null) {
                System.out.println("Time conflict - " + classConflict.getFitClass().name() + " - " +
                        classConflict.getInstructor().name() + ", " + classConflict.getTime().toString() + ", " +
                        classConflict.getLocation().toString());
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean validGuestCheckIn(Member mem, String className, String instructorName,
                                      String locationName, FitnessClass fitness) {
        Date today = new Date();
        if(!(memData.getMember(mem) instanceof Family)) {
            System.out.println("Standard membership - guest check-in is not allowed.");
            return false;
        }
        if(!validConditions(mem, className, instructorName, locationName, fitness)) {
            return false;
        }
        if(memData.getMember(mem).getExpire().compareTo(today) < 0) {
            System.out.println(mem.getFname() + " " + mem.getLname() + " " + mem.getDOB() + " membership expired.");
            return false;
        }
        if(!memData.getMember(mem).getLocation().equals(fitness.getLocation())) {
            System.out.println(mem.getFname() + " " + mem.getLname() + " Guest checking in " +
                    fitness.getLocation().toString() + " - guest location restriction.");
            return false;
        }
        return true;
    }

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
    private void dropClass(String[] info) {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        int c = 0;
        String className = info[++c];
        Classes fclass = findClass(className);
        fitness.setClass(fclass);

        String instructorName = info[++c];
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);

        String locationName = info[++c];
        Location location = findLocation(locationName);
        fitness.setLocation(location);

        mem.setFname(info[++c]);
        mem.setLname(info[++c]);
        Date dob = new Date(info[++c]);
        mem.setDOB(dob);

        if(validConditions(mem, className, instructorName, locationName, fitness)) {
            if(!schedule.getFitnessClass(fitness).drop(mem)) {
                System.out.println(mem.getFname() + " " + mem.getLname() + " did not check in.");
            }
            else {
                System.out.println(mem.getFname() + " " + mem.getLname() + " done with the class.");
            }
        }
    }
    private void dropClassGuest(String[] info) {
        FitnessClass fitness = new FitnessClass();
        Member mem = new Member();
        int c = 0;
        String className = info[++c];
        Classes fclass = findClass(className);
        fitness.setClass(fclass);
        String instructorName = info[++c];
        Instructor instructor = findInstructor(instructorName);
        fitness.setInstructor(instructor);
        String locationName = info[++c];
        Location location = findLocation(locationName);
        fitness.setLocation(location);
        mem.setFname(info[++c]);
        mem.setLname(info[++c]);
        Date dob = new Date(info[++c]);
        mem.setDOB(dob);
        if(validConditions(mem, className, instructorName, locationName, fitness)) {
            if (!schedule.getFitnessClass(fitness).dropGuest((Family) memData.getMember(mem))) {
                System.out.println(mem.getFname() + " " + mem.getLname() + " has no guests checked in");
            }
            else {
                System.out.println(mem.getFname() + " " + mem.getLname() + " Guest done with the class.");
            }
        }

    }

}
 */
