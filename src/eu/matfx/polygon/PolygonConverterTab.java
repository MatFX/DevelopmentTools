package eu.matfx.polygon;

import eu.matfx.interfaces.ITabView;
import javafx.scene.control.Tab;

public class PolygonConverterTab extends Tab implements ITabView
{
	private PolygonConverterContent content;
	
	public PolygonConverterTab()
	{
		this.setText("Convert polygon points from SVG");
		this.setClosable(false);
		
	}

	@Override
	public void showTabView() 
	{
		content = new PolygonConverterContent();
		this.setContent(content);
		
		
	}

	@Override
	public void releaseTabView() {
		this.setContent(null);
		
	}

}
