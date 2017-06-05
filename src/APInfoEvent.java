import java.util.EventObject;

/**
 * A class defining an APInfoEvent
 * 
 * @see java.util.EventObject 
 * @author Luke Badini
 */
public class APInfoEvent extends EventObject
{
	private int rechargeTime;
	
	/**
	 * Constructor for an APInfoEvent
	 * 
	 * @param source the object that causes the event to fire
	 * @param rechargeTimeS an int corresponding to the recharge time
	 * 						in seconds for the event
	 */
	public APInfoEvent(Object source, int rechargeTimeS)
	{
		super(source);
		this.rechargeTime = rechargeTimeS;
	}
	
	/**
	 * @return an int corresponding to this event's recharge time in seconds
	 */
	public int getRechargeTime()
	{
		return rechargeTime;
	}
}
