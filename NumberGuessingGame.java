import javax.swing.*;

public class NumberGuessingGame {
    private static int tryCount = 0;
    private static int limit = 0;

    public static void main(String[] args) {
        //Loops until user decides to stop playing
        do {
            playGame();
        }
        while(playAgain());
        JOptionPane.showMessageDialog(null, "Bye thanks for playing!");
    }
    public static void playGame()
    {
        int computerNumber;
        int userGuess = 0;
        //Max number - upper limit
        limit = Integer.parseInt(JOptionPane.showInputDialog(null,
                "WELCOME TO THE NUMBER GUESSING GAME!\n\n" +
                        "You will be trying to guess the computer's number\n\n" + "Please specify an upper limit number","Upper Limit Entry",
                JOptionPane.INFORMATION_MESSAGE));
        //Generates random computer number between 1 and users limit
        computerNumber = (int) (Math.random() * limit) + 1;
        while(userGuess != computerNumber) {
            ++tryCount;
            String promptGuess = JOptionPane.showInputDialog(null,
                    "Please enter a number between 1 and " + limit, "User Guess Input",
                    JOptionPane.INFORMATION_MESSAGE);
            userGuess = Integer.parseInt(promptGuess);

            JOptionPane.showMessageDialog(null,
                    calcUserGuess(userGuess, computerNumber, tryCount, limit) +
                            calcFirstGuess(userGuess, computerNumber, tryCount));
        }
        if (userGuess == computerNumber)
        {
            calcUserGuess(userGuess,computerNumber,tryCount,limit);
            tryCount = 0;
            userGuess = 0;
            computerNumber = (int) (Math.random()* limit + 1);
        }
    }

    public static String calcUserGuess(int userGuess, int computerNumber, int count, int limit)
    {
        //out of bounds if
        if(userGuess <= 0 && userGuess > limit)
            return "Invalid Guess - Please enter a number between 1 and " + limit;
        //compare guess to computer number
        else if (userGuess == computerNumber)
            return "You guessed correctly!\nNumber of tries: " + count;
        else if (userGuess < computerNumber)
            return "Your guess is too low, please try again!\nNumber of tries: " + count;
        else if(userGuess > computerNumber)
            return "Your guess is too high, please try again!\nNumber of tries: " + count;
        //invalid
        else
            return "Invalid guess, please try again!\nNumber of tries: " + count;
    }
    public static String calcFirstGuess(int userGuess, int computerNumber, int count)
    {
        //Will concatenate to message dialog if user guesses first try
        String congrats = "";
        if(userGuess == computerNumber && count == 1)
            congrats = "\n\nCongratulations! You guessed the computer number on your first guess!";
        return congrats;
    }
    public static boolean playAgain()
    {
        int userChoice = JOptionPane.showConfirmDialog(null,
        "Would you like to play again?", "Continue?",
                JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if(userChoice == JOptionPane.YES_OPTION)
            return true;
        else
            return false;
    }
}