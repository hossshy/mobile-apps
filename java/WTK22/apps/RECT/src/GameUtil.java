import java.util.Random;
import java.util.Vector;
import javax.microedition.rms.RecordStore;

public class GameUtil
{
	private static Random rand = new Random();

	public static boolean isHit(int x0, int y0, int x1, int y1, int radius)
	{
		return (x0-x1) * (x0-x1) + (y0-y1) * (y0-y1) < radius * radius;
	}

	public static String[] split(String s, char delim)
	{
		Vector v = new Vector();
		for ( int st = 0, end = 0;; )
		{
			end = s.indexOf(delim, st);
			if ( end == -1 )
			{
				v.addElement(s.substring(st));
				break;
			}
			v.addElement(s.substring(st, end));
			st = (end + 1);
		}

		String[] ret = null;
		if ( v.size() > 0 )
		{
			ret = new String[v.size()];
			for ( int i = 0; i < v.size(); i++ )
			{
				ret[i] = (String) v.elementAt(i);
			}
		}
		return ret;
	}
	
	public static int rand(int num)
	{
		return (rand.nextInt() >>> 1) % num;
	}
	
	public static boolean isRecord(String key)
	{
		RecordStore rs = null;
		try
		{
			rs = RecordStore.openRecordStore(key, false);
			return rs != null;
		}
		catch ( Exception e ){}
		finally
		{
			if ( rs != null )
			{
				try
				{
					rs.closeRecordStore();
				} catch ( Exception ignore ){ignore.printStackTrace();}
			}
		}
		return false;
	}

	public static String load(String key) throws Exception
	{
		RecordStore rs = null;
		try
		{
			rs = RecordStore.openRecordStore(key, false);
			if ( rs == null )
			{
				return null;
			}
			else
			{
				byte [] w = rs.getRecord(1);
				rs.closeRecordStore();
				return new String(w);
			}
		}
		catch ( Exception e )
		{
			try
			{
				if ( rs != null )
				{
					rs.closeRecordStore();
				}
			}
			catch ( Exception ignore ){}
			throw e;
		}
	}
	
	public static void delete(String key) throws Exception
	{
		RecordStore.deleteRecordStore(key);
	}
	
	public static void save(String key, String value) throws Exception
	{
		RecordStore rs = null;
		try
		{
			byte[] w = value.getBytes();
			rs = RecordStore.openRecordStore(key, true);
			if ( rs.getNumRecords() == 0 )
			{
				rs.addRecord(w, 0, w.length);
			}
			else
			{
				rs.setRecord(1, w, 0, w.length);
			}
			
			rs.closeRecordStore();
		}
		catch ( Exception e )
		{
			try
			{
				if ( rs != null )
				{
					rs.closeRecordStore();
				}
			}
			catch ( Exception ignore) {}
			throw e;
		}
	}
}
