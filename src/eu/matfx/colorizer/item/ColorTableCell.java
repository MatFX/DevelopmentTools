package eu.matfx.colorizer.item;

import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class ColorTableCell<T> extends TableCell<T, Color> 
{    
    private final ColorPicker colorPicker;
    public ColorTableCell(TableColumn<T, Color> column) 
    {
		colorPicker = new ColorPicker();
		colorPicker.setPrefWidth(Double.MAX_VALUE);
		
		this.colorPicker.setOnShowing(event -> 
		{
		    final TableView<T> tableView = getTableView();
		    tableView.getSelectionModel().select(getTableRow().getIndex());
		    tableView.edit(tableView.getSelectionModel().getSelectedIndex(), column);	  
		});

		this.colorPicker.setOnHidden(event ->{
			commitEdit(colorPicker.valueProperty().get());
		});
		setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }
 
    @Override
    protected void updateItem(Color item, boolean empty)
    {
		super.updateItem(item, empty);	
	 
		setText(null);	
		if(empty) 
		{	    
		    setGraphic(null);
		} 
		else 
		{	    
		    this.colorPicker.setValue(item);
		    this.setGraphic(this.colorPicker);
		} 
    }
}