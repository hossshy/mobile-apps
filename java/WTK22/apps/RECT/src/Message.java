public class Message
{
	private String[] msg;
	private int low;
	private int count;
	
	public Message(String m)
	{
		this.msg = GameUtil.split(m, '\n');
		count = 0;
		low = 0;
	}
	
	public void nextLow()
	{
		low++;
		count = 0;
	}
	public boolean isNextLow()
	{
		return (low < msg.length - 1);
	}
	
	public void nextCount()
	{
		count++;
	}

	public boolean isNextCount()
	{
		return (count <= msg[low].length());
	}
	
	public void paint(MainCanvas g)
	{
		g.setColor(255,255,255);
		
		for ( int i = 0; i < low; i++ )
		{
			g.drawString(msg[i], 40, 100 + (14 * i));
		}
		int max = Math.min(msg[low].length(), count);
		String tmp = "";
		for ( int i = 0; i <= max; i++ )
		{
			tmp = msg[low].substring(0, i);
			g.drawString(tmp, 40, 100 + (14*low));
		}
	}
}
