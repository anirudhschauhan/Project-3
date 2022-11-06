package com.example.project3;

import javafx.fxml.*;
import javafx.scene.control.Label;
import java.io.*;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.collections.*;

public class GymManagerController {

    @FXML
    private Label welcomeText;
    @FXML
    private Label fname;
    @FXML
    private Label lname;
    @FXML
    private Label DOB;
    @FXML
    private Label location;
    @FXML
    private Label StdMem;
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


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    protected void onAddButtonClick(){
        added.setText("Member added to database.");
    }

    protected  void onRemoveButtonClick(){
        removed.setText("Member removed from database");
    }

}
