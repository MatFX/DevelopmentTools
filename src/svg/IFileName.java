package svg;

/**
 * Ein Interface dass in jedem zu speichernden "XML-Objekt" zu implementieren ist.
 * @author M.Goerlich
 *
 */
public interface IFileName 
{
	/**
	 * Hier wird immer der reine FileName für das entsprechende Objekt ermittelt.
	 * @return
	 */
	public String getFileName();
	
	/**
	 * Hier kann der Filename gesetzt werden. Im Regelfall geschieht dieses im Konstruktur und 
	 * <br>nur in Ausnahmefaellen ist diese Methode zu verwenden.
	 * @param fileName
	 * @return
	 */
	public void setFileName(String fileName);
	
	/**
	 * Ist die Datei in einem Unterverzeichnis von dem Standardpfad zu finden?
	 * @return
	 */
	public boolean isUnterverzeichnisVorhanden();

	/**
	 * Der Komplette Pfad ist der bis zur Datei (inkl.).
	 * @return
	 */
	public String getCompletePath();
	
}
