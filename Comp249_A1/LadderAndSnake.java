package Comp249_A1;
/*
Name(s) and ID(s) Chris Egener 40196182
COMP249
Assignment # 1
Due Date Feb 3rd
*/

import java.util.Scanner;
import java.util.Set;
import static java.lang.System.exit;

public class LadderAndSnake {
    static char gameBoard[][] = new char[10][10];
    static int numPlayers = 0;
    static int player1Cord = 0;
    static int player2Cord = 0;
    static int player1xCord = 0;
    static int player2xCord = 0;
    static int player1yCord = 0;
    static int player2yCord = 0;
    static int valueflipDicePlayer1;
    static int valueflipDicePlayer2;
    static int numOfTimesFlipped;
    static boolean flippedSameNum = true;
    static int startingPlayer;
    Scanner myScanner = new Scanner(System.in);

    public LadderAndSnake() {
        welcomeFunction();
        myScanner.nextLine();
        //starts game
        while(player1Cord != 100 && player2Cord != 100){

            //checks who the starting player is
            if(startingPlayer == 1) {
                valueflipDicePlayer1 = flipDice();
                valueflipDicePlayer2 = flipDice();
                player1Cord += valueflipDicePlayer1;
                player2Cord += valueflipDicePlayer2;
                System.out.println("Player 1 rolled a: " + valueflipDicePlayer1 + "\nPlayer 2 rolled a: " + valueflipDicePlayer2);
            }

            else if(startingPlayer == 2) {
                valueflipDicePlayer1 = flipDice();
                valueflipDicePlayer2 = flipDice();
                player1Cord += valueflipDicePlayer1;
                player2Cord += valueflipDicePlayer2;
                System.out.println( "\nPlayer 2 rolled a: " + valueflipDicePlayer2 + "\nPlayer 1 rolled a: " + valueflipDicePlayer1);
            }
            //resets board
            initializeGameBoard();
            //adds player positions to the game board
            player1xCord = player1Cord%10;
            player2xCord = player2Cord%10;
            player1yCord = (player1Cord - player1xCord) /10;
            player2yCord = (player2Cord - player2xCord) /10;
            gameBoard[player1xCord][player1yCord] = '1';
            gameBoard[player2xCord][player2yCord] = '2';

            for(int i = 0; i < 10; i++) {
                for(int k = 0; k < 10; k++) {
                    System.out.print(gameBoard[i][k]);
                }
                System.out.println();
            }

            System.out.println("Press enter to continue");
            myScanner.nextLine();
        }

    }

    //displays welcome message
    public void welcomeFunction() {
        System.out.println("   ,-,--.  .-._         ,---.      ,--.-.,-.      ,----.    ,-,--.  ");
        System.out.println(" ,-.'-  _\\/==/ \\  .-._.--.'  \\    /==/- |\\  \\  ,-.--` , \\ ,-.'-  _\\ ");
        System.out.println("/==/_ ,_.'|==|, \\/ /, |==\\-/\\ \\   |==|_ `/_ / |==|-  _.-`/==/_ ,_.' ");
        System.out.println("\\==\\  \\   |==|-  \\|  |/==/-|_\\ |  |==| ,   /  |==|   `.-.\\==\\  \\    ");
        System.out.println(" \\==\\ -\\  |==| ,  | -|\\==\\,   - \\ |==|-  .|  /==/_ ,    / \\==\\ -\\   ");
        System.out.println(" _\\==\\ ,\\ |==| -   _ |/==/ -   ,| |==| _ , \\ |==|    .-'  _\\==\\ ,\\  ");
        System.out.println("/==/\\/ _ ||==|  /\\ , /==/-  /\\ - \\/==/  '\\  ||==|_  ,`-._/==/\\/ _ | ");
        System.out.println("\\==\\ - , //==/, | |- \\==\\ _.\\=\\.-'\\==\\ /\\=\\.'/==/ ,     /\\==\\ - , / ");
        System.out.println(" `--`---' `--`./  `--``--`         `--`      `--`-----``  `--`---'  ");

        System.out.println("   ,---.      .-._                     ");
        System.out.println(" .--.'  \\    /==/ \\  .-._  _,..---._   ");
        System.out.println(" \\==\\-/\\ \\   |==|, \\/ /, /==/,   -  \\  ");
        System.out.println(" /==/-|_\\ |  |==|-  \\|  ||==|   _   _\\ ");
        System.out.println(" \\==\\,   - \\ |==| ,  | -||==|  .=.   | ");
        System.out.println(" /==/ -   ,| |==| -   _ ||==|,|   | -| ");
        System.out.println("/==/-  /\\ - \\|==|  /\\ , ||==|  '='   / ");
        System.out.println("\\==\\ _.\\=\\.-'/==/, | |- ||==|-,   _`/  ");
        System.out.println(" `--`        `--`./  `--``-.`.____.'   ");

        System.out.println("             ,---.                                  ,----.                  ,-,--.  ");
        System.out.println("   _.-.    .--.'  \\      _,..---._   _,..---._   ,-.--` , \\  .-.,.---.    ,-.'-  _\\ ");
        System.out.println(" .-,.'|    \\==\\-/\\ \\   /==/,   -  \\/==/,   -  \\ |==|-  _.-` /==/  `   \\  /==/_ ,_.' ");
        System.out.println("|==|, |    /==/-|_\\ |  |==|   _   _\\==|   _   _\\|==|   `.-.|==|-, .=., | \\==\\  \\    ");
        System.out.println("|==|- |    \\==\\,   - \\ |==|  .=.   |==|  .=.   /==/_ ,    /|==|   '='  /  \\==\\ -\\   ");
        System.out.println("|==|, |    /==/ -   ,| |==|,|   | -|==|,|   | -|==|    .-' |==|- ,   .'   _\\==\\ ,\\  ");
        System.out.println("|==|- `-._/==/-  /\\ - \\|==|  '='   /==|  '='   /==|_  ,`-._|==|_  . ,'.  /==/\\/ _ | ");
        System.out.println("/==/ - , ,|==\\ _.\\=\\.-'|==|-,   _`/|==|-,   _`//==/ ,     //==/  /\\ ,  ) \\==\\ - , / ");
        System.out.println("`--`-----' `--`        `-.`.____.' `-.`.____.' `--`-----`` `--`-`--`--'   `--`---'  ");

        initializeGameBoard();
        System.out.print("\nWelcome! Please enter the numbers of players(limited to 2 for the time being): ");

        //takes in user input for the amount of players
        numPlayers = myScanner.nextInt();

        //checks to see if the amount of players is a valid number

        if(numPlayers > 2) {
            System.out.println("Initialization was attempted for " + numPlayers + " member of players; however, this is only\n"
                    + "expected for an extended version the game. Value will be set to 2");
            numPlayers = 2;
        }

        else if(numPlayers < 2){
            System.out.println("Error: Cannot execute the game with less than 2 players! Will exit");
            exit(0);
        }

        else;
        //determining who goes first
        System.out.println("You will now roll to find the order of players");

        while(flippedSameNum == true) {
            numOfTimesFlipped++;
            valueflipDicePlayer1 = flipDice();
            valueflipDicePlayer2 = flipDice();
            System.out.println("Player 1 rolled a: " + valueflipDicePlayer1 + "\nPlayer 2 rolled a: " + valueflipDicePlayer2);
            //checks if players rolled the same number
            if(valueflipDicePlayer1 != valueflipDicePlayer2) {
                flippedSameNum = false;
            }
            else {
                System.out.println("We will now reroll because all players got the same number");
            }
        }

        if(valueflipDicePlayer1 > valueflipDicePlayer2) {
            startingPlayer = 1;
        }
        else if(valueflipDicePlayer1 < valueflipDicePlayer2) {
            startingPlayer = 2;
        }

        System.out.println("The starting player is player: " + startingPlayer + "\nIt took " + numOfTimesFlipped + " iterations to find who starts\nThe game will now commence\nH'stand for ladders, and O's stand for snakes\n");

    }

    //flip dice function
    public int flipDice() {
        int returnValue = 0;

        returnValue = (int)Math.floor(Math.random() *(6 - 1 + 1) + 1);

        return returnValue;
    }
    //creates gameboard
    public void initializeGameBoard() {

        for(int i = 0; i < 10; i++) {
            for(int k = 0; k < 10; k++) {
                gameBoard[i][k] = '.';
            }
        }

    }
}
