package eu.matfx.polygon;

import javafx.beans.property.SimpleDoubleProperty;

/**
 * Data container for the values ​​are shown in the table 
 * Container for the table values.
 * @author m.goerlich
 *
 */
public class DoubleContainer 
{
	/**
	 * 
	 */
	private SimpleDoubleProperty xValue = new SimpleDoubleProperty();
	
	private SimpleDoubleProperty yValue = new SimpleDoubleProperty();

	public DoubleContainer()
	{
		
	}
	
	public DoubleContainer(double newXValue, double newYValue)
	{
		xValue.set(newXValue);
		yValue.set(newYValue);
	}

	//getxValue wird nicht akzeptiert unbedingt groß schreiben
	//TestValue testValue gleiches Phänomän
	public Double getXValue() {
		return xValue.get();
	}

	public void setXValue(double xDoubleValue) {
		this.xValue.set(xDoubleValue);
	}

	
	public Double getYValue() {
		return yValue.get();
	}

	public void setYValue(double yDoubleValue) {
		this.yValue.set(yDoubleValue);
	}
	
	public SimpleDoubleProperty getDoubleXProperty()
	{
		return xValue;
	}
	
	public SimpleDoubleProperty getDoubleYProperty()
	{
		return yValue;
	}
	
	
}
