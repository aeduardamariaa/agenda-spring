package com.agenda;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class PasswordSceneController {
    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = PasswordSceneController.class.getResource("password-scene.fxml");

        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }
    
    @FXML
    protected void submit(ActionEvent e) throws Exception {}
}
