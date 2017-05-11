import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Represents anything in Mater Tua
 * that has a hitbox (cannot be "phased through").
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
	
	/**
	 * Moves this Solid horizontally. 
	 * @param dir direction in which this Solid should move horizontally (false = left, true = right)
	 */
	void moveHorizontal(boolean dir, ArrayList<Solid> solids);
	
	/**
	 * Moves this Solid vertically. 
	 * @param dir direction in which this Solid should move vertically (false = up, true = down)
	 */
	void moveVertical(boolean dir, ArrayList<Solid> solids);
	
	/**
	 * Makes this Solid act in whatever way it is supposed to.
	 */
	void act();
}
