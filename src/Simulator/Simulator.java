package Simulator;

import Simulator.Event.Event;
import Simulator.Event.EventHolder;
import Simulator.State.State;

/**
 * A class to hold a simulation. It has the possibility to add an event that is to be executed at a specific time and
 * appends this to a queue of events that is placed in order of timing and is later executed at its correlated time.
 */
public class Simulator {

    private EventHolder eventHolder;
    private State state;

    public Simulator(State state) {
        this.eventHolder = new EventHolder();
        this.state = state;

    }

    public State getState() {
        return this.state;
    }

    /**
     * Runs the simulation. Takes the next event, waits until it is to be executed, and then runs the function "run"
     */
    public void run() {
        while (this.state.getRun()) {
            try {
                while (this.state.getTime() < this.eventHolder.getEvent().getEventTime()) {
                }
            } catch (Error e) {
                break;
            }

            Event temp = this.eventHolder.getEvent();
            this.eventHolder.removeFirst();

            temp.run();
        }
    }

    /**
     * Forwards an event to the eventHolder.
     *
     * @param event
     */
    public void addEvent(Event event) {
        this.eventHolder.add(event);

    }

}
