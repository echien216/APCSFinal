import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Represents anything in Mater Tua
 * that has a hitbox (cannot be "phased through).
 * 
 * @author echien216
 *
 */
public interface Solid 
{
	/**
	 * Returns this Solid's hitbox used for
	 * collision detection.
	 */
	Rectangle getHitbox();
	
	/**
	 * Draws this Solid.
	 * @param g the Graphics object used to draw the Actor. Must not be null. 
	 */
	void draw(Graphics g);
}
