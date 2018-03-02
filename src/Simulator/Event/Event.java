package Simulator.Event;

public class Event {

	protected int personNumber;
	protected int eventTime;

	public Event(int eventTime, int personNumber){
		this.eventTime = eventTime;
		this.personNumber = personNumber;

		//System.out.format("Added event at %d\n", this.eventTime);
	}

	public void run(){
		System.out.println("This is a default action. You should probably override the run function.");
	}

	public int getEventTime(){
		return this.eventTime;
	}

	@Override
	public String toString() {
		return String.format("%d (%d)", this.personNumber, this.eventTime);
	}
}
