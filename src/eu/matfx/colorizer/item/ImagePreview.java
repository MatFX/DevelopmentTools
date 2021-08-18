package eu.matfx.colorizer.item;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * combobox content
 * <br>. 
 * @author m.goerlich
 *
 */
public class ImagePreview implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7113359383984766929L;

	private String fileNameToShow;
	
	private Image image;
	
	public ImagePreview(Image image, String fileNameToShow)
	{
		this.fileNameToShow = fileNameToShow;
		this.image = image;
	}

	public String getFileNameToShow() {
		return fileNameToShow;
	}

	public Image getImage() {
		return image;
	}

	public void setFileNameToShow(String fileNameToShow) {
		this.fileNameToShow = fileNameToShow;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public String toString()
	{
		return this.fileNameToShow;
	}
}
