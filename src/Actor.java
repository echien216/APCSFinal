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
	private int v;
	private int face; //direction actor is facing; 1 = up, 2 = right, 3 = down, 4 = left
	private int hpMax, hpNow;
	private boolean canMove;

	
	public static final int WIDTH = 10;
	public static final int HP_BAR = 20;
	public int basev = 5;
	
	/**
	 * Creates an Actor object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Actor(int x, int y, int hp)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		v = basev;
		face = 1;
		canMove = true;
		hpMax = hp;
		hpNow = hpMax;
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
	 * Moves this Actor horizontally if it can move, and makes this Actor
	 * face in the direction of motion whether it can move or not. 
	 * @param dir direction in which this Actor should move horizontally (-1 = left, 1 = right)
	 * @param solids the other Solids on the screen
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids)
	{	
		if (dir > 0) face = 2;
		else face = 4;
		
		if (dir > 0) hitbox.setBounds(hitbox.x, hitbox.y, WIDTH + v, WIDTH);
		else hitbox.setBounds(hitbox.x - v, hitbox.y, WIDTH + v, WIDTH);
		
		canMove(solids);
		
		System.out.println(hitbox);

		if (canMove)
		{
			if (dir > 0) hitbox.x += v;
			else hitbox.setSize(WIDTH, WIDTH);	
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
		
		System.out.println(hitbox);
		
		if (canMove)
		{
			if (dir > 0) hitbox.y += v;
			hitbox.setSize(WIDTH, WIDTH);	
		}
	}
	
	/**
	 * Resets this Actor's hitbox.
	 */
	public void act() 
	{
		hitbox.setSize(WIDTH, WIDTH);	
		canMove = true;
		
		System.out.println(hitbox);
	}
	
	public void shoot(ArrayList<Solid> solids)
	{
		Projectile p = new Projectile(100, 100, 5, face);
		solids.add(p);
		p.move();
	}
	
	/**
	 * Draws this Actor and its HP bar.
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
		
		g.setColor(Color.RED);
		g.fillRect(hitbox.x - WIDTH / 2, hitbox.y - WIDTH / 2, HP_BAR, 2);
		g.setColor(Color.GREEN);
		g.fillRect(hitbox.x - WIDTH / 2, hitbox.y - WIDTH / 2, (int)(HP_BAR * ((double)(hpNow) / hpMax) + 0.5), 2);		
	}
	
	private ArrayList<Rectangle> getHitboxes(ArrayList<Solid> solids)
	{
		ArrayList<Rectangle> hitboxes = new ArrayList<Rectangle>();

		for(int i = 0; i < solids.size(); i++)
		{
			if (solids.get(i) != null) hitboxes.add(solids.get(i).getHitbox());
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
			if (hitbox != hitboxes.get(i) && hitbox.intersects(hb)) canMove = false;
		}		
	}

}
