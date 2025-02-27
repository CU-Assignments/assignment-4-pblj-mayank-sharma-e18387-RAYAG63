import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Card {
    private String symbol;
    private String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + symbol;
    }
}

public class CardCollectionSystem {
    private static Map<String, List<Card>> cardCollection = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nCard Collection System");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCard();
                    break;
                case 2:
                    searchCardsBySymbol();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addCard() {
        System.out.print("Enter Card Symbol (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();
        System.out.print("Enter Card Value (e.g., Ace, 2, King): ");
        String value = scanner.nextLine();

        Card card = new Card(symbol, value);
        cardCollection.computeIfAbsent(symbol, k -> new ArrayList<>()).add(card);
        System.out.println("Card added successfully.");
    }

    private static void searchCardsBySymbol() {
        System.out.print("Enter Card Symbol to search: ");
        String symbol = scanner.nextLine();

        List<Card> cards = cardCollection.get(symbol);
        if (cards != null && !cards.isEmpty()) {
            System.out.println("Cards with symbol " + symbol + ":");
            for (Card card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("No cards found with symbol " + symbol + ".");
        }
    }
}
