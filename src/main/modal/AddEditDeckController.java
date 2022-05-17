package main.modal;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Deck;
import services.DataService;

public class AddEditDeckController {
    Deck deck;
    DataService ds = DataService.getInstance();
    @FXML
    TextField addTitleField;
    Stage modal;
    @FXML
    Button saveBtn;
    @FXML
    Button cancelBtn;

    /**
     * Saving New Deck to the List
     * 
     * @param event
     */
    public void onSave(ActionEvent event) {
        // check if its a new deck or old deck
        if (this.deck != null) {
            // todo implement for edit
            System.out.println("Edit Deck" + this.deck.toString());
            ArrayList<Deck> tempDecks = this.ds.getDecks();
            for (Deck d : tempDecks) {
                if (d.getTitleOfDeck().equals(this.deck.getTitleOfDeck())) {
                    System.out.println("Editing this deck " + d.toString());
                    tempDecks.remove(d);
                    d.setTitle(this.addTitleField.getText());
                    tempDecks.add(d);
                    break;
                }
            }
            this.ds.setDecks(tempDecks);
            System.out.println("Edited Decks " + tempDecks.toString());
            modal.close();

        } else if (this.deck == null) {
            System.out.println("Creating new Deck");
            // read from file all decks, then add new deck to the list
            ArrayList<Deck> tempDecks = this.ds.getDecks();
            tempDecks.add(new Deck(addTitleField.getText()));
            System.out.println("Saving " + tempDecks);
            this.ds.setDecks(tempDecks);
            modal.close();
        }

    }

    public void onCancel(ActionEvent event) {
        System.out.println("Cancelling");
        // todo We need to stop calling initialize in Main Controller when we cancel the
        // modal
        modal.close();
    }

    public void initModal(Stage modal) {
        this.modal = modal;
    }

    public void initModal(Stage modal, Deck deck) {
        this.modal = modal;
        this.deck = deck;
        addTitleField.setText(deck.getTitleOfDeck());

    }
}
