package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SymbolParameter extends Parameter {
	public SymbolParameter(String name, String description, ArrayList<String> validSymbols) {
		super(name, description);
		this.validSymbols = validSymbols;
	}

	private String value;
	private final ArrayList<String> validSymbols;
	
	/**
	 * @return the validSymbols
	 */
	Collection<String> getValidSymbols() {
		return Collections.unmodifiableCollection(validSymbols);
	}

	@Override
	public void setValue(String value) {
		if (validSymbols.contains(value)) {
			this.value = value;
		} else {
			throw new IllegalArgumentException();
		}
		
	}

	@Override
	public int getValueAsInteger() {
		throw new UnsupportedOperationException();
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
