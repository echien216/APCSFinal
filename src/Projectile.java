import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** A <code>Proejctile<\code> represents anything in
 * Mater Tua that is fired by Actors towards other Actors,
 * and deals damage to Actors on contact.
 * 
 * @author christine
 */

public class Projectile implements Solid
{
	private double v;
	private int face;
	private Rectangle hitbox;
	
	public static final int WIDTH = 5;
	public static final int BASEV = 30;

	/**
	 * Creates a Projectile object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels. It moves in
	 * the given direction
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param face the direction in which this Projectile will be fired (1 = up, 2 = right, 3 = down, 4 = left)
	 */
	public Projectile(int x, int y, int face){
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		this.v = BASEV;
		this.face = face;
	}
	
	/**
	 * Returns this Projectile's hitbox used for
	 * collision detection.
	 */
	public Rectangle getHitbox() 
	{
		return null;
	}

	/**
	 * Draws this Projectile.
	 * @param g the Graphics object used to draw the Projectile. Must not be null. 
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(hitbox.x ,hitbox.y, WIDTH, WIDTH);
	}
	
	/**
	 * Does nothing because movement is handled by act().
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids) {
		
	}

	/**
	 * Does nothing because movement is handled by act().
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids) {
		
	}

	/**
	 * Moves this Projectile in the direction it was fired in. 
	 */
	public void act() //needs motion hitbox + corresponding reset
	{
		if (face == 1) hitbox.y -= v;
		if (face == 2) hitbox.x += v;
		if (face == 3) hitbox.y += v;
		if (face == 4) hitbox.x -= v;
	}
	
	/**
	 * Detects if this Projectile hit anything, and 
	 * handles interactions accordingly. If it did
	 * not hit anything, it will continue to move in the 
	 * direction in which it was fired.
	 */
	public void detect(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = getHitboxes(solids);
		
		for(int i = 0; i < hitboxes.size(); i++)
		{
			Solid s = solids.get(i);
			Rectangle hb = hitboxes.get(i);

			if (hitbox.intersects(hb))
			{
				if (s instanceof Actor)
				{
					hitbox.setBounds(-10, -10, 0, 0);
					((Actor)s).changeHp(-5);
				}
				else if (s instanceof Obstacle) hitbox.setBounds(-10, -10, 0, 0);
			}
		}
		
		act();
	}
	
	private ArrayList<Rectangle> getHitboxes(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();

		for(int i = 0; i < solids.size(); i++)
		{
			hitboxes.add(solids.get(i).getHitbox());
		}
		
		return hitboxes;
	}
}
