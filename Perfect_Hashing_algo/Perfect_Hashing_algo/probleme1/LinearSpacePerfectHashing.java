package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			data = new QuadraticSpacePerfectHashing[0];
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;
			data = new QuadraticSpacePerfectHashing[1];
			data[0] = (QuadraticSpacePerfectHashing<AnyType>)array.get(0);
			return;
		}

		data = new QuadraticSpacePerfectHashing[array.size()];
		a = generator.nextInt(p-1)+1;
		b = generator.nextInt(p);
		
		for (AnyType i : array)
		{
			//if(i.hashCode()>=p) break;
			int position = getKey(i);
			if (data[position]==null)
			{
				ArrayList<AnyType> arrayTemp = new ArrayList<AnyType>();
				arrayTemp.add(i);
				data[position]=  new QuadraticSpacePerfectHashing<AnyType>(arrayTemp);
				
			}
			else 
			{
				ArrayList<AnyType> arrayTemp = new ArrayList<AnyType>();
				for(AnyType items : data[position].items)
				{	
					if(items!=null)
						arrayTemp.add(items);
				}
				arrayTemp.add(i);
				data[position] = new QuadraticSpacePerfectHashing<AnyType>(arrayTemp);
			}
		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		return (data[key] == null ? false:true);

	}
	
	public int getKey (AnyType x) {
		return ((a * x.hashCode() + b ) % p ) % (data.length);
		
	}
	
	public boolean containsValue (AnyType x) {
		if(containsKey(getKey(x))){
			return data[getKey(x)].containsValue(x);
				
		}
		else 
			return false;

		

			
	}
	
	public void remove (AnyType x) {
		
		int key= getKey(x);
		if(containsValue(x))
			data[key].remove(x);
	}

	public String toString () {
		String result = "";
		for(int i=0;i<data.length;i++)
		{
			if(data[i]!=null)
			result += "["+ String.valueOf(i) +"] " +"->" + data[i].toString() + System.lineSeparator() ;
		}
			
		
		return result; 
	}
	
}
