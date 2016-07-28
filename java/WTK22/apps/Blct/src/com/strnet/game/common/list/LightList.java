/*
 * Last modified: 2010/02/26 16:54:40
 * @author Kazamai, Kou
 */
package com.strnet.game.common.list;

public class LightList
{
	private Object[] elements;
	private int size;

	public LightList()
	{
		this(10);
	}
	
	public LightList(int capacity)
	{
		elements = new Object[capacity];
		size = 0;
	}

	public void add(Object o)
	{
		int arrLen = elements.length;
		if ( (size+1) > arrLen )
		{
			int newSize = size + (size / 2);
			Object[] old = elements;
			elements = new Object[newSize];
			System.arraycopy(old, 0, elements, 0, size);
		}
		
		elements[size++] = o;
	}

	public int indexOf(Object o)
	{
		for ( int i = 0; i < size; i++ )
		{
			if ( o.equals(elements[i]) )
			{
				return i;
			}
		}
		return -1;
	}

	public void remove(int index)
	{
		//int numMoved = size - index - 1;
		int numMoved = size - index;
		if (numMoved > 0)
		{
			System.arraycopy(elements, index+1, elements, index, numMoved);
			elements[--size] = null;
		}
	}

	public void remove(Object o)
	{
		int index = indexOf(o);
		if ( index >= 0 )
		{
			remove(index);
		}
	}

	public int size()
	{
		return size;
	}

	public Object get(int index)
	{
		return elements[index];
	}

	public boolean contains(Object o)
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
			elements[i] = null;
		}
		size = 0;
	}

	public void trimToSize()
	{
		Object[] old = elements;
		elements = new Object[size];
		System.arraycopy(old, 0, elements, 0, size);
	}
}