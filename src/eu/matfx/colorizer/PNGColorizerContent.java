package eu.matfx.colorizer;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import eu.matfx.colorizer.item.ColorTableCell;
import eu.matfx.colorizer.item.ImageListCell;
import eu.matfx.colorizer.item.ImagePreview;
import eu.matfx.colorizer.item.TableImageRow;
import eu.matfx.helper.ImageLoader;
import eu.matfx.helper.Tools;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * content of the image colorizer tab
 * @author m.goerlich
 *
 */
public class PNGColorizerContent extends BorderPane
{
	
	private ComboBox<ImagePreview> comboBox;
	
	private ImageView imageViewOrginal;
	
	private TableView<TableImageRow> tableView;
	

	public PNGColorizerContent() 
	{
		HBox topBox = new HBox(10);
		topBox.setAlignment(Pos.CENTER_LEFT);
		topBox.setPadding(new Insets(15, 15 , 15, 15));
		
		//Combobox with choice of possible changeable icons	
		List<ImagePreview> tempList = new ArrayList<ImagePreview>();
		//read out the source folder
		
		//create the observablelist
		List<String> imageNameList = ImageLoader.getImageNameListFrom(ImageLoader.ICONS);
		for(String imageName : imageNameList)
		{
			Image image = ImageLoader.getImageFromIconFolder(imageName);
			String filenameToShow = imageName.replace(ImageLoader.SUFFIX_FILE, "");
			tempList.add(new ImagePreview(image, filenameToShow));
		}
	
		ObservableList<ImagePreview> obsList = FXCollections.observableList(tempList);
		comboBox = new ComboBox<ImagePreview>(obsList);
		if(tempList != null && tempList.size() > 0)
			comboBox.getSelectionModel().select(0);
		else
			comboBox.setDisable(true);
		comboBox.setCellFactory(c -> new ImageListCell());
		comboBox.valueProperty().addListener(new ChangeListener<ImagePreview>(){

			@Override
			public void changed(ObservableValue<? extends ImagePreview> observable, ImagePreview oldValue, ImagePreview newValue) 
			{
				if(newValue != null)
				{
					imageViewOrginal.setImage(newValue.getImage());
					
					for(int i = 0; i < tableView.getItems().size(); i++)
					{
						tableView.getItems().get(i).setNewImagePreview(newValue);
						
					}
				
				
				}
				
				
			}

			
		});
		
		imageViewOrginal = new ImageView();
		if(tempList != null && tempList.size() > 0)
		{
			ImagePreview imagePreview = comboBox.getSelectionModel().getSelectedItem();
			imageViewOrginal.setImage(imagePreview.getImage());
		}
	
		topBox.getChildren().addAll(imageViewOrginal, comboBox);
		
		Button add = new Button("add color");
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ImagePreview imagePreview = comboBox.getSelectionModel().getSelectedItem();
				
				TableImageRow tableImageRow = new TableImageRow(imagePreview);
				
				tableView.getItems().add(tableImageRow);
				
			}
			
		});
		
		Button delete = new Button("delete color");
		delete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<TableImageRow> obsList = tableView.getSelectionModel().getSelectedItems();
				tableView.getItems().removeAll(obsList);
			}
			
		});
		
		Button export = new Button("export images");
		export.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//export only the current tableview
				for(int i = 0; i < tableView.getItems().size(); i++)
				{
					//tableView.getItems().get(i).setNewImagePreview(newValue);
					
					TableImageRow tableImageRow = tableView.getItems().get(i);
					
					String newFileName = tableImageRow.getFileNameToShow() + tableImageRow.getSuffix();
					
					ImageLoader.writeImageToExportFolder(tableImageRow.getPreview().getImage(), newFileName);
					
				}
				
				
			}
			
		});

		
		
		topBox.getChildren().addAll(add, delete, export);
		this.setTop(topBox);
		
		
	
		
		VBox vBoxContent = new VBox();
		vBoxContent.setPadding(new Insets(15, 15 , 15, 15));
		
		ScrollPane scrollPane = new ScrollPane();
		
		
		List<TableImageRow> emptyList = new ArrayList<TableImageRow>();
		
		//no content when view is starting
		ObservableList<TableImageRow> obsTableList = FXCollections.observableList(emptyList);
		tableView = new TableView<TableImageRow>(obsTableList);
		//Verhindert die zusätzlichen Spalten die automatisch generiert werden.
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		tableView.setEditable(true);
		//three columns: color, preview and suffix textfield
		TableColumn<TableImageRow, Color> colorColumn = new TableColumn<TableImageRow, Color>("color");
		colorColumn.setCellValueFactory(new PropertyValueFactory<TableImageRow, Color>("newColorValue"));
		colorColumn.setCellFactory(param -> new ColorTableCell<TableImageRow>(colorColumn));
		colorColumn.setOnEditCommit((CellEditEvent<TableImageRow, Color> event) -> 
		{
			  TablePosition<TableImageRow, Color> pos = event.getTablePosition();
			  Color newColorValue = event.getNewValue();
	          int row = pos.getRow();
	          TableImageRow tableImageRow = event.getTableView().getItems().get(row);
	          tableImageRow.setNewColorValue(newColorValue);
	          //TODO nicht schön aber erstmal praktikabel...weiß noch nicht wie ich suffix informiere
	          event.getTableView().refresh();
		});
		
		TableColumn<TableImageRow, ImageView> imagePreviewColumn = new TableColumn<TableImageRow, ImageView>("preview");
		imagePreviewColumn.setCellValueFactory(new PropertyValueFactory<TableImageRow, ImageView>("preview"));
		
		
		TableColumn<TableImageRow, String> suffixColumn = new TableColumn<TableImageRow, String>("suffix");
		suffixColumn.setCellValueFactory(new PropertyValueFactory<TableImageRow, String>("suffix"));
		suffixColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		suffixColumn.setOnEditCommit((CellEditEvent<TableImageRow, String> event) -> 
		{
	            TablePosition<TableImageRow, String> pos = event.getTablePosition();
	            String newSuffixName = event.getNewValue();
	            int row = pos.getRow();
	            TableImageRow tableImageRow = event.getTableView().getItems().get(row);
	            tableImageRow.setSuffix(newSuffixName);
	    });
		
		
		tableView.getColumns().addAll(colorColumn, imagePreviewColumn, suffixColumn);

		scrollPane.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		scrollPane.setFitToHeight(true);
		scrollPane.setFitToWidth(true);
		scrollPane.setContent(tableView);
		vBoxContent.getChildren().add(scrollPane);
		
		
		this.setCenter(vBoxContent);
		
		
		
		
		
		
		
		
		
		
		
	}

}
