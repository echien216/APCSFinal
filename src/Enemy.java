import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/** 
 * An <code>Enemy</code> represents anything in
 * Mater Tua that can deal damage to the Player, 
 * and that the Player must ("must" for those with
 * godlike mechanics, theoretically) kill to advance.
 * 
 * @author eugenia
 */

public class Enemy extends Actor
{
	/**
	 * Creates an Enemy object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param hp this Enemy's maximum HP
	 * @param atk this Enemy's attack value
	 */
	public Enemy(int x, int y, int hp, int atk) 
	{
		super(x, y, hp, atk);
		setSpeed(BASE_SPEED / 2);
	}
	
	/**
	 * Moves this Enemy towards the given location (x, y).
	 * @param solids the other Solids on the screen
	 * @param x x coordinate of this Enemy's target location
	 * @param y y coordinate of this Enemy's target location
	 */
	public void moveTowards(ArrayList<Solid> solids, int x, int y, boolean[][] grid)
	{
		x /= 10;
		y /= 10;
		
		//A* ALGORITHM IDK M8
	}
	
	/**
	 * Draws this Enemy and its HP bar.
	 * @param g the Graphics object used to draw the Enemy. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		if (getStatus())
		{
			g.setColor(Color.BLACK);
			g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH);
			
			g.setColor(Color.RED);
			
			if (getFace() == 1) g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH / 2);
			if (getFace() == 2) g.fillRect(getHitbox().x + WIDTH / 2, getHitbox().y, WIDTH / 2, WIDTH);
			if (getFace() == 3) g.fillRect(getHitbox().x, getHitbox().y + WIDTH / 2, WIDTH, WIDTH / 2);
			if (getFace() == 4) g.fillRect(getHitbox().x, getHitbox().y, WIDTH / 2, WIDTH);
			
			g.setColor(Color.BLACK);
			g.fillRect(getHitbox().x - WIDTH / 2, getHitbox().y - WIDTH / 2, HP_BAR, 2);
			g.setColor(new Color(255, 65, 36));
			g.fillRect(getHitbox().x - WIDTH / 2, getHitbox().y - WIDTH / 2, (int)(HP_BAR * ((double)(getCurrentHP()) / getMaxHP()) + 0.5), 2);	
		}
	}

}
