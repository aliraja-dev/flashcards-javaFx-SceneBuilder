package main;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private DataService ds = DataService.getInstance();
    private ArrayList<Deck> decks;

    @FXML
    private Label label;
    @FXML
    private ListView<String> deckList;

    public void initialize() {
        this.decks = this.ds.getDecks();

        if (this.decks != null) {
            System.out.println("Decks from file " + decks);
            // ! Extract titles of decks and assign to ListView
            ArrayList<String> titles = new ArrayList<String>();
            for (Deck deck : this.decks) {
                titles.add(deck.getTitleOfDeck());
            }
            // * converted ArrayList to ObservableList
            ObservableList<String> observableList = FXCollections.observableList(titles);
            deckList.getItems().addAll(observableList);
        } else {
            System.out.println("No decks found");
            deckList.setPlaceholder(new Label("No decks found"));
        }

        attachEventHandlers();
    }

    public void attachEventHandlers() {
        // * Do this on MouseClick on ListView
        deckList.setOnMouseClicked(event -> {
            if (this.decks != null) {
                String title = deckList.getSelectionModel().getSelectedItem();
                Deck selectedDeck = this.decks.stream().filter(deck -> title.equals(deck.getTitleOfDeck())).findAny()
                        .orElse(null);

                if (selectedDeck != null) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
                    try {
                        Parent root = loader.load();
                        PlayerController controller = loader.getController();
                        controller.initPlayer(selectedDeck);
                        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("No selected deck Available");
                }
            } else {
                System.out.println("No Decks Available");
            }
        });
    }

    public void addEditDeck(ActionEvent event) {
        // todo check again

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
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO check this method again
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
