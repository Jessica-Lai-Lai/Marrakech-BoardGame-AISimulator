package comp1110.ass2;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Player {
    // The players will have an identification of (1, 2, 3, 4)
    // which represent Cyan, Yellow, Red, and Purple respectively
    public char id;
    // the amount of money that the player has, maximum 999
    public int money;
    // the number of rugs the player has remaining, initial value of 15
    public int rugs;
    // Ture if the player is in the game, false if not
    // i: true
    // o: false
    public boolean inTheGame;
    //the carpet image
    public Image image;

    public boolean computer = false;

    /**
     * This method reads in an 8 length string and convert it to a player instance
     * @param singlePlayerString e.g. Pc03700i
     * @author Jinqiao Jiang
     */
    public Player(String singlePlayerString) {
        //read id from string
        this.id = singlePlayerString.toCharArray()[1];
        //read money from string
        this.money = Integer.parseInt(singlePlayerString.substring(2, 5));
        //read rugs from string
        this.rugs = Integer.parseInt(singlePlayerString.substring(5, 7));
        //read in the game from string
        this.inTheGame = (singlePlayerString.toCharArray()[7] == 'i');
        //read carpet image according to player id
        this.image = readImage(id);
    }


    /** This constructor use to update player information
     * @author Jessica Lai
     */

    public Player(char id, int money, int rugs,boolean inTheGame) {
        this.id = id;
        this.money = money;
        this.rugs = rugs;
        this.inTheGame=inTheGame;
    }

    /**
     * This method is used to initialize the PlayerStringList
     * @return a String "Pc03015iPy03015iPr03015iPp03015i"
     * @author Jinqiao Jiang
     */
    public static String[] initPlayerStringList() {
        String[] playerStringList = new String[4];
        playerStringList[0] = "Pc03015i";
        playerStringList[1] = "Py03015i";
        playerStringList[2] = "Pr03015i";
        playerStringList[3] = "Pp03015i";
        return playerStringList;
    }
    /**
     * This method finds the player in current player list by given id
     * @param currentPlayers a player list
     * @param id the given player id
     * @return a player in list that matches given id, null otherwise
     * @author Jinqiao Jiang
     */
    public static Player getPlayerInList(Player[] currentPlayers, char id) {
        for (Player currentPlayer : currentPlayers) {
            if (currentPlayer.getId() == id) {
                return currentPlayer;
            }
        }
        return null;
    }

    /**
     * This method is used to Construct player instances,
     * where the playerString is found and read
     * for example,"Pr00803i" are representing the red player,
     * who has 8 dirhams, 3 rugs remaining to place on the board, who is in the game.
     * @param gameString String representation of all players
     * @return the List with maximum four players
     * @author Jinqiao Jiang
     */
    public static Player[] constructPlayerList(String gameString) {
        List<Player> playerList = new ArrayList<>();
        String playerString = getPlayerString(gameString);
        int i = 0;
        while (i < playerString.length()) {
            String singlePlayerString = playerString.substring(i, i + 8);
            playerList.add(new Player(singlePlayerString));
            i += 8;
        }
        return playerList.toArray(new Player[0]);
    }
    /**
     * This method is used to Construct player instances,
     * where the playerString is found and read
     * for example,"Pr00803i" are representing the red player,
     * who has 8 dirhams, 3 rugs remaining to place on the board, who is in the game.
     * @param gameString String representation of all players
     * @param computers specify how many players are controlled by computer
     * @return the List with maximum four players
     * @author Jinqiao Jiang
     */
    public static Player[] constructPlayerList(String gameString, int computers) {
        List<Player> playerList = new ArrayList<>();
        String playerString = getPlayerString(gameString);
        int i = 0;
        while (i < playerString.length()) {
            String singlePlayerString = playerString.substring(i, i + 8);
            playerList.add(new Player(singlePlayerString));
            i += 8;
        }
        switch (computers) {
            case(3): playerList.get(1).setComputer(true);
            case(2): playerList.get(2).setComputer(true);
            case(1): playerList.get(3).setComputer(true);
        }
        return playerList.toArray(new Player[0]);
    }
    /**
     * This method reads in a game string and return the player string
     * @param gameString a game string, e.g. Pc03700iPy03200iPp02400iPr02700iA61WBn00...
     * @return the player string, e.g. Pc03700iPy03200iPp02400iPr02700i
     * @author Jinqiao Jiang
     */
    public static String getPlayerString(String gameString) {
        Integer length = Assam.getAssamIndex(gameString);
        if (length == null) return "";
        return gameString.substring(0, length);
    }

    /**
     * This method links an image to player id
     * @param id player id
     * @return image if the image is found, null otherwise
     * @author Jinqiao Jiang
     */
    public static Image readImage(int id) {
        Image image1, image2, image3, image4;
        try {
            image1 = new Image("file:assets/Cyan_Carpet.png");
            image2 = new Image("file:assets/Yellow_Carpet.png");
            image3 = new Image("file:assets/Red_Carpet.png");
            image4 = new Image("file:assets/Purple_Carpet.png");

        } catch (Exception e) {
            return null;
        }
        switch (id) {
            case ('c') -> {return image1;}
            case ('y') -> {return image2;}
            case ('r') -> {return image3;}
            case ('p') -> {return image4;}
            default -> {return null;}
        }
    }

    /**
     * This method is used to convert 'c', 'y', 'r', 'p' to 1,2,3,4
     * @param c the player id represent Cyan, Yellow, Red, and Purple as 'c', 'y', 'r', 'p'
     * @return the integer 1,2,3,4 represents c,y,r,p, -1 otherwise
     * @author Jinqiao Jiang
     */
    public static int idToInt(char c) {
        return switch (c) {
            case ('c') -> 1;
            case ('y') -> 2;
            case ('r') -> 3;
            case ('p') -> 4;
            default -> -1;//if it is n
        };
    }

    /**
     * This method is used to extract each player information from Player String,
     * and store this information in 1-d array
     * @param playerString is a String represent player information, ex: Pc02912iPy02712iPp03113iPr03213i
     * @return playerArray
     * @author Jessica Lai
     */
    public static String[] extractPlayerData(String playerString) {

        String[] playerDataArray = playerString.split("P");
        String[] playerArray = new String[playerDataArray.length];
        for (int i = 0; i < playerDataArray.length; i++) {
            playerArray[i] = playerDataArray[i] ;
        }
        return playerArray;
    }

    //Get-Set method
    public char getId() {
        return id;
    }

    public void setId(char id) {
        this.id = id;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void minusMoney(int money) {
        if (money > this.money) {
            this.money = 0;
        } else {
            this.money -= money;
        }
    }
    public void addMoney(int money) {
        this.money += money;
    }
    public int getRugs() {
        return rugs;
    }

    public void setRugs(int rugs) {
        this.rugs = rugs;
    }

    public boolean isInTheGame() {
        return inTheGame;
    }

    public void setInTheGame(boolean inTheGame) {
        this.inTheGame = inTheGame;
    }

    public Image getImage() {
        return image;
    }

    public boolean isComputer() {
        return computer;
    }

    public void setComputer(boolean computer) {
        this.computer = computer;
    }

    //check whether the input money is a valid value
    public static boolean isMoneyValueValid(int money) {
        return (money >= 0 && money <= 999);
    }

    //override toString method
    @Override
    public String toString() {
        StringBuilder moneyString = new StringBuilder();
        if (money <= 9) {
            //money <= 9
            moneyString.append("00");
            moneyString.append(money);
        } else if (money <= 99) {
            //9 < money <= 99
            moneyString.append("0");
            moneyString.append(money);
        } else {
            //99 < money
            moneyString.append(money);
        }
        StringBuilder rugString = new StringBuilder();
        if (rugs <= 9) {
            //rugs <= 9
            rugString.append("0");
            rugString.append(rugs);
        } else {
            //9 < rugs
            rugString.append(rugs);
        }
        char inTheGame;
        if (isInTheGame()) {
            inTheGame = 'i';
        } else {
            inTheGame = 'o';
        }
        return "P" + id + moneyString + rugString + inTheGame;
    }
}
