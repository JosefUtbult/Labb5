package labb5;

import java.util.ArrayList;

public class FIFO {

	private ArrayList<Integer> queue;

	public FIFO(){
		queue = new ArrayList<>();
	}

	public void add(int value){
		this.queue.add(value);
	}

	public int get(){
		int temp = this.queue.get(0);
		this.queue.remove(0);
		return temp;
	}

}
