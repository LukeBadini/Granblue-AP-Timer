import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class MainFrame extends JFrame
{
	private APInfoPanel detailsPanel;
	private TimerPanel timerPanel;
	
	public MainFrame(String title)
	{
		super(title);
		
		// Set layout manager
		setLayout(new BorderLayout());
		
		// Create Swing components
//		final JTextArea textArea = new JTextArea();
		
		timerPanel = new TimerPanel();
		
		detailsPanel = new APInfoPanel();
		detailsPanel.addDetailListener(new APInfoListener()
		{
			public void detailEventOccured(APInfoEvent event)
			{
				int rechargeTime = event.getRechargeTime();
				timerPanel.startTimer(rechargeTime);
			}
		});
		
		
		
		// Add Swing components to content pane
		Container container = getContentPane();
		container.add(timerPanel, BorderLayout.EAST);
		container.add(detailsPanel, BorderLayout.WEST);	
		
	}
}
