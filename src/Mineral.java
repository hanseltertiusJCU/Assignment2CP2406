public class Mineral extends Card{

    private float cardHardness;
    private float cardSpecGravity;
    private String cardCleavage;
    private int cardCleavageScore;
    private String cardCrystalAbundance;
    private int cardCrystalAbundanceScore;
    private String cardEconomicValue;
    private int cardEconomicValueScore;

    Mineral(String n, float hardness, float specificGravity, String cleavage, String crystalAbundance, String economicValue)
    {
        //Constructor of the Mineral card/ non Supertrump card
        super(n);
        cardHardness = hardness;
        cardSpecGravity = specificGravity;
        cardCleavage = cleavage;
        cardCrystalAbundance = crystalAbundance;
        cardEconomicValue = economicValue;
        cardCleavageScore = convertCleavageScore();
        cardCrystalAbundanceScore = convertAbundanceScore();
        cardEconomicValueScore = convertEconomicScore();

    }

    public int getCardCleavageScore() {
        return cardCleavageScore;
    }

    public int getCardCrystalAbundanceScore() {
        return cardCrystalAbundanceScore;
    }

    public int getCardEconomicValueScore() {
        return cardEconomicValueScore;
    }

    public float getCardHardness() {
        return cardHardness;
    }

    public float getCardSpecGravity() {
        return cardSpecGravity;
    }

    public String getCardCleavage() {
        return cardCleavage;
    }

    public String getCardCrystalAbundance() {
        return cardCrystalAbundance;
    }

    public String getCardEconomicValue() {
        return cardEconomicValue;
    }

    public int convertCleavageScore(){
        int cleavageScore = 0;
        String c = getCardCleavage();
        if(c.equals("none")){
            cleavageScore = 1;
        }
        else if(c.equals("poor/none")){
            cleavageScore = 2;
        }
        else if (c.equals("1 poor")){
            cleavageScore = 3;
        }
        else if (c.equals("2 poor")){
            cleavageScore = 4;
        }
        else if (c.equals("1 good")){
            cleavageScore = 5;
        }
        else if (c.equals("1 good/1 poor")){
            cleavageScore = 6;
        }
        else if (c.equals("2 good")){
            cleavageScore = 7;
        }
        else if (c.equals("3 good")){
            cleavageScore = 8;
        }
        else if (c.equals("1 perfect")){
            cleavageScore = 9;
        }
        else if (c .equals("1 perfect/1 good")){
            cleavageScore = 10;
        }
        else if (c.equals("1 perfect/2 good")){
            cleavageScore = 11;
        }
        else if (c.equals("2 perfect/1 good")){
            cleavageScore = 12;
        }
        else if (c.equals("3 perfect")){
            cleavageScore = 13;
        }
        else if (c.equals("4 perfect")){
            cleavageScore = 14;
        }
        else if (c.equals("6 perfect")){
            cleavageScore = 15;
        }
        return cleavageScore;

    }

    public int convertAbundanceScore(){
        int abundanceScore = 0;
        String a = getCardCrystalAbundance();
        if (a.equals("ultratrace")){
            abundanceScore = 1;
        }
        else if (a.equals("trace")){
            abundanceScore = 2;
        }
        else if (a.equals("low")){
            abundanceScore = 3;
        }
        else if (a.equals("moderate")){
            abundanceScore = 4;
        }
        else if (a.equals("high")){
            abundanceScore = 5;
        }
        else if (a.equals("very high")){
            abundanceScore = 6;
        }
        return abundanceScore;
    }

    public int convertEconomicScore(){
        int economicScore = 0;
        String e = getCardEconomicValue();
        if (e.equals("trivial")){
            economicScore = 1;
        }
        else if (e.equals("low")){
            economicScore = 2;
        }
        else if (e.equals("moderate")){
            economicScore = 3;
        }
        else if (e.equals("high")){
            economicScore = 4;
        }
        else if (e.equals("very high")){
            economicScore = 5;
        }
        else if (e.equals("I'm rich!")){
            economicScore = 6;
        }
        return economicScore;
    }
}
