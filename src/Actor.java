import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** An <code>Actor<\code> represents anything in
 * Mater Tua that can move.
 * 
 * @author eugenia
 */

public class Actor implements Solid
{
	private Rectangle hitbox;
	private int vx, vy;
	private int face; //direction actor is facing; 1 = up, 2 = right, 3 = down, 4 = left
	private boolean canMove;
	
	public static final int WIDTH = 10;
	
	/**
	 * Creates an Actor object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Actor(int x, int y)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		vx = 5;
		vy = 5;
		face = 1;
		canMove = true;
	}
	
	/**
	 * Returns this Actor's hitbox used for
	 * collision detection.
	 */
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	private void canMove(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();
		
		for(int i = 0; i < solids.size(); i++)
		{
			hitboxes.add(solids.get(i).getHitbox());
		}
		
		for(int i = 0; i < hitboxes.size(); i++)
		{
			if (hitbox != solids.get(i) && hitbox.intersects(hitboxes.get(i))) canMove = false;;
		}
		
		canMove = true;;
	}
	
	/**
	 * Moves this Actor horizontally if it can move, and makes this Actor
	 * face in the direction of motion. 
	 * @param dir direction in which this Actor should move horizontally (-1 = left, 1 = right)
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids)
	{	
		if (dir > 0) face = 2;
		else face = 4;
		
		hitbox = new Rectangle(hitbox.x, hitbox.y, WIDTH + dir * vx, WIDTH);

		hitbox.x += dir * vx;
	}
		
	/**
	 * Moves this Actor vertically if it can move, and makes this Actor
	 * face in the direction of motion. 
	 * @param dir direction in which this Actor should move vertically (-1 = up, 1 = down)
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids)
	{	
		if (dir > 0) face = 1;
		else face = 3;
		
		hitbox.y += dir * vy;
				
		hitbox = new Rectangle(hitbox.x, hitbox.y, WIDTH, WIDTH + vy);
	}
	
	/**
	 * Resets this Actor's hitbox.
	 */
	public void act() 
	{
		hitbox = new Rectangle(hitbox.x, hitbox.y, WIDTH, WIDTH);
		canMove = true;
	}
	
	/**
	 * Draws this Actor.
	 * @param g the Graphics object used to draw the Actor. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(hitbox.x, hitbox.y, WIDTH, WIDTH);
		
		g.setColor(Color.CYAN);
		
		if (face == 1) g.fillRect(hitbox.x, hitbox.y, WIDTH, WIDTH / 2);
		if (face == 2) g.fillRect(hitbox.x + WIDTH / 2, hitbox.y, WIDTH / 2, WIDTH);
		if (face == 3) g.fillRect(hitbox.x, hitbox.y + WIDTH / 2, WIDTH, WIDTH / 2);
		if (face == 4) g.fillRect(hitbox.x, hitbox.y, WIDTH / 2, WIDTH);
	}
}
