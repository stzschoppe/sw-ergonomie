package shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Diese Klasse definiert eine Shell und dient als Interface für den Benutzer. Die Shell ist nach dem Singleton-Muster realisiert.
 *
 */
public class Shell {
	private Context activeContext;
	private Context defaultContext;
	/**
	 * @return Gibt den Standardkontext aus, in dem sich die Shell befindet. 
	 */
	Context getDefaultContext() {
		return defaultContext;
	}

	/**
	 * @param defaultContext the defaultContext to set
	 */
	void setDefaultContext(Context defaultContext) {
		this.defaultContext = defaultContext;
	}

	private boolean run;
	private static Shell instance;
	
	public static Shell getInstance() {
		if (instance==null) {
			instance = new Shell();
		}
		return instance;
	}
	
	private Shell(){
		this.defaultContext = new Context("default", "",  ">");
		setActiveContext(defaultContext);
		addCommand(new shell.tools.ShowAllCommandsCommand("?", "Zeigt eine Liste aller möglichen Befehle an."));
		addCommand(new shell.tools.ShowCommandHelpCommand("?", "Zeigt die Hilfe zu einem Befehl an."));
		addCommand(new shell.tools.ShowAllCommandsWithHelpCommand("??", "Zeigt eine Liste aller möglichen Befehle mit Hilfe an."));
	}
	
	/**
	 * Diese Methode startet die Shell so dass Befehle eingegeben werden können. Der Aufruf weiterer Methoden ist dazu nicht notwendig. Die Shell wird über die Methode halt() gestoppt.
	 */
	public void run(){
		run = true;
		while (run) {
			try {
				Command command = readCommand();
				command.executeHandler();
			} catch (IllegalArgumentException exception) {
				outln(exception.getMessage());
		    } catch (UnsupportedOperationException exception) {
		    	outln("Befehl unbekannt.");
		    	if (exception.getMessage()!=null && getActiveContext().hasCommandsStartingWith(exception.getMessage())) {
		    		outln("Meinten Sie einen der folgenden Befehle?");
			    	getActiveContext().showAllCommandsStartingWith(exception.getMessage());
		    	} else {
		    		outln("Geben Sie \"?\" ein, um eine Liste der möglichen Befehle anzuzeigen.");
		    	}
		    }
		}
	}
	
	/**
	 * Stoppt die Shell.
	 */
	public void halt() {
		run=false;
	}
	
	public void addCommand(Command command){
		
		defaultContext.addCommand(command);
	}
	
	public void out(String out){
		System.out.print(out);
	}	
	
	public void outln(String out){
		System.out.println(out);
	}

	public Context getActiveContext() {
		return activeContext;
	}

	public void setActiveContext(Context activeContext) {
		this.activeContext = activeContext;
	}
	
	public void setDescription(String description) {
		getDefaultContext().setDescription(description);
	}
	
	/**
	 * Liest die Kommando-Zeile und erzeugt daraus Tokens, die interpretiert werden.
	 * @return der eingegebene Befehl 
	 */
	private Command readCommand() {
	    String inputLine = "";
        String commandString = "?";
        Command calledCommand = null;
        
        System.out.print(getActiveContext().getPrompt()); 

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            inputLine = reader.readLine();
        }
        catch(java.io.IOException exc) {
            System.out.println ("Fehler beim Lesen: "+ exc.getMessage());
        }

        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            commandString = tokenizer.nextToken();
        else
            commandString = null;
        
        int givenParameterCount = tokenizer.countTokens();
        
        for (Command command : getActiveContext().getCommands()) {
        	if (command.getName().equals(commandString)) {
        		if (calledCommand==null || command.getParameters().size()==givenParameterCount) {
        		    calledCommand = (Command) command.clone();
        		}
        	}
        }
        
        if (calledCommand==null) {
        	throw new UnsupportedOperationException(commandString);
        }
        
        for (int x=0; x<calledCommand.getParameters().size(); x++) {
        	Parameter currentParameter=calledCommand.getParameters().get(x);
        	if(tokenizer.hasMoreTokens()) {
        		try {
        			currentParameter.setValue(tokenizer.nextToken());
        		} catch(IllegalArgumentException exception) {
        			throw new IllegalArgumentException("Argumente passen nicht: Falscher Datentyp für das Argument "+currentParameter.getName()+", erwartet "+currentParameter.getType()+".\n"+exception.getMessage());
        		}
        	} else {
        		throw new IllegalArgumentException("Argumente passen nicht: Zu wenige Argumente.\nErwartetes Argument: "+currentParameter.getName()+".");
        	}
        }
         
        return calledCommand;

    }

}
