package library.lang;

/**
 * A pair of {@link Object}
 * @param <X> the first object type
 * @param <Y> the second object type
 */
public class Pair<X, Y> {
    private X x;
    private Y y;

    /**
     * Constructor of the class Pair
     */
    public Pair(){
        this(null, null);
    }

    /**
     * Constructor of the class {@link Pair}
     * @param x the first {@link Object}
     * @param y the second {@link Object}
     */
    public Pair(X x, Y y){
        this.x = x;
        this.y = y;
    }

    /**
     * Set the first {@link Object}
     * @param x the new value
     */
    public void setX(X x) {
        this.x = x;
    }

    /**
     * Get the first {@link Object}
     * @return the {@link Object}
     */
    public X getX() {
        return x;
    }

    /**
     * Set the second {@link Object}
     * @param y the new value
     */
    public void setY(Y y) {
        this.y = y;
    }
    /**
     * Get the second {@link Object}
     * @return the {@link Object}
     */
    public Y getY() {
        return y;
    }

    /**
     * Get the first {@link Object}'s {@link Class}
     * @return the corresponding {@link Class}
     */
    public Class<?> getClassX(){
        return getX().getClass();
    }
    /**
     * Get the second {@link Object}'s {@link Class}
     * @return the corresponding {@link Class}
     */
    public Class<?> getClassY(){
        return getY().getClass();
    }
}
