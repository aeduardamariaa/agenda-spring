package com.agenda;

import java.text.SimpleDateFormat;
import java.util.Optional;

import com.agenda.model.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ListCellUtil extends ListCell<Event>{
    private HBox content;
    private VBox vBox;
    private Label nameLabel;
    private Label dateLabel;
    private Label descriptionLabel;
    private Button deleteButton;

    public ListCellUtil() {
        super();
        nameLabel = new Label();
        dateLabel = new Label();
        descriptionLabel = new Label();
        deleteButton = new Button("Delete");

        deleteButton.setOnAction(event -> {
            Event eventItem = getItem();
            if (eventItem != null) {
                // Exibir caixa de confirmação
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Exclusão");
                alert.setHeaderText("Excluir evento");
                alert.setContentText("Tem certeza que deseja excluir este evento?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {

                    removeEventFromDatabase(eventItem);
                    // Remover da ListView
                    getListView().getItems().remove(eventItem);
                }
            }
        });

        vBox = new VBox(nameLabel, dateLabel, descriptionLabel);
        content = new HBox(vBox, deleteButton);
        HBox.setHgrow(vBox, Priority.ALWAYS);
    }

    @Override
    protected void updateItem(Event item, boolean empty) {
        super.updateItem(item, empty);
        if (item != null && !empty) {
            nameLabel.setText(item.getName());
            dateLabel.setText(new SimpleDateFormat("dd 'de' MMMM 'de' yyyy").format(item.getDate()));
            descriptionLabel.setText(item.getDescription());
            setGraphic(content);
        } else {
            setGraphic(null);
        }
    }

    private void removeEventFromDatabase(Event event) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();

        session.delete(event);
        transaction.commit();
    }
}
