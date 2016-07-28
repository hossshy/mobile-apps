/*
 * Last modified: 2009/06/15 14:32:40
 * author: Hoshi, Yuichiro
 */

import java.io.*;
public class FileSplit
{
	static final int SPLIT_SIZE = 65536;
	
	public static void main(String[] args) throws Exception
	{
		File file = new File(args[0]);
		String parent = file.getParent();

		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		byte[] buf = new byte[64];
		int len;
		ByteArrayOutputStream tmp = new ByteArrayOutputStream();
		while ( (len = in.read(buf)) != -1 )
		{
			tmp.write(buf, 0, len);
		}
		in.close();

		byte[] data = tmp.toByteArray();
		int seek = 0;
		int end;
		int i = 0;
		boolean loop = true;
		while ( loop )
		{
			if ( (seek + SPLIT_SIZE) > data.length )
			{
				end = data.length - seek;
				loop = false;
			}
			else
			{
				end = SPLIT_SIZE;
			}
			
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(parent+File.separator+ (i++) + ".spl"));
			out.write(data, seek, end);
			out.flush();
			out.close();
			seek += end;
		}
	}
}