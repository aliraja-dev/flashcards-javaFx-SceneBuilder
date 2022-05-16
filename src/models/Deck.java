package models;

import java.io.Serializable;
import java.util.ArrayList;

public class Deck implements Serializable {
    private String title;
    private ArrayList<Card> cards;

    public Deck(String title, ArrayList<Card> cards) {
        this.title = title;
        this.cards = cards;
    }

    // when creating a new Deck, we need to pass in the title and initialize the
    // cards arraylist
    public Deck(String title) {
        this.title = title;
        this.cards = new ArrayList<Card>();
    }

    public Deck() {
        this(null, null);
    }

    // * Card Methods
    public void addCard(Card card) {
        this.cards.add(card);
        // TODO call service here to save to file
    }

    public void removeCard(Card card) {
        // removes the first instance of the card
        this.cards.remove(card);
        // call service here to remove from file if found

    }

    public ArrayList<Card> getCards() {
        return cards;
        // todo read from file
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public boolean isEmpty() {
        return this.cards.isEmpty();
    }

    public int size() {
        return this.cards.size();
    }

    public boolean contains(Card card) {
        return this.cards.contains(card);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        // todo call service here to save to file
    }

    @Override
    public String toString() {
        return "Deck [title=" + title + ", cards=" + cards + "]";
    }
}
