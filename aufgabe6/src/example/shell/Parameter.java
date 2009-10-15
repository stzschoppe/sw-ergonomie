package shell;

/**
 * Abstrakte Klasse zur Speicherung von Parametern.
 * @author stephan
 *
 */
public abstract class Parameter {
	/**
	 * Name des Parameters.
	 */
	private final String name;
	/**
	 * Beschreibung des Parameters.
	 */
	private final String description;
	
	public abstract String getType();
	
	/**
	 * Setzt den Wert des Parameters. Die Methode validiert voher den
	 * übergebenen Wert. Passt dieser nicht zum Parametertyp, 
	 * wirft die Methode eine <code>IllegalArgumentException</code>.
	 * @param value Der zu setzende Wert.
	 */
	public abstract void setValue(String value);
	
	/**
	 * Gibt den Wert des Parameters als Integer zurück. Wenn aufgrund
	 * von inkompatiblen Typen die Umwandlung keinen Sinn hat, wirft
	 * die Methode eine <code>UnsupportedOperationException</code>.
	 * @return Wert des Parameters als <code>int</code>.
	 */
	public abstract int getValueAsInteger();
	
	/**
	 * Gibt den Wert des Parameters als String zurück.
	 * @return Wert des Parameters als <code>String</code>
	 */
	public abstract String getValueAsString();
	
	/**
	 * Konstruktor zum erzeugen eines leeren Parameters.
	 * @param name Der Name des Parameters.
	 * @param description Beschreibung des Parameters
	 */
	public Parameter(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Getter des Namens.
	 * @return Der Name des Parameters.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter für die Beschreibung des Parameters.
	 * @return Beschreibung des Parameters.
	 */
	public String getDescription() {
		return description;
	}

	@Override
	abstract protected Object clone();
	
	@Override
	public String toString() {
		return getName()+"    "+getDescription()+" ("+getType()+")";
	}
	
}
