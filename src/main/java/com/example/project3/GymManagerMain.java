package com.example.project3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class launches the GUI for the GymManager program
 * @author Anirudh Schauhan, Matthew Calora
 */
public class GymManagerMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GymManagerMain.class.getResource("GymManagerView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 550);
        stage.setTitle("Gym Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This is the main method for the GUI.
     * Launches the GUI.
     * @author Anirudh Schauhan, Matthew Calora
     */
    public static void main(String[] args) {
        launch();
    }
}