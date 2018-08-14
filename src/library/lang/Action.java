package library.lang;

public interface Action<T,E>{
    public T run(E arg);
}