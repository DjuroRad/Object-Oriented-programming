package hwk8;

import java.security.InvalidParameterException;
/**
*Derives form GTUSet class
*/
public class GTUVector<T> extends GTUSet<T> {

/**
*Inserts and allowes duplicates
*/
    @Override
    public void insert( T el ){//duplicates are allowed here
        if( size() > max_size() ) {
            System.out.println("Max size reached");
            throw new InvalidParameterException();
        }
        my_data.insert( el );
    }

}
