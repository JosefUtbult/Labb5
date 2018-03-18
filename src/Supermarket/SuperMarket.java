package Supermarket;

import Simulator.Event.Event;
import Simulator.Event.Start;
import Simulator.Simulator;
import labb5.Queue;


/**
 * A class to simulate a super marked.
 */
public class SuperMarket {
    private Simulator simulator;
    private int maxpersonsinsupermarket = 10;
    private int personsinsupermarket;
    private Register register;

    public SuperMarket() {
        this.simulator = new Simulator(new SupermarketState());

        this.personsinsupermarket = 0;
        this.register = new Register(this.simulator);

        this.simulator.addEvent(new SuperStart(this.simulator));
        this.simulator.run();

    }

    public boolean enterStore(int personNumber) {
        if (this.personsinsupermarket < this.maxpersonsinsupermarket) {
            this.personsinsupermarket++;
            return true;
        }
        return false;
    }

    public SupermarketState getState() {
        return (SupermarketState) this.simulator.getState();
    }

    private class SuperStart extends Start {

        public SuperStart(Simulator simulator) {
            super(simulator);

        }

        @Override
        public void run() {
            this.simulator.addEvent(new GeneratePerson(((SupermarketState) simulator.getState()).clockPerson(), ((SupermarketState) simulator.getState()).getTimeKeeper().getAddPersonTime().next(), this.simulator));

        }

    }

    private class GeneratePerson extends Event {

        private Simulator simulator;
        private int personNumber;

        public GeneratePerson(int personNumber, int eventTime, Simulator simulator) {

            super(eventTime);
            this.simulator = simulator;
            this.personNumber = personNumber;
            System.out.format("Added person nr %d event at %d\n", personNumber, eventTime);
        }

        @Override
        public void run() {
            this.simulator.addEvent(new GeneratePerson(getState().clockPerson(), ((SupermarketState) simulator.getState()).getTimeKeeper().getAddPersonTime().next(), this.simulator));

            if (enterStore(personNumber)) {
                System.out.format("Im(%d) going in.\n", this.personNumber);
                this.simulator.addEvent(new BuyShit(personNumber, ((SupermarketState) simulator.getState()).getTimeKeeper().getByShitTime().next(), simulator));
            } else {
                System.out.println("Could not enter store. Too few children available for service.");
            }
        }
    }

    private class BuyShit extends Event {
        private int personNumber;
        private Simulator simulator;

        public BuyShit(int personNumber, int eventTime, Simulator simulator) {
            super(eventTime);
            this.simulator = simulator;
            this.personNumber = personNumber;
            System.out.format("Person number (%d) is looking for stuff.\n", this.personNumber);
        }

        @Override
        public void run() {
            System.out.format("Person number %d has collected a pair of socks and tampons and an ugandan child.\n", personNumber);
            register.add(this.personNumber);
        }
    }

    private class Register {
        private int numberOfRegisters = 5;
        private int registerInUse;
        private Simulator simulator;
        private Queue queue;

        public Register(Simulator simulator) {
            super();
            this.registerInUse = 0;
            this.simulator = simulator;
            this.queue = new Queue();
        }

        public void add(int personNumber) {

            if (registerInUse < numberOfRegisters) {
                this.simulator.addEvent(new ServeCustomer(personNumber, ((SupermarketState) simulator.getState()).getTimeKeeper().getServeTime().next(), simulator, this));
                this.registerInUse++;
            } else {
                queue.enqueue(personNumber);
            }

			/*if(this.queue.size() < numberOfRegisters - registerInUse){
				this.registerInUse ++;

				this.simulator.addEvent(new ServeCustomer(this.dequeue(), (int) erandomstream.customNext(), simulator, this));
			}
			*/
        }

        public void serve(int personNumber) {
            System.out.format("Served customer nr %d\n", personNumber);

            if (this.queue.size() > 0) {
                this.simulator.addEvent(new ServeCustomer(queue.dequeue(), ((SupermarketState) simulator.getState()).getTimeKeeper().getServeTime().next(), simulator, this));
            } else {
                this.registerInUse--;
            }

            personsinsupermarket--;


        }

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
    }

    private class CloseStore extends Event {

        public CloseStore(int eventTime) {
            super(eventTime);
        }
    }

}
