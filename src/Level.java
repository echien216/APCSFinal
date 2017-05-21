import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A <code>Level</code> object takes in a txt file of a level 
 * and translates it into an ArrayList with Solid objects.
 * 
 * @author christine
 */

public class Level 
{

	private ArrayList<Solid> obs;
	private String fileName;
	private int playerIndex, goalIndex;
	private int level;
	private int time, timeLimit;
	private boolean playerStatus;
	
	public static final int MAX_LEVEL = 3;

	/**
	 * 
	 * Creates a Level object.
	 */
	public Level()
	{
		fileName = "level4.txt";
		obs = new ArrayList<Solid>();
		level = 4;
	}

	/**
	 * Returns an ArrayList of Solids corresponding
	 * to the .txt file from which this Level was created.
	 */
	public ArrayList<Solid> getSolids()
	{
		return obs;	
	}
	
	/**
	 * Returns the level the user is currently on.
	 */
	public int getLevel()
	{
		return level;
	}
	
	/**
	 * Returns the amount of time the user has spent
	 * on this level so far, including the 5 second countdown.
	 */
	public int getTime()
	{
		return time;
	}
	
	/**
	 * Returns this level's time limit, including the
	 * 5 second countdown.
	 */
	public int getTimeLimit()
	{
		return timeLimit;
	}
	
	/**
	 * Returns the index of the Player in the current level.
	 */
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	/**
	 * Returns the status of the Player in this level.
	 * @return
	 */
	public boolean getPlayerStatus()
	{
		return playerStatus;
	}
	
	/**
	 * Returns a boolean array containing values corresponding
	 * to whether or not each of the Player's skill is off cooldown.
	 */
	public boolean[] getPlayerSkillsReady()
	{
		if (playerStatus) return ((Player) obs.get(playerIndex)).getSkillsReady();
		else
		{
			boolean[] ready = {false, false, false};
			return ready;
		}
	}
	
	/**
	 * Makes all Solids in this level act in whatever
	 * way they are supposed to, removes any Solids
	 * that are no longer valid (dead, offscreen, etc.),
	 * and increments the amount of time the user has spent on
	 * the current level. 
	 * Also checks for completion, and loads the next level if
	 * the current one has been completed.
	 */
	public void act()
	{
		for(int i = 0; i < obs.size(); i++)
		{
			Solid s = obs.get(i);
			
			if (s instanceof Actor && !((Actor) s).getStatus())
			{
				if (s instanceof Player)
				{
					playerStatus = ((Player) s).getStatus();
					playerIndex = -1;
				}
				
				obs.remove(i);
			}
			else s.act();
		}
		
		if (time >= (timeLimit - 250)) playerStatus = false;
		
		playerIndex = obs.indexOf(new Player(0, 0, 0, 0));
		goalIndex = obs.indexOf(new Goal(0, 0));
				
		if (obs.indexOf(new Enemy(0, 0, 0, 0)) == -1 && ((Goal) obs.get(goalIndex)).getStatus())
		{
			level++;
			SFileIO fileIO = new SFileIO();
			fileIO.writeObject("ecksdee.XD", obs.get(playerIndex));
			obs = new ArrayList<Solid>();
			fileName = "level" + level + ".txt";
			parse();
			time = 0;
		}
		
		((Goal) obs.get(goalIndex)).setStatus(false);
		time++;
	}


	/**
	 * Fills ArrayList with Solid objects
	 */
	public void parse()
	{
		FileReader reader;
		BufferedReader breader = null;
		int lineNum = 0;
		Scanner in = null;
		timeLimit = timeLimit - time + 3750 + 250 * (level - 1);
		
		try 
		{
			reader = new FileReader(fileName);
			breader = new BufferedReader(reader,96);
			in = new Scanner(breader);

			while(in.hasNextLine())
			{
				String input = in.nextLine();
				StringBuffer bLine = new StringBuffer(input);

				for(int i = 0; i < bLine.length(); i++)
				{
					int x = i * 10;
					int y = lineNum * 10;
					char c = bLine.charAt(i);
					
					if(c == 'p')
					{
						if (level == 1) obs.add(new Player(x, y, 100, 10));
						else
						{
							SFileIO fileIO = new SFileIO();
							Player p = (Player) fileIO.readObject("ecksdee.XD");
							p.initHitbox(x, y);
							p.changeHP((int) (0.2 * (p.getMaxHP() - p.getCurrentHP())));
							obs.add(p);
						}
						
						playerIndex = obs.size() - 1;
						playerStatus = ((Actor) obs.get(playerIndex)).getStatus();
					}
					else if(c == 'e')
					{
						Enemy enemy = new Enemy(x, y, 100, 6);
						
						obs.add(enemy);
					}
					else if(c == 'w')
					{
						obs.add(new Obstacle(x, y));
					}
					else if(c == 'g')
					{
						obs.add(new Goal(x, y));
						goalIndex = obs.size() - 1;
					}
					else if(c == 'a')
					{
						obs.add(new Actor(x, y, 100, 10));	
					}
				}
				lineNum++;
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
