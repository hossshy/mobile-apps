/*
 * Last modified: 2009/08/04 22:09:58
 * @author Kazamai, Kou
 */
package com.strnet.game.main;

public class FixedPoint
{
	private static final int[] COS = new int[]
		{256, 256, 256, 255, 255, 255, 254, 254, 253, 252, 251, 250, 249, 248, 247, 246, 245, 243, 242, 241, 239, 237, 236, 234, 232, 230, 228, 226, 224, 222, 219, 217, 215, 212, 210, 207, 204, 202, 199, 196, 193, 190, 187, 184, 181, 178, 175, 171, 168, 165, 161, 158, 154, 150, 147, 143, 139, 136, 132, 128, 124, 120, 116, 112, 108, 104, 100, 96, 92, 88, 83, 79, 75, 71, 66, 62, 58, 53, 49, 44, 40, 36, 31, 27, 22, 18, 13, 9, 4, 0, -4, -9, -13, -18, -22, -27, -31, -36, -40, -44, -49, -53, -58, -62, -66, -71, -75, -79, -83, -88, -92, -96, -100, -104, -108, -112, -116, -120, -124, -128, -132, -136, -139, -143, -147, -150, -154, -158, -161, -165, -168, -171, -175, -178, -181, -184, -187, -190, -193, -196, -199, -202, -204, -207, -210, -212, -215, -217, -219, -222, -224, -226, -228, -230, -232, -234, -236, -237, -239, -241, -242, -243, -245, -246, -247, -248, -249, -250, -251, -252, -253, -254, -254, -255, -255, -255, -256, -256, -256, -256, -256, -256, -256, -255, -255, -255, -254, -254, -253, -252, -251, -250, -249, -248, -247, -246, -245, -243, -242, -241, -239, -237, -236, -234, -232, -230, -228, -226, -224, -222, -219, -217, -215, -212, -210, -207, -204, -202, -199, -196, -193, -190, -187, -184, -181, -178, -175, -171, -168, -165, -161, -158, -154, -150, -147, -143, -139, -136, -132, -128, -124, -120, -116, -112, -108, -104, -100, -96, -92, -88, -83, -79, -75, -71, -66, -62, -58, -53, -49, -44, -40, -36, -31, -27, -22, -18, -13, -9, -4, 0, 4, 9, 13, 18, 22, 27, 31, 36, 40, 44, 49, 53, 58, 62, 66, 71, 75, 79, 83, 88, 92, 96, 100, 104, 108, 112, 116, 120, 124, 128, 132, 136, 139, 143, 147, 150, 154, 158, 161, 165, 168, 171, 175, 178, 181, 184, 187, 190, 193, 196, 199, 202, 204, 207, 210, 212, 215, 217, 219, 222, 224, 226, 228, 230, 232, 234, 236, 237, 239, 241, 242, 243, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 254, 255, 255, 255, 256, 256, 256, 256};

	private static final int[] SIN = new int[]
		{-4, -9, -13, -18, -22, -27, -31, -36, -40, -44, -49, -53, -58, -62, -66, -71, -75, -79, -83, -88, -92, -96, -100, -104, -108, -112, -116, -120, -124, -128, -132, -136, -139, -143, -147, -150, -154, -158, -161, -165, -168, -171, -175, -178, -181, -184, -187, -190, -193, -196, -199, -202, -204, -207, -210, -212, -215, -217, -219, -222, -224, -226, -228, -230, -232, -234, -236, -237, -239, -241, -242, -243, -245, -246, -247, -248, -249, -250, -251, -252, -253, -254, -254, -255, -255, -255, -256, -256, -256, -256, -256, -256, -256, -255, -255, -255, -254, -254, -253, -252, -251, -250, -249, -248, -247, -246, -245, -243, -242, -241, -239, -237, -236, -234, -232, -230, -228, -226, -224, -222, -219, -217, -215, -212, -210, -207, -204, -202, -199, -196, -193, -190, -187, -184, -181, -178, -175, -171, -168, -165, -161, -158, -154, -150, -147, -143, -139, -136, -132, -128, -124, -120, -116, -112, -108, -104, -100, -96, -92, -88, -83, -79, -75, -71, -66, -62, -58, -53, -49, -44, -40, -36, -31, -27, -22, -18, -13, -9, -4, 0, 4, 9, 13, 18, 22, 27, 31, 36, 40, 44, 49, 53, 58, 62, 66, 71, 75, 79, 83, 88, 92, 96, 100, 104, 108, 112, 116, 120, 124, 128, 132, 136, 139, 143, 147, 150, 154, 158, 161, 165, 168, 171, 175, 178, 181, 184, 187, 190, 193, 196, 199, 202, 204, 207, 210, 212, 215, 217, 219, 222, 224, 226, 228, 230, 232, 234, 236, 237, 239, 241, 242, 243, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 254, 255, 255, 255, 256, 256, 256, 256, 256, 256, 256, 255, 255, 255, 254, 254, 253, 252, 251, 250, 249, 248, 247, 246, 245, 243, 242, 241, 239, 237, 236, 234, 232, 230, 228, 226, 224, 222, 219, 217, 215, 212, 210, 207, 204, 202, 199, 196, 193, 190, 187, 184, 181, 178, 175, 171, 168, 165, 161, 158, 154, 150, 147, 143, 139, 136, 132, 128, 124, 120, 116, 112, 108, 104, 100, 96, 92, 88, 83, 79, 75, 71, 66, 62, 58, 53, 49, 44, 40, 36, 31, 27, 22, 18, 13, 9, 4, 0};

	// 0~45
	private static final int[] TAN = new int[]
		{256, 247, 239, 231, 223, 215, 207, 200, 193, 186, 179, 173, 166, 160, 154, 148, 142, 136, 130, 125, 119, 114, 109, 103, 98, 93, 88, 83, 78, 73, 69, 64, 59, 54, 50, 45, 41, 36, 31, 27, 22, 18, 13, 9, 4, 0};
	
	private static final int SHIFT = 8;

	public static int toFixedInt(int i)
	{
		//return i * (1 << SHIFT);
		return i << SHIFT;
	}

	public static int toInt(int fix)
	{
		return fix >> SHIFT;
	}

	public static int resiveAngle(int angle)
	{
		if ( angle < 0 )
		{
			return 360 + angle;
		}
		else if ( angle >= 360 )
		{
			return resiveAngle(angle - 360);
		}
		else
		{
			return angle;
		}
	}

	public static int cos(int fix)
	{
		fix = resiveAngle(fix);
		if ( fix < 0 || fix > COS.length ) return 0;
		
		return COS[fix];
	}

	public static int sin(int fix)
	{
		fix = resiveAngle(fix);
		if ( fix < 0 || fix > SIN.length ) return 0;
		return SIN[fix];
	}

	public static int getAngle(int fixX, int fixY)
	{
		int fix = getAngle1(fixX, fixY);
		fix = resiveAngle(fix);
		return fix;
	}

	private static int getAngle1(int fixX, int fixY)
	{
		if ( fixX < 0 && fixY < 0 )
		{
			if ( -fixY > -fixX )
			{
				// 90-135
				return (360 - getAngle2(-fixY, -fixX)) + 90;
			}
			else
			{
				// 135-180
				return getAngle2(-fixX, -fixY) - 180;
			}
		}
		else if ( fixX < 0 )
		{
			if ( fixY > -fixX )
			{
				// 180-225
				return getAngle2(fixY, -fixX) - 90;
			}
			else
			{
				// 225-270
				return (360 - getAngle2(-fixX, fixY)) + 180;
			}
		}
		else if ( fixY < 0 )
		{
			if ( -fixY > fixX )
			{
				// 45-90
				return getAngle2(-fixY, fixX) - 270;
			}
			else
			{
				// 0-45
				return 360 - getAngle2(fixX, -fixY);
			}
		}
		else
		{
			if ( fixY > fixX )
			{
				// 270-315
				return (360 - getAngle2(fixY, fixX)) + 270;
			}
			else
			{
				// 315-360
				int ret = getAngle2(fixX, fixY);
				if ( ret >= 360 )
				{
					ret -= 360;
				}
				return ret;
			}
		}
	}

	private static int getAngle2(int fixX, int fixY)
	{
		if ( fixX == 0 ) return 0;
		return 360 - toInt(divide(fixY, fixX) * 45);
	}

	public static int multiply(int fixX, int fixY)
	{
		return (fixX * fixY) >> SHIFT;
	}

	public static int divide(int fixX, int fixY)
	{
		return (fixX << SHIFT) / fixY;
	}
}