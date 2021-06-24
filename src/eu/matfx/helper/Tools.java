package eu.matfx.helper;

public class Tools 
{
	
	/**
	 * with any css version it's possible to write three digits for a rgb color.
	 * <br>example: #fb0 same as #ffbb00
	 * <br>method give a non scriptkiddie color hex value
	 * @param stop_color
	 * @return
	 */
	public static String getSixDigitColor(String stop_color) 
	{
		StringBuilder sb = new StringBuilder("#");
		String temp = stop_color.replace("#", "");
		if(temp.length() == 3)
		{
			String[] splittedValue = temp.split("");
			System.out.println("split " + splittedValue.length);
			for(int i = 0; i < splittedValue.length; i++)
			{
				sb.append(splittedValue[i]);
				sb.append(splittedValue[i]);
			}
			
		}
		else
			sb.append(temp);
		
		
		return sb.toString();
	}
	
	public static String toHexString(int value, boolean fill)
	{
		boolean leading = true;
		StringBuffer buffer = new StringBuffer();

		for (int i = 3; i >= 0; i--)
		{
			byte digit = (byte)(value >>> i * 8);

			if (digit != 0)
				leading = false;
			
			if ((leading && fill) |!leading)
				buffer.append(toHexString(digit));
		}

		return buffer.toString();
	}	

	public static String toHexString(byte value)
	{
		char[] chars = new char[2];
		chars[0] = digits[(value >>> 4) & 0xF];
		chars[1] = digits[value & 0x0F];
		return new String(chars);
	}
	

    private final static char[] digits = 
    {
    	'0' , '1' , '2' , '3' , '4' , '5' ,
    	'6' , '7' , '8' , '9' , 'A' , 'B' ,
    	'C' , 'D' , 'E' , 'F'
    };
    

}
