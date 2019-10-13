import java.util.*;
public class BinarySearchTree {
    protected BinaryNode root;
    
    public BinarySearchTree( ) {
        root = null;
    }
        
    public void insert( Comparable x, String pareho ) {
        root = insert( x, root,pareho );
    }
    
    protected BinaryNode insert( Comparable x, BinaryNode t, String pareho ) {
	    
        if( t == null )
            t = new BinaryNode( x,pareho );
        else if( x.compareTo( t.element ) < 0 )
            t.left = insert( x, t.left,pareho );
        else if( x.compareTo( t.element ) > 0 )
            t.right = insert( x, t.right,pareho );
        else if( x.compareTo( t.element ) == 0 )
        
           		t.equivalent=pareho;

        return t;
    }
    
     public String find( Comparable x ) {
        return find( x, root );
    }
    
    private String find( Comparable x, BinaryNode t ) {
        while( t != null ) {
            if( x.compareTo( t.element ) < 0 )
                t = t.left;
            else if( x.compareTo( t.element ) > 0 )
                t = t.right;
            else /*if( x.compareTo(t.element) == 0 ) ,don't care condition*/
                return t.equivalent;    // Match
        }
        
        return null;         // Not found
    }
}
 
class BinaryNode {
    BinaryNode( Comparable theElement ,String pareho) {
        element = theElement;
        //equivalent.add(pareho);
        equivalent=pareho;
        left = right = null;
    }
    
    Comparable element;      // The data in the node
    BinaryNode left;         // Left child
    BinaryNode right;        // Right child
    String equivalent;		 
}
