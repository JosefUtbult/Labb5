package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;
import labb5.FIFO;

//A class that holds all register-logic
class Register{
	private int numberOfRegisters;
	private int registerInUse;
	private SuperMarket superMarket;
	private FIFO fifo;	//The queue that holds personNumbers


	public Register(SuperMarket superMarket){
		super();
		this.numberOfRegisters = superMarket.getParam().numOfReg;
		this.registerInUse = 0;
		this.superMarket = superMarket;
		this.fifo = new FIFO();
	}

	public FIFO getFifo(){
		return fifo;
	}


	public void add(int personNumber){

		if(registerInUse < numberOfRegisters){
			this.registerInUse ++;

			this.superMarket.getSimulator().addEvent(new ServeCustomer(personNumber, this.superMarket.getState().getTimeKeeper().getServeTime().next(), superMarket.getSimulator(), this));
		}
		else{
			fifo.add(personNumber);
			this.superMarket.getState().clockPeopleWhoQueued();
			this.superMarket.getState().appendPersonQueueTime(personNumber);

		}

		this.superMarket.getState().getTimeRegistersUnused().setRegistersInUse(registerInUse);
		((SupermarketState)this.superMarket.getSimulator().getState()).setNrOfRegistersInUse(this.registerInUse);

			/*if(this.queue.size() < numberOfRegisters - registerInUse){
				this.registerInUse ++;

				this.simulator.addEvent(new ServeCustomer(this.get(), (int) erandomstream.customNext(), simulator, this));
			}
			*/
	}

	public void serve(int personNumber){


		if(this.fifo.size() > 0){
			int person = fifo.get();
			this.superMarket.getState().removePersonQueueTime(person);
			this.superMarket.getSimulator().addEvent(new ServeCustomer((int)person,((SupermarketState)this.superMarket.getSimulator().getState()).getTimeKeeper().getServeTime().next(), this.superMarket.getSimulator(), this));

		}
		else {

			this.registerInUse --;
		}

		this.superMarket.setPersonsinsupermarket(this.superMarket.getPersonsinsupermarket() - 1);

		if(this.superMarket.getState().getPersonsInStore() == 1 && !this.superMarket.getOpen()){
			this.superMarket.getState().getTimeRegistersUnused().setRegistersInUse(numberOfRegisters);
		}
		else {
			this.superMarket.getState().getTimeRegistersUnused().setRegistersInUse(this.registerInUse);
		}

		((SupermarketState)this.superMarket.getState()).setNrOfRegistersInUse(this.registerInUse);
		((SupermarketState)this.superMarket.getState()).clockLeave();

	}


	public int getRegisterInUse(){
		return this.registerInUse;
	}

	class ServeCustomer extends Event {

		private Simulator simulator;
		private Register register;
		private int personNumber;

		public ServeCustomer(int personNumber, double eventTime, Simulator simulator, Register register){
			super(eventTime);

			this.personNumber = personNumber;
			this.simulator = simulator;
			this.register = register;
		}

		@Override
		public void run(){
			register.serve(this.personNumber);
		}

		@Override
		public String toString() {
			return String.format("Served person		%d", this.personNumber);

		}
	}
}