package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;

//An event that upon execution appends a new instance of itself if the store is open, and a BuyShit event
class GeneratePerson extends Event {

	private SuperMarket superMarket;
	private int personNumber;

	GeneratePerson(int personNumber, double eventTime, SuperMarket superMarket) {

		super(eventTime);
		this.superMarket = superMarket;
		this.personNumber = personNumber;
	}

	@Override
	public void run() {
		if (superMarket.getOpen()) {
			this.superMarket.getSimulator().addEvent(new GeneratePerson(this.superMarket.getState().clockPerson(), this.superMarket.getState().getTimeKeeper().getAddPersonTime().next(), this.superMarket));
		}

		if (this.superMarket.enterStore(personNumber) && this.superMarket.getOpen()) {
			this.superMarket.getState().clockPersonEnteredClocker();    //Clocks that a new person has entered the store
			this.superMarket.getSimulator().addEvent(new BuyShit(personNumber, this.superMarket.getState().getTimeKeeper().getByShitTime().next(), this.superMarket));
		} else if (this.superMarket.getOpen()) {
			//Clocks that the person couldnt go in
			this.superMarket.getState().clockCouldntGoIn();
		}
	}

	@Override
	public String toString() {
		return String.format("Generated person	%d", this.personNumber);
	}

}
