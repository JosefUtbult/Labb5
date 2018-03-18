package labb5;

import java.util.ArrayList;

public class Queue {

    protected ArrayList<Integer> queue;

    public Queue() {
        queue = new ArrayList<>();
    }

    public void enqueue(int value) {
        this.queue.add(value);
    }

    public int dequeue() {
        if (this.queue.size() < 1) {
            throw new Error("There is no more persons in the event queue.");
        }
        return this.queue.remove(0);
    }

    public int size() {
        return this.queue.size();
    }
}
