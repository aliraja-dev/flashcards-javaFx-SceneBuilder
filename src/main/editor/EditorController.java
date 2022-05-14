package main.editor;

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
    @FXML
    TextArea questionTextArea;
    @FXML
    TextArea answerTextArea;

    public void backToDeck(ActionEvent event) {
        // todo implement back to deck from the editor
    }

    public void switchToPlayer(ActionEvent event) throws Exception {
        System.out.println("Switch to player");
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
    }

    public void saveCard(ActionEvent event) {
        // todo implement save card - take the value from fields and save it to the
        // deck.
        System.out.println("Save Card and return to all cards");

    }

    public void initEditor(Deck deck, Card selectedCard) {
        // todo implement init editor
        this.deck = deck;
        this.question = selectedCard.getQuestion();
        this.answer = selectedCard.getAnswer();
        questionTextArea.setText(selectedCard.getQuestion());
        answerTextArea.setText(selectedCard.getAnswer());

    }
}
