import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * A <code>Level</code> object takes in a txt file of a level and translates it into an ArrayList with Solid objects.
 * @author christine
 */

public class Level 
{

	private ArrayList<Solid> obs;
	private String fileName;
	private int playerIndex, goalIndex;
	private int currentLevel;

	/**
	 * 
	 * Creates a level object from a txt file
	 */
	public Level(String fileName)
	{
		this.fileName = fileName;
		obs = new ArrayList<Solid>();
		currentLevel = 1;
	}

	/**
	 * Returns an ArrayList of Solids corresponding
	 * to the txt file from which this Level was created.
	 */
	public ArrayList<Solid> getLevel()
	{
		return obs;	
	}
	
	/**
	 * Returns the index of the Player in the current level.
	 */
	public int getPlayerIndex()
	{
		return playerIndex;
	}
	
	/**
	 * Makes all Solids in this level act in whatever
	 * way they are supposed to, and removes any Solids
	 * that are no longer valid (dead, offscreen, etc.).
	 */
	public void act()
	{
		for(int i = 0; i < obs.size(); i++)
		{
			Solid s = obs.get(i);
			
			if (s instanceof Actor && !((Actor) s).getStatus()) obs.remove(i);
			else s.act();
		}
		
		if (((Goal) obs.get(goalIndex)).getStatus())
		{
			currentLevel++;
			SFileIO fileIO = new SFileIO();
			fileIO.writeObject("ecksdee.XD", obs.get(playerIndex));
			obs = new ArrayList<Solid>();
			fileName = "level" + currentLevel + ".txt";
			parse();
		}
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
					
					if(c == 'w')
					{
						obs.add(new Obstacle(x, y));
					}
					else if(c == 'g')
					{
						obs.add(new Goal(x, y));
						goalIndex = obs.size() - 1;
					}
					else if(c == 'p')
					{
						if (currentLevel == 1) obs.add(new Player(x, y, 100, 10));
						else
						{
							SFileIO fileIO = new SFileIO();
							Player p = (Player) fileIO.readObject("ecksdee.XD");
							p.initHitbox(x, y);
							obs.add(p);
						}
						
						playerIndex = obs.size() - 1;
					}
					else if(c == 'e')
					{
						obs.add(new Enemy(x, y, 100, 5));
					}
					else if(c == 'a')
					{
						obs.add(new Actor(x, y, 100, 10));						
					}
				}
				lineNum++;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
