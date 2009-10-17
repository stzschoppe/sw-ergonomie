package hanoi;

import java.awt.*;

/**
 * Ein Reckteck auf einem canvas.
 * 
 * @author	Lothar Schmitz nach Michael Kolling und David J. Barnes
 */

public class Rechteck extends Figur {

    private int breite;
    private int hoehe;

    /**
     * Konstruktor.
     */
    public Rechteck(int x, int y, int breite, int hoehe) {
        super();
		this.xPosition = x;
		this.yPosition = y;
		this.breite  = breite;
		this.hoehe = hoehe;
    }

	/*
	 * Zeichne ein Rechteck.
	 */
	protected void zeichne()
	{
		if(istSichtbar) {
			Canvas canvas = Canvas.getCanvas();
			canvas.draw(this, farbe,
						new Rectangle(xPosition-(breite/2), yPosition-hoehe, breite, hoehe));
			canvas.wait(10);
		}
	}
}
