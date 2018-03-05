package Supermarket;

import Simulator.State.State;
import random.UniformRandomStream;

import java.util.Date;

public class SupermarketState extends State {

	private int personClocker;
	private TimeKeeper timeKeeper;

	public SupermarketState(){
		super();
		personClocker = 0;
		timeKeeper = new TimeKeeper();
	}

	public int clockPerson(){
		personClocker ++;
		return this.personClocker;
	}

	public TimeKeeper getTimeKeeper() {
		return timeKeeper;
	}

	class TimeKeeper {
		private AddPersonTime addPersonTime;
		private ByShitTime byShitTime;
		private ServeTime serveTime;

		public TimeKeeper(){
			this.addPersonTime = new AddPersonTime();
			this.byShitTime = new ByShitTime();
			this.serveTime = new ServeTime();
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

	class AddPersonTime{

		private UniformRandomStream uniformRandomStream;
		public AddPersonTime(){
			this.uniformRandomStream = new UniformRandomStream(100, 5000, new Date().getTime());
		}

		public int next(){
			return (int) (uniformRandomStream.next() + (new Date().getTime() - getSTARTTIME()));
		}
	}

	class ByShitTime{

		private UniformRandomStream uniformRandomStream;

		public ByShitTime(){
			this.uniformRandomStream = new UniformRandomStream(1000, 20000, new Date().getTime());
		}

		public int next(){
			return (int) (uniformRandomStream.next() + (new Date().getTime() - getSTARTTIME()));
		}

	}

	class ServeTime{

		private UniformRandomStream uniformRandomStream;

		public ServeTime(){
			this.uniformRandomStream = new UniformRandomStream(500, 10000, new Date().getTime());
		}

		public int next(){
			return (int) (uniformRandomStream.next() + (new Date().getTime() - getSTARTTIME()));
		}

	}
}
