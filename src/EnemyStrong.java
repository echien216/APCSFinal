import java.awt.Color;
import java.awt.Graphics;

/** 
 * An <code>EnemyStrong</code> is a special
 * type of enemy that has more health and does
 * more damage than the typical enemy.
 * 
 * @author eugenia
 */

public class EnemyStrong extends Enemy
{
	/**
	 * Creates an Enemy object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * It has 120 HP and an attack value of 8.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 */
	public EnemyStrong(int x, int y) 
	{
		super(x, y);
		setAtk(8);
		changeMaxHP(10);
	}
	
	/**
	 * Draws this EnemyStrong and its HP bar.
	 * @param g the Graphics object used to draw the EnemyStrong. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		if (getStatus())
		{
			g.setColor(Color.BLACK);
			g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH);
			
			g.setColor(new Color(255, 77, 228));
			
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
