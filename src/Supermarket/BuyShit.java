package Supermarket;

import Simulator.Event.Event;
import Simulator.Simulator;

//An event that demonstrates that a customer has collected wares. Adds the person to the queue when executed
class BuyShit extends Event {
	private int personNumber;
	private SuperMarket superMarket;

	BuyShit(int personNumber, double eventTime, SuperMarket superMarket) {
		super(eventTime);
		this.superMarket = superMarket;
		this.personNumber = personNumber;
	}
	@Override
	public void run() {
		this.superMarket.getRegister().add(this.personNumber);
	}

	@Override
	public String toString() {
		return String.format("Picked up shit		%d", this.personNumber);
	}
}
