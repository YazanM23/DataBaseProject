package com.example.secondloginpage;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ResourceBundle;
public class EmployeeController implements Initializable {
    @FXML
    private AnchorPane EmpSilde;

    public void Emove(ActionEvent e){
        if(tableEmp.getTranslateY()==-90){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(tableEmp);

            slide.setToX(211);
            slide.setToY(0);
            slide.play();


            EmpSilde.setVisible(true);

        }
        else{
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.5));
            slide.setNode(tableEmp);

            slide.setToX(211);
            slide.setToY(-90);
            slide.play();
            EmpSilde.setVisible(false);
        }

    }






    @FXML
    private TableColumn<EntityEmployee, String> Fnamecol;

    @FXML
    private TableColumn<EntityEmployee, String> Lnamecol;

    @FXML
    private TableColumn<EntityEmployee,String > SSNcol;

    @FXML
    private Button add;

    @FXML
    private TableColumn<EntityEmployee , String> agecol;

    @FXML
    private TableColumn<EntityEmployee, String> blodcol;

    @FXML
    private TableColumn<EntityEmployee, String> edatecol;

    @FXML
    private TableColumn<EntityEmployee, String> phonecol;

    @FXML
    private TableColumn<EntityEmployee, String> salarycol;

    @FXML
    private TableColumn<EntityEmployee, String> sdatecol;
    @FXML
    private TableColumn<EntityEmployee, String> typecol;
    @FXML
    private TableColumn<EntityEmployee, String> gendercol;
    @FXML
    private TableColumn<EntityEmployee, String> CityColumn;

    @FXML
    private TextField tAge;

    @FXML
    private TextField tBlod;

    @FXML
    private TextField tCity;

    @FXML
    private DatePicker tEDate;

    @FXML
    private TextField tFName;

    @FXML
    private TextField tGender;

    @FXML
    private TextField tLName;

    @FXML
    private TextField tPNumber;

    @FXML
    private DatePicker tSDate;

    @FXML
    private TextField tSSN;

    @FXML
    private TextField tSalary;

    @FXML
    private TableView<EntityEmployee> tableEmp;

    public void onTable(){
        SSNcol.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        Fnamecol.setCellValueFactory(new PropertyValueFactory<>("FName"));
        Lnamecol.setCellValueFactory(new PropertyValueFactory<>("LName"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("PNumber"));
        sdatecol.setCellValueFactory(new PropertyValueFactory<>("SDate"));
        edatecol.setCellValueFactory(new PropertyValueFactory<>("EDate"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("Age"));
        salarycol.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        blodcol.setCellValueFactory(new PropertyValueFactory<>("Blod"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("City"));


/*

  DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MMM-dd hh:mm:ss");
                LocalDateTime now = LocalDateTime.now();

                String date=new String(rs.getDate(6).toString());
                String date1=new String(now.toLocalDate().toString());

                if(date.equals(date1)){
                   System.out.println( rs.getString(12));
                    if(rs.getString(12).equals("M") || rs.getString(12).equals("m")||rs.getString(12).equals("Male")||rs.getString(12).equals("male")){
                    Notifications.create()
                            .title("Warning")
                            .text("Employee SSN = "+rs.getString(3)+" his Contract is Ended")
                            .showWarning();

                    }
                    else{
                        Notifications.create()
                                .title("Warning")
                                .text("Employee SSN = "+rs.getString(3)+" her Contract is Ended")
                                .showWarning();
                    }




                }




 */


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();


            int i=1;
            ResultSet rs = stmt.executeQuery("select * from employee order by fname asc");
            while (rs.next()) {
                EntityEmployee r=new EntityEmployee(rs.getString(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
                tableEmp.getItems().add(r);
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            LocalDateTime now = LocalDateTime.now();
            ResultSet rs1=stmt.executeQuery("select * from employee where econtract='"+now.toLocalDate().format(dtf)+"'");


      rs1.next();
                if(rs1.getDate(6).toLocalDate().equals(now.toLocalDate())){

                    if(rs1.getString(12).equals("M") || rs1.getString(12).equals("m")||rs1.getString(12).equals("Male")||rs1.getString(12).equals("male")) {

                        Notifications.create()
                                .title("Warning")
                                .text("Employee SSN = " + rs1.getString(3) + " His contract is ended")
                                .showWarning();


                    }
                    else{
                        Notifications.create()
                                .title("Warning")
                                .text("Employee SSN = " + rs1.getString(3) + " Her contract is ended")
                                .showWarning();

                    }
                }




            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }

    }
    @FXML
    private ChoiceBox<String> choicebox;


    private String[] e={"Manager","Worker","Engineer"};
    @FXML
    private Hyperlink getrow;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

      onTable();
      choicebox.getItems().addAll(e);

    }


    public void Delete(ActionEvent e){
        String s=new String(tableEmp.getSelectionModel().getSelectedItem().getSSN());

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();



            stmt.executeUpdate("delete from employee where ssn="+s+" ");


            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }
tableEmp.getItems().clear();
        onTable();

    }
    public void Add(ActionEvent e){
        Validation v=new Validation();
        if(!v.validationPhoneNumber(tPNumber.getText())||(tPNumber.getText().isEmpty())){
            tPNumber.setStyle("-fx-border-color: red ; ");
        }
        else{
            tPNumber.setStyle("-fx-border-color: #101152 ; ");
        }

        if(!v.validationSSN(tSSN.getText())||(tSSN.getText().isEmpty())){
            tSSN.setStyle("-fx-border-color: red ; ");
        }
        else{
            tSSN.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationName(tFName.getText())||(tFName.getText().isEmpty())){
            tFName.setStyle("-fx-border-color: red ; ");
        }
        else{
            tFName.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationName(tLName.getText())||(tLName.getText().isEmpty())){
            tLName.setStyle("-fx-border-color: red ; ");
        }
        else{
            tLName.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationName(tCity.getText())||(tCity.getText().isEmpty())){
            tCity.setStyle("-fx-border-color: red ; ");
        }
        else{
            tCity.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationBlod(tBlod.getText())||(tBlod.getText().isEmpty())){
            tBlod.setStyle("-fx-border-color: red ; ");
        }
        else{
            tBlod.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationNumber(tAge.getText())||(tAge.getText().isEmpty())){
            tAge.setStyle("-fx-border-color: red ; ");
        }
        else{
            tAge.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationNumber(tSalary.getText())||(tSalary.getText().isEmpty())){
            tSalary.setStyle("-fx-border-color: red ; ");
        }
        else{
            tSalary.setStyle("-fx-border-color: #101152 ; ");
        }
        if(!v.validationGender(tGender.getText())||(tGender.getText().isEmpty())){
            tGender.setStyle("-fx-border-color: red ; ");
        }
        else{
            tGender.setStyle("-fx-border-color: #101152 ; ");
        }
        if((tEDate.toString().isEmpty())){
            tEDate.setStyle("-fx-border-color: red ; ");
        }
        else{
            tEDate.setStyle("-fx-border-color: #101152 ; ");
        }
        if((tSDate.toString().isEmpty())){
            tSDate.setStyle("-fx-border-color: red ; ");
        }
        else{
            tSDate.setStyle("-fx-border-color: #101152 ; ");
        }
        if((choicebox.getSelectionModel().isEmpty())){
            choicebox.setStyle("-fx-border-color: red ; ");
        }
        else{
            choicebox.setStyle("-fx-border-color: #101152 ; ");
        }



        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();
            String date=new String();
            if(tEDate.getValue()!=null) {
                 date = tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            }
            String date1=new String();
            if(tEDate.getValue()!=null) {
                 date1 = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            }


            stmt.executeUpdate("insert into employee (ssn,fname,lname,jop_type,age,city,salary,scontract,econtract,blod_tybe,gender,phone_number)" +
                    "values ("+Integer.parseInt(tSSN.getText())+",'"+tFName.getText()+"',"+"'"+tLName.getText()+"','"+choicebox.getSelectionModel().getSelectedItem()+"',"+Integer.parseInt(tAge.getText())+",'"+tCity.getText()+"',"+Integer.parseInt(tSalary.getText())+",'"+ date1+"','"+date+"','"+tBlod.getText()+"','"+tGender.getText()+"',"+Integer.parseInt(tPNumber.getText())+")");

            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }
        tableEmp.getItems().clear();
        onTable();


    }
    @FXML
    private AnchorPane out;
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;

    public void Clear(ActionEvent e){
        tSSN.setText("");
        tLName.setText("");
        tFName.setText("");
        tCity.setText("");
        choicebox.setValue("");
        tAge.setText("");
        tGender.setText("");
        tSalary.setText("");
        tBlod.setText("");
        tPNumber.setText("");
        tableEmp.getItems().clear();
        tSDate.getEditor().clear();
        tEDate.getEditor().clear();
        tSDate.setValue(null);
        tEDate.setValue(null);
onTable();
    }
    public void getRow(ActionEvent e){

        tSSN.setText(tableEmp.getSelectionModel().getSelectedItem().getSSN());
        tLName.setText(tableEmp.getSelectionModel().getSelectedItem().getLName());
        tFName.setText(tableEmp.getSelectionModel().getSelectedItem().getFName());
        tCity.setText(tableEmp.getSelectionModel().getSelectedItem().getCity());
        tAge.setText(tableEmp.getSelectionModel().getSelectedItem().getAge());
        tGender.setText(tableEmp.getSelectionModel().getSelectedItem().getGender());
        tSalary.setText(tableEmp.getSelectionModel().getSelectedItem().getSalary());
        tBlod.setText(tableEmp.getSelectionModel().getSelectedItem().getBlod());
        tPNumber.setText(tableEmp.getSelectionModel().getSelectedItem().getPNumber());
        choicebox.setValue(tableEmp.getSelectionModel().getSelectedItem().getType());

        String date =tableEmp.getSelectionModel().getSelectedItem().getEDate();
        if(!date.isEmpty()){
        tEDate.setValue(LocalDate.parse(date,DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));}

        String date1 =tableEmp.getSelectionModel().getSelectedItem().getSDate();
        if(!date1.isEmpty()){
        tSDate.setValue(LocalDate.parse(date1,DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));}
    }
public void Update(ActionEvent e){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
        Statement stmt = con.createStatement();
        String date=new String();
        if(tEDate.getValue()!=null){
         date = tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));}
        String date1=new String();
if(tSDate.getValue()!=null) {
     date1 = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
}
String typejop=new String();
if(!choicebox.getSelectionModel().getSelectedItem().isEmpty()){
    typejop=choicebox.getSelectionModel().getSelectedItem().toString();
}
        String s=tSSN.getText();
        stmt.executeUpdate("update employee set SSN="+tSSN.getText()+",FName='"+tFName.getText()+"',LName='"+tLName.getText()+"',City='"+tCity.getText()+"',scontract='"+date1+"',"+"econtract='"+date+"',jop_type='"+typejop+"',age="+Integer.parseInt(tAge.getText())+",phone_number="+tPNumber.getText()+",blod_tybe='"+tBlod.getText()+"',salary="+tSalary.getText()+",gender='"+tGender.getText()+"'where ssn="+s);

tableEmp.getItems().clear();
onTable();
        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }


}


    public void Search(ActionEvent e){ int flagempty=0;
        SSNcol.setCellValueFactory(new PropertyValueFactory<>("SSN"));
        Fnamecol.setCellValueFactory(new PropertyValueFactory<>("FName"));
        Lnamecol.setCellValueFactory(new PropertyValueFactory<>("LName"));
        phonecol.setCellValueFactory(new PropertyValueFactory<>("PNumber"));
        sdatecol.setCellValueFactory(new PropertyValueFactory<>("SDate"));
        edatecol.setCellValueFactory(new PropertyValueFactory<>("EDate"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("Type"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("Age"));
        salarycol.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        blodcol.setCellValueFactory(new PropertyValueFactory<>("Blod"));
        gendercol.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("City"));
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");
            Statement stmt = con.createStatement();
            int i = 0;
        if(tEDate.getValue()==null && tSDate.getValue()==null&&!(choicebox.getSelectionModel().isEmpty())) {

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%' and jop_type like '%" + choicebox.getSelectionModel().getSelectedItem().toString() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {
                if (i == 0) {flagempty=1;
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }


        } else if (tSDate.getValue()==null && tEDate.getValue()!=null&&!(choicebox.getSelectionModel().isEmpty())) {
            String date = tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%' and jop_type like '%" + choicebox.getSelectionModel().getSelectedItem().toString() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' and econtract='"+date+"' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }


        }
        else if(tSDate.getValue()!=null && tEDate.getValue()==null&&!(choicebox.getSelectionModel().isEmpty())){
            String date = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%' and jop_type like '%" + choicebox.getSelectionModel().getSelectedItem().toString() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' and scontract='"+date+"' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }
        }

        else if(tSDate.getValue()!=null && tEDate.getValue()!=null&&!(choicebox.getSelectionModel().isEmpty())){

            String date = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            String date1= tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%' and jop_type like '%" + choicebox.getSelectionModel().getSelectedItem().toString() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' and scontract='"+date+"' and econtract='"+date1+"' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }
        }
        else if (tSDate.getValue()==null && tEDate.getValue()!=null&&(choicebox.getSelectionModel().isEmpty())) {
            String date = tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' and econtract='"+date+"' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }


        }
        else if(tSDate.getValue()!=null && tEDate.getValue()==null&&(choicebox.getSelectionModel().isEmpty())){
            String date = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' and scontract='"+date+"' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }
        }
        else if(tSDate.getValue()!=null && tEDate.getValue()!=null&&(choicebox.getSelectionModel().isEmpty())){
            String date = tSDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
            String date1= tEDate.getValue().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' and scontract='"+date+"' and econtract='"+date1+"' order by asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }
        }
        else     if(tEDate.getValue()==null && tSDate.getValue()==null&&(choicebox.getSelectionModel().isEmpty())) {

            ResultSet rs = stmt.executeQuery("select * from employee where SSN like '%" + tSSN.getText() + "%' and FName like '%" + tFName.getText() + "%'and LName like '%" + tLName.getText() + "%' and PHONE_NUMBER like '%" + tPNumber.getText() + "%'  and blod_tybe like '%" + tBlod.getText() + "%' and salary like '%" + tSalary.getText() + "%' and age like '%" + tAge.getText() + "%' and city like '%" + tCity.getText() + "%' and gender like '%" + tGender.getText() + "%' order by fname asc");//and gender like '%"+tGender+"%'");//and econtract like'%"+date+"%' and scontract like '%"+date1+"%'");



            while (rs.next()) {flagempty=1;
                if (i == 0) {
                    tableEmp.getItems().clear();
                }
                i++;
                System.out.println(rs.getString(1) + " " + rs.getString(2));
                EntityEmployee r = new EntityEmployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));

                tableEmp.getItems().add(r);

            }


        }
            con.close();
        if(flagempty==0){
            tableEmp.getItems().clear();
        }
        }

        catch (Exception exx) {
            System.out.println(exx);
        }
    }




}
