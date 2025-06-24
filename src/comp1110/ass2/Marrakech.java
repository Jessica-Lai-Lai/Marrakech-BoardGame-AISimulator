package comp1110.ass2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Marrakech {

    /**
     * Determine whether a rug String is valid.
     * For this method, you need to determine whether the rug String is valid, but do not need to determine whether it
     * can be placed on the board (you will determine that in Task 10 ). A rug is valid if and only if all the following
     * conditions apply:
     *  - The String is 7 characters long
     *  - The first character in the String corresponds to the colour character of a player present in the game
     *  - The next two characters represent a 2-digit ID number
     *  - The next 4 characters represent coordinates that are on the board
     *  - The combination of that ID number and colour is unique
     * To clarify this last point, if a rug has the same ID as a rug on the board, but a different colour to that rug,
     * then it may still be valid. Obviously multiple rugs are allowed to have the same colour as well so long as they
     * do not share an ID. So, if we already have the rug c013343 on the board, then we can have the following rugs
     *  - c023343 (Shares the colour but not the ID)
     *  - y013343 (Shares the ID but not the colour)
     * But you cannot have c014445, because this has the same colour and ID as a rug on the board already.
     * @param gameString A String representing the current state of the game as per the README
     * @param rug A String representing the rug you are checking
     * @return true if the rug is valid, and false otherwise.
     * @author Jinqiao Jiang
     */
    public static boolean isRugValid(String gameString, String rug) {
        // FIXME: Task 4
        //empty input
        if(gameString.equals("") || rug.equals("")) {
            return false;
        }
        Tile[][] currentBoard = Tile.constructBoard(gameString);
        //invalid gameString
        if(currentBoard == null) { return false; }
        Carpet testCarpet = new Carpet(rug);
        //invalid player
        if(!testCarpet.isPlayerValid()) { return false; }
        //invalid position
        if(!testCarpet.isPositionValid()) { return false; }
        //not adjacent
        if(!testCarpet.isSegmentsAdjacent()) { return false; }
        //already exists on board
        if(testCarpet.isOnBoard(currentBoard)) { return false; }
        return true;
    }

    /**
     * Roll the special Marrakech die and return the result.
     * Note that the die in Marrakech is not a regular 6-sided die, since there
     * are no faces that show 5 or 6, and instead 2 faces that show 2 and 3. That
     * is, of the 6 faces
     *  - One shows 1
     *  - Two show 2
     *  - Two show 3
     *  - One shows 4
     * As such, in order to get full marks for this task, you will need to implement
     * a die where the distribution of results from 1 to 4 is not even, with a 2 or 3
     * being twice as likely to be returned as a 1 or 4.
     * @return The result of the roll of the die meeting the criteria above
     * @author Aaron Nathan Vas
     */
    public static int rollDie() {

        int[] arr= new int[]{1,2,2,3,3,4};
        int result = arr[new Random().nextInt(arr.length)];


        // FIXME: Task 6
        return result;
    }

    /**
     * Determine whether a game of Marrakech is over
     * Recall from the README that a game of Marrakech is over if a Player is about to enter the rotation phase of their
     * turn, but no longer has any rugs. Note that we do not encode in the game state String whose turn it is, so you
     * will have to think about how to use the information we do encode to determine whether a game is over or not.
     * @param currentGame A String representation of the current state of the game.
     * @return true if the game is over, or false otherwise.
     * @author Aaron Nathan Vas
     */
    public static boolean isGameOver(String currentGame) {
        // FIXME: Task 8

        Player[] currentPlayers = Player.constructPlayerList(currentGame);

        int Rug = 0;
        for (Player player : currentPlayers) {
            if (player.isInTheGame()){
                Rug = player.getRugs() + Rug;
            }

        }
        return Rug == 0; }


    /**
     * Implement Assam's rotation.
     * Recall that Assam may only be rotated left or right, or left alone -- he cannot be rotated a full 180 degrees.
     * For example, if he is currently facing North (towards the top of the board), then he could be rotated to face
     * East or West, but not South. Assam can also only be rotated in 90 degree increments.
     * If the requested rotation is illegal, you should return Assam's current state unchanged.
     * @param currentAssam A String representing Assam's current state
     * @param rotation The requested rotation, in degrees. This degree reading is relative to the direction Assam
     *                 is currently facing, so a value of 0 for this argument will keep Assam facing in his
     *                 current orientation, 90 would be turning him to the right, etc.
     * @return A String representing Assam's state after the rotation, or the input currentAssam if the requested
     * rotation is illegal.
     * @author Aaron Nathan Vas, Jessica Lai
     */
    public static String rotateAssam(String currentAssam, int rotation) {
        // FIXME: Task 9
        // Ensure that rotation is in the range of 0 to 270 degrees (multiples of 90 degrees).
        rotation = rotation % 360;
        if (rotation < 0) {
            rotation += 360;
        }

        // Get the current direction that Assam is facing.
        String currentDirection = Assam.directionAssam(currentAssam);
        String currentPosition = Assam.coordinatesAssam(currentAssam);

        // Define an array of directions in clockwise order.
        String[] directions = {"N", "E", "S", "W"}; // Change to String array

        // Find the index of the current direction in the array.
        int currentIndex = -1;
        for (int i = 0; i < directions.length; i++) {
            if (directions[i].equals(currentDirection)) { // Use .equals() to compare Strings
                currentIndex = i;
                break;
            }
        }

        // Rotate Assam to the new direction based on the specified rotation.
        int newIndex;
        if (rotation == 90) {
            newIndex = (currentIndex + 1) % 4; // Rotate 90 degrees clockwise.
        } else if (rotation == 270) {
            newIndex = (currentIndex + 3) % 4; // Rotate 90 degrees counterclockwise.
        } else {
            // Rotation is not a multiple of 90 degrees, so Assam cannot be rotated.
            return currentAssam;
        }

        // Get the new direction.
        String newDirection = directions[newIndex];

        // Return the new Assam state.
        return "A" + currentPosition + newDirection;
    }

    /**
     * Determine whether a potential new placement is valid (i.e that it describes a legal way to place a rug).
     * There are a number of rules which apply to potential new placements, which are detailed in the README but to
     * reiterate here:
     *   1. A new rug must have one edge adjacent to Assam (not counting diagonals)
     *   2. A new rug must not completely cover another rug. It is legal to partially cover an already placed rug, but
     *      the new rug must not cover the entirety of another rug that's already on the board.
     * @param gameState A game string representing the current state of the game
     * @param rug A rug string representing the candidate rug which you must check the validity of.
     * @return true if the placement is valid, and false otherwise.
     * @author Jessica Lai
     */
    // FIXME: Task 10
    public static boolean isPlacementValid(String gameState, String rug) {

        Tile[][] Boards = Tile.constructBoard(gameState);

        // rug information split
        Carpet carpet = new Carpet(rug);

        Coordinates rugCoordinates1 = carpet.seg1Position;
        Coordinates rugCoordinates2 = carpet.seg2Position;
        int x1=rugCoordinates1.getX();
        int y1=rugCoordinates1.getY();
        int x2=rugCoordinates2.getX();
        int y2=rugCoordinates2.getY();

        for (int i = 0; i < Boards.length; i++) {
            for (int j = 0; j < Boards[i].length; j++) {
                boolean isOccupiedByAssam = Boards[i][j].isOccupiedByAssam();
                Coordinates Position = Boards[i][j].getPosition();

                if (isOccupiedByAssam != false & !Position.equals(rugCoordinates1) & !Position.equals(rugCoordinates2)) {

                    List<Tile> neighbors = Boards[i][j].getNeighbors(Boards);
                    for (Tile neighbor : neighbors) {

                        if (neighbor.getPosition().equals(rugCoordinates1) || neighbor.getPosition().equals(rugCoordinates2)) {
                            // Adjacent to Assam

                            if (Boards[x1][y1].getOwnerId() != 'n' && Boards[x2][y2].getOwnerId() != 'n') {
                                if (Boards[x1][y1].getOwnerId() == Boards[x2][y2].getOwnerId() ) {
                                    if(Boards[x1][y1].getCarpetId().equals(Boards[x2][y2].getCarpetId())) {
                                        return false; // Rug placement is not valid
                                    }
                                }
                            }
                            return true;
                        }
                    }
                }
            }
        }
        return false; // Rug placement is not valid
    }


    /**
     * Determine the amount of payment required should another player land on a square.
     * For this method, you may assume that Assam has just landed on the square he is currently placed on, and that
     * the player who last moved Assam is not the player who owns the rug landed on (if there is a rug on his current
     * square). Recall that the payment owed to the owner of the rug is equal to the number of connected squares showing
     * on the board that are of that colour. Similarly to the placement rules, two squares are only connected if they
     * share an entire edge -- diagonals do not count.
     * @param gameString A String representation of the current state of the game.
     * @return The amount of payment due, as an integer.
     * @author Jessica Lai
     */

    // FIXME: Task 11

    public static int getPaymentAmount(String gameString) {
        // get board
        Tile[][] board = Tile.constructBoard(gameString);

        //get Assam Position
        Assam assam =new Assam(gameString);
        Coordinates assamPosition=assam.getPosition();

        // Get the carpet color on Assam's current tile (if there is a carpet)
        char assamRugColor = 'n';  // default:No carpet
        if (board[assamPosition.getX()][assamPosition.getY()].isOccupiedByCarpet()) {
            assamRugColor = board[assamPosition.getX()][assamPosition.getY()].getOwnerId();
        }

        int connectedSquares = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].isOccupiedByAssam()) {
                    for (Tile neighbor : board[i][j].getSameColorNeighbors(board, assamRugColor)) {

                        // Check if we have the same carpet color as our neighbor
                        if (neighbor.getOwnerId() == assamRugColor) {
                            connectedSquares++;
                        }
                    }
                }
            }
        }
        return connectedSquares;
    }


    /**
     * Determine the winner of a game of Marrakech.
     * For this task, you will be provided with a game state string and have to return a char representing the colour
     * of the winner of the game. So for example if the cyan player is the winner, then you return 'c', if the red
     * player is the winner return 'r', etc...
     * If the game is not yet over, then you should return 'n'.
     * If the game is over, but is a tie, then you should return 't'.
     * Recall that a player's total score is the sum of their number of dirhams and the number of squares showing on the
     * board that are of their colour, and that a player who is out of the game cannot win. If multiple players have the
     * same total score, the player with the largest number of dirhams wins. If multiple players have the same total
     * score and number of dirhams, then the game is a tie.
     * @param gameState A String representation of the current state of the game
     * @return A char representing the winner of the game as described above.
     * @author Jessica Lai
     */


    // FIXME: Task 12

    public static char getWinner(String gameState) {
        // Get player string
        String player = Player.getPlayerString(gameState);
        String[] playerStrings = Player.extractPlayerData(player);

        // Get board string
        Board board = new Board(gameState);
        String boardString = board.getBoardString(gameState).replaceAll("^B", "");

        // Initialize variables
        char winningColor = 'n';
        int maxScore = Integer.MIN_VALUE;
        List<Integer> otherPlayersDirhams = new ArrayList<>();

        for (String playerString : playerStrings) {
            // Skip empty strings or "out" player strings
            if (playerString.length() == 0 || playerString.endsWith("o")) {
                continue;
            }

            //Extract player information from player string
            Player playerInfo = new Player("P"+playerString);
            char color = playerInfo.getId();
            int dirhams = playerInfo.getMoney();

            // Calculate player's score
            int score = dirhams;

            // Calculate the number of squares of the player's color on the board
            int colorCount = boardString.length() - boardString.replace(String.valueOf(color), "").length();
            score += colorCount;
            // Update winning player if necessary
            if (score > maxScore) {
                maxScore = score;
                winningColor = color;
            } else if (score == maxScore) {
                // If the scores are the same, compare the number of dirhams
                for (int otherDirhams : otherPlayersDirhams) {
                    if (dirhams > otherDirhams) {
                        winningColor = color;
                    }else if (dirhams == otherDirhams){
                        winningColor = 't';}
                }
            }
            otherPlayersDirhams.add(dirhams);
        }
        return winningColor;
    }




    /**
     * Implement Assam's movement.
     * Assam moves a number of squares equal to the die result, provided to you by the argument dieResult. Assam moves
     * in the direction he is currently facing. If part of Assam's movement results in him leaving the board, he moves
     * according to the tracks diagrammed in the assignment README, which should be studied carefully before attempting
     * this task. For this task, you are not required to do any checking that the die result is sensible, nor whether
     * the current Assam string is sensible either -- you may assume that both of these are valid.
     * @param currentAssam A string representation of Assam's current state.
     * @param dieResult The result of the die, which determines the number of squares Assam will move.
     * @return A String representing Assam's state after the movement.
     * @author Jessica Lai
     */

    // FIXME: Task 13
    public static String moveAssam(String currentAssam, int dieResult){

        String xy=Assam.coordinatesAssam(currentAssam);
        Coordinates  coordinates  = new Coordinates(xy);
        int x=coordinates.getX();
        int y=coordinates.getY();

        int[] currentPosition = new int[]{x,y}; //Initial Assam coordinates
        String currentDirection = Assam.directionAssam(currentAssam);


        for (int i = 0; i < dieResult; i++) {
            //Try to move one step in the current direction
            int[] newPosition = Assam.move(currentPosition, currentDirection);


            // Check whether the new position is within the legal range (7*7 board）
            if (Assam.isValidPosition(newPosition)) {
                currentPosition = newPosition;

            }
            else{
                // If the new position is illegal, need to redirect and update the coordinates
                currentDirection = Assam.turn(currentPosition,currentDirection);
                currentPosition = Assam.newMove(currentPosition, currentDirection);
            }
        }

        String newAssam = "A" + currentPosition[0]+ currentPosition[1] + currentDirection;
        return newAssam;
    }

    /**
     * Place a rug on the board
     * This method can be assumed to be called after Assam has been rotated and moved, i.e in the placement phase of
     * a turn. A rug may only be placed if it meets the conditions listed in the isPlacementValid task. If the rug
     * placement is valid, then you should return a new game string representing the board after the placement has
     * been completed. If the placement is invalid, then you should return the existing game unchanged.
     * @param currentGame A String representation of the current state of the game.
     * @param rug A String representation of the rug that is to be placed.
     * @return A new game string representing the game following the successful placement of this rug if it is valid,
     * or the input currentGame unchanged otherwise.
     * @author Jessica Lai
     */
    // FIXME: Task 14

    public static String makePlacement(String currentGame, String rug) {
        // Check if the placement is valid
        if (isPlacementValid(currentGame, rug) !=true || isRugValid(currentGame,rug)!=true) {
            return currentGame;
        }else {
            // Get board string
            String boardInfo = Board.getBoardString(currentGame);

            // Get player string
            String player = Player.getPlayerString(currentGame);
            String[] playerStrings = Player.extractPlayerData(player);

            // Get assam string
            String assamString = Assam.getAssamString(currentGame);

            // rug information split
            Carpet carpet = new Carpet(rug);
            char rugColor = carpet.ownerId;
            String rugID = carpet.carpetId;
            Coordinates rugCoordinates1 = carpet.seg1Position;
            Coordinates rugCoordinates2 = carpet.seg2Position;

            // update Boards information
            Tile[][] Boards = Tile.constructBoard(currentGame);
            Coordinates[] targetPositions = {rugCoordinates1, rugCoordinates2};
            for (Coordinates targetPosition : targetPositions) {
                // Traverse the game Board to find the target Tile
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (Boards[i][j].getPosition().equals(targetPosition)) {
                            // After finding the target Tile, change ownerId & CarpetId to the new value
                            Boards[i][j].changeOwnerId(rugColor);
                            Boards[i][j].changeCarpetId(rugID);
                            break;
                        }
                    }
                }
            }

            //Convert game board(Tile[][]) to string
            String newboardString = Board.boardToString(Boards);

            // update Player information
            for (int i = 0; i < playerStrings.length; i++) {
                if (playerStrings[i] != null && playerStrings[i].length() > 0) {

                    Player playerInfo = new Player("P"+playerStrings[i]);
                    char currentPlayerColor = playerInfo.getId();

                    if (rugColor==currentPlayerColor) {
                        boolean inTheGame=playerInfo.isInTheGame();
                        int dirhams = playerInfo.getMoney();
                        int rugs = playerInfo.getRugs();
                        int updatedRugs = rugs - 1;

                        // Update the player's rug count
                        Player updateplayerInfo = new Player(currentPlayerColor,dirhams ,updatedRugs,inTheGame);
                        playerStrings[i] = updateplayerInfo.toString().replaceAll("^P", "");
                    }
                }
            }
            String newGame = String.join("P", playerStrings);
            newGame += assamString + newboardString;
            return newGame;
        }
    }

}

