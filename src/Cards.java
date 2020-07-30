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

    public static int check(int[] oldarr) {
        int[] v = new int[5];
        int[] s = new int[5];
        for (int x = 0; x < 5; x++) {
            v[x] = oldarr[x] % 13;
            s[x] = oldarr[x] / 13;
        }
        Arrays.sort(v);

        int str8 = straight(v);
        boolean f = flush(s);

        if (str8 == 999 && f) {
            //royal flush
            return 696969;
        }
        if (str8 == 1 && f) {
            //straight flush
            return 900 + (v[4]);
        }
        if (fourofakind(v)) {
            //4 of a kind
            return 800 + (v[2]);
        }

        if (fullhouse(v) > 0) {
            //full house
            return 700 + (v[fullhouse(v)]);
        }

        if (f) {
            //flush
            return 600 + (v[4]);
        }

        if (str8 > 0) {
            //straight
            if (str8 == 999) {
                return 513;
            } else {
                return 500 + v[4];
            }
        }

        if (threeofakind(v)) {
            //3 of a kind
            return 400 + v[2];
        }

        if (twopairs(v)) {
            // 2 pairs
            return 300 + v[3];
        }

        if (pair(v) > 0) {
            // 1 pair
            return 200 + v[pair(v)];
        }

        // high card
        return 100 + v[4];


    }

    public static int pair(int[] arr) {
        if (arr[0] == arr[1]) {
            return 1;
        }
        if (arr[1] == arr[2]) {
            return 2;
        }
        if (arr[2] == arr[3]) {
            return 3;
        }
        if (arr[3] == arr[4]) {
            return 4;
        }
        return 0;
    }

    public static boolean twopairs(int[] arr) {
        return (arr[0] == arr[1] && (arr[2] == arr[3] || arr[3] == arr[4])) || (arr[1] == arr[2] && arr[3] == arr[4]);
    }

    public static boolean threeofakind(int[] arr) {
        return arr[0] == arr[2] || arr[1] == arr[3] || arr[2] == arr[4];
    }

    public static int fullhouse(int[] arr) {
        if (arr[0] == arr[1] && arr[2] == arr[3] && arr[3] == arr[4]) {
            return 4;
        }
        if (arr[0] == arr[1] && arr[1] == arr[2] && arr[3] == arr[4]) {
            return 1;
        }
        return 0;

    }

    public static boolean fourofakind(int[] arr) {
        int a = arr[0];
        int b = arr[4];
        return (a == arr[1] && a == arr[2] && a == arr[3]) || (b == arr[1] && b == arr[2] && b == arr[3]);
    }

    public static int straight(int[] arr) {
        int v = arr[0];
        if (v == 0 && arr[1] == 9 && arr[2] == 10 && arr[3] == 11 && arr[4] == 12) {
            return 999;
        }
        if (arr[1] == v + 1 && arr[2] == v + 2 && arr[3] == v + 3 && arr[4] == v + 4) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean flush(int[] arr) {
        int s = arr[0];
        return arr[1] == s && arr[2] == s && arr[3] == s && arr[4] == s;
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
