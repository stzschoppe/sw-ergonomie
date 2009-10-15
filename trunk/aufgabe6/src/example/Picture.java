 


/**
 * This class represents a simple picture. You can draw the picture using
 * the draw method. But wait, there's more: being an electronic picture, it
 * can be changed. You can set it to black-and-white display and back to
 * colors (only after it's been drawn, of course).
 *
 * This class was written as an early example for teaching Java with BlueJ.
 * 
 * @author	Michael Kolling and David J. Barnes
 * @version 1.1  (24 May 2001)
 */
public class Picture
{
	private Square wall;
	private Square window;
	private Triangle roof;
	private	Circle sun;

	/**
	 * Constructor for objects of class Picture
	 */
	public Picture()
	{
		wall = new Square();
		wall.moveVertical(80);
		wall.changeSize(100);
		
		window = new Square();
		window.changeColor("yellow");
		window.moveHorizontal(20);
		window.moveVertical(100);
		
		roof = new Triangle();	
		roof.changeSize(50, 140);
		roof.moveHorizontal(60);
		roof.moveVertical(70);

		sun = new Circle();
		sun.changeColor("yellow");
		sun.moveHorizontal(180);
		sun.moveVertical(-10);
		sun.changeSize(60);
	}

	/**
	 * Draw this picture.
	 */
	public void draw()
	{
		wall.makeVisible();
		window.makeVisible();
		roof.makeVisible();
		sun.makeVisible();
	}

	/**
	 * Change this picture to black/white display
	 */
	public void setBlackAndWhite()
	{
		wall.changeColor("black");
		window.changeColor("white");
		roof.changeColor("black");
		sun.changeColor("black");
		
		this.draw();
	}

	/**
	 * Change this picture to use color display
	 */
	public void setColor()
	{
		wall.changeColor("red");
		window.changeColor("yellow");
		roof.changeColor("green");
		sun.changeColor("yellow");
		
		this.draw();
	}

}
