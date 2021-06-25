package example;


import java.util.HashMap;

import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;



public class ExampleUI extends Region
{
	/**
	 * rounded rectangle for the base area
	 */
	private Rectangle base_background, inlay, inlay_shine;
	
	private Polygon arrow_up;
	
	/**
	 * starting width and height values from the base component
	 */
	private double w = 170, h = 80;
	
	/**
	 * This map stored the created stop arrays from the method {@link #initGraphics()} .
	 * <br>With every call of the method {@link #resize()} the gradient will be new calculated and 
	 * <br>the suitable stop array will be set.
	 */
	private HashMap<StopIndizes, Stop[]> stopMap = new HashMap<StopIndizes, Stop[]>();
	
	public enum StopIndizes
	{
		INLAY_SHINE,
		
		
		
		;
	}
	
	public ExampleUI()
	{
		super();
		this.initGraphics();
		this.registerListener();
	}

	private void registerListener() 
	{
		widthProperty().addListener(observable -> resize());
		heightProperty().addListener(observable -> resize());
	}

	private void initGraphics() 
	{
		base_background = new Rectangle();
		base_background.setFill(Color.web("#353533"));
		
		inlay = new Rectangle();
		inlay.setFill(Color.web("#5a5a5a"));
		
		//sample polygon arrow
		arrow_up = new Polygon();
		arrow_up.setFill(Color.web("#FFFFFF"));
		
		inlay_shine = new Rectangle();
		
		//build the stop array and store it in map
		Stop[] stopArray = new Stop[]{
				new Stop(0, Color.web("#ffffff00")),
				new Stop(0.01156, Color.web("#dcdcdc2C")),
				new Stop(0.07122, Color.web("#353533")),
				new Stop(0.30517, Color.web("#4c4c4bE2")),
				new Stop(0.62175, Color.web("#8e8e8d8F")),
				new Stop(0.98425, Color.web("#fafafa07")),
				new Stop(1, Color.web("#ffffff00"))
			};
		stopMap.put(StopIndizes.INLAY_SHINE, stopArray);
		
		
		
		//add the component in the sequence of the layer
		this.getChildren().addAll(base_background, inlay, arrow_up, inlay_shine);
		
	}
	
	private void resize() 
	{
		w = getWidth();
		h = getHeight();
		
		base_background.setWidth(w);
		base_background.setHeight(h);
		//rounded corner; Starting with 8px
		base_background.setArcWidth(w * 0.047058823529411764);
		base_background.setArcHeight(w * 0.047058823529411764);
		
		//inlay component
		inlay.setX(w * 0.023529411764705882);
		inlay.setY(h * 0.05);
		inlay.setWidth(w * 0.9529411764705883);
		inlay.setHeight(h * 0.9);
		inlay.setArcWidth(w * 0.023529411764705882);
		inlay.setArcHeight(w * 0.023529411764705882);
		
		//first clear the old points
		arrow_up.getPoints().clear();
		//then add the new calculated points
		arrow_up.getPoints().addAll(new Double[]
		{
			 w * 0.3480882352941176, h * 0.4852,
			 w * 0.19967058823529413, h * 0.16981250000000003,
			 w * 0.051252941176470584, h * 0.4852,
			 w * 0.14682941176470587, h * 0.4852,
			 w * 0.14682941176470587, h * 0.7875,
			 w * 0.2525117647058823, h * 0.7875,
			 w * 0.2525117647058823, h * 0.4852,
			 w * 0.3480882352941176, h * 0.4852
		});
		
		
		inlay_shine.setX(w * 0.023529411764705882);
		inlay_shine.setY(h * 0.05);
		inlay_shine.setWidth(w * 0.9529411764705883);
		inlay_shine.setHeight(h * 0.9);
		inlay_shine.setArcWidth(w * 0.023529411764705882);
		inlay_shine.setArcHeight(w * 0.023529411764705882);
		
		//build the linear gradient for the inlay_shine.
		//(in the example.svg the definition called "Unbenannter_Verlauf_27")
		LinearGradient lg = new LinearGradient(getWidth()*0.5, 
				getHeight() * 1.0066928750000002, 
				getWidth()  * 0.5,
				getHeight() * 0.0834645, 
				false, CycleMethod.NO_CYCLE, stopMap.get(StopIndizes.INLAY_SHINE));
		
		inlay_shine.setFill(lg);
		
		
		
		
		
	}
	

}
