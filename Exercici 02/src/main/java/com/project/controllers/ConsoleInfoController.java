package com.project.controllers;

import java.net.URL;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ConsoleInfoController {
    @FXML
    private ImageView image = new ImageView();
    @FXML 
    private Text title = new Text();
    @FXML
    private Label date = new Label();
    @FXML
    private Rectangle color = new Rectangle();
    @FXML
    private Label processor = new Label();
    @FXML
    private Label soldUnits = new Label(); 

    public void setContent(JSONObject console){
        URL imageURL = getClass().getResource("/assets/images/"+console.getString("imatge"));
        image.setImage(new Image(imageURL.toString()));
    
        title.setText(console.getString("nom"));
        date.setText(console.getString("data"));
        color.setStyle("-fx-fill: "+console.getString("color"));
        processor.setText(console.getString("procesador"));
        soldUnits.setText(String.valueOf(console.getInt("venudes")));
    }
}
