import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** 
 * A <code>Proejctile<\code> represents anything in
 * Mater Tua that is fired by Actors towards other Actors,
 * and deals damage to Actors on contact. Projectiles cannot
 * damage the Actor that fired it.
 * 
 * @author eugenia
 */

public class Projectile implements Solid
{
	private int v;
	private int face;
	private int atk;
	private Rectangle hitbox;
	private Actor a;
	private boolean status;
	
	/** Width of the Actor when drawn */
	public static final int WIDTH = 5; 
	
	/** Projectile's base movement speed */
	public static final int BASEV = 50;

	/**
	 * Creates a Projectile object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels. It moves in
	 * the given direction
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param face the direction in which this Projectile will be fired (1 = up, 2 = right, 3 = down, 4 = left)
	 * @param atk how much damage this Projectile does to Actors on contact
	 * @param aHitbox the Actor that fired this Projectile
	 */
	public Projectile(int x, int y, int face, int atk, Actor a)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		this.v = BASEV;
		this.face = face;
		this.atk = atk;
		this.a = a;
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
	 * Does nothing.
	 */
	public void act() 
	{
		status = true;
	}
	
	private void moveHorizontal(int dir, ArrayList<Solid> solids) 
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

	private void moveVertical(int dir, ArrayList<Solid> solids) 
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
	
	private void detect(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = getHitboxes(solids);
		int d1 = 999999999, d2 = 0, j = 0;
		boolean b = false;
		
		for(int i = 0; i < hitboxes.size(); i++)
		{
			Solid s = solids.get(i);
			Rectangle hb = hitboxes.get(i);

			if (s != a && hitbox.intersects(hb))
			{
				b = true;
				
				if (face == 1 || face == 3) d2 = Math.abs(hitbox.y - hb.y);
				else d2 = Math.abs(hitbox.x - hb.x);
				
				if (d2 < d1)
				{
					j = i;
					d1 = d2;
				}
			}
		}
		
		if (b)
		{
			hitbox.setBounds(-10, -10, 0, 0);
			
			Solid s = solids.get(j);
			Rectangle hb = hitboxes.get(j);
			
			if ((a instanceof Enemy && s instanceof Player) || (a instanceof Player && s instanceof Enemy)) ((Actor)s).changeHP(-atk);
			
			if (face == 4) hitbox.x = hb.x + WIDTH;
			if (face == 1) hitbox.y = hb.y + WIDTH;
			status = false;
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
