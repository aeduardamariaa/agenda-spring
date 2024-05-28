package com.agenda;

import java.net.URL;

import com.agenda.model.UserData;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;


public class RegisterSceneController {
    private UserData currenUser;
    public UserData getCurrenUser() {
        return currenUser;
    }
    public void setCurrenUser(UserData currenUser) {
        this.currenUser = currenUser;
    }

    public static Scene CreateScene(UserData user) throws Exception
    {
        URL sceneUrl = RegisterSceneController.class.getResource("register-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);

        Scene scene = new Scene(loader.load());

        RegisterSceneController controller = loader.getController();
        controller.setCurrenUser(user);
        
        return scene;
    }

}
