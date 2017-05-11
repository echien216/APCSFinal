import java.awt.Color;
import java.awt.Graphics;

public class Goal extends Obstacle
{
	public Goal(int x, int y){
		super(x,y);
	}
	
	public void draw(Graphics g){
		g.setColor(Color.YELLOW);
		g.fillRect(this.getHitbox().x, this.getHitbox().y, WIDTH, WIDTH);

	}
}
