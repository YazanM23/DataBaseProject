package com.example.secondloginpage;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController1 implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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



    }

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