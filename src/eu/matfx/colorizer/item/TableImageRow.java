package eu.matfx.colorizer.item;

import eu.matfx.colorizer.ColorizedImage;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * A row in the table view
 * @author m.goerlich
 *
 */
public class TableImageRow
{
	private ImagePreview imagePreview;
	
	private Color newColorValue = Color.BLACK;
	
	private String suffix = "_000000";
	
	private ColorizedImage colorizedImageView;
	
	
	public TableImageRow(ImagePreview imagePreview)
	{
		this.imagePreview = imagePreview;
		this.suffix = "_"+newColorValue.toString(); 
		//initial das unveränderte?
		this.colorizedImageView = new ColorizedImage(imagePreview.getImage(), newColorValue);
	}

	public Color getNewColorValue() {
		return newColorValue;
	}

	/**
	 * Color change and start the mechanism of coloring the image
	 * @param newColorValue
	 */
	public void setNewColorValue(Color newColorValue) {
		this.newColorValue = newColorValue;
		colorizedImageView.setNewColor(this.newColorValue);
		
		//Change suffix only when _0x detected
		if(this.suffix.startsWith("_0x"))
			this.suffix = "_"+newColorValue.toString();
	}
	
	public String getFileNameToShow() {
		return imagePreview.getFileNameToShow();
	}
	
	public String getSuffix()
	{
		return suffix;
	}
	
	/**
	 * its possible that the user define his own suffix
	 * <br>No own suffix the default is the hex value of the current color
	 * @param suffix
	 */
	public void setSuffix(String suffix)
	{
		this.suffix = suffix;
	}
	
	/**
	 * Return of the colorised image
	 * @return
	 */
	public ImageView getPreview()
	{
		return colorizedImageView;
	}
	
	/**
	 * needed when user change the image of the combobox
	 * <br>the content of the table get new pic and all defined color values will be applied to the images
	 * @param imagePreview
	 */
	public void setNewImagePreview(ImagePreview imagePreview)
	{
		this.imagePreview = imagePreview;
		this.colorizedImageView.setNewOriginalImage(imagePreview.getImage());
		
	}
	
	
	

}
