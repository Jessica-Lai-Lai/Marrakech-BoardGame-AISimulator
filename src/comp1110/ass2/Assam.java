package comp1110.ass2;

import javafx.scene.image.Image;

// The piece of Assam that will be moving on the board.
public class Assam {
    // This tells us which tile the Assam is currently occupying.
    // For example (3,3) is the initial coordinates
    Coordinates position;
    // This tells the direction that Assam is currently facing,
    // which can be top, right, left or bottom.
    // North, East, South, West = 1,2,3,4
    char direction;
    //the image of Assam
    static Image image;
    

    //constructor
    public Assam(Coordinates position, char direction) {
        this.position = position;
        this.direction = direction;
    }
    /**
     * This method will read the gameString and find Assam
     * for example in the Assam string A04N,
     * Assam is at position (0,4), facing towards the top of the board.
     * @param gameString String representation of a game
     * @author Jinqiao Jiang
     */
    public Assam(String gameString) {
        String assamString = getAssamString(gameString);
         this.position = new Coordinates(assamString.substring(1, 3));
         this.direction = assamString.charAt(3);
    }
    public static String initAssamString() {
        return "A33E";
    }
    /**
     * This method will convert char direction to integer
     * for example, N, E, S, W = North, East, South, West = 1,2,3,4
     * @param c the direction represented by a char
     * @return 1,2,3,4
     * @author Jinqiao Jiang
     */
    public static int readDirection(char c) {
        return switch (c) {
            case ('N') -> 1;
            case ('E') -> 2;
            case ('S') -> 3;
            case ('W') -> 4;
            default -> -1;
        };
    }

    /**
     * This method returns the index of 'A'
     * @param gameString a game string, e.g. Pc03700iPy03200iPp02400iPr02700iA6
     * @return the index of 'A', e.g. 32
     * @author Jinqiao Jiang
     */
    static Integer getAssamIndex(String gameString) {
        //empty input
        if (gameString.equals("")) {
            return null;
        }
        int length = 0;
        while (gameString.charAt(length) != 'A') {
            length += 8;
        }
        return length;
    }

    /**
     * This method reads in a game string and return the Assam string
     * @param gameString a game string, e.g. Pc03700iPy03200iPp02400iPr02700iA61WBn00...
     * @return the Assam string, e.g. A61W
     * @Author Jinqiao Jiang
     */
    public static String getAssamString(String gameString) {
        Integer length = getAssamIndex(gameString);
        if (length == null) return "";
        return gameString.substring(length, length + 4);
    }


    /**
     * This method reads in a currentAssam string and return the coordinatesAssam
     * @param currentAssam a game string, e.g. A61W
     * @return the coordinatesAssam String, e.g. 61
     * @author Jessica Lai
     */
    public static String coordinatesAssam(String currentAssam){
        String coordinatesAssam=  currentAssam.substring(1, 3);
        return coordinatesAssam;
    }


    /**
     * This method reads in a currentAssam string and return the currentDirection
     * @param currentAssam a game string, e.g. A61W
     * @return the currentDirection String, e.g. W
     * @author Jessica Lai
     */
    public static String directionAssam(String currentAssam){
        String currentDirection = currentAssam.substring(3, 4);
        return currentDirection;
    }


    /**
     * This method reads in a current Assam's position and direction, then return the new position of Assam after moving
     * @param direction is a string which represent Assan current , e.g. S
     * @param position is a 1-dimension array which represent Assan current position , e.g. {0,4}
     * @return the new position of Assam,
     * @author Jessica Lai
     */
    public static int[] move(int[] position, String direction) {
        int x = position[0];
        int y = position[1];

        switch (direction) {
            case "N":
                return new int[]{x , y-1};
            case "S":
                return new int[]{x , y+1};
            case "E":
                return new int[]{x+1, y };
            case "W":
                return new int[]{x-1, y };
            default:
                return position;
        }
    }

    /**
     * This method reads in a current Assam's position,
     * use to determine whether the coordinates are within the legal range
     * @param position is a 1-dimension array which represent Assan current position , e.g. {0,4}
     * @return true or false
     * @author Jessica Lai
     */
    public static boolean isValidPosition(int[] position) {
        int x = position[0];
        int y = position[1];
        return x >= 0 && x <= 6 && y >= 0 && y <= 6;
    }


    /**
     * This method reads in a current Assam's position and direction,
     * then return the new currentDirection of Assam after changing direction
     * @param currentDirection is a string which represent Assan current , e.g. S
     * @param position is a 1-dimension array which represent Assan current position , e.g. {0,4}
     * @return the new currentDirection of Assam,
     * @author Jessica Lai
     */
    public static String turn(int[] position, String currentDirection) {
        int x = position[0];
        int y = position[1];

        // Check if the current position is at (6, 0) or (0, 6)
        if ((x == 6 && y == 0) || (x == 0 && y == 6)) {
            switch (currentDirection) {
                case "N":
                    return "W";
                case "S":
                    return "E";
                case "E":
                    return "S";
                case "W":
                    return "N";
                default:
                    return currentDirection;
            }
        } else {
            switch (currentDirection) {
                case "N":
                    return "S";
                case "S":
                    return "N";
                case "E":
                    return "W";
                case "W":
                    return "E";
                default:
                    return currentDirection;
            }
        }
    }

    /**
     * This method reads in a current Assam's position and direction,
     * then return the new position of Assam after moving and changing direction
     * @param direction is a string which represent Assan current , e.g. S
     * @param position is a 1-dimension array which represent Assan current position , e.g. {0,4}
     * @return the new position of Assam (after changing direction)
     * @author Jessica Lai
     */
    public static int[] newMove(int[] position, String direction) {
        int x = position[0];
        int y = position[1];

        if ((x == 6 && y == 0) || (x == 0 && y == 6)) {

            switch (direction) {
                case "N"://south to east
                case "E"://east to south
                case "W"://west to north
                case "S"://east to west
                    return new int[]{x, y};

                default:
                    return position;

            }

        } else {
            switch (direction) {
                case "N"://south to north
                case "S"://north to south
                    if (y==0 &&x % 2 == 0 || x % 2 != 0 && y==6) {
                        return new int[]{x + 1, y};
                    }else{return new int[]{x - 1, y};}

                case "E"://west to east
                case "W"://east to west
                    if (x==0 &&y % 2 == 0 || y % 2 != 0 && x==6) {
                        return new int[]{x , y+1};
                    }else{return new int[]{x , y-1};}


                default:
                    return position;
            }
        }
    }


    // Get-Set Method
    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public static Image getImage() {
        try {
            image = new Image ("file:assets/Assam.png");
        } catch (Exception e) {image = null;}
        return image; }


    @Override
    public String toString() {
        return "A" + position + direction;
        }
}
