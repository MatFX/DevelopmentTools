package example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ExampleStart extends Application {

	private  ExampleUI exampleUI = new ExampleUI();
	
	
	public static void main(String[] args) {
        Application.launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		
		 BorderPane pane = new BorderPane();
		 pane.setPadding(new Insets(5,5,5,5));
		 pane.setStyle("-fx-background-color: #444444");
		 
		 pane.setPadding(new Insets(10, 10, 10, 10));
		 
		 HBox hBox = new HBox();
		 hBox.setPadding(new Insets(15,15,15,15));
		 hBox.getChildren().add(exampleUI);
		 
		 pane.setCenter(exampleUI);
		 
		 
		 Scene scene = new Scene(pane);
		    
		 primaryStage.setTitle("Example SVG/JavaFx");
		 primaryStage.setScene(scene);
		 primaryStage.setWidth(500);
		 primaryStage.setHeight(350);
		 primaryStage.show();
		
	}
	
	
	

}
