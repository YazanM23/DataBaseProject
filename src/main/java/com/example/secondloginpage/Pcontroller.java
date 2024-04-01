package com.example.secondloginpage;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;


public class Pcontroller implements Initializable {

@FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private AnchorPane slideq;

    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("test");
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(slideq);

        slide.setToX(240);
        slide.setToY(1);
        slide.play();

    }


}
