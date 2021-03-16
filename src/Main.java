/*
    Name: Jerome Ortega
    Module 1 - String Manipulation
    Date: March 7, 2021
*/

public class Main
{
    public static final int MAX_PULLS = 40;

    public static void main(String[] args)
    {
        int bet;
        int winnings;
        ThreeString pullResult = null;
        Assig2 assigner = new Assig2();

        bet = assigner.getBet();

        // Keeps the game going until they enter 0 for their bet.
        while (bet > 0) {
            pullResult = assigner.pull();
            winnings = assigner.getPayMultiplier(pullResult) * bet;

            // If there's a problem savings the winnings, we break the loop and end the game.
            if (!pullResult.saveWinnings(winnings)) {
                break;
            }

            assigner.display(pullResult, winnings);
            bet = assigner.getBet();
        };

        System.out.printf("Thanks for playing at the casino! %n");

        // If there is a pull present, print out the results of their game.
        if (pullResult != null) {
            System.out.printf("%s%n", pullResult.toStringWinnings());
        }
    }
}
