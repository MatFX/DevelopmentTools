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

public class ColorizedImage extends ImageView
{
	
	private Image orginalImage;

	private SimpleObjectProperty<Color> colorProperty;
	
	public ColorizedImage(Image imageFromIconFolder, Color initialColor) 
	{
		this.orginalImage = imageFromIconFolder;
		
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

	protected void colorizeImage(Color newValue) {
		//dann koloriere es

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

	public void setNewColor(Color newColorValue) {
		colorProperty.set(newColorValue);
	}
	
	public void setNewOriginalImage(Image newImage)
	{
		this.orginalImage = newImage;
		//colorise image with the current color
		this.colorizeImage(colorProperty.get());
	}

}
