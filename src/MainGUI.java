import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MainGUI extends JFrame implements ActionListener {
    //Constructors of the class
    JLabel gameGuide = new JLabel("Enter the number of players");
    JPanel mainScreen = new JPanel();
    JButton threePlayers = new JButton("3");
    JButton fourPlayers = new JButton("4");
    JButton fivePlayers = new JButton("5");
    JTextField playerName = new JTextField(20);
    JButton playerDone = new JButton("Done");
    JPanel gameScreen = new JPanel();
    JPanel tableScreen = new JPanel(new FlowLayout());
    JPanel cardsInHand = new JPanel(new GridLayout(2,8));
    ImageIcon deckCover = new ImageIcon("images\\Slide65.jpg");
    JButton deckCard = new JButton(new ImageIcon(deckCover.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH)));
    JLabel recentCard = new JLabel("No Card Played Recently");
    JButton hardnessCategory = new JButton("Hardness");
    JButton specificGravityCategory = new JButton("Specific Gravity");
    JButton cleavageCategory = new JButton("Cleavage");
    JButton crystalAbundanceCategory = new JButton("Crystal Abundance");
    JButton economicValueCategory = new JButton("Economic Value");
    ArrayList<JButton> listOfJButtons = new ArrayList<>();
    boolean pickMode = false;
    int playerTurn = 0;
    int playerDoneCount = 1;
    int numberOfPlayers;
    Game tableGame;
    ArrayList<String> playersList;
    Deck gameDeck;
    int passCount=0;
    ArrayList<String> winners;
    MainGUI()
    {
        super("Mineral Supertrumps");
        ArrayList<Card> cardList = new ArrayList<Card>();
        winners = new ArrayList<String>();
        playersList = new ArrayList<String>();
        setLayout(new BorderLayout());
        gameScreen.setLayout(new BoxLayout(gameScreen,BoxLayout.Y_AXIS));
        cardsInHand.setMaximumSize(new Dimension(1920,1080));
        gameGuide.setFont(new Font("Sans Serif",Font.PLAIN, 20));
        recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
        add(mainScreen,BorderLayout.CENTER);
        add(gameGuide,BorderLayout.NORTH);
        gameGuide.setHorizontalAlignment(JLabel.CENTER);
        playerDone.addActionListener(this);
        playerDone.setBackground(Color.WHITE); // set background color
        deckCard.addActionListener(this);
        deckCard.setBackground(Color.WHITE);
        hardnessCategory.addActionListener(this);
        hardnessCategory.setBackground(Color.RED);
        hardnessCategory.setFont(new Font("Sans Serif",Font.PLAIN, 12)); // set font type and size
        specificGravityCategory.addActionListener(this);
        specificGravityCategory.setBackground(Color.BLUE);
        specificGravityCategory.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        cleavageCategory.addActionListener(this);
        cleavageCategory.setBackground(Color.LIGHT_GRAY);
        cleavageCategory.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        crystalAbundanceCategory.addActionListener(this);
        crystalAbundanceCategory.setBackground(Color.CYAN);
        crystalAbundanceCategory.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        economicValueCategory.addActionListener(this);
        economicValueCategory.setBackground(Color.YELLOW);
        economicValueCategory.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        setSize(1920,1080);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        String[] array;
        String string = "";
        Path file =
                Paths.get("D:\\Bachelor of IT\\CP2406\\Assignment\\Assignment2CP2406\\src\\card.txt"); // Path file to get the data of the cards
        try
        {
            InputStream input = new BufferedInputStream(Files.newInputStream(file)); // input the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(input)); // read the file
            reader.readLine();
            int y=1;
            while ((string = reader.readLine()) != null){
                array = string.split(",");
                ImageIcon image = new ImageIcon("images\\Slide"+y+".jpg");// load the image file of the card
                cardList.add(new MineralWithPic(array[0],Float.valueOf(array[1]),Float.valueOf(array[2]),array[3],array[4],array[5],new ImageIcon(image.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
                y++;
            }
            ImageIcon image1 = new ImageIcon("images\\Slide58.jpg"); // load the image file of the Supertrumps card
            cardList.add(new SuperTrumpsWithPic("The Mineralogist",new ImageIcon(image1.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon image2 = new ImageIcon("images\\Slide60.jpg");
            cardList.add(new SuperTrumpsWithPic("The Geologist",new ImageIcon(image2.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon image3 = new ImageIcon("images\\Slide59.jpg");
            cardList.add(new SuperTrumpsWithPic("The Geophysicist",new ImageIcon(image3.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon image4 = new ImageIcon("images\\Slide56.jpg");
            cardList.add(new SuperTrumpsWithPic("The Petrologist",new ImageIcon(image4.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon image5 = new ImageIcon("images\\Slide55.jpg");
            cardList.add(new SuperTrumpsWithPic("The Miner",new ImageIcon(image5.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
            ImageIcon image6 = new ImageIcon("images\\Slide57.jpg");
            cardList.add(new SuperTrumpsWithPic("The Gemmologist",new ImageIcon(image6.getImage().getScaledInstance(200,300,Image.SCALE_SMOOTH))));
        }
        catch(Exception e)
        {
            gameGuide.setText("Error, please reopen the application");
        }
        gameDeck = new Deck(cardList);
        playerSelection();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==threePlayers)
        {
            numberOfPlayers = 3;
            mainScreen.removeAll();
            enterPlayerName();
        }
        else if(e.getSource()==fourPlayers)
        {
            numberOfPlayers = 4;
            mainScreen.removeAll();
            enterPlayerName();
        }
        else if(e.getSource()==fivePlayers) {
            numberOfPlayers = 5;
            mainScreen.removeAll();
            enterPlayerName();
        }
        else if(e.getSource()== playerDone)
        {
            if(playerDoneCount < numberOfPlayers)
            {
                // Used when the number of player in the input is less than number of players selected
                String name = playerName.getText();
                //Add the player
                if (!(name.equals(""))){
                    // This conditional is to prevent the empty string to add with the array
                    playersList.add(name);
                    playerDoneCount+=1;
                    gameGuide.setText("Enter player "+playerDoneCount+" name");
                    playerName.setText("");
                }

            }
            else
            {
                // Used when the number of player in the input is equal to number of players selected
                String name = playerName.getText();
                if (!(name.equals(""))){
                    // This conditional is to prevent the empty string to add with the array and to access the game
                    playersList.add(name);
                    tableGame = new Game(playersList,gameDeck);
                    pickTrump();
                }

            }
        }
        else if(e.getSource()==hardnessCategory)
        {
            tableGame.setGameMode("HARD");
            pickMode = true;
            passCount = 0;
            playCard();
        }
        else if(e.getSource()==specificGravityCategory)
        {
            tableGame.setGameMode("SPECGRAV");
            pickMode = true;
            passCount = 0;
            playCard();
        }
        else if(e.getSource()==cleavageCategory)
        {
            tableGame.setGameMode("CLE");
            pickMode = true;
            passCount = 0;
            playCard();
        }
        else if(e.getSource()==crystalAbundanceCategory)
        {
            tableGame.setGameMode("ABU");
            pickMode = true;
            passCount = 0;
            playCard();
        }
        else if(e.getSource()==economicValueCategory)
        {
            tableGame.setGameMode("ECO");
            pickMode = true;
            passCount = 0;
            playCard();
        }
        else if(e.getSource()==deckCard)
        {
            if (tableGame.getCardUsed().size()==0 || pickMode)
            {gameGuide.setText("You cannot pass, this is the first turn or you have picked the mode so you need to play a card");}
            else {
                tableGame.getPlayers().get(playerTurn % (tableGame.getPlayers().size())).drawPlayerCard(tableGame.getCardDeck().drawnCard());
                playerTurn++;
                passCount++;
                playCard();
            }
            if(tableGame.getCardDeck().getCardDeckContent().size()==0)
            {
                tableGame.restoreDeck();
            }
        }

        revalidate();
        repaint();
    }

    public static void main(String[] args) {

        MainGUI app = new MainGUI();
    }

    public void playerSelection()
    {
        // This method is used for selecting number of players for the game
        threePlayers.addActionListener(this);
        threePlayers.setBackground(Color.WHITE);
        threePlayers.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        fourPlayers.addActionListener(this);
        fourPlayers.setBackground(Color.WHITE);
        fourPlayers.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        fivePlayers.addActionListener(this);
        fivePlayers.setBackground(Color.WHITE);
        fivePlayers.setFont(new Font("Sans Serif",Font.PLAIN, 12));
        mainScreen.add(threePlayers);
        mainScreen.add(fourPlayers);
        mainScreen.add(fivePlayers);
    }

    public void pickTrump()
    {
        gameGuide.setText(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerName()+", choose the Trump mode you want to play");
        mainScreen.removeAll();
        gameScreen.removeAll();
        tableScreen.removeAll();
        cardsInHand.removeAll();
        mainScreen.add(gameScreen);
        gameScreen.add(tableScreen);
        gameScreen.add(cardsInHand);
        tableScreen.add(hardnessCategory);
        tableScreen.add(specificGravityCategory);
        tableScreen.add(cleavageCategory);
        tableScreen.add(crystalAbundanceCategory);
        tableScreen.add(economicValueCategory);
        int playerInTurnX = playerTurn%(tableGame.getPlayers().size());
        for(int x = 0; x< tableGame.getPlayers().get(playerInTurnX).getPlayerHand().size();x++)
        {
            if(tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x) instanceof Mineral)
            { // to add button for Mineral Card
                JButton cardButton = new JButton(new ImageIcon(((MineralWithPic) tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x)).getImageCard().getImage()));
                cardButton.setSize(200,300);
                cardButton.setBackground(Color.WHITE);
                cardsInHand.add(cardButton);

            }
            else if(tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x) instanceof SuperTrumps)
            { // to add button for Supertrumps Card
                JButton cardButton = new JButton(new ImageIcon(((SuperTrumpsWithPic) tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x)).getImageCard().getImage()));
                cardButton.setSize(200,300);
                cardButton.setBackground(Color.WHITE);
                cardsInHand.add(cardButton);
            }
        }


    }

    public void enterPlayerName()
    {
        // This method is used for inputting the player name
        gameGuide.setText("Enter player "+playerDoneCount+" name");
        mainScreen.removeAll();
        mainScreen.add(playerName);
        mainScreen.add(playerDone);
        while (gameGuide.getText().equals("")){
            // Looping the player name when the input is empty
            gameGuide.setText("Enter player "+playerDoneCount+" name");
            mainScreen.removeAll();
            mainScreen.add(playerName);
            mainScreen.add(playerDone);
        }

    }

    public void playCard()
    {
        if(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerName().equals(tableGame.getRecentPlayer())&& passCount == tableGame.getPlayers().size()-1)
        {
            pickTrump(); //Pick trump again if all the other players have pass the turn
            recentCard = new JLabel("No Card Played Recently");
            recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
        }
        else {
            // Validation for Mineral cards
            int playerInTurnX = playerTurn % (tableGame.getPlayers().size());
            gameGuide.setText("Your turn " + tableGame.getPlayers().get(playerInTurnX).getPlayerName() + ", click on the card you want to play. Current Trump Category = " + tableGame.getGameModeMessage() + " or click the deck to pass. Mode = " + tableGame.getGameMode() + ". Below is the card you have");
            // Remove and re add the components for GUI
            mainScreen.removeAll();
            tableScreen.removeAll();
            cardsInHand.removeAll();
            listOfJButtons.clear();
            gameScreen.add(tableScreen);
            gameScreen.add(cardsInHand);
            tableScreen.add(deckCard);
            tableScreen.add(recentCard);
            // Change cards into the button and add it into the arraylist
            for (int x = 0; x < tableGame.getPlayers().get(playerInTurnX).getPlayerHand().size(); x++) {
                if (tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x) instanceof Mineral) {
                    JButton cardButton = new JButton(new ImageIcon(((MineralWithPic) tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x)).getImageCard().getImage()));
                    cardButton.setSize(200, 300);
                    cardButton.setBackground(Color.WHITE);
                    listOfJButtons.add(cardButton);
                } else {
                    JButton cardButton = new JButton(new ImageIcon(((SuperTrumpsWithPic) tableGame.getPlayers().get(playerInTurnX).getPlayerCard(x)).getImageCard().getImage()));
                    cardButton.setSize(200, 300);
                    cardButton.setBackground(Color.WHITE);
                    listOfJButtons.add(cardButton);
                }
            }
            // Add all of the button into the GUI and then implement the action event
            for (int button = 0; button < tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerHand().size(); button++) {
                listOfJButtons.get(button).addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent e) {
                                                                    // Get the index of the card and then get the card from the index that we get and validate it from previous assignment
                                                                    JButton buttonSource = (JButton) e.getSource();
                                                                    Card cardPlayed = tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerCard(listOfJButtons.indexOf(buttonSource));             //Trying to get the card inputted
                                                                    boolean gameContinue = useCard(cardPlayed, tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()));     //Use the card validation method from assignment 1 with modification
                                                                    // Modified version of the code from previous assignment
                                                                    if(tableGame.getGameMode().equals("CHOICE")) {
                                                                        tableGame.putCardToGame(cardPlayed);
                                                                        tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerHand().remove(listOfJButtons.indexOf(buttonSource));
                                                                        recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                        recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                        if(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerHand().size()==0)
                                                                        {
                                                                            playerTurn = playerTurn%tableGame.getPlayers().size();
                                                                            winners.add(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerName());
                                                                            tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).playerLeft(tableGame);
                                                                            if (tableGame.getRecentCard() instanceof SuperTrumps) {
                                                                                recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                            } else {
                                                                                recentCard = new JLabel(new ImageIcon(((MineralWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                            }
                                                                            pickMode = false;
                                                                            playCard();
                                                                            revalidate();
                                                                            repaint();
                                                                        }
                                                                        else {
                                                                            pickMode = false;
                                                                            pickTrump();
                                                                        }
                                                                    }
                                                                    else if(tableGame.getGameMode().equals("GRAV/MAG"))
                                                                    {
                                                                        if(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).lookAtWinCard())                          //Check if the player has got the winning card (Magnetite)
                                                                        {
                                                                            for (int x = 0; x < tableGame.getPlayers().get(playerInTurnX).getPlayerHand().size(); x++)
                                                                            {
                                                                                tableGame.putCardToGame(cardPlayed);
                                                                                tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerHand().remove(listOfJButtons.indexOf(buttonSource));
                                                                                tableGame.setRecentPlayer(tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerName());
                                                                            }
                                                                            tableGame.setGameMode("SPECGRAV"); // Return back the game mode of Specific Gravity
                                                                            if(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerHand().size()==0)
                                                                            {
                                                                                playerTurn = playerTurn%tableGame.getPlayers().size();
                                                                                winners.add(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerName());
                                                                                tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).playerLeft(tableGame);
                                                                                if (tableGame.getRecentCard() instanceof SuperTrumps) {
                                                                                    recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15)); // set image of recent card
                                                                                } else {
                                                                                    recentCard = new JLabel(new ImageIcon(((MineralWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                }
                                                                                pickMode = false;
                                                                                playCard();
                                                                                revalidate();
                                                                                repaint();
                                                                            }
                                                                        }
                                                                        else
                                                                        {tableGame.setGameMode("SPECGRAV");}
                                                                        if(gameContinue) {
                                                                            tableGame.putCardToGame(cardPlayed);
                                                                            tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerHand().remove(listOfJButtons.indexOf(buttonSource));
                                                                            tableGame.setRecentPlayer(tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerName());
                                                                            if(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerHand().size()==0)
                                                                            {
                                                                                // when players have no card in the hand anymore
                                                                                playerTurn = playerTurn%tableGame.getPlayers().size();
                                                                                winners.add(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerName());
                                                                                tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).playerLeft(tableGame);
                                                                                if (tableGame.getRecentCard() instanceof SuperTrumps) {
                                                                                    recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                } else {
                                                                                    recentCard = new JLabel(new ImageIcon(((MineralWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                }
                                                                                pickMode = false;
                                                                                playCard();
                                                                                revalidate();
                                                                                repaint();
                                                                            }
                                                                            else {
                                                                                // when the game continue as normal
                                                                                playerTurn++;
                                                                                if (tableGame.getRecentCard() instanceof SuperTrumps) {
                                                                                    recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                } else {
                                                                                    recentCard = new JLabel(new ImageIcon(((MineralWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                }
                                                                                pickMode = false;
                                                                                passCount = 0;
                                                                                playCard();
                                                                                revalidate();
                                                                                repaint();
                                                                            }
                                                                        }
                                                                    }
                                                                    else{
                                                                        if (gameContinue) {
                                                                            tableGame.putCardToGame(cardPlayed);
                                                                            tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerHand().remove(listOfJButtons.indexOf(buttonSource));
                                                                            tableGame.setRecentPlayer(tableGame.getPlayers().get(playerTurn % tableGame.getPlayers().size()).getPlayerName());
                                                                            if(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerHand().size()==0)
                                                                            {
                                                                                playerTurn = playerTurn%tableGame.getPlayers().size();
                                                                                winners.add(tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).getPlayerName());
                                                                                tableGame.getPlayers().get(playerTurn%tableGame.getPlayers().size()).playerLeft(tableGame);
                                                                                if (tableGame.getRecentCard() instanceof SuperTrumps) {
                                                                                    recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                } else {
                                                                                    recentCard = new JLabel(new ImageIcon(((MineralWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                }

                                                                                pickMode = false;
                                                                                playCard();
                                                                                revalidate();
                                                                                repaint();
                                                                            }
                                                                            else {
                                                                                playerTurn++;
                                                                                if (tableGame.getRecentCard() instanceof SuperTrumps) {
                                                                                    recentCard = new JLabel(new ImageIcon(((SuperTrumpsWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                } else {
                                                                                    recentCard = new JLabel(new ImageIcon(((MineralWithPic) tableGame.getRecentCard()).getImageCard().getImage()));
                                                                                    recentCard.setFont(new Font("Sans Serif",Font.PLAIN, 15));
                                                                                }
                                                                                pickMode = false;
                                                                                passCount = 0;
                                                                                playCard();
                                                                                revalidate();
                                                                                repaint();
                                                                            }
                                                                        } else {
                                                                            gameGuide.setText("Card is not playable, pick the card that can outplay the recent card or pass. The Game mode is: " + tableGame.getGameMode());
                                                                            revalidate();
                                                                            repaint();
                                                                        }}
                                                                    if(tableGame.getPlayers().size()==1)
                                                                    {
                                                                        showWinners();
                                                                        revalidate();
                                                                        repaint();
                                                                    }
                                                                }
                                                            }
                );
                cardsInHand.add(listOfJButtons.get(button)); //add the card images and buttons of card for accessing the card
            }
            mainScreen.add(gameScreen);

        }
    }

    public boolean useCard(Card card, Player play)
    {   //This method is used to look at the card whether it is allowed to play or not
        boolean higherScore = false;
        int difference = 0;
        if(passCount == tableGame.getPlayers().size()-1 || tableGame.getCardUsed().size()==0 || pickMode) // This conditional is used to know whether the game is the first turn or a player that allows to play again
        {
            // The method is used to compare the card and return true if the card is played (particularly by higher value of specific supertrumps or supertrumps card)
            if(card  instanceof SuperTrumps)
            {
                tableGame.setGameMode(((SuperTrumps) card).cardEffect());
            }
            higherScore = true;
        }
        else {
            if(card instanceof Mineral) {
                if (tableGame.getRecentCard() instanceof Mineral) {
                    if (tableGame.getGameMode().equals("HARD")) {
                        Float now = new Float(((Mineral) card).getCardHardness());
                        Float prev = new Float(((Mineral) tableGame.getRecentCard()).getCardHardness());
                        difference = now.compareTo(prev);
                    } else if (tableGame.getGameMode().equals("SPECGRAV")) {
                        Float now = new Float(((Mineral) card).getCardSpecGravity());
                        Float prev = new Float(((Mineral) tableGame.getRecentCard()).getCardSpecGravity());
                        difference = now.compareTo(prev);
                    } else if (tableGame.getGameMode().equals("CLE")) {
                        Float now = new Float(((Mineral) card).getCardCleavageScore());
                        Float prev = new Float(((Mineral) tableGame.getRecentCard()).getCardCleavageScore());
                        difference = now.compareTo(prev);
                    } else if (tableGame.getGameMode().equals("ABU")) {
                        Float now = new Float(((Mineral) card).getCardCrystalAbundanceScore());
                        Float prev = new Float(((Mineral) tableGame.getRecentCard()).getCardCrystalAbundanceScore());
                        difference = now.compareTo(prev);
                    } else if (tableGame.getGameMode().equals("ECO")) {
                        Float now = new Float(((Mineral) card).getCardEconomicValueScore());
                        Float prev = new Float(((Mineral) tableGame.getRecentCard()).getCardEconomicValueScore());
                        difference = now.compareTo(prev);
                    } // Compare the value to the recent card based on the specified trump category
                    if (difference > 0) {
                        higherScore = true;
                    }
                } else {
                    higherScore = true;
                }
            }
            else
            {
                tableGame.setGameMode(((SuperTrumps) card).cardEffect());
                higherScore = true;
            }
        }
        return higherScore;
    }

    public void showWinners()
    {
        // This method is to show the winners screen when the game ends
        mainScreen.removeAll();
        JPanel listOfWinners = new JPanel();
        listOfWinners.setLayout(new BoxLayout(listOfWinners,BoxLayout.Y_AXIS));
        for(int x = 0; x < winners.size();x++)
        {
            String show = "No "+(x+1)+ ". " + winners.get(x) +"";
            listOfWinners.add(new JLabel(show));
        }
        gameGuide.setText("The game is over, here are the winners:");
        mainScreen.add(listOfWinners);
    }
}

