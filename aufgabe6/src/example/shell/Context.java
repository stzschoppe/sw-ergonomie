package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public class Context {
	private ArrayList<Command> commands;
	private final String name;
	private String description;
	private String prompt;
	
	public Context(String name, String description, String prompt){
		this.name = name;
		this.description = description;
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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void showCommandWithHelp(String commandString) {
		for (Command command:commands) {
			if (command.getName().equals(commandString)) {
				Shell.getInstance().out(command.toString());
			}
		}
		
	}
	
	public void showAllCommands(){
		Shell.getInstance().outln(getDescription());
		Shell.getInstance().outln("Sie können die folgenden Befehle eingeben:");
		HashSet<String> commandsSet = new HashSet<String>();
	    for (Command command:commands) {
            commandsSet.add(command.getName());	    		
	    }
	    for (String commandString : commandsSet) {
	    	Shell.getInstance().outln("    "+commandString);
	    }
	    Shell.getInstance().outln("Geben Sie \"??\" ein um eine Liste der Befehle mit zugehöriger Hilfe anzuzeigen.");
	}
	
	public void showAllCommandsWithHelp(){
		Shell.getInstance().outln(getDescription());
		Shell.getInstance().outln("Sie können die folgenden Befehle eingeben:");		
	    for (Command command:commands) {
	    	Shell.getInstance().out(command.toString());
	    }
	}
	
	public boolean hasCommandsStartingWith(String prefix){
		boolean found=false;
	    for (Command command:commands) {
	    	if (command.getName().startsWith(prefix)) {
	    		found=true;    		
	    	}
	    }
	    return found;
	}	
	
	public void showAllCommandsStartingWith(String prefix){
		HashSet<String> commandsSet = new HashSet<String>();
	    for (Command command:commands) {
	    	if (command.getName().startsWith(prefix)) {
                commandsSet.add(command.getName());	    		
	    	}
	    }
	    for (String commandString : commandsSet) {
	    	Shell.getInstance().outln("    "+commandString);
	    }
	}
}
