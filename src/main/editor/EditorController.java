package main.editor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditorController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void backToDeck(ActionEvent event) {
        // todo implement back to deck from the editor
    }

    public void switchToPlayer(ActionEvent event) throws Exception {
        System.out.println("Switch to player");
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/player.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void saveCard(ActionEvent event) {
        // todo implement save card
        System.out.println("Save Card and return to all cards");
    }
}
