package Supermarket;

import Simulator.Event.Event;
import Simulator.Event.Stop;
import Simulator.State.StateViewer;

import java.util.Observable;

/**
 * Observs the state and prints the events to the console
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class SupermarketStateViewer extends StateViewer {
	protected Param param;

	/**
	 * Generates the supermarket veiw
	 * @param state the state that it observs
	 * @param param the parameters of the simulation
	 */
	public SupermarketStateViewer(SupermarketState state, Param param) {
		super(state);
		this.param = param;
	}

	/**
	 * Saves the events to a variable, and prints the variable when the simulation is finished
	 * @param o observablen
	 * @param arg event
	 */
	@Override
	public void update(Observable o, Object arg){
		Event event = (Event)arg;

		if(event.getClass() == SuperStart.class){
			this.printOutput += String.format(	"PARAMETERS\n" +
								"--------------------------------------\n" +
								"Number of registers..............%d\n" +
								"Max number of persons in store...%d\n" +
								"Open-time........................%.2f\n" +
								"Arrival time, lamda..............%.2f\n" +
								"Time to pick goods........%.2f - %.2f\n" +
								"Time to serve customers...%.2f - %.2f\n" +
								"Seed.............................%d\n" +
								"\n" +
								"Time: Current time\n\n" +
								"Event: The triggering event\n\n" +
								"ID: Person who triggered the event\n\n" +
								"O/C: If the store is open\n\n" +
								"RegAv: Registers in not currently used\n\n" +
								"TRegAv: Total time that any registers have been available\n\n" +
								"InStore: Number of people in the store\n\n" +
								"Shopped: Number of people that has shopped\n\n" +
								"Missed: Number of people that couldn't go into the store\n\n" +
								"WhoQueued: Number of people that has queued\n\n" +
								"TQueue: Total of every persons queue-time\n\n" +
								"QueueLen: Current length of the queue\n\n" +
								"Queue: The current queue\n\n" +
								"\n\n" +
								"EVENTS\n" +
								"--------------------------------------\n" +
								"	Time	Event				ID	O/C		RegAv	TRegAv	InStore	Shopped	Missed	WhoQueued	TQueue		QueueLen	Queue\n",

								this.param.numOfReg,
								this.param.maxNumOfPpl,
								this.param.openTime,
								this.param.ankomst,
								this.param.plock[0],
								this.param.plock[1],
								this.param.scann[0],
								this.param.scann[1],
								this.param.seed);

								this.printOutput += parseValues(event, -1);

		}
		else if(event.getClass() == SuperStop.class){
			this.printOutput += String.format(	parseValues(event, Stop.MAXVAL) +
								"\n\nSTOP\n" +
								"--------------------------------------\n" +
								"Total time: %.2f\n" +
								"Total number of people: %d\n" +
								"Number of people that shopped: %d\n" +
								"Number of people that couldn't go into the store: %d\n" +
								"Total queue-time (%d customers that queued): %.2f\n" +
								"Average queue-time (%d customers that queued): %.2f\n" +
								"Total time registers (%d registers) where available: %.2f\n" +
								"Average time registers (%d registers) where available: %.2f\n",

								state.getTime(),
								((SupermarketState)state).clockPerson() - 1,
								((SupermarketState)state).getPersonsLeft(),
								((SupermarketState)state).getCouldntGoIn(),
								((SupermarketState)state).getPeopleWhoQueued(),
								((SupermarketState)state).getQueueTime(),
								((SupermarketState)state).getPeopleWhoQueued(),
								((SupermarketState)state).getQueueTime() / (((SupermarketState)state).getPeopleWhoQueued() > 0 ? ((SupermarketState)state).getPeopleWhoQueued() : 1),
								param.numOfReg,
								((SupermarketState)state).getTimeRegistersUnused().getTime(),
								param.numOfReg,
								((SupermarketState)state).getTimeRegistersUnused().getTime() / this.param.numOfReg);

			this.print();

		}
		else{

			this.printOutput += parseValues(event, -1);

		}


	}
	//Prints everything out in the 'right order'
	private String parseValues(Event event, double time){
		return String.format("%s	%s	" + 		    	"%b	" + "%d		%.2f	" + "%d		%d		%d		%d			%.2f		%d			%s\n",
				time < 0 ? String.format("	%.2f", state.getTime()) : String.format("%.1f", time),
				event.toString(),
				((SupermarketState)state).getStoreOpen(),
				this.param.numOfReg - ((SupermarketState)state).getNrOfRegistersInUse(),
				((SupermarketState)state).getTimeRegistersUnused().getTime(),
				((SupermarketState)state).getPersonsInStore(),
				((SupermarketState)state).getPersonsLeft(),
				((SupermarketState)state).getCouldntGoIn(),
				((SupermarketState)state).getPeopleWhoQueued(),
				((SupermarketState)state).getQueueTime(),
				((SupermarketState)state).queueSize(),
				((SupermarketState)state).fifoToString());
	}



}

