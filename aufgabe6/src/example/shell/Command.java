package shell;

import java.util.ArrayList;

public class Command {
	private final String name;
	private final CommandHandler handler;
	private final ArrayList<Parameter> parameters;
	private final String description;
	
	public Command(String name, String description, CommandHandler handler) {
		this.name = name;
		this.description = description;
		this.handler = handler;
		this.parameters = new ArrayList<Parameter>();
	}
	
	public String getDescription() {
		return description;
	}	
	
	public String getName() {
		return name;
	}
	
	CommandHandler getHandler() {
		return handler;
	}
	
	public void executeHandler() {
		getHandler().execute(this);
	}
	
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Command command = new Command(name, description, handler);
		for (int x=0; x<parameters.size(); x++) {
			command.addParameter((Parameter)parameters.get(x).clone());
		}
		return command;
	}
	
	

}
