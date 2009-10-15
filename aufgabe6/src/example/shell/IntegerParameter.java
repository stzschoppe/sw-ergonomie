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
					+ value + " ist kein g�ltiger Integerwert.");
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
	protected Object clone() throws CloneNotSupportedException {
		return new IntegerParameter(getName(), getDescription());
	}
}