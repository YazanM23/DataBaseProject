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
import org.controlsfx.control.Notifications;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class projectsController implements Initializable {
    @FXML
    private TextField tCapacity;

    @FXML
    private DatePicker tDate;

    @FXML
    private DatePicker tDateMan;

    @FXML
    private TextField tID;

    @FXML
    private TextField tLocation;

    @FXML
    private TextField tName;

    @FXML
    private TextField tOwner;

    @FXML
    private TextField tType;

    @FXML
    private TextField tYear;

    @FXML
    private AnchorPane prSlide;
    @FXML
    private TableView<EntityProject> prTable;
    public void Prmove(ActionEvent e){

        if(prTable.getTranslateY()==-90){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(prTable);

            slide.setToX(210);
            slide.setToY(0);
            slide.play();


            prSlide.setVisible(true);

        }
        else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(prTable);

            slide.setToX(210);
            slide.setToY(-90);
            slide.play();
            prSlide.setVisible(false);
        }

    }





    @FXML
    private TableColumn<EntityProject, String> CapacityColumn;
    @FXML
    private ChoiceBox<String> tStore;
    @FXML
    private TableColumn<EntityProject, String> IDcolumn;

    @FXML
    private TableColumn<EntityProject, String> LocationColumn;

    @FXML
    private TableColumn<EntityProject, String> ManufColumn;

    @FXML
    private TableColumn<EntityProject, String> NameColumn;

    @FXML
    private TableColumn<EntityProject, String> OwnerColumn;

    @FXML
    private TableColumn<EntityProject, String> TypeColumn;

    @FXML
    private TableColumn<EntityProject, String> YearColumn;

    public void onTable(){
        IDcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        OwnerColumn.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        YearColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));
        CapacityColumn.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        ManufColumn.setCellValueFactory(new PropertyValueFactory<>("Manufactoring_Date"));
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            int i=0;
            ResultSet rs = stmt.executeQuery("select * from projects order by id asc");
            while (rs.next()) {

                EntityProject r=new EntityProject(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
                prTable.getItems().add(r);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();

         String date=new String(rs.getDate(6).toString());
         String date1=new String(now.toLocalDate().toString());

if(date.equals(date1)){
    Notifications.create()
            .title("Warning")
            .text("Project ID = "+rs.getString(1)+" Need Manufacture")
            .showWarning();
}
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
            ResultSet rs = stmt.executeQuery("select id from warehouse order by id asc");
            ArrayList<String> store = new ArrayList<String>();
            while(rs.next()){
  store.add(rs.getString(1));

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
     String s=new String(prTable.getSelectionModel().getSelectedItem().getID());

    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();



        stmt.executeUpdate("delete from projects where id="+s+" ");


        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
    prTable.getItems().clear();
    onTable();

}
public void Add(ActionEvent e){
    Validation v=new Validation();
    if(!v.validationNumber(tID.getText())||(tID.getText().isEmpty())){
        tID.setStyle("-fx-border-color: red ; ");
    }
    else{
        tID.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationNumber(tCapacity.getText())||(tCapacity.getText().isEmpty())){
        tCapacity.setStyle("-fx-border-color: red ; ");
    }
    else{
        tCapacity.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationNumber(tYear.getText())||(tYear.getText().isEmpty())){
        tYear.setStyle("-fx-border-color: red ; ");
    }
    else{
        tYear.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tOwner.getText())||(tOwner.getText().isEmpty())){
        tOwner.setStyle("-fx-border-color: red ; ");
    }
    else{
        tOwner.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tType.getText())||(tType.getText().isEmpty())){
        tType.setStyle("-fx-border-color: red ; ");
    }
    else{
        tType.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tLocation.getText())||(tLocation.getText().isEmpty())){
        tLocation.setStyle("-fx-border-color: red ; ");
    }
    else{
        tLocation.setStyle("-fx-border-color: #101152 ; ");
    }
    if(!v.validationName(tName.getText())||(tName.getText().isEmpty())){
        tName.setStyle("-fx-border-color: red ; ");
    }
    else{
        tName.setStyle("-fx-border-color: #101152 ; ");
    }
    if((tDateMan.toString().isEmpty())){
        tDateMan.setStyle("-fx-border-color: red ; ");
    }
    else{
        tDateMan.setStyle("-fx-border-color: #101152 ; ");
    }
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String date = tDateMan.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        if(tDateMan.getValue()!=null) {
           date = tDateMan.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

        }


        stmt.executeUpdate("insert into projects (id,capacity,location,year,type_of_projects,DATE_MANUFACTORING,Name,owner,sid) values ("
        +tID.getText()+","+tCapacity.getText()+",'"+tLocation.getText()+"',"+tYear.getText()+",'"+tType.getText()+"','"+date+"','"+tName.getText()+"','"+tOwner.getText()+"',"+tStore.getSelectionModel().getSelectedItem()+")");


        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }
    prTable.getItems().clear();
    onTable();

}
    @FXML
    private AnchorPane out;
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;

public void Clear(ActionEvent e){
      tID.setText("");
    tOwner.setText("");
    tName.setText("");
    tType.setText("");
    tLocation.setText("");
    tYear.setText("");
    tCapacity.setText("");
    tStore.setValue("");
    prTable.getItems().clear();
    tDateMan.getEditor().clear();
    tDateMan.setValue(null);

    onTable();

}
public void getRow(ActionEvent e){
    tID.setText(prTable.getSelectionModel().getSelectedItem().getID());
    tOwner.setText(prTable.getSelectionModel().getSelectedItem().getOwner());
    tName.setText(prTable.getSelectionModel().getSelectedItem().getName());
    tType.setText(prTable.getSelectionModel().getSelectedItem().getType());
    tLocation.setText(prTable.getSelectionModel().getSelectedItem().getLocation());
    tYear.setText(prTable.getSelectionModel().getSelectedItem().getYear());
    tCapacity.setText(prTable.getSelectionModel().getSelectedItem().getCapacity());
    String date =prTable.getSelectionModel().getSelectedItem().getManufactoring_Date();
    tDateMan.setValue(LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));


    String s= new String("");
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();



        ResultSet rs1 = stmt.executeQuery("select sid from projects where id="+prTable.getSelectionModel().getSelectedItem().getID());

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

    tStore.setValue(s);
}

public void Update (ActionEvent e){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String s=tID.getText();
        String date=new String();
        if(tDateMan.getValue()!=null) {
             date = tDateMan.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        }

        stmt.executeUpdate("update projects set capacity="+tCapacity.getText()+",location='"+tLocation.getText()+"',year="+tYear.getText()+",type_of_projects='"+tType.getText()+"',DATE_MANUFACTORING='"+date+"'"+",name='"+tName.getText()+"',owner='"+tOwner.getText()+"',sid=2 where id="+s);

       prTable.getItems().clear();
        onTable();
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }

}
public void Search(ActionEvent e){
    int flagempty=0;
    IDcolumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
    NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
    TypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
    OwnerColumn.setCellValueFactory(new PropertyValueFactory<>("Owner"));
    LocationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
    YearColumn.setCellValueFactory(new PropertyValueFactory<>("Year"));
    CapacityColumn.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
    ManufColumn.setCellValueFactory(new PropertyValueFactory<>("Manufactoring_Date"));
        //select * from projects where id like '%%' and capacity like '%%' and location like '%%' and year like '%%' and type_of_projects like '%%'  and name like '%%' and owner like '%%' and sid like '%%';
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        int i=0;
        if(tDateMan.getValue()==null&&!(tStore.getSelectionModel().isEmpty())) {
        ResultSet rs = stmt.executeQuery("select * from projects where id like'%"+tID.getText()+"%' and capacity like '%"+tCapacity.getText()+"%'and location like '%"+tLocation.getText()+"%' and year like '%"+tYear.getText()+"%' and type_of_projects like '%"+tType.getText()+"%'  and name like '%"+tName.getText()+"%' and owner like '%"+tOwner.getText()+"%' and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%' order by id asc");
EntityProject d=new EntityProject();


    while (rs.next()) {
        if (i == 0) {flagempty=1;
            prTable.getItems().clear();
        }
        i++;

        d = new EntityProject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

        prTable.getItems().add(d);

    }

}else if (tDateMan.getValue()!=null&&(tStore.getSelectionModel().isEmpty())){        String date = tDateMan.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
//and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'
            ResultSet rs = stmt.executeQuery("select * from projects where id like'%"+tID.getText()+"%' and capacity like '%"+tCapacity.getText()+"%'and location like '%"+tLocation.getText()+"%' and year like '%"+tYear.getText()+"%' and type_of_projects like '%"+tType.getText()+"%'  and name like '%"+tName.getText()+"%' and owner like '%"+tOwner.getText()+"%' and DATE_MANUFACTORING='"+date+"' order by id asc");
            EntityProject d=new EntityProject();


            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    prTable.getItems().clear();
                }
                i++;

                d = new EntityProject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

                prTable.getItems().add(d);

            }

        }
        else if (tDateMan.getValue()!=null&&!(tStore.getSelectionModel().isEmpty())){        String date = tDateMan.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
//and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'
            ResultSet rs = stmt.executeQuery("select * from projects where id like'%"+tID.getText()+"%' and capacity like '%"+tCapacity.getText()+"%'and location like '%"+tLocation.getText()+"%' and year like '%"+tYear.getText()+"%' and type_of_projects like '%"+tType.getText()+"%'  and name like '%"+tName.getText()+"%' and owner like '%"+tOwner.getText()+"%' and DATE_MANUFACTORING='"+date+"' and sid="+tStore.getSelectionModel().getSelectedItem().toString()+" order by id asc");
            EntityProject d=new EntityProject();


            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    prTable.getItems().clear();
                }
                i++;

                d = new EntityProject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

                prTable.getItems().add(d);

            }

        }
        else if (tDateMan.getValue()==null&&(tStore.getSelectionModel().isEmpty())){
//and sid like '%"+tStore.getSelectionModel().getSelectedItem().toString()+"%'
            ResultSet rs = stmt.executeQuery("select * from projects where id like'%"+tID.getText()+"%' and capacity like '%"+tCapacity.getText()+"%'and location like '%"+tLocation.getText()+"%' and year like '%"+tYear.getText()+"%' and type_of_projects like '%"+tType.getText()+"%'  and name like '%"+tName.getText()+"%' and owner like '%"+tOwner.getText()+"%' order by id asc");
            EntityProject d=new EntityProject();


            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    prTable.getItems().clear();
                }
                i++;

                d = new EntityProject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));

                prTable.getItems().add(d);

            }

        }
        con.close();
        if(flagempty==0){
            prTable.getItems().clear();
        }
    }

    catch (Exception exx) {
        System.out.println(exx);
    }
    }
}
