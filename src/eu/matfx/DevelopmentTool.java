package eu.matfx;
	
import eu.matfx.changeConfig.ChangeGitConfigTab;
import eu.matfx.interfaces.ITabView;
import eu.matfx.polygon.PolygonConverterTab;
import eu.matfx.roundrectangle.RoundRectangleTab;
import eu.matfx.stop.StopConverterTab;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class DevelopmentTool extends Application 
{
	
	@Override
	public void start(Stage primaryStage) {
		try 
		{
			BorderPane root = new BorderPane();
			
			TabPane tabPane = new TabPane();
			
			
			ChangeGitConfigTab changeConfigTab = new ChangeGitConfigTab(primaryStage);
			
			tabPane.getTabs().addAll(changeConfigTab, new RoundRectangleTab(), new PolygonConverterTab(), new StopConverterTab());
			
			root.setCenter(tabPane);
			
			tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>(){

				@Override
				public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
					
					//release the old tab view
					if(oldValue != null && oldValue instanceof ITabView)
					{
						((ITabView)oldValue).releaseTabView();
					}
					
					//fill the view with the content of the selected tab
					if(newValue != null && newValue instanceof ITabView)
					{
						((ITabView)newValue).showTabView();
					}
				}
				
			});
			changeConfigTab.showTabView();
			
			
			
			
			//root.setCenter(new ChangeGitConfig(primaryStage));
			
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
