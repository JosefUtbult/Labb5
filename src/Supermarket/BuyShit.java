package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;

class BuyShit extends Event {
    private SuperMarket superMarket;
    private int personNumber;
    private Simulator simulator;

    public BuyShit(SuperMarket superMarket, int personNumber, int eventTime, Simulator simulator) {
        super(eventTime);

        this.superMarket = superMarket;
        this.simulator = simulator;
        this.personNumber = personNumber;

        System.out.format("Person number (%d) is looking for stuff.\n", this.personNumber);
    }

    @Override
    public void run() {
        System.out.format("Person number %d has collected a pair of socks and tampons and an ugandan child.\n", personNumber);

        Register register = superMarket.getRegister();
        register.add(this.personNumber);
    }
}
