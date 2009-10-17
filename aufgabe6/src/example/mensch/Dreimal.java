package mensch;  


/**
 * Dreimal w�rfeln.
 * 
 * @author (Lothar Schmitz) 
 * @version (9.6.04)
 */
public class Dreimal extends Wuerfeln {

	private int anzahl;

	/**
	 * Konstruktor f�r Dreimal-Objekte
	 */
	public Dreimal() {
		anzahl = 3;
	}

	/**
	 * Dreimal w�rfeln, aber erste 6 melden.
	 */
	public int wuerfle() {
	    for (int i = anzahl; i > 0; i--) {
	        if (Wuerfel.wuerfeln() == 6) 
	           return 6;
	    };
		return 0;
	}
}
