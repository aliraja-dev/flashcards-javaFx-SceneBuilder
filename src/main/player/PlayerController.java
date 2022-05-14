package main.player;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PlayerController {
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void switchToMain(ActionEvent event) throws Exception {
        System.out.println("Switch to main");
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditor(ActionEvent event) throws Exception {
        System.out.println("Switch to editor");
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/editor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void addCardToDeck(ActionEvent event) throws Exception {
        // todo implement add card to deck
        System.out.println("Switch to add new card");
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/editor.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
