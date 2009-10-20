package hanoi;

/**
 * Abstrakte Klasse Figur - bewegliche grafische Objekte
 * 
 * @author Lothar Schmitz
 */
public abstract class Figur {

	protected int xPosition;
	protected int yPosition;
	
	protected String farbe;
	
    /**
     * Konstruktor setzt default-Werte.
     */
    public Figur() {
		xPosition = 100;
		yPosition = 100;
		farbe = "red";
		istSichtbar = false;
    }

    /**
     * Neue Farbe aus "red", "yellow", "blue", "green",
	 * "magenta" und "black".
     */
    public void neueFarbe(String neu) {
		farbe = neu;
		zeichne();
    }
	
	protected boolean istSichtbar;
	
	/**
	 * Figur erscheinen lassen. (Nur, wenn nicht sichtbar).
	 */
	public void erscheine() {
		istSichtbar = true;
		zeichne();
	}
	
	/**
	 * Figur verschwinden lassen. (Nur, wenn sichtbar.)
	 */
	public void verschwinde() {
		loesche();
		istSichtbar = false;
	}
	
	/**
	 * Figur zeichnen. 
	 * MUSS IN KONKRETEN UNTERKLASSEN IMPLEMENTIERT WERDEN.
	 */
	abstract protected void zeichne();
	
	/**
	 * Figur loeschen.
	 */
	private void loesche() {
		if(istSichtbar) {
			Canvas canvas = Canvas.getCanvas();
			canvas.erase(this);
		}
	}
	
	/**
	 * Figur versetzen.
	 */
	public void setzeNach (int x, int y) {
	    loesche();
		xPosition = x;
		yPosition = y;
		if (istSichtbar) zeichne();
	}
	
    /**
     * Langsam horizontal um distanz pixel bewegen.
     */
    public void horizontal(int distanz) {
		int delta = 1;
		if(distanz < 0) {
			delta = -1;
			distanz = -distanz;
		}
		for(int i = 0; i < distanz; i++) {
			xPosition += delta;
			zeichne();
		}
    }
	
    /**
     * Langsam vertikal um distanz pixel bewegen.
     */
    public void vertikal(int distanz) {
		int delta = 1;
		if(distanz < 0) {
			delta = -1;
			distanz = -distanz;
		}
		for(int i = 0; i < distanz; i++) {
			yPosition += delta;
			zeichne();
		}
    }

}
