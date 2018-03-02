package Simulator.Event;

import java.util.Comparator;
import Simulator.Simulator;

import java.util.ArrayList;

public class EventHolder {

	private ArrayList<Event> eventQueue;

	public EventHolder(){
		this.eventQueue = new ArrayList<>();
	}

	public void add(Event event){

		this.eventQueue.add(event);
		this.eventQueue.sort(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {

				return Integer.compare(((Event)o1).getEventTime(), ((Event)o2).getEventTime());

			}
		});

		/*String temp = "eventQueue: ";

		for (Event currentEvent : this.eventQueue) {
			temp += currentEvent + ", ";
		}

		System.out.println(temp);
*/
		return;
	}

	public Event getEvent(){
		if(this.eventQueue.size() < 1){
			throw new Error("There is no more events.");
		}

		return this.eventQueue.get(0);
	}

	public void removeFirst(){

		this.eventQueue.remove(0);
	}

}