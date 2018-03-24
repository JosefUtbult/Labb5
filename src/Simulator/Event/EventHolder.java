package Simulator.Event;

import java.util.Comparator;
import Simulator.Simulator;

import java.util.ArrayList;


/**
 * Holds a series of events, and appends them in the order of there execution.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class EventHolder {

	private ArrayList<Event> eventQueue;

	/**
	 * Creates the event holder
	 */
	public EventHolder(){
		this.eventQueue = new ArrayList<>();
	}

	/**
	 * Adds an event and places it in the order of its execution
	 * @param event
	 */
	public void add(Event event){

		this.eventQueue.add(event);
		this.eventQueue.sort(new Comparator<Event>() {
			@Override
			public int compare(Event o1, Event o2) {

				return Double.compare(((Event)o1).getEventTime(), ((Event)o2).getEventTime());

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

	/**
	 * @return Returns the first event in the series
	 */
	public Event getEvent(){
		if(this.eventQueue.size() < 1){
			throw new Error("There is no more events.");
		}

		return this.eventQueue.get(0);
	}

	/**
	 * Removes the first event in the series.
	 */
	public void removeFirst(){

		this.eventQueue.remove(0);
	}

}