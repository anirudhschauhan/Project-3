package com.example.project3;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;



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
            }
            else {
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
            }
            else {
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
    @FXML
    protected void printCounty(){
        outputTextArea.appendText(memData.printByCounty());
    }
    @FXML
    protected void printName(){
        outputTextArea.appendText(memData.printByName());
    }
    @FXML
    protected void printExp(){
        outputTextArea.appendText(memData.printByExpirationDate());
    }
    @FXML
    protected void printFees(){
        outputTextArea.appendText(memData.printMemFees());
    }
    @FXML
    protected void addFile(){
        if(scheduletxt.isSelected()){
            outputTextArea.appendText("-list of classes loaded-\n");
            String [] stuff= schedule.readLines("classSchedule.txt");

            for(int i = 0; i < stuff.length;i++){
                int count = 0;
                String [] lines = stuff[i].split(" ");
                FitnessClass fitness = new FitnessClass();
                String className = lines[count++];
                Classes fclass = findClass(className);
                fitness.setClass(fclass);

                String instructorName = lines[count++];
                Instructor instructor = findInstructor(instructorName);
                fitness.setInstructor(instructor);
                String timeStr = lines[count++];
                Time time = findTime(timeStr);
                fitness.setTime(time);
                String locationName = lines[count];
                Location location = findLocation(locationName);
                fitness.setLocation(location);
                schedule.addClass(fitness);
                outputTextArea.appendText(fitness.toString()+"\n");
             }

            outputTextArea.appendText("-end of class list-\n");
        }
        else if(membertxt.isSelected()){
            outputTextArea.appendText("-list of members loaded-\n");
            String [] stuff = memData.readLines("memberList.txt");
            for(int i = 0; i < stuff.length;i++) {
                String[] lines = stuff[i].split("\\s+");
                if (stuff[i].equals("File Not Found")) {
                    outputTextArea.appendText(stuff[i]);
                    break;
                }
                outputTextArea.appendText(addFileMem(lines));
            }
            outputTextArea.appendText("-end of list-\n");

        }

    }

    private String addFileMem(String[] info) {
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
                return(mem.getFname() + " " + mem.getLname() + " is already in the database.\n");
            } else {
                return(mem.toString()+"\n");
            }
        }
        return"Error";
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
    @FXML
    private void checkInPerson(){
        if(guestCheck.isSelected()){
            checkInGuest();
        }
        else{
            checkInMem();
        }

    }
    @FXML
    private void dropPerson(){
        if(guestCheck.isSelected()){
            dropClassGuest();
        }
        else{
            dropClass();
        }

    }
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

}
