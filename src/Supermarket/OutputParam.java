package Supermarket;

/**
 * An OutputParam is a container that holds all the data from a successful SuperMarket simulation.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class OutputParam {
	public double totalTime = 0;	//The total runtime of the simulation
	public int nrOfPeople = 0;		//The total number of people that was generated
	public int nrShopped = 0;		//The total number of people that shopped
	public int nrNotEnter = 0;		//The total number of people that couldn't enter
	public double avrQueueTime = 0;	//Average time a customer had to queue (of them who queued)
	public double avaRegister = 0;	//Average time the registers where available
}
