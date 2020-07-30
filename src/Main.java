import java.util.*;
public class Main {
public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {

        //Diamonds, clubs, hearts, spades
        //   0        1      2        3

        while (true) {
            System.out.println("New Hold'em hand to analyze. Enter starting cards:");
            String c1 = input.nextLine();
            String c2 = input.nextLine();
            String n1 = Cards.cardName(c1);
            String n2 = Cards.cardName(c2);

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

            numOthers = askPlayers();

            //calculate

            System.out.println("Enter 4th card:");
            String f4 = input.nextLine();
            if (f4.equals("gg")) {
                continue;
            }
            numOthers = askPlayers();

            //calculate

            System.out.println("Enter 5th card:");
            String f5 = input.nextLine();
            if (f5.equals("gg")) {
                continue;
            }
            numOthers = askPlayers();

            //calculate

            System.out.println("Press enter for new game");
            String lmao = input.nextLine();

        }
    }


    public static int askPlayers() {
        int num = input.nextInt();
        String dummy = input.nextLine();
        return num;
    }
}
