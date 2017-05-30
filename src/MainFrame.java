import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class MainFrame extends JFrame
{
	private APInfoPanel apInfoPanel;
	private TimerPanel timerPanel;
	
	public MainFrame(String title)
	{
		super(title);
		
		// Set layout manager
		setLayout(new BorderLayout());
		
		// Create Swing components		
		timerPanel = new TimerPanel();
		
		apInfoPanel = new APInfoPanel();
		apInfoPanel.addStartTimerListener(new StartTimerListener()
		{
			public void startTimerEventOccured(APInfoEvent event)
			{
				int rechargeTime = event.getRechargeTime();
				timerPanel.startTimer(rechargeTime);
			}
		});
		
		apInfoPanel.addResetTimerListener(new ResetTimerListener()
		{
			public void resetTimerEventOccured(APInfoEvent event)
			{
				timerPanel.resetTimer();
			}
		});
		
		
		// Add Swing components to content pane
		Container container = getContentPane();
		container.add(timerPanel, BorderLayout.EAST);
		container.add(apInfoPanel, BorderLayout.CENTER);
	}
}
