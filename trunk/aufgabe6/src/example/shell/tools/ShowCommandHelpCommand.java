package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;
import shell.StringParameter;

public class ShowCommandHelpCommand extends Command {

	public ShowCommandHelpCommand(String name, String description) {
		super(name, description, new CommandHandler() {

			@Override
			public void execute(Command command) {
				String commandString = command.getParameterByName("befehl").getValueAsString();
				Shell.getInstance().getActiveContext().showCommandWithHelp(commandString);
			}
			
		});
		addParameter(new StringParameter("befehl", "Name des Befehls zu dem die Hilfe angezeigt wird."));
	}

}
