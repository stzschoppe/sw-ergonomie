package mensch;  

/**
 * Felder des Mensch-Ärgere-Dich-Nicht-Spiels.
 * 
 * @author (Lothar Schmitz) 
 * @version (20.06.04)
 */
public class Feld {

    private Figur figur;
    private Feld vorgaenger;
    private Feld nachfolger;
    /**
     * Das Feld bekommt eine Scheibe auf dem Canvas zugeordnet.
     */    
    public Scheibe scheibe;
    
    /** 
     * Scheibenobject
     */

    
    /**
     * Konstruktor für Feld-Objekte
     */
    public Feld(Figur f) {
        figur      = f;
        vorgaenger = null;
        nachfolger = null;
    }

    /**
     * Konstruktor für Feld-Objekte
     */
    public Feld() {
        this(null);
    }

    /**
     * getter für Vorgaenger
     */
    public Feld getVorgaenger() {
        return vorgaenger;
    }

    /**
     * getter für Nachfolger
     */
    public Feld getNachfolger() {
        return nachfolger;
    }

    /**
     * getter für Figur
     */
    public Figur getFigur() {
        return figur;
    }

    /**
     * setter für Vorgaenger
     */
    public void setVorgaenger(Feld v) {
        vorgaenger = v;
    }

    /**
     * setter für Nachfolger
     */
    public void setNachfolger(Feld n) {
        nachfolger = n;
    }

    /**
     * setter für Figur
     */
    public void setFigur(Figur f) {
        figur = f;
    }

    /**
     * Standardmethode 
     */
    public String toString() {
        return figur == null ? "." : figur.getSpieler().farbAnf();
    }
}
