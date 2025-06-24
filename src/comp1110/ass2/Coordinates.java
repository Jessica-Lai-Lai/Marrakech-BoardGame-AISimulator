package comp1110.ass2;
//the cartesian coordinates which stores a pair of int (x, y)
//valid value should in the range (0,0) to (6,6)
public class Coordinates {
    //integer from 0 to 6
    int x;
    //integer from 0 to 6
    int y;
    //constructor
    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * another constructor for coordinates
     * @param xy a string representation of coordinates
     * @author Jinqiao Jiang
     */
    public Coordinates(String xy) {
        this.x = Character.getNumericValue(xy.charAt(0));
        this.y = Character.getNumericValue(xy.charAt(1));
    }


    // Get-set method
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }//set the value of y


    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return x + "" + y;
    }


}
