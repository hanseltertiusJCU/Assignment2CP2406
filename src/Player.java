import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    // Store player names, which depends on the number of the players
    private ArrayList<Card> playerHand;
    private String playerName;

    Player (String name){
        // Constructor for the player
        playerHand = new ArrayList<>();
        playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Card getPlayerCard(int c){
        return playerHand.get(c);
    }

    public void drawPlayerCard(Card card){
        playerHand.add(card);
    }

    public void playerTurn(Game game){
        // It is used to state every action during the player turn
        int cardHandNum;
        boolean nextPlayer = false;
        String recentCardDesc;
        if(game.recentCardPlayed() && !(game.playerGetAnotherTurn(this))){
            if(game.getRecentCard() instanceof Mineral){
                recentCardDesc = "\nRecent card = Name: " + game.getRecentCard().getCardName() + "  " +
                        "Hardness: " + ((Mineral) game.getRecentCard()).getCardHardness() + "  " +
                        "Specific Gravity: " + ((Mineral) game.getRecentCard()).getCardSpecGravity() + "  " +
                        "Cleavage: " + ((Mineral) game.getRecentCard()).getCardCleavage() + "  " +
                        "Crystal Abundance: " + ((Mineral) game.getRecentCard()).getCardCrystalAbundance() + "  " +
                        "Economic Value: " + ((Mineral) game.getRecentCard()).getCardEconomicValue() + "\n"; // The recent card description of the Mineral card
            }
        else{
                recentCardDesc = "\nThe recent player play the " + ((SuperTrumps) game.getRecentCard()).getCardName() + " card" + "\n"; // The recent card description of the Supertrumps card
            }
        }
        else if(game.recentCardPlayed() && game.playerGetAnotherTurn(this)){
            System.out.println("You are granted to select the trump mode again");
            recentCardDesc = "\nYou may pick the card again as you made all of other players pass" + "\n";
            gameStart(game); // Allowing the player that is not passed to pick up another trump category
        }
        else {
            recentCardDesc = "\nThere are no recent cards that have been played" + "\n";
            gameStart(game); // Description when there is no cards that is been played (when it is the start of the game)
        }
        while (!nextPlayer){
            // Used for looping until the person did the action (play the card or pass), which allows the next player to move
            String playerChoice;
            System.out.println("Game mode: " + game.getGameModeMessage() + recentCardDesc + showCardInHand() + getPlayerName() + ", enter the card number you want to play or simply enter PASS to pass");
            System.out.print(">>>");
            Scanner options = new Scanner(System.in);
            playerChoice = options.nextLine();
            if (playerChoice.toUpperCase().equals("PASS")){
                drawPlayerCard(game.getCardDeck().drawnCard());
                nextPlayer = true;
            }
            else{
                try {
                    cardHandNum = Integer.parseInt(playerChoice); // Changing the input into int from String
                    Card cardPlayed = getPlayerCard(cardHandNum); // Used for trying to get the card played
                    boolean continueGame = game.gameCard(cardPlayed, this); // Trying to play the card
                    if(game.getGameMode().equals("CHOICE")){ // Decision when a player play a special supertrump card to allow player to freely choose the trump mode
                        game.putCardToGame(cardPlayed);
                        playerHand.remove((cardHandNum));
                        gameStart(game);
                        game.setRecentPlayer(this.getPlayerName());
                        nextPlayer = true;
                    }
                    else if (game.getGameMode().equals("GRAV/MAG")) {
                        if(lookAtWinCard()) { // Check whether or not the player has the winning card, which is Magnetite
                            for (Card cardInHand : playerHand) {
                                game.putCardToGame(cardInHand);
                                playerHand.remove((cardInHand));
                                game.setRecentPlayer(this.getPlayerName());
                            }
                            game.setGameMode("SPECGRAV"); // Change the game mode to Specific Gravity
                            nextPlayer = true;
                        }
                        else {
                            game.setGameMode("SPECGRAV"); // Change the game mode to Specific Gravity
                        }
                        if(continueGame){
                            game.putCardToGame(cardPlayed);
                            playerHand.remove((cardHandNum));
                            game.setRecentPlayer(this.getPlayerName());
                            nextPlayer = true;
                        }
                    }
                    else {
                        if(continueGame){
                            // Used for the normal card play
                            game.putCardToGame(cardPlayed);
                            playerHand.remove((cardHandNum));
                            game.setRecentPlayer(this.getPlayerName());
                            nextPlayer = true;
                        }
                    }
                }
                catch (Throwable e){
                    System.out.println("Invalid input!"); // When the user try to enter the input that does not match the card in the hand
                }
            }
        }
        if (playerHand.size() == 0){
            // Occurs when the player has no more card in the hand
            game.setRecentPlayer(game.getPlayers().get((game.getPlayers().indexOf(this)+1)%game.getPlayers().size()).getPlayerName());
            playerLeft(game);
        }
    }


    public String showCardInHand(){
        // Used for showing the cards in each players' hand
        String handCard = "";
        int cardNum = 0;
        for(Card cards: playerHand)
        {
            String cardDesc;
            if (cards instanceof Mineral) {
                // Description of the Mineral card
                cardDesc = "No: " + cardNum + "  " +
                        "Name: " + cards.getCardName() + "  " +
                        "Hardness: " + ((Mineral) cards).getCardHardness() + "  " +
                        "Specific Gravity: " + ((Mineral) cards).getCardSpecGravity() + "  " +
                        "Cleavage: " + ((Mineral) cards).getCardCleavage() + "  " +
                        "Crystal Abundance: " + ((Mineral) cards).getCardCrystalAbundance() + "  " +
                        "Economic Value: " + ((Mineral) cards).getCardEconomicValue() + "\n";
            }
            else{
                // Description of the Supertrump card
                cardDesc = "No: "+ cardNum+ "  " + "Name: " + cards.getCardName()+ "   " + "Description: " +
                        ((SuperTrumps) cards).effectDescription()+ "\n";
            }
            cardNum += 1;
            handCard += cardDesc; // Used for adding the description of the card in the hand
        }
        return handCard;
    }

    public void gameStart(Game starter){
        // Allowing the player to enter the game mode
        String getGameMode;
        System.out.println("Enter the mode you want to play. Player: " + getPlayerName() + "\n" + showCardInHand() + "(HARD) Hardness" +"\n(SPECGRAV) Specific Gravity" + "\n(CLE) Cleavage" + "\n(ABU) Crystal Abundance" + "\n(ECO) Economic Value");
        System.out.print(">>>");
        Scanner gameMode = new Scanner(System.in);
        getGameMode = gameMode.nextLine();
        while (!(getGameMode.equals("HARD") || getGameMode.equals("SPECGRAV") || getGameMode.equals("ECO") || getGameMode.equals("ABU") || getGameMode.equals("CLE") || getGameMode .equals("hard") || getGameMode.equals("specgrav") || getGameMode.equals("eco") || getGameMode.equals("abu") || getGameMode.equals("cle"))){
            // If the player enter the game mode that does not match the criteria
            System.out.println("Invalid game mode!");
            System.out.println("Enter the mode you want to play. Player: " + getPlayerName() + "\n" + showCardInHand() + "(HARD) Hardness" +"\n(SPECGRAV) Specific Gravity" + "\n(CLE) Cleavage" + "\n(ABU) Crystal Abundance" + "\n(ECO) Economic Value");
            System.out.print(">>>");
            getGameMode = gameMode.nextLine();
        }
        starter.setGameMode(getGameMode.toUpperCase()); // Accepting uppercase/lowercase input
    }

    public boolean lookAtWinCard(){
        // To check if there is the winning card or not when the player put a certain Supertrump card
        boolean winCard = false;
        for(Card cards: playerHand){
            if(cards.getCardName().equals("Magnetite")){
                winCard = true;
            }
        }
        return winCard;
    }


    public void playerLeft(Game game){
        // Used for removing the player from the game
        game.getPlayers().remove(this);
        System.out.println("Player " + this.getPlayerName() + " has left the game");
    }
}
