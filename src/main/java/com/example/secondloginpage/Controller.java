package com.example.secondloginpage;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
    @FXML
    ImageView img;

    public void setImage(Image s){
        img.setImage(s);
    }

}
