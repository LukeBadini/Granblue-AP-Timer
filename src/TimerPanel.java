import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * A class defining the APInfoPanel. This contains a single text
 * label that represents the timer.
 * 
 * @author Luke Badini
 */
public class TimerPanel extends JPanel
{
	// This is probably bad, I should instead center the
	// JLabel but for now I can't figure that out
	private final String STRING_BUFFER = "     ";
	private JLabel timerLabel = new JLabel("00:00:00" + STRING_BUFFER);
	private int timeRemaining = 0;
	
	private Timer timer = new Timer(1000, new ActionListener()
	{
		/*
		 * Defines what to do when the Timer fires an event.
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		public void actionPerformed(ActionEvent event)
		{
			timeRemaining--;
			updateTimer();
		}
	});
	
	/**
	 * Constructor for a TimerPanel. Formats the timer label.
	 */
	public TimerPanel()
	{
		// Set the initial delay to 0 ms so that the timer starts
		// as soon as the user presses the "Start Timer" button
		timer.setInitialDelay(0);
		timerLabel.setFont(new Font(timerLabel.getName(), Font.PLAIN, 36));		
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 0;
		gc.gridy = 0;
		add(timerLabel, gc);
	}
	
	/**
	 * Starts this TimerPanel's timer.
	 * 
	 * @param timeSec how long the timer will count down for
	 */
	public void startTimer(int timeSec)
	{
		timeRemaining = timeSec;
		timer.start();
	}
	
	/**
	 * Stops the timer and resets the time back to 0 seconds
	 */
	public void resetTimer()
	{
		timer.stop();
		timerLabel.setText("00:00:00" + STRING_BUFFER);
	}
	
	/**
	 * Updates the timer label to display the current time remaining
	 */
	private void updateTimer()
	{
		String hours = intToFormattedString(timeRemaining / 3600);
		String minutes = intToFormattedString(timeRemaining % 3600 / 60);
		String seconds = intToFormattedString(timeRemaining % 3600 % 60);
		timerLabel.setText(hours + ":" + minutes + ":" + seconds + STRING_BUFFER);
		if (timeRemaining == 0)
		{
			timer.stop();
		}
	}
	
	/**
	 * Given an int, formats it to a String. If the int is below 10,
	 * a leading 0 is added to the front of it.
	 * 
	 * @param num an integer to convert to a String
	 * @return the integer converted to a String
	 */
	private String intToFormattedString(int num)
	{
		if (num < 10)
		{
			return "0" + Integer.toString(num);
		}
		else
		{
			return Integer.toString(num);
		}
	}
}
