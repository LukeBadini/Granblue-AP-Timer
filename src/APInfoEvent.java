import java.util.EventObject;


public class APInfoEvent extends EventObject
{
	private int rechargeTime;
	
	public APInfoEvent(Object source, int rechargeTimeMS)
	{
		super(source);
		this.rechargeTime = rechargeTimeMS;
	}
	
	
	public int getRechargeTime()
	{
		return rechargeTime;
	}
}
