package eu.matfx.colorizer;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * the imageview in the table column
 * @author m.goerlich
 *
 */
public class ColorizedImage extends ImageView
{
	
	private Image orginalImage;

	private SimpleObjectProperty<Color> colorProperty;
	
	public ColorizedImage(Image imageFromIconFolder, Color initialColor) 
	{
		this.orginalImage = imageFromIconFolder;
		
		//color property with change mechanism
		colorProperty = new SimpleObjectProperty<Color>();
		colorProperty.addListener(new ChangeListener<Color>() 
		{

			@Override
			public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {
				if(newValue != null)
				{
					colorizeImage(newValue);
					
				}
				
			}
			
		});
		setNewColor(initialColor);
	}

	/**
	 * Change the color of the current viewed image
	 * @param newValue
	 */
	protected void colorizeImage(Color newValue) {
		

		int maxX = (int) orginalImage.getWidth();
		int maxY = (int) orginalImage.getHeight();
		
		PixelReader pixelReader =   orginalImage.getPixelReader();
		
		WritableImage writableImage = new WritableImage(maxX, maxY);
		PixelWriter pixelWriter = writableImage.getPixelWriter();
		
		for (int y = 0; y < maxY; y++) 
		{
			for (int x = 0; x < maxX; x++) 
			{
				Color color = pixelReader.getColor(x, y);
				
				Color newColor = new Color(newValue.getRed(), newValue.getGreen(), newValue.getBlue(), color.getOpacity());
				pixelWriter.setColor(x, y, newColor);
			}
		}
		ColorizedImage.this.setImage(writableImage);
		
	}

	/**
	 * user changed the color; the TableImageRow triggered this method
	 * @param newColorValue
	 */
	public void setNewColor(Color newColorValue) {
		colorProperty.set(newColorValue);
	}
	
	/**
	 * user changed the selection of the combobox; the items of the tableview get the new image
	 * <br>and every TableViewRow triggered this method.
	 * @param newImage
	 */
	public void setNewOriginalImage(Image newImage)
	{
		this.orginalImage = newImage;
		//colorise image with the current color
		this.colorizeImage(colorProperty.get());
	}

}
