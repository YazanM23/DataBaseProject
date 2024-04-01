package com.example.secondloginpage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoadingController extends Thread{
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;
   public LoadingController(){
       try {
           Parent root = FXMLLoader.load(getClass().getResource("loading.fxml"));

           scene=new Scene(root);
           stage.setScene(scene);
           stage.show();


       }
       catch (Exception e){
           System. out.println("test1");
       }
   }

}
