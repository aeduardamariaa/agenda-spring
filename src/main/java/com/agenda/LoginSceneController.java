package com.agenda;

import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
// import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginSceneController {
    // Vamos fazer uma função CreateScene que irá
    // criar a cena apartir de um FXMLLoader carregando
    // o .fxml.
    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = LoginSceneController.class.getResource("login-scene.fxml");

        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }
    // Variáveis que representam os componentes
    // Note que id/field devem ser iguais ao nome
    // que aparece aqui.
    @FXML
    protected Button btLogin;
    @FXML
    protected TextField tfLogin;
    @FXML
    protected PasswordField pfPass;
    @FXML
    protected CheckBox cbPass;
    // Evento submit executado ao rodar a aplicação.
    @FXML
    protected void submit(ActionEvent e) throws Exception {
        Authentification auth = Authentification.tryLogin(
            tfLogin.getText(), pfPass.getText()
        );

        if (!auth.userExists()) {
            Alert alert = new Alert(
                AlertType.ERROR,
                "Usuário inexistente.",
                ButtonType.OK
            );
            alert.showAndWait();
            return;
        }
        if (auth.getUser() == null) {
                Alert alert = new Alert(
                AlertType.ERROR,
                "Senha incorreta.",
                ButtonType.OK
            );
            alert.showAndWait();
            return;
        }
        // // Fechando o login
        // Stage crrStage = (Stage)btLogin
        // .getScene().getWindow();
        // crrStage.close();
        // // Abrindo a tela principal
        // Stage stage = new Stage();
        // Scene scene = MainSceneController.CreateScene();
        // stage.setScene(scene);
        // stage.show();
    }
}

