package hanoi;

import java.util.*;

/**
 * Ein Turm der Tuerme von Hanoi - bestimmt durch seine Scheiben.
 * 
 * @author Lothar Schmitz 
 */
public class Turm {

	protected String name;
	protected int maxHoehe;
	protected List scheiben;
	
	// Grafik-Anteile: 
	private int yBasis;
	private int xMitte;
	private Rechteck pfahl;

	/**
	 * Konstruktor fuer Turm-Objekte
	 */
	public Turm(String name, int hoehe, int xMitte, int yWolke) {
		this.name = name;
		maxHoehe  = hoehe;
		scheiben  = new ArrayList();
		// Grafik-Anteile: 
		this.xMitte = xMitte;
		int hoch = 15 * maxHoehe;
		yBasis  = yWolke + 30 + hoch;
		pfahl   = new Rechteck (xMitte, yBasis, 10, hoch);
		pfahl.neueFarbe("black");
		pfahl.erscheine();
	}
	
	/**
	 * Getter-Methode fuer name
	 */
	public String name() {
	    return name;
	}
	
	/**
	 * Grundmethode fuer Objekte
	 * 
	 * @return Klartextdarstellung der Form:
	 *  <name> : [ <Scheibe unten> ] ... [ <Scheibe oben> ]
	 */
	public String toString() {
	    String ergebnis = name() + " : ";
	    Iterator s = scheiben.iterator();
	    while (s.hasNext()) {
	        ergebnis += " " + s.next();
	    }
	    return ergebnis;
	}
	
	/**
	 * Breite der obersten Scheibe; 
	 * n + 1, wenn der Turm leer ist.
	 */
	public int breiteOben() {
	    if (scheiben.isEmpty()) return maxHoehe + 1;
	    return ((Scheibe)scheiben.get(scheiben.size()-1)).breite();
	}
	
	/**
	 * Anzahl der Scheiben auf dem Turm.
	 */
	public int scheibenZahl() {
	    return scheiben.size();
	}
	
	/**
	 * Gib die oberste Scheibe und entferne sie vom Turm;
	 * null, wenn der Turm leer ist.
	 */
	public Scheibe gib() {
	    if (scheiben.isEmpty()) return null;
	    return (Scheibe)scheiben.remove(scheiben.size()-1);
	}
	
	/**
	 * Nimm eine Scheibe und lege sie oben auf den Turm;
	 * keine Pruefung auf Zulaessigkeit.
	 */
	public void nimm(Scheibe s) {
	    scheiben.add(s);
	}
	
	/**
	 * Baut einen vollen Turm auf.
	 */
	public void mitAllenScheiben() {
	    int nr = maxHoehe;
	    scheiben.clear();
	    while (nr > 0) {
	        Scheibe s = new Scheibe(nr);
	        if (pfahl != null)
	           s.setzeNach(xMitte(), yObersteScheibe());
	        nimm(s);
	        nr--;
	    }
	}
	
	/**
	 * Getter-Methode fuer yBasis
	 */
	public int yBasis() {
	    return yBasis;
	}
	
	/**
	 * y-Position der Pfahlspitze.
	 */
	public int yPfahlspitze() {
	    return yBasis - 15 * maxHoehe ;
	}
	
	/**
	 * x-Position des Pfahls (Mitte).
	 */
	public int xMitte() {
	    return xMitte;
	}
	
	/**
	 * y-Position der obersten Scheibe.
	 */
	public int yObersteScheibe() {
	    return yBasis - 15 * scheiben.size();
	}

}
