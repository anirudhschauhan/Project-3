package com.example.project3;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.format.DateTimeFormatter;



public class GymManagerController {
/*
    private MemberDatabase memData;
    private Schedule schedule;
    public GymManagerController() {
        memData = new MemberDatabase();
        schedule = new Schedule();
    }
    */
    @FXML
    private Label welcomeText;
    @FXML
    private TextField fname;
    @FXML
    private TextField lname;
    @FXML
    private DatePicker DOB;
    @FXML
    private TextField location;
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
    //@FXML
   /* protected void addMember(){
        if((fname.getText() == "") || (lname.getText()=="")){
            outputTextArea.appendText("Please enter your name.");
        }
        if(DOB.getValue().equals("")){
            outputTextArea.appendText("Please enter your date of birth.");
        }
        if(location.getText() == ""){
            outputTextArea.appendText("Please enter your location.");
        }
        if (standardMem.isSelected()) {
            addStandMem();
        }
        if(familyMem.isSelected()){

        }
        if(premiumMem.isSelected()){

        }

    }
    @FXML
    protected void removeMember(){
        removed.setText("Member removed from database");
    }
    private void addStandMem() {
        Member mem = new Member();
        int c = 1;
        mem.setFname(fname.getText());
        mem.setLname(lname.getText());
        Date dob = new Date(DOB.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        mem.setDOB(dob);
        Date expire = new Date();
        expire.getMonthsLater(expire);
        mem.setExpire(expire);

        String locate = location.getText();
        for (Location locatet : Location.values()) {
            if (locate.toUpperCase().equals(locatet.name())) {
                mem.setLocation(locatet);
            }
        }
        if (validation(mem, locate)) {
            if (!memData.add(mem)) {
                outputTextArea.appendText(mem.getFname() + " " + mem.getLname() + " is already in the database.");
            }
            else {
                outputTextArea.appendText(mem.getFname() + " "+ mem.getLname() + ", DOB: " + mem.getDOB() + ", Membership expires " + mem.getExpire() + ", Location: " + mem.getLocation());
            }
        }
    }
    private boolean validation(Member mem, String location) {
        if (!mem.isDOBValid()) {
            outputTextArea.appendText("DOB " + mem.getDOB().toString() + ": invalid calendar date!");
            return false;
        }

        if (!mem.isExpirationValid()) {
            outputTextArea.appendText("Expiration date " + mem.getExpire().toString() + ": invalid calendar date!");
            return false;
        }

        if (!mem.isDOBPast()) {
            outputTextArea.appendText("DOB " + mem.getDOB().toString() + ": cannot be today or a future date!");
            return false;
        }

        if(!mem.isAbove18()) {
            outputTextArea.appendText("DOB " + mem.getDOB().toString() + ": must be 18 or older to join!");
            return false;
        }

        if(!mem.isLocationValid()) {
            outputTextArea.appendText(location + ": invalid location!");
            return false;
        }

        return true;
    }
*/
}
