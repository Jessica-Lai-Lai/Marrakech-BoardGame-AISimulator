package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CarpetTest {

    private Carpet carpet;


    public void setUp() {
        // Create a new Carpet instance for testing
        carpet = new Carpet('n', "00");
    }

    @Test
    void readCarpet() { //TODO add carpet rotation according to its coordinates of two segments is remaining
    }

//    @Test
//    void isPositionValid() {
//        // Test when carpet position is valid
//        Coordinates position1= carpet.seg1Position;
//        Coordinates position2= carpet.seg2Position;
//
//        if(position1.x > 6 || position1.y > 6 ||
//                position2.x > 6 ||position2.y > 6) {
//
//            assertTrue(carpet.isPositionValid());
//
//        } else if (position1.x <= 6 || position1.y <= 6 ||
//                position2.x <= 6 ||position2.y <= 6) {
//
//            assertFalse(carpet.isPositionValid());
//            System.out.println(" The position of the carpet is out of the board");
//        }
//
//
//    }

    @Test
    void isSegmentsAdjacent() {

        // Test when segments are adjacent
        Coordinates seg1 = new Coordinates(0, 0);
        Coordinates seg2 = new Coordinates(0, 1);
        Carpet carpet = new Carpet('c', "01", seg1, seg2, -1);
        assertTrue(carpet.isSegmentsAdjacent());

        // Test when segments are not adjacent
        seg1 = new Coordinates(0, 0);
        seg2 = new Coordinates(1, 2);
        carpet = new Carpet('y', "02", seg1, seg2, -1);
        assertFalse(carpet.isSegmentsAdjacent());


    }

//    @Test
//    public void testIsPlayerValid() {
//        // Test when player character is valid
//        carpet.ownerId = 1;
//        assertTrue(carpet.isPlayerValid());
//
//        carpet.ownerId = 2;
//        assertTrue(carpet.isPlayerValid());
//        carpet.ownerId = 3;
//        assertTrue(carpet.isPlayerValid());
//        carpet.ownerId = 4;
//        assertTrue(carpet.isPlayerValid());
//
//        // Test when player character is not valid
//        carpet.ownerId = 'n';
//        assertFalse(carpet.isPlayerValid());
//    }

//    @Test
//    public void isOnBoard() {
//        boolean ans = true;
//        Tile[][] currentBoard1 = new Tile[5][5];
//        boolean val;
//       // Carpet test = new Carpet();
//
//        assertEquals(ans,carpet.isOnBoard(currentBoard1));
//    }

//    @Test
//    void setRotation() {
//
//
//
//        Coordinates position1= carpet.seg1Position;
//        Coordinates position2= carpet.seg2Position;
//
//        if(position1.x > 1 || position1.y > 1 ||
//                position2.x > 1 ||position2.y > 1){
//
//            System.out.println("The rotation is invalid since x or y coordinates is greater than one");
//        }
//        if(position1.x < 0 || position1.y < 0 ||
//                position2.x < 0 ||position2.y < 0){
//
//            System.out.println("The rotation is invalid since x or y coordinates is lesser than zero");
//
//        }
//        if(position1.x == 0 || position1.y == 0 ||
//                position2.x == 0 ||position2.y == 0 ||
//                position1.x == 1 || position1.y == 1 ||
//                position2.x == 1 ||position2.y == 1 ){
//
//            System.out.println("The rotation is valid since x or y coordinates is either zero or 1");
//        }
//
//    }


    /*
    @Test
    public void testIsOnBoard() {
        // Create a sample Tile[][] representing the game board
        Tile[][] currentBoard = new Tile[7][7];

        // Create a Carpet instance to test
        Carpet carpetToTest = new Carpet(1, 1);

        // Place the carpet on the board at a specific location
        Tile [][] currentBoard2 = new Tile[3][3]; // Assuming ownerId is 1 and carpetId is 1 at this position

        // Test when the carpet is on the board
        assertTrue(carpetToTest.isOnBoard(currentBoard));

        // Test when the carpet is not on the board
        currentBoard[3][3] = null; // Remove the carpet from the board
        assertFalse(carpetToTest.isOnBoard(currentBoard));
}
*/


}