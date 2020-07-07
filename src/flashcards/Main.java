package flashcards;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        startMenu();
    }

    public String getKey(Map<String, String> map, String val) {
        for (String key : map.keySet()) {
            if (map.get(key).equals(val)) {
                return key;
            }
        }
        return null;
    }

    public static void startMenu() {
        Scanner sc = new Scanner(System.in);
        Map<String, String> flashCards = new HashMap<>();
        while (true) {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            String action = sc.nextLine().toLowerCase();
            if (action.equals("add")) {
                addCard(flashCards, sc);
            } else if (action.equals("remove")) {
                removeCard(flashCards, sc);
            } else if (action.equals("exit")) {
                break;
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
        System.out.println("The pair (\"" + description + "\":\"" + definition + "\") has been added.");
        return;
    }

    public static void removeCard(Map<String, String> flashCards, Scanner sc) {
        String description;
        System.out.println("The card:");
        description = sc.nextLine().toLowerCase();
        if (flashCards.containsKey(description)) {
            flashCards.remove(description);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + description + "\": there is no such card.");
        }
        return;
    }

    public static void importFromFile(Map<String, String> flashCards, Scanner sc) {

    }

    public static void exportToFile(Map<String, String> flashCards, Scanner sc) {

    }

    public static void askQuestions(Map<String, String> flashCards, Scanner sc) {

    }
}
