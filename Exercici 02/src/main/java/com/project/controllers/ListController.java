package com.project.controllers;

import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class ListController {
    
    @FXML
    private ImageView photo = new ImageView();

    @FXML
    private Text text = new Text();

    @FXML
    public void setPhoto(URL photoURL){
        this.photo.setImage(new Image(photoURL.toString()));     
    }

    @FXML
    public void setText(String txt){
        this.text.setText(txt);
    }
}
