package flashcards.FlashCard;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Deck {
    private final ArrayList<Card> currentCards = new ArrayList<>();

    public boolean containsCard(String description) {
        Card card = new Card(description);
        return currentCards.contains(card);
    }

    public boolean containsDefinition(String definition) {
        for (Card card : currentCards) {
            if (card.getDefinition().equals(definition)) {
                return true;
            }
        }
        return false;
    }

    public void addCard(String description, String definition) {
        currentCards.add(new Card(description, definition));
    }

    public void addCard(String description, String definition, int errorCount){
        currentCards.add(new Card(description, definition, errorCount));
    }

    public void removeCard(String description) {
        currentCards.remove(new Card(description));
    }

//    Import cards to the deck from a file.
    public void importFromFile(File myCards) {
        int sum = 0;
        try (Scanner cardReader = new Scanner(myCards)) {
            if (cardReader.nextLine().equals("This file is for the project flashcards.")) {
                cardReader.nextLine();
                String[] input;
                String description;
                String definition;
                int errorCount;
                while (cardReader.hasNextLine()) {
                    input = cardReader.nextLine().split(":");
                    description = input[0];
                    definition = input[1];
                    errorCount = Integer.parseInt(input[2]);
                    if (this.containsCard(description)) {
                        removeCard(description);
                    }
                    this.addCard(description, definition,errorCount);
                    sum++;
                }
                System.out.println(sum + (sum <= 1 ? " card" : " cards") + " have been loaded.");
                return;
            }
        } catch (IOException e) {
            System.out.println("File does not exist.");
        }
        System.out.println("0 cards loaded. File not found or file format not supported");
    }

//    Export the current deck to a file provided.
    public void exportToFile(File myCards) {
        int sum = 0;
        try (PrintWriter cardsWriter = new PrintWriter(myCards)) {
            cardsWriter.println("This file is for the project flashcards.");
            cardsWriter.println("Description:Definition:Error Count");
            for (Card card : currentCards) {
                sum++;
                cardsWriter.println(card.getDescription() + ':' + card.getDefinition() + ':' + card.getErrorCount());
            }
            System.out.println(sum + (sum <= 1 ? " card" : " cards") + " have been saved.");
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
    }

//    Return the card with by description.
    public Card getCard(String description){
        for (Card card : currentCards) {
            if (card.getDescription().equals(description)) {
                return card;
            }
        }
        return new Card("Error");
    }

//    Return the card that has the exact definition as provided.
    public Card getCardFromDefinition(String definition) {
        for (Card card : currentCards) {
            if (card.getDefinition().equals(definition)) {
                return card;
            }
        }
        return new Card("Error");
    }

//    Return a random card in the deck.
    public Card getRandomCard(){
        Random index = new Random();
        return (Card) currentCards.toArray()[index.nextInt(currentCards.size())];
    }

//    Return the number of cards in the deck.
    public int getSize(){
        return currentCards.size();
    }

//    Reset all cards' error count to zero.
    public void resetError() {
        for (Card card : currentCards) {
            card.clearErrorCount();
        }
    }

//    Return a collection of cards or a card that has the most error count in the deck.
    public ArrayList<Card> getHardestCards(){
        ArrayList<Card> hardestCards = new ArrayList<>();
        int errorCount = 0;
        for(Card card:currentCards){
            if(card.getErrorCount()>errorCount){
                errorCount = card.getErrorCount();
            }
        }
        if(errorCount==0) return hardestCards;
        for (Card card:currentCards){
            if(card.getErrorCount()==errorCount){
                hardestCards.add(card);
            }
        }
        return hardestCards;
    }
}
