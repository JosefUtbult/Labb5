package labb5;

import java.util.ArrayList;

public class FIFO {

	protected ArrayList<Integer> queue;

	public FIFO(){
		queue = new ArrayList<>();
	}

	public void add(int value){
		this.queue.add(value);
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
