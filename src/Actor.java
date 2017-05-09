import java.awt.Graphics;
import java.awt.Rectangle;

/** An <code>Actor<\code> represents anything in
 * Mater Tua that can move.
 * @author eugenia
 */

public class Actor 
{
	private Rectangle hitbox;
	private double vx, vy;
	
	public static final int WIDTH = 10;
	
	/**
	 * Creates an Actor object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Actor(int x, int y)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		vx = 0;
		vy = 0;
	}
	
	/**
	 * Returns this Actor's hitbox.
	 */
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	/**
	 * 
	 */
	public void move()
	{
		
	}
	
	/**
	 * Draws this Actor.
	 * @param g the Graphics object used to draw the Actor. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		
	}
}
