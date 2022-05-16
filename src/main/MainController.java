package main;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.modal.AddEditDeckController;
import main.player.PlayerController;
import models.Deck;
import services.DataService;

public class MainController {
    private Stage primaryStage;
    private Stage stage;
    private Scene scene;
    private DataService dataService;
    private ArrayList<Deck> decks;

    @FXML
    private Label label;
    @FXML
    private ListView<Deck> deckList;

    public void initialize() {
        String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        // label.setText("Hello, JavaFX " + javafxVersion + "\nRunning on Java " +
        // javaVersion + ".");
        dataService = new DataService();
        decks = dataService.ReadFromFile();
        if (decks != null) {
            System.out.println(decks);
            deckList.getItems().addAll(decks);

        } else {
            System.out.println("No decks found");
            deckList.setPlaceholder(new Label("No decks found"));
        }

        attachEventHandlers();
    }

    public void attachEventHandlers() {
        deckList.setOnMouseClicked(event -> {
            Deck deck = deckList.getSelectionModel().getSelectedItem();
            System.out.println(deck.getTitle() != null ? deck.getTitle() : "No deck Available");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
            try {
                Parent root = loader.load();
                PlayerController controller = loader.getController();
                controller.initPlayer(deck);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addEditDeck(ActionEvent event) {
        // todo show modal to add / edit deck name

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/addEditDeck.fxml"));
            Parent root = loader.load();
            AddEditDeckController addEditDeckController = loader.getController();

            Stage modal = new Stage();
            modal.setTitle("Add Deck");
            modal.initOwner(primaryStage);
            modal.initModality(Modality.APPLICATION_MODAL);
            modal.setScene(new Scene(root));
            addEditDeckController.initStage(modal);
            modal.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
