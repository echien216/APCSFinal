import javax.swing.*;

public class Main {
	
	
	public static void main(String [] args){
		JFrame f = new JFrame("Meh");
		TitleScreen menu = new TitleScreen();
		f.add(menu);
		f.setSize(menu.getSize());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setVisible(true);
		
		
	}
}
