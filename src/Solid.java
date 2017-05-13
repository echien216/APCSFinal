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
	 * @param dir direction in which this Solid should move horizontally (-1 = left, 1 = right)
	 * @param solids the other Solids on the screen
	 */
	void moveHorizontal(int dir, ArrayList<Solid> solids);
	
	/**
	 * Moves this Solid vertically. 
	 * @param dir direction in which this Solid should move vertically (-1 = up, 1 = down)
	 * @param solids the other Solids on the screen (not including Actors)
	 */
	void moveVertical(int dir, ArrayList<Solid> solids);
	
	/**
	 * Makes this Solid act in whatever way it is supposed to.
	 */
	void act();
}
