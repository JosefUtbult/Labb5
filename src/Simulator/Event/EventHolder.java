package Simulator.Event;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Holds a series of events, and appends them in the order of there execution.
 */
public class EventHolder {

    private ArrayList<Event> eventQueue;

    public EventHolder() {
        this.eventQueue = new ArrayList<>();
    }

    /**
     * Adds an event and places it in the order of its execution
     *
     * @param event
     */
    public void add(Event event) {
        this.eventQueue.add(event);
        this.eventQueue.sort(Comparator.comparingInt(Event::getEventTime));

		/*String temp = "eventQueue: ";

		for (Event currentEvent : this.eventQueue) {
			temp += currentEvent + ", ";
		}

		System.out.println(temp);
*/
    }

    /**
     * @return Returns the first event in the series
     */
    public Event getEvent() {
        if (this.eventQueue.size() < 1) {
            throw new Error("There is no more events.");
        }

        return this.eventQueue.get(0);
    }

    /**
     * Removes the first event in the series.
     */
    public void removeFirst() {
        this.eventQueue.remove(0);
    }

}