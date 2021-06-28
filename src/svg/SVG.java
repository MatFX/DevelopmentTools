
package svg;
import java.io.File;
import java.io.Serializable;


import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "svg")
@XmlAccessorType(XmlAccessType.FIELD)
public class SVG implements Serializable, IFileName 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1230325074833943486L;

	@XmlElement
	private String viewBox;
	
	/**
	 * 
	 */
	private Definitions defs;
	
	private List<Group> g;

	@Override
	public String getFileName() {
		return "example.svg";
	}

	@Override
	public void setFileName(String fileName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUnterverzeichnisVorhanden() {
		return false;
	}

	@Override
	public String getCompletePath() {
		//Der Pfad führt in den Settingsbereich wobei jedes Profil sein eigenes Verzeichnis hat.
		
		File temp = new File("");
		
		temp = new File(temp.getAbsolutePath() + "/" + getFileName());
		
		
		return temp.getAbsolutePath();
	}
	
	

}
