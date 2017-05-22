import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * A <code>Solid</code> represents anything in Mater Tua
 * that cannot be "phased through", i.e. has a hitbox.
 * 
 * @author eugenia
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
	
	/**
	 * Makes this Solid act in whatever way it is supposed to.
	 */
	void act();
}
