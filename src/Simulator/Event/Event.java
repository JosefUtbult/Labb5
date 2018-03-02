package Simulator.Event;

public class Event {

	private int eventTime;

	public Event(int eventTime){
		this.eventTime = eventTime;
	}

	public void run(){
		System.out.println("This is a default action. You should probably override the run function.");
	}

	public int getEventTime(){
		return this.eventTime;
	}

}
