package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;
import shell.StringParameter;

/** Vorlage für einen Befehl der die Hilfe zu einem einzelnen Befehl anzeigt.
 * 
 * @author Thomas Bühring
 *
 */
public class ShowCommandHelpCommand extends Command {

	/** Erstellt den Befehl mit Handler.
	 * 
	 * @param name        Name des Befehls, wie er in der Shell erscheinen soll.
	 * @param description Beschreibung des Befehls für die Hilfe.
	 */
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
