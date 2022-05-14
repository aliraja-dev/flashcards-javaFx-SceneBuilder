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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.player.PlayerController;
import models.Card;
import models.Deck;

public class EditorController {
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Deck deck;
    private String question;
    private String answer;
    private Card selectedCard;
    @FXML
    TextArea questionTextArea;
    @FXML
    TextArea answerTextArea;
    @FXML
    Label mainLabel;

    public void backToDeck(ActionEvent event) {
        // todo implement back to deck from the editor
    }

    public void switchToPlayer(ActionEvent event) throws Exception {
        System.out.println("Switch to player");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
        try {
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
        if (selectedCard == null) {
            System.out.println("New card");
            this.deck.addCard(new Card(this.question, this.answer));
            System.out.println("Saved card " + this.question + " " + this.answer);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
            try {
                Parent root = loader.load();
                PlayerController controller = loader.getController();
                controller.initPlayer(this.deck, true);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (selectedCard != null) {
            System.out.println("Update card");
            ArrayList<Card> cards = this.deck.getCards();
            int index = cards.indexOf(selectedCard);
            cards.set(index, new Card(this.question, this.answer));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/player.fxml"));
            try {
                Parent root = loader.load();
                PlayerController controller = loader.getController();
                controller.initPlayer(this.deck, true);
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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

    public void initEditor(Deck deck) {
        // * Loads the editor to create a new card
        this.deck = deck;
        this.question = "";
        this.answer = "";
        questionTextArea.setText(this.question);
        answerTextArea.setText(this.answer);
        mainLabel.setText("Create New Card");
    }
}
