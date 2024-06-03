package com.agenda;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import com.agenda.model.Event;
import com.agenda.model.UserData;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

        Scene scene = new Scene(loader.load());

        HomeSceneController controller = loader.getController();
        controller.setCurrenUser(user);
        controller.data();
        
        return scene;
    }

    @FXML
    protected Button btNewEvent;
    @FXML
    protected ListView<String> listView;
    
    @FXML
    protected void submit(ActionEvent e) throws Exception {

        Stage crrStage = (Stage)btNewEvent
            .getScene().getWindow();
            crrStage.close();

        Stage stage = new Stage();
        Scene scene = RegisterSceneController.CreateScene(getCurrenUser());
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    protected void data() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(
            "from Event where IdUser = :user "
        );

        query.setParameter("user", currenUser.getId());
        List<Event> events = query.list();

        ObservableList<String> eventList = FXCollections.observableArrayList();

        for (Event event : events) {

            String details = event.getName() + "\n" +
                            new SimpleDateFormat("dd 'de' MMMM 'de' yyyy").format(event.getDate())+ "\n" +
                            event.getDescription();
                            
            eventList.add(details);
        }
        
        listView.setItems(eventList);

        transaction.commit();
    }
}