import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Projectile implements Solid
{
	private int x, y;
	private double velocity;
	private int face;
	
	public static final int WIDTH = 5;
	public static final int BASEV = 30;

	public Projectile(int x, int y, double velocity, int face){
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.face = face;
	}
	
	public Rectangle getHitbox() {
		return null;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x ,y, WIDTH, WIDTH);
	}
	
	public void move(){
		if (face == 1) y -= velocity;
		if (face == 2) x += velocity;
		if (face == 3) y += velocity;
		if (face == 4) x -= velocity;
	}
	
	@Override
	public void moveHorizontal(int dir, ArrayList<Solid> solids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveVertical(int dir, ArrayList<Solid> solids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void act() {
		while(true){
			
		}
		
	}

}
