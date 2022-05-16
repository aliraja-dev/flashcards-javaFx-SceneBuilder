package main.modal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddEditDeckController {
    @FXML
    TextField addTitleField;
    Stage primaryStage;

    public void onSave(ActionEvent event) {
        System.out.println("Saving");
        // todo implement add new deck to Decks object
        // todo implement save to file
        // todo implement close window
        System.out.println(event.getSource().toString());
        System.out.println(addTitleField.getText());
    }

    public void onCancel(ActionEvent event) {
        System.out.println("Cancelling");
        // todo implement cancel
    }

    public String getDeckName() {
        return addTitleField.getText();
    }

    public void getPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
