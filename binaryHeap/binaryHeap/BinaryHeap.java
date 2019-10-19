import java.util.*; 

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType>
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType [] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( boolean min )
    {
		this.min = min;
		currentSize = 0;
		array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1 ];
    }
    
    
    /*********************************************/    
    @SuppressWarnings("unchecked")
    public BinaryHeap( AnyType[] items, boolean min )
    {
		this.min = min;
		
		// COMPLETEZ
		// invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
		currentSize = items.length;
System.out.println("currentSize =  " + currentSize);
		
		array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1 ];
		
		int i = 1;
		for( AnyType item : items )
		{
			array[ i++ ] = item;
			modifications++;
		}
			
		
		if (min == true)
			buildMinHeap();
		else
			buildMaxHeap();
    }
    
    /*********************************************/
    public boolean offer( AnyType x )
    {
		if (x == null)
		    throw new NullPointerException("Cannot insert null in a BinaryHeap");
		
		if( currentSize + 1 == array.length )
		    doubleArray();
		
		// COMPLETEZ
		// Percolate up
		int hole = ++currentSize;
		
		
		
	//	System.out.println("/*********************************************/ \n\n");
	//	System.out.println("currentSize =  " + currentSize);
		
		if (min == true)
		{
			for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) < 0; hole /= 2)
			{
				
		//		System.out.println("-------------------------");
				
				array[ hole ] = array[ hole / 2 ];
				
		//		boolean c = (x.compareTo( array[ hole / 2 ] ) == -1)? true:false;
		//		System.out.println("x < parent =  " + c );
		//		System.out.println("hole =  " + hole);
		//		System.out.println("x =  " + x);
		//		System.out.println("parent : array[ hole / 2 ] =  " + array[ hole / 2 ]);
			}
				
		}
		else
		{
			for( ; hole > 1 && x.compareTo( array[ hole / 2 ] ) > 0; hole /= 2)
				array[ hole ] = array[ hole / 2 ];
		}
		
		
		array[ hole ] = x;
		modifications++;
		
		return true;
    }
    
    public AnyType peek()
    {
		if(!isEmpty())
		    return array[1];
		
		return null;
    }
    
    
    /*********************************************/
    public AnyType poll()
    {
    	//COMPLETEZ
    	AnyType rootKey = array[ 1 ];
    	if( !isEmpty( ) )
    	{
    		array[ 1 ] = array[ currentSize--];
    		
    /*
    		if (min == true)
    			percolateDownMinHeap( 1, currentSize );
    		else
    			percolateDownMaxHeap( 1, currentSize );
    */
    		if (min == true)
    			buildMinHeap();
    		else
    			buildMaxHeap();
    		
    		modifications--;
    	}

    	
    	return rootKey;
    }
    
    
    /*********************************************/
    public Iterator<AnyType> iterator()
    {
    	return new HeapIterator();
    }
    
    
    
    /************************************************************/
    private void buildMinHeap()
    {
    	//COMPLETEZ
    	for( int i = currentSize / 2; i > 0; i-- )
    		percolateDownMinHeap( i, currentSize );
   
    }
    
    
    /****************************************************************/
    private void buildMaxHeap()
    {
    	//COMPLETEZ
    	for( int i = currentSize / 2; i > 0; i-- )
    		percolateDownMaxHeap( i, currentSize );
    }
    
    public boolean isEmpty()
    {
	return currentSize == 0;
    }
    
    public int size()
    {
	return currentSize;
    }
    
    public void clear()
    {
	currentSize = 0;
	modifications = 0;
	array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    private static int leftChild( int i, boolean heapIndexing )
    {
    	return ( (heapIndexing == true)? 2*i : 2*i+1 );
    }
    
    private void swapReferences( int index1, int index2 )
    {
	swapReferences(array, index1, index2);
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
				    void swapReferences( AnyType[] array, int index1, int index2 )
    {
	AnyType tmp = array[ index1 ];
	array[ index1 ] = array[ index2 ];
	array[ index2 ] = tmp;
    }
    
    @SuppressWarnings("unchecked")
	private void doubleArray()
    {
	AnyType [ ] newArray;
	
	newArray = (AnyType []) new Comparable[ array.length * 2 ];
	for( int i = 0; i < array.length; i++ )
	    newArray[ i ] = array[ i ];
	array = newArray;
    }
    
    ////////////////////////////////////////////////////////////
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMinHeap( int hole, int size )
    {
    	percolateDownMinHeap(array, hole, size, true);
    }
    
    /**
     * @param array   Tableau d'element
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
        void percolateDownMinHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
    	//COMPLETEZ
    	
    	int child;
    	AnyType tmp; 
    	
 
  /* 		
    	for(tmp = array[ hole ]; leftChild( hole, heapIndexing ) < size; hole = child )
    	{
    		child = leftChild( hole, heapIndexing ); //Considérer fils de gauche
        	
        	// il y a deux fils et fils droit<fils gauche
        	if( ( child != size ) && ( array[ child + 1 ].compareTo( array[ child ] ) < 0 ) )
        		child++; //Considérer fils droit
        	if( array[ child ].compareTo( tmp ) < 0 )//fils considéré< élément à percoler
        		array[ hole ] = array[ child ];//Remonter le fils courrent de un niveau
        	else
        		break; //sortir de la boucle. L’élément à percoler sera inséréà position hole
    	}
    	
    	array[ hole ] = tmp;// Insérer l’élément à percoler à la position hole
    	    		
  */ 	
    	/*
    	for(tmp = array[ hole ]; leftChild( hole, heapIndexing ) < size; hole = child ) 
		{
			child = leftChild( hole, heapIndexing ) ;
			if ( child != size -1 && array[ child ].compareTo( array[ child + 1 ] ) < 0 )
				child++;
			if( tmp.compareTo( array[ child ] ) < 0 )
				array[ hole ] = array[ child ];
			else
				break;
		}
		
*/		
    	if (heapIndexing == true)
    	{
    		
        	for(tmp = array[ hole ]; hole * 2 <= size; hole = child )
        	{
	        	child = hole * 2; //Considérer fils de gauche
	        	
	        	// il y a deux fils et fils droit<fils gauche
	        	if( ( child != size ) && ( array[ child + 1 ].compareTo( array[ child ] ) < 0 ) )
	        		child++; //Considérer fils droit
	        	if( array[ child ].compareTo( tmp ) < 0 )//fils considéré< élément à percoler
	        		array[ hole ] = array[ child ];//Remonter le fils courrent de un niveau
	        	else
	        		break; //sortir de la boucle. L’élément à percoler sera inséréà position hole
        	}
        	
        	array[ hole ] = tmp;// Insérer l’élément à percoler à la position hole
  
    	}
    }
    
    /**********************************************************/
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMaxHeap( int hole, int size )
    {
    	percolateDownMaxHeap(array, hole, size, true);
    }
    
    /**
     * @param array         Tableau d'element
     * @param hole          Position a percoler
     * @param size          Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>> 
    	void percolateDownMaxHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
    	//COMPLETEZ
    	
    	
    	int child;
    	AnyType tmp;
    	
    	
    		
		for(tmp = array[ hole ]; leftChild( hole, heapIndexing ) < size; hole = child ) 
		{
			child = leftChild( hole, heapIndexing ) ;
			if ( child != size -1 && array[ child ].compareTo( array[ child + 1 ] ) < 0 )
				child++;
			if( tmp.compareTo( array[ child ] ) < 0 )
				array[ hole ] = array[ child ];
			else
				break;
		}
		
		array[ hole ] = tmp;
    	
   /*
    	
    	if (heapIndexing == true)
    	{
    		
    		for(tmp = array[ hole ]; leftChild( hole, heapIndexing ) < size; hole = child ) 
    		{
    			child = leftChild( hole, heapIndexing ) ;
    			if ( child != size -1 && array[ child ].compareTo( array[ child + 1 ] ) < 0 )
    				child++;
    			if( tmp.compareTo( array[ child ] ) < 0 )
    				array[ hole ] = array[ child ];
    			else
    				break;
			}
    		
			array[ hole ] = tmp;
    	}
    	
  */  	
    	
    	
    }
    
    
    /**********************************************************/
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSort( AnyType[] a )
    {
    	//COMPLETEZ
    	int taille =  a.length;
    	
    	for( int i = taille / 2; i >= 0; i--) /* construire le monceau */    		
    		percolateDownMaxHeap( a, i, taille-1, false );
    	
    
		for( int i = taille -1; i > 0; i--)
		{
    		swapReferences( a, 0, i );/* permuter le maximum (racine)
    		avec le dernière élément du monceau */
    		percolateDownMaxHeap( a, 0, i, false );
		}
    }
    
    
    /**********************************************************/
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSortReverse( AnyType[] a )
    {
    	//COMPLETEZ
    	
    	int taille =  a.length;
    	
    	for( int i = taille / 2; i >= 0; i--) /* construire le monceau */    		
    		percolateDownMinHeap( a, i, taille, false );
    	
    
		for( int i = taille -1; i > 0; i--)
		{
    		swapReferences( a, 0, i );/* permuter le minimum (racine)
    		avec le dernière élément du monceau */
    		percolateDownMinHeap( a, 0, i, false );
		}
    }
    
    
    /**********************************************************/
    public String nonRecursivePrintFancyTree()
    {
    	String outputString = "";
	
		//COMPLETEZ
  /*  	
        for(AnyType item : a)
        {
           outputString += item;
           outputString += ", ";
        }

        return outputString.substring(0,outputString.length()-2);
   */     
        

    	return outputString;
    }
    
    public String printFancyTree()
    {
	return printFancyTree(1, "");
    }
    
    
    /**********************************************************/
    private String printFancyTree( int index, String prefix)
    {
	String outputString = "";
	
	outputString = prefix + "|__";
	
	if( index <= currentSize )
	    {
		boolean isLeaf = index > currentSize/2;
		
		outputString += array[ index ] + "\n";
		
		String _prefix = prefix;
		
		if( index%2 == 0 )
		    _prefix += "|  "; // un | et trois espace
		else
		    _prefix += "   " ; // quatre espaces
		
		if( !isLeaf ) {
		    outputString += printFancyTree( 2*index, _prefix);
		    outputString += printFancyTree( 2*index + 1, _prefix);
		}
	    }
	else
	    outputString += "null\n";
	
	return outputString;
    }
    
    //////////////////////////////////////////////////////////////////
    
    
    private class HeapIterator implements Iterator 
    {
    	int currentPosition = 1;  // Position actuelle de l'élément.
    	int concurrentModifications = modifications;
    	
		public boolean hasNext() 
		{
		    //COMPLETEZ
			return ( ( currentPosition <= currentSize)? true:false );
		}
	
		public Object next() throws NoSuchElementException, 
					    ConcurrentModificationException, 
					    UnsupportedOperationException 
		{
		    //COMPLETEZ
			if ( !hasNext() ) {
                throw new NoSuchElementException();
            }
			
			if ( concurrentModifications != modifications ) {
                throw new ConcurrentModificationException();
            }
			
            return array[ currentPosition++ ];
		}
		
		public void remove() 
		{
		    throw new UnsupportedOperationException();
		}
    }
}









































