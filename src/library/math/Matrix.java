package library.math;

import library.lang.Action;
import library.lang.Application;

/**
 * A matematical-like two dimensional matrix
 * @param <T> the matrix type
 */
public class Matrix<T> {
    private T[][] array;

    /**
     * Constructor of the Matrix
     * @param rows the number of rows
     * @param columns the number of columns
     * @param element the default element to fill the matrix with
     */
    public Matrix(int rows, int columns, T element){
        array = (T[][]) new Object[rows][columns];
        for(int x=0 ; x < rows; x++){
            for(int y=0 ; y < columns ; y++){
                array[x][y] = element;
            }
        }
    }

    /**
     * Set a value in the matrix
     * @param x the row number
     * @param y the column number
     * @param t the new element
     */
    public void set(int x, int y, T t){
        array[x][y] = t;
    }

    /**
     * Get an element from the matrix
     * @param x the row number
     * @param y the column number
     * @return the element at position [x][y]
     */
    public T get(int x, int y){
        return array[x][y];
    }

    /**
     * Get the number of rows
     * @return the number of rows
     */
    public int getRows(){
        return array.length;
    }
    /**
     * Get the number of columns
     * @return the number of columns
     */
    public int getColumns(){
        return array[0].length;
    }

    public boolean equals(Matrix<T> matrix){
        for(int x=0 ; x < getRows(); x++){
            for(int y=0 ; y < getColumns() ; y++){
                if(!get(x, y).equals(matrix.get(x, y))){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean is(Action<Boolean, T> action){
        for(int x=0 ; x < getRows(); x++){
            for(int y=0 ; y < getColumns() ; y++){
                boolean bool = action.run(get(x, y));
                if(!bool){
                    return false;
                }
            }
        }
        return true;
    }
    public <E> void forEach(Action<E,T> action){
        for(int x=0 ; x < getRows(); x++){
            for(int y=0 ; y < getColumns() ; y++){
                action.run(get(x, y));
            }
        }
    }
    public void apply(Application<T> application){
        for(int x=0 ; x < getRows(); x++){
            for(int y=0 ; y < getColumns() ; y++){
                set(x, y, application.run(get(x, y)));
            }
        }
    }
}