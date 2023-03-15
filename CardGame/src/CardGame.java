import java.util.*;

public class CardGame {
    private static final String[] colorsList = {"R", "G", "B", "W"};
    private static final int maxValue = 10;
    private static final int maxPlayers = 100;
    private static final int maxCards = colorsList.length * maxValue;
    private static Map<Integer, List<String>> playerCards = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("You can to use 'start N C' and 'get-cards C' commands");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim();
            String[] tokens = input.split(" ");

            if (tokens[0].equals("start")) {
                handleStartCommand(tokens);
            } else if (tokens[0].equals("get-cards")) {
                handleGetCardsCommand(tokens);
            } else {
                System.out.println("Invalid command.");
            }
        }
    }

    private static void handleStartCommand(String[] tokens) {
        int N = Integer.parseInt(tokens[1]);
        int C = Integer.parseInt(tokens[2]);

        if (N <= 0 || C <= 0 || N > maxCards || C > maxPlayers) {
            System.out.println("Invalid input parameters for start command.");
            return;
        }

        List<String> deck = createDeck();
        Collections.shuffle(deck);

        for (int i = 1; i <= C; i++) {
            List<String> playerHand = new ArrayList<>();
            for (int j = 0; j < N; j++) {
                playerHand.add(deck.remove(0));
            }
            playerCards.put(i, playerHand);
        }
    }

    private static void handleGetCardsCommand(String[] tokens) {
        int C = Integer.parseInt(tokens[1]);

        if (C <= 0 || C > maxPlayers) {
            System.out.println("Invalid input parameter for get-cards command.");
            return;
        }

        List<String> playerHand = playerCards.get(C);
        if (playerHand == null) {
            System.out.println("Player " + C + " has no cards.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(C).append(" ");
        for (String card : playerHand) {
            sb.append(card).append(" ");
        }
        System.out.println(sb.toString().trim());
    }

    private static List<String> createDeck() {
        List<String> deck = new ArrayList<>();
        for (String color : colorsList) {
            for (int i = 1; i <= maxValue; i++) {
                deck.add(color + i);
            }
        }
        return deck;
    }
}