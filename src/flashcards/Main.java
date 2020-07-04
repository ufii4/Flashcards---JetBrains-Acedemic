package flashcards;

import java.util.*;

public class Main {
    public static String getKey(Map<String, String> map, String val){
        for(String key : map.keySet()){
            if(map.get(key).equals(val)){
                return key;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of cards:");
        int n = scanner.nextInt();
        scanner.nextLine();

        Map<String, String> flashCards = new LinkedHashMap<>();

        String description;
        String definition;
        for (int i = 1; i <= n; i++) {
            System.out.println("The card #" + i + ":");
            while (true) {
                description = scanner.nextLine();
                if (flashCards.containsKey(description)) {
                    System.out.println("The card \"" + description + "\" already exists. Try again:");
                } else {
                    break;
                }
            }
            System.out.println("The definition of the card #" + i + ":");
            while (true) {
                definition = scanner.nextLine();
                if (flashCards.containsValue(definition)) {
                    System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                } else {
                    break;
                }
            }
            flashCards.put(description, definition);
        }

        String answer;
        for (String des : flashCards.keySet()) {
            System.out.println("Print the definition of \"" + des + "\":");
            answer = scanner.nextLine();
            if(flashCards.get(des).equals(answer)){
                System.out.println("Correct answer.");
            }else{
                System.out.print("Wrong answer. The correct one is \"" + flashCards.get(des) + "\"");
                if(flashCards.containsValue(answer)){
                    System.out.print(", you've just written the definition of \"" + getKey(flashCards, answer) + "\"" );
                }
                System.out.println(".");
            }

        }


    }
}
