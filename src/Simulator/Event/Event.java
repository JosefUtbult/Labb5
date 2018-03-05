package Simulator.Event;

/**
 *An event that is extended to other classes. It has an eventTime, a time it is to be executed, and a run, a function
 *that is called after the eventTime
 */
public class Event {
	protected int eventTime;

	public Event(int eventTime){
		this.eventTime = eventTime;

		//System.out.format("Added event at %d\n", this.eventTime);
	}

	public void run(){
		System.out.println("This is a default action. You should probably override the run function.");
	}

	public int getEventTime(){
		return this.eventTime;
	}

}
