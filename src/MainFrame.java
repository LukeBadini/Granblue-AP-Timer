import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class MainFrame extends JFrame
{
	private DetailsPanel detailsPanel;
	
	public MainFrame(String title)
	{
		super(title);
		
		// Set layout manager
		setLayout(new BorderLayout());
		
		// Create Swing components
		final JTextArea textArea = new JTextArea();
		
		detailsPanel = new DetailsPanel();
		detailsPanel.addDetailListener(new DetailListener()
		{
			public void detailEventOccured(DetailEvent event)
			{
				String text = event.getText();
				textArea.append(text);
			}
		});
		
		// Add Swing components to content pane
		Container container = getContentPane();
		container.add(textArea, BorderLayout.CENTER);
		container.add(detailsPanel, BorderLayout.WEST);
		
		// Add behavior
		
	}
}
