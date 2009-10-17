package mensch;  

/**
 * Felder des Mensch-�rgere-Dich-Nicht-Spiels.
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
     * Konstruktor f�r Feld-Objekte
     */
    public Feld(Figur f) {
        figur      = f;
        vorgaenger = null;
        nachfolger = null;
    }

    /**
     * Konstruktor f�r Feld-Objekte
     */
    public Feld() {
        this(null);
    }

    /**
     * getter f�r Vorgaenger
     */
    public Feld getVorgaenger() {
        return vorgaenger;
    }

    /**
     * getter f�r Nachfolger
     */
    public Feld getNachfolger() {
        return nachfolger;
    }

    /**
     * getter f�r Figur
     */
    public Figur getFigur() {
        return figur;
    }

    /**
     * setter f�r Vorgaenger
     */
    public void setVorgaenger(Feld v) {
        vorgaenger = v;
    }

    /**
     * setter f�r Nachfolger
     */
    public void setNachfolger(Feld n) {
        nachfolger = n;
    }

    /**
     * setter f�r Figur
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
