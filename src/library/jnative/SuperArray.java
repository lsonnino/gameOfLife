package library.jnative;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * The {@link SuperArray} is a flexible generic array. It can expand and elements can be removed as an {@link ArrayList} but
 * it has the structure of a normal Array so it can simply be converted and used in different programming languages
 * @param <T> The sype of the array
 * @see java.util.Arrays
 */
public class SuperArray<T> implements Iterator<T> {
    private T[] array;
    private int pointer;

    /**
     * Constructor of the {@link SuperArray}. It creates a new Array with only one element created using the empty constructor of the class T
     * @param clazz the class object of T
     * @throws  IllegalAccessException  if the class or its nullary
     *          constructor is not accessible.
     * @throws  InstantiationException
     *          if this {@link Class} represents an abstract class,
     *          an interface, an array class, a primitive type, or void;
     *          or if the class has no null constructor;
     *          or if the instantiation fails for some other reason.
     * @throws  ExceptionInInitializerError if the initialization
     *          provoked by this method fails.
     * @throws  SecurityException
     *          If a security manager, <i>s</i>, is present and
     *          the caller's class loader is not the same as or an
     *          ancestor of the class loader for the current class and
     *          invocation of {@link SecurityManager#checkPackageAccess
     *          s.checkPackageAccess()} denies access to the package
     *          of this class.
     */
    public SuperArray(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        this(clazz.newInstance());
    }
    /**
     * Constructor of the {@link SuperArray}
     * @param array an initial array
     */
    public SuperArray(T ... array){
        this.array = array;
    }

    /**
     * Add an element to the array. The new element is added at the end of the array
     * @param t the added element
     */
    public void add(T t) {
        add(getSize(), t);
    }
    /**
     * Add an element at a given index to the array
     * @param index the index of the added element
     * @param t the added element
     */
    public void add(int index, T t) {
        ArrayList<T> arrayList = toArrayList();

        arrayList.add(index, t);

        setArray(arrayList.toArray(array));
    }
    /**
     * Remove the element at the given element of the array. It then switch every element after the removed one in order to fill the gap
     * @param t the element to remove
     * @return the removed element
     */
    public T remove(T t) {
        ArrayList<T> arrayList = toArrayList();

        arrayList.remove(t);

        setArray(arrayList.toArray(array));

        return t;
    }

    /**
     * Remove the element at the given index. It then switch every element after the removed one in order to fill the gap
     * @param index the index
     * @return the removed element
     * @throws ArrayIndexOutOfBoundsException if the index is not within [0 ; size[
     */
    public T remove(int index) {
        if(index < 0 && index >= getSize()){
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return remove(get(index));
    }

    /**
     * Set the element at a given index
     * @param t the element
     * @param i the index
     * @return the old element at index i
     * @throws ArrayIndexOutOfBoundsException if the index is not within [0 ; size[
     */
    public T set(T t, int i){
        if(i < 0 && i >= getSize()){
            throw new ArrayIndexOutOfBoundsException(i);
        }

        T old = getArray()[i];
        getArray()[i] = t;

        return old;
    }

    /**
     * Get an element of the array in a given index
     * @param i the index of the element
     * @return the element at index i
     * @throws ArrayIndexOutOfBoundsException if the index is not within [0 ; size[
     */
    public T get(int i){
        if(i < 0 && i >= getSize()){
            throw new ArrayIndexOutOfBoundsException(i);
        }

        return getArray()[i];
    }

    /**
     * Gives the size of the array
     * @return the size of the array
     */
    public int getSize(){
        return array.length;
    }

    /**
     * Replaces the array by the new one given as parameter
     * @param array the new array
     */
    public void setArray(T ... array) {
        this.array = array;
    }

    /**
     * Return the array contained in the {@link SuperArray}
     * @return the array
     */
    public T[] getArray() {
        return array;
    }

    /**
     * Return the index of an object in the array
     * @param element the object
     * @return the index of the object
     */
    public int indexOf(T element){
        for(int i=0 ; i < getSize() ; i++){
            if(get(i).equals(element)){
                return i;
            }
        }
        return -1;
    }
    /**
     * Return the last index of an object in the array
     * @param element the object
     * @return the last index of the object
     */
    public int indexLastOf(T element){
        for(int i=getSize() -1 ; i >= 0 ; i--){
            if(get(i).equals(element)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Tell if an element is contained on the array
     * @param element the element
     * @return if the element is contained
     */
    public boolean contains(T element){
        return indexOf(element) != -1;
    }

    /**
     * Convert the {@link SuperArray} in a more flexible {@link ArrayList} of T
     * @return the object converted in an {@link ArrayList}
     */
    public ArrayList<T> toArrayList(){
        ArrayList<T> arrayList = new ArrayList<>();

        for(T obj : array){
            arrayList.add(obj);
        }

        return arrayList;
    }

    /**
     * Gives the type of the {@link SuperArray}
     * @return the type of the array
     */
    public Class<T> getType(){
        return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * @param comparator the comparator to determine the order of the array.  A
     *        {@code null} value indicates that the elements'
     *        {@linkplain Comparable natural ordering} should be used.
     * @throws ClassCastException if the array contains elements that are not <i>mutually comparable</i> using the specified comparator
     * @throws IllegalArgumentException (optional) if the comparator is found to violate the {@link Comparator} contract
     */
    public void sort(Comparator<? super T> comparator){
        Arrays.sort(getArray(), comparator);
    }

    /**
     * Indicate wheter an arrays obj contains the exact same array of this one
     * @param obj the array to compare
     * @return true if both contains the same array
     */
    public boolean equals(T ... obj) {
        return this.equals(obj);
    }
    /**
     * Indicate wheter an arrays obj contains the exact same array of this one
     * @param obj the array to compare
     * @return true if both contains the same array
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof SuperArray){
            SuperArray superArray = (SuperArray) obj;

            if(superArray.getSize() != getSize()){
                return false;
            }

            for(int i=0 ; i < getSize() ; i++){
                if(!get(i).equals(superArray.get(i))){
                    return false;
                }
            }

            return true;
        }
        return false;
    }
    /**
     * Indicate wheter an arrays obj contains the exact same array of this one
     * @param obj the array to compare
     * @return true if both contains the same array
     */
    public boolean equals(SuperArray<T> obj) {
        return this.equals(obj.getArray());
    }

    @Override
    public boolean hasNext() {
        return pointer < getSize() - 1;
    }

    @Override
    public T next() {
        if(hasNext()){
            pointer++;
            return get(pointer);
        }
        return null;
    }
}
