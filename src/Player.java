import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/** 
 * A <code>Player</code> represents the
 * entity that the person playing Mater Tua
 * controls.
 * 
 * @author eugenia
 */

public class Player extends Actor 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int[] cooldowns;
	
	public static final int[] CD = {0, 200, 500, 3000};
	
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
		cooldowns = new int[4];
		cooldowns[0] = CD[0];
		cooldowns[1] = CD[1];
		cooldowns[2] = CD[2];
		cooldowns[3] = CD[3];

	}
	
	/**
	 * Returns a boolean array containing values corresponding
	 * to whether or not each skill is off cooldown.
	 */
	public boolean[] getSkillsReady()
	{
		boolean[] ready = {cooldowns[0] >= CD[0], cooldowns[1] >= CD[1], cooldowns[2] >= CD[2], cooldowns[3] >= CD[3]};
		return ready;
	}
	
	/**
	 * Resets this Actor's hitbox, and increments its cooldowns.
	 */
	public void act() 
	{
		super.act();
		
		for(int i = 0; i < CD.length; i++)
		{
			cooldowns[i]++;
		}
	}
	
	/**
	 * Performs this Player's 1st skill, which fires a Projectile
	 * in the direction it is currently facing. After being used,
	 * this skill goes on cooldown for .2 seconds, during which it
	 * cannot be used.
	 * @param projectiles the other projectiles in the game
	 * @param solids the other solids on the screen
	 */
	public void skill1(ArrayList<Projectile> projectiles, ArrayList<Solid> solids)
	{
		if (cooldowns[0] >= CD[0])
		{
			cooldowns[0] = 0;
			
			super.skill1(projectiles, solids);
		}
	}
	
	/**
	 * Performs this Player's 2nd skill, which fires a high-power Projectile
	 * in all 4 directions. After being used, this skill goes on cooldown for
	 * 4 seconds, during which it cannot be used.
	 * @param projectiles the other projectiles in the game
	 * @param solids the other solids on the screen
	 */
	public void skill2(ArrayList<Projectile> projectiles, ArrayList<Solid> solids)
	{
		if (cooldowns[1] >= CD[0])
		{
			cooldowns[1] = 0;
			
			Projectile[] p = 
			{
				new Projectile(getHitbox().x + WIDTH / 2, getHitbox().y, 1, (int) (getAtk() * 5), this),
				new Projectile(getHitbox().x + WIDTH, getHitbox().y + WIDTH / 2, 2, (int) (getAtk() * 5), this),
				new Projectile(getHitbox().x + WIDTH / 2, getHitbox().y + WIDTH, 3, (int) (getAtk() * 5), this),
				new Projectile(getHitbox().x, getHitbox().y + WIDTH / 2, 4, (int) (getAtk() * 5), this),
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
	}
	
	/**
	 * Performs this Player's 3rd skill, which heals it for
	 * 25% of its maximum HP + 10% of its missing HP. 
	 * After being used, this skill goes on cooldown for
	 * 10 seconds, during which it cannot be used. This skill 
	 * cannot be used if this Player is currently at max HP.
	 */
	public void skill3()
	{
		if (cooldowns[2] >= CD[1] & getCurrentHP() < getMaxHP())
		{
			cooldowns[2] = 0;
			changeHP((int)(getMaxHP() * 0.25 + ((getMaxHP() - getCurrentHP()) * 0.10) + 0.5));
		}
	}
	
	/**
	 * Performs this Player's 4th skill, which deals damage to every
	 * other Actor on the screen equal to 30% of that Actor's maximum HP + 
	 * 10% of this Actor's missing HP. After being used, this skill goes 
	 * on cooldown for 60 seconds, during which it cannot be used.
	 * @param solids the other solids on the screen
	 */
	public void skill4(ArrayList<Solid> solids)
	{
		if (cooldowns[3] >= CD[3])
		{
			cooldowns[3] = 0;
			for(int i = 0; i < solids.size(); i++)
			{
				Solid s = solids.get(i);
				if (s != this && s instanceof Actor)
				{
					((Actor) s).changeHP((int) (((Actor) s).getMaxHP() * -0.3 - 0.5 - (int)(getMaxHP() - getCurrentHP()) * 0.1 + 0.5));
				}
			}
		}
	}
	
	/**
	 * Returns true if the specified object is a Player 
	 * (for level completion determination purposes), false otherwise.
	 * @param o the other Object this Player will be compared to
	 */
	public boolean equals(Object o)
	{
		if (o instanceof Player) return true;
		else return false;
	}
	
	/**
	 * Draws this Player and its HP bar.
	 * @param g the Graphics object used to draw the Player. Must not be null. 
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
