package com.example.secondloginpage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    @FXML
    private Label newLabel;

    @FXML
    private Label oldLabel;

    @FXML
    private PasswordField tNew;

    @FXML
    private PasswordField tOld;
    @FXML
    private TextField tAge;

    @FXML
    private TextField tBlood;

    @FXML
    private TextField tCity;

    @FXML
    private DatePicker tEDate;
    @FXML
    private TextField tUser;
    @FXML
    private TextField tFname;

    @FXML
    private TextField tGender;

    @FXML
    private TextField tLname;



    @FXML
    private TextField tPNumber;

    @FXML
    private DatePicker tSDate;

    @FXML
    private TextField tSSN;

    @FXML
    private TextField tSalary;
    @FXML
    private AnchorPane slideA;
    public  void password(ActionEvent e){
       tNew.setVisible(true);
       tOld.setVisible(true);
       oldLabel.setVisible(true);
       newLabel.setVisible(true);

    }String Info=new String();
    public void moveInfo(String info){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            System.out.println(info);
            ResultSet rs = stmt.executeQuery("select * from employee where user_name= '"+info+"'");
            System.out.println(Info);
            while (rs.next()) {System.out.println("in while ");
                tUser.setText(rs.getString(14));
                tType.setValue(rs.getString(7));
                tSSN.setText(rs.getString(3));
                tFname.setText(rs.getString(1));
                tLname.setText(rs.getString(2));
                tAge.setText(rs.getString(8));
                tCity.setText(rs.getString(9));
                tGender.setText(rs.getString(12));
                tBlood.setText(rs.getString(10));
                tSalary.setText(rs.getString(11));
                tPNumber.setText(rs.getString(4));

                String date =rs.getString(6);

                tEDate.setValue(LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

                String date1 =rs.getString(5);
                tSDate.setValue(LocalDate.parse(date1,DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

            }

            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }
    }


    @FXML
    private ChoiceBox<String> tType;
String []s={"Manager","Engineer","Worker"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       tType.getItems().setAll(s);


    }
    public void Clear(ActionEvent e){
        tType.setValue("");
        tNew.setText("");
        tOld.setText("");
        tAge.setText("");
        tBlood.setText("");
        tCity.setText("");
        tPNumber.setText("");
        tSSN.setText("");
        tSalary.setText("");
        tFname.setText("");
        tLname.setText("");
        tUser.setText("");
        tGender.setText("");
       // String date1 =new String( "");
      //  tEDate.setValue(LocalDate.of(1,0,0));

        tEDate.getEditor().clear();
        tSDate.getEditor().clear();
        tEDate.setValue(null);
        tSDate.setValue(null);
    }
    public void Update(ActionEvent e){

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();

String date=new String("");
            String date1=new String("");

if(tEDate.getValue()!=null){
             date = tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));}
if(tSDate.getValue()!=null) {
     date1 = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
}
            String s=tSSN.getText();
            System.out.println("city : "+tCity.getText());

            stmt.executeUpdate("update employee set SSN="+tSSN.getText()+",FName='"+tFname.getText()+"',LName='"+tLname.getText()+"',City='"+tCity.getText()+"',scontract='"+date1+"',"+"econtract='"+date+"',jop_type='"+tType.getSelectionModel().getSelectedItem()+"',age="+tAge.getText()+",phone_number="+tPNumber.getText()+",blod_tybe='"+tBlood.getText()+"',salary="+tSalary.getText()+",gender='"+tGender.getText()+"'where ssn="+s);

            if(!tOld.getText().isEmpty()){

              ResultSet rs1=stmt.executeQuery("select password from employee where ssn="+tSSN.getText());
              while(rs1.next()){
                  if(tOld.getText().equals(rs1.getString(1))){
                      tOld.setStyle("-fx-border-color: #101152 ; ");

                      stmt.executeUpdate("update employee set password='"+tNew.getText()+"'"+"where ssn="+tSSN.getText());
                  }
                  else{
                      tOld.setStyle("-fx-border-color: red ; ");

                  }
              }            }




            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }
    }

}
