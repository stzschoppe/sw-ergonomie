package shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * This class represents a command shell and is the interface for any user. 
 * The class has a ArrayList of all possible contexts. A Context is a 
 * @author Pascal Staudenrauß
 *
 */
public class Shell {
	private Context activeContext;
	private Context defaultContext;
	private boolean run;
	private static Shell instance;
	
	public static Shell getInstance() {
		if (instance==null) {
			instance = new Shell();
		}
		return instance;
	}
	
	private Shell(){
		this.defaultContext = new Context("default", ">");
		setActiveContext(defaultContext);
		addCommand(new shell.tools.ShowAllCommandsCommand("?", "Zeigt eine Liste aller möglichen Befehle an."));
		addCommand(new shell.tools.ShowAllCommandsWithHelpCommand("??", "Zeigt eine Liste aller möglichen Befehle mit Hilfe an."));
	}
	
	public void run(){
		run = true;
		while (run) {
			try {
				Command command = readCommand();
				command.executeHandler();
			} catch (IllegalArgumentException exception) {
				outln(exception.getMessage());
		    } catch (UnsupportedOperationException exception) {
		    	getActiveContext().showAllCommandsStartingWith(exception.getMessage());
		    }
		}
	}
	
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
        		if (calledCommand==null || calledCommand.getParameters().size()==givenParameterCount) {
        		    calledCommand = (Command) command.clone();
        		}
        	}
        }
        
        if (calledCommand==null) {
        	throw new UnsupportedOperationException("Befehl unbekannt.\n Meinten Sie einen der folgenden Befehle?\n");
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
