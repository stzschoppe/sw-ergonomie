package shell;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private boolean run=true;
	
	public Shell(){
		this.defaultContext = new Context("default", ">");
		setActiveContext(defaultContext);
	}
	
	public void run(){
		while (run) {
			readCommand();
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
            System.out.println ("There was an error during reading: "+ exc.getMessage());
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
        			throw new RuntimeException("Argument mismatch. Wrong argument data type.\nExpected "+currentParameter.getType());
        		}
        	} else {
        		throw new RuntimeException("Argument mismatch. Too few arguments given.\nExpected "+currentParameter.getName());
        	}
        }
         
        return calledCommand;

    }
	
	
	

}
