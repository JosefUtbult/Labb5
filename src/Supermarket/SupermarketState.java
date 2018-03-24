package Supermarket;

import Simulator.State.State;
import labb5.FIFO;
import random.ExponentialRandomStream;
import random.UniformRandomStream;

import java.util.ArrayList;

/**
 * A extention of the class State, that holds a larger number of variables.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class SupermarketState extends State {

	private int personClocker;
	private int personLeaveClocker;
	private int personCouldntGoInClocker;
	private int personEnteredClocker;
	private TimeKeeper timeKeeper;
	private int nrOfRegistersInUse;
	private ClockParser timeRegistersUnused;
	private double OldQueueTime;
	private int peopleWhoQueued;
	private FIFO queue;
	private boolean storeOpen;
	private ArrayList<double[]> personsInQueue;


	/**
	 * Generates a state object.
	 * @param param the parameters of the simulation.
	 */
	public SupermarketState(Param param){
		super();

		this.personClocker = -1;
		this.personLeaveClocker = 0;
		this.nrOfRegistersInUse = 0;
		this.personEnteredClocker = 0;
		this.OldQueueTime = 0;
		this.peopleWhoQueued = 0;
		this.personCouldntGoInClocker = 0;
		this.storeOpen = false;

		this.queue = new FIFO();
		this.timeKeeper = new TimeKeeper(param);
		if(param.view){
			this.stateViewer = new SupermarketStateViewer(this, param);
		}
		this.timeRegistersUnused = new ClockParser(this, param);
		this.personsInQueue = new ArrayList<>();
	}

	/**
	 * Adds another person to the simulation and gives it a number
	 * @return next persons number
	 */
	public int clockPerson(){
		this.personClocker ++;
		return this.personClocker;
	}

	/**
	 * Stores the queue from Register
	 * @param fifo the queue
	 */
	public void setFIFO(FIFO fifo){
		this.queue = fifo;
	}

	/**
	 * Stringifies a queue to its components
	 * @return The queue in string form
	 */
	public String fifoToString(){
		String s = "(";
		for(int i = 0; i<queue.size(); i++){
			s += Integer.toString((int)queue.getInstance(i));

			if(i != queue.size() - 1){
				s += ", ";
			}

		}
		return s + ")";
	}

	/**
	 *
	 * @return The queue size
	 */
	public int queueSize(){
		return queue.size();
	}

	/**
	 * Counts the number of persons that has shopped and left the supermarket
	 */
	public void clockLeave(){
		this.personLeaveClocker ++;
	}

	/**
	 * Counts the number of people that couldn't go in
	 */
	public void clockCouldntGoIn(){
		this.personCouldntGoInClocker ++;
	}

	/**
	 *
	 * @return the number of persons that couldn't go in
	 */
	public int getCouldntGoIn(){
		return personCouldntGoInClocker;
	}

	/**
	 *
	 * @return the number of persons in the store
	 */
	public int getPersonsInStore(){
		return this.personEnteredClocker - this.personLeaveClocker;
	}

	/**
	 *
	 * @return the number of persons that has shopped and left the store
	 */
	public int getPersonsLeft(){
		return this.personLeaveClocker;
	}

	/**
	 *
	 * @return the number of people that has queued
	 */
	public int getPeopleWhoQueued(){
		return this.peopleWhoQueued;
	}

	/**
	 * Counts the number of people that has queued
	 */
	public void clockPeopleWhoQueued(){
		peopleWhoQueued ++;
	}

	/**
	 * Appends a person and the time it begins to queue, used to calculate the time it queued
	 * @param person a personNumber
	 */
	public void appendPersonQueueTime(double person){
		this.personsInQueue.add(new double[] {(double)person, this.getTime()});
	}

	/**
	 * Removes customers queue time and saves it in the total queue time.
	 * @param person
	 */
	public void removePersonQueueTime(double person){

		for (int i = 0; i < this.personsInQueue.size(); i++ ) {
			if((int)this.personsInQueue.get(i)[0] == person){
				this.OldQueueTime += this.getTime() - this.personsInQueue.get(i)[1];
				this.personsInQueue.remove(this.personsInQueue.get(i));
			}
		}
	}

	/**
	 *
	 * @return if the store is open or not
	 */

	public boolean getStoreOpen() {
		return storeOpen;
	}

	/**
	 * Sets if the store is to be closed.
	 * @param storeOpen if store is open
	 */

	public void setStoreOpen(boolean storeOpen) {
		this.storeOpen = storeOpen;
	}

	/**
	 * Calculates current total queue time
	 * @return current queue time
	 */

	public double getQueueTime(){
		double personsInQueueTime = 0;

		for (double[] instance : this.personsInQueue) {
			personsInQueueTime += this.getTime() - instance[1];
		}
		return this.OldQueueTime + personsInQueueTime;
	}

	/**
	 *
	 * @return returns the object that holds the timing objects variables
	 */

	public TimeKeeper getTimeKeeper() {
		return timeKeeper;
	}

	/**
	 *
	 * @return numbers of register in use
	 */

	public int getNrOfRegistersInUse() {
		return nrOfRegistersInUse;
	}

	/**
	 *
	 * @return time registers remained unused
	 */

	public ClockParser getTimeRegistersUnused() {
		return timeRegistersUnused;
	}

	/**
	 * Sets the current number of registers in use
	 * @param nrOfRegistersInUse number of registers in use
	 */

	public void setNrOfRegistersInUse(int nrOfRegistersInUse) {
		this.nrOfRegistersInUse = nrOfRegistersInUse;
	}

	/**
	 *
	 * @return hows many people have entered the store
	 */

	public int getPersonEnteredClocker() {
		return personEnteredClocker;
	}

	/**
	 * Clocks/counts the number of people that have entered
	 */

	public void clockPersonEnteredClocker() {
		this.personEnteredClocker ++;
	}

	/**
	 * Holds the time keeping of when the registers are available
	 */
	public class ClockParser{
		double storedTime;
		double lastTime;
		double lastRegistersInUse;
		int registersInUse;
		Param param;
		SupermarketState state;

		/**
		 * Initiates the clock parser
		 * @param state
		 * @param param
		 */
		public ClockParser(SupermarketState state, Param param){
			this.state = state;
			this.param = param;

			this.storedTime = 0;
			this.lastTime = 0;
			this.registersInUse = 0;

		}

		/**
		 * Sets how many registers are in use
		 * @param nrOfRegistersInUse
		 */
		public void setRegistersInUse(int nrOfRegistersInUse) {

			this.registersInUse = nrOfRegistersInUse;

		}

		/**
		 * Calculates the current time registers have been available and appends it
		 * @return the time registers been available
		 */
		public double getTime(){

			double timeDifference = this.state.getTime() - lastTime;
			double delta = timeDifference * (this.param.numOfReg - this.registersInUse);

			this.storedTime += delta;
			this.lastTime = this.state.getTime();

			return this.storedTime;

		}

	}

	/**
	 * Holds the different time keeping objects
	 */
	class TimeKeeper {
		private AddPersonTime addPersonTime;
		private ByShitTime byShitTime;

		private ServeTime serveTime;

		public TimeKeeper(Param param){
			this.addPersonTime = new AddPersonTime(param);
			this.byShitTime = new ByShitTime(param);
			this.serveTime = new ServeTime(param);
		}

		public AddPersonTime getAddPersonTime() {
			return addPersonTime;
		}

		public ByShitTime getByShitTime() {
			return byShitTime;
		}

		public ServeTime getServeTime() {
			return serveTime;
		}
	}

	/**
	 * An object that holds an exponential random stream to be used when creating new people
	 */
	class AddPersonTime{

		private ExponentialRandomStream uniformRandomStream;

		public AddPersonTime(Param param){
			this.uniformRandomStream = new ExponentialRandomStream(param.ankomst , param.seed);
		}
		public double next(){
			return  uniformRandomStream.next() + getTime();
		}

	}

	/**
	 * An object that holds an uniform random stream to be used to decide how long it takes
	 * to gather your groceries
	 */
	class ByShitTime{


		private UniformRandomStream uniformRandomStream;

		public ByShitTime(Param param){
			this.uniformRandomStream = new UniformRandomStream(param.plock[0], param.plock[1], param.seed);
		}

		public double next(){
			return (uniformRandomStream.next() + getTime());
		}

	}

	/**
	 * An object that holds an uniform random stream to be used to decide how long it takes
	 * to serve a customer at the registers
	 */
	class ServeTime{


		private UniformRandomStream uniformRandomStream;

		public ServeTime(Param param){
			this.uniformRandomStream = new UniformRandomStream(param.scann[0], param.scann[1], param.seed);
		}

		public double next(){
			return (uniformRandomStream.next() + getTime());
		}

	}
}
