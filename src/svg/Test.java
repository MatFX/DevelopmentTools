package svg;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;


public class Test {

	public static void main(String[] args) 
	{
		
		SVG svg = (SVG)readObjectFromFile(new SVG());
		System.out.println("svg " + svg.toString());
	
	}
	
	
	/**
	 * Übergabe des FileNames für die Ermittlung des Files und des Einlesen des Objekts.
	 * @param iFileName Alle Objekte entgegennehmen die das Interface implementiert haben. 
	 * @return
	 */
	public static Object readObjectFromFile(IFileName iFileName)
	{
		try 
		{
			File f = new File(iFileName.getCompletePath());
			if(f.exists())
			{
				JAXBContext jc = JAXBContext.newInstance(iFileName.getClass());
				Unmarshaller u = jc.createUnmarshaller();
				IFileName saveObject = (IFileName)u.unmarshal(f);
				return saveObject;
			}
		}
		catch(UnmarshalException e)
		{
			e.printStackTrace();
		}
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		return iFileName;
	}
	
	

}
