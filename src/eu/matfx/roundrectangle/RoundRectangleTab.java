package eu.matfx.roundrectangle;

import eu.matfx.interfaces.ITabView;
import javafx.scene.control.Tab;

public class RoundRectangleTab extends Tab implements ITabView
{
	
	private RoundRectangleContent content;
	
	public RoundRectangleTab()
	{
		this.setText("Create round rectangle");
		this.setClosable(false);
	}
	
	@Override
	public void showTabView() {
		
		content = new RoundRectangleContent();
		this.setContent(content);
	}

	@Override
	public void releaseTabView() {
		this.setContent(null);
	}

}
