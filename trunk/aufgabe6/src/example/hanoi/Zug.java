package hanoi;

/**
 * Ein Zug der Tuerme von Hanoi-Simulation - 
 * bestimmt durch Quell- und Zielturm.
 * 
 * @author Lothar Schmitz 
 */
public class Zug {
	protected Turm quelle;
	protected Turm ziel;

	/**
	 * Konstruktor fuer Zug-Objekte
	 */
	public Zug(Turm quelle, Turm ziel) {
		this.quelle = quelle;
		this.ziel   = ziel;
	}
	
	/**
	 * Grundmethode fuer Objekte
	 * 
	 * @return Klartextdarstellung der Form:
	 *  Zug von Turm <Name quelle> nach <Name ziel>
	 */
	public String toString() {
	    return "Zug von Turm " + quelle.name() +
	           " nach " + ziel.name();
	}
	
	/**
	 * Zug auf Zulaessigkeit pruefen
	 */
	public boolean erlaubt() {
	    return (quelle.breiteOben() < ziel.breiteOben())
	         && quelle.scheibenZahl() > 0;
	}
	
	/**
	 * Zug ausführen
	 */
	public boolean ziehen(boolean animiert) {
	    boolean darf = erlaubt();
	    if (darf) {
	        Scheibe s = quelle.gib();
	        if (animiert) {
	            boolean rechts = 
	               quelle.name().equals("A") || 
	               ziel.name().equals("C");
	            int richtung = rechts ? 1 : -1;
	            Dreieck d =
	               new Dreieck(
	                  ziel.xMitte(), quelle.yBasis() + 50,
	                  0, 7, 
	                  richtung * 7, 0,
	                  0, -7);
	            d.neueFarbe("green");
	            d.erscheine();
	            Rechteck r = 
	               new Rechteck(
	                  (quelle.xMitte() + ziel.xMitte()) / 2,
	                  quelle.yBasis() + 54,
	                  Math.abs(quelle.xMitte() - ziel.xMitte()),
	                  7);
	            r.neueFarbe("green");
	            r.erscheine();
	            int aufw = quelle.yPfahlspitze() - 15 - quelle.yObersteScheibe();
	            s.senkrecht(aufw);
	            int waag = ziel.xMitte() - quelle.xMitte();
	            s.waagerecht(waag);
	            int abw  = ziel.yObersteScheibe() - ziel.yPfahlspitze() + 15;
	            s.senkrecht(abw);
	            r.verschwinde();
	            d.verschwinde();
	        }
	        else s.setzeNach(ziel.xMitte(), ziel.yObersteScheibe());
	        ziel.nimm(s);
	    }
	    return darf;
	}
	
	/**
	 * Inverser Zug 
	 */
	public Zug invers() {
	    return new Zug(ziel, quelle);
	}
}
