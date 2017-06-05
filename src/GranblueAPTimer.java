import javax.swing.*;

/**
 * An application for timing how long it takes for AP
 * to refill in the mobile game Granblue Fantasy.
 * 
 * @author Luke Badini
 */
public class GranblueAPTimer
{
	
	public static void main(String[] args)
	{
		final int WINDOW_WIDTH = 500;
		final int WINDOW_HEIGHT = 300;
		
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() 
			{
				JFrame mainWindow = new MainFrame("Granblue AP Timer");
				mainWindow.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainWindow.setVisible(true);
			}
		});
		

	}

}
