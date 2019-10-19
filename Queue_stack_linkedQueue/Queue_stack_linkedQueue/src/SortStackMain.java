/**
 * Classe SortStackMain
 * @authors : OBOSSOU EMA-WO, SANYAN
 *            GNAGA, GEORGES 
 * @date    : 20 fevrier 2018 
 */

import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_VALUE = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_VALUE));
		
		stack = sortStack(stack);
		
		boolean countIsGood = size(stack) == COUNT;
			
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				sortIsGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		else if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}
    
	private static int size(Stack<Integer> stack) {
		//A completer
		int taille = 0;    // La taille de la pile.
		
	    for(Integer pile : stack)
	    	taille++;

	    
	    return (int)taille;
    }
    
	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		Stack<Integer> stackTemp = new Stack<Integer>();
		
		
		while(!stack.isEmpty())
		{

			int temp = stack.pop();
			
			
			while(!stackTemp.isEmpty() && stackTemp.peek() < temp)
			{

				stack.push(stackTemp.pop());
			}
			
			stackTemp.push(temp);
		}
		
		
		return stackTemp;
	}
}
