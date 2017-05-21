import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/** 
 * The <code>SFileIO</code> class handles creating objects from
 * files and converting an object into a file.
 * 
 * @author eugenia
 */

public class SFileIO 
{	
	public static final String fileSeparator = System.getProperty("file.separator");
	public static final String lineSeparator = System.getProperty("line.separator");
	
	/**
	 * Reads a file.
	 * @param filename the name of the file to be read
	 * @return an ArrayList<String> containing the file's contents.
	 */
	public ArrayList<String> readFile(String filename) 
	{
		ArrayList<String> output = new ArrayList<String>();
		FileReader reader;
		BufferedReader breader = null;
		
		Scanner in = null;
		
		try 
		{
			reader = new FileReader(filename);
			breader = new BufferedReader(reader);
			
			in = new Scanner(breader);
			
			while(in.hasNextLine()) 
			{
				String input = in.nextLine();
				output.add(input);
			}
			
			return output;
			
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{
			if (in != null)
				in.close();
		}
		
		return null;
	}
	
	/**
	 * Creates an object from a file.
	 * @param filename the name of the file to be read from
	 * @return an Object created from the specified file.
	 */
	public Object readObject(String filename) 
	{
		
		Object output = null;
		
		FileInputStream fis;
		ObjectInputStream ois = null;
		
		try 
		{
			fis = new FileInputStream(filename);
			ois = new ObjectInputStream(fis);
			
			output = ois.readObject();			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (ois != null) ois.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		return output;
	}
	
	/**
	 * Writes a file.
	 * @param filename the name of the file to write to
	 * @param data the data that should be written to the file
	 */
	public void writeFile(String filename, ArrayList<String> data) 
	{
		
		FileWriter writer = null;
		BufferedWriter bwriter = null;

		try 
		{
			writer = new FileWriter(filename);
			bwriter = new BufferedWriter(writer);

			for(String line : data) 
			{
				bwriter.write(line+lineSeparator);
			}
			
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{		
			try 
			{
				if (bwriter != null) bwriter.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

	}

	/**
	 * Creates a file from an Object.
	 * @param filename the name of the file to create
	 * @param data the Object that should be written to the file
	 */
	public void writeObject(String filename, Object data) 
	{
		
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;

		try 
		{
			fos = new FileOutputStream(filename);
			oos = new ObjectOutputStream(fos);

			oos.writeObject(data);
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		} finally 
		{		
			try 
			{
				if (oos != null)
					oos.close();
			} catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
}
