package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
public class AssamTest {
    @Test
    public void AssamConstructorCheck() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/assam_constructor_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line: testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], String.valueOf(new Assam(splitLine[0])), splitLine[2]);
        }
    }
    @Test
    public void readDirectionCheck() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/assam_read_direction_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line: testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], String.valueOf(Assam.readDirection(splitLine[0].charAt(0))), splitLine[2]);
        }
    }
    @Test
    public void getAssamIndexCheck() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_assam_index_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line: testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], String.valueOf(Assam.getAssamIndex(splitLine[0])), splitLine[2]);
        }
    }
    @Test
    public void getAssamStringCheck() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/get_assam_string_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line: testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], Assam.getAssamString(splitLine[0]), splitLine[2]);
        }
    }
}
