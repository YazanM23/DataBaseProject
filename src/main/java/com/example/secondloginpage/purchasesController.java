package com.example.secondloginpage;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class purchasesController implements Initializable {
    @FXML
    private AnchorPane purSlide;
    @FXML
    private ChoiceBox<String> tESSN;
    @FXML
    private TableView<EntityPurchases> purTable;
    @FXML
    private TextField t1;

    @FXML
    private TextField t2;

    @FXML
    private TextField t3;

    @FXML
    private TextField t4;
    @FXML
    private TableColumn<EntityPurchases, String> AmountColumn;

    @FXML
    private TableColumn<EntityPurchases, String> DateColumn;

    @FXML
    private TableColumn<EntityPurchases, String> NameColumn;

    @FXML
    private TableColumn<EntityEmployee, String> QuantityColumn;

    @FXML
    private TableColumn<EntityEmployee, String> TypeColumn;
    public void Pmove(ActionEvent e){
        if(purTable.getTranslateY()==-90){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(purTable);

            slide.setToX(212);
            slide.setToY(0);
            slide.play();


            purSlide.setVisible(true);

        }
        else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(purTable);

            slide.setToX(212);
            slide.setToY(-90);
            slide.play();
            purSlide.setVisible(false);
        }

    }



    public void onTable(){
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        AmountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            ResultSet rs = stmt.executeQuery("select * from purchases order by name asc");
            while (rs.next()) {
                EntityPurchases r=new EntityPurchases(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(6));

                purTable.getItems().add(r);

            }

            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }

    }

    @FXML
    private ChoiceBox<String > tStore;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();

            ArrayList<String> employee = new ArrayList<String>();

            ResultSet rs1 = stmt.executeQuery("select ssn from employee where jop_type='Manager' order by ssn asc");
            while(rs1.next()){
                employee.add(rs1.getString(1));

            }
            tESSN.getItems().addAll(employee);

            ResultSet rs2=stmt.executeQuery("select * from warehouse order by id asc");

            ArrayList<String> store = new ArrayList<String>();

            while (rs2.next()){
                store.add(rs2.getString(1));

            }
            tStore.getItems().addAll(store);
            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }

        onTable();

    }

    public void Delete(ActionEvent e){
        String s=new String(purTable.getSelectionModel().getSelectedItem().getName());


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            stmt.executeUpdate("delete from purchases where name="+"'"+s+"'");


            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }
        purTable.getItems().clear();
        onTable();
    }
    @FXML
    private TextField tAmount;

    @FXML
    private DatePicker tDate;

    @FXML
    private TextField tName;

    @FXML
    private TextField tQuantity;

    @FXML
    private TextField tType;
public  void  Clear(ActionEvent e){
        tName.setText("");
        tQuantity.setText("");
    tAmount.setText("");
    tType.setText("");
    tESSN.setValue("");
    purTable.getItems().clear();
    tDate.getEditor().clear();
    tDate.setValue(null);
    tStore.setValue("");
    onTable();
}
public void getRow(){
    tName.setText(purTable.getSelectionModel().getSelectedItem().getName());
    tQuantity.setText(purTable.getSelectionModel().getSelectedItem().getQuantity());
    tAmount.setText(purTable.getSelectionModel().getSelectedItem().getAmount());
    tType.setText(purTable.getSelectionModel().getSelectedItem().getType());
    String date =purTable.getSelectionModel().getSelectedItem().getDate();
    tDate.setValue(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    String s= new String("");
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();



        ResultSet rs1 = stmt.executeQuery("select essn from purchases where Name='"+purTable.getSelectionModel().getSelectedItem().getName()+"'");
while(rs1.next()){
    System.out.println("in get row in while");
  s=new String(rs1.getString(1));


    System.out.println(rs1.getString(1));
}
        ResultSet rs2=stmt.executeQuery("Select wid from wareh_purch where pname ='"+purTable.getSelectionModel().getSelectedItem().getName()+"'");

while(rs2.next()){
    tStore.setValue(rs2.getString(1));
}
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }

    tESSN.setValue(s);
}
public  void Add(ActionEvent e){
    Validation v=new Validation();
    if((tDate.toString().isEmpty())){
        tDate.setStyle("-fx-border-color: red ; ");
    }
    else{
        tDate.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tName.getText())||(tName.getText().isEmpty())){
        tName.setStyle("-fx-border-color: red ; ");
    }
    else{
        tName.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationNumber(tQuantity.getText())||(tQuantity.getText().isEmpty())){
        tQuantity.setStyle("-fx-border-color: red ; ");
    }
    else{
        tQuantity.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationNumber(tAmount.getText())||(tAmount.getText().isEmpty())){
        tAmount.setStyle("-fx-border-color: red ; ");
    }
    else{
        tAmount.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tType.getText())||(tType.getText().isEmpty())){
        tType.setStyle("-fx-border-color: red ; ");
    }
    else{
        tType.setStyle("-fx-border-color: #101152 ; ");
    }
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String date=new String();
        if(tDate.getValue()!=null){
             date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        }
        String ESSN=new String();
        if(!tESSN.getSelectionModel().isEmpty()){
            ESSN=tESSN.getSelectionModel().getSelectedItem().toString();
        }
        String Store=new String();
        if(!tStore.getSelectionModel().isEmpty()){
            Store=tStore.getSelectionModel().getSelectedItem().toString();
        }
System.out.println("insert into purchases (amount,quantity,essn,ptype,date_of_buy,name) values ("+tAmount.getText()+","+tQuantity.getText()+","+"412675613"+",'"+tType.getText()+"','"+date+"','"+tName.getText()+"')");

        stmt.executeUpdate("insert into purchases (amount,quantity,essn,ptype,date_of_buy,name) values ("+tAmount.getText()+","+tQuantity.getText()+","+ESSN+",'"+tType.getText()+"','"+date+"','"+tName.getText()+"')");
stmt.executeUpdate("insert into wareh_purch (wid,pname) values ("+Store+",'"+tName.getText()+"')");

        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
    purTable.getItems().clear();
    onTable();
}
public void Update(ActionEvent e){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String s=tName.getText();
        String date=new String();
        if(tDate.getValue()!=null){
             date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        }
        String ESSN=new String();
        if(!tESSN.getSelectionModel().isEmpty()){
            ESSN=tESSN.getSelectionModel().getSelectedItem().toString();
        }
        String Store=new String();
        if(!tStore.getSelectionModel().isEmpty()){
            Store=tStore.getSelectionModel().getSelectedItem().toString();
        }
        System.out.println("update Purchases set name='"+tName.getText()+"',amount="+tAmount.getText()+",quantity="+tQuantity.getText()+",date_of_buy='"+date+"',ptype='"+tType.getText() +"',essn="+ESSN+" where name='"+s+"'");

        stmt.executeUpdate("update Purchases set name='"+tName.getText()+"',amount="+tAmount.getText()+",quantity="+tQuantity.getText()+",date_of_buy='"+date+"',ptype='"+tType.getText() +"',essn="+ESSN+" where name='"+s+"'");
        System.out.println("update wareh_purch set pname='"+tName.getText()+"',wid='"+Store+"')");

        stmt.executeUpdate("update wareh_purch set pname='"+tName.getText()+"',wid='"+Store+"'where pname='"+tName.getText()+"'");

        ResultSet r=stmt.executeQuery("select * from wareh_purch where pname='"+tName.getText()+"'");
        System.out.println("select * from wareh_purch where pname='"+tName.getText()+"'");


            System.out.println("in while r.next");
int flag=0;
            while(r.next()){
                flag=1;
                System.out.println("here");
            }
            if(flag==0){
                System.out.println("insert into wareh_purch (wid,pname) values ("+tStore.getSelectionModel().getSelectedItem()+",'"+tName.getText()+"')");

                stmt.executeUpdate("insert into wareh_purch (wid,pname) values ("+tStore.getSelectionModel().getSelectedItem()+",'"+tName.getText()+"')");


        }
        purTable.getItems().clear();
        onTable();
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }

}
    @FXML
    private AnchorPane out;
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;

    public void Search(ActionEvent e){
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        QuantityColumn.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        AmountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        //select * from projects where id like '%%' and capacity like '%%' and location like '%%' and year like '%%' and type_of_projects like '%%'  and name like '%%' and owner like '%%' and sid like '%%';
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();
            int i=0;
            int flagempty=0;

            String date=new String();
            if(tDate.getValue()!=null){
                date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            }
            String ESSN=new String();

            if(!tESSN.getSelectionModel().isEmpty()){
                ESSN=  tESSN.getSelectionModel().getSelectedItem().toString();
            }
            String Store=new String();

            if(!tStore.getSelectionModel().isEmpty()){
                Store=  tStore.getSelectionModel().getSelectedItem().toString();
            }
            System.out.println("select id,name,ptype,amount,quantity,date_of_buy from purchases,wareH_purch,wareHouse where Wid=id and pname=name and Name like'%"+tName.getText()+"%' and amount like '%"+tAmount.getText()+"%'and quantity like '%"+tQuantity.getText()+"%' and ptype like '%"+tType.getText()+"%' and date_of_buy like '%"+date+"%'"+"and essn like '%"+ESSN+"%' and wid like '%"+Store+"%' order by name asc");//and amount like '%"+tAmount.getText()+"%'  and PID like '%"+tPID.getText()+"%' ");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");
            ResultSet rs=null;
            if(tDate.getValue()!=null) {
                 rs = stmt.executeQuery("select id,name,ptype,amount,quantity,date_of_buy from purchases,wareH_purch,wareHouse where Wid=id and pname=name and Name like'%" + tName.getText() + "%' and amount like '%" + tAmount.getText() + "%'and quantity like '%" + tQuantity.getText() + "%' and ptype like '%" + tType.getText() + "%'" + "and essn like '%" + ESSN + "%' and id like '%" + Store + "%' and date_of_buy='"+date+"' order by name asc");//and amount like '%"+tAmount.getText()+"%'  and PID like '%"+tPID.getText()+"%' ");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");
            }
            else{
                rs = stmt.executeQuery("select id,name,ptype,amount,quantity,date_of_buy from purchases,wareH_purch,wareHouse where Wid=id and pname=name and Name like'%" + tName.getText() + "%' and amount like '%" + tAmount.getText() + "%'and quantity like '%" + tQuantity.getText() + "%' and ptype like '%" + tType.getText() + "%'" + "and essn like '%" + ESSN + "%' and id like '%" + Store + "%' order by name asc");//and amount like '%"+tAmount.getText()+"%'  and PID like '%"+tPID.getText()+"%' ");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");


            }
                while(rs.next()){ flagempty=1;
                    if(i==0){

                        purTable.getItems().clear();
                    }i++;

                    EntityPurchases r=new EntityPurchases(rs.getString(2),rs.getString(4),rs.getString(5), rs.getString(6), rs.getString(3));

                    purTable.getItems().add(r);

                }



            if (flagempty==0){
                purTable.getItems().clear();
            }
            con.close();
            }
        catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }





    }



}
