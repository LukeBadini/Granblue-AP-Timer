import javax.swing.*;

public class GranblueAPTimer
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() 
			{
				JFrame mainWindow = new MainFrame("Granblue AP Timer");
				mainWindow.setSize(500, 300);
				mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainWindow.setVisible(true);
			}
		});
		

	}

}
