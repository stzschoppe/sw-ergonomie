package shell;

public abstract class Parameter {
	private String name;
	public abstract void setValue(String value);
	public abstract int getValueAsInteger();
	public abstract String getValueAsString();
	
	public Parameter(String name) {
		this.setName(name);
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
