package Supermarket;

import Simulator.Simulator;
import labb5.Queue;

public class Register {
    private SuperMarket superMarket;
    private int numberOfRegisters = 5;
    private int registersInUse;
    private Simulator simulator;
    private Queue queue;

    public Register(SuperMarket superMarket, Simulator simulator) {
        super();
        this.superMarket = superMarket;
        this.registersInUse = 0;
        this.simulator = simulator;
        this.queue = new Queue();
    }

    public void add(int personNumber) {
        if (registersInUse < numberOfRegisters) {
            this.simulator.addEvent(new ServeCustomer(personNumber, ((SupermarketState) simulator.getState()).getTimeKeeper().getServeTime().next(), simulator, this));
            this.registersInUse++;
        } else {
            queue.enqueue(personNumber);
        }

        /*if(this.queue.size() < numberOfRegisters - registersInUse){
            this.registersInUse ++;

            this.simulator.addEvent(new ServeCustomer(this.dequeue(), (int) erandomstream.customNext(), simulator, this));
        }
        */
    }

    public void serve(int personNumber) {
        System.out.format("Served customer nr %d\n", personNumber);

        if (this.queue.size() > 0) {
            int nextPersonNumber = queue.dequeue();
            SupermarketState state = (SupermarketState) simulator.getState();
            int eventTime = state.getTimeKeeper().getServeTime().next();
            ServeCustomer serveCustomerEvent = new ServeCustomer(nextPersonNumber, eventTime, simulator, this);
            this.simulator.addEvent(serveCustomerEvent);
        } else {
            this.registersInUse--;
        }

        int personsInSuperMarket = superMarket.getPersonsInSuperMarket();
        superMarket.setPersonsInSuperMarket(personsInSuperMarket - 1);
    }
}
