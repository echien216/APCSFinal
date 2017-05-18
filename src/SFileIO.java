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

public class SFileIO 
{	
	public static final String fileSeparator = System.getProperty("file.separator");
	public static final String lineSeparator = System.getProperty("line.separator");
	
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
		finally {
			try 
			{
				if (ois != null) ois.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		if(output == null) System.out.println("ECKS DEE");
		return output;
	}
	
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
