package svg.gradients;

import javax.xml.bind.annotation.XmlElement;

public class StopXML
{
	@XmlElement
	private double offset;
	
	@XmlElement(name="stop-color")
	private String stop_color;
	
	/**
	 * optional
	 */
	@XmlElement(name="stop-opacity")
	private String stop_opacity;
	
	

}
