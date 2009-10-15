import shell.Command;
import shell.CommandHandler;
import shell.Shell;

 

/**
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
    	
    	Shell.getInstance().out(">>> Willkommen im Kino <<<" + "\n" + "Unser Programm besteht derzeit aus zwei Standbildern,." + "\n" + "einem farbigen und einem Schwarz-Wei�-Bild" + "\n" + "Mit dem Kommando 'Hilfe' erhalten Sie eine Liste aller Kommandos");
        
        shell.getActiveContext().showAllCommands();
    
    /**
     * Startet das Beispiel auf der Kommandozeile.
     * @param args Kommandozeilenargumente, hier nicht genutzt.
     */
    public static void main(String[] args) {
    	(new Game()).play();
    }