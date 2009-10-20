package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

/**
 * Diese Klasse stellt einen Kontext dar, in dem sich der Benutzer bzw. die Shell befindet. Der Kontext enthält unter anderem eine Liste aller Befehle (Commands),
 * die in diesem Kontext ausgeführt werden können.
 * 
 * @author Pascal Staudenrauß
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
	
	/**
	 * @return Die registrierten Befehle dieses Kontexts.
	 */
	public Collection<Command> getCommands() {
		return Collections.unmodifiableCollection(commands);
	}
	
	/**
	 * @return Den Namen des Kontexts.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Die Eingabeabfrage, die in diesem Kontext angezeigt werden soll.
	 */
	public String getPrompt() {
		return prompt;
	}
	
	/** Setzt die Eingabeabfrage.
	 * 
	 * @param prompt Die Eingabeabfrage, die in diesem Kontext angezeigt werden soll.
	 */
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	/** Setzt die Beschreibung des Kontexts.
	 * 
	 * @param description Beschreibung des Kontexts für die Hilfe.
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
    /**
     * @return Die Beschreibung des Kontexts für die Hilfe.
     */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Zeigt die Hilfe zum entsprechenden Befehl an.
	 * @param commandString Der Name des Befehls, dessen Hilfe aufgerufen werden soll.
	 */
	public void showCommandWithHelp(String commandString) {
		Collections.sort(commands);
		for (Command command:commands) {
			if (command.getName().equals(commandString)) {
				Shell.getInstance().out(command.toString());
			}
		}
		
	}
	
	/** Erzeugt eine Liste, in der überladene Befehle nur einmal vorkommen.
	 * 
	 * @return Die unter dem Befehlsnamen vereinigte und sortierte Liste der Befehlsnamen.
	 */
	protected ArrayList<String> getUnifiedCommandNames() {
		HashSet<String> commandsSet = new HashSet<String>();
	    for (Command command:commands) {
            commandsSet.add(command.getName());	    		
	    }
	    ArrayList<String> commandsList = new ArrayList<String>(commandsSet);
	    Collections.sort(commandsList);
	    return commandsList;
	}
	
	/**
	 * Zeigt alle gültigen Befehle dieses Kontextes an.
	 */
	public void showAllCommands(){		
		Shell.getInstance().outln(getDescription());
		Shell.getInstance().outln("Sie können die folgenden Befehle eingeben:");
	    for (String commandString : getUnifiedCommandNames()) {
	    	Shell.getInstance().outln("    "+commandString);
	    }
	    Shell.getInstance().outln("Geben Sie \"??\" ein um eine Liste der Befehle mit zugehöriger Hilfe anzuzeigen.");
	}
	
	/**
	 * Zeigt alle in diesem Kontext gültigen Befehle und deren Beschreibung an.
	 */
	public void showAllCommandsWithHelp(){
		Collections.sort(commands);	
		Shell.getInstance().outln(getDescription());
		Shell.getInstance().outln("Sie können die folgenden Befehle eingeben:");		
	    for (Command command:commands) {
	    	Shell.getInstance().out(command.toString());
	    }
	}
	
	/**
	 * Gibt aus ob es in diesem Kontext einen Befehl gibt, der den übergebenen Präfix besitzt.
	 * @param prefix Der übergebene Präfix.
	 * @return true wenn es solche Befehle gibt.
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
	    for (String commandString : getUnifiedCommandNames()) {
	    	if (commandString.startsWith(prefix)) {
	    		Shell.getInstance().outln("    "+commandString);
	    	}
	    }
	}
}
