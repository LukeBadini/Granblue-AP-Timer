import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.*;
import javax.swing.event.EventListenerList;

/**
 * A class defining the APInfoPanel. This contains the text fields
 * for uses to put their max and current AP as well as buttons to start
 * and reset the timer. Also includes definitions for what to do when
 * either of those buttons is pressed.
 * 
 * @author Luke Badini
 */
public class APInfoPanel extends JPanel
{
	private final int MINUTES_PER_AP = 5;
	private final int S_PER_MINUTE = 60;
	
	private EventListenerList listenerList = new EventListenerList();
	
	/**
	 * Constructor for an APInfoPanel. Formats the text fields and start/reset buttons and
	 * defines their behavior via anonymous functions.
	 */
	public APInfoPanel()
	{		
		JLabel maxAPLabel = new JLabel("Maximum AP: ");
		JLabel currentAPLabel = new JLabel("Current AP: ");
		
		final JTextField maxAPField = new JTextField(10);
		final JTextField currentAPField = new JTextField(10);
		
		JButton startTimerButton = new JButton("Start Timer");
		startTimerButton.addActionListener(new ActionListener()
		{
			/*
			 * Defines what the "Start Timer" button does when clicked.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					int currentAP = Integer.valueOf(currentAPField.getText());
					int maxAP = Integer.valueOf(maxAPField.getText());
					if (currentAP > maxAP)
					{
						JOptionPane.showMessageDialog(returnThisAPInfoPanel(),
							    "No AP to recover");
					}
					else
					{
						int apToRecover = maxAP - currentAP;
						int rechargeTimeS = apToRecover * MINUTES_PER_AP * S_PER_MINUTE;
						
						fireStartTimerEvent(new APInfoEvent(this, rechargeTimeS));
					}
				}
				catch (NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(returnThisAPInfoPanel(),
						    "Please use whole numbers for \"Rank\" and \"Current AP\" fields.",
						    "Text error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton resetTimerButton = new JButton("Reset Timer");
		resetTimerButton.addActionListener(new ActionListener()
		{
			/*
			 * Defines what the "Reset Timer" button does when clicked.
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e)
			{
				fireResetTimerEvent(new APInfoEvent(this, 0));
			}
		});
		
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		// First column
		gc.anchor = GridBagConstraints.LINE_END;
		gc.weightx = 0.5;
		gc.weighty = 10;
		gc.gridx = 0;
		gc.gridy = 0;
		add(maxAPLabel,gc);
		
		gc.gridx = 0;
		gc.gridy = 1;
		add(currentAPLabel, gc);
		
		// Second column
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		gc.gridy = 0;
		add(maxAPField, gc);
		
		gc.gridx = 1;
		gc.gridy = 1;
		add(currentAPField, gc);
		
		// Button row
		gc.weighty = 10;
		gc.anchor = GridBagConstraints.LINE_END;
		gc.gridx = 0;
		gc.gridy = 2;
		add(startTimerButton, gc);
		
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridx = 1;
		add(resetTimerButton, gc);
	}
	
	/**
	 * Used to get this APInfoPanel for use inside of anonymous functions.
	 * 
	 * @return this APInfoPanel
	 */
	private APInfoPanel returnThisAPInfoPanel()
	{
		return this;
	}
	
	/**
	 * Calculates the player's maximum AP based on their rank.
	 * see: http://gbf-wiki.com/index.php?%C9%AC%CD%D7EXP%C9%BD#de6e3e32
	 * 
	 * Currently deprecated because from a user perspective, it is easier
	 * to enter max AP rather than their rank since the AP gauge is
	 * almost always on-screen.
	 * 
	 * @param rank an integer corresponding to the player's rank
	 * @return the maximum amount of AP given the player's rank
	 */
	@Deprecated
	private int calculateMaxAP(int rank)
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
		
		return (int)maxAP;
	}
	
	/**
	 * Searches through the APInfoPanel's list of event listeners for a
	 * StartTimerListener. If one is found, that listener fires an event.
	 * 
	 * @param event the event corresponding to pressing the "Start Timer" button on the GUI
	 */
	public void fireStartTimerEvent(APInfoEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == StartTimerListener.class)
			{
				((StartTimerListener) listeners[i + 1]).startTimerEventOccured(event);
			}
		}
	}
	
	/**
	 * Searches through the APInfoPanel's list of event listeners for a
	 * ResetTimerListener. If one is found, that listener fires an event.
	 * 
	 * @param event the event corresponding to pressing the "Reset Timer" button on the GUI
	 */
	public void fireResetTimerEvent(APInfoEvent event)
	{
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2)
		{
			if (listeners[i] == ResetTimerListener.class)
			{
				((ResetTimerListener) listeners[i + 1]).resetTimerEventOccured(event);
			}
		}
	}
	
	
	/**
	 * Adds a StartTimerListener to this APInfoPanel's list
	 * of listeners.
	 * 
	 * @param listener the StartTimerListener to add
	 */
	public void addStartTimerListener(StartTimerListener listener)
	{
		listenerList.add(StartTimerListener.class, listener);
	}
	
	/**
	 * Given a StartTimerListener, removes it from this APInfoPanel's
	 * list of listeners.
	 * 
	 * @param listener the StartTimerListener to remove
	 */
	public void removeStartTimerListener(StartTimerListener listener)
	{
		listenerList.remove(StartTimerListener.class, listener);
	}
	
	/**
	 * Adds a ResetTimerListener to this APInfoPanel's list
	 * of listeners.
	 * 
	 * @param listener the ResetTimerListener to add
	 */
	public void addResetTimerListener(ResetTimerListener listener)
	{
		listenerList.add(ResetTimerListener.class, listener);
	}
	
	/**
	 * Given a ResetTimerListener, removes it from this APInfoPanel's
	 * list of listeners.
	 * 
	 * @param listener the ResetTimerListener to remove
	 */
	public void removeResetTimerListener(ResetTimerListener listener)
	{
		listenerList.remove(ResetTimerListener.class, listener);
	}
}
