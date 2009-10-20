package hanoi;

/**
 * Eine Scheibe der Tuerme von Hanoi - bestimmt durch ihre Breite.
 * 
 * @author Lothar Schmitz 
 */
public class Scheibe {
	
	protected int breite;
	
	// Fuer die Grafik:  
	private int xPosition;
	private int yPosition;
	private Rechteck rechteck;

	/**
	 * Konstruktor fuer Scheibe-Objekte
	 */
	public Scheibe(int breite) {
	    this.breite = breite;
	}

	/**
	 * Konstruktor fuer Scheibe-Objekte mit Grafik
	 */
	public Scheibe(int breite, int x, int y) {
	    this(breite);
		xPosition = x;
		yPosition = y;
		bestimmeRechteck();
		rechteck.erscheine();
	}

	/**
	 * Grundmethode fuer Objekte
	 * 
	 * @return Klartextdarstellung der Form [ <breite> ]
	 */
	public String toString() {
	    return "[ " + breite() + " ]";
	}

	/**
	 * Getter-Methode fuer breite
	 */
	public int breite() {
	    return breite;
	}

	/**
	 * Hilfsmethode: Rechteck neu bestimmen. 
	 */
	private void bestimmeRechteck() {
		rechteck = new Rechteck(xPosition,yPosition,(breite*2+1)*10,13);
	}
	
	/**
	 * Figur versetzen.
	 */
	public void setzeNach (int x, int y) {
	    if (rechteck != null)
	       rechteck.verschwinde();
		xPosition = x;
		yPosition = y;
		bestimmeRechteck();
		rechteck.erscheine();
	}
	
	/**
	 * Scheibe senkrecht bewegen.
	 */
	public void senkrecht (int distanz) {
		int delta = 1;
		if(distanz < 0) {
			delta = -1;
			distanz = -distanz;
		}
		for(int i = 0; i < distanz; i++) {
		    setzeNach(xPosition, yPosition + delta);
		}
	}
	
	/**
	 * Scheibe waagerecht bewegen.
	 */
	public void waagerecht (int distanz) {
		int delta = 1;
		if(distanz < 0) {
			delta = -1;
			distanz = -distanz;
		}
		for(int i = 0; i < distanz; i++) {
		    setzeNach(xPosition + delta, yPosition);
		}
	}
	
}
