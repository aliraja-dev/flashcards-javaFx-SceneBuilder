package main.editor;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import main.player.PlayerController;
import models.Card;
import models.Deck;
import services.DataService;

public class EditorController {

    private Stage stage;
    private Scene scene;
    private Deck deck;
    private String question;
    private String answer;
    private Card selectedCard;
    private DataService ds = DataService.getInstance();

    @FXML
    TextArea questionTextArea;
    @FXML
    TextArea answerTextArea;
    @FXML
    Label mainLabel;

    public void backToDeck(ActionEvent event) {
        // todo implement back to deck from the editor
    }

    // todo on Back click from editor, go back to PlayerController
    public void switchToPlayer(ActionEvent event) {
        System.out.println("Switch to player");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
            Parent root = loader.load();
            PlayerController controller = loader.getController();
            controller.initPlayer(this.deck);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveCard(ActionEvent event) {
        // * implement Save Card
        this.question = questionTextArea.getText();
        this.answer = answerTextArea.getText();
        if (this.selectedCard == null) {
            System.out.println("New card");
            // todo Create a Revised Deck, then read all decks from file, find this deck in
            // there, replace with revised deck, then save it to file again

            // * creating revised deck with new card in it
            Deck revisedDeck = this.deck;
            revisedDeck.addCard(new Card(this.question, this.answer));
            System.out.println("revised deck: " + revisedDeck.toString());
            // * Read from File
            ArrayList<Deck> tempDecks = this.ds.getDecks();
            // * find using old deck in the tempDecks and then replace with revisedDeck
            for (Deck d : tempDecks) {
                if (d.getTitleOfDeck().equals(this.deck.getTitleOfDeck())) {
                    tempDecks.remove(d);
                    tempDecks.add(revisedDeck);
                    break;
                }
            }

            // * Save to File
            this.ds.setDecks(tempDecks);
            /**
             * return to Player Controller
             */
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
                Parent root = loader.load();
                PlayerController controller = loader.getController();
                controller.initPlayer(revisedDeck);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("/resources/css/player.css");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (this.selectedCard != null) {
            /**
             * Update the card
             */

            System.out.println("Edit card");
            ArrayList<Card> cards = this.deck.getCards();
            int indexOfCard = cards.indexOf(selectedCard);
            cards.set(indexOfCard, new Card(this.question, this.answer));
            this.deck.setCards(cards);
            Deck revisedDeck = this.deck;
            // * Read from File
            ArrayList<Deck> tempDecks = this.ds.getDecks();
            // * find using old deck in the tempDecks and then replace with revisedDeck
            for (Deck d : tempDecks) {
                if (d.getTitleOfDeck().equals(this.deck.getTitleOfDeck())) {
                    tempDecks.remove(d);
                    tempDecks.add(revisedDeck);
                    break;
                }
            }

            // * Save to File
            this.ds.setDecks(tempDecks);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
            try {
                Parent root = loader.load();
                PlayerController controller = loader.getController();
                controller.initPlayer(revisedDeck);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                scene.getStylesheets().add("/resources/css/player.css");
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * To Edit a Card
     * 
     * @param deck
     * @param selectedCard
     */
    public void initEditor(Deck deck, Card selectedCard) {
        // todo implement init editor
        this.deck = deck;
        this.selectedCard = selectedCard;
        this.question = selectedCard.getQuestion();
        this.answer = selectedCard.getAnswer();
        questionTextArea.setText(selectedCard.getQuestion());
        answerTextArea.setText(selectedCard.getAnswer());
        mainLabel.setText("Edit Card");
    }

    /**
     * To Create a New Card
     * 
     * @param deck
     */
    public void initEditor(Deck deck) {
        // * Loads the editor to create a new card
        this.deck = deck;
        this.question = "";
        this.answer = "";
        this.selectedCard = null;
        questionTextArea.setText(this.question);
        answerTextArea.setText(this.answer);
        mainLabel.setText("Create New Card");
    }
}
