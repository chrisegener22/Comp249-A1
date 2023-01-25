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

            int p1LastPos = player1Cord;
            int p2LastPos = player2Cord;

            //checks who the starting player is
            if(startingPlayer == 1) {
                valueflipDicePlayer1 = flipDice();
                valueflipDicePlayer2 = flipDice();
                player1Cord += valueflipDicePlayer1;
                player2Cord += valueflipDicePlayer2;
                System.out.println("\nPlayer 1 rolled a: " + valueflipDicePlayer1 + "\nPlayer 2 rolled a: " + valueflipDicePlayer2);
                sameSquare(p1LastPos,p2LastPos);
            }

            else if(startingPlayer == 2){
                valueflipDicePlayer1 = flipDice();
                valueflipDicePlayer2 = flipDice();
                player1Cord += valueflipDicePlayer1;
                player2Cord += valueflipDicePlayer2;
                player1Cord = snakeorladder(player1Cord);
                player2Cord = snakeorladder(player2Cord);
                System.out.println( "\nPlayer 2 rolled a: " + valueflipDicePlayer2 + "\nPlayer 1 rolled a: " + valueflipDicePlayer1);
                sameSquare(p2LastPos, p1LastPos);
            }
            //resets board
            initializeGameBoard();
            //adds player positions to the game board
            player1xCord = (player1Cord%10)-1;
            player2xCord = (player2Cord%10)-1;
            player1yCord = (player1Cord+1 - player1xCord) /10;
            player2yCord = (player2Cord+1 - player2xCord) /10;
            System.out.println("player 1, x: " + player1xCord + ", y: " + player1yCord + ", total: " + player1Cord);
            System.out.println("player 2, x: " + player2xCord + ", y: " + player2yCord + ", total: " + player2Cord);

            if(player1xCord < 0){
                player1xCord = 0;
            }
            else;
            if(player2xCord < 0){
                player2xCord = 0;
            }
            else;
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

        System.out.println("The starting player is player: " + startingPlayer + "\nIt took " + numOfTimesFlipped + " iterations to find who starts\nThe game will now commence\nH'stands start of a ladder and + is end, and O's stand for start of snake and - is end\n");

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
        //1-38
        gameBoard[0][0] = 'H';
        gameBoard[7][3] = '+';
        //4-14
        gameBoard[3][0] = 'H';
        gameBoard[3][1] = '+';
        //9-31
        gameBoard[8][0] = 'H';
        gameBoard[0][3] = '+';
        //28-84
        gameBoard[7][2] = 'H';
        gameBoard[3][8] = '+';
        //21-42
        gameBoard[0][2] = 'H';
        gameBoard[1][4] = '+';
        //36-44
        gameBoard[5][3] = 'H';
        gameBoard[3][4] = '+';
        //51-67
        gameBoard[0][5] = 'H';
        gameBoard[6][6] = '+';
        //71-91
        gameBoard[0][7] = 'H';
        gameBoard[0][9] = '+';
        //80-100
        gameBoard[9][7] = 'H';
        gameBoard[9][9] = '+';

    }

    public void sameSquare(int lastp1, int lastp2) {
        //checks if the two players on the same square
        if (startingPlayer == 1) {
            if (player1Cord == lastp2) {
                player1Cord = 1;
                System.out.println("Player 1 landed on the same square as player 1, therefore is reset");
            }
            else if(player2Cord == player1Cord) {
                player2Cord = 1;
                System.out.println("Player 2 landed on the same square as player 1, therefore is reset");
            }
        }

        else if (startingPlayer == 2) {
            if (player2Cord == lastp1) {
                player2Cord = 1;
                System.out.println("Player 2 landed on the same square as player 1, therefore is reset");
            }
            else if(player1Cord == player2Cord) {
                player1Cord = 1;
                System.out.println("Player 1 landed on the same square as player 2, therefore is reset");
            }
        }
        else;

    }

    public int snakeorladder(int playerposition){

        boolean onLadder = false;
        boolean onSnake = false;

        switch (playerposition){
            case 1:
                playerposition = 38;
                onLadder = true;
                break;
            case 4:
                playerposition = 14;
                onLadder = true;
                break;
            case 9:
                playerposition = 31;
                onLadder = true;
                break;
            case 21:
                playerposition = 42;
                onLadder = true;
                break;
            case 28:
                playerposition = 84;
                onLadder = true;
                break;
            case 36:
                playerposition = 44;
                onLadder = true;
                break;
            case 51:
                playerposition = 54;
                onLadder = true;
                break;
            case 71:
                playerposition = 91;
                onLadder = true;
                break;
            case 80:
                playerposition = 100;
                onLadder = true;
                break;
            case 16:
                playerposition = 6;
                onSnake = true;
                break;
            case 48:
                playerposition = 30;
                onSnake = true;
                break;
            case 62:
                playerposition = 19;
                onSnake = true;
                break;
            case 64:
                playerposition = 60;
                onSnake = true;
                break;
            case 93:
                playerposition = 68;
                onSnake = true;
                break;
            case 95:
                playerposition = 24;
                onSnake = true;
                break;
            case 97:
                playerposition = 76;
                onSnake = true;
                break;
            case 98:
                playerposition = 78;
                onLadder = true;
                break;
            default:
                break;
        }

        if(onLadder == true){
            System.out.println("Congrats you hit a ladder");
        }
        else if(onSnake == true){
            System.out.println("Unfortunate, you hit a snake");
        }
        else;
        return playerposition;
    }
}
