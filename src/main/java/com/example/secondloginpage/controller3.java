package com.example.secondloginpage;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class controller3 implements Initializable {
    @FXML
    private AnchorPane slideq;
    @FXML
    private AnchorPane Ftables;


    @FXML
    private StackPane stackP;
    @FXML
    private ImageView projV;
    @FXML
    private ImageView imageV;
    @FXML
    private Button bt;
    @FXML
    private Label header;
    @FXML
    private Stage stage;
    private Scene scene;


    private Parent root;

public  void mainPa(ActionEvent e) throws IOException {
  RunMainPage();
    Image image=new Image(getClass().getResourceAsStream("PalCo.gif"));

    imageV.setImage(image);
    imageV.setFitWidth(440);
    imageV.setFitHeight(100);
    imageV.setLayoutX(496);
    imageV.setLayoutY(-1);
}

public  void RunMainPage() throws IOException {

    Parent root = FXMLLoader.load(getClass().getResource("imageSlider.fxml"));
    stackP.getChildren().removeAll();
    stackP.getChildren().setAll(root);
    usertxt.setText("Welcome "+Info);

}
    public void initialize(URL url, ResourceBundle resourceBundle) {

        prevImg.setScaleX(0.85);
        prevImg.setScaleY(0.85);
        temp.setScaleX(0.85);
        temp.setScaleY(0.85);
        nextImg.setScaleX(0.85);
        nextImg.setScaleY(0.85);

        DropShadow imgsDropShadow= (DropShadow) img.getEffect();

        nextImg.setEffect(imgsDropShadow);
        prevImg.setEffect(imgsDropShadow);
        temp.setEffect(imgsDropShadow);

        System.out.println("test intial");
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(slideq);

        slide.setToX(2);
        slide.setToY(0);
        slide.play();

    }












    public void empl(ActionEvent s) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Femployee.fxml"));
        stackP.getChildren().removeAll();
        stackP.getChildren().setAll(root);
        Image image=new Image(getClass().getResourceAsStream("Emply.gif"));

        imageV.setImage(image);
        imageV.setFitWidth(292);
        imageV.setFitHeight(99);
        imageV.setLayoutX(550);
        imageV.setLayoutY(-2);



    }

public void SingOut(ActionEvent s) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
    stage=(Stage)((Node)s.getSource()).getScene().getWindow();
    scene=new Scene(root);
    stage.setScene(scene);
    stage.show();

}
    public void Proj(ActionEvent s) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Ptable.fxml"));
        stackP.getChildren().removeAll();
        stackP.getChildren().setAll(root);


     /*   TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.5));
        slide.setNode(slideq);

        slide.setToX(0);
        slide.setToY(0);
        slide.play();*/



        Image image=new Image(getClass().getResourceAsStream("pr.gif"));

        imageV.setImage(image);
        imageV.setFitWidth(292);
        imageV.setFitHeight(99);
        imageV.setLayoutX(550);
        imageV.setLayoutY(-2);

    }
    public void exp(ActionEvent e) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("table1.fxml"));
        stackP.getChildren().removeAll();
        stackP.getChildren().setAll(root);
        Image image=new Image(getClass().getResourceAsStream("Expens.gif"));

        imageV.setImage(image);
        imageV.setFitWidth(292);
        imageV.setFitHeight(99);
        imageV.setLayoutX(550);
        imageV.setLayoutY(-2);





    }
    public void pur(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Fpurchases.fxml"));
        stackP.getChildren().removeAll();
        stackP.getChildren().setAll(root);
        Image image=new Image(getClass().getResourceAsStream("Purch.gif"));

        imageV.setImage(image);
        imageV.setFitWidth(292);
        imageV.setFitHeight(99);
        imageV.setLayoutX(550);
        imageV.setLayoutY(-2);
    }
    public void Ware(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Fware.fxml"));
        stackP.getChildren().removeAll();
        stackP.getChildren().setAll(root);
        Image image=new Image(getClass().getResourceAsStream("WareH.gif"));

        imageV.setImage(image);
        imageV.setFitWidth(292);
        imageV.setFitHeight(99);
        imageV.setLayoutX(550);
        imageV.setLayoutY(-2);
    }
    public void mainP(MouseEvent e){
     if(slideq.getTranslateX()==2){
         TranslateTransition slide = new TranslateTransition();
         slide.setDuration(Duration.seconds(0.5));
         slide.setNode(slideq);

         slide.setToX(-230);
         slide.setToY(0);
         slide.play();
     }
     else{
         TranslateTransition slide = new TranslateTransition();
         slide.setDuration(Duration.seconds(0.5));
         slide.setNode(slideq);

         slide.setToX(2);
         slide.setToY(0);
         slide.play();
     }
    }

    @FXML
TextField txt1,txt2,txt3;
    @FXML
    public void add  ( ActionEvent e) {


        //step1 load the driver class
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");

            Statement stmt = con.createStatement();
//            String PID, Pname, GID;
//            PID = txt1.getText();
//            Pname = txt2.getText();
//            GID = txt3.getText();


            ResultSet rs = stmt.executeQuery("select fname,lname,ssn from employee ");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + "" + rs.getInt(3));

            }

            con.close();

        }

        catch (Exception exx) {
            System.out.println(exx);
        }





    }














    @FXML
    ImageView img;
    @FXML
    ImageView prevImg;
    @FXML
    ImageView nextImg;
    @FXML
    Button btn;
    @FXML
    ImageView temp;
    private TranslateTransition translateNext;
    boolean flag = true;

    @FXML
    GridPane grid;

    private double x,y; //The location of temp image.
    private double wid = 1225;
    private ArrayList<Image> imgArr = new ArrayList<Image>();
    private int currentState;



    @FXML
    private Label usertxt;






        @FXML
        public void leftArrow(MouseEvent e) throws InterruptedException {
            if(flag) {
                flag=!flag;
                TranslateTransition transition = new TranslateTransition();
                transition.setDuration(Duration.seconds(1));
                transition.setNode(img);
                transition.setInterpolator(Interpolator.EASE_BOTH);
                transition.setByX(-550);

                //Scale Transition to the left and minimize the photo.
                ScaleTransition imgScale = new ScaleTransition(Duration.seconds(1), img);
                imgScale.setToX(0.85);
                imgScale.setToY(0.85);
                //imgScale.setDelay(Duration.seconds(0.05));
                imgScale.setInterpolator(Interpolator.EASE_BOTH);


                //playing the two animations.
                imgScale.play();
                transition.play();


                //animating prevImg
                TranslateTransition translatePrev = new TranslateTransition(Duration.seconds(1), prevImg);
                translatePrev.setByX(-550);
                ScaleTransition prevScale = new ScaleTransition(Duration.seconds(1), prevImg);
                prevScale.setToX(1);
                prevScale.setToY(1);

                translatePrev.play();
                prevScale.play();

                //animating nextImg
                translateNext = new TranslateTransition(Duration.seconds(1), nextImg);
                translateNext.setByX(-550);
                translateNext.play();

                //animating temp
                TranslateTransition translateTemp = new TranslateTransition(Duration.seconds(1), temp);
                translateTemp.setByX(-550);
                translateTemp.play();

                translateTemp.setOnFinished(es ->{
                    flag=!flag;
                });
                switching();


            }



        }

        private void switchToRight(){
            ImageView t = prevImg;
            prevImg = img;
            img = nextImg;
            nextImg = temp;
            temp = t;



        }
    String Info=new String();
    public void moveInfo(String info){
        Info=info;
        usertxt.setText("Welcome "+info);


    }
        private void switching() {
            ImageView t = img;
            img = prevImg;
            prevImg = temp;
            temp = nextImg;
            nextImg = t;

            translateNext.setOnFinished(e->{
                temp.setLayoutX(prevImg.getLayoutX()+550);
                temp.setLayoutY(115);

            });




            //temp.setImage(new Image(String.valueOf(getClass().getResource("/ICONS/right-arrow.png"))));



        }

        public void selectPhotos(MouseEvent mouseEvent) {
            FileChooser fileChooser = new FileChooser();
            Stage temp = new Stage();
            File f = fileChooser.showOpenDialog(temp);
            System.out.println(String.valueOf(f.toURI()));
            if(f == null){
                System.out.println("Please Choose a File !!");
            }else{
                Image imgToAddArr = new Image(String.valueOf(f.toURI()));
                imgArr.add(imgToAddArr);

                if(imgArr.size()==1)
                    img.setImage(imgArr.get(0));
                else if (imgArr.size()==2)
                    prevImg.setImage(imgArr.get(1));


            }
        }

        public void rightArrow(MouseEvent mouseEvent) {

            TranslateTransition transition = new TranslateTransition();
            transition.setDuration(Duration.seconds(1));
            transition.setNode(img);
            transition.setInterpolator(Interpolator.EASE_BOTH);
            transition.setByX(550);

            //Scale Transition to the left and minimize the photo.
            ScaleTransition imgScale = new ScaleTransition(Duration.seconds(1) , img);
            imgScale.setToX(0.85);
            imgScale.setToY(0.85);
            //imgScale.setDelay(Duration.seconds(0.05));
            imgScale.setInterpolator(Interpolator.EASE_BOTH);


            //playing the two animations.
            imgScale.play();
            transition.play();


            //animating prevImg
            TranslateTransition translatePrev = new TranslateTransition(Duration.seconds(1) ,prevImg);
            translatePrev.setByX(550);
            ScaleTransition prevScale = new ScaleTransition(Duration.seconds(1),nextImg);
            prevScale.setToX(1);
            prevScale.setToY(1);

            translatePrev.play();
            prevScale.play();

            //animating nextImg
            TranslateTransition translateNext = new TranslateTransition(Duration.seconds(1) , nextImg);
            translateNext.setByX(550);
            translateNext.play();

            //animating temp
            TranslateTransition translateTemp = new TranslateTransition(Duration.seconds(1),temp);
            translateTemp.setByX(550);
            translateTemp.play();

            switchToRight();
        }
        int i = 0;
        public void addCard(ActionEvent actionEvent) throws IOException {
            Card pur = new Card(getClass().getResource("/ICONS/MSI.jpg"));
            grid.add(new Card(getClass().getResource("/ICONS/right-arrow.png")).getParent() , i++ , 0);
        }


    class Card extends FXMLLoader{
        Parent root;
        Card(URL url) throws IOException {
            super(Card.class.getResource("/Card.fxml"));
            Image s = new Image(String.valueOf(url));
            root = this.load();

            Controller cont = this.getController();
            cont.setImage(s);

        }

        public Parent getParent()
        {
            return root;
        }



    }







    @FXML
    private AnchorPane out;

public void out(ActionEvent s) throws IOException {
   out.setVisible(true);


}
public void back(ActionEvent e){
    out.setVisible(false);
    out.setLayoutX(0);
    out.setLayoutY(0);


}
public void back1(ActionEvent e) throws IOException {
    Parent root1= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
    stage=(Stage)((Node)e.getSource()).getScene().getWindow();
    scene=new Scene(root1);
    stage.setScene(scene);
    stage.show();
}

    @FXML
    private AnchorPane profile;
public void scaleChange(MouseEvent e) throws IOException {
    FXMLLoader loader=new FXMLLoader(getClass().getResource("Fprofile.fxml"));
    root=loader.load();
    ProfileController r=loader.getController();
    r.moveInfo(Info);
   // Parent root = FXMLLoader.load(getClass().getResource("Fprofile.fxml"));
    stackP.getChildren().removeAll();
    stackP.getChildren().setAll(root);
    Image image=new Image(getClass().getResourceAsStream("Profile.gif"));

    imageV.setImage(image);
    imageV.setFitWidth(292);
    imageV.setFitHeight(99);
    imageV.setLayoutX(630);
    imageV.setLayoutY(-2);


}
public void report (MouseEvent s){
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@10.211.55.3:1521:xe", "Yazan", "Yazan123");

        Statement stmt = con.createStatement();

        InputStream input=new FileInputStream(new File("pur.jrxml"));
        JasperDesign jd= JRXmlLoader.load(input);
        JasperReport jr= JasperCompileManager.compileReport(jd);
        JasperPrint jp = JasperFillManager.fillReport(jr,null,con);
        OutputStream os=new FileOutputStream(new File("pur1.pdf"));
        JasperExportManager.exportReportToPdfStream(jp,os);

        os.close();
        input.close();

        InputStream input1=new FileInputStream(new File("amountReport1.jrxml"));
        JasperDesign jd1= JRXmlLoader.load(input1);
        JasperReport jr1= JasperCompileManager.compileReport(jd1);
        JasperPrint jp1 = JasperFillManager.fillReport(jr1,null,con);
        OutputStream os1=new FileOutputStream(new File("Amount.pdf"));
        JasperExportManager.exportReportToPdfStream(jp1,os1);

        os1.close();
        input1.close();
        
        //Manager_Amounts

        con.close();

    }

    catch (Exception exx) {
        System.out.println(exx);
    }

}


}
