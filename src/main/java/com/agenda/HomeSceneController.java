package com.agenda;
import java.net.URL;

import com.agenda.model.UserData;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeSceneController {

    private UserData currenUser;
    public UserData getCurrenUser() {
        return currenUser;
    }
    public void setCurrenUser(UserData currenUser) {
        this.currenUser = currenUser;
    }

    public static Scene CreateScene(UserData user) throws Exception {
        URL sceneUrl = HomeSceneController.class.getResource("home-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);

        //teste
        Scene scene = new Scene(loader.load());
        // fim-teste

        HomeSceneController controller = loader.getController();
        controller.setCurrenUser(user);
        
        return scene;
    }

    @FXML
    protected Button btNewEvent;
    
    @FXML
    protected void submit(ActionEvent e) throws Exception {

        Stage crrStage = (Stage)btNewEvent
            .getScene().getWindow();
            crrStage.close();

        Stage stage = new Stage();
        Scene scene = RegisterSceneController.CreateScene(getCurrenUser());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
