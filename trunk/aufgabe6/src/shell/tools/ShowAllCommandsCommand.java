package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;

public class ShowAllCommandsCommand extends Command {

	public ShowAllCommandsCommand(String name, String description) {
		super(name, description, new CommandHandler() {

			@Override
			public void execute(Command command) {
				Shell.getInstance().getActiveContext().showAllCommands();
				
			}
			
		});
		// TODO Auto-generated constructor stub
	}

}
