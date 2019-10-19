
/**
 * Classe LinkedListQueue
 * @authors : OBOSSOU EMA-WO, SANYAN
 *            GNAGA, GEORGES 
 * @date    : 20 fevrier 2018 
 */
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node    next;
		
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	public int i, j;
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexité asymptotique: O(1)
	public AnyType peek()
	{
		//A completer
		if (empty())
			return null;
		
		
		//return last.getNext().getData();
		Node<AnyType> head = last.getNext();
		return head.getData();
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if (empty())
			throw new EmptyQueueException();
		else 
		{
			//Node<AnyType> first = new Node(last.getNext().getData(), last.getNext());
	//		last.setNext(last.getNext());

			
			Node<AnyType> newHead = last.getNext().getNext();
			last.setNext(newHead);
			size-- ;
			
			
			j++;
			System.out.println("Pop  " + j + "  reussi" );
			System.out.println("last   =  " + newHead.data );
		}
	}
	
	//Ajoute un element a la fin de la file
	//complexité asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer
		if (empty())		
		{
			last = new Node<AnyType>(item, null);
			last.setNext(last); 
			size = 1;
		}
		else
		{
			Node<AnyType> aAjouter = new Node<AnyType>(item, last.getNext());
			last.setNext(aAjouter); 
			last = aAjouter; 
			size++ ;
		}	
		
		
		i = i + 1;
		System.out.println("push  " + i + "  reussi" );
		System.out.println("last   =  " + last.getData() );
	} 	
}
