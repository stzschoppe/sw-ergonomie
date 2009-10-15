package shell;

public class IntegerParameter extends Parameter {

	private int value;
	
	public IntegerParameter(String name, String description) {
		super(name, description);
	}

	@Override
	public void setValue(String value) {
		try {
			this.value = Integer.valueOf(value);
		} catch (Exception e) {
			throw new IllegalArgumentException();
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
