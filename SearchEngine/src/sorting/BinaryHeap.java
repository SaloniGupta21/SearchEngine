package sorting;

import java.util.Random;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>>
{
	public BinaryHeap( )
	{
	   this( DEFAULT_CAPACITY );
	}
	public BinaryHeap( int capacity )
	{
	   actualSize = 0;
	   array = (AnyType[]) new Comparable[ capacity + 1 ];
	}
	public BinaryHeap( AnyType [ ] items )
	{
	       actualSize = items.length;
	       array = (AnyType[]) new Comparable[ ( actualSize + 2 ) * 11 / 10 ];
	
	       int i = 1;
	       for( AnyType item : items )
	           array[ i++ ] = item;
	       buildHeap( );
	}
	public void insert( AnyType x )
	{
	   if( actualSize == array.length - 1 )
	       enlargeArray( array.length * 2 + 1 );
	
	       // Percolate up
	   int hole = ++actualSize;
	   for( array[ 0 ] = x; x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2 )
	       array[ hole ] = array[ hole / 2 ];
	   array[ hole ] = x;
	}
	private void enlargeArray( int newSize )
	{
	       AnyType [] old = array;
	       array = (AnyType []) new Comparable[ newSize ];
	       for( int i = 0; i < old.length; i++ )
	           array[ i ] = old[ i ];        
	}
	public AnyType findMin( )
	{
	   if( isEmpty( ) )
	       //throw new UnderflowException( );
	  	 return null;
	   return array[ 1 ];
	}
	public AnyType deleteMin( )
	{
	   if( isEmpty( ) )
	       //throw new UnderflowException( );
	  	 return null;
	
	   AnyType minItem = findMin( );
	   array[ 1 ] = array[ actualSize-- ];
	   percolateDown( 1 );
	
	   return minItem;
	}
	private void buildHeap( )
	{
	   for( int i = actualSize / 2; i > 0; i-- )
	       percolateDown( i );
	}
	public boolean isEmpty( )
	{
	   return actualSize == 0;
	}
	public void makeEmpty( )
	{
	   actualSize = 0;
	}
	
	private static final int DEFAULT_CAPACITY = 10;
	
	private int actualSize;      // Number of elements in heap
	private AnyType [ ] array; // The heap array
	private void percolateDown( int hole )
	{
	   int child;
	   AnyType tmp = array[ hole ];
	
	   for( ; hole * 2 <= actualSize; hole = child )
	   {
	       child = hole * 2;
	       if( child != actualSize &&
	               array[ child + 1 ].compareTo( array[ child ] ) < 0 )
	           child++;
	       if( array[ child ].compareTo( tmp ) < 0 )
	           array[ hole ] = array[ child ];
	       else
	           break;
	   }
	   array[ hole ] = tmp;
	}
	public static void main( String [ ] args )
	{
	   int items = 10000;
	   Random random = new Random();
	   random.setSeed(100);
	   
	   long startTime = System.currentTimeMillis();
	   
	   BinaryHeap<Integer> binaryHeap = new BinaryHeap<>( );
	   
	   for (int i=0; i < items; i++)
	       //binaryHeap.insert( i );
		   binaryHeap.insert(random.nextInt());
	
	   for(int i=0; i < items; i++ )
	       if( binaryHeap.deleteMin() != i )
	           System.out.println( "Oops! " + i );
	
	   long totalTime = System.currentTimeMillis() - startTime; 
	   System.out.println("totalTime = " + totalTime);
	}
}
