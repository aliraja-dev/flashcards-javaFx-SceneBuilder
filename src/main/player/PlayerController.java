package main.player;

import java.util.ArrayList;

import javafx.collections.FXCollections;
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
import javafx.stage.Stage;
import main.editor.EditorController;
import models.Card;
import models.Deck;

public class PlayerController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private boolean showAnswer = false;
    private Card selectedCard;
    private Deck deck;
    private boolean updated;
    @FXML
    Label playerTitle;
    @FXML
    ListView<String> cardList;
    @FXML
    Label questionLabel;
    @FXML
    Button toggleAnswerBtn;

    /**
     * Return to Main Screen
     * 
     * @param event
     */

    public void switchToMain(ActionEvent event) {

        System.out.println("Switch to main");
        try {
            root = FXMLLoader.load(getClass().getResource("/resources/fxml/main.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void switchToEditor(ActionEvent event) {

        if (selectedCard != null) {
            System.out.println("Switch to editor");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/editor.fxml"));
            try {
                root = loader.load();
                EditorController controller = loader.getController();
                controller.initEditor(deck, this.selectedCard);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                System.out.println("Error loading editor.fxml");
                e.printStackTrace();
            }
        } else {
            System.out.println("Select a card first");
        }
    }

    public void addCardToDeck(ActionEvent event) throws Exception {
        // todo implement add card to deck
        System.out.println("Create New Card ");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/editor.fxml"));
        try {
            root = loader.load();
            EditorController controller = loader.getController();
            controller.initEditor(deck);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error loading editor.fxml");
            e.printStackTrace();
        }

    }

    /**
     * Initialize the PlayerController
     * 
     * @param deck
     */
    public void initPlayer(Deck deck) {
        this.deck = deck;
        if (deck != null) {
            playerTitle.setText(deck.getTitleOfDeck());
            // * Populate ListView with Questions from Deck
            ArrayList<String> questions = new ArrayList<String>();
            for (Card card : deck.getCards()) {
                questions.add(card.getQuestion());
            }
            ObservableList<String> observableList = FXCollections.observableArrayList(questions);
            cardList.getItems().addAll(observableList);
            attachEventHandlers();
        } else {
            cardList.setPlaceholder(new Label("No cards found"));
            System.out.println("No Cards Available");
        }
    }

    /**
     * Initialize the PlayerController after Saving New CARD
     * 
     * @param deck
     * @param updated
     */
    public void initPlayer(Deck deck, boolean updated) {
        this.deck = deck;
        this.updated = updated;
        System.out.println("Revised DECK " + deck.toString());
        playerTitle.setText(deck.getTitleOfDeck());
        // todo commented below line
        // cardList.getItems().addAll(deck.getCards());
        cardList.setPlaceholder(new Label("No cards found"));

        attachEventHandlers();
    }

    // * attach event handlers to card items in list
    public void attachEventHandlers() {
        cardList.setOnMouseClicked(event -> {
            String selectedQuestion = cardList.getSelectionModel().getSelectedItem();
            System.out.println(selectedQuestion);
            // * find the selected card using the selected Question from the card List
            Card selectedCard = this.deck.getCards().stream()
                    .filter(card -> card.getQuestion().equals(selectedQuestion)).findAny().orElse(null);
            this.selectedCard = selectedCard;
            questionLabel.setText(selectedCard.getQuestion());
            this.toggleAnswer();
        });
    }

    // TODO check it
    public void toggleAnswer(ActionEvent event) {
        showAnswer = !showAnswer;
        if (showAnswer && selectedCard != null) {
            questionLabel.setText(selectedCard.getAnswer());
            toggleAnswerBtn.setText("Hide Answer");
        } else if (selectedCard != null) {
            questionLabel.setText(selectedCard.getQuestion());
            toggleAnswerBtn.setText("Show Answer");
        } else { // no card selected}
            System.out.println("No card selected");
        }
    }

    // todo check it
    public void toggleAnswer() {
        showAnswer = !showAnswer;
        toggleAnswerBtn.setText("Show Answer");
    }
}
