import java.util.*;

public class Cards {

    public static String cardName(String card) {
        String name = "";
        char value = card.charAt(0);
        char suit = card.charAt(1);
        if (value < 58 || value == 84 || value == 116) {
            //value is 2-10

            if (value > 58) {
                name += "10 of ";
            } else {
                name += value + " of ";
            }

        } else {
            //Ace or face card
            if (value == 'A' || value == 'a') {
                name += "Ace of ";
            }
            if (value == 'J' || value == 'j') {
                name += "Jack of ";
            }
            if (value == 'Q' || value == 'q') {
                name += "Queen of ";
            }
            if (value == 'K' || value == 'k') {
                name += "King of ";
            }

        }

        if (suit == 'S' || suit == 's') {
            name += "Spades";
        }
        if (suit == 'C' || suit == 'c') {
            name += "Clubs";
        }
        if (suit == 'H' || suit == 'h') {
            name += "Hearts";
        }
        if (suit == 'D' || suit == 'd') {
            name += "Diamonds";
        }


        return name;
    }

    //Diamonds, clubs, hearts, spades
    //   0        1      2        3
    public static int cardID(String card) {
        char value = card.charAt(0);
        char suit = card.charAt(1);
        int id = 0;
        if (value == 'a' || value == 'A') {
            id += 0;
        } else if (value == 'T' || value == 't') {
            id += 9;
        } else if (value == 'J' || value == 'j') {
            id += 10;
        } else if (value == 'Q' || value == 'q') {
            id += 11;
        } else if (value == 'K' || value == 'k') {
            id += 12;
        } else {
            id += (value - '0') - 1;
        }
        if (suit == 'd' || suit == 'D') {
            id += (13 * 0);
        }
        if (suit == 'c' || suit == 'C') {
            id += (13 * 1);
        }
        if (suit == 'h' || suit == 'H') {
            id += (13 * 2);
        }
        if (suit == 's' || suit == 'S') {
            id += (13 * 3);
        }

        return id;

    }


    public static int evaluate(int c1, int c2, ArrayList<Integer> othersCards, ArrayList<Integer> drawnFlops) {
        int numOthers = othersCards.size() / 2;

        int selfScore = getScore(get5from7(c1, c2, drawnFlops));
        ArrayList<Integer> othersScores = new ArrayList<>();
        for (int x = 0; x < othersCards.size(); x += 2) {
            othersScores.add(getScore(get5from7(othersCards.get(x), othersCards.get(x + 1), drawnFlops)));
        }
        Collections.sort(othersScores);
        if (selfScore > othersScores.get(othersScores.size() - 1)) {
            return 1;
        } else if (selfScore == othersScores.get(othersScores.size() - 1)) {
            return 0;
        } else {
            return -1;
        }
    }

    public static int getScore(ArrayList<int[]> arr) {
        int max = 0;
        int temp;
        for (int x = 0; x < arr.size(); x++) {
            temp = check(arr.get(x));
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    public static int check(int[] arr) {

    }

    public static ArrayList<int[]> get5from7(int c1, int c2, ArrayList<Integer> drawnFlops) {
        ArrayList<int[]> arr = new ArrayList<>();
        for (int a = 0; a < 7; a++) {
            for (int b = a + 1; b < 7; b++) {
                for (int c = b + 1; c < 7; c++) {
                    for (int d = c + 1; d < 7; d++) {
                        for (int e = d + 1; e < 7; e++) {
                            arr.add(new int[]{a, b, c, d, e});
                        }
                    }
                }
            }
        }
        return arr;
    }
}
