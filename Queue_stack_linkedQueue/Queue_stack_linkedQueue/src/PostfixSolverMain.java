/**
 * Classe PostfixSolverMain
 * @authors : OBOSSOU EMA-WO, SANYAN
 *            GNAGA, GEORGES 
 * @date    : 20 fevrier 2018 
 */
import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		
		Double nombre_1 = 0.0;
		Double nombre_2 = 0.0;
		Double resultat = 0.0;
		//L'expression est separee en tokens selon les espaces
		for(String token : s.split("\\s")) 
		{
			//A completer
			
			// 	on verifit si l'element lu est different  de "+,*,/,-" si oui on a 
			//un nombre , on le convertit en Double et on  stoque dans la pile  
	        	if( (!token.equals("+")) && (!token.equals("-")) && (!token.equals("*")) && ( !token.equals("/")) ) 
	        		stack.push(Double.parseDouble(token));      // on verifit on lit un nombre  on  convertit en double et on  stoque dans la pile selement les nombres 
	        	 
	        	else {                      // sinon on fait l'operation correspondant
	        		 
	        		switch(token) {
	        		
	        		case "+":               // operation à faire si l'element lu est un +
	        			  nombre_1 = stack.pop();
	        			  nombre_2 = stack.pop();
	        			  resultat = nombre_1+ nombre_2;
	        			  stack.push(resultat);
	        			  break;
	        			  
	        		case "-":     			//  operation a faire si l'element lu est un -
	        			  nombre_1 = stack.pop();
	        			  nombre_2 = stack.pop();
	        			  resultat = nombre_2- nombre_1;
	        			  stack.push(resultat);
	        			  break;
	        		
	        		case "/":    			  // operation a faire si l'element lu est un /
	        			  nombre_1 = stack.pop();
	        			  nombre_2 = stack.pop();
	        			  resultat = nombre_2 / nombre_1;
	        			  stack.push(resultat);
	        			  break;
	        			
	        		case "*":      			  // operation a faire si l'element lu est un *
	        			  nombre_1 = stack.pop();
	        			  nombre_2 = stack.pop();
	        			  resultat = nombre_1 * nombre_2;
	        			  stack.push(resultat);
	        			  break;
	        		 
	        		}
	        		
	        		
	        	}
	        
		}
     
		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}
