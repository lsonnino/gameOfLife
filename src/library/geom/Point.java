package library.geom;

import library.lang.Pair;

/**
 * A pair of coordinates (x, y)
 *
 * @see library.lang.Pair
 */
public class Point extends Pair {

    /**
     * Constructor of the class Point
     */
    public Point(){
        this(0.0, 0.0);
    }
    /**
     * Constructor of the class {@link Point}
     * @param x the first coordinate
     * @param y the second coordinate
     */
    public Point(double x, double y){
        super(x, y);
    }

    /**
     * Set the first coordinate
     * @param x the new value
     */
    public void setX(double x){
        setX(x);
    }
    /**
     * Set the second coordinate
     * @param y the new value
     */
    public void setY(double y){
        setY(y);
    }
}
