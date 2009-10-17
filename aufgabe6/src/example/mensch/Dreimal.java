package mensch;  


/**
 * Dreimal würfeln.
 * 
 * @author (Lothar Schmitz) 
 * @version (9.6.04)
 */
public class Dreimal extends Wuerfeln {

	private int anzahl;

	/**
	 * Konstruktor für Dreimal-Objekte
	 */
	public Dreimal() {
		anzahl = 3;
	}

	/**
	 * Dreimal würfeln, aber erste 6 melden.
	 */
	public int wuerfle() {
	    for (int i = anzahl; i > 0; i--) {
	        if (Wuerfel.wuerfeln() == 6) 
	           return 6;
	    };
		return 0;
	}
}
