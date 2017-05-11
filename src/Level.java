import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**

 * 
 * @author Christine
 * @version idek okay
 * 
 */

public class Level {
	
	private ArrayList<Obstacle> obs;
	private String fileName;
	
	/**
	 * 
	 * Creates a level object from a txt file
	 */
	public Level(String fileName){
		this.fileName = fileName;
		obs = new ArrayList<Obstacle>();
	}
	
	/**
	 * 
	 * Fills ArrayList with Obstacle objects or null where there are no Obstacles
	 */
	public void parseObs(){
		FileReader reader;
		BufferedReader breader = null;
		int lineNum = 0;
		
		
		Scanner in = null;
		try {
			reader = new FileReader(fileName);
			breader = new BufferedReader(reader,960);
			in = new Scanner(breader);

			while(in.hasNextLine()){
				String input = in.nextLine();

				StringBuffer bLine = new StringBuffer(input);
				
				for(int i = 0; i < bLine.length(); i++){
					if(bLine.charAt(i) == '-')
						obs.add(null);
					else if(bLine.charAt(i) == 'o'){
						int x = i;
						int y = lineNum;
						obs.add(new Obstacle(x ,y));
					}
				}
				lineNum++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
