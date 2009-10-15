package shell;

import java.util.ArrayList;

public class SymbolParameter extends Parameter {
	/**
	 * Konstruktor zum erzeugen eines leeren SymbolParameters.
	 * 
	 * @param name
	 *            Der Name des Parameters.
	 * @param validSymbols
	 *            Eine <code>ArrayList</code> von Strings der erlaubten Symbole
	 */
	public SymbolParameter(String name, String description,
			ArrayList<String> validSymbols)

	{
		super(name, description);
		this.validSymbols = validSymbols;
	}

	/**
	 * Wert des Parameters.
	 */
	private String value;

	/**
	 * Liste der gültigen Symbole.
	 */
	private ArrayList<String> validSymbols;

	@Override
	public void setValue(String value) {
		if (validSymbols.contains(value)) {
			this.value = value;
		} else {
			throw new IllegalArgumentException("Der angegebene Parameter "
					+ value + " gehört nicht zu den zulässigen Werten: "
					+ validSymbols);
		}

	}

	@Override
	public int getValueAsInteger() {
		throw new UnsupportedOperationException(
				"Der Parameter ist ein Symbol und kann darum nicht als Integer dargestellt werden.");
	}

	@Override
	public String getValueAsString() {
		return value;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new SymbolParameter(getName(), getDescription(), validSymbols);
	}

}
