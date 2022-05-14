package main.player;

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
    @FXML
    Label deckTitle;
    @FXML
    ListView<Card> cardList;
    @FXML
    Label questionLabel;
    @FXML
    Button toggleAnswerBtn;

    public void switchToMain(ActionEvent event) throws Exception {
        System.out.println("Switch to main");
        root = FXMLLoader.load(getClass().getResource("/resources/fxml/main.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToEditor(ActionEvent event) {
        System.out.println("Switch to editor");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/editor.fxml"));
        try {
            root = loader.load();
            EditorController controller = loader.getController();
            controller.initEditor(deck, selectedCard);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error loading editor.fxml");
            e.printStackTrace();
        }
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

    public void initPlayer(Deck deck) {
        this.deck = deck;
        System.out.println("FROM PLAYER " + deck.toString());
        deckTitle.setText(deck.getTitle());
        cardList.getItems().addAll(deck.getCards());
        cardList.setPlaceholder(new Label("No cards found"));

        attachEventHandlers();
    }

    // * attach event handlers to card items in list
    public void attachEventHandlers() {
        cardList.setOnMouseClicked(event -> {
            Card card = cardList.getSelectionModel().getSelectedItem();
            System.out.println(card.getQuestion());
            selectedCard = card;
            questionLabel.setText(card.getQuestion());
        });
    }

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
}
