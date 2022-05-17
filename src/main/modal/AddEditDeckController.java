package main.modal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Deck;
import services.DataService;

public class AddEditDeckController {
    @FXML
    TextField addTitleField;
    Stage primaryStage;
    Stage modal;
    @FXML
    Button saveBtn;
    @FXML
    Button cancelBtn;

    public void onSave(ActionEvent event) {
        System.out.println("Saving");
        DataService ds = DataService.getInstance();
        Deck deck = new Deck(addTitleField.getText());
        ds.setDecks(deck);
        modal.close();
        // ACcess Deck Modal and do the title edit or add
        // close this window and revert to main window.
    }

    public void onCancel(ActionEvent event) {
        System.out.println("Cancelling");
        // todo implement cancel
        modal.close();
    }

    public String getDeckName() {
        return addTitleField.getText();
    }

    public void getPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void initStage(Stage modal) {
        this.modal = modal;
    }
}
