import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** 
 * A <code>Proejctile<\code> represents anything in
 * Mater Tua that is fired by Actors towards other Actors,
 * and deals damage to Actors on contact.
 * 
 * @author christine
 */

public class Projectile implements Solid
{
	private int v;
	private int face;
	private int atk;
	private Rectangle hitbox, aHitbox;
	private boolean status;
	
	public static final int WIDTH = 5;
	public static final int BASEV = 30;

	/**
	 * Creates a Projectile object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels. It moves in
	 * the given direction
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param face the direction in which this Projectile will be fired (1 = up, 2 = right, 3 = down, 4 = left)
	 * @param atk how much damage this Projectile does to Actors on contact
	 * @param aHitbox the hitbox of the Actor that fired this Projectile
	 */
	public Projectile(int x, int y, int face, int atk, Rectangle aHitbox)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		this.v = BASEV;
		this.face = face;
		this.atk = atk;
		this.aHitbox = aHitbox;
		status = true;
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
	 * Returns this Projectile's status (true = in screen, false = not in screen)
	 * @return
	 */
	public boolean getStatus()
	{
		return status;
	}

	/**
	 * Draws this Projectile.
	 * @param g the Graphics object used to draw the Projectile. Must not be null. 
	 */
	public void draw(Graphics g) 
	{
		if (status)
		{
			g.setColor(Color.BLACK);
			g.fillRect(hitbox.x ,hitbox.y, WIDTH, WIDTH);
		}
	}
	
	/**
	 * Moves this Projectile in the direction in which it was fired.
	 * This is the movement-related method that should be called by
	 * other objects besides Actor.
	 * @param solids the other Solids on the screen
	 */
	public void move(ArrayList<Solid> solids)
	{
		if (face == 1) moveVertical(-1, solids);
		if (face == 2) moveHorizontal(1, solids);
		if (face == 3) moveVertical(1, solids);
		if (face == 4) moveHorizontal(-1, solids);
	}
	
	/**
	 * Moves this Projectile horizontally in the direction specified.
	 * This method should only be called by the Actor that fired it.
	 * @param dir direction in which this Actor should move horizontally (-1 = left, 1 = right)	 
	 * @param solids the other Solids on the screen
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids) 
	{		
		if (dir > 0) hitbox.setBounds(hitbox.x, hitbox.y, WIDTH + v, WIDTH);
		else hitbox.setBounds(hitbox.x - v, hitbox.y, WIDTH + v, WIDTH);

		detect(solids);
		
		if (status)
		{
			if (dir > 0) hitbox.x += v;
			else hitbox.setBounds(hitbox.x, hitbox.y, WIDTH, WIDTH);
		} 
	}

	/**
	 * Moves this Projectile vertically in the direction specified.
	 * This method should only be called by the Actor that fired it
	 * @param dir direction in which this Actor should move vertically (-1 = up, 1 = down)	
	 * @param solids the other Solids on the screen 
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids) 
	{
		if (dir > 0) hitbox.setBounds(hitbox.x, hitbox.y, WIDTH, WIDTH + v);
		else hitbox.setBounds(hitbox.x, hitbox.y - v, WIDTH, WIDTH + v);
		
		detect(solids);
				
		if (status)
		{
			if (dir > 0) hitbox.y += v;
			else hitbox.setBounds(hitbox.x, hitbox.y, WIDTH, WIDTH);
		}
	}
	
	/**
	 * Does nothing.
	 */
	public void act() 
	{
		status = true;
	}
	
	private void detect(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = getHitboxes(solids);
		
		for(int i = 0; i < hitboxes.size(); i++)
		{
			Solid s = solids.get(i);
			Rectangle hb = hitboxes.get(i);

			if (hb != aHitbox && hitbox.intersects(hb))
			{
				//System.out.println("hi");
				hitbox.setBounds(-10, -10, 0, 0);
				
				if (s instanceof Actor)
				{
					//System.out.println("ow");
					((Actor)s).changeHP(-atk);
				}
				
				if (face == 4) hitbox.x = hb.x + WIDTH;
				if (face == 1) hitbox.y = hb.y + WIDTH;
				status = false;
			}
		}
		
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
