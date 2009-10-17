package mensch;  

/**
 * Diese Klasse zeichnet, bewegt und löscht Scheiben in Kreisform auf
 * dem Canvas mit dem Namen Leinwand.
 *
 * @author Arbeitsgruppe Grafik
 * @version 20.06.2004
 */

public class Scheibe {
   // Private Variablen der Scheibe
    private Koord Ort;
    private String Farbe;
    private int Durchmesser;
    public int size;
    private int Radius;

    /**
     * Konstruktor der Scheibe<BR>
     * Die Scheibe wird das erste mal auf dem Canvas dargestellt.
     * An den Kontruktor müssen ein Satz Koordinaten vom typ <TT>Koord</TT>,
     * die Farbe als <TT>String</TT> und der Radius als <TT>Integer</TT>
     * übergeben werden
     */
    public Scheibe(Koord kor, String farb, int rad) {

        this.Ort = kor;
        this.Farbe = farb;
        this.Durchmesser = 2*rad;
        this.Radius = rad;

        Canvas Leinwand = Canvas.getCanvas(Radius);
        Leinwand.draw(this, Farbe, new java.awt.geom.Ellipse2D.Double(
            this.Ort.getX()-(Durchmesser/2), // X-Mittelpunkt
            this.Ort.getY()-(Durchmesser/2), // Y-Mittelpunkt
            this.Durchmesser,this.Durchmesser)); // Ausdehnung in X- und Y-Richtung
    }

    /**
     * Zuerst wird das Objekt vom Canvas gelöscht.<BR>
     * Danach wird das Objekt an den Koordinaten des übergebenen
     * Feldes neu dargestellt.<P>
     * 
     * <TT>feld</TT> ist das Zielfeld
     */
    public void move(Feld feld) {
        
        if (feld.scheibe != null) Ort = feld.scheibe.getOrt();
        
        Canvas Leinwand = Canvas.getCanvas(Radius);

        Leinwand.draw(this, Farbe, new java.awt.geom.Ellipse2D.Double(
            this.Ort.getX()-(this.Radius), // X-Mittelpunkt
            this.Ort.getY()-(this.Radius), // Y-Mittelpunkt
            this.Durchmesser,this.Durchmesser)); // Ausdehnung in X- und Y-Richtung

    }

    /**
     * Das Objekt wird vom Canvas entfernt.
     * 
     * Um das Objekt neu zu zeichnen, muss die Methode <TT>move</TT> ausgefuehrt werden.
     */
    public void loesche() {
        Canvas Leinwand = Canvas.getCanvas(Radius);
        Leinwand.erase(this);
    }

    /**
     * Die Koordinaten des Objekts werden gesetzt.
     */
    public void setOrt(Koord a) {
        this.Ort = a;
    }
    
    /**
     * Die Koordinaten des Objekts werden ausgelesen und zuruechgegeben
     */
    public Koord getOrt() {
        return this.Ort;
    }
    /**
     * Die Radius der Scheibe wird ausgegeben
     */
    public int getRadius() {
        return Radius;
    }
}
