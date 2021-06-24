package eu.matfx.polygon;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import eu.matfx.changeConfig.ConfigResourceLocation;
import eu.matfx.helper.GenericPair;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;



/**
 * Convert a polygon points from an SVG file to an Java Double array. This converted double is 
 * TODO
 * @author m.goerlich
 *
 */
public class PolygonConverterContent extends BorderPane 
{
	private TextField totalWidthFromSVG, totalHeightFromSVG;
   
	private TableView<DoubleContainer> svgValueTable;
	
	private TableView<DoubleContainer> calculatedResultTable;
	
	/**
	 * Content map from the two tables
	 */
	private TreeMap<Integer, GenericPair<DoubleContainer, DoubleContainer>> tableMap = new TreeMap<Integer, GenericPair<DoubleContainer, DoubleContainer>>();

	/**
	 * Text areas to import the svg snippet and export the double array
	 */
	private TextArea importArea, exportArea;
	
    public PolygonConverterContent()
    {
       
        
        VBox vBox = new VBox(3);
        vBox.setPadding(new Insets(5,5,5,5));
        HBox initLinie = new HBox(5);
        initLinie.setPadding(new Insets(5,5,5,5));
      
        totalWidthFromSVG = new TextField(ConfigResourceLocation.getTotal_Width());
        totalHeightFromSVG = new TextField(ConfigResourceLocation.getTotal_Height());
        
        
        Button importButton = new Button("Import/resize");
        importButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) 
			{
				double calcW = Double.parseDouble(totalWidthFromSVG.getText());
		    	double calcH = Double.parseDouble(totalHeightFromSVG.getText());
		    	clearView();
				
				//get the values from the import area
				String valueFromArea = importArea.getText();
				
				int nextLine = 0;
				//spaces and comma are the splitting values
				
				//pre check; I think in any Illustrator version the export was changed and the commas was eliminated.
				StringBuilder convertToCommaSeperated = new StringBuilder();
				if(!valueFromArea.contains(","))
				{
					String[] splitedValues = valueFromArea.split(" ");
					List<String> arrayList = (List<String>) Arrays.asList(splitedValues);
					Iterator<String> iterator = arrayList.iterator();
					while(iterator.hasNext())
					{
						convertToCommaSeperated.append(iterator.next());
						convertToCommaSeperated.append(",");
						convertToCommaSeperated.append(iterator.next());
						convertToCommaSeperated.append(" ");
					}
				}
				System.out.println("valueFromArea before " +valueFromArea);
				valueFromArea = convertToCommaSeperated.toString();
				System.out.println("valueFromArea after " +valueFromArea);
				
				
				//Now, delete the commas from the string
				String[] splitedValues = valueFromArea.split(" ");
				for(int i = 0; i < splitedValues.length; i++)
				{
					String tempValue = splitedValues[i];
					GenericPair<DoubleContainer, DoubleContainer> lineContainer = null;
					if(tempValue.contains(","))
					{
						String[] xyValues = tempValue.split(",");
						
						try
						{
							//parse the values 
							Double xValue = Double.parseDouble(xyValues[0]);
							Double yValue = Double.parseDouble(xyValues[1]);
							lineContainer = new GenericPair<DoubleContainer, DoubleContainer>();
							DoubleContainer leftValue = new DoubleContainer(xValue, yValue);
							lineContainer.setLeft(leftValue);
							//Calculate the relative values ​​depending on the variables w and h
							double calculatedXFactor = 100D / calcW * leftValue.getXValue() / 100D;
							double calculatedYFactor = 100D / calcH * leftValue.getYValue() / 100D;
							
							lineContainer.setRight(new DoubleContainer(calculatedXFactor, calculatedYFactor));
							
							
							
						}
						catch(Exception e)
						{
							//wenn es scheppern sollte? alles verwerfen?
							e.printStackTrace();
						}
					}
					
					if(lineContainer != null)
					{
						tableMap.put(nextLine, lineContainer);
						nextLine++;
					}
					
					
				}
				
				if(tableMap.size() > 0)
				{
					//TODO make it sense to change values in the tables?
					ObservableList<DoubleContainer> leftSideList = FXCollections.<DoubleContainer>observableArrayList();
					ObservableList<DoubleContainer> rightSideList = FXCollections.<DoubleContainer>observableArrayList();
					for(Entry<Integer, GenericPair<DoubleContainer, DoubleContainer>> entry : tableMap.entrySet())
					{
						entry.getValue().getLeft().getDoubleXProperty().addListener(new ChangeListener<Number>()
						{

							@Override
							public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) 
							{
								System.out.println("new Value " + arg2.toString());
								//changeXValue(arg2);
							}
							
						});
						leftSideList.add(entry.getValue().getLeft());
						rightSideList.add(entry.getValue().getRight());
						
						
					}
					//svgWerteTabelle.getColumns().get(0).setVisible(false);
					//svgWerteTabelle.getColumns().get(0).setVisible(true);
					
					svgValueTable.getItems().addAll(leftSideList);
					calculatedResultTable.getItems().addAll(rightSideList);
					
					exportCalculatedValues(rightSideList);
					
				}
			}

        	
        });
        
       //clear the content of the view
        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>()
        {

			@Override
			public void handle(ActionEvent event) 
			{
				clearView();
				importArea.clear();
				exportArea.clear();
			}
        	
        });
        
        initLinie.getChildren().addAll(new Label("Total width: "), totalWidthFromSVG, new Label("Total height: "), totalHeightFromSVG, importButton, clear);
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
    
		
		
		Label labelTableImport = new Label("Table SVG:");
        Label labelTableExport = new Label("Table converted points:");
        
        gridPane.add(labelTableImport, 0, 0);
        gridPane.add(labelTableExport, 1, 0);
		
		ScrollPane scrollLeft = new ScrollPane();
		scrollLeft.setFitToWidth(true);
	    ScrollPane scrollRight = new ScrollPane();
	    scrollRight.setFitToWidth(true);
        svgValueTable = new TableView<DoubleContainer>();
        //set editable on svg table
        svgValueTable.setEditable(true);
        calculatedResultTable = new TableView<DoubleContainer>();
      
        svgValueTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
       
        //zwingend erfoderlich dass jede Tabelle seine eigene ColumnDefintion erhält
        TableColumn<DoubleContainer, Double> xSVGCol = new TableColumn<DoubleContainer, Double>("x");
        xSVGCol.setMinWidth(200);
        xSVGCol.setCellValueFactory(new PropertyValueFactory<DoubleContainer, Double>("xValue"));
        
		TableColumn<DoubleContainer, Double> ySVGCol = new TableColumn<DoubleContainer, Double>("y");
		ySVGCol.setMinWidth(200);
		ySVGCol.setCellValueFactory(new PropertyValueFactory<DoubleContainer, Double>("yValue"));  
		

		 
        TableColumn<DoubleContainer, Double> xCol = new TableColumn<DoubleContainer, Double>("x");
        xCol.setMinWidth(200);
        xCol.setCellValueFactory(new PropertyValueFactory<DoubleContainer, Double>("xValue"));
		
		TableColumn<DoubleContainer, Double> yCol = new TableColumn<DoubleContainer, Double>("y");
		yCol.setMinWidth(200);
		yCol.setCellValueFactory(new PropertyValueFactory<DoubleContainer, Double>("yValue"));  
		
		
		
		
		svgValueTable.getColumns().addAll(xSVGCol, ySVGCol);
		
		
		
		calculatedResultTable.getColumns().addAll(xCol, yCol);
        
        scrollLeft.setContent(svgValueTable);
        scrollRight.setContent(calculatedResultTable);
		
		gridPane.add(scrollLeft, 0, 1);
        gridPane.add(scrollRight, 1, 1);
        
        
        Label labelImport = new Label("Import SVG:");
        Label labelExport = new Label("Export Double[]:");
        
        gridPane.add(labelImport, 0, 2);
        gridPane.add(labelExport, 1, 2);
        
        
        importArea = new TextArea();
        exportArea = new TextArea();
        
        gridPane.add(importArea, 0, 3);
        gridPane.add(exportArea, 1, 3);
        
        vBox.getChildren().add(gridPane);
        this.setCenter(vBox);
     
    }
    

    /**
     * printing for the export area
     * @param rightSideList
     */
	private void exportCalculatedValues(ObservableList<DoubleContainer> rightSideList) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("new Double[]\n");
		sb.append("{\n");
		for(int i = 0; i < rightSideList.size(); i++)
		{
			DoubleContainer value = rightSideList.get(i);
			sb.append("\t w * " + value.getXValue() + ", h * "  + value.getYValue());
			//last value?
			if((i + 1) < rightSideList.size())
			{
				//okay min one more value
				sb.append(",\n");
			}
			else
			{
				//last value noch comma
				sb.append("\n");
			}
		}
		sb.append("}\n");
		exportArea.setText(sb.toString());
	}
    
    
    public void clearView()
    {
    	tableMap.clear();
		calculatedResultTable.getItems().clear();
		svgValueTable.getItems().clear();
    }


	public void saveSize() 
	{
		ConfigResourceLocation.setTotal_Width(totalWidthFromSVG.getText());
		ConfigResourceLocation.setTotal_Height(totalHeightFromSVG.getText());
	}

    
  
}


