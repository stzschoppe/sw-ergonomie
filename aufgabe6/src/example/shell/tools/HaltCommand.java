package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;

public class HaltCommand extends Command {

	public HaltCommand(String name, String description) {
		super(name, description, 
				new CommandHandler() {

					@Override
					public void execute(Command command) {
                        Shell.getInstance().halt();						
					}
			
		        }
		);
	}

}
