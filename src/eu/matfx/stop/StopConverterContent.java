package eu.matfx.stop;


import java.io.File;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import eu.matfx.stop.interfaces.IStopValue;
import eu.matfx.stop.xml.version1.StopList_v1;
import eu.matfx.stop.xml.version2.StopList_v2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;


/**
 * convert stop values from a svg gradient to javafx stop array
 * @author m.goerlich
 *
 */
public class StopConverterContent extends BorderPane
{
	private TextArea importArea, exportArea;
	
	public StopConverterContent() 
	{
		
		VBox vBox = new VBox(3);
		vBox.setPadding(new Insets(5,5,5,5));
		
		Button convertButton = new Button("Convert");
		convertButton.setTooltip(new Tooltip("Convert the stop values from SVG"));
		convertButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) 
			{
				exportArea.clear();
				
				//init from string builder 
				StringBuilder ausgabe = new StringBuilder("Stop[] stopArray = new Stop[]{\n");
				
				//import the string from the text area and convert the values
				StringBuilder sb = new StringBuilder("");
				sb.append("<stopList>");
				String valueFromArea = importArea.getText();
				sb.append(valueFromArea);
				sb.append("</stopList>");
				
				Object whichList = null;
				List<? extends IStopValue> listToConvert = null;
				//compare which version for the converting is to use
				if(valueFromArea.contains("style="))
				{
					whichList = (Object) readObject(StopList_v1.class, sb.toString());
					if(whichList != null)
						listToConvert = ((StopList_v1)whichList).getIStopValueList();
				}
				else
				{
					whichList = (Object) readObject(StopList_v2.class, sb.toString());
					if(whichList != null)
						listToConvert = ((StopList_v2)whichList).getIStopValueList();
				}
				
				System.out.println("which " +whichList.toString());
				if(listToConvert != null)
				{
					for(int i = 0; i < listToConvert.size(); i++)
					{
						String offset = listToConvert.get(i).getOffset();
						String finalColorValue = listToConvert.get(i).getFinalColorValue();
						
						//System.out.println("offset " + offset);
						//System.out.println("finalColorValue " + finalColorValue);
						
						ausgabe.append("\tnew Stop("+offset+", Color.web(\""+finalColorValue+"\"))");
						
						//more entries available?
						if((i + 1) < listToConvert.size())
						{
							ausgabe.append(",");
						}
						ausgabe.append("\n");
					}
					ausgabe.append("};");
					exportArea.setText(ausgabe.toString());
				
				}
			}
		});
		
		//clear the content area
		Button clear = new Button("Clear");
		clear.setOnAction(new EventHandler<ActionEvent>()
        {

			@Override
			public void handle(ActionEvent event) 
			{
				importArea.clear();
				exportArea.clear();
			}
        	
        });
	    HBox initLinie = new HBox(5);
	    initLinie.setPadding(new Insets(5,5,5,5));
	    initLinie.getChildren().addAll(convertButton, clear);
		
		vBox.getChildren().add(initLinie);
		
		
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(5);
		RowConstraints row = new RowConstraints();
	    row.setVgrow(Priority.ALWAYS);
	    gridPane.getRowConstraints().add(0, row);
	    gridPane.getRowConstraints().add(1, row);
		//zwei textfelder für eingabe ausgabe
		
		//zwei button; Convert und lösche Textinhalt
	    Label labelImport = new Label("Content SVG:");
        Label labelExport = new Label("Converted data:");
		
        gridPane.add(labelImport, 0, 0);
        gridPane.add(labelExport, 1, 0);
        
        
        importArea = new TextArea();
        exportArea = new TextArea();
        
        gridPane.add(importArea, 0, 1);
        gridPane.add(exportArea, 1, 1);
		
        vBox.getChildren().add(gridPane);
        this.setCenter(vBox);
    }
	
	/**
	 * Auslesen des Strings und Objekt befüllen
	 * @return
	 */
	public static Object readObject(Class<?> klasse, String value)
	{
		try 
		{
			JAXBContext jc = JAXBContext.newInstance(klasse);
			Unmarshaller u = jc.createUnmarshaller();
			StringReader reader = new StringReader(value);
			Object returnValue = (Object)u.unmarshal(reader);
			return returnValue;
		}
		catch(UnmarshalException e)
		{
			e.printStackTrace();
		}
		catch (JAXBException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean writeObjectToFile(StopList_v1 stopList)
	{
		try 
    	{
    		JAXBContext jc = JAXBContext.newInstance(stopList.getClass());
    		Marshaller m = jc.createMarshaller();
    		//Formatierung, damit es übersichtlich ist, falls mal jemand reinschaut
    		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
    		m.marshal(stopList, new File("test.xml"));
    		return true;
    		
    	} 
    	catch (JAXBException e) 
    	{
    		e.printStackTrace();
    	}
    	catch(NullPointerException e)
    	{
    		e.printStackTrace();
    	}
		return false;
	}

}
