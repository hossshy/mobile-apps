/*
 * Last modified: 2010/03/23 12:49:58
 * @author Kazamai, Kou
 */
package com.strnet.game.common.list;

public class StringLightList
{
	private String[] elements;
	private int size;

	public StringLightList()
	{
		this(10);
	}
	
	public StringLightList(int capacity)
	{
		elements = new String[capacity];
		size = 0;
	}

	public void add(String o)
	{
		int arrLen = elements.length;
		if ( (size+1) > arrLen )
		{
			int newSize = size + (size / 2);
			String[] old = elements;
			elements = new String[newSize];
			System.arraycopy(old, 0, elements, 0, size);
		}
		
		elements[size++] = o;
	}
	
	public void addAll(StringLightList list)
	{
		for ( int i = 0; i < list.size(); i++ )
		{
			add(list.get(i));
		}
	}

	public int indexOf(String o)
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
		int numMoved = size - index - 1;
		if (numMoved >= 0)
		{
			System.arraycopy(elements, index+1, elements, index, numMoved);
			elements[--size] = null;
		}
	}

	public void remove(String o)
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

	public String get(int index)
	{
		if ( size == 0 )
		{
			return null;
		}
		else
		{
			return elements[index];
		}
	}

	public boolean contains(String o)
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
		String[] old = elements;
		elements = new String[size];
		System.arraycopy(old, 0, elements, 0, size);
	}
}