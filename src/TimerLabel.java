import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;


public class TimerLabel extends JLabel
{
	int time;
	
	public TimerLabel(int timeSeconds)
	{
		super();
		time = timeSeconds;
		addLabels();
	}
	
	
	public void addLabels()
	{
		JLabel timeLabel = new JLabel(formatTime());
//		JLabel textLabel = new JLabel("remaining until full AP");
		
		setLayout(new BorderLayout());		
		
	}
	
	
	public void paint(Graphics g)
	{
		g.drawString(formatTime(), 0, 0);
	}
	
	
	private String formatTime()
	{
		int hours = time / 3600;
		int minutes = (time % 3600) / 60;
		int seconds = time % 60;
		
		String hourString = formatTimeString(hours);
		String minuteString = formatTimeString(minutes);
		String secondString = formatTimeString(seconds);
		
		return hourString + ":" + minuteString + ":" + secondString;
	}
	
	
	private String formatTimeString(int time)
	{
		String toReturn = Integer.toString(time);
		if (time < 10)
		{
			toReturn = "0" + toReturn;
		}
		return toReturn;
	}
}
