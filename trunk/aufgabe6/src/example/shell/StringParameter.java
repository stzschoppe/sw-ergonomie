package shell;

public class StringParameter extends Parameter{

	public StringParameter(String name) {
		super(name);
	}

	private String value;
	
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

}