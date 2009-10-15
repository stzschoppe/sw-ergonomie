package shell;

import java.util.ArrayList;

public class SymbolParameter extends Parameter {
	public SymbolParameter(String name, ArrayList<String> validSymbols) {
		super(name);
		this.validSymbols = validSymbols;
	}

	private String value;
	private ArrayList<String> validSymbols;
	
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

}
