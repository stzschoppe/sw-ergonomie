package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Context {
	private ArrayList<Command> commands;
	private final String name;
	private String prompt;
	
	public Context(String name, String prompt){
		this.name = name;
		this.prompt = prompt;
		this.commands = new ArrayList<Command>(); 
	}
	
	public void addCommand(Command command) {
		commands.add(command);
	}
	
	public Collection<Command> getCommands() {
		return Collections.unmodifiableCollection(commands);
	}
	
	public String getName() {
		return name;
	}
	
	public String getPrompt() {
		return prompt;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	public void showCommandWithHelp(String commandString) {
		for (Command command:commands) {
			if (command.getName().equals(commandString)) {
				Shell.getInstance().out(command.getName());
				for (int x=0; x<command.getParameters().size(); x++) {
					Shell.getInstance().out(command.getParameters().get(x).getName());
				}
				Shell.getInstance().outln(""); Shell.getInstance().outln("");
				for (int x=0; x<command.getParameters().size(); x++) {
					Shell.getInstance().outln("    "+command.getParameters().get(x).getName()+"    "+command.getParameters().get(x).getDescription());
				}
			}
		}
		
	}
	
	public void showAllCommands(){
	    for (Command command:commands) {
	    	Shell.getInstance().outln(command.getName());
	    }
	    Shell.getInstance().outln("Geben Sie \"??\" ein um eine Liste der Befehle mit zugehöriger Hilfe anzuzeigen.");
	}
	
	public void showAllCommandsWithHelp(){
	    for (Command command:commands) {
	    	Shell.getInstance().outln(command.getName()+"    "+command.getDescription());
	    }
	}
	
	public void showAllCommandsStartingWith(String prefix){
	    for (Command command:commands) {
	    	if (command.getName().startsWith(prefix)) {
                Shell.getInstance().outln(command.getName());	    		
	    	}
	    }
	}
}
