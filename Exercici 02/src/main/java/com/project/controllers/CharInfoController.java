package com.project.controllers;

import java.net.URL;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class CharInfoController {
    @FXML
    private ImageView image = new ImageView();
    @FXML
    private Text title = new Text();
    @FXML
    private Rectangle color = new Rectangle();
    @FXML 
    private Label game = new Label();

    public void setContent(JSONObject info){
        URL imageURL = getClass().getResource("/assets/images/"+info.getString("imatge"));
        this.image.setImage(new Image(imageURL.toString()));

        this.title.setText(info.getString("nom"));

        this.color.setStyle("-fx-fill: "+info.getString("color"));;

        this.game.setText(info.getString("nom_del_videojoc"));
    }
}
