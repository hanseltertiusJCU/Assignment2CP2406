import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Card> cardUsed;
    private Deck cardDeck;
    private ArrayList<Player> players;
    private String gameMode;
    private String recentPlayer;

    Game(ArrayList<String> namePlayer, Deck deck){
        // Constructor of the game
        gameMode = "";
        cardUsed = new ArrayList<>();
        players = new ArrayList<>();
        cardDeck = deck;
        recentPlayer = "";
        for(int p = 0; p < namePlayer.size(); p++){
            // Enter the players' name for numPlayer times (based on the input
                players.add(new Player(namePlayer.get(p)));
            }
        for (int c = 0; c < 8; c++){
            // Draw the card until the players in the game have 8 card each
            for (Player player : players){
                player.drawPlayerCard(deck.drawnCard());
            }
        }
    }

    public String getGameMode(){
        return gameMode;
    }

    public String getGameModeMessage() {
        // To specify the game mode
        String gameMsg = "";
        if (gameMode.equals("HARD")){
            gameMsg = "Hardness";
        }
        else if (gameMode.equals("SPECGRAV")){
            gameMsg = "Specific Gravity";
        }
        else if (gameMode.equals("ECO")){
            gameMsg = "Economic Value";
        }
        else if (gameMode.equals("ABU")){
            gameMsg = "Crystal Abundance";
        }
        else if (gameMode.equals("CLE")){
            gameMsg = "Cleavage";
        }
        return gameMsg;
    }

    public void setGameMode(String gameMode){
        this.gameMode = gameMode;
    }

    public void restoreDeck() {
        // To retrieve the card back
        ArrayList<Card> restore = new ArrayList<>();
        for (Card cards: cardUsed)
        {
            restore.add(cards);
        }
        setCardDeck(new Deck(restore));
        cardUsed.clear();
        cardUsed.add(restore.get(restore.size()-1));
    }

    public void setCardDeck(Deck cardDeck){
        this.cardDeck = cardDeck;
    }

    public ArrayList<Player> getPlayers(){
        // Array list of players
        return players;
    }

    public Card getRecentCard(){
        return cardUsed.get(cardUsed.size()-1);
    }

    public boolean recentCardPlayed(){
        // To check whether there is the recent card played or not
        boolean played = false;
        if (cardUsed.size() > 0){
            played = true;
        }
        return played;
    }

    public Deck getCardDeck() {
        return cardDeck;
    }

    public boolean gameCard(Card card, Player play){
        // To get the comparison between the recent card and the card played
        boolean higherScore = false;
        int difference = 0;
        if(cardUsed.size() == 0 || this.playerGetAnotherTurn(play)) {
            if (card instanceof SuperTrumps) {
                gameMode = ((SuperTrumps) card).cardEffect();
            }
            higherScore = true;
        }
        else
        {
            if (card instanceof Mineral){
                if (getRecentCard() instanceof Mineral){
                    if (gameMode.equals("HARD")){
                        Float now = new Float(((Mineral) card).getCardHardness());
                        Float prev = new Float(((Mineral) getRecentCard()).getCardHardness());
                        difference = now.compareTo(prev);
                    }
                    else if (gameMode.equals("SPECGRAV")){
                        Float now = new Float(((Mineral) card).getCardSpecGravity());
                        Float prev = new Float(((Mineral) getRecentCard()).getCardSpecGravity());
                        difference = now.compareTo(prev);
                    }
                    else if (gameMode.equals("ECO")){
                        Float now = new Float(((Mineral) card).getCardEconomicValueScore());
                        Float prev = new Float(((Mineral) getRecentCard()).getCardEconomicValueScore());
                        difference = now.compareTo(prev);
                    }
                    else if (gameMode.equals("ABU")){
                        Float now = new Float(((Mineral) card).getCardCrystalAbundanceScore());
                        Float prev = new Float(((Mineral) getRecentCard()).getCardCrystalAbundanceScore());
                        difference = now.compareTo(prev);
                    }
                    else if (gameMode.equals("CLE")){
                        Float now = new Float(((Mineral) card).getCardCleavageScore());
                        Float prev = new Float(((Mineral) getRecentCard()).getCardCleavageScore());
                        difference = now.compareTo(prev);
                    }
                    if (difference > 0) {
                        // To allow the replacement of the recent card
                        higherScore = true;
                    }
                    else{
                        System.out.println("Invalid choice, your card doesn't have enough value compared to the recent card in the game");
                    }
                } else {
                    higherScore = true;
                }
            }
            else {
                setGameMode(((SuperTrumps) card).cardEffect());
                higherScore = true;
            }
        }
        return higherScore;
    }

    public void putCardToGame(Card card){
        cardUsed.add(card);
    }

    public String getRecentPlayer() {
        return recentPlayer;
    }


    public void setRecentPlayer(String recentPlayerTurn){
        this.recentPlayer = recentPlayerTurn;
    }

    public boolean playerGetAnotherTurn(Player playerGame){
        // To check the player that did not passed the card after any other player passed the card
        boolean anotherTurn = false;
        if (getRecentPlayer().equals(playerGame.getPlayerName()))
        {
            anotherTurn = true;
        }
        return anotherTurn;
    }

    public ArrayList<Card> getCardUsed() {
        return cardUsed;
    }

    // add the array

}
