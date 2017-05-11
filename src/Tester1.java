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
<<<<<<< HEAD
	private ArrayList<Solid> solids;
	private Actor a;
	private Level oneL, twoL;
	public static final int LEVELLENGTH = 5184;

=======
	private ArrayList<Actor> actors;
	
	public Tester1()
	{
		setBackground(Color.WHITE);
		k = new KeyHandler();
<<<<<<< HEAD
		solids = new ArrayList<Solid>();
		solids.add(new Actor(10, 10));
		solids.add(new Obstacle(30, 30));
		a = new Actor(40, 40);
		
=======
		actors = new ArrayList<Actor>();
		actors.add(new Actor(40, 40, 100));
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
    	
<<<<<<< HEAD
    	for(int i = 0; i < solids.size(); i++)
    	{
    		solids.get(i).draw(g);
    	}
    	
    	for(Solid s : oneL.getLevel()){
=======
    	for(Solid s: oneL.getLevel()){
>>>>>>> branch 'master' of https://github.com/echien216/APCSFinal.git
    		if(s != null)
    			s.draw(g);
    	}
<<<<<<< HEAD
    	for(Solid s : twoL.getLevel()){
    		if(s != null)
    		s.draw(g);
    	}
    	a.draw(g);
=======
    	
    	for(Actor a: actors) a.draw(g);
>>>>>>> branch 'master' of https://github.com/echien216/APCSFinal.git
	}
	
	public void run()
	{
		oneL.parse();
		twoL.parse();
		
		while(true)
		{
			long startTime = System.currentTimeMillis();

			if (k.isPressed(KeyEvent.VK_UP)) actors.get(0).moveVertical(-1, oneL.getLevel(), actors);
			else if (k.isPressed(KeyEvent.VK_DOWN)) actors.get(0).moveVertical(1, oneL.getLevel(), actors);
			else if (k.isPressed(KeyEvent.VK_LEFT)) actors.get(0).moveHorizontal(-1, oneL.getLevel(), actors);
			else if (k.isPressed(KeyEvent.VK_RIGHT)) actors.get(0).moveHorizontal(1, oneL.getLevel(), actors);
			
			for(Actor a: actors) a.act(oneL.getLevel(), actors);
			
			repaint();
			
			long waitTime = 17 - (System.currentTimeMillis()-startTime);
			
		  	try 
		  	{
		  		if (waitTime > 0) Thread.sleep(waitTime);
		  		else Thread.yield();
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
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = w.getContentPane();
		Tester1 t = new Tester1();
		c.add(t);
		w.setVisible(true);
		w.addKeyListener(t.getKeyHandler());
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


