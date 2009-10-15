package shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * This class represents a command shell and is the interface for any user. 
 * The class has a ArrayList of all possible contexts. A Context is a 
 * @author Pascal Staudenrau�
 *
 */
public class Shell {
	private Context activeContext;
	private Context defaultContext;
	private boolean run=true;
	
	public Shell(){
		this.defaultContext = new Context("default", ">");
		setActiveContext(defaultContext);
	}
	
	public void run(){
		while (run) {
			try {
				Command command = readCommand();
				command.executeHandler();
			} catch (IllegalArgumentException exception) {
				out(exception.getMessage());
			}
		}
	}
	
	public void addCommand(Command command){
		
		defaultContext.addCommand(command);
	}
	
	public static void out(String out){
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
        
        for (Command command : getActiveContext().getCommands()) {
        	if (command.getName().equals(commandString)) {
        		calledCommand = (Command) command.clone();
        	}
        }
        for (int x=0; x<calledCommand.getParameters().size(); x++) {
        	Parameter currentParameter=calledCommand.getParameters().get(x);
        	if(tokenizer.hasMoreTokens()) {
        		try {
        			currentParameter.setValue(tokenizer.nextToken());
        		} catch(IllegalArgumentException exception) {
        			throw new IllegalArgumentException("Argumente passen nicht: Falscher Datentyp f�r das Argument "+currentParameter.getName()+", erwartet "+currentParameter.getType()+".\n"+exception.getMessage());
        		}
        	} else {
        		throw new IllegalArgumentException("Argumente passen nicht: Zu wenige Argumente.\nErwartetes Argument: "+currentParameter.getName()+".");
        	}
        }
         
        return calledCommand;

    }

}
