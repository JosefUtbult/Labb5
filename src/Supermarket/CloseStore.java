package Supermarket;

import Simulator.Event.Event;

class CloseStore extends Event {

	private SuperMarket superMarket;

	public CloseStore(double eventTime, SuperMarket superMarket) {
		super(eventTime);
		this.superMarket = superMarket;
	}

	@Override
	public void run(){
		superMarket.setOpen(false);
		superMarket.getState().setStoreOpen(false);

	}

	@Override
	public String toString() {
		return String.format("Closing store		-");

	}
}