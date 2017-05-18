import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/** 
 * A <code>Level</code> object takes in a txt file of a level and translates it into an ArrayList with Solid objects.
 * @author christine
 */

public class Level {

	private ArrayList<Solid> obs;
	private String fileName;
	private int playerIndex;
	private ArrayList<Node> grid;

	/**
	 * 
	 * Creates a level object from a txt file
	 */
	public Level(String fileName)
	{
		this.fileName = fileName;
		obs = new ArrayList<Solid>();
		grid = new ArrayList<Node>();
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
	 * Returns this Level's Node map for AI pathfinding.
	 */
	public ArrayList<Node> getGrid()
	{
		return grid;
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
	}


	/**
	 * Fills ArrayList with Obstacle objects
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
						grid.add(new Node(i, lineNum, 100));
					}
					else if(c == 'g')
					{
						obs.add(new Goal(x, y));
						grid.add(new Node(i, lineNum, 80));
					}
					else if(c == 'p')
					{
						obs.add(new Player(x, y, 100, 10));
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
					else grid.add(new Node(i, lineNum, 10));
				}
				lineNum++;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
