import shell.Command;
import shell.CommandHandler;
import shell.Shell;

 

/** * Class Game - die Hauptklasse des Abenteuerspiels. *  *  Es handelt sich um ein sehr einfaches, textbasiertes Spiel. *  Dieses Spiel wird erst interessant, wenn man es ausbaut! *  *  Um das Spiel zu spielen, erzeugen Sie ein "Game"-Objekt und *  rufen Sie dessen "play"-Methode auf. *  *  Diese Klasse baut die komplette Spielumgebung auf, legt den Parser *  an, der die Benutzereingaben entschluesselt und startet das Spiel.   *  Hier werden auch die vom Parser entschlüsselten Kommandos *  interpretiert. *   *  HIER UMGEBAUT ZUM KOMMANDOINTERPRETER FUER PICTURES!! */class Game {    private Picture bild;    private Shell shell;        /**     * Spiel anlegen.     */    public Game()     {
    	shell = Shell.getInstance();
    	
    	Command tempCommand = new Command("farbig", "mach bunt", new CommandHandler(){

			@Override
			public void execute(Command command) {
				zeichneFarbig();				
			}});
    	Shell.getInstance().addCommand(tempCommand);
    	
    	tempCommand = new Command("schwarz", "mach schwarz", new CommandHandler(){

			@Override
			public void execute(Command command) {
				zeichneSchwarz();				
			}});
    	Shell.getInstance().addCommand(tempCommand);
    	
    	tempCommand = new Command("Hilfe", "mach Hilfe", new CommandHandler(){

			@Override
			public void execute(Command command) {
				printHelp();				
			}});
    	Shell.getInstance().addCommand(tempCommand);
    	
    	tempCommand = new Command("stop", "mach aus", new CommandHandler(){

			@Override
			public void execute(Command command) {
				shell.halt();				
			}});
    	Shell.getInstance().addCommand(tempCommand);
    	        bild   = new Picture();    }    /**     *  Zentrale Verarbeitungsschleife.     */    public void play()     {                    // Solange Befehle lesen, analysieren und ausfuehren,        // bis "true" zurueckgegeben wird        
    	Shell.getInstance().out(">>> Willkommen im Kino <<<" + "\n" + "Unser Programm besteht derzeit aus zwei Standbildern,." + "\n" + "einem farbigen und einem Schwarz-Weiß-Bild" + "\n" + "Mit dem Kommando 'Hilfe' erhalten Sie eine Liste aller Kommandos");        Shell.getInstance().run();
            }    // Implementierung der Befehle:    /**     * Die Befehlsliste anzeigen     */    private void printHelp()     {        System.out.println("Sie sind im Kino.");        System.out.println();        System.out.println("Sie können folgende Kommandos geben:");
        shell.getActiveContext().showAllCommands();    }    /**      * Farbige Szene anzeigen.     */    private void zeichneFarbig()     {        bild.setColor();    }    /**      * Schattenriss-Szene anzeigen.     */    private void zeichneSchwarz()     {        bild.setBlackAndWhite();    }
    
    /**
     * Startet das Beispiel auf der Kommandozeile.
     * @param args Kommandozeilenargumente, hier nicht genutzt.
     */
    public static void main(String[] args) {
    	(new Game()).play();
    }}