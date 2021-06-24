package eu.matfx.stop;

import eu.matfx.interfaces.ITabView;
import javafx.scene.control.Tab;


public class StopConverterTab extends Tab implements ITabView
{
	private StopConverterContent stopConverterContent;
	
	public StopConverterTab()
	{
		this.setText("Convert stop points from SVG");
		this.setClosable(false);
	}

	@Override
	public void showTabView() 
	{
		stopConverterContent = new StopConverterContent();
		this.setContent(stopConverterContent);
	}

	@Override
	public void releaseTabView() {
		this.setContent(null);
	}

}
