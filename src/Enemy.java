import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/** 
 * An <code>Enemy</code> represents anything in
 * Mater Tua that can deal damage to the Player, 
 * and that the Player must kill to advance.
 * Enemies cannot damage each other.
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
		setSpeed(BASE_SPEED / 4);
		setAtk(getAtk() / 2);
	}
	
	/**
	 * Moves this Enemy towards the given location (x, y).
	 * @param solids the other Solids on the screen
	 * @param x x coordinate of this Enemy's target location
	 * @param y y coordinate of this Enemy's target location
	 */	
	public void moveTowards(ArrayList<Solid> solids, int x, int y)
	{
		int x1 = getHitbox().x;
		int y1 = getHitbox().y;
		int d = getSpeed();
		
		if(Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2)) < 350)
		{
			double d1 = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - (y1 - d), 2));
			double d2 = Math.sqrt(Math.pow(x - (x1 + d), 2) + Math.pow(y - y1, 2));
			double d3 = Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - (y1 + d), 2));
			double d4 = Math.sqrt(Math.pow(x - (x1 - d), 2) + Math.pow(y - y1, 2));
					
			if (d1 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveVertical(-1, solids);
			if (d2 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveHorizontal(1, solids);
			if (d3 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveVertical(1, solids);
			if (d4 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveHorizontal(-1, solids);
		}		
	}
	
	/**
	 * Performs this Player's 2nd skill, which fires a high-power Projectiles
	 * in all 4 directions. After being used, this skill goes on cooldown for
	 * 4 seconds, during which it cannot be used.
	 * @param projectiles the other projectiles in the game
	 * @param solids the other solids on the screen
	 */
	public void skill2(ArrayList<Projectile> projectiles, ArrayList<Solid> solids)
	{		
		Projectile[] p = 
		{
				new Projectile(getHitbox().x + WIDTH / 2, getHitbox().y, 1, (int) (getAtk() * 1.5), this),
				new Projectile(getHitbox().x + WIDTH, getHitbox().y + WIDTH / 2, 2, (int) (getAtk() * 1.5), this),
				new Projectile(getHitbox().x + WIDTH / 2, getHitbox().y + WIDTH, 3, (int) (getAtk() * 1.5), this),
				new Projectile(getHitbox().x, getHitbox().y + WIDTH / 2, 4, (int) (getAtk() * 1.5), this),
		};
			
		for(Projectile e: p)
		{
			projectiles.add(e);
		}
			
		for (int i = 0; i < p.length; i++)
		{
			p[i].move(solids);
		}
	}
	
	/**
	 * Returns true if the specified object is an Enemy 
	 * (for level completion determination purposes), false otherwise.
	 * @param o the other Object this Enemy will be compared to
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Enemy) return true;
		else return false;
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

