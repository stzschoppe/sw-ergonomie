package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;

public class ShowAllCommandsWithHelpCommand extends Command {

	public ShowAllCommandsWithHelpCommand(String name, String description) {
		super(name, description, new CommandHandler() {

			@Override
			public void execute(Command command) {
				Shell.getInstance().getActiveContext().showAllCommandsWithHelp();
				
			}
			
		});
		// TODO Auto-generated constructor stub
	}

}
