import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/** An <code>Obstacle<\code> represents anything in
 * Mater Tua that <code>Actors<\code> cannot
 * move through.
 * @author eugenia
 */

public class Obstacle 
{
	private Rectangle hitbox;
	public static final int WIDTH = 10;
	
	/**
	 * Creates an Obstacle object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Obstacle(int x, int y)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
	}
	
	/**
	 * Returns this Obstacle's hitbox.
	 */
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	/**
	 * Draws this Obstacle.
	 * @param g the Graphics object used to draw the Obstacle. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		g.setColor(new Color(204, 128, 47));
		g.drawRect(hitbox.x, hitbox.y, WIDTH, WIDTH);
	}
}
