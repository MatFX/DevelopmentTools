package svg.gradients;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public abstract class AGradient 
{
	@XmlElement
	private String id;
	
	@XmlElement(name="data-name")
	private String data_name;
	
	private List<StopXML> stopList;
	
	
	

}
