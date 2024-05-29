package com.agenda;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.agenda.model.Event;
import com.agenda.model.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;


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

    @FXML
    protected Button btSave;
    @FXML
    protected TextField tfName;
    @FXML
    protected DatePicker pkDate;
    @FXML
    protected TextArea taDescription;

    @FXML
    protected void submit (ActionEvent e) throws Exception {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Event newEvent = new Event();
        Date date = Date.from(
            pkDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        newEvent.setDate(date);
        newEvent.setName(tfName.getText());
        newEvent.setDescription(taDescription.getText());
        newEvent.setUser(getCurrenUser());
        newEvent.setDone(false);
        session.save(newEvent);

        transaction.commit();

        Stage crrStage = (Stage)btSave
            .getScene().getWindow();
            crrStage.close();

        Stage stage = new Stage();
        Scene scene = HomeSceneController.CreateScene(currenUser);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
    }
}
