package Supermarket;

import Simulator.Event.Start;
import Simulator.Simulator;

/**
 * Overrides the standard start-event
 */
public class SuperStart extends Start {

	private SuperMarket superMarket;

	SuperStart(SuperMarket superMarket) {
		super(superMarket.getSimulator(), false);
		superMarket.getSimulator().addEvent(new SuperStop(superMarket));
		this.superMarket = superMarket;
	}

	/**
	 * Adds starting events to the simulation
	 */
	@Override
	public void run(){
		this.simulator.addEvent(new CloseStore(this.superMarket.getParam().openTime, this.superMarket));
		this.simulator.addEvent(new GeneratePerson(((SupermarketState)simulator.getState()).clockPerson(),
				((SupermarketState)simulator.getState()).getTimeKeeper().getAddPersonTime().next(), this.superMarket));
		this.superMarket.getState().getTimeRegistersUnused().setRegistersInUse(0);
	}

	/**
	 *
	 * @return The event name and id
	 */
	@Override
	public String toString() {
		return "Starting simulation	-";
	}

}
