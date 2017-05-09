import java.awt.Rectangle;

/**
 * 
 * @author eugenia
 *
 */

public class Obstacle 
{
	private Rectangle hitbox;
	public static final int width = 10;
	
	public Obstacle(int x, int y)
	{
		hitbox = new Rectangle(x, y, width, width);
	}
}
