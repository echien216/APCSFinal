import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

/** A <code>Proejctile<\code> represents anything in
 * Mater Tua that is fired by Actors towards other Actors,
 * and deals damage to Actors on contact.
 * 
 * @author christine
 */

public class Projectile implements Solid
{
	private int x, y;
	private double velocity;
	private int face;
	
	public static final int WIDTH = 5;
	public static final int BASEV = 30;

	/**
	 * Creates a Projectile object. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels. It moves in
	 * the given direction
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param velocity
	 * @param face
	 */
	public Projectile(int x, int y, int face){
		this.x = x;
		this.y = y;
		this.velocity = BASEV;
		this.face = face;
	}
	
	/**
	 * Returns this Projectile's hitbox used for
	 * collision detection.
	 */
	public Rectangle getHitbox() {
		return null;
	}

	/**
	 * Draws this Projectile.
	 * @param g the Graphics object used to draw the Projectile. Must not be null. 
	 */
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x ,y, WIDTH, WIDTH);
	}
	
	/**
	 * Moves this Projectile in the direction it was fired in.
	 */
	public void move(){
		if (face == 1) y -= velocity;
		if (face == 2) x += velocity;
		if (face == 3) y += velocity;
		if (face == 4) x -= velocity;
	}
	
	/**
	 * Does nothing because movement is handled by move().
	 */
	public void moveHorizontal(int dir, ArrayList<Solid> solids) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Does nothing because movement is handled by move().
	 */
	public void moveVertical(int dir, ArrayList<Solid> solids) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * WORK IN PROGRESS
	 */
	public void act() 
	{
		
	}

}
