package com.example.secondloginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;

public class SingController implements Initializable {

    @FXML
    private ChoiceBox<String> choicebox;


    private String[] e={"Manager","Worker","Engineer"};

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField tAge;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tLName;

    @FXML
    private PasswordField tPassword;

    @FXML
    private TextField tSSN;
    @FXML
    private TextField tUser;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        choicebox.getItems().addAll(e);

    }
    public void SingOut(ActionEvent s) throws IOException {
 Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage=(Stage)((Node)s.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SignUp(ActionEvent s) throws IOException {
        Validation v=new Validation();
int flag=0;
        if(!v.validationSSN(tSSN.getText())||(tSSN.getText().isEmpty())){
            tSSN.setStyle("-fx-border-color: red ; ");
            flag=1;

        }
        else{
            tSSN.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationName(tFName.getText())||(tFName.getText().isEmpty())){
            tFName.setStyle("-fx-border-color: red ; ");
            flag=1;

        }
        else{
            tFName.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationName(tLName.getText())||(tLName.getText().isEmpty())){
            tLName.setStyle("-fx-border-color: red ; ");
            flag=1;
        }
        else{
            tLName.setStyle("-fx-border-color: #101152 ; ");
        }

        if(!v.validationNumber(tAge.getText())||(tAge.getText().isEmpty())){
            tAge.setStyle("-fx-border-color: red ; ");
            flag=1;
        }
        else{
            tAge.setStyle("-fx-border-color: #101152 ; ");
        }


        if((choicebox.getSelectionModel().isEmpty())){
            choicebox.setStyle("-fx-border-color: red ; ");
            flag=1;
        }
        else{
            choicebox.setStyle("-fx-border-color: #101152 ; ");

        }
        if(tPassword.getText().isEmpty()){
            tPassword.setStyle("-fx-border-color: red ; ");
            flag=1;
        }
        else{
            tPassword.setStyle("-fx-border-color: #101152 ; ");
        }

        if(tUser.getText().isEmpty()){
            tUser.setStyle("-fx-border-color: red ; ");
            flag=1;
        }
        else{
            tUser.setStyle("-fx-border-color: #101152 ; ");
        }


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();


            stmt.executeUpdate("insert into employee (ssn,fname,lname,jop_type,age,password,user_name)" +
                    "values ("+Integer.parseInt(tSSN.getText())+",'"+tFName.getText()+"',"+"'"+tLName.getText()+"','"+choicebox.getSelectionModel().getSelectedItem()+"',"+Integer.parseInt(tAge.getText())+",'"+tPassword.getText()+"','"+tUser.getText()+"')");
            con.close();
            if(flag==0){
                Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
                stage=(Stage)((Node)s.getSource()).getScene().getWindow();
                scene=new Scene(root);
                stage.setScene(scene);
                stage.show();}



        }

        catch (Exception exx) {
            System.out.println(exx);
        }


    }



}
