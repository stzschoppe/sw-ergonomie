package shell;

import java.util.ArrayList;

public class Context {
	private ArrayList<Command> commands;
	private String name;
	
	public Context(String name){
		this.name = name;
	}
	
	
	public void addCommand(Command command)
	{}
	
	public void showAllCommands(){
		
	}
	
	public void showAllCommandsWithHelp(){
		
	}
	
	public void showAllCommandsStartingWith(String str){
		
	}
}
