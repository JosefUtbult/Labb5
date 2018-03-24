package Supermarket;

import Simulator.Event.Event;
import Simulator.Event.Start;
import Simulator.Event.Stop;
import Simulator.Simulator;
import labb5.FIFO;

/**
 * A class to simulate a super marked.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class SuperMarket {
	private Simulator simulator;
	private int maxpersonsinsupermarket;
	private int personsinsupermarket;
	private Register register;
	private boolean open;
	private Param param;
	private OutputParam outputParam;

	/**
	 * Runs the simulated supermarket and puts the results in the outputParam object.
	 * @param param A param of type Param to hold the params
	 */
	public SuperMarket(Param param){

		this.param = param;
		this.open = true;
		this.simulator = new Simulator(new SupermarketState(param));	//Generates some stuff
		this.outputParam = new OutputParam();
		this.register = new Register(this);
		this.maxpersonsinsupermarket = param.maxNumOfPpl;

		this.getState().setStoreOpen(true);
		((SupermarketState)this.simulator.getState()).setFIFO(this.register.getFifo());

		this.personsinsupermarket = 0;

		this.simulator.addEvent(new SuperStart(this));	//Adds a start event and begins to run
		this.simulator.run();
	}

	/**
	 * Guess
	 * @return the results of the simulation in an OutputParam-object.
	 */
	public OutputParam getOutput(){
		return this.outputParam;
	}

	//Checks if the store is full
	boolean enterStore(int personNumber) {
		if(this.personsinsupermarket < this.maxpersonsinsupermarket) {
			this.personsinsupermarket++;
			return true;
		}
		return false;
	}

	/**
	 * @return The current state-object.
	 */
	public SupermarketState getState(){
		return (SupermarketState) this.simulator.getState();
	}


	public boolean getOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public Register getRegister(){
		return this.register;
	}

	public Simulator getSimulator(){
		return this.simulator;
	}

	public void setSimulator(Simulator simulator){
		this.simulator = simulator;
	}

	public void setPersonsinsupermarket(int personsinsupermarket){
		this.personsinsupermarket = personsinsupermarket;
	}

	public int getPersonsinsupermarket(){
		return this.personsinsupermarket;
	}

	public void setMaxpersonsinsupermarket(int maxpersonsinsupermarket){
		this.maxpersonsinsupermarket = maxpersonsinsupermarket;
	}

	public int getMaxpersonsinsupermarket(){
		return this.maxpersonsinsupermarket;
	}


}



