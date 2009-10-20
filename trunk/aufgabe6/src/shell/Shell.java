package shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Diese Klasse definiert eine Shell und dient als Interface für den Benutzer. Die Shell ist nach dem Singleton-Muster realisiert.
 *
 */
public class Shell {
	/** Der aktive Kontext.
	 */
	private Context activeContext;
	/** Der Standard-Kontext (der beim Starten der Shell aktiv ist).
	 */
	private Context defaultContext;
	/** Die Instanzvariable des Singleton-Musters.
	 */
	private static Shell instance;	
	/** Status der Shell (laufend oder zum Beenden vorgemerkt).
	 */
	private boolean run;
	
	/**
	 * @return Gibt den Standardkontext zurück, in dem sich die Shell befindet. 
	 */
	Context getDefaultContext() {
		return defaultContext;
	}

	/** Setzt den Standard-Kontext.
	 * @param defaultContext Zu setzender Standard-Kontext.
	 */
	void setDefaultContext(Context defaultContext) {
		this.defaultContext = defaultContext;
	}
	
	/**
	 * @return Die Instanz der Shell (Singleton-Muster).
	 */
	public static Shell getInstance() {
		if (instance==null) {
			instance = new Shell();
		}
		return instance;
	}
	
	/** Erzeugt eine neue Shell. Dabei werden einige Befehle automatisch hinzugefügt.
	 *  @see shell.tools
	 */
	private Shell(){
		this.defaultContext = new Context("default", "",  ">");
		setActiveContext(defaultContext);
		addCommand(new shell.tools.ShowAllCommandsCommand("?", "Zeigt eine Liste aller möglichen Befehle an."));
		addCommand(new shell.tools.ShowCommandHelpCommand("?", "Zeigt die Hilfe zu einem Befehl an."));
		addCommand(new shell.tools.ShowAllCommandsWithHelpCommand("??", "Zeigt eine Liste aller möglichen Befehle mit Hilfe an."));
	}
	
	/**
	 * Diese Methode startet die Shell so dass Befehle eingegeben werden können.
	 * Der Aufruf weiterer Methoden ist dazu nicht notwendig. Die Shell wird über die Methode halt() gestoppt.
	 */
	public void run(){
		run = true;
		while (run) {
			try {
				// liest Befehl ein
				Command command = readCommand();
				// führt zugehörigen Handler aus
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
	
	/** Fügt einen Befehl zum Standard-Kontext hinzu.
	 * 
	 * @param command Der hinzuzufügende Befehl.
	 */
	public void addCommand(Command command){
		
		defaultContext.addCommand(command);
	}
	
	/** Gibt einen Text auf der Shell aus.
	 * 
	 * @param out Der auszugebende Text.
	 */
	public void out(String out){
		System.out.print(out);
	}	
	
	/** Gibt einen Text auf der Shell aus und fügt einen Zeilenumbruch an.
	 * 
	 * @param out Der auszugebende Text.
	 */
	public void outln(String out){
		System.out.println(out);
	}

	/**
	 * @return Der aktive Kontext der Shell.
	 */
	public Context getActiveContext() {
		return activeContext;
	}
	
    /** Setzt den aktiven Kontext der Shell.
     * @param activeContext Der zu setzende Kontext.
     */
	public void setActiveContext(Context activeContext) {
		this.activeContext = activeContext;
	}

    /** Setzt die Beschreibung des Standard-Kontext der Shell.
     * @param description Die zu setzende Beschreibung.
     */
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
        
        // Gibt die Eingabeabfrage der Shell aus.
        System.out.print(getActiveContext().getPrompt()); 

        // Erzeugt aus der Benutzereingabe einen Tokenizer (Wort-für-Wort-Leser)
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
        
        // Findet den passenden Befehl zur Benutzereingabe
        for (Command command : getActiveContext().getCommands()) {
        	if (command.getName().equals(commandString)) {
        		// hier werden überladene Befehle unterstützt: wenn die Zahl der Paramter /besser/ passt,
        		// wird der gefundene Befehl übernommen
        		if (calledCommand==null || command.getParameters().size()==givenParameterCount) {
        		    calledCommand = (Command) command.clone();
        		}
        	}
        }
        
        if (calledCommand==null) {
        	throw new UnsupportedOperationException(commandString);
        }
        
        // Setzt im gefunden Befehl Werte für die eingegebenen Parameter, wenn vorhanden
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
