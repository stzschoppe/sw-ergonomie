package shell;

import java.util.ArrayList;

public class Shell {
	private ArrayList<Context> contexts;
	private Context ActiveContext;
	
	public Shell(Context DefaultContext){
		ActiveContext = DefaultContext;
	}
	
	public void run(){
		//TODO Method-stub
	}
	
	public void addContext(){
		//TODO Method-stub
	}
	
	public void setActiveContext(){
		//TODO Method-stub
	}
	
	public void out(String out){
		// TODO Method-stub
	}

	public ArrayList<Context> getContexts() {
		return contexts;
	}

	public void setContexts(ArrayList<Context> contexts) {
		this.contexts = contexts;
	}

	public Context getActiveContext() {
		return ActiveContext;
	}

	public void setActiveContext(Context activeContext) {
		ActiveContext = activeContext;
	}
	
	

}
