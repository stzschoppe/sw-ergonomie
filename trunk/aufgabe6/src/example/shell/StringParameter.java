package shell;

/**
 * Klasse zum Speichern von Symbolparametern
 * @author stephan
 *
 */
public class StringParameter extends Parameter{
	/**
	 * Wert des Parameters
	 */
	private String value;
	
	/**
	 * Konstruktor zum erzeugen eines leeren SymbolParameters.
	 * 
	 * @param name
	 *            Der Name des Parameters.
	 * @param description Hilfetext des Parameters

	 */
	public StringParameter(String name, String description) {
		super(name, description);
	}

	
	
	@Override
	public void setValue(String value) {
		this.value = value;
		
	}

	@Override
	public int getValueAsInteger() {
		return Integer.valueOf(value);
	}

	@Override
	public String getValueAsString() {
		return value;
	}

	@Override
	protected Object clone() {
		return new StringParameter(getName(), getDescription());
	}

	@Override
	public String getType() {
		return "string";
	}

}
