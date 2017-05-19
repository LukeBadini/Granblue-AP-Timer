import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;


public class TimerLabel extends JLabel
{
	int time;
	
	public TimerLabel(int timeSeconds)
	{
		time = timeSeconds;
		Timer timer = new Timer(1000, new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				time--;
			}
		});
		timer.start();
	}
}
