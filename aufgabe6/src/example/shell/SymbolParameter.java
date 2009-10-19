package shell;

import java.util.ArrayList;

/**
 * Klasse zur Speicherung von Symbolparametern. Ein Symbolparameter kann mehrere g�ltige Werte annehmen. 
 * @author stephan
 *
 */
public class SymbolParameter extends Parameter {
	/**
	 * Konstruktor zum erzeugen eines leeren SymbolParameters.
	 * 
	 * @param name
	 *            Der Name des Parameters.
	 * @param description Hilfetext des Parameters
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
	 * Konstruktor zum erzeugen eines leeren SymbolParameters.
	 * 
	 * @param name
	 *            Der Name des Parameters.
	 * @param description
	 *            Hilfetext des Parameters
	 * @param validSymbols
	 *            Eine durch Kommata getrennte Liste Strings der erlaubten Symbole
	 */
	public SymbolParameter(String name, String description,
			String... validSymbols)
	{
		super(name, description);
		this.validSymbols = new ArrayList<String>();
		for (String string : validSymbols) {
			this.validSymbols.add(string);
		}
	}

	/**
	 * Wert des Parameters.
	 */
	private String value;

	/**
	 * Liste der g�ltigen Symbole.
	 */
	private ArrayList<String> validSymbols;

	@Override
	public void setValue(String value) {
		if (validSymbols.contains(value)) {
			this.value = value;
		} else {
			throw new IllegalArgumentException("Der angegebene Parameter "
					+ value + " geh�rt nicht zu den zul�ssigen Werten: "
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
	protected Object clone() {
		return new SymbolParameter(getName(), getDescription(), validSymbols);
	}

	@Override
	public String getType(){
		return "symbol";
	}
	
	@Override
	public String toString(){
		return getName()+"    "+getDescription() + ", " +
		"m�gliche Werte sind: " + validSymbols;
	}

}
