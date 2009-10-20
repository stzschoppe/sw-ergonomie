package shell;

/**
 * Klasse zur Speicherung von Integer-Parametern.
 * @author stephan
 *
 */
public class IntegerParameter extends Parameter {

	/**
	 * Wert des Parameters.
	 */
	private int value;

	/**
	 * Konstruktor zum erzeugen eines leeren IntegerParameters.
	 * @param name Der Name des Parameters.
	 * @param description Hilfetext zum Parameter
	 */
	public IntegerParameter(String name, String description) {
		super(name, description);
	}

	@Override
	public void setValue(String value) {
		try {
			this.value = Integer.valueOf(value);
		} catch (Exception e) {
			throw new IllegalArgumentException("Der angegebene Parameter "
					+ value + " ist kein gültiger Integerwert.");
		}
	}

	@Override
	public int getValueAsInteger() {
		return value;
	}

	@Override
	public String getValueAsString() {
		return String.valueOf(value);
	}

	@Override
	protected Object clone() {
		return new IntegerParameter(getName(), getDescription());
	}

	@Override
	public String getType() {
		return "integer";
	}
}