package eu.matfx.stop.xml.version1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import eu.matfx.stop.interfaces.IStopValue;




@XmlRootElement(name = "stopList")
@XmlAccessorType(XmlAccessType.FIELD)
public class StopList_v1 implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1924013961473852428L;
	
	private List<StopValue_v1> stop = new ArrayList<StopValue_v1>();
	
	public void setStopList(List<StopValue_v1> stopValueList)
	{
		this.stop = stopValueList;
	}
	
	public List<? extends IStopValue> getIStopValueList()
	{
		return stop;
	}

}
