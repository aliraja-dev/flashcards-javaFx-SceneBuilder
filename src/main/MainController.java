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

public class MainController implements Initializable {
    private Stage primaryStage;
    private Stage stage;
    private Scene scene;
    private DataService ds = DataService.getInstance();
    private ArrayList<Deck> decks;

    @FXML
    private Label label;
    @FXML
    private ListView<String> deckList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.decks = this.ds.getDecks();

        if (this.decks != null) {
            System.out.println(decks);
            // TODO populate listview with Titles only
            ArrayList<String> titles = new ArrayList<String>();
            for (Deck deck : this.decks) {
                titles.add(deck.getTitleOfDeck());
            }
            System.out.println(titles);

            // todo convert arraylist to observable list

            ObservableList<String> observableList = FXCollections.observableList(titles);
            deckList.getItems().addAll(observableList);

        } else {
            System.out.println("No decks found");
            deckList.setPlaceholder(new Label("No decks found"));
        }

        attachEventHandlers();
    }

    public void attachEventHandlers() {
        deckList.setOnMouseClicked(event -> {
            if (this.decks != null) {
                Deck deck = deckList.getSelectionModel().getSelectedItem();
                // System.out.println(deck.getTitle() != null ? deck.getTitle() : "No deck
                // Available");
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
            } else {
                System.out.println("No Deck Available");
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
            initialize();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
