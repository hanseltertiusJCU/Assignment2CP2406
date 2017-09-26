import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        // Main program of the game
        ArrayList<Card> listCard = new ArrayList<>();
        String[] fileArray;
        String fileString = "";

        int numPlayer = 0;
        Boolean repeat = true;


        while (repeat) {
            // Used to repeat the input when the input does not match the criteria
            Scanner num = new Scanner(System.in);
            try {
                System.out.println("Enter the number of players (3-5 players)");
                System.out.print(">>>");
                numPlayer = num.nextInt();
                repeat = false;
                if (numPlayer < 3 || numPlayer > 5)   {
                    System.out.println("The amount of players does not match the specified criteria");
                    repeat = true;
                }
            } catch (Exception e) {
                System.out.println("The input type does not match the specified criteria");
                repeat = true;
            }
        }

        Path file = Paths.get("D:\\Bachelor of IT\\CP2406\\Assignment\\Assignment2CP2406\\src\\card.txt"); // Used for file reading (change directory for different computers, otherwise it won't work)
        try {
            InputStream fileInput = new BufferedInputStream(Files.newInputStream(file));
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileInput));
            fileReader.readLine();
            while ((fileString = fileReader.readLine()) != null){
                fileArray = fileString.split(",");
                listCard.add(new Mineral(fileArray[0], Float.valueOf(fileArray[1]), Float.valueOf(fileArray[2]),fileArray[3],fileArray[4],fileArray[5]));
            }
            // Adding the Supertrump card
            listCard.add(new SuperTrumps("The Mineralogist"));
            listCard.add(new SuperTrumps("The Geologist"));
            listCard.add(new SuperTrumps("The Geophysicist"));
            listCard.add(new SuperTrumps("The Petrologist"));
            listCard.add(new SuperTrumps("The Miner"));
            listCard.add(new SuperTrumps("The Gemmologist"));
        }
        catch (Exception e){
            System.out.println("Message: " + e.getMessage());
        }
        Deck deck = new Deck(listCard);
        Game game = new Game(numPlayer, deck);
        int counter = 0;
        while (game.getPlayers().size() > 1){
            // Loop until there is only a player left
            int playerNum = counter%game.getPlayers().size();
            if(game.getCardDeck().getCardDeckContent().size() == 0) {
                game.restoreDeck();
            }
            else{

                game.getPlayers().get(playerNum).playerTurn(game); // Using the turn for the players
                counter += 1;
            }
        }
        System.out.println("The game is over, the loser is " + game.getPlayers().get(0).getPlayerName());
    }
}
