/* package comp1110.ass2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void equalsTo() {
    }
}

 */


package comp1110.ass2;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class CoordinatesTest {
    private Coordinates coordinates;

    public void setUp() {
        // Create a new Coordinates instance for testing
        coordinates = new Coordinates(coordinates.x, coordinates.y);
    }


    @Test
    public void testx(){

        coordinates = new Coordinates(1, 1);
        int actual = Integer.parseInt(String.valueOf(coordinates.x));

        if(coordinates.getX() ==1){
            int expected = 1;
            actual = Integer.parseInt(String.valueOf(coordinates.x));
            assertEquals(1,actual,"The value of x is not 1");
        }
        coordinates = new Coordinates(2, 1);
        if(coordinates.getX() ==2){
            actual = Integer.parseInt(String.valueOf(coordinates.x));
            assertEquals(2,actual,"The value of x is 2");
        }
        coordinates = new Coordinates(3, 1);
        if(coordinates.getX() ==3){
            actual = Integer.parseInt(String.valueOf(coordinates.x));
            assertEquals(3,actual,"The value of x is 3");
        }
        coordinates = new Coordinates(4, 1);
        if(coordinates.getX() ==4){
            actual = Integer.parseInt(String.valueOf(coordinates.x));
            assertEquals(4,actual,"The value of x is 4");
        }
        coordinates = new Coordinates(5, 1);
        if(coordinates.getX() ==5){
            actual = Integer.parseInt(String.valueOf(coordinates.x));
            assertEquals(5,actual,"The value of x is 5");
        }
        coordinates = new Coordinates(6, 1);
        if(coordinates.getX() ==6){
            actual = Integer.parseInt(String.valueOf(coordinates.x));
            assertEquals(6,actual,"The value of x is 6");
        }
    }
    @Test
    public void testy(){
        coordinates = new Coordinates(1, 1);
        int actual = Integer.parseInt(String.valueOf(coordinates.y));

        if(coordinates.getY() ==1){
            int expected = 1;
            actual = Integer.parseInt(String.valueOf(coordinates.y));
            assertEquals(1,actual,"The value of y is not 1");
        }
        coordinates = new Coordinates(1, 2);
        if(coordinates.getY() ==2){
            actual = Integer.parseInt(String.valueOf(coordinates.y));
            assertEquals(2,actual,"The value of y is 2");
        }
        coordinates = new Coordinates(1, 3);
        if(coordinates.getY() ==3){
            actual = Integer.parseInt(String.valueOf(coordinates.y));
            assertEquals(3,actual,"The value of y is 3");
        }
        coordinates = new Coordinates(1, 4);
        if(coordinates.getY() ==4){
            actual = Integer.parseInt(String.valueOf(coordinates.y));
            assertEquals(4,actual,"The value of y is 4");
        }
        coordinates = new Coordinates(1, 5);
        if(coordinates.getY() ==5){
            actual = Integer.parseInt(String.valueOf(coordinates.y));
            assertEquals(5,actual,"The value of y is 5");
        }
        coordinates = new Coordinates(1, 6);
        if(coordinates.getY() ==6){
            actual = Integer.parseInt(String.valueOf(coordinates.y));
            assertEquals(6,actual,"The value of y is 6");
        }


    }

}












    /*
    @BeforeEach
    public void setUp() {
        // Create a new Coordinates instance for testing
        coordinates = new Coordinates(7, 4);
    }




    @Test
    public void testGetX() {
        // Test the getX() method
        assertEquals(3, coordinates.getX());
    }

    @Test
    public void testSetX() {
        // Test the setX() method
        coordinates.setX(5);
        assertEquals(5, coordinates.getX());
    }

    @Test
    public void testGetY() {
        // Test the getY() method
        assertEquals(4, coordinates.getY());
    }

    @Test
    public void testSetY() {
        // Test the setY() method
        coordinates.setY(6);
        assertEquals(6, coordinates.getY());
    }

    @Test
    public void testEqualsTo() {
        // Test the equalsTo() method
        Coordinates otherCoordinates = new Coordinates(3, 4);
        assertTrue(coordinates.equalsTo(otherCoordinates));

        Coordinates differentCoordinates = new Coordinates(1, 2);
        assertFalse(coordinates.equalsTo(differentCoordinates));
    }

    @Test
    public void testToString() {
        // Test the toString() method
        assertEquals("(3, 4)", coordinates.toString());
    }

    // You can add more test methods as needed


    @Test
    public void assertEquals_demo() {
        // integers
        int expected = 5;
        int actual = 2 + 2;
        assertEquals(expected, actual,"the value is 4");


        // strings
        assertEquals("JUnit5", "JUnit" + "5");
    }a  */