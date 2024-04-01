package com.example.secondloginpage;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;
public class Tcontroller implements Initializable {
    @FXML
    private ChoiceBox<String> choicebox;


    private String[] e={"ID","Date","Name","Type","Amount","Paid for","Project ID","Employee SSN"};


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicebox.getItems().addAll(e);

    }




}