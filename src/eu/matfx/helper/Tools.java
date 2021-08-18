package eu.matfx.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    
    /**
     * minimize oder maximize an image.
     * <br>scalefactor 0.5f = 50% percent size from the original dimension
     */
	public static ImageView scaleImage(Image image, double scaleFactorX,  double scaleFactorY)
	{
		if(image == null || scaleFactorX == 0 || scaleFactorY == 0)
			return new ImageView(image);
		
		
		double newWidth = image.getWidth() * scaleFactorX;
		double newHeight = image.getHeight() * scaleFactorY;
		
		ImageView returnValue = new ImageView(image);
		
		
		returnValue.setFitWidth(newWidth);
		returnValue.setFitHeight(newHeight);
		return returnValue;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Object & Serializable> T copyObject(final T original) throws IllegalStateException
	{
		if (original == null)
		{
			return null;
		}
		
		try (final ByteArrayOutputStream bout = new ByteArrayOutputStream(); final ObjectOutputStream objectout = new ObjectOutputStream(bout);)
		{
			objectout.writeObject(original);
			// Object rekonstruieren
			final byte[] objBytes = bout.toByteArray();

			try (final ByteArrayInputStream bin = new ByteArrayInputStream(objBytes); final ObjectInputStream objectin = new ObjectInputStream(bin);)
			{
				return (T) objectin.readObject();
			}
		}

		catch (final IOException | ClassNotFoundException e)
		{
			//Could not happen here
		}
		throw new IllegalStateException("copyobject failed to copy object of type " + original.getClass());

	}
    

}
