package shell;

import java.util.ArrayList;

public class Command {
	private String name;
	private CommandHandler handler;
	private ArrayList<Parameter> parameters;
	private String help;
	
	public Command(String name, CommandHandler handler) {
		this.name = name;
		this.handler = handler;
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
	

}
