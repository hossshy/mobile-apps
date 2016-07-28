/*
 * Last modified: 2010/02/26 16:54:29
 * @author Kazamai, Kou
 */
package com.strnet.game.common.list;

public class IntLightList
{
	public static final int VALUE_NULL = -1;
	
	private int[] elements;
	private int size;

	public IntLightList()
	{
		this(10);
	}
	
	public IntLightList(int capacity)
	{
		elements = new int[capacity];
		size = 0;
	}

	public void add(int o)
	{
		int arrLen = elements.length;
		if ( (size+1) > arrLen )
		{
			int newSize = size + (size / 2);
			int[] old = elements;
			elements = new int[newSize];
			System.arraycopy(old, 0, elements, 0, size);
		}
		
		elements[size++] = o;
	}

	public int indexOf(int o)
	{
		for ( int i = 0; i < size; i++ )
		{
			if ( o == elements[i] )
			{
				return i;
			}
		}
		return VALUE_NULL;
	}

	public void removeByIndex(int index)
	{
		int numMoved = size - index - 1;
		if (numMoved >= 0)
		{
			System.arraycopy(elements, index+1, elements, index, numMoved);
			elements[--size] = VALUE_NULL;
		}
	}

	public void remove(int o)
	{
		int index = indexOf(o);
		if ( index >= 0 )
		{
			removeByIndex(index);
		}
	}

	public int size()
	{
		return size;
	}

	public int get(int index)
	{
		if ( size == 0 )
		{
			return 0;
		}
		else
		{
			return elements[index];
		}
	}

	public boolean contains(int o)
	{
		return (indexOf(o) >= 0);
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public void clear()
	{
		for ( int i = 0; i < size; i++ )
		{
			elements[i] = VALUE_NULL;
		}
		size = 0;
	}

	public void trimToSize()
	{
		int[] old = elements;
		elements = new int[size];
		System.arraycopy(old, 0, elements, 0, size);
	}
}