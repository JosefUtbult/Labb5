package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;
import Simulator.State.State;
import labb5.FIFO;
import random.ExponentialRandomStream;

public class SuperMarket {
	private Simulator simulator;
	private State state;
	private ExponentialRandomStream erandomstream;
	private int maxpersonsinsupermarket = 10;
	private int personsinsupermarket;
	private Register register;

	public SuperMarket(){
		this.state = new State();
		this.simulator = new Simulator(state);
		this.erandomstream = new ExponentialRandomStream(10000);
		this.personsinsupermarket = 0;
		this.register = new Register(this.simulator);

		for(int i = 0; i < 40; i++){
			this.simulator.addEvent(new GoIn(i, (int) (erandomstream.customNext() + simulator.getTime()), simulator));
		}

		this.simulator.run();

	}

	public boolean enterStore() {
		if(this.personsinsupermarket < this.maxpersonsinsupermarket) {
			this.personsinsupermarket++;
			return true;
		}
		return false;
	}



	private class GoIn extends Event{

		private Simulator simulator;
		private int personNumber;

		public GoIn(int personNumber, int eventTime, Simulator simulator) {

			super(eventTime, personNumber);
			this.simulator = simulator;
			this.personNumber = personNumber;
			System.out.format("Added person nr %d event at %d\n", personNumber, eventTime);
		}

		@Override
		public void run(){
			if (enterStore()) {
				System.out.format("Im(%d) going in.\n", this.personNumber);
				this.simulator.addEvent(new BuyShit(personNumber, (int) (erandomstream.customNext() + simulator.getTime()), simulator));
			}
			else {
				System.out.println("Could not enter store. Too few children available for service.");
			}
		}
	}

	private class BuyShit extends Event {
		private int personNumber;
		private Simulator simulator;
		public BuyShit(int personNumber, int eventTime, Simulator simulator) {
			super(eventTime, personNumber);
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

	private class Register{
		private int numberOfRegisters = 5;
		private int registerInUse;
		private Simulator simulator;
		private FIFO fifo;

		public Register(Simulator simulator){
			super();
			this.registerInUse = 0;
			this.simulator = simulator;
			this.fifo = new FIFO();
		}

		public void add(int personNumber){

			if(registerInUse < numberOfRegisters){
				this.simulator.addEvent(new ServeCustomer(personNumber, (int) (erandomstream.customNext() + simulator.getTime()), simulator, this));
				this.registerInUse ++;
			}
			else{
				fifo.add(personNumber);
			}

			/*if(this.queue.size() < numberOfRegisters - registerInUse){
				this.registerInUse ++;

				this.simulator.addEvent(new ServeCustomer(this.get(), (int) erandomstream.customNext(), simulator, this));
			}
			*/
		}

		public void serve(int personNumber){
			System.out.format("Served customer nr %d\n", personNumber);

			if(this.fifo.size() > 0){
				this.simulator.addEvent(new ServeCustomer(fifo.get(), (int)(erandomstream.customNext() + simulator.getTime()), simulator, this));
			}
			else {
				this.registerInUse --;
			}

			personsinsupermarket --;


		}

		class ServeCustomer extends Event{

			private Simulator simulator;
			private Register register;

			public ServeCustomer(int personNumber, int eventTime, Simulator simulator, Register register){
				super(eventTime, personNumber);

				this.personNumber = personNumber;
				this.simulator = simulator;
				this.register = register;
			}

			@Override
			public void run(){
				register.serve(this.personNumber);
			}
		}
	}

}
