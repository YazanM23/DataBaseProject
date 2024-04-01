package com.example.secondloginpage;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class WareHouseController implements Initializable {


    @FXML
    private TableColumn<EntityWareHouse, String> ColumnAmount;

    @FXML
    private TableColumn<EntityWareHouse, String > ColumnDate;

    @FXML
    private TableColumn<EntityWareHouse, String> ColumnID;

    @FXML
    private TableColumn<EntityWareHouse, String> ColumnQuantity;

    @FXML
    private TableColumn<EntityWareHouse, String> ColumnType;

    @FXML
    private TableColumn<EntityWareHouse, String> ColumnName;
    @FXML
    private AnchorPane wareSlide;
    @FXML
    private TableView<EntityWareHouse> wareTable;
    public void Hmove(ActionEvent e){
        if(wareTable.getTranslateY()==-90){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(wareTable);

            slide.setToX(213);
            slide.setToY(0);
            slide.play();


            wareSlide.setVisible(true);

        }
        else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(wareTable);

            slide.setToX(213);
            slide.setToY(-90);
            slide.play();
            wareSlide.setVisible(false);
        }

    }
    @FXML
    private Hyperlink getrow;

    @FXML
    private TextField tAmount;

    @FXML
    private DatePicker tDate;

    @FXML
    private TextField tID;

    @FXML
    private TextField tName;

    @FXML
    private TextField tName1;

    @FXML
    private TextField tQuantity;

    @FXML
    private TextField tType;


public void getRow(ActionEvent e) {
    tName.setText(wareTable.getSelectionModel().getSelectedItem().getName());
    tQuantity.setText(wareTable.getSelectionModel().getSelectedItem().getQuantity());
    tAmount.setText(wareTable.getSelectionModel().getSelectedItem().getAmount());
    tType.setText(wareTable.getSelectionModel().getSelectedItem().getType());
    String date =wareTable.getSelectionModel().getSelectedItem().getDate();
    tDate.setValue(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    tID.setText(wareTable.getSelectionModel().getSelectedItem().getID());

}

 public void Clear(ActionEvent e){
     tName.setText("");
     tQuantity.setText("");
     tAmount.setText("");
     tType.setText("");
  tID.setText("");
     tDate.getEditor().clear();
     tDate.setValue(null);
     wareTable.getItems().clear();
     onTable();
 }
    @FXML
    private ChoiceBox<String> tStore;
 public void onTable(){
     ColumnAmount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
     ColumnDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
     ColumnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
     ColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
     ColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
     ColumnType.setCellValueFactory(new PropertyValueFactory<>("Type"));
     try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection con = DriverManager.getConnection(
                 "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
         Statement stmt = con.createStatement();



         ResultSet rs = stmt.executeQuery("select id,name,ptype,amount,quantity,date_of_buy from purchases,wareH_purch,wareHouse where Wid=id and pname=name order by id asc");

         while (rs.next()) {

            EntityWareHouse r=new EntityWareHouse(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5) ,rs.getString(6));

             wareTable.getItems().add(r);

         }

         con.close();

     }

     catch (Exception exx) {
         System.out.println(exx);
     }

 }
 public void Delete(ActionEvent e){
     String s=new String(wareTable.getSelectionModel().getSelectedItem().getName());


     try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection con = DriverManager.getConnection(
                 "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
         Statement stmt = con.createStatement();

         System.out.println("delete from wareh_purch where pname="+"'"+s+"'");


         stmt.executeUpdate("delete from wareh_purch where pname="+"'"+s+"'");


         con.close();

     }

     catch (Exception exx) {
         System.out.println(exx);
     }
     wareTable.getItems().clear();
     onTable();
 }
public void add(ActionEvent e){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();


ResultSet rs=stmt.executeQuery("select * from warehouse order by id asc");
int i=1;
while(rs.next()){
    i++;
}
if(i<=2){System.out.println("in insert");
    stmt.executeUpdate("insert into warehouse (id) values ("+tID.getText()+")");
}
else{
    Dialog<String> dialog = new Dialog<String>();
    //Setting the title
    dialog.setTitle("Warning");
    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
    //Setting the content of the dialog
    dialog.setContentText("You Can't Add More Than Two WareHouses");

    //Adding buttons to the dialog pane
    dialog.getDialogPane().getButtonTypes().add(type);
dialog.show();}



        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
}
public void Search(ActionEvent e){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        ResultSet rs = null;
        int i=0;
        int flagempty=0;


        /*
Class.forName("oracle.jdbc.driver.OracleDriver");
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@ip:1521:xe","user","userpass"):
preparedStatment stms=con.PreparedStatment("insertinto (?,?))
stms.set
stmt.execute
         */

        String date=new String();
        if(tDate.getValue()!=null){
            date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        }

if(tDate.getValue()!=null) {
    rs = stmt.executeQuery("select id,name,ptype,amount,quantity,date_of_buy from purchases,wareH_purch,wareHouse where Wid=id and pname=name and Name like'%" + tName.getText() + "%' and amount like '%" + tAmount.getText() + "%'and quantity like '%" + tQuantity.getText() + "%' and ptype like '%" + tType.getText() + "%' and date_of_buy= '" + date + "'" + " and id like '%" + tID.getText() + "%' order by id asc");//and amount like '%"+tAmount.getText()+"%'  and PID like '%"+tPID.getText()+"%' ");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");
}
else{
    rs = stmt.executeQuery("select id,name,ptype,amount,quantity,date_of_buy from purchases,wareH_purch,wareHouse where Wid=id and pname=name and Name like'%" + tName.getText() + "%' and amount like '%" + tAmount.getText() + "%'and quantity like '%" + tQuantity.getText() + "%' and ptype like '%" + tType.getText() + "%' " + " and id like '%" + tID.getText() + "%' order by id asc");//and amount like '%"+tAmount.getText()+"%'  and PID like '%"+tPID.getText()+"%' ");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");

}
        while(rs.next()){ flagempty=1;
            if(i==0){

                wareTable.getItems().clear();
            }i++;

            EntityWareHouse r=new EntityWareHouse(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6));

            wareTable.getItems().add(r);

        }



        if (flagempty==0){
            wareTable.getItems().clear();
        }

        con.close();
    }
    catch (SQLException ex) {
        throw new RuntimeException(ex);
    } catch (ClassNotFoundException ex) {
        throw new RuntimeException(ex);
    }


}
public void DeleteStore(ActionEvent e){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();

      System.out.println("delete  from warehouse where id="+tID.getText());

stmt.executeUpdate("delete from warehouse where id="+tID.getText());

        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
}
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        onTable();
    }
}
