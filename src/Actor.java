import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/** An <code>Actor<\code> represents anything in
 * Mater Tua that can move.
 * 
 * @author eugenia
 */

public class Actor 
{
	private Rectangle hitbox;
	private double vx, vy;
	private int face; //direction actor is facing; 1 = up, 2 = right, 3 = down, 4 = left
	
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
	 * Moves this Actor horizontally, and makes this Actor
	 * face in the direction of motion. 
	 * @param x amount this Actor should move horizontally (- = left, + = right)
	 */
	public void moveHorizontal(int dir)
	{
		hitbox.x += dir * 10;
		
		if (dir > 0) face = 2;
		else face = 4;
	}
	
	/**
	 * Moves this Actor vertically, and makes this Actor
	 * face in the direction of motion. 
	 * @param y amount this Actor should move vertically (-= up, += down)
	 */
	public void moveVertical(int dir)
	{
		hitbox.y += dir * 10;
		
		if (dir > 0) face = 3;
		else face = 1;
	}
	
	/**
	 * Draws this Actor.
	 * @param g the Graphics object used to draw the Actor. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(hitbox.x, hitbox.y, WIDTH, WIDTH);
		
		if (face == 1) g.drawRect(hitbox.x, hitbox.y, WIDTH, WIDTH / 2);
		if (face == 2) g.drawRect(hitbox.x + WIDTH / 2, hitbox.y, WIDTH / 2, WIDTH);
		if (face == 3) g.drawRect(hitbox.x, hitbox.y + WIDTH / 2, WIDTH, WIDTH / 2);
		if (face == 4) g.drawRect(hitbox.x, hitbox.y, WIDTH / 2, WIDTH);

	}
}
