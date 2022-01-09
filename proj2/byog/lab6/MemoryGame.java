package byog.lab6;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {

        /** if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }
        */
        //int seed = Integer.parseInt(args[0]);
        int seed = 3456;
        MemoryGame game = new MemoryGame(80, 40, seed);
        game.startGame();

    }

    public MemoryGame(int width, int height, int seed) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
        rand = new Random(seed);
    }


    /**
     * generate random character depends on seed.
     * @return single random character
     */
    private Character generateRandomCha() {
        int chaNum = rand.nextInt(26);
        return CHARACTERS[chaNum];
    }


    /**
     * generate a random string
     * @param n length of string
     * @return a random string of length n
     */
    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        StringBuilder randomStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            randomStr.append(generateRandomCha());
        }
        return randomStr.toString();
    }


    public void drawFrame(String s, int x, int y) {
        //TODO: Take the string and display it in the center of the screen
        String displayStr = s;
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setPenColor(StdDraw.WHITE);

        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.text(x, y, displayStr);
        StdDraw.show();
    }

    public void flashSequence(String letters, int x, int y) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        char[] letterArray  = letters.toCharArray();
        for (int i = 0; i < letterArray.length; i++) {
            String singleS = String.valueOf(letterArray[i]);
            StdDraw.clear(Color.BLACK);
            drawFrame(singleS, x, y);
            StdDraw.pause(1000);
            StdDraw.clear(Color.BLACK);
            drawFrame("", x, y);
            StdDraw.pause(500);
        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        StringBuilder strInput = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (StdDraw.hasNextKeyTyped()) {
                strInput.append(StdDraw.nextKeyTyped());
                i++;
            }
        }
        return strInput.toString();
    }

    /**
     * this method is used to throw away the character player types in before the time that they should type in.
     * @return
     */
    private String trushNCharsInput() {
        StringBuilder trushInput = new StringBuilder();
        while (StdDraw.hasNextKeyTyped()) {
            trushInput.append(StdDraw.nextKeyTyped());
        }
        return trushInput.toString();
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        round = 1;
        gameOver = false;
        playerTurn = false;
        //TODO: Establish Game loop
        while (!gameOver) {
            // before every round, clear the screen.
            StdDraw.clear(Color.black);
            // start round n, pause 3 seconds.
            drawFrame("Round " + round, width / 2, height / 2);
            StdDraw.pause(3000);
            // generate round length random string, display to player.
            String randomstr = generateRandomString(round);
            flashSequence(randomstr, width / 2, height / 2);
            // the character player type in is useless until it is player's turn to type in.
            String trushStr = trushNCharsInput();
            // tell player it's time to type in , and receive the content being typed.
            drawFrame("Now your turn to type", width / 2, height / 2);
            String playertype = solicitNCharsInput(round);
            // tell player the goal string and the content that player typed in.
            StdDraw.clear(Color.black);
            drawFrame("Goal str: " + randomstr, width / 2, height / 2 + 4);
            drawFrame("Your input: " + playertype, width / 2, height / 2 - 4);
            StdDraw.pause(4000);
            // If player got it correct, increasing the round by 1, and repeat while loop.
            if (playertype.equals(randomstr)) {
                round++;
            }
            // If they got it wrong, end the game and display the message.
            else{
                StdDraw.clear(Color.black);
                drawFrame("Game Over!", width / 2, height / 2 + 4);
                drawFrame("You made it to round " + round + ".", width / 2, height / 2 - 4);
                StdDraw.pause(5000);
                gameOver = true;
            }
        }
    }
}
