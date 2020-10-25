package hwk8;
/**
*Used to iterator through containers
*/
public class GTUIterator<T> {
    private LList<T>.Node<T> node_iter;
    public GTUIterator( LList<T>.Node<T> node_iter ) {
        this.node_iter = node_iter;
    }

/**
*Returns true if the iterator is not null
*/
    public boolean hasNext(){
        if ( node_iter != null )
            return true;
        else
            return false;
    }

/**
*Returns the next value and moves the iterator to point to the next value
*/
    public T next(){
        T to_return = null;;
        if( node_iter != null ) {
            to_return = node_iter.getData();
            node_iter = node_iter.getNext();
        }
        return to_return;
    }
}
