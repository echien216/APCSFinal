
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/** 
 * The <code>Overworld</code> handles all graphics
 * and all player-enemy interactions.
 * 
 * @author eugenia
 *
 */

public class Overworld extends JPanel
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
		level = new Level("level1.txt");
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
    		for(Solid s: level.getLevel())
    		{
        		s.draw(g);
        	}
    	}
    	catch(Exception e)
    	{
    		
    	}
    	
    	for(Projectile p: projectiles)
    	{
    		p.draw(g);
    	} 
    	
    	if (!status)
    	{
    		g.setColor(Color.RED);
    		g.setFont(new Font("Agency FB", Font.PLAIN, 50));
    		g.drawString("YOU DIED", (getWidth() * 3) / 2 , getHeight() / 2);
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
			if(!level.getPlayerStatus())
			{
				status = false;
				break;
			}
			
			long startTime = System.currentTimeMillis();
			int pIndex = level.getPlayerIndex();
			Player player = (Player) level.getLevel().get(pIndex);
			ArrayList<Solid> s = level.getLevel();
			
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
					if (count % 120 == 0) ((Enemy) solid).skill2(projectiles, s);
					if (count % 40 == 0) ((Enemy) solid).skill1(projectiles, s);
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
	 * The KeyHandler handles all key presses from the user.
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



