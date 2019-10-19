/**
 * Classe ArrayQueue
 * @authors : OBOSSOU EMA-WO, SANYAN
 *            GNAGA, GEORGES 
 * @date    : 20 fevrier 2018 
 */
import java.util.EmptyStackException;

public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;

	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		//A completer
		table = (AnyType[]) new Object[1] ;    // initialiation de la taille du tableau à 1
		
	}
	
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
		
		if(this.empty()) return null;
		else {
			AnyType v = table[startindex];  // v contient l'element au top 
			return v; 
		}
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
			if(this.empty())
				throw new EmptyQueueException();
			else {
			startindex= (startindex+1) % table.length ; // assigne l'index du premier element non nul dans le tableau
			size-- ;
			}
	}
	
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire (utiliser la fonction resize definie plus bas)
	//complexité asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	public void push(AnyType item)
	{
		//A completer
		if(size == table.length) 
			this.resize(2);
			table[(startindex + (size++))% table.length]= item;  // rendre utilisable les cases mis a null dans tableau 
																 // donc on peut y inserer des elements
	}
   
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexité asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		//A completer
		int oldTaille = table.length;
		int newTaille = size*resizeFactor;
		AnyType[] tableau =  (AnyType[]) new Object[newTaille];
		
		for(int i = 0; i < size; i++)						  // pour bien repartir les données dans le tableau
			tableau[i]= table[(startindex +i) % oldTaille];  // eviter de laisse des cases vide au milieu ou au debut du tableau    
		table=null;
		table = (AnyType[]) new Object[newTaille]; 			 // réinitialisation de table avec la nouvelle taille
		
		System.arraycopy(tableau,0,table,0,newTaille);  	  // copie des elements de tableau vers  table;
		
		startindex =0;    //  reinitialisation de l'indice de debut 0 ;
		tableau= null;
	}
}

