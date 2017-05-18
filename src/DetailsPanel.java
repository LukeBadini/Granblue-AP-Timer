import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.EventListenerList;


public class DetailsPanel extends JPanel
{
	private final int MINUTES_PER_AP = 5;
	
	private EventListenerList listenerList = new EventListenerList();
	
	public DetailsPanel()
	{
		Dimension size = getPreferredSize();
		size.width = 250;
		setPreferredSize(size);
		
		setBorder(BorderFactory.createTitledBorder("Character Information"));
		
		JLabel rankLabel = new JLabel("Rank: ");
		JLabel currentAPLabel = new JLabel("Current AP: ");
		
		final JTextField rankField = new JTextField(10);
		final JTextField currentAPField = new JTextField(10);
		
		JButton startTimerButton = new JButton("Start Timer");
		startTimerButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int rank = Integer.valueOf(rankField.getText());
				int currentAP = Integer.valueOf(currentAPField.getText());
				int apToRecover = calculateAPToRecover(rank, currentAP);
				int rechargeTime = apToRecover * MINUTES_PER_AP;
				
				String info = Integer.toString(rechargeTime) + " minutes until AP is fully restored.\n" 
								+ "Recovering " + Integer.toString(apToRecover)+ " AP.\n";
				fireDetailEvent(new DetailEvent(this, info));
			}
		});
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// First column
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.5;
		gc.weighty = 0.5;
		gc.gridx = 0;
		gc.gridy = 0;
		add(rankLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(currentAPLabel, gc);
		
		// Second column
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridx = 1;
		gc.gridy = 0;
		add(rankField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(currentAPField, gc);
		
		// Final row
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.gridx = 1;
		gc.gridy = 2;
		add(startTimerButton, gc);
	}
	
	
	private int calculateAPToRecover(int rank, int currentAP)
	{
		float maxAP = 10;
		final int FIRSTGAP = 12;
		final int SECONDGAP = 39;
		final int THIRDGAP = 100;
		
		if (rank <= FIRSTGAP)
		{
			maxAP += (rank - 1) * 2;
		}
		else if (rank <= SECONDGAP)
		{
			maxAP += 10 + rank;
		}
		else if (rank <= THIRDGAP)
		{
			maxAP += Math.ceil(49 + ((rank - (SECONDGAP - 1)) / 2.0));
		}
		else
		{
			maxAP += Math.ceil(80 + ((rank - THIRDGAP) / 3.0));
		}
		
		return ((int)maxAP - currentAP);
	}
	
	
	public void fireDetailEvent(DetailEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == DetailListener.class)
			{
				((DetailListener) listeners[i + 1]).detailEventOccured(event); 
			}
		}
		
	}
	
	
	public void addDetailListener(DetailListener listener)
	{
		listenerList.add(DetailListener.class, listener);
	}
	
	
	public void removeDetailListener(DetailListener listener)
	{
		listenerList.remove(DetailListener.class, listener);
	}

}
