import java.util.*;
public class Main {

    public static Scanner input = new Scanner(System.in);
    public static int[] deck = new int[52];
    public static Random r = new Random();

    public static void main(String[] args) {

        for (int x = 0; x < 52; x++) {
            deck[x] = x;
        }

        //Diamonds, clubs, hearts, spades
        //   0        1      2        3

        while (true) {
            System.out.println("New Hold'em hand to analyze. Enter starting cards:");
            String c1 = input.nextLine();
            String c2 = input.nextLine();
            String n1 = Cards.cardName(c1);
            String n2 = Cards.cardName(c2);
            ArrayList<Integer> flops = new ArrayList<>();

            System.out.println("How many players are you playing against?");
            int numOthers = askPlayers();

            //calculate pre-flop

            System.out.println("Enter flop:");
            String f1 = input.nextLine();
            if (f1.equals("gg")) {
                continue;
            }
            String f2 = input.nextLine();
            String f3 = input.nextLine();
            flops.add(Cards.cardID(f1));
            flops.add(Cards.cardID(f2));
            flops.add(Cards.cardID(f3));

            numOthers = askPlayers();

            //calculate

            System.out.println("Enter 4th card:");
            String f4 = input.nextLine();
            if (f4.equals("gg")) {
                continue;
            }
            flops.add(Cards.cardID(f4));
            numOthers = askPlayers();

            //calculate

            System.out.println("Enter 5th card:");
            String f5 = input.nextLine();
            if (f5.equals("gg")) {
                continue;
            }
            flops.add(Cards.cardID(f5));
            numOthers = askPlayers();

            //calculate

            System.out.println("Press enter for new game");
            String lmao = input.nextLine();

        }
    }

    public static ArrayList<Double> monteCarlo(String card1, String card2, int numOthers, ArrayList<Integer> flops) {
        ArrayList<Double> results = new ArrayList<>();
        int wins = 0;
        int ties = 0;
        int losses = 0;
        int c1 = Cards.cardID(card1);
        int c2 = Cards.cardID(card2);
        HashSet<Integer> used;
        ArrayList<Integer> othersCards;
        ArrayList<Integer> drawnFlops = new ArrayList<>();
        int temp;
        int outcome;
        for (int repetitions = 0; repetitions < 10000; repetitions++) {
            used = new HashSet<>();
            othersCards = new ArrayList<>();
            drawnFlops = (ArrayList<Integer>) flops.clone();
            used.add(c1);
            used.add(c2);
            for (int x = 0; x < numOthers * 2; x++) {
                temp = draw();
                while (used.contains(temp)) {
                    temp = draw();
                }
                used.add(temp);
                othersCards.add(temp);
            }

            while (drawnFlops.size() < 5) {
                temp = draw();
                while (used.contains(temp)) {
                    temp = draw();
                }
                used.add(temp);
                drawnFlops.add(temp);
            }

            outcome = Cards.evaluate(c1, c2, othersCards, drawnFlops);

            if (outcome == 1) {
                wins++;
            }
            if (outcome == 0) {
                ties++;
            }
            if (outcome == -1) {
                losses++;
            }

        }

        return results;
    }

    public static int askPlayers() {
        int num = input.nextInt();
        String dummy = input.nextLine();
        return num;
    }

    public static int draw() {
        return r.nextInt(52);
    }

}
