package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;

class ServeCustomer extends Event {

    private Simulator simulator;
    private Register register;
    private int personNumber;

    public ServeCustomer(int personNumber, int eventTime, Simulator simulator, Register register) {
        super(eventTime);

        this.personNumber = personNumber;
        this.simulator = simulator;
        this.register = register;
    }

    @Override
    public void run() {
        register.serve(this.personNumber);
    }
}
