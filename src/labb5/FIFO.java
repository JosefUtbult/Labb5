package labb5;

import java.util.ArrayList;

/**
 * A class that holds a FIFO-queue (First in, first out) of integers.
 * Designed to hold a queue of person numbers.
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */

public class FIFO {

	protected ArrayList<Integer> queue;

	public FIFO(){
		queue = new ArrayList<>();
	}

	public void add(int value){
		this.queue.add(value);
	}

	public double getInstance(int index){
		return queue.get(index);
	}

	public int get(){
		if(this.queue.size() < 1){
			throw new Error("There is no more persons in the event queue.");
		}

		int temp = this.queue.get(0);
		this.queue.remove(0);
		return temp;
	}

	public int size(){
		return this.queue.size();
	}
}
