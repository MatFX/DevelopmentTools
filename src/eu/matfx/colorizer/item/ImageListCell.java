package eu.matfx.colorizer.item;


import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

public class ImageListCell extends ListCell<ImagePreview>
{
	protected void updateItem(ImagePreview item, boolean empty)
	{
		super.updateItem(item, empty);
		
        setGraphic(null);
        setText(null);
        if(item!=null)
       	{
        	//TODO ?
        	//ImageView imageView = UIToolBox.scaleImage(item.getLanguage().getImage(), 0.7f, 0.7f);
        	ImageView imageView = new ImageView(item.getImage());
            setGraphic(imageView);
            setText(item.getFileNameToShow());
       	}
    }


}
