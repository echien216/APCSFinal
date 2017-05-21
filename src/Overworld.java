
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * The <code>Overworld</code> handles all graphics,
 * user-game interactions, and level progression.
 * 
 * @author eugenia
 */

public class Overworld extends JPanel implements Runnable
{
	private KeyHandler k;
	private Level level;
	private ArrayList<Projectile> projectiles;
	private boolean status;
	public static final int LEVELLENGTH = 5184;
	
	/**
	 * Creates an Overworld object.
	 * @param m the class with the main method.
	 */
	public Overworld(Main m)
	{
		setBackground(Color.WHITE);
		k = new KeyHandler();
		projectiles = new ArrayList<Projectile>();
		status = true;
		level = new Level();
	}
	
	/**
	 * Returns this Overworld's KeyHandler.
	 */
	public KeyHandler getKeyHandler()
	{
		return k;
	}
	
	/**
	 * Draws everything on the screen.
	 * @param g the Graphics object used to draw everything.
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		double xRatio = getWidth() / 960.0;
    	double yRatio = getHeight() / 540.0;
    	
    	g2D.scale(xRatio, yRatio);

    	try
    	{
    		for(Solid s: level.getSolids())
    		{
        		s.draw(g);
        	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    	//if(level.getTime() - 250 == 0)
    	//{
    		//g.setColor(Color.RED);
    		//g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
    		//g.drawString("" + level.getTime() / 50, 360, 270);
    	//}
    	//else
    	//{
        	g.setColor(new Color(87, 51, 12));
        	g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        	g.drawString("Level: " + level.getLevel(), 0, 10);
        	g.drawString("Time: " + (level.getTime() / 50) + "/" + ((level.getTimeLimit() - 250) / 50), 800, 10);
    	//}
    	
    	boolean[] ready = level.getPlayerSkillsReady();
    	
    	for(int i = 0; i < ready.length; i++)
    	{
    		boolean r = ready[i];
    		
    		if (r) g.setColor(Color.GREEN);
    		else g.setColor(Color.RED);
    		
    		g.fillOval(880 + 20 * i, 0, 10, 10);
    		
    		g.setColor(new Color(105, 105, 105));
        	g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
        	String s = "";
        	
        	if (i == 0) s = "A";
        	if (i == 1) s = "S";
        	if (i == 2) s = "D";
        	if (i == 3) s = "F";
        	
        	g.drawString(s, 881 + 20 * i, 10);
    	}
    	
    	try
    	{
    		for(Projectile p: projectiles)
    		{
        		p.draw(g);
        	}
    	}
    	catch(Exception e)
    	{
    		
    	} 
    	
    	if (!status)
    	{
    		g.setColor(Color.BLACK);
    		g.fillRect(0, 0, 2 * getWidth(), 2 * getHeight());
    		g.setColor(Color.RED);
    		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
    		g.drawString("YOU DIED", 360, 270);
    	}
	}
	
	/**
	 * Runs this Overworld.
	 */
	public void run()
	{
		level.parse();
		int count = 0;
		
		while(status)
		{
			long startTime = System.currentTimeMillis();
			
			if(!level.getPlayerStatus())
			{
				status = false;
				break;
			}
			
			int pIndex = level.getPlayerIndex();
			Player player = (Player) level.getSolids().get(pIndex);
			ArrayList<Solid> s = level.getSolids();
			
			if (k.isPressed(KeyEvent.VK_UP)) player.moveVertical(-1, s);
			else if (k.isPressed(KeyEvent.VK_DOWN)) player.moveVertical(1, s);
			else if (k.isPressed(KeyEvent.VK_LEFT)) player.moveHorizontal(-1, s);
			else if (k.isPressed(KeyEvent.VK_RIGHT)) player.moveHorizontal(1, s);
			
			if (k.isPressed(KeyEvent.VK_A)) player.skill1(projectiles, s);
			else if (k.isPressed(KeyEvent.VK_S)) player.skill2(projectiles, s);
			else if (k.isPressed(KeyEvent.VK_D)) player.skill3();
			else if (k.isPressed(KeyEvent.VK_F)) player.skill4(s);
			
			for(int i = 0; i < projectiles.size(); i++)
			{
				Projectile p = projectiles.get(i);
				
				if (p.getStatus()) p.move(s);
				else projectiles.remove(i);
			}
			
			for(int i = 0; i < s.size(); i++)
			{
				Solid solid = s.get(i);
				
				if (solid instanceof Enemy)
				{
					((Enemy) solid).moveTowards(s, player.getHitbox().x, player.getHitbox().y);
					if (count % 150 == 0) ((Enemy) solid).skill2(projectiles, s);
					if (count % 50 == 0) ((Enemy) solid).skill1(projectiles, s);
				}
			}
			
			level.act();
			
			count++;
			repaint();		
			
			long endTime = System.currentTimeMillis();
			
		  	try 
		  	{
		  		long waitTime = 20 - (endTime-startTime);
		  		if (waitTime > 0) Thread.sleep(waitTime);
		  		else Thread.yield();		  		
		  	} 
		  	catch (InterruptedException e) 
		  	{
		  		
		  	}			
		}	
		
		repaint();
	}
	
	/**
	 * The <code>KeyHandler</code> handles all key presses from the user.
	 * @author eugenia
	 *
	 */
	public class KeyHandler implements KeyListener 
	{

		  private ArrayList<Integer> keys;
		  
		  /**
		   * Creates a KeyHandler object.
		   */
		  public KeyHandler() 
		  {
			  keys = new ArrayList<Integer>();
		  }

		  /**
		   * Tells this KeyHandler that a key was pressed.
		   * @param e the KeyEvent corresponding to the key pressed
		   */
		  public void keyPressed(KeyEvent e) 
		  {
			  keys.add(e.getKeyCode());
		  }

		  /**
		   * Tells this KeyHandler that a key was released.
		   * @param e the KeyEvent corresponding to the key released
		   */
		  public void keyReleased(KeyEvent e) 
		  {
			  Integer code = e.getKeyCode();
			  while(keys.contains(code))
				  keys.remove(code);
		  }

		  /**
		   * Does nothing.
		   */
		  public void keyTyped(KeyEvent e) 
		  {

		  }
		  
		  /**
		   * Returns if the key corresponding to the specified code 
		   * is currently being pressed by the user.
		   * @code the code corresponding to the key being pressed
		   */
		  public boolean isPressed(int code) 
		  {
			  return keys.contains(code);
		  }	  
	}
}



