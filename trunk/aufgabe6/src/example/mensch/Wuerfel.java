package mensch;  

/**
 *Simuliert einen W�rfelwurf mit einem Ergebnis von 1 bis 6.
 *@author Marko Bartusch, Alexander Wichmann
 *@version 1
 */

public class Wuerfel {
	
	/** Gibt die kleinste Augenzahl an, die gew�rfelt werden darf (untere Grenze). */
	private static int ug = 1;
	/** Gibt die gr��te Augenzahl an, die gew�rfelt werden darf (obere Grenze). */
    private static int og = 6;
    
    /**leerer Konstruktor*/
    public Wuerfel(){
    }
    
    /**
     *Erzeugt eine Zufallszahl zwischen ug (Untergrenze = 1) 
     *und og (Obergrenze = 6)
     *@return int
     */
    public static int wuerfeln(){
        int ergebnis = ug+(int)(Math.random()* (og - ug + 1));
        System.out.println("wirft eine " + ergebnis);
        return ergebnis;
    }
}
