package hanoi;

import java.awt.*;

/**
 * Ein Dreieck auf einem canvas.
 * 
 * @author Lothar Schmitz
 */

public class Dreieck extends Figur {
    
    int x1, x2, x3, y1, y2, y3;

    /**
     * Konstruktor.
     */
    public Dreieck(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3) {
        super();
		xPosition = x;
		yPosition = y;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.x3 = x3;
		this.y3 = y3;
		farbe = "green";
    }

	/*
	 * Das Dreieck zeichnen.
	 */
	protected void zeichne()
	{
		if(istSichtbar) {
			Canvas canvas = Canvas.getCanvas();
			int[] xpoints = { xPosition+x1, xPosition+x2, xPosition+x3 };
			int[] ypoints = { yPosition+y1, yPosition+y2, yPosition+y3 };
			canvas.draw(this, farbe, new Polygon(xpoints, ypoints, 3));
			canvas.wait(10);
		}
	}

}
