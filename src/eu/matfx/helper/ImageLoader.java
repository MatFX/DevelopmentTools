package eu.matfx.helper;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageLoader 
{
	
	public static final String ICONS = "/icons";
	
	public static final String ICONS_EXPORT = ICONS + "/exported_png"; 
	
	public static final String SUFFIX_FILE = ".png";
	
	public static InputStream getResourceStream(String pkname, String fname) throws FileNotFoundException
	{
		String resname = "" + pkname + "/" + fname;
		File file = new File("");
		
		file = new File(file.getAbsoluteFile() + resname);
		InputStream inputStream = new FileInputStream(file.getAbsolutePath());
	
		return inputStream;
	
	
	}
	
	public static Image getImageFromIconFolder(String fileName)
	{
		Image image = null;
		
		if(!fileName.contains(ImageLoader.SUFFIX_FILE))
			fileName = fileName + ImageLoader.SUFFIX_FILE;
		try
		{
			InputStream ins = ImageLoader.getResourceStream(ICONS, fileName);
			image = new Image(ins);
			ins.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return image;
	}

	public static void writeImageToExportFolder(Image imageToWrite, String fileNameWithoutSuffix)
	{
		String resname = "" + ICONS_EXPORT + "/" + fileNameWithoutSuffix;
		if(!resname.contains(ImageLoader.SUFFIX_FILE))
			resname = resname + ImageLoader.SUFFIX_FILE;
		
		File outputFile = new File("");
		outputFile = new File(outputFile.getAbsoluteFile() + resname);
		BufferedImage bImage = SwingFXUtils.fromFXImage(imageToWrite, null);
		try {
		      ImageIO.write(bImage, "png", outputFile);
		    } catch (IOException e) {
		      throw new RuntimeException(e);
		    }
	}

	public static List<String> getImageNameListFrom(String folderName)
	{
		List<String> returnList = new ArrayList<String>();
		
		File directory = new File("");
		directory = new File(directory.getAbsoluteFile() + folderName);
		
		String[] pathNames = directory.list();
		for(String pathName : pathNames)
		{
			if(pathName.endsWith(SUFFIX_FILE))
				returnList.add(pathName);
		}
		return returnList;
	}
	
	

}