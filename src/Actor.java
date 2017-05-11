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
	private int hpMax, hpNow;
	private boolean canMove;
	
	public static final int WIDTH = 10;
	
	/**
	 * Creates an Actor object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public Actor(int x, int y, int hp)
	{
		hitbox = new Rectangle(x, y, WIDTH, WIDTH);
		vx = 5;
		vy = 5;
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
	
	private int canMove(ArrayList<Solid> solids, ArrayList<Actor> actors)
	{
		ArrayList<Rectangle> hb1 = new ArrayList<Rectangle>();
		ArrayList<Rectangle> hb2 = new ArrayList<Rectangle>();
		int x = 0;
		
		for(int i = 0; i < solids.size(); i++)
		{
			if (solids.get(i) != null)hb1.add(solids.get(i).getHitbox());
		}
		
		for(int i = 0; i < hb1.size(); i++)
		{
			if (hitbox != hb1.get(i) && hitbox.intersects(hb1.get(i)))
			{
				canMove = false;
				x = hitbox.x - hb1.get(i).x;
			}
		}
		
		for(int i = 0; i < actors.size(); i++)
		{
			if (actors.get(i) != null) hb1.add(actors.get(i).getHitbox());
		}
		
		for(int i = 0; i < hb2.size(); i++)
		{
			if (hitbox != hb2.get(i) && hitbox.intersects(hb2.get(i)))
			{
				canMove = false;
				if (hitbox.x - hb2.get(i).x < x) x = hitbox.x - hb2.get(i).x ;
			}
		}
		
		return x;
	}
	
	/**
	 * Moves this Actor horizontally if it can move, and makes this Actor
	 * face in the direction of motion whether it can move or not. 
	 * @param dir direction in which this Actor should move horizontally (-1 = left, 1 = right)
	 * @param solids the other Solids on the screen (not including Actors)
	 * @param actors the other Actors on the screen
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids, ArrayList<Actor> actors)
	{	
		if (dir > 0) face = 2;
		else face = 4;
		
		hitbox = new Rectangle(hitbox.x, hitbox.y, WIDTH + dir * vx, WIDTH);
		int x = canMove(solids, actors);
		
		if (canMove) hitbox.x += dir * vx;
		else hitbox.x += dir * x;
	}
		
	/**
	 * Moves this Actor vertically if it can move, and makes this Actor
	 * face in the direction of motion whether it can move or not. 
	 * @param dir direction in which this Actor should move vertically (-1 = up, 1 = down)	 
	 * @param solids the other Solids on the screen (not including Actors)
	 * @param actors the other Actors on the screen
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids, ArrayList<Actor> actors)
	{	
		if (dir > 0) face = 1;
		else face = 3;
		
		hitbox = new Rectangle(hitbox.x, hitbox.y, WIDTH, WIDTH + dir * vy);
		int x = canMove(solids, actors);
		
		if (canMove) hitbox.y += dir * vy;
		else hitbox.x += dir * x;
	}
	
	/**
	 * Resets this Actor's hitbox, and "pushes" it out of any Solids or Actors.
	 */
	public void act(ArrayList<Solid> solids, ArrayList<Actor> actors) 
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
