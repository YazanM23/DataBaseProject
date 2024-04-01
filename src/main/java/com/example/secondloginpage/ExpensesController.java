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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ExpensesController implements Initializable {
    @FXML
    private TableColumn<EntityExpenses, String> AmountColumn;

    @FXML
    private TableColumn<EntityExpenses, String > DateColumn;

    @FXML
    private TableColumn<EntityExpenses, String> IDcolumn;

    @FXML
    private TableColumn<EntityExpenses, String> NameColumn;

    @FXML
    private TableColumn<EntityExpenses, String> ProjectIDcolumn;

    @FXML
    private TableColumn<EntityExpenses, String> ReasonColumn;
    @FXML
    private ChoiceBox<String> tESSN;
    @FXML
    private ChoiceBox<String> tPID;
    @FXML
    private TableColumn<EntityExpenses, String> TypeColumn;
    @FXML
    private TableView<EntityExpenses> tS;
    @FXML
    private AnchorPane pAn;
    public void move(ActionEvent e){
        if(tS.getTranslateY()==0){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(tS);

            slide.setToX(0);
            slide.setToY(86);
            slide.play();


            pAn.setVisible(true);

        }
        else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(tS);

            slide.setToX(0);
            slide.setToY(0);
            slide.play();
            pAn.setVisible(false);
        }

    }
public void onTableView(){
    IDcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
    AmountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
    DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
    ReasonColumn.setCellValueFactory(new PropertyValueFactory<>("Reason"));
    ProjectIDcolumn.setCellValueFactory(new PropertyValueFactory<>("Project_ID"));


    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();



        ResultSet rs = stmt.executeQuery("select * from expenses order by id asc");


        while (rs.next()) {
            EntityExpenses r=new EntityExpenses(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

            tS.getItems().add(r);

        }

        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();

            int i=0;
           System.out.println("select id from projects order by id asc");

            ResultSet rs = stmt.executeQuery("select id from projects order by id asc");
            ArrayList<String> projects = new ArrayList<String>();

            System.out.println("before while");
            while(rs.next()){
                System.out.println("in while");

                projects.add(rs.getString(1));

            }
            tPID.getItems().addAll(projects);
            ArrayList<String> employee = new ArrayList<String>();
            System.out.println("select ssn from employee where jop_type='Manager' order by ssn asc");

            ResultSet rs1 = stmt.executeQuery("select ssn from employee where jop_type='Manager' order by ssn asc");

            while(rs1.next()){
                employee.add(rs1.getString(1));

            }
            tESSN.getItems().addAll(employee);
            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }

        onTableView();
    }

    public void Delete(ActionEvent e){
        String s=new String(tS.getSelectionModel().getSelectedItem().getID());

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            stmt.executeUpdate("delete from expenses where id="+s+" ");


            con.close();

        }

        catch (Exception exx) {
        }
        tS.getItems().removeAll();

tS.getItems().clear();
        onTableView();
    }

    @FXML
    private TextField TName;
    @FXML
    private TextField tAmount;
    @FXML
    private TextField tID;

    @FXML
    private TextField tReason;
    @FXML
    private TextField tType;

    public void Clear(ActionEvent e){
TName.setText("");
        tID.setText("");
        tReason.setText("");
        tType.setText("");
        tAmount.setText("");
        tPID.setValue("");
        tESSN.setValue("");
        tDate.getEditor().clear();
        tDate.setValue(null);
tS.getItems().clear();
onTableView();

    }
    @FXML
    private DatePicker tDate;
public void Add(ActionEvent e){
Validation v=new Validation();
    if(!v.validationName(TName.getText())||(TName.getText().isEmpty())){
        TName.setStyle("-fx-border-color: red ; ");
    }
    else{
        TName.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationNumber(tID.getText())||(tID.getText().isEmpty())){
        tID.setStyle("-fx-border-color: red ; ");
    }
    else{
        tID.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tReason.getText())||(tReason.getText().isEmpty())){
        tReason.setStyle("-fx-border-color: red ; ");
    }
    else{
        tReason.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tType.getText())||(tType.getText().isEmpty())){
        tType.setStyle("-fx-border-color: red ; ");
    }
    else{
        tType.setStyle("-fx-border-color: #101152 ; ");
    }
    if(tPID.getSelectionModel().isEmpty()){
        tPID.setStyle("-fx-border-color: red ; ");
    }
    else{
        tPID.setStyle("-fx-border-color: #101152 ; ");
    }
    if(tESSN.getSelectionModel().isEmpty()){
        tESSN.setStyle("-fx-border-color: red ; ");
    }
    else{
        tESSN.setStyle("-fx-border-color: #101152 ; ");
    }

    if(!v.validationNumber(tAmount.getText())||(tAmount.getText().isEmpty())){
        tAmount.setStyle("-fx-border-color: red ; ");
    }
    else{
        tAmount.setStyle("-fx-border-color: #101152 ; ");
    }
    if((tDate.toString().isEmpty())){
        System.out.println("in");
        tDate.setStyle("-fx-border-color: red ; ");
    }
    else{
        System.out.println("in");
        tDate.setStyle("-fx-border-color: #101152 ; ");
    }





    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String date=new String();
        if(tDate.getValue()!=null){
             date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        }            String PID=new String();
        String ESSN=new String();
        if(!tPID.getSelectionModel().isEmpty()){
            PID= tPID.getSelectionModel().getSelectedItem().toString();
        }
        if(!tESSN.getSelectionModel().isEmpty()){
            ESSN=tESSN.getSelectionModel().getSelectedItem().toString();
        }

        System.out.println("insert into expenses (id,amount,pid,name,type,reason_of_paid,buy_date,essn) values ("+Integer.parseInt(tID.getText())+","+Integer.parseInt(tAmount.getText())+","+Integer.parseInt(tPID.getSelectionModel().getSelectedItem().toString())+",'"+TName.getText()+"','"+tType.getText()+"','"+tReason.getText()+"','"+"2-feb-2020"+"',"+Integer.parseInt("412675613")+")");
        System.out.println("insert into expenses (id,amount,pid,name,type,reason_of_paid,buy_date,essn) values ("+tID.getText()+","+tAmount.getText()+","+PID+",'"+TName.getText()+"','"+tType.getText()+"','"+tReason.getText()+"','"+date+"',"+ESSN+")");


        stmt.executeUpdate("insert into expenses (id,amount,pid,name,type,reason_of_paid,buy_date,essn) values ("+tID.getText()+","+tAmount.getText()+","+PID+",'"+TName.getText()+"','"+tType.getText()+"','"+tReason.getText()+"','"+date+"',"+ESSN+")");

        System.out.println("after add");
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }


    tS.getItems().clear();
    onTableView();
}
    @FXML
    private AnchorPane out;
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;


public void getRow(ActionEvent e){
    TName.setText(tS.getSelectionModel().getSelectedItem().getName());
    tID.setText(tS.getSelectionModel().getSelectedItem().getID());
    tReason.setText(tS.getSelectionModel().getSelectedItem().getReason());
    tType.setText(tS.getSelectionModel().getSelectedItem().getType());
    tAmount.setText(tS.getSelectionModel().getSelectedItem().getAmount());
tPID.setValue(tS.getSelectionModel().getSelectedItem().getProject_ID());
String date =tS.getSelectionModel().getSelectedItem().getDate();

tDate.setValue(LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
    String s= new String("");
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();



        ResultSet rs1 = stmt.executeQuery("select essn from expenses where id="+tS.getSelectionModel().getSelectedItem().getID());

        while(rs1.next()){
            System.out.println("in get row in while");
            s=new String(rs1.getString(1));


            System.out.println(rs1.getString(1));
        }
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }

    tESSN.setValue(s);

}
public void Update(ActionEvent e){
    try {

        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String s=tID.getText();
        String date=new String();
        if(tDate.getValue()!=null){
             date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        }
        String PID=new String();
        String ESSN=new String();
        if(!tPID.getSelectionModel().isEmpty()){
            PID= tPID.getSelectionModel().getSelectedItem().toString();
        }
        if(!tESSN.getSelectionModel().isEmpty()){
            ESSN=  tESSN.getSelectionModel().getSelectedItem().toString();
        }

        stmt.executeUpdate("update expenses set id="+tID.getText()+",name='"+TName.getText()+"',type='"+tType.getText()+"',reason_of_paid='"+tReason.getText()+"'"+",Buy_date='"+date+"',pid="+PID+",amount="+tAmount.getText()+",essn="+ESSN+"where id="+s);

       tS.getItems().clear();
        onTableView();
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
}


    public void Search(ActionEvent e){
        IDcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        AmountColumn.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
        ReasonColumn.setCellValueFactory(new PropertyValueFactory<>("Reason"));
        ProjectIDcolumn.setCellValueFactory(new PropertyValueFactory<>("Project_ID"));
int flagempty=0;
        //select * from projects where id like '%%' and capacity like '%%' and location like '%%' and year like '%%' and type_of_projects like '%%'  and name like '%%' and owner like '%%' and sid like '%%';
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();
            int i=0;
            String date=new String();
            ResultSet rs;

            String PID=new String();
            String ESSN=new String();
if(!tPID.getSelectionModel().isEmpty()){
   PID= tPID.getSelectionModel().getSelectedItem().toString();
}
if(!tESSN.getSelectionModel().isEmpty()){
  ESSN=  tESSN.getSelectionModel().getSelectedItem().toString();
}
            if(tDate.getValue()!=null){
                date = tDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
                 rs = stmt.executeQuery("select * from expenses where id like'%"+tID.getText()+"%' and name like '%"+TName.getText()+"%'and type like '%"+tType.getText()+"%' and reason_of_paid like '%"+tReason.getText()+"%' and amount like '%"+tAmount.getText()+"%'  and PID like '%"+PID+"%'and essn like '%"+ESSN+"%' and Buy_date='"+date+"' order by id asc");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");

            }else{
                 rs = stmt.executeQuery("select * from expenses where id like'%"+tID.getText()+"%' and name like '%"+TName.getText()+"%'and type like '%"+tType.getText()+"%' and reason_of_paid like '%"+tReason.getText()+"%' and amount like '%"+tAmount.getText()+"%'  and PID like '%"+PID+"%'and essn like '%"+ESSN+"%'" +
                         "" +
                         " order by id asc");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");


            }


            System.out.println("select * from expenses where id like'%"+tID.getText()+"%' and name like '%"+TName.getText()+"%'and type like '%"+tType.getText()+"%' and reason_of_paid like '%"+tReason.getText()+"%' and amount like '%"+tAmount.getText()+"%'  and PID like '%"+PID+"%'and essn like '%"+ESSN+"%' and Buy_date like '%"+date+"%'");//and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'");




    while(rs.next()){flagempty=1;
        if(i==0){
            tS.getItems().clear();
        }i++;

        EntityExpenses   d=new EntityExpenses(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));

        tS.getItems().add(d);

    }
    con.close();


        }



        catch (Exception exx) {
            System.out.println(exx);
        }
        if(flagempty==0){
            tS.getItems().clear();
        }


    }
}
