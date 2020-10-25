package hwk8;

import java.security.InvalidParameterException;

/**
*Functions implemented from GTUContainer
*/
public class GTUSet< T > extends GTUContainer< T >{
    @Override
    public boolean empty(){
        if( my_data.getSize() == 0 )
            return true;
        else
            return false;
    }
    @Override
    public int max_size(){
        return 1000;
    }
    @Override
    public int size(){
        return my_data.getSize();
    }

/**
*Inserts and doesn't allow duplicates
*/
    @Override
    public void insert( T el ){
        if( size() > max_size() ) {
            System.out.println("Reached max_size");
            throw new InvalidParameterException();
        }
        boolean has_duplicate = false;
        LList<T>.Node<T> temp = my_data.getHead();
        while( temp != null && !has_duplicate ){
            if (temp.getData().equals(el) ) {
                has_duplicate = true;
            } else {
                temp = temp.getNext();
            }
        }
        if( !has_duplicate )
            my_data.insert( el );
        else{
            System.out.println("Set cannot insert an element that is already in it");
            throw new InvalidParameterException();
        }
    }
    @Override
    public boolean erase( T el ){
        return my_data.erase( el );
    }
    public void clear(){
        my_data.clear();
    }
    @Override
    public GTUIterator<T> iterator() {
        return new GTUIterator<T>( my_data.getHead() );
    }
    @Override
    public boolean contains(Object o ) {
	@SuppressWarnings("unchecked")
        T t = (T)o;
        if( t == null )
            return false;

        GTUIterator<T> i = this.iterator();
        while( i.hasNext() )
            if( i.next() == t )
                return true;

        return false;
    }
}
