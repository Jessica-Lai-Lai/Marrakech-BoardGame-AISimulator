package comp1110.ass2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class TileTest {
    @Test
    public void constructTileTest() {
        BufferedReader file = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("testdata/constructTile_test.txt")));
        Stream<String> testLines = file.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split(",");

            int tileX = Integer.parseInt(splitLine[0]); // Assuming the first value is X coordinate
            int tileY = Integer.parseInt(splitLine[1]); // Assuming the second value is Y coordinate
            int assamX = Integer.parseInt(splitLine[2]); // Assuming the third value is Assam's X coordinate
            int assamY = Integer.parseInt(splitLine[3]); // Assuming the fourth value is Assam's Y coordinate
            String tileString = splitLine[4];

            Coordinates tilePosition = new Coordinates(tileX, tileY);
            Coordinates assamPosition = new Coordinates(assamX, assamY);

            Tile tile = new Tile(tilePosition, assamPosition, tileString);


            String expectedOwnerId = splitLine[5];
            String expectedCarpetId = splitLine[6];
            boolean expectedIsAssamPosition = Boolean.parseBoolean(splitLine[7]);


            Assertions.assertEquals(expectedOwnerId, String.valueOf(tile.getOwnerId()));
            Assertions.assertEquals(expectedCarpetId, tile.carpetId);
            Assertions.assertEquals(expectedIsAssamPosition, tile.isOccupiedByAssam);
        }
    }



//    @Test
//    public void ConstructBoard() {
//        BufferedReader file;
//        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/constructBoard_test.txt")));
//        Stream<String> testLines = file.lines();
//        for (String line: testLines.toList()) {
//            String[] splitLine = line.split(",");
//            Assertions.assertEquals(splitLine[1], String.valueOf( Tile.constructBoard(splitLine[0])), splitLine[2]);
//        }
//    }



    }








