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
	
	public void addCommand(Command command)
	{}
	
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
	
	public void showAllCommands(){
		
	}
	
	public void showAllCommandsWithHelp(){
		
	}
	
	public void showAllCommandsStartingWith(String str){
		
	}
}
