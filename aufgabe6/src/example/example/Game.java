package example;
import shell.Command;
import shell.CommandHandler;
import shell.Shell;
import shell.StringParameter;
import shell.SymbolParameter;
import shell.tools.HaltCommand;

 

/**
    	Shell.getInstance().addCommand(new Command("farbig", "mach bunt", new CommandHandler(){
			@Override
			public void execute(Command command) {
				zeichneFarbig();				
			}}));
    	
    	Shell.getInstance().addCommand(new Command("schwarz", "mach schwarz", new CommandHandler(){
			@Override
			public void execute(Command command) {
				zeichneSchwarz();				
			}}));
    	
    	Shell.getInstance().addCommand(new HaltCommand("stop", "mach aus"));
    	
    	
    	Command echoCommand = (new Command("echo", "Gibt nen Text aus", new CommandHandler(){
			@Override
			public void execute(Command command) {
				Shell.getInstance().outln(command.getParameterByName("text").getValueAsString());				
			}}));
    	echoCommand.addParameter(new StringParameter("text", "anzugeigender Text"));
    	
    	Shell.getInstance().addCommand(echoCommand);
    	
    	Command zeigeCommand = new Command("zeige", "Zeige ein Bild", new CommandHandler(){
			@Override
			public void execute(Command command) {
				if (command.getParameterByName("modus").getValueAsString().equals("farbig")) {
					zeichneFarbig();
				} else {
					zeichneSchwarz();
				}
			}
			});
    	zeigeCommand.addParameter(new SymbolParameter("modus", "Bildschirmodus", "schwarz", "farbig"));
    	Shell.getInstance().addCommand(zeigeCommand);
    	
    	Shell.getInstance().setDescription("Sie Sind im Kino.");
    	
    	Shell.getInstance().outln(">>> Willkommen im Kino <<<"
    			+ "\n" + "Unser Programm besteht derzeit aus zwei Standbildern,."
    			+ "\n" + "einem farbigen und einem Schwarz-Wei�-Bild"
    			+ "\n" + "Mit dem Kommando '?' erhalten Sie eine Liste aller Kommandos");
        
    
    /**
     * Startet das Beispiel auf der Kommandozeile.
     * @param args Kommandozeilenargumente, hier nicht genutzt.
     */
    public static void main(String[] args) {
    	(new Game()).play();
    }