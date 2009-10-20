package shell;

/** Behandelt den Aufruf eines Befehls.
 * 
 * @author Thomas Bühring
 *
 */
public interface CommandHandler {
	
	/** Wird aufgerufen, wenn der Benutzer einen Befehl eingegeben hat, dem dieser Handler zugeordnet. 
	 * 
	 * @param command Der eingegebene Befehl samt Parametern.
	 */
	void execute(Command command);

}
