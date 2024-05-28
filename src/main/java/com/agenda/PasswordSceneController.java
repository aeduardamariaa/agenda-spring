package com.agenda;

import java.net.URL;

import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;

import com.agenda.model.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class PasswordSceneController {

    private UserData currenUser;
    public UserData getCurrenUser() {
        return currenUser;
    }
    public void setCurrenUser(UserData currenUser) {
        this.currenUser = currenUser;
    }

    public static Scene CreateScene(UserData user) throws Exception
    {
        URL sceneUrl = PasswordSceneController.class.getResource("password-scene.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);

        Scene scene = new Scene(loader.load());

        PasswordSceneController controller = loader.getController();
        controller.setCurrenUser(user);
        
        return scene;
    }
    
    
    @FXML
    protected Button btSave;
    @FXML
    protected PasswordField pfPass;
    @FXML
    protected PasswordField pfPass1;

    @FXML
    protected void submit(ActionEvent e) throws Exception {

        if (pfPass.getText().equals(pfPass1.getText())) {
            UserData user = getCurrenUser();

            Session session = HibernateUtil.getSessionFactory().getCurrentSession();

            Transaction transaction = session.beginTransaction();

            Query query = session.createSQLQuery(
            "update UserData set Password = :pass, IsRandPass = 0 where id = :id"
            );

            query.setParameter("pass", pfPass.getText());
            query.setParameter("id", user.getId());
            query.executeUpdate();
            transaction.commit();

            Stage crrStage = (Stage)btSave
            .getScene().getWindow();
            crrStage.close();

            Stage stage = new Stage();
            Scene scene = HomeSceneController.CreateScene(currenUser);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }else{ 
            return;
        }
    }
}
