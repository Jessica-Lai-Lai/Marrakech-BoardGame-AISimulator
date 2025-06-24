package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//the Board is the whole game board and this grid is made of 7 x 7 tiles
public class Board {
    Tile[][] tiles;
    Player[] playerList;
    Assam assam;
    public static final int BOARD_WIDTH = 7;
    public static final int BOARD_HEIGHT = 7;
    //Constructor
    Board(Tile[][] tiles) {
        this.tiles = tiles;
    }
    public Board(String gameString) {
        this.tiles = Tile.constructBoard(gameString);
        this.playerList = Player.constructPlayerList(gameString);
        this.assam = new Assam(gameString);
    }

    /**
     * This method is used to convert char 1-digit number to integer,
     * @param c any char
     * @return an integer if c is 0 - 9, -1 otherwise
     * @author Jinqiao Jiang
     */
    public static int charToInt(char c) {
        int out;
        try {
            //0 - 9
            out = Integer.parseInt(String.valueOf(c));
        } catch(Exception e) {
            //not 0 - 9
            out = -1;
        }
        return out;
    }


    /**
     * This method is used to convert board from a matrix of 7x7 Tiles(representing the board) back to board String
     * @param board
     * @return a board string, e.g. Bn00...
     * @author Jessica Lai
     */
   public static String boardToString(Tile[][] board) {
       StringBuilder boardString = new StringBuilder();

       for (int i = 0; i < board.length; i++) {
           for (int j = 0; j < board[i].length; j++) {
               // Extract the ownerId(color, ex:n) and carpetId(ex:00) from the Tile
               char ownerId = board[i][j].getOwnerId();
               String carpetId = board[i][j].getCarpetId();

               // Append the ownerId and carpetId to the boardString
               boardString.append(ownerId).append(carpetId);
           }
       }

       String boardStart = "B";
       return boardStart + boardString.toString();
   }


    public static String initBoardString() {
        return 'B' + "n00".repeat(BOARD_HEIGHT).repeat(BOARD_WIDTH);
    }

    public Tile[][] getTiles() {
        return tiles;
    }
    public Player[] getPlayerList() { return playerList; }
    public Assam getAssam() { return assam; }

    /**
     * This method returns the board string
     * @param gameString a game string, e.g. Pc03700iPy03200iPp02400iPr02700iA61WBn00...
     * @return a board string, e.g. Bn00...
     * @author Jinqiao Jiang
     */
    public static String getBoardString(String gameString) {
        Integer length = Assam.getAssamIndex(gameString);
        if (length == null) return "";
        return gameString.substring(length + 4);
    }

    @Override
    public String toString() {
        return "Board{" +
                "tiles=" + Arrays.toString(tiles) +
                ", playerList=" + Arrays.toString(playerList) +
                ", assam=" + assam +
                '}';
    }
}
