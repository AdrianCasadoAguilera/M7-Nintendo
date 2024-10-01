package com.project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {

    @FXML
    private VBox listBox = new VBox();
    @FXML
    private VBox contentBox = new VBox();

    @FXML
    private ChoiceBox<String> choice = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choice.getItems().addAll(new String[]{"Personatges","Jocs","Consoles"});
        choice.setValue("Personatges");

        choice.setOnAction((evt)->{
            putList(choice.getValue());
        });

        putList(choice.getValue());
    }

    @FXML
    public void putList(String listToShow){
        JSONArray itemsJson;

        try{

            // Llegim el JSON en un String amb StringBuilder
            StringBuilder jsonTxt = new StringBuilder();
            Scanner scan = new Scanner(new InputStreamReader(new FileInputStream(new File("./src/main/resources/assets/data/"+listToShow.toLowerCase()+".json"))));
            while(scan.hasNext()){
                jsonTxt.append(scan.nextLine());
            }

            // Convertim el JSON a un objecte JSONArray
            itemsJson = new JSONArray(jsonTxt.toString());

            listBox.getChildren().clear();

            for(Object itemObj : itemsJson){
                // Carreguem el FXML com a sub plantilla
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/layouts/listLayout.fxml"));
                Parent itemTemplate = loader.load();
                ListController listController = loader.getController();

                // Afegim un listener a l'item que hem carregat per detectar la seva selecció
                itemTemplate.setOnMouseClicked((evt)->{
                    itemSelected(itemTemplate,(JSONObject)itemObj);
                });

                listController.setText(((JSONObject)itemObj).getString("nom"));
                URL characterImage = getClass().getResource("/assets/images/"+((JSONObject)itemObj).getString("imatge"));
                listController.setPhoto(characterImage);

                listBox.getChildren().add(itemTemplate);
            }

            scan.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    private void itemSelected(Parent item, JSONObject itemInfo){
        // Eliminem la selecció de tots els elements
        for(Node elem : listBox.getChildren()){
            elem.setStyle("-fx-background-color: white;");
        }

        // Seleccionem l'element
        item.setStyle("-fx-background-color: lightgray;");

        contentBox.getChildren().clear();

        try{
            if(choice.getValue()=="Personatges"){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/layouts/contentLayouts/charInfoLayout.fxml"));
                Parent charLayout = loader.load();
                CharInfoController charController = loader.getController();

                charController.setContent(itemInfo);

                contentBox.getChildren().add(charLayout);
            }else if(choice.getValue()=="Jocs"){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/layouts/contentLayouts/gameInfoLayout.fxml"));
                Parent gameLayout = loader.load();
                GameInfoController gameController = loader.getController();

                gameController.setContent(itemInfo);

                contentBox.getChildren().add(gameLayout);
            }else if(choice.getValue()=="Consoles"){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/assets/layouts/contentLayouts/consoleInfoLayout.fxml"));
                Parent consoleLayout = loader.load();
                ConsoleInfoController consoleController = loader.getController();

                consoleController.setContent(itemInfo);

                contentBox.getChildren().add(consoleLayout);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
    }
}

