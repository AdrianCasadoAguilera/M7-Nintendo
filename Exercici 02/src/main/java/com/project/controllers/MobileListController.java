package com.project.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MobileListController {

    @FXML
    private VBox box = new VBox();

    public void clear(){
        box.getChildren().clear();
    }

    public void add(Parent node){
        this.box.getChildren().add(node);
    }
}
