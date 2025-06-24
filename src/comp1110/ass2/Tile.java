package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

// The board consists of tiles and each tile on the board will have a number of a matrix of 7x7.
// It has variables that tell us whether the tile is covered with a rug and the type of player rug it is covered with.
// A string representation style in game string: "n00" means no carpet is placed on this tile
// "p02" means a carpet with id 2 is placed on this tile, the carpet belongs to player id 4 (p represents 4)
public class Tile {
    // the position of this tile on the board
    public Coordinates position;
    // true if Assam stands on this tile
    public boolean isOccupiedByAssam;
    // true if this tile is covered by a carpet
    public boolean isOccupiedByCarpet;
    // if this tile is occupied by a carpet,
    // then assign the id of the player who placed the carpet on this tile
    //else assign -1
    //e.g. string representation of a tile "n00" means this tile is not occupied by player and ownerId is assigned to -1
    public char ownerId;
    //then assign the id of the carpet
    public String carpetId;
    //the carpet occupying this tile, null if no carpet
    public Carpet carpet;

    //constructor
    Tile(Coordinates position, boolean isOccupiedByAssam, boolean isOccupiedByCarpet, char ownerId, String carpetId) {
        this.position = position;
        this.isOccupiedByAssam = isOccupiedByAssam;
        this.isOccupiedByCarpet = isOccupiedByCarpet;
        this.ownerId = ownerId;
        this.carpetId = carpetId;
    }

    /**
     * This method will read the position of the tile,
     * the position of Assam,
     * three char in tile string,
     * and convert it to a tile
     * for example of tile string,
     * "n00" means no carpet is placed on this tile
     * "p02" means a carpet with id 2 is placed on this tile, the carpet belongs to player id 4 (p represents 4)
     * @param tilePosition the position of the tile
     * @param AssamPosition the position of Assam
     * @param tileString tile string
     * @author Jinqiao Jiang
     */
    Tile(Coordinates tilePosition, Coordinates AssamPosition, String tileString) {
        this.ownerId = tileString.charAt(0);
        this.carpetId = tileString.substring(1,3);
        this.position = tilePosition;
        this.isOccupiedByAssam = tilePosition.equals(AssamPosition);
        this.isOccupiedByCarpet = ownerId != 'n';
        this.carpet = null;
    }
    /**
     * This method reads in a game string and will construct a matrix of 7x7 Tiles representing the board
     * @param gameString String representation of a game
     * @return the board with 7x7 tiles
     * @author Jinqiao Jiang
     */
    public static Tile[][] constructBoard(String gameString) {
        //empty input
        if(gameString.equals("")) {
            return null;
        }
        //get board string, i.e. Bn00...
        String boardString = Board.getBoardString(gameString);
        //get Assam
        Assam assam = new Assam(gameString);
        //the board
        Tile[][] output = new Tile[7][7];
        //
        int i = 0;
        //position of Assam
        Coordinates positionOfAssam = assam.getPosition();
        for (int j = 0; j < output.length; j++) {
            for (int k = 0; k < output[j].length; k++) {
                String currentSingleTileString = boardString.substring(i + 1,i + 4);
                output[j][k] = new Tile(new Coordinates(j, k), positionOfAssam, currentSingleTileString);
                i += 3;
            }
        }
        for (i = 0; i < output.length; i++) {
            for (int j = 0; j < output[i].length; j++) {
                //if it is already assigned carpet, skip
                if (output[i][j].getCarpet() == null) {
                    //check neighbors
                    for (Tile neighbor : output[i][j].getNeighbors(output)) {
                        if (output[i][j].getCarpetId().equals(neighbor.getCarpetId()) &&
                                output[i][j].getOwnerId() == neighbor.getOwnerId()) {
                            //build carpet string
                            String carpetString = output[i][j].getOwnerId() +
                                    output[i][j].getCarpetId() +
                                    output[i][j].getPosition() +
                                    neighbor.getPosition();
                            //create carpet according to previously built string
                            Carpet carpet = new Carpet(carpetString);
                            //assign carpet to current tile and its neighbor
                            output[i][j].setCarpet(carpet);
                            neighbor.setCarpet(carpet);
                            //skip other
                            break;
                        }
                    }
                }
            }
        }
        return output;
    }

    /**
     * This method is used to find neighbors of the calling tile in the specified board
     * @param board the input board
     * @return a list of tiles that are adjacent to the calling tile
     * @author Jinqiao Jiang
     */
    public List<Tile> getNeighbors(Tile[][] board) {
        List<Tile> neighborList = new ArrayList<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        if (x - 1 >= 0) {
            neighborList.add(board[x - 1][y]);
        }
        if (x + 1 <= 6) {
            neighborList.add(board[x + 1][y]);
        }
        if (y - 1 >= 0) {
            neighborList.add(board[x][y - 1]);
        }
        if (y + 1 <= 6) {
            neighborList.add(board[x][y + 1]);
        }
        return neighborList;
    }


    /**
     * This method reads in the board and targetColor, using recursive method to search for neighbors of the same color
     * @param board with 7x7 tiles
     * @param targetColor is the color of the carpet which is occupied by Assam
     * @return neighborList
     * @author Jessica Lai
     */
    public List<Tile> getSameColorNeighbors(Tile[][] board, char targetColor) {
        List<Tile> neighborList = new ArrayList<>();
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();

        // Use a Set to track visited Tiles to prevent repeated visits
        Set<Tile> visitedTiles = new HashSet<>();

        // Use recursive method to search for neighbors of the same color
        recursiveCheck(board, x, y, targetColor, neighborList, visitedTiles);
        return neighborList;
    }

    /**
     *
     * @param board
     * @param x
     * @param y
     * @param targetColor
     * @param neighborList
     * @param visitedTiles
     */
    private void recursiveCheck(Tile[][] board, int x, int y, char targetColor, List<Tile> neighborList, Set<Tile> visitedTiles) {
        // Check if the border and color match, return if they don't match
        if (x < 0 || x >= board.length || y < 0 || y >= board[x].length || visitedTiles.contains(board[x][y]) || board[x][y].getOwnerId() != targetColor) {
            return;
        }

        //Add the current Tile to the neighbor list
        neighborList.add(board[x][y]);
        visitedTiles.add(board[x][y]);

        //Continue to search for adjacent Tiles recursively
        recursiveCheck(board, x - 1, y, targetColor, neighborList, visitedTiles);
        recursiveCheck(board, x + 1, y, targetColor, neighborList, visitedTiles);
        recursiveCheck(board, x, y - 1, targetColor, neighborList, visitedTiles);
        recursiveCheck(board, x, y + 1, targetColor, neighborList, visitedTiles);
    }


    public void changeOwnerId(char newOwnerId) {
        this.ownerId = newOwnerId;
    }


    public void changeCarpetId(String newCarpetId) {
        this.carpetId  = newCarpetId;
    }


    //get set method

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public boolean isOccupiedByAssam() {
        return isOccupiedByAssam;
    }

    public void setOccupiedByAssam(boolean occupiedByAssam) {
        isOccupiedByAssam = occupiedByAssam;
    }

    public boolean isOccupiedByCarpet() {
        return isOccupiedByCarpet;
    }

    public void setOccupiedByCarpet(boolean occupiedByCarpet) {
        isOccupiedByCarpet = occupiedByCarpet;
    }

    public char getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(char ownerId) {
        this.ownerId = ownerId;
    }

    public String getCarpetId() {
        return carpetId;
    }

    public void setCarpetId(String carpetId) {
        this.carpetId = carpetId;
    }

    public Carpet getCarpet() {
        return carpet;
    }

    public void setCarpet(Carpet carpet) {
        this.carpet = carpet;
    }

    //override toString method

    @Override
    public String toString() {
        return "Tile " +
                ownerId + carpetId +
                '{' +
                "position=" + position +
                ", isOccupiedByAssam=" + isOccupiedByAssam +
                ", isOccupiedByCarpet=" + isOccupiedByCarpet +
                ", ownerId=" + ownerId +
                ", carpetId=" + carpetId +
                '}';
    }
}
