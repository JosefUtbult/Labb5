package Supermarket;

import Simulator.Event.Start;
import Simulator.Simulator;

class SuperStart extends Start {

    private SuperMarket superMarket;

    public SuperStart(SuperMarket superMarket, Simulator simulator) {
        super(simulator);
        this.superMarket = superMarket;
    }

    @Override
    public void run() {
        SupermarketState state = (SupermarketState) simulator.getState();
        int eventTime = state.getTimeKeeper().getAddPersonTime().next();
        GeneratePerson event = new GeneratePerson(superMarket, state.clockPerson(), eventTime, this.simulator);
        this.simulator.addEvent(event);
    }

}
