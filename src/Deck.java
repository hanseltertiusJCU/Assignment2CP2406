import java.util.ArrayList;
import java.util.Random;

public class Deck {
    //Create a Deck class that is based on the card lists in the .txt file
    private ArrayList<Card> cardDeck;

    Deck(ArrayList<Card> listOfcard){
        //Constructor of the Deck
        cardDeck = listOfcard;
    }

    public Card drawnCard(){
        //Remove the card when the player drawn the random card (prevent duplicate card)
        int rc = new Random().nextInt(cardDeck.size());
        Card getCard = cardDeck.get(rc);
        cardDeck.remove(rc);
        return getCard;
    }

    public ArrayList<Card> getCardDeckContent() {
        //Get the contents of the deck
        return cardDeck;
    }
}
