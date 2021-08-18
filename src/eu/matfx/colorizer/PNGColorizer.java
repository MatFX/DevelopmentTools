package eu.matfx.colorizer;


import eu.matfx.interfaces.ITabView;
import javafx.scene.control.Tab;


public class PNGColorizer extends Tab implements ITabView
{
	private PNGColorizerContent  content;
	
	public PNGColorizer()
	{
		this.setText("Change color");
		this.setClosable(false);
	}
	

	@Override
	public void showTabView() {
		content = new PNGColorizerContent();
		this.setContent(content);
		
	}

	@Override
	public void releaseTabView() {
		this.setContent(null);
		
	}

}
