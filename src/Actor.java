import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** 
 * An <code>Actor</code> represents anything in
 * Mater Tua that can move.
 * 
 * @author eugenia
 */

public class Actor implements Solid
{
	private Rectangle hitbox;
	private int v;
	private int face; //direction actor is facing; 1 = up, 2 = right, 3 = down, 4 = left
	private int hpMax, hpNow;
	private int atk;
	private boolean canMove;
	private boolean status; //true = alive, false = dead;
	
	public static final int WIDTH = 10;
	public static final int HP_BAR = 20;
	public static final int BASEV = 5;
	
	/**
	 * Creates an Actor object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param hp this Actor's maximum HP
	 * @param atk this Actor's attack value
	 */
	public Actor(int x, int y, int hp, int atk)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		v = BASEV;
		face = 1;
		canMove = true;
		hpMax = hp;
		hpNow = hpMax;
		this.atk = atk;
		status = true;
	}
	
	/**
	 * Returns this Actor's hitbox used for
	 * collision detection.
	 */
	public Rectangle getHitbox()
	{
		return hitbox;
	}
	
	/**
	 * Returns this Actor's status (true = alive, false = dead).
	 */
	public boolean getStatus()
	{
		return status;
	}
	
	/**
	 * Returns this Actor's current HP.
	 */
	public int getCurrentHP()
	{
		return hpNow;
	}
	
	/**
	 * Returns this Actor's maximum HP.
	 */
	public int getMaxHP()
	{
		return hpMax;
	}
	
	/**
	 * Returns the direction in which this Actor
	 * is currently facing (1 = up, 2 = right, 3 = down, 4 = left).
	 */
	public int getFace()
	{
		return face;
	}
	
	/**
	 * Returns this Actor's attack value.
	 */
	public int getAtk()
	{
		return atk;
	}
	
	/**
	 * Changes this Actor's HP by the amount specified, and updates
	 * its status accordingly. This Actor's HP cannot exceed its max HP. 
	 * @param change amount this Actor's current HP should be changed by
	 */
	public void changeHp(int change)
	{
		hpNow += change;
		if (hpNow > hpMax) hpNow = hpMax;
		if (hpNow <= 0) status = false;
	}
		
	/**
	 * Moves this Actor horizontally if it can move, and makes this Actor
	 * face in the direction of motion whether it can move or not. 
	 * @param dir direction in which this Actor should move horizontally (-1 = left, 1 = right)
	 * @param solids the other Solids on the screen
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids)
	{	
		if (dir > 0) face = 2;
		else face = 4;
		
		System.out.println(dir);
		
		if (dir > 0) hitbox.setBounds(hitbox.x, hitbox.y, WIDTH + v, WIDTH);
		else hitbox.setBounds(hitbox.x - v, hitbox.y, WIDTH + v, WIDTH);
		
		canMove(solids);

		
		if (canMove)
		{
			if (dir > 0) hitbox.x += v;
			else hitbox.setBounds(hitbox.x, hitbox.y, WIDTH, WIDTH);
		} 
	}
		
	/**
	 * Moves this Actor vertically if it can move, and makes this Actor
	 * face in the direction of motion whether it can move or not. 
	 * @param dir direction in which this Actor should move vertically (-1 = up, 1 = down)	 
	 * @param solids the other Solids on the screen
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids)
	{	
		if (dir > 0) face = 3;
		else face = 1;
		
		if (dir > 0) hitbox.setBounds(hitbox.x, hitbox.y, WIDTH, WIDTH + v);
		else hitbox.setBounds(hitbox.x, hitbox.y - v, WIDTH, WIDTH + v);
		
		canMove(solids);
				
		if (canMove)
		{
			if (dir > 0) hitbox.y += v;
			else hitbox.setBounds(hitbox.x, hitbox.y, WIDTH, WIDTH);
		}
	}
	
	/**
	 * Resets this Actor's hitbox, and moves it offscreen if it is dead.
	 */
	public void act() 
	{
		hitbox.setSize(WIDTH, WIDTH);	
		canMove = true;		
	}
	
	/**
	 * Performs this Actor's 1st skill, which fires a Projectile
	 * in the direction it is currently facing.
	 * @param projectiles the other projectiles in the game
	 * @param solids the other solids on the screen
	 */
	public void skill1(ArrayList<Projectile> projectiles, ArrayList<Solid> solids)
	{
		Projectile p = null;
		
		if (face == 1) p = new Projectile(hitbox.x + WIDTH / 2, hitbox.y, face, atk);
		if (face == 2) p = new Projectile(hitbox.x + WIDTH, hitbox.y + WIDTH / 2, face, atk);
		if (face == 3) p = new Projectile(hitbox.x + WIDTH / 2, hitbox.y + WIDTH, face, atk);
		if (face == 4) p = new Projectile(hitbox.x, hitbox.y + WIDTH / 2, face, atk);
		
		if (p != null)
		{
			projectiles.add(p);
			
			if (face == 1) p.moveVertical(-1, solids);
			if (face == 2) p.moveHorizontal(1, solids);
			if (face == 3) p.moveVertical(1, solids);
			if (face == 4) p.moveHorizontal(-1, solids);
		}
	}
	
	/**
	 * Draws this Actor and its HP bar.
	 * @param g the Graphics object used to draw the Actor. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		if (status)
		{
			g.setColor(Color.BLACK);
			g.fillRect(hitbox.x, hitbox.y, WIDTH, WIDTH);
			
			g.setColor(Color.CYAN);
			
			if (face == 1) g.fillRect(hitbox.x, hitbox.y, WIDTH, WIDTH / 2);
			if (face == 2) g.fillRect(hitbox.x + WIDTH / 2, hitbox.y, WIDTH / 2, WIDTH);
			if (face == 3) g.fillRect(hitbox.x, hitbox.y + WIDTH / 2, WIDTH, WIDTH / 2);
			if (face == 4) g.fillRect(hitbox.x, hitbox.y, WIDTH / 2, WIDTH);
			
			g.setColor(Color.RED);
			g.fillRect(hitbox.x - WIDTH / 2, hitbox.y - WIDTH / 2, HP_BAR, 2);
			g.setColor(Color.GREEN);
			g.fillRect(hitbox.x - WIDTH / 2, hitbox.y - WIDTH / 2, (int)(HP_BAR * ((double)(hpNow) / hpMax) + 0.5), 2);	
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
	
	private void canMove(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = getHitboxes(solids);
		
		for(int i = 0; i < hitboxes.size(); i++)
		{
			Rectangle hb = hitboxes.get(i);
			System.out.println(hitbox.intersects(hb) + " " + hitbox);
			System.out.println(hb);
			if (hitbox != hb && hitbox.intersects(hb))
			{				
				if (face == 4) hitbox.x = hb.x + WIDTH;
				if (face == 1) hitbox.y = hb.y + WIDTH;
				
				canMove = false;
			}
		}

	}
}
