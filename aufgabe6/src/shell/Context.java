package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

/**
 * Diese Klasse stellt einen Kontext dar, in dem sich der Benutzer bzw. die Shell befindet. Der Kontext enthält unter anderem eine Liste aller Befehle (Commands),
 * die in diesem Kontext ausgeführt werden können.
 * @author pascal
 *
 */

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
	
	/**
	 * Fügt einen in diesem Kontext gültigen Befehl hinzu.
	 * @param command
	 */
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
	
	/**
	 * Zeigt die Hilfe zum entsprechenden Befehl an.
	 * @param commandString Der Name des Befehls, dessen Hilfe aufgerufen werden soll.
	 */
	public void showCommandWithHelp(String commandString) {
		for (Command command:commands) {
			if (command.getName().equals(commandString)) {
				Shell.getInstance().out(command.toString());
			}
		}
		
	}
	
	/**
	 * Zeigt alle gültigen Befehle dieses Kontextes an.
	 */
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
	
	/**
	 * Zeigt alle in diesem Kontext gültigen Befehle und deren Beschreibung an.
	 */
	public void showAllCommandsWithHelp(){
		Shell.getInstance().outln(getDescription());
		Shell.getInstance().outln("Sie können die folgenden Befehle eingeben:");		
	    for (Command command:commands) {
	    	Shell.getInstance().out(command.toString());
	    }
	}
	
	/**
	 * Gibt aus ob es in diesem Kontext einen Befehl gibt, der den übergebenen Präfix besitzt.
	 * @param prefix Der übergebene Präfix.
	 * @return
	 */
	public boolean hasCommandsStartingWith(String prefix){
		boolean found=false;
	    for (Command command:commands) {
	    	if (command.getName().startsWith(prefix)) {
	    		found=true;    		
	    	}
	    }
	    return found;
	}	
	
	/**
	 * Gibt alle Befehle aus, die den angegebenen Präfix haben.
	 * @param prefix Der übergebene Präfix.
	 */
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
