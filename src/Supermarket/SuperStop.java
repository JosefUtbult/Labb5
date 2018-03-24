package Supermarket;

import Simulator.Event.Stop;

/**
 * Overrides the standard stop-event.
 */

public class SuperStop extends Stop {
	SuperMarket superMarket;

	SuperStop(SuperMarket superMarket){
		this.superMarket = superMarket;
	}

	@Override
	public void run(){


		this.superMarket.getOutput().nrOfPeople = this.superMarket.getState().clockPerson() - 1;
		this.superMarket.getOutput().nrShopped = this.superMarket.getState().getPersonsLeft();
		this.superMarket.getOutput().nrNotEnter = this.superMarket.getState().getCouldntGoIn();
		this.superMarket.getOutput().totalTime = this.superMarket.getState().getTime();
		this.superMarket.getOutput().avrQueueTime = this.superMarket.getState().getQueueTime() / (this.superMarket.getState().getPeopleWhoQueued() > 0 ? this.superMarket.getState().getPeopleWhoQueued() : 1);
		this.superMarket.getOutput().avaRegister = this.superMarket.getState().getTime() - this.superMarket.getState().getTimeRegistersUnused().getTime();

		this.superMarket.getState().setRun(false);
	}
}
