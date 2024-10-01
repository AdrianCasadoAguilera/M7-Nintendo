package com.project.controllers;

import java.net.URL;

import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameInfoController {
    @FXML
    private ImageView image = new ImageView();
    @FXML
    private Label title = new Label();
    @FXML
    private Label year = new Label();
    @FXML
    private Label type = new Label();
    @FXML
    private Label description = new Label();

    public void setContent(JSONObject game){
        try{
            URL imageURL = getClass().getResource("/assets/images/"+game.getString("imatge"));
            image.setImage(new Image(imageURL.toString()));
            title.setText(game.getString("nom"));
            year.setText(String.valueOf(game.getInt("any")));
            type.setText(game.getString("tipus"));
            description.setText(game.getString("descripcio"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
