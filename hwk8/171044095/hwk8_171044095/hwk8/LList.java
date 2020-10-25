package hwk8;
/**
*Linked list used to store elements of vector and set structures
*/
public class LList< T >  {
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

/**
*Returns the size of the list
*/
    public int getSize(){ return size; }

/**
*Returns the head of the list
*/
    public Node<T> getHead(){ return head; }

/**
*Returns the tail of the list
*/
    public Node<T> getTail(){ return tail; }

/**
*Node class to store the elements of the list
*/
    class Node<T>{
        private T data;
        private Node<T> next;
        private Node<T> prev;
/**
*Provides the next element( Node )
*/
        public Node<T> getNext(){ return next; }
/**
*Provides the previous element( Node )
*/
        public Node<T> getPrev(){ return prev; }
/**
*Returns the data stored in the current Node
*/
        public T getData(){ return data; }
/**
*Sets the data of the current node
*/
        public void setData( T data ){ this.data = data; }
    }
/**
*Returns the size of the list
*/
    public void insert( T el ){
        Node<T> temp = new Node<T>();
        temp.data = el;
        temp.next = null;
        if( head == null ){
            head = tail = temp;
            head.prev = tail.next = null;
        }
        else{
            temp.prev = tail;
            tail.next = temp;
            tail = temp;
        }
        ++size;
    }

/**
*Removes the element if found, returns false if not found
*/
    public boolean erase( T el ){
        boolean success = false;
        Node< T > temp = head;
        while( temp != null && !success ) {
            if( temp.data == el )
                success = true;
            else
                temp = temp.next;
        }
        if( success ){//if there is an element that can be deleted in our linked list
            if( temp.prev != null )
                temp.prev.next = temp.next;

            if( temp.next != null )
                temp.next.prev = temp.prev;
            if( temp == head )
                head = temp.next;
            if( temp == tail )
                tail = temp.prev;

            --size;
        }

        return success;
    }
/**
*Sets the list to contain no elements
*/
    public void clear(){
        head = tail = null;
        size = 0;
    }
}
