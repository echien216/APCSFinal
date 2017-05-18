import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/** 
 * An <code>Enemy</code> represents anything in
 * Mater Tua that can deal damage to the Player, 
 * and that the Player must ("must" for those with
 * godlike mechanics, theoretically) kill to advance.
 * 
 * @author eugenia
 */

public class Enemy extends Actor
{
	/**
	 * Creates an Enemy object that is facing up. Its "hitbox" used for collision detection has
	 * its top left corner at (x, y), and its width is 10 pixels.
	 * @param x x coordinate of hitbox's top left corner
	 * @param y y coordinate of hitbox's top left corner
	 * @param hp this Enemy's maximum HP
	 * @param atk this Enemy's attack value
	 */
	public Enemy(int x, int y, int hp, int atk) 
	{
		super(x, y, hp, atk);
		setSpeed(BASE_SPEED / 4);
		setAtk(getAtk() / 2);
	}
	
	/**
	 * Moves this Enemy towards the given location (x, y).
	 * @param solids the other Solids on the screen
	 * @param x x coordinate of this Enemy's target location
	 * @param y y coordinate of this Enemy's target location
	 * @param grid Node grid for AI pathfinding
	 */	
	//may change to A* algorithm later if i can figure that out. for now here's this buggy shit.
	public void moveTowards(ArrayList<Solid> solids, int x, int y, ArrayList<Node> grid)
	{
		int xm = getHitbox().x;
		int ym = getHitbox().y;
		int d = getSpeed();
		
		double d1 = Math.sqrt(Math.pow(x - xm, 2) + Math.pow(y - (ym - d), 2));
		double d2 = Math.sqrt(Math.pow(x - (xm + d), 2) + Math.pow(y - ym, 2));
		double d3 = Math.sqrt(Math.pow(x - xm, 2) + Math.pow(y - (ym + d), 2));
		double d4 = Math.sqrt(Math.pow(x - (xm - d), 2) + Math.pow(y - ym, 2));
				
		if (d1 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveVertical(-1, solids);
		if (d2 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveHorizontal(1, solids);
		if (d3 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveVertical(1, solids);
		if (d4 == Math.min(Math.min(d1, d2), Math.min(d3, d4))) moveHorizontal(-1, solids);		
	}
	
	/**
	 * Draws this Enemy and its HP bar.
	 * @param g the Graphics object used to draw the Enemy. Must not be null. 
	 */
	public void draw(Graphics g)
	{
		if (getStatus())
		{
			g.setColor(Color.BLACK);
			g.fillRect(getHitbox().x, getHitbox().y, WIDTH, WIDTH);
			
			g.setColor(Color.RED);
			
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

	private Node findMin(ArrayList<Node> nodes)
	{
		int minIndex = 0;
		int min = nodes.get(0).getf();
		
		for(int i = 1; i < nodes.size(); i++)
		{
			if(nodes.get(i).getf() < min)
			{
				minIndex = i;
				min = nodes.get(i).getf();
			}
		}
		
		return nodes.get(minIndex);
	}
}
/*
int f1 = -1, f2 = -1, f3 = -1, f4 = -1;

if (y1 > 0)
{
	int i = nodes.indexOf(new Node(x1, y1 - 1, 0));
	int g = -1;
	if (i != -1) g = nodes.get(i).getg();
	if (g != -1) f1 = g + Math.abs(getHitbox().x - x) + Math.abs(getHitbox().y - y);
}
if (x1 < 96)
{
	int i = nodes.indexOf(new Node(x1 + 1, y1, 0));
	int g = -1;
	if (i != -1) g = nodes.get(i).getg();
	if (g != -1) f2 = g + Math.abs(getHitbox().x - x) + Math.abs(getHitbox().y - y);
}
if (y1 < 54)
{
	int i = nodes.indexOf(new Node(x1, y1 + 1, 0));
	int g = -1;
	if (i != -1) g = nodes.get(i).getg();
	if (g != -1) f3 = g + Math.abs(getHitbox().x - x) + Math.abs(getHitbox().y - y);
}
if (x1 > 0)
{
	int i = nodes.indexOf(new Node(x1 - 1, y1, 0));
	int g = -1;
	if (i != -1) g = nodes.get(i).getg();
	if (g != -1) f4 = g + Math.abs(getHitbox().x - x) + Math.abs(getHitbox().y - y);
}

System.out.println(f1 + " " + f2 + " " + f3 + " " + f4);

if (f1 != -1 && f1 == Math.min(Math.min(f1, f2), Math.min(f3, f4)))
{
	moveVertical(-1, solids);
	//System.out.println(getHitbox());
}
else if (f2 != -1 && f2 == Math.min(Math.min(f1, f2), Math.min(f3, f4))) moveHorizontal(1, solids);
else if(f3 != -1 && f3 == Math.min(Math.min(f1, f2), Math.min(f3, f4))) moveVertical(1, solids);
else if(f4 != -1 && f4 == Math.min(Math.min(f1, f2), Math.min(f3, f4))) moveHorizontal(-1, solids);

x /= 10;
		y /= 10;
		int x1 = getHitbox().x / 10;
		int y1 = getHitbox().y / 10;
		int a = 0;
		ArrayList<Node> nodes = new ArrayList<Node>(grid);
		
		for(int i = 0; i < solids.size(); i++)
		{
			Solid s = solids.get(i);
			
			if (s instanceof Actor)
			{
				int m = s.getHitbox().y / 10;
				int n = s.getHitbox().x / 10;
				
				if (s instanceof Actor)
				{
					nodes.add(new Node(m, n, -10));
					nodes.get(nodes.size() - 1).setgh(-10, Math.abs(x1 - m) + Math.abs(y1 - n));
				}
				else if (this == s)
				{
					nodes.add(new Node(m, n, 0));
					a = nodes.size() - 1;
				}
				else if (s instanceof Enemy)
				{
					nodes.add(new Node(m, n, 100));
					nodes.get(nodes.size() - 1).setgh(100, Math.abs(x1 - m) + Math.abs(y1 - n));
				}
			}			
		}
		
		ArrayList<Node> open = new ArrayList<Node>();
		ArrayList<Node> closed = new ArrayList<Node>();
		open.add(nodes.get(a));
		
		while(open.size() != 0)
		{
			Node q = findMin(nodes);
			open.remove(nodes.indexOf(q));
			
			ArrayList<Node> successors = new ArrayList<Node>();
			
			if (q.x < 96)
			{
				if (q.y != 0) successors.add(nodes.get(nodes.indexOf(new Node(q.x + 1, q.y - 1, 0))));
				successors.add(nodes.get(nodes.indexOf(new Node(q.x + 1, y1, 0))));
				if (q.y < 54) successors.add(nodes.get(nodes.indexOf(new Node(q.x + 1, q.y + 1, 0))));
			}
			
			if (q.y != 0) successors.add(nodes.get(nodes.indexOf(new Node(q.x, q.y - 1, 0))));
			if (q.y < 54) successors.add(nodes.get(nodes.indexOf(new Node(q.x, q.y + 1, 0))));

			if (q.x != 0)
			{
				if (q.y != 0) successors.add(nodes.get(nodes.indexOf(new Node(q.x - 1, q.y - 1, 0))));
				successors.add(nodes.get(nodes.indexOf(new Node(q.x - 1, q.y, 0))));
				if (q.y < 54) successors.add(nodes.get(nodes.indexOf(new Node(q.x, q.y + 1, 0))));
			}
			
			for(Node n: successors)
			{
				if (n.x == x && n.y == n.y)
				{
					if (y < y1) moveVertical(-1, solids);
					if (x > x1) moveHorizontal(1, solids);
					if (y > y1) moveVertical(1, solids);
					if (x < x1) moveHorizontal(-1, solids);
					
					return;
				}
				else if (open.indexOf(n) != -1 && open.get(open.indexOf(n)).getf() < n.getf()) break;
				else if (closed.indexOf(n) != -1 && closed.get(open.indexOf(n)).getf() < n.getf()) break;
				else open.add(n);
			}
			
			closed.add(q);
		}
		
*/
