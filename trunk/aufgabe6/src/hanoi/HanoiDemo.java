package hanoi;import shell.Command;import shell.CommandHandler;import shell.IntegerParameter;import shell.Shell;/** * Class HanoiDemo - die Hauptklasse der Simulation. *  *  Es handelt sich um ein sehr einfaches, textbasiertes Spiel. *  Dieses Spiel wird erst interessant, wenn man es ausbaut! *  *  Um die Simulation zu starten, erzeugen Sie ein "HanoiDemo"-Objekt  *  und rufen Sie dessen "play"-Methode auf. *  *  Diese Klasse baut eine Konfiguration auf, legt den Parser an,  *  der die Benutzereingaben entschluesselt und startet die Simulation.   *  Hier werden auch die vom Parser entschl�sselten Kommandos interpretiert. *   *  HIER UMGEBAUT ZUM KOMMANDOINTERPRETER FUER HANOI!! *   *  @author ((adaptiert von Lothar Schmitz)nachadaptiert von Soeren Gaertner) */class HanoiDemo {    /**     * aktuelle Konfiguration     */    private Konfiguration aktuell;            /**     * Hauptmethode, started das Spiel.     * @param args     */    public static void main(String[] args) {       (new HanoiDemo(3)).play();    }    /**     * Spiel anlegen.     * @param n Anzahl der Scheiben     */    public HanoiDemo(int n)  {    	createCommands();        aktuell = new Konfiguration(20,20, n,"A","B","C");        Shell.getInstance().outln(aktuell.toString());    }        /**     * Erstellt die commands f�r die shell. Ausgelagert aus dem Konstruktor auf Gruenden der Uebersicht.     * Weiterfuehrende Erlaeuterungen zu finden in der shell/package.html.     */    private void createCommands(){ 	    	    	//Erstellt ein Kommando ohne Parameter    	Command vorCommand = (new Command("vor", "Die Demo n Schritte vorspulen.", new CommandHandler(){    					@Override			public void execute(Command command) {				vorText(command.getParameterByName("Schrittzahl").getValueAsInteger());							}}));    	//F�gt dem Kommande einen Parameter hinzu    	vorCommand.addParameter(new IntegerParameter("Schrittzahl", "Anzahl der Schritte."));	    	Shell.getInstance().addCommand(vorCommand);    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("vor", "Die Demo genau einen Schritt vorspulen.", new CommandHandler(){    					@Override			public void execute(Command command) {				vorText(1);			}}));    	    	    	//Erstellt ein Kommando ohne Parameter    	Command rueckCommand = (new Command("rueck", "Die Demo n Schritte zur�ckspulen.", new CommandHandler(){    					@Override			public void execute(Command command) {				rueckText(command.getParameterByName("Schrittzahl").getValueAsInteger());							}}));    	//F�gt dem Kommande einen Parameter hinzu    	rueckCommand.addParameter(new IntegerParameter("Schrittzahl", "Anzahl der Schritte."));    	    	Shell.getInstance().addCommand(rueckCommand);    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("rueck", "Die Demo genau einen Schritt zur�ckspulen.", new CommandHandler(){    					@Override			public void execute(Command command) {				rueckText(1);			}}));    	    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("zuege", "Zeigt die Zuege der Demo.", new CommandHandler(){    					@Override			public void execute(Command command) {				zuegeText();			}}));    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("anfang", "Zum Anfang von der Demo.", new CommandHandler(){    					@Override			public void execute(Command command) {				zumAnfangText();			}}));    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("ende", "Zum Ende von der Demo.", new CommandHandler(){    					@Override			public void execute(Command command) {				zumEndeText();			}}));    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("animiert", "Wechselt in den Animationsmodus. Jeder einzelne Schritt wird animiert.", new CommandHandler(){    					@Override			public void execute(Command command) {				setzeAnimiert(true);			}}));    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("schnell", "Deaktiviert den Animationsmodus. Jeder Schritt geschieht unverz�glich.", new CommandHandler(){    					@Override			public void execute(Command command) {				setzeAnimiert(false);			}}));    	    	//Erstellt ein Kommando ohne Parameter    	Shell.getInstance().addCommand(new Command("stop", "Beendet die Demo.", new CommandHandler(){						@Override			public void execute(Command command) {				Shell.getInstance().halt();					System.exit(0);			}}));    }    /**     *  Methode zum starten der Demo. Gibt einen Willkommenstext aus und startet per run() die shell.     */    public void play()     {        Shell.getInstance().outln(">>> Willkommen beim T�rme-von-Hanoi-Spiel <<<"    			+ "\n" + "Mit dem Kommando '?' erhalten Sie eine Liste aller Kommandos");        Shell.getInstance().run();    }    // Implementierung der Befehle    /**      * Vorwaerts gehen um angegebene Schrittzahl.     *      * @param parameter Anzahl der Schritte     */    private void vorText(int parameter)  {    	    	aktuell.vor(parameter);        Shell.getInstance().outln(aktuell.toString());    }    /**      * Zugliste anzeigen.     */    private void zuegeText()  {    	Shell.getInstance().outln(aktuell.zuegeToString());    }    /**      * Rueckwaerts gehen um angegebene Schrittzahl.     *      * @param parameter Anzahl der Schritte     */    private void rueckText(int parameter)  {        aktuell.rueck(parameter);               Shell.getInstance().outln(aktuell.toString());    }    /**      * Zugfolge rueckwaerts bis zum Anfang abarbeiten.     */    private void zumAnfangText()  {        aktuell.zumAnfang();        Shell.getInstance().outln(aktuell.toString());    }    /**      * Zugfolge bis zum Ende abarbeiten.     */    private void zumEndeText()  {        aktuell.zumEnde();        Shell.getInstance().outln(aktuell.toString());    }        /**      * Zugliste anzeigen.     */    private void setzeAnimiert(boolean animiert)  {        if (animiert) {			Shell.getInstance().outln("Animationsmodus aktiviert.");		}        else {			Shell.getInstance().outln("Animationsmodus deaktiviert.");		}		aktuell.setzeAnimiert(animiert);    }}