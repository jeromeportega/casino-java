/*
    Name: Jerome Ortega
    Module 1 - String Manipulation
    Date: March 7, 2021
*/

import java.util.Scanner;

public class Assig2
{
    static final int MAX = 1000;
    static final int MIN = 1;
    final static Scanner sc = new Scanner(System.in);

    // Gets a valid bet from the user.
    public int getBet()
    {
        int bet = -1;

        do {
            System.out.print("Please make a bet from $1 - $100 (0 to quit): ");
            bet = sc.nextInt();
        } while (bet < 0 || bet > 100);

        return bet;
    }

    // Grabs three random strings for the result of the pull.
    public ThreeString pull()
    {
        ThreeString newPull = new ThreeString();

        newPull.setString1(randString());
        newPull.setString2(randString());
        newPull.setString3(randString());

        return newPull;
    }

    // Gets a random string for the result.  This also holds the odds of each string.
    private String randString() {
        String resultStr;
        int randomInt = (int) ((Math.random() * (MAX - MIN)) + MIN);

        if (randomInt <= 125) {
            resultStr = "7";
        } else if (randomInt > 125 && randomInt <= 250) {
            resultStr = "BAR";
        } else if (randomInt > 250 && randomInt <= 500) {
            resultStr = "cherries";
        } else {
            resultStr = "-----";
        }

        return resultStr;
    }

    // Gets the multiplier by finding valid string combinations based on the odds.
    int getPayMultiplier (ThreeString thePull) {
        int multiplier;
        String str1 = thePull.getString1();
        String str2 = thePull.getString2();
        String str3 = thePull.getString3();

        if (str1 == "cherries" && str2 != "cherries") {
            multiplier = 5;
        } else if (str1 == "cherries" && str2 == "cherries" && str3 != "cherries") {
            multiplier = 15;
        } else if (str1 == "cherries" && str2 == "cherries" && str3 == "cherries") {
            multiplier = 30;
        } else if (str1 == "BAR" && str2 == "BAR" && str3 == "BAR") {
            multiplier = 50;
        } else if (str1 == "7" && str2 == "7" && str3 == "7") {
            multiplier = 100;
        } else {
            multiplier = 0;
        }

        return multiplier;
    }

    // States whether the user won or lost, adds the result and winnings.
    void display(ThreeString thePull, int winnings) {
        if (winnings <= 0) {
            System.out.printf("%s %n sorry - you lost.%n%n", thePull.toString());
        } else {
            System.out.printf("%s %n congratulations, you won $%d%n%n", thePull.toString(), winnings);
        }
    }
}

class ThreeString
{
    public static final int MAX_LEN = 20;
    public static final int MAX_PULLS = 40;
    static int numPulls = 0;
    static int[] pullWinnings = new int[MAX_PULLS];

    String string1;
    String string2;
    String string3;

    public ThreeString()
    {
        string1 = "";
        string2 = "";
        string3 = "";
    }

    // Converts the three result strings to one formatted string.
    public String toString()
    {
        return ("| ").concat(string1).concat(" | ").concat(string2).concat(" | ").concat(string3).concat(" |");
    }

    private boolean validString(String str)
    {
        return (str != null && str.length() > 0 && str.length() <= MAX_LEN);
    }

    public String getString1()
    {
        return string1;
    }

    public boolean setString1(String str)
    {
        boolean result = false;

        if (validString(str)) {
            string1 = str;
            result = true;
        }

        return result;
    }

    public String getString2()
    {
        return string2;
    }

    public boolean setString2(String str)
    {
        boolean result = false;

        if (validString(str)) {
            string2 = str;
            result = true;
        }

        return result;
    }

    public String getString3()
    {
        return string3;
    }

    public boolean setString3(String str)
    {
        boolean result = false;

        if (validString(str)) {
            string3 = str;
            result = true;
        }

        return result;
    }

    // If the array is not maxed out, save the winnings and advance numPulls by 1.
    public boolean saveWinnings(int winnings)
    {
        boolean success = false;

        if (numPulls < MAX_PULLS) {
            pullWinnings[numPulls] = winnings;
            numPulls++;
            success = true;
        }

        return success;
    }

    // Converts the array of winnings to individual string entries and a cumulative total.
    public String toStringWinnings()
    {
        int calculatedWinnings = 0;
        String winningsString = String.format("Your individual winnings were: %n");

        for (int i = 0; i < numPulls; i++) {
            calculatedWinnings += pullWinnings[i];
            winningsString += String.format("$%d ", pullWinnings[i]);
        }

        winningsString += String.format("%nYour total winnings were: $%d", calculatedWinnings);

        return winningsString;
    }
}