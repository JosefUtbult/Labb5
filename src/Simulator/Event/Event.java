package Simulator.Event;

/**
 *An event that is extended to other classes. It has an eventTime, a time it is to be executed, and a run, a function
 *that is called after the eventTime
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class Event {
	protected double eventTime;

	/**
	 * Generated an event with an execution time
	 * @param eventTime
	 */
	public Event(double eventTime){
		this.eventTime = eventTime;

	}

	/**
	 * A function that is executed at a specified time meant to be overridden
	 */
	public void run(){
		System.out.println("This is a default action. You should probably override the run function.");
	}

	/**
	 *
	 * @return the event time
	 */
	public double getEventTime(){
		return this.eventTime;
	}

	/**
	 *
	 * @return event name meant to be overridden
	 */
	@Override
	public String toString() {
		return "Unidentified event";
	}
}
