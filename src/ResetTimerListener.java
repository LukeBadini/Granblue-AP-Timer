import java.util.EventListener;


public interface ResetTimerListener extends EventListener
{
	public void resetTimerEventOccured(APInfoEvent event);
}
