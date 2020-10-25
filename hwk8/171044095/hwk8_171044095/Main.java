import hwk8.LList;
import hwk8.GTUIterator;
import hwk8.GTUVector;
import hwk8.GTUSet;
import hwk8.GTUContainer;

public class Main {
    public static void main(String[] args) {
        driver();
    }
    public static void driver(){

        System.out.println("Making a set with 4 elements and trying to add the fifth already existing one");
        GTUSet< String > my_set = new GTUSet< String >();
        my_set.insert( "string set 1" );
        my_set.insert( "string set 2");
        my_set.insert( "string set 3");
        my_set.insert("string set 4");
        try{
            my_set.insert("string set 1" );
        }
        catch( Exception e ){
            System.out.println("Exception cought");
        }
        System.out.println("Making a vector with 4 elements");
        GTUVector<String> my_vector = new GTUVector<>();
        my_vector.insert( "string vector 1" );
        my_vector.insert( "string vector 2");
        my_vector.insert( "string vector 3");
        my_vector.insert( "string vector 4");

        GTUIterator<String> iter;

        System.out.println("Size of set is" + my_set.size() );
        System.out.println("Printing set using iterators");
        iter = my_set.iterator();
        while( iter.hasNext() )
            System.out.println(iter.next() + " ");
        System.out.println();

        System.out.println("Size of vector obj is" + my_vector.size() );
        System.out.println("Printing vector using iterators");
        iter = my_vector.iterator();
        while( iter.hasNext() ) {
            System.out.println(iter.next() + " ");
        }
        System.out.println();

        System.out.println("Erasing string vector 2 and printing vector again");
        my_vector.erase("string vector 2");
        iter = my_vector.iterator();
        while( iter.hasNext() ) {
            System.out.println(iter.next() + " ");
        }
        System.out.println("Size of vector obj now is" + my_vector.size() );
        System.out.println();

        System.out.println("Erasing string set 4 and printing set again");
        my_set.erase("string set 4");
        iter = my_set.iterator();
        while( iter.hasNext() )
            System.out.println(iter.next() + " ");
        System.out.println("Size of set obj now is " + my_vector.size() );
        System.out.println();
        System.out.println("Max size of vector is " + my_vector.max_size() + " and of set is " + my_set.max_size() );

        System.out.println("Checking the contains function for set with string 'String' ");
        if( my_set.contains( "String") )
            System.out.println("Contains 'String' ");
        else
            System.out.println("Doesn't contain 'String' ");

        System.out.println("Checking the contains function for vector with string 'string vector 1' ");
        if( my_vector.contains( "string vector 1") )
            System.out.println("Contains 'string vector 1' ");
        else
            System.out.println("Doesn't contain 'string vector 1' ");
        my_vector.clear();
        System.out.println("Clearing the vector");
        System.out.println("Vector obj size is now " + my_vector.size() );
        System.out.println("Checking if empty");
        if( my_vector.empty() )
            System.out.println("Vector obj is empty");
        else
            System.out.println("Vector obj is not empty");

        my_set.clear();
        System.out.println("Clearing the set");
        System.out.println("Set obj size is now " + my_set.size() );
        System.out.println("Checking if empty");
        if( my_set.empty() )
            System.out.println("Set obj is empty");
        else
            System.out.println("Set obj is not empty");
    }
}
