package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;

class GeneratePerson extends Event {

    private SuperMarket superMarket;
    private Simulator simulator;
    private int personNumber;

    public GeneratePerson(SuperMarket superMarket, int personNumber, int eventTime, Simulator simulator) {
        super(eventTime);

        this.superMarket = superMarket;
        this.simulator = simulator;
        this.personNumber = personNumber;
        System.out.format("Added person nr %d event at %d\n", personNumber, eventTime);
    }

    @Override
    public void run() {
        addGeneratePersonEvent();

        if (this.superMarket.enterStore(personNumber)) {
            addBuyShitEvent();
        } else {
            System.out.println("Could not enter store. Too few children available for service.");
        }
    }

    private void addBuyShitEvent() {
        System.out.format("Im(%d) going in.\n", this.personNumber);
        SupermarketState state1 = (SupermarketState) simulator.getState();
        int eventTime = state1.getTimeKeeper().getByShitTime().next();
        BuyShit buyShitEvent = new BuyShit(this.superMarket, personNumber, eventTime, simulator);
        this.simulator.addEvent(buyShitEvent);
    }

    private void addGeneratePersonEvent() {
        SupermarketState state = (SupermarketState) simulator.getState();
        int eventTime = state.getTimeKeeper().getAddPersonTime().next();
        this.personNumber += 1;
        GeneratePerson event = new GeneratePerson(this.superMarket, personNumber, eventTime, this.simulator);
        this.simulator.addEvent(event);
    }
}
