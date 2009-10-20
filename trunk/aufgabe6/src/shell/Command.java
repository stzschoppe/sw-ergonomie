package shell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Die Klasse Command stellt einen einzelnen Befehl dar. Sie enth�lt den Namen
 * des Befehls, mit dem er auf der Kommandozeile aufgerufen wird, seine Beschreibung f�r die Hilfe und eine Liste der m�glichen Parameter f�r diesen Befehl.
 * 
 * @author thom
 *
 */
public class Command {
	/**
	 * Der Name mit dem der Befehl in der Shell aufgerufen wird.
	 */
	private final String name;
	/**
	 * Der Handler, der aufgerufen wird, wenn der Befehl vom Benutzer eingegeben wurde.
	 * @see CommandHandler
	 */
	private final CommandHandler handler;
	/**
	 * Die Liste der Parameter diese Befehls.
	 */
	private final ArrayList<Parameter> parameters;
	/**
	 * Die Beschreibung des Befehls f�r die Hilfe.
	 */
	private final String description;
	
	/**
	 * Legt einen neuen Befehl an.
	 * @param name        Name des Befehls auf der Kommandozeile.
	 * @param description Beschreibung des Befehls f�r die Hilfe.
	 * @param handler     Handler der aufgerufen werden soll, wenn der Befehl vom Benutzer eingegeben wurde.
	 */
	public Command(String name, String description, CommandHandler handler) {
		this.name = name;
		this.description = description;
		this.handler = handler;
		this.parameters = new ArrayList<Parameter>();
	}
	
	/**
	 * @return Gibt die Beschreibung des Befehls f�r die Hilfe zur�ck.
	 */
	public String getDescription() {
		return description;
	}	
	
	/**
	 * @return Gibt den Namen des Befehls zur�ck, wie er in der Shell eingegeben wird.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *@return Gibt den Handler des Befehls zur�ck, der aufgerufen wird wenn der Benutzer den Befehl eingegeben hat. 
	 */
	CommandHandler getHandler() {
		return handler;
	}
	
	/**
	 * F�hrt den Handler dieses Befehls aus.
	 */
	public void executeHandler() {
		getHandler().execute(this);
	}
	
	/**
	 * F�gt einen Parameter zu dem Befehl hinzu.
	 * @param parameter Der hinzuzuf�gende Parameter.
	 */
	public void addParameter(Parameter parameter) {
		parameters.add(parameter);
	}
	
	/**
	 * @return Gibt eine Liste der Parameter zur�ck. 
	 */
	public ArrayList<Parameter> getParameters() {
		return parameters;
	}
	
	public Parameter getParameterByName(String name) {
		for (int x=0; x<getParameters().size(); x++) {
			Parameter currentParameter = getParameters().get(x);
			if (currentParameter.getName().equals(name)) {
				return currentParameter;
			}
		}
		throw new IllegalArgumentException("Kein Parameter mit dem Namen "+name+".");
	}



    /**
     * @return Erstellt eine Kopie dieses Objekts.
     */
	@Override
	protected Object clone() {
		Command command = new Command(name, description, handler);
		for (int x=0; x<parameters.size(); x++) {
			command.addParameter((Parameter)parameters.get(x).clone());
		}
		return command;
	}
	
	@Override
	public String toString() {
		String result = "    "+getName();
		for (int x=0; x<getParameters().size(); x++) {
			result += " <"+getParameters().get(x).getName()+">";
		}
		result+="\n        "+getDescription()+"\n";
		if (!getParameters().isEmpty()) {
			result+="        Parameter:\n";
		    for (int x=0; x<getParameters().size(); x++) {
    			result += "            "+getParameters().get(x).toString()+"\n";
	    	}
		}
		return result;
	}
	
	

}
