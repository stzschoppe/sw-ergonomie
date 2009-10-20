package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;

/** Vorlage f�r ein Kommando das alle Befehle des aktuellen Kontexts anzeigt.
 * 
 * @author Thomas B�hring
 *
 */
public class ShowAllCommandsCommand extends Command {

	/** Erstellt den Befehl mit Handler.
	 * 
	 * @param name        Name des Befehls, wie er in der Shell erscheinen soll.
	 * @param description Beschreibung des Befehls f�r die Hilfe.
	 */
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
