package mensch; 

/**
 * Klasse Pfeil erstellt einen Pfeil vom aktuellen Ort des Spielsteins an seinen Zielort.
 * 
 * @author Arbeitsgruppe Grafik
 * @version 20.06.2004
 */

public class Pfeil
{
    
    /**
     * Der Konstruktor Pfeil zeichnet auf dem aktiven Canvas einen grünen Pfeil von einem
     * Startfeld zum Zielfeld. Nach 1.5 Sekunden wird der Pfeil wieder vom
     * Bildschirm gelöscht.<P>
     * 
     * <TT>Nummerierung der Punkte:<BR>&nbsp;<BR>
     * 
     * &nbsp;&nbsp; 4<BR>
     * &nbsp;&nbsp; /\<BR>
     * &nbsp; /&nbsp; \<BR>
     * 3/_&nbsp; _\5<BR>
     * &nbsp; 2II6<BR>
     * &nbsp;&nbsp; II<BR>
     * &nbsp;&nbsp; II<BR>
     * &nbsp; 1&nbsp; 7</TT>
     **/
    public Pfeil (Feld Start, Feld Ziel, int radius) {        
      // neues Polygon mit dem Namen p anlegen
      java.awt.Polygon p = new java.awt.Polygon();
        
      // Die Koordinaten aus den Feldern extrahieren.
      int x1 = (int)Start.scheibe.getOrt().getX(); // Ausgangs...
      int y1 = (int)Start.scheibe.getOrt().getY(); // Koordinaten
      int x2 = (int)Ziel.scheibe.getOrt().getX();  // Ziel...
      int y2 = (int)Ziel.scheibe.getOrt().getY();  // Koordinaten

      // Satz von Pytagoras
      double delta_x = x2-x1;
      double delta_y = y2-y1;
      double laenge = Math.sqrt(delta_x*delta_x + delta_y*delta_y);
      
      // x3 erzeugen mit Abstand radius von x2
      int x3 = erzeugeX3(x1, x2, laenge, radius);
      int y3 = erzeugeY3(y1, y2, laenge, radius);

      // Die Punkte für das Polygon werden gesetzt      
      double sx = -1*delta_y/laenge;
      double sy = delta_x/laenge;
      double breite = radius / 2;
      p.addPoint(x1-calc(breite,sx), y1-calc(breite,sy)); //Punkt 1
      p.addPoint(x3-calc(breite,sx), y3-calc(breite,sy)); //Punkt 2
      p.addPoint(x3-calc(breite*2,sx), y3-calc(breite*2, sy)); //Punkt 3
      p.addPoint(x2, y2); //Punkt 4
      p.addPoint(x3+calc(breite*2,sx)  , y3+calc(breite*2,sy)); //Punkt 5
      p.addPoint(x3+calc(breite,sx)  , y3+calc(breite,sy)); //Punkt 6
      p.addPoint(x1+calc(breite,sx), y1+calc(breite,sy)); //Punkt 7

      // Bereite Canvas vor
      Canvas Leinwand = Canvas.getCanvas(radius);
      
      // Zeichne p auf der Leinwand
      Leinwand.draw(this,"green",p);
      
      // Warte 1.5 Sekunden
      Leinwand.wait(1500);
        
      // Nimm die Pfeile wieder weg  
      Leinwand.erase(this);        
    } // Ende des Konstruktors
    

    /**
     * Berechnen der Variablen für den senkrechten Einheitsvektor
     */
    private int calc(double var, double svar) {
        return (int) (var*svar);
    }
    
    /**
     * Berechnung der Variable <TT>x3</TT> als Bezugspunkt für den Pfeil
     */
    private int erzeugeX3(int x1, int x2, double laenge, int radius) {
        int x3 = (int)(((x1-x2)/laenge*radius)+x2);
        return x3;
    }
    
    /**
     * Berechnung der Variable <TT>y3</TT> als Bezugspunkt für den Pfeil     
     */
    private int erzeugeY3(int y1, int y2, double laenge, int radius) {
        int y3 = (int)(((y1-y2)/laenge*radius)+y2);
        return y3;
    }    
   
} // End of Pfeil :-)