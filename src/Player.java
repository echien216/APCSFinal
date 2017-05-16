import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/** 
 * A <code>Player</code> represents the
 * entity that the person playing the game
 * controls.
 * 
 * @author eugenia
 */

public class Player extends Actor
{
	private int[] cooldowns;
	
	/**
	 * Creates a Player object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param hp this Actor's maximum HP
	 * @param atk this Actor's attack value
	 */
	public Player(int x, int y, int hp, int atk) 
	{
		super(x, y, hp, atk);
		cooldowns = new int[3];
	}
	
	/**
	 * Resets this Actor's hitbox, increments its cooldowns, 
	 * and moves it offscreen if it is dead.
	 */
	public void act() 
	{
		super.act();
		
		for(int i = 0; i < 3; i++)
		{
			cooldowns[i]++;
		}
	}
	
	/**
	 * Performs this Actor's 2nd skill, which fires 4 high-power Projectiles
	 * in all 4 directions.
	 * @param projectiles the other projectiles in the game
	 * @param solids the other solids on the screen
	 */
	public void skill2(ArrayList<Projectile> projectiles, ArrayList<Solid> solids)
	{
		Projectile[] p = 
		{
			new Projectile(getHitbox().x + WIDTH / 2, getHitbox().y, 1, (int) (getAtk() * 1.5)),
			new Projectile(getHitbox().x + WIDTH, getHitbox().y + WIDTH / 2, 2, (int) (getAtk() * 1.5)),
			new Projectile(getHitbox().x + WIDTH / 2, getHitbox().y + WIDTH, 3, (int) (getAtk() * 1.5)),
			new Projectile(getHitbox().x, getHitbox().y + WIDTH / 2, 4, (int) (getAtk() * 1.5)),
		};
		
		for(Projectile e: p)
		{
			projectiles.add(e);
		}
		
		p[0].moveVertical(-1, solids);
		p[1].moveHorizontal(1, solids);
		p[2].moveVertical(1, solids);
		p[3].moveHorizontal(-1, solids);
	}
	
	/**
	 * Draws this Player and its HP bar.
	 * @param g the Graphics object used to draw the Actor. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		if (getStatus())
		{
			g.setColor(Color.BLACK);
			g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH);
			
			g.setColor(Color.GREEN);
			
			if (getFace() == 1) g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH / 2);
			if (getFace() == 2) g.fillRect(getHitbox().x + WIDTH / 2, getHitbox().y, WIDTH / 2, WIDTH);
			if (getFace() == 3) g.fillRect(getHitbox().x, getHitbox().y + WIDTH / 2, WIDTH, WIDTH / 2);
			if (getFace() == 4) g.fillRect(getHitbox().x, getHitbox().y, WIDTH / 2, WIDTH);
			
			g.setColor(Color.RED);
			g.fillRect(getHitbox().x - WIDTH / 2, getHitbox().y - WIDTH / 2, HP_BAR, 2);
			g.setColor(Color.GREEN);
			g.fillRect(getHitbox().x - WIDTH / 2, getHitbox().y - WIDTH / 2, (int)(HP_BAR * ((double)(getCurrentHP()) / getMaxHP()) + 0.5), 2);	
		}
	}
}
