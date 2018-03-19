package Supermarket;

import Simulator.State.State;

public class SupermarketState extends State {

    private int personClocker;
    private TimeKeeper timeKeeper;

    public SupermarketState() {
        super();
        personClocker = 0;
        timeKeeper = new TimeKeeper(this);
    }

    public int clockPerson() {
        personClocker++;
        return this.personClocker;
    }

    public TimeKeeper getTimeKeeper() {
        return timeKeeper;
    }

}
