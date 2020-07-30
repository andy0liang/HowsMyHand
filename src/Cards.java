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


}
