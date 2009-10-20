package hanoi;

import java.util.*;

/**
 * Eine Konfiguration der Tuerme von Hanoi-Simulation; 
 * bestimmt durch drei Tuerme (quelle, ziel, dritter),
 * die Zahl n der im Spiel befindlichen Scheiben
 * sowie eine Zugfolge mit einem Positionsindex, der
 * angibt, welcher Zug der Folge als naechster in
 * Vorwaertsrichtung auszufuehren waere.
 * 
 * @author Lothar Schmitz 
 */
public class Konfiguration {

	private Turm quelle;
	private Turm ziel;
	private Turm dritter;
	
	private int n;
	
	private List zuege;
	private int position;
	
    private boolean animiert;
	
	/**
	 * Konstruktor fuer Konfigurations-Objekte 
	 */
	public Konfiguration(int x, int y, int n, String qName, String zName, String dName) { 
		this.n   = n;
		int halbe = n * 10 + 5; // halbe Turmbreite in Pixel
		quelle   = new Turm(qName, n, x + halbe, 40);
		ziel     = new Turm(zName, n, x + 3 * halbe, 40);
		dritter  = new Turm(dName, n, x + 5 * halbe, 40);
		zuege    = new LinkedList();
		position = 0;
		animiert = false;
		quelle.mitAllenScheiben();
		zugFolge(n, quelle, ziel, dritter);
	}

	/**
	 * Rekursive Zugfolgengenerierung
	 * 
	 * @param  n     Zahl der Scheiben
	 * @param  von   Ausgangsturm
	 * @param  nach  Zielturm
	 * @param  ueber Hilfsturm
	*/
	public void zugFolge(int n, Turm von, Turm nach, Turm ueber) {
	    if (n > 0) {
	        zugFolge(n-1, von, ueber, nach);
	        zuege.add(new Zug(von, nach));
	        zugFolge(n-1, ueber, nach, von);
	    }
	}

	/**
	 * Naechsten Vorwaertszug ausfuehren
	*/
	public void vor() {
	    boolean erfolg = position < zuege.size();
	    if (erfolg)  
	       erfolg = ((Zug)zuege.get(position)).ziehen(animiert);
	    if (erfolg)  
	       position++;
	}

	/**
	 * Naechsten Rueckwaertszug ausfuehren
	*/
	public void rueck() {
	    boolean erfolg = position > 0;
	    if (erfolg)  
	       erfolg = ((Zug)zuege.get(position-1)).invers().ziehen(animiert);
	    if (erfolg)  
	       position--;
	}

	/**
	 * Vorwaertszuege bis zum Ende ausfuehren
	*/
	public void zumEnde() {
	    while (position < zuege.size()) {vor();}
	}

	/**
	 * Rueckwaertszuege bis zum Anfang ausfuehren
	*/
	public void zumAnfang() {
	    while (position > 0) {rueck();}
	}

	/**
	 * Naechste i Vorwaertszuege ausfuehren
	*/
	public void vor(int i) {
	    while (i > 0) {vor(); i--;}
	}

	/**
	 * Naechste i Rueckwaertszuege ausfuehren
	*/
	public void rueck(int i) {
	    while (i > 0) {rueck(); i--;}
	}
	
	
	/**
	 * Naechste i Rueckwaertszuege ausfuehren
	*/
	public void setzeAnimiert(boolean animiert) {
	    this.animiert = animiert;
	}
	
	/**
	 * Grundmethode fuer Objekte
	 * 
	 * @return Klartextdarstellung der Form:
	 * 
	 *  <qTurm> : [ <Scheibe unten> ] ... [ <Scheibe oben> ]
	 *  <zTurm> : [ <Scheibe unten> ] ... [ <Scheibe oben> ]
	 *  <dTurm> : [ <Scheibe unten> ] ... [ <Scheibe oben> ]
	 */
	public String toString() {
	    return           "\n" 
	         + quelle  + "\n" 
	         + ziel    + "\n" 
	         + dritter + "\n";
	}
	
	/**
	 * Textdarstellung von Zugliste und aktueller Position
	 * 
	 * @return Klartextdarstellung der Form:
	 *  
	 *  Zugliste:
	 *  
	 *  <zug 0>
	 *  <zug 1>
	 *  ...
	 *  <zug position-1>
	 *  ----------------
	 *  <zug position>
	 *  <zug position+1>
	 *  ...
	 *  <letzter zug>
	 */
	public String zuegeToString() {
	    String ergebnis = "\nZugliste:\n";
	    Iterator z = zuege.iterator();
	    int i = 0;
	    while (z.hasNext()) {
	        if (i == position)
	           ergebnis += "\n-------------------";
	        i++;
	        ergebnis += "\n" + z.next();
	    }
	    if (position == zuege.size())
	       ergebnis += "\n-------------------";
	    return ergebnis + "\n";
	}
	
}
