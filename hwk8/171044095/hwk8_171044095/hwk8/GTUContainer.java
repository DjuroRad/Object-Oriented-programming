package hwk8;
/**
*
*Contains abstract methods only
*
*/
abstract public class GTUContainer<T> {
    protected LList<T> my_data;
    public GTUContainer() {
        this.my_data = new LList<T>();
    }

/**
*Cheks if the Container is empty( Vector, Set )
*/
    public abstract boolean empty();
/**
*Gives the size of the Container( Vector, Set )
*/
    public abstract int size();
/**
*Gives the maximum size of the Container( Vector, Set )
*/
    public abstract int max_size();
/**
*Inserts the element to the Container( Vector, Set )
*/
    public abstract void insert( T el );
/**
*Erases the element from the container( Vector, Set ), if element not present returns false
*/
    public abstract boolean erase( T el );
/**
*Clears all the elements from the Container( Vector, Set )
*/
    public abstract void clear();
/**
*Returns the iterator to the beginning of the Container( Vector, Set )
*/
    public abstract GTUIterator<T> iterator();
/**
*Checks if the Container( Vector, Set ) has a certain elemnt in it
*/
    public abstract boolean contains( Object o );
}
