package eu.matfx.colorizer.item;

import eu.matfx.colorizer.ColorizedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

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
	
	public String toString()
	{
		return imagePreview.toString();
	}
	
	public String getSuffix()
	{
		return suffix;
	}
	
	public void setSuffix(String suffix)
	{
		this.suffix = suffix;
	}
	
	public ImageView getPreview()
	{
		return colorizedImageView;
	}
	
	public void setNewImagePreview(ImagePreview imagePreview)
	{
		this.imagePreview = imagePreview;
		this.colorizedImageView.setNewOriginalImage(imagePreview.getImage());
		
	}
	
	
	

}
