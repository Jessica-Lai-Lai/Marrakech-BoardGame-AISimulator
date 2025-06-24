package comp1110.ass2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;




public class PlayerTest {
    @Test
    public void getPlayerInList() {
        //TODO add test code for this method after this method is complete
        }

//    @Test
//    public void constructPlayerList() {
//        BufferedReader file;
//        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/constructPlayerList_test.txt")));
//        Stream<String> testLines = file.lines();
//        for (String line : testLines.toList()) {
//            String[] splitLine = line.split(",");
//            Assertions.assertEquals(splitLine[1], String.valueOf(Player.constructPlayerList(splitLine[0])), splitLine[2]);
//
//        }
//    }


    @Test
    public void constructPlayer() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/constructPlayer_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split("@");
            Assertions.assertEquals(splitLine[1], String.valueOf(new Player(splitLine[0])), splitLine[2]);

        }
    }



    @Test
    public void getPlayerString() {
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/getPlayerString_test.txt")));
        Stream<String> testLines = file.lines();
        for (String line : testLines.toList()) {
            String[] splitLine = line.split(",");
            Assertions.assertEquals(splitLine[1], String.valueOf(Player.getPlayerString(splitLine[0])), splitLine[2]);

        }
    }





    @Test
    public void isMoneyValueValid(){
        BufferedReader file;
        file = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("testdata/isMoneyValueValid_test.txt")));
        Stream<String> testLines = file.lines();

        for (String line : testLines.toList()) {
            String[] splitLine = line.split(",");
            int moneyValue = Integer.parseInt(splitLine[0]); // 将String解析为int
            Assertions.assertEquals(splitLine[1], String.valueOf(Player.isMoneyValueValid(moneyValue)), splitLine[2]);
        }
    }











}
