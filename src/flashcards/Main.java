package flashcards;

import flashcards.FlashCard.*;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        startMenu();
    }

    public static void startMenu() {
        Scanner sc = new Scanner(System.in);
        Deck deck = new Deck();
        while (true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit, hardest card, reset stats):");
            String action = sc.nextLine();
            if (action.equals("add")) {
                addCard(deck, sc);
            } else if (action.equals("remove")) {
                removeCard(deck, sc);
            } else if (action.equals("ask")) {
                askQuestions(deck, sc);
            } else if (action.equals("import")) {
                importFromFile(deck, sc);
            } else if (action.equals("export")) {
                exportToFile(deck, sc);
            } else if (action.equals("exit")) {
                break;
            } else if (action.equals("reset stats")) {
                deck.resetError();
                System.out.println("Card statistics has been reset.");
            } else if (action.equals("hardest card")) {
                showHardestCard(deck, sc);
            } else {
                System.out.println("Please enter a valid action.");
            }
        }
        System.out.println("Bye bye!");
    }

    public static void addCard(Deck deck, Scanner sc) {
        String description;
        String definition;
        System.out.println("The card:");
        description = sc.nextLine();
        if (deck.containsCard(description)) {
            System.out.println("The card \"" + description + "\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        definition = sc.nextLine();
        if (deck.containsDefinition(definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }
        deck.addCard(description, definition);
        System.out.println("The pair (\"" + description + "\":\"" + definition + "\") has been added.");
        return;
    }

    public static void removeCard(Deck deck, Scanner sc) {
        String description;
        System.out.println("The card:");
        description = sc.nextLine();
        if (deck.containsCard(description)) {
            deck.removeCard(description);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + description + "\": there is no such card.");
        }
        return;
    }

    public static void importFromFile(Deck deck, Scanner sc) {
        System.out.println("File name:");
        File myCards = new File(sc.nextLine());
        deck.importFromFile(myCards);
    }

    public static void exportToFile(Deck deck, Scanner sc) {
        System.out.println("File name:");
        File myCards = new File(sc.nextLine());
        deck.exportToFile(myCards);
    }

    public static void askQuestions(Deck deck, Scanner sc) {
        if (deck.getSize() == 0) {
            System.out.println("You don't have any cards yet.");
            return;
        }
        System.out.println("How many times to ask?");
        int turns = Integer.parseInt(sc.nextLine());
        String answer;
        Card nextCard;
        for (int i = 0; i < turns; i++) {
            nextCard = deck.getRandomCard();
            System.out.println("Print the definition of \"" + nextCard.getDescription() + "\":");
            answer = sc.nextLine();
            if (nextCard.getDefinition().equals(answer)) {
                System.out.println("Correct answer.");
            } else {
                System.out.print("Wrong answer. The correct one is \"" + nextCard.getDefinition() + "\"");
                nextCard.addErrorCount();
                if (deck.containsDefinition(answer)) {
                    System.out.print(", you've just written the definition of \"" + deck.getCardFromDefinition(answer).getDescription() + "\"");
                }
                System.out.println(".");
            }
            if (i >= turns) {
                break;
            }
        }
    }

    public static void showHardestCard(Deck deck, Scanner sc) {
        ArrayList<Card> hardestCards = deck.getHardestCards();
        if (hardestCards.size() == 0) {
            System.out.println("There are no cards with errors.");
            return;
        }
        System.out.print("The hardest card");
        if (hardestCards.size() == 1) {
            Card hardestCard = hardestCards.get(0);
            System.out.println(" is \"" + hardestCard.getDescription() + "\". You have " + hardestCard.getErrorCount() + " errors answering it.");
        } else {
            System.out.print("s are ");
            for (int i = 0; i < hardestCards.size(); i++) {
                Card hardestCard = hardestCards.get(i);
                System.out.print("\"" + hardestCard.getDescription() + "\"" + (i + 1 == hardestCards.size() ? "." : ","));
            }
            System.out.println("You have " + hardestCards.get(0).getErrorCount() + " errors answering them.");
        }
    }
}
