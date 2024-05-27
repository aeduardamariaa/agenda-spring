package com.agenda;

import java.io.IOException;
import java.net.URL;

import com.agenda.model.UserData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class HomeSceneController {

    private UserData currenUser;
    public UserData getCurrenUser() {
        return currenUser;
    }
    public void setCurrenUser(UserData currenUser) {
        this.currenUser = currenUser;
    }

    public static Scene CreateScene(UserData user) throws IOException {
        URL sceneUrl = HomeSceneController.class.getResource("home-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);

        Scene scene = new Scene(loader.load());

        HomeSceneController controller = loader.getController();
        controller.setCurrenUser(user);
        
        return scene;
    }

    @FXML
    protected void submit(ActionEvent e) throws Exception {

    }

}
