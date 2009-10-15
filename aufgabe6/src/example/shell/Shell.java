package shell;

import java.util.ArrayList;

/**
 * This class represents a command shell and is the interface for any user. 
 * The class has a ArrayList of all possible contexts. A Context is a 
 * @author Pascal Staudenrauﬂ
 *
 */
public class Shell {
	private ArrayList<Context> contexts;
	private Context activeContext;
	private Context defaultContext;
	
	public Shell(){
		this.defaultContext = new Context("default", ">");
		addContext(defaultContext);
		setActiveContext(defaultContext);
	}
	
	public void run(){
		//TODO Method-stub
	}
	
	public void addCommand(Command command){
		
		defaultContext.addCommand(command);
	}
	
	public void addContext(Context context){
		contexts.add(context);
	}
	
	
	public static void out(String out){
		// TODO Method-stub
	}

	public ArrayList<Context> getContexts() {
		return contexts;
	}

	public void setContexts(ArrayList<Context> contexts) {
		this.contexts = contexts;
	}

	public Context getActiveContext() {
		return activeContext;
	}

	public void setActiveContext(Context activeContext) {
		this.activeContext = activeContext;
	}
	
	

}
