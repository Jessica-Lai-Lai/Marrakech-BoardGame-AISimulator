package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class BoardTest {
    @Test
    public void charToIntCheck() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/char_to_int_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line: testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], String.valueOf(Board.charToInt(splitLine[0].charAt(0))), splitLine[2]);
        }
    }
    @Test
    public void getBoardStringCheck() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_board_string_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line: testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], Board.getBoardString(splitLine[0]), splitLine[2]);
        }
    }
    @Test
    public void findAllFullCarpetCheck() {
        //TODO add test code for this method after this method is complete
    }
}
