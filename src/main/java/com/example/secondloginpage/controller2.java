package com.example.secondloginpage;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class controller2 extends Thread{
    public controller2(){

    }
    Button log;
    @FXML
    ImageView qr;



    @FXML
    private Stage stage;
    private Stage stage1;
    private Scene scene;
    private Scene scene1;
    private Parent root;
    @FXML
    private AnchorPane loadingAn;

    private Label welcomeText;

    @FXML
    private PasswordField tPassword;

    @FXML
    private TextField tUser;
    private String info;
public void setInfo(String Info){
    info=Info;
}
    public void switchTo(ActionEvent e)throws IOException
    {


        /*Timeline t=new Timeline(new KeyFrame(Duration.seconds(4)));
        t.setCycleCount(1);
        t.play();*/
     /*   new Timer ().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("test2");
                    }
                },0,5000
        );

*/

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            ResultSet rs = stmt.executeQuery("select * from employee where password='"+tPassword.getText()+"' and user_name='"+tUser.getText()+"'");
            while (rs.next()) {
System.out.println("in test");
             if(rs.getString(14).equals(tUser.getText())&&rs.getString(13).equals(tPassword.getText())){

                 tUser.setStyle("-fx-border-color: #101152 ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
                 tPassword.setStyle("-fx-border-color: #101152 ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent; ");
                setInfo(tUser.getText());
                System.out.println("info in c2 : "+moveinfo());
                 con.close();
                 FXMLLoader loader=new FXMLLoader(getClass().getResource("Second.fxml"));
                 root=loader.load();
                  controller3 r=loader.getController();
                 r.moveInfo(tUser.getText());
                // Parent root = FXMLLoader.load(getClass().getResource("Second.fxml"));
                 stage=(Stage)((Node)e.getSource()).getScene().getWindow();
                 scene=new Scene(root);
                 stage.setScene(scene);
                 stage.show();

             }


            }
            tUser.setStyle("-fx-border-color: red ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent;");
            tPassword.setStyle("-fx-border-color: red ;-fx-border-width: 0px 0px 2px 0px; -fx-background-color: transparent; ");


            con.close();



        }

        catch (Exception exx) {
            System.out.println(exx);
        }






    }
    public String moveinfo(){
        return info;
    }

    public void switchToSingUp(ActionEvent e)throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("SingUp.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

public void Qr(MouseEvent s)throws IOException {

    ScaleTransition d = new ScaleTransition(Duration.seconds(0.5),qr);
    d.setByY(1.5);
    d.setByX(1.5);

    d.play();

}
public void Qr2(MouseEvent s)throws IOException{
    ScaleTransition d = new ScaleTransition(Duration.seconds(0.5),qr);
    d.setByY(-1.5);
    d.setByX(-1.5);

    d.play();


}





}