public class SuperTrumps extends Card {

    SuperTrumps (String n){
        //Constructor of Supertrump card
        super (n);
    }

    public String cardEffect() {
        // Useful for setting up the game mode based on the Supertrump card name
        String effect = "";
        String n = getCardName();
        if (n.equals("The Mineralogist")){
            effect = "CLE";
        }
        else if (n.equals("The Geologist")){
            effect = "CHOICE";
        }
        else if (n.equals("The Geophysicist")){
            effect = "GRAV/MAG";
        }
        else if (n.equals("The Gemmologist")){
            effect = "HARD";
        }
        else if (n.equals("The Miner")){
            effect = "ECO";
        }
        else if (n.equals("The Petrologist")){
            effect = "ABU";
        }
        return effect;
    }

    public String effectDescription() {
        // Adding the description to the Supertrump card based on the name
        String desc = "";
        String n = getCardName();
        if (n.equals("The Mineralogist")){
            desc = "changes the trumps category to Cleavage";
        }
        else if (n.equals("The Geologist")){
            desc = "changes the trumps category of your choice";
        }
        else if (n.equals("The Geophysicist")){
            desc = "changes the trumps category to Specific Gravity (or throw magnetite to win the hand)";
        }
        else if (n.equals("The Gemmologist")){
            desc = "changes the trumps category to Hardness";
        }
        else if (n.equals("The Miner")){
            desc = "changes the trumps category to Economic Value";
        }
        else if (n.equals("The Petrologist")){
            desc = "changes the trumps category to Crystal Abundance";
        }
        return desc;
    }
}
