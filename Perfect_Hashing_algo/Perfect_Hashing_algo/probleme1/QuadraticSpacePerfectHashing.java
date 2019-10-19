package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		
		return (items[key] == null ? false:true);
	}

	public boolean containsValue(AnyType x )
	{
		return ( containsKey(getKey(x)) );

	}

	public void remove (AnyType x) {
		
		items[getKey(x)] = null;

	}

	public int getKey (AnyType x) {
		
		return ((a * x.hashCode() + b ) % p ) % (items.length);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			
			items = (AnyType[]) new Object[0];
		
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

				
			items = (AnyType[]) new Object[1];
			items[0]=array.get(0);
			return;
		}

		do
		{
			items = null;

			
			a = generator.nextInt(p-1)+1; //zero pas inclus
			b = generator.nextInt(p);
			items = (AnyType[]) new Object[array.size() * array.size()];
		}
		while( collisionExists( array ) );
	}

	@SuppressWarnings("unchecked")
	private boolean collisionExists(ArrayList<AnyType> array)
	{
		
		for(AnyType i : array){
			int position = ((a * i.hashCode() + b ) % p ) % (items.length);
			if(containsKey(position))
				return true;
			else 
				items[position]=i;
		}
		return false;
	}
		

	
	
	public String toString () {
		String result = "";
		
		// A completer
		for(AnyType i : items)
		{
			if (i!=null)
			result += "(" + String.valueOf(getKey(i)) + ", " + String.valueOf(i) + ")" + ", ";
		}
		
		result = result.substring(0, result.length()-2) +".";
		
		return result; 
	}
}
