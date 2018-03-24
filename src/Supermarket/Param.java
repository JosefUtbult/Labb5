package Supermarket;

/**
 * A Param is a container that holds all the parameters for a Supermarket simulation run.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class Param{
	public int numOfReg = 0;	//Number of registers that the store has
	public int maxNumOfPpl = 0;	//Max number of people that can be in the store
	public double ankomst = 0;	//The lambda value that demonstrates how often customers arrive
	public double[] scann = {0,0};	//The max and minimum time to scan a customers wares
	public double[] plock = {0,0};	//The max and minimum time for a customer to collect wares
	public double openTime = 0;	//The time the store is open
	public int seed = 0;	//The seeds for the time-generators
	public boolean view = false;	//Whether a simulation contains a view
}
