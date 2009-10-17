package mensch;  

/**
 * Figuren des Mensch-Ärgere-Dich-Nicht-Spiels.
 * 
 * @author (Lothar Schmitz) 
 * @version (20.06.04)
 */

public class Figur {

    private Feld ort;
    private Feld heimat;
    private Spieler besitzer;
    private int fortschritt;
    private int nummer;
    /**
     * Die Figur bekommt eine Scheibe auf dem Canvas zugeordnet.
     */
    public Scheibe scheibe;
    /**
     * Die Figur bekommt eine Pfeil zum Ziehen zugeordnet
     */ 
    public Pfeil pfeil;
  
    /**
     * Konstruktor für Figur-Objekte
     */
    public Figur(Spieler s, Feld nest, int n) {
        besitzer    = s;
        heimat      = nest;
        ort         = nest;
        fortschritt = -5;
        nummer      = n;
    }

    /**
     * getter für Spieler
     */
    public Spieler getSpieler() {
        return besitzer;
    }

    /**
     * getter für Nummer
     */
    public int getNummer() {
        return nummer;
    }

    /**
     * getter für Ort
     */
    public Feld getOrt() {
        return ort;
    }

    /**
     * setter für Ort
     */
    public void setOrt(Feld v) {
        if (ort != null)
          getOrt().setFigur(null);
        ort = v;
        v.setFigur(this);
    }

    public Feld getHeimat(){
        return heimat;
    }

    /**
     * Fortschritt erhöhen
     */
    public void erhoeheFortschritt(int weiter) {
        fortschritt += weiter;
    }

    /**
     * zurück ins Nest (wenn geschlagen)
     */
    public void insNest() {
        Feld temp = getOrt();
        setOrt(heimat);
        fortschritt = -5;
        // Grafik abarbeiten
        if (scheibe != null) {
            pfeil = new Pfeil(temp,heimat,scheibe.getRadius()+1);
            pfeil = null;
            System.gc();
            scheibe.move(heimat);
        } // Ende der Grafik
    }

    /**
     * im Nest?
     */
    public boolean imNest() {
        return getOrt() == heimat;
    }

    /**
     * Zugziel ermitteln
     */
    public Feld weiter(int n) {
        Feld aktuell = null;
        if (fortschritt == -5 && n == 6) {
            return besitzer.ansetzFeld();}
        else if (fortschritt == -5)
            return null;
        else if (fortschritt > 0 && fortschritt + n <= 48) {
            aktuell = getOrt();
            for (int i = n; i > 0; i--) {
                aktuell = aktuell.getNachfolger();
            };
            return aktuell;
           
        }
        else if (fortschritt + n > 52)
            return null;
        else {
            aktuell = besitzer.hausEingang();
            for (int j = fortschritt + n - 49; j > 0; j--) {
                aktuell = aktuell.getNachfolger();
            };
            return aktuell;
        }
    }
    
    /**
     * Standardmethode 
     */
    public String toString() {
        String ergebnis = 
           "<" + besitzer.farbAnf() + ":" +
           nummer + ":" + fortschritt + ">";
        int anz  = fortschritt < 49 ? 10 : fortschritt - 49;
        Feld aktuell = getOrt().getVorgaenger();
        while (aktuell != null && anz > 0) {
            ergebnis = aktuell + ergebnis;
            aktuell = aktuell.getVorgaenger();
            anz--;
        };
        anz  = 10;
        aktuell = getOrt().getNachfolger();
        while (aktuell != null && anz > 0) {
            ergebnis = ergebnis + aktuell;
            aktuell = aktuell.getNachfolger();
            anz--;
        };
        return ergebnis;
    }
}
