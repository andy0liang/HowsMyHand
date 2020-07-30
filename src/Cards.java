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


}
