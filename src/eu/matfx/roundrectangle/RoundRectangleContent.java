package eu.matfx.roundrectangle;

import eu.matfx.changeConfig.ConfigResourceLocation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;


/**
 * convert x,y,w,h and arcW in a javafx rectangle snippet
 * @author m.goerlich
 *
 */
public class RoundRectangleContent extends BorderPane
{
	/**
	 * width and heigt from the greatest base component of the svg file
	 */
	private TextField totalWidthFromSVG, totalHeightFromSVG;
	
	/**
	 * insert fields 
	 */
	private TextField x, y, w, h, arcW;//, arcH;
	
	/**
	 * show the result
	 */
	private TextArea exportArea;

	
	public RoundRectangleContent()
	{
		
		
		VBox vBox = new VBox(3);
        vBox.setPadding(new Insets(5,5,5,5));
        HBox initLinie = new HBox(5);
        initLinie.setPadding(new Insets(5,5,5,5));
        
        totalWidthFromSVG = new TextField(ConfigResourceLocation.getTotal_Width());
        totalHeightFromSVG = new TextField(ConfigResourceLocation.getTotal_Height());
        
        Button convertButton = new Button("Convert values");
        convertButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) 
			{
				try
				{
					//parse the values
					double xVal = Double.parseDouble(x.getText());
					double yVal = Double.parseDouble(y.getText());
					double wVal = Double.parseDouble(w.getText());
					double hVal = Double.parseDouble(h.getText());
					double arcWVal = Double.parseDouble(arcW.getText());
					//For my rectangles I dont use the arcHVal
					//double arcHVal = Double.parseDouble(arcH.getText());
					
					
					double oneHundredW = Double.parseDouble(totalWidthFromSVG.getText());
					double oneHundredH = Double.parseDouble(totalHeightFromSVG.getText());
					
					
					//relatives values to the total w and h
					
					double xPercent = 100D / oneHundredW * xVal / 100D;
					double yPercent = 100D / oneHundredH * yVal / 100D;
					double wPercent = 100D / oneHundredW * wVal/ 100D; 
					double hPercent = 100D / oneHundredH * hVal/ 100D;
					
					double arcWPercent = 100D / oneHundredW * arcWVal / 100D;
					//print the result in the text area
					StringBuilder sb = new StringBuilder("rectangle = new Rectangle();\n");
					sb.append("rectangle.setX(w * "  + xPercent+");\n");
					sb.append("rectangle.setY(h * "  + yPercent+");\n");
					sb.append("rectangle.setWidth(w * "  + wPercent+");\n");
					sb.append("rectangle.setHeight(h * "  + hPercent+");\n");
					sb.append("rectangle.setArcWidth(w * "  + arcWPercent+");\n");
					sb.append("rectangle.setArcHeight(w * "  + arcWPercent+");\n");
					
					exportArea.setText(sb.toString());
					
				}
				catch(Exception e)
				{
				}
				
				
			}});
        
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) 
			{
				x.clear();
				y.clear();
				w.clear();
				h.clear();
				arcW.clear();
				//arcH.clear();
				exportArea.clear();
			}
        });
		
		
		initLinie.getChildren().addAll(new Label("Total width: "), totalWidthFromSVG, new Label("Total height: "), totalHeightFromSVG, convertButton, clear);
	    vBox.getChildren().add(initLinie);
		
	   
	    GridPane gridPane = new GridPane();
    	gridPane.setHgap(5);
		gridPane.setVgap(5);
        RowConstraints row = new RowConstraints();
        row.setVgrow(Priority.ALWAYS);
		gridPane.getRowConstraints().add(0, row);
		gridPane.getRowConstraints().add(1, row);
		gridPane.getRowConstraints().add(2, row);
		gridPane.getRowConstraints().add(3, row);	
    
		
		HBox boxX = new HBox(5);
		boxX.setPadding(new Insets(5,5,5,5));
		x = new TextField();
		
		boxX.getChildren().addAll(new Label("     x:"), x);
		
		
		y = new TextField();
		HBox boxY = new HBox(5);
		boxY.setPadding(new Insets(5,5,5,5));
		boxY.getChildren().addAll(new Label("     y:"), y);
		
        gridPane.add(boxX, 0, 0);
        gridPane.add(boxY, 1, 0);
		
    	HBox boxW = new HBox(5);
		boxW.setPadding(new Insets(5,5,5,5));
		w = new TextField();
		boxW.getChildren().addAll(new Label("     w:"), w);
		
		HBox boxH = new HBox(5);
		boxH.setPadding(new Insets(5,5,5,5));
		h = new TextField();
		boxH.getChildren().addAll(new Label("     h:"), h);
        
		gridPane.add(boxW, 0, 1);
		gridPane.add(boxH, 1, 1);
	    
		
		HBox boxArcW = new HBox(5);
		boxArcW.setPadding(new Insets(5,5,5,5));
		arcW = new TextField();
		boxArcW.getChildren().addAll(new Label("arcW:"), arcW);
		
		
		HBox boxArcH = new HBox(5);
		boxArcH.setPadding(new Insets(5,5,5,5));
		//arcH = new TextField();
		//boxArcH.getChildren().addAll(new Label("arcH:"), arcH);
		
		gridPane.add(boxArcW, 0, 2);
		//gridPane.add(boxArcH, 1, 2);
		
		
		exportArea = new TextArea();
		
	    
	    vBox.getChildren().addAll(gridPane, exportArea);
	    this.setCenter(vBox);
	
	    
   
		
		
		
	}


	
	public void saveSize() 
	{
		ConfigResourceLocation.setTotal_Width(totalWidthFromSVG.getText());
		ConfigResourceLocation.setTotal_Height(totalHeightFromSVG.getText());
	}

}
