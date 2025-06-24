package comp1110.ass2;

/* There are carpets of four different suits that belong to four players respectively. We need
 * to define which player each carpet belongs to. Additionally, we need to define the location of
 * each carpet using Tiles.
 * For example: Tile [0][1] represents the position of the rug.
 */
public class Carpet {
    //specify the owner of this carpet
    char ownerId;
    //specify the id of this carpet
    String carpetId;
    //the cartesian position of segment1
    Coordinates seg1Position;
    //the cartesian position of segment2
    Coordinates seg2Position;
    //currentRotation, range from 1,2,3,4
    int rotation;

    //constructor method
    Carpet(char ownerId, String carpetId, Coordinates seg1Position, Coordinates seg2Position, int rotation) {
        this.ownerId = ownerId;
        this.carpetId = carpetId;
        this.seg1Position = seg1Position;
        this.seg2Position = seg2Position;
        this.rotation = rotation;
    }
    Carpet(char ownerId, String carpetId) {
        this.ownerId = ownerId;
        this.carpetId = carpetId;
    }

    /**
     * This method reads in a carpet string and convert it to a carpet instance
     * @param carpetString p011112
     * @author Jinqiao Jiang
     */
    Carpet(String carpetString) {
        if (carpetString.length() != 7) {
            this.ownerId = 'n';
            this.carpetId = "00";
        } else {
            this.ownerId = carpetString.charAt(0);
            this.carpetId = carpetString.substring(1, 3);
            this.seg1Position = new Coordinates(carpetString.substring(3, 5));
            this.seg2Position = new Coordinates(carpetString.substring(5, 7));
            if (isPositionValid() && isSegmentsAdjacent()) {
                setRotation();
            }
        }

    }

    /**
     * This method checks whether the carpet position is out of board
     * @return true if the carpet position is in the board, false otherwise
     * @author Jinqiao Jiang
     */
    public boolean isPositionValid() {
        return !(seg1Position.x > 6 || seg1Position.y > 6 ||
                seg2Position.x > 6 ||seg2Position.y > 6);
    }

    /**
     * This method checks whether the two segments of the carpet is adjacent
     * @return true if it is adjacent, false otherwise
     * @author Jinqiao Jiang
     */
    public boolean isSegmentsAdjacent() {
        return Math.abs(seg1Position.x - seg2Position.x) +
                Math.abs(seg1Position.y - seg2Position.y) == 1;
    }

    /**
     * This method checks whether the player character is one of "c,y,r,p"
     * @return true if it is one of them, false otherwise
     * @author Jinqiao Jiang
     */
    public boolean isPlayerValid() {
        return ownerId == 'c' || ownerId == 'y' || ownerId == 'r' || ownerId == 'p';
    }

    /**
     * This method checks whether the carpet is already existed on the provided game board
     * @param currentBoard the game board
     * @return true if it exists, false otherwise
     * @author Jinqiao Jiang
     */
    public boolean isOnBoard(Tile[][] currentBoard) {
        for (Tile[] tiles : currentBoard) {
            for (Tile tile : tiles) {
                if (tile.carpetId.equals(carpetId) && tile.ownerId == ownerId) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * This method will set the carpet rotation according to its segment position
     * e.g. if seg1 and seg2 have the position of (0,0) and (0,1) respectively, then the rotation is set to 1
     * if seg1 and seg2 have the position of (0,0) and (1,0) respectively, then the rotation is set to 2
     * if seg1 and seg2 have the position of (0,1) and (0,0) respectively, then the rotation is set to 3
     * if seg1 and seg2 have the position of (1,0) and (0,0) respectively, then the rotation is set to 4
     * @author Jinqiao Jiang
     */
    public void setRotation() {
        if (seg1Position.getX() == seg2Position.getX()) {
            if (seg1Position.getY() - seg2Position.getY() == 1) {
                this.rotation = 3;
            } else {
                this.rotation = 1;
            }
        }
        if (seg1Position.getY() == seg2Position.getY()) {
            if (seg1Position.getX() - seg2Position.getX() == 1) {
                this.rotation = 2;
            } else {
                this.rotation = 4;
            }
        }
    }

    //override toString method

    @Override
    public String toString() {
        return ownerId + carpetId + seg1Position + seg2Position;
    }



}
