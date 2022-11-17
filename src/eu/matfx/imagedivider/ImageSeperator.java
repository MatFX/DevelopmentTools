package eu.matfx.imagedivider;

import eu.matfx.interfaces.ITabView;
import javafx.scene.control.Tab;

public class ImageSeperator extends Tab implements ITabView
{
	private ImageSeperatorContent content;

	public ImageSeperator()
	{
		this.setText("Image seperator");
		this.setClosable(false);
	}
	
	@Override
	public void showTabView() 
	{
		content = new ImageSeperatorContent();
		this.setContent(content);
	
		
	}
	@Override
	public void releaseTabView() 
	{
		if(content != null)
			content.saveConfig();
		this.setContent(null);
		
	}
}
