import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * tester for actor and obstacle and maybe other stuff??
 */
public class Tester1 extends JPanel
{
	private KeyHandler k;
	private Level oneL, twoL;
	private ArrayList<Projectile> projectiles;
	public static final int LEVELLENGTH = 5184;
	
	public Tester1()
	{
		setBackground(Color.WHITE);
		k = new KeyHandler();
		projectiles = new ArrayList<Projectile>();
		oneL = new Level("levelone.txt");
		twoL = new Level("leveltwo.txt");

	}
	
	public KeyHandler getKeyHandler()
	{
		return k;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D)g;
		
		double xRatio = getWidth() / 960.0;
    	double yRatio = getHeight() / 540.0;
    	
    	g2D.scale(xRatio, yRatio);

    	try
    	{
    		for(Solid s: oneL.getLevel())
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
    	
/*    	for(Solid s : twoL.getLevel()){
    		if(s != null)
    		s.draw(g);
    	}*/    	    	
	}
	
	public void run()
	{
		oneL.parse();
		twoL.parse();
		int pIndex = oneL.getPlayerIndex();
		Player player = (Player) oneL.getLevel().get(pIndex);
		ArrayList<Solid> s = oneL.getLevel();
		
		while(true)
		{
			long startTime = System.currentTimeMillis();
			
			if (k.isPressed(KeyEvent.VK_UP)) player.moveVertical(-1, s);
			else if (k.isPressed(KeyEvent.VK_DOWN)) player.moveVertical(1, s);
			else if (k.isPressed(KeyEvent.VK_LEFT)) player.moveHorizontal(-1, s);
			else if (k.isPressed(KeyEvent.VK_RIGHT)) player.moveHorizontal(1, s);
			
			if (k.isPressed(KeyEvent.VK_A)) player.skill1(projectiles, s);
			else if (k.isPressed(KeyEvent.VK_S)) player.skill2(projectiles, s);
			else if (k.isPressed(KeyEvent.VK_D)) player.skill3();
			
			for(int i = 0; i < projectiles.size(); i++)
			{
				Projectile p = projectiles.get(i);
				
				if (p.getStatus()) p.move(s);
				else projectiles.remove(i);
			}
			
			oneL.act();
			
			repaint();		
			
			long endTime = System.currentTimeMillis();
			
		  	try 
		  	{
		  		long waitTime = 20 - (endTime-startTime);
		  		if (waitTime > 0) Thread.sleep(waitTime);
		  		else Thread.yield();
		  		
		  		System.out.println(waitTime);
		  	} 
		  	catch (InterruptedException e) 
		  	{
		  		
		  	}
		}
	}
	
	public static void main(String[] args)
	{
		JFrame w = new JFrame("Tester1");
		w.setBounds(100, 100, 960, 540);
		Container c = w.getContentPane();
		Tester1 t = new Tester1();
		c.add(t);
	    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.addKeyListener(t.getKeyHandler());
		w.setVisible(true);
		t.run();
	}
	
	public class KeyHandler implements KeyListener 
	{

		  private ArrayList<Integer> keys;

		  public KeyHandler() 
		  {
			  keys = new ArrayList<Integer>();
		  }

		  public void keyPressed(KeyEvent e) 
		  {
			  keys.add(e.getKeyCode());
		  }

		  public void keyReleased(KeyEvent e) 
		  {
			  Integer code = e.getKeyCode();
			  while(keys.contains(code))
				  keys.remove(code);
		  }

		  public void keyTyped(KeyEvent e) 
		  {

		  }
		  
		  public boolean isPressed(int code) 
		  {
			  return keys.contains(code);
		  }	  
	}
}


