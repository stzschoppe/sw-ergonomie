package shell.tools;

import shell.Command;
import shell.CommandHandler;
import shell.Shell;

/** Vorlage f�r ein Kommando das die Shell anh�lt.
 * 
 * @author Thomas B�hring
 *
 */
public class HaltCommand extends Command {

	/** Erstellt den Befehl mit Handler.
	 * 
	 * @param name        Name des Befehls, wie er in der Shell erscheinen soll.
	 * @param description Beschreibung des Befehls f�r die Hilfe.
	 */
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
