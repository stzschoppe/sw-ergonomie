package shell;

public abstract class Parameter {
	private final String name;
	private final String description;
	
	public abstract void setValue(String value);
	public abstract int getValueAsInteger();
	public abstract String getValueAsString();
	
	public Parameter(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}	
	
	@Override
	abstract protected Object clone() throws CloneNotSupportedException;
}
