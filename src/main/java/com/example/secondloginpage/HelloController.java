package com.example.secondloginpage;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloController {
    Button log;
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Label welcomeText;

    public HelloController(Stage stage) {
        this.stage = stage;
    }



}