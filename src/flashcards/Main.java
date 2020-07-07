package flashcards;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        startMenu();
    }

    public static int numberOfCards;

    public static void startMenu() {
        Scanner sc = new Scanner(System.in);
        Map<String, String> flashCards = new HashMap<>();
        while (true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = sc.nextLine();
            if (action.equals("add")) {
                addCard(flashCards, sc);
            } else if (action.equals("remove")) {
                removeCard(flashCards, sc);
            } else if (action.equals("exit")) {
                break;
            } else if (action.equals("import")) {
                importFromFile(flashCards, sc);
            } else if (action.equals("export")) {
                exportToFile(flashCards, sc);
            } else if (action.equals("ask")) {
                askQuestions(flashCards, sc);
            } else {
                System.out.println("Please enter a valid action.");
            }
        }
        System.out.println("Bye bye!");
    }

    public static void addCard(Map<String, String> flashCards, Scanner sc) {
        String description;
        String definition;
        System.out.println("The card:");
        description = sc.nextLine();
        if (flashCards.containsKey(description)) {
            System.out.println("The card \"" + description + "\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        definition = sc.nextLine();
        if (flashCards.containsValue(definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }
        flashCards.put(description, definition);
        numberOfCards++;
        System.out.println("The pair (\"" + description + "\":\"" + definition + "\") has been added.");
        return;
    }

    public static void removeCard(Map<String, String> flashCards, Scanner sc) {
        String description;
        System.out.println("The card:");
        description = sc.nextLine();
        if (flashCards.containsKey(description)) {
            flashCards.remove(description);
            numberOfCards--;
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + description + "\": there is no such card.");
        }
        return;
    }

    public static void importFromFile(Map<String, String> flashCards, Scanner sc) {
        System.out.println("File name:");
        File myCards = new File(sc.nextLine());
        int sum = 0;
        try (Scanner cardReader = new Scanner(myCards)) {
            if (cardReader.nextLine().equals("This file is for the project flashcards.")) {
                String[] input;
                String description;
                String definition;
                while (cardReader.hasNextLine()) {
                    input = cardReader.nextLine().split(":");
                    description = input[0];
                    definition = input[1];
                    if (flashCards.containsKey(description) || flashCards.containsValue(definition)) {
                        numberOfCards--;
                    }
                    flashCards.put(description, definition);
                    sum++;
                }
                numberOfCards += sum;
                System.out.println(sum + " cards have been loaded.");
                return;
            }
        } catch (IOException e) {
        }
        System.out.println("0 cards loaded. File not found or file format not supported");
        return;
    }

    public static void exportToFile(Map<String, String> flashCards, Scanner sc) {
        System.out.println("File name:");
        //If the file already exist, ask if to rewrite it.
        File myCards = new File(sc.nextLine());
        int sum = 0;
        try (PrintWriter cardsWriter = new PrintWriter(myCards)) {
            cardsWriter.println("This file is for the project flashcards.");
            for (String description : flashCards.keySet()) {
                sum++;
                cardsWriter.println(description + ':' + flashCards.get(description));
            }
            System.out.println(sum + (sum == 1 ? " card" : " cards") + " have been saved.");
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
        return;
    }

    public static void askQuestions(Map<String, String> flashCards, Scanner sc) {
        System.out.println("How many times to ask?");
        int turns = Integer.parseInt(sc.nextLine());
        String answer;
        String nextCard;
        Random index = new Random();
        for (int i = 0; i < turns; i++) {
            nextCard = (String) flashCards.keySet().toArray()[index.nextInt(numberOfCards)];
            System.out.println("Print the definition of \"" + nextCard + "\":");
            answer = sc.nextLine();
            if (flashCards.get(nextCard).equals(answer)) {
                System.out.println("Correct answer.");
            } else {
                System.out.print("Wrong answer. The correct one is \"" + flashCards.get(nextCard) + "\"");
                if (flashCards.containsValue(answer)) {
                    System.out.print(", you've just written the definition of \"" + getKeyFromValue(flashCards, answer) + "\"");
                }
                System.out.println(".");
            }
            if (i >= turns) {
                break;
            }
        }
    }

    public static String getKeyFromValue(Map<String, String> map, String val) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(val)) {
                return key;
            }
        }
        return null;
    }
}
