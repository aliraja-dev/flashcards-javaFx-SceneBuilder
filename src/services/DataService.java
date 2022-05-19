package services;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import models.Deck;
import models.Card;

public class DataService {

    ArrayList<Deck> decks = new ArrayList<Deck>();
    ArrayList<Card> cards = new ArrayList<Card>();

    // * implement singleton pattern
    private final static DataService INSTANCE = new DataService();

    public static DataService getInstance() {
        return INSTANCE;
    }

    // Private Constructor - Singleton Pattern
    private DataService() {
        // cards.add(new Card("What is the capital of Alaska?", "Juneau"));
        // cards.add(new Card("What is the capital of New York?", "Albany"));
        // cards.add(new Card("What is the capital of Texas?", "Austin"));
        // cards.add(new Card("What is the capital of California?", "Sacramento"));
        // cards.add(new Card("What is the capital of Florida?", "Tallahassee"));
        // cards.add(new Card("What is the capital of Washington?", "Olympia"));
        // cards.add(new Card("What is the capital of Oregon?", "Salem"));

        // // create First Deck
        // Deck deck = new Deck("Geography", cards);
        // decks.add(deck);

        // // create Second Deck
        // cards = new ArrayList<Card>();
        // cards.add(new Card("Name of the first president of the United States?",
        // "George Washington"));
        // cards.add(new Card("Name of the first president of the India?", "Moodi"));
        // cards.add(new Card("Name of the first president of the Brazil?", "Joao"));
        // cards.add(new Card("Name of the first president of the Canada?",
        // "Burberry"));
        // cards.add(new Card("Name of the first president of the Australia?", "John
        // Smith"));
        // Deck deck2 = new Deck("Presidents", cards);
        // decks.add(deck2);
    }

    public void setDecks(ArrayList<Deck> decks) {
        // TODO save to file all the decks, using object serialization

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("decks.ser"));
            os.writeObject(decks);
            System.out.println("Serialized data is saved in decks.ser");
        } catch (Exception e) {
            System.out.println("Error Serializing decks");
            e.printStackTrace();

        }

    }

    public void setDecks(Deck deck) {
        this.decks.add(deck);
        setDecks(this.decks);
    }

    public void setDecks() {
        setDecks(this.decks);
    }

    public ArrayList<Deck> getDecks() {
        // TODO load from file
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("decks.ser"));
            ArrayList<Deck> decks = (ArrayList<Deck>) is.readObject();
            return decks; // dummy data
        } catch (Exception e) {
            System.out.println("Error Deserializing decks");
            e.printStackTrace();
            return null;
        }

    }
}
