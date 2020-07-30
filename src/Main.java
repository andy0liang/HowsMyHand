import java.util.*;
public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("New Hold'em hand to analyze. Enter starting cards:");
            String c1 = input.nextLine();
            String c2 = input.nextLine();
            String n1 = Cards.cardName(c1);
            String n2 = Cards.cardName(c2);

            System.out.println(n1 + "\n" + n2);

        }
    }
}
