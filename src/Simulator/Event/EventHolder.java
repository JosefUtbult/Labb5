package Simulator.Event;

import java.util.Comparator;
import Simulator.Simulator;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;

public class EventHolder {

	private ArrayList<Event> eventQueue;

	public EventHolder(){
		this.eventQueue = new ArrayList<>();
	}

	public void add(Event event){

		TimeComparitor timeComparitor = new TimeComparitor();

		this.eventQueue.add(event);
		this.eventQueue.sort(timeComparitor);
	}

	public Event getEvent(){
		return this.eventQueue.get(0);
	}

	public void removeFirst(){

		this.eventQueue.remove(0);
	}

}


class TimeComparitor implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {

		if(o1.getClass() != Event.class || o2.getClass() != Event.class){
			throw new  ValueException("The objects need to be of the type \"Event\".");
		}

		return Integer.compare(((Event)o1).getEventTime(), ((Event)o2).getEventTime());

	}
}