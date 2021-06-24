package eu.matfx.changeConfig;

import eu.matfx.interfaces.ITabView;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class ChangeGitConfigTab extends Tab implements ITabView
{
	private ChangeGitConfigContent  content;
	
	private Stage primaryStage;
	
	public ChangeGitConfigTab(Stage primaryStage)
	{
		
		this.primaryStage = primaryStage;
		this.setText("Change config path");
		this.setClosable(false);
	}
	

	@Override
	public void showTabView() 
	{
		content = new ChangeGitConfigContent(primaryStage);
		this.setContent(content);
	
		
	}

	@Override
	public void releaseTabView() 
	{
		this.setContent(null);
	
		
	}

}
