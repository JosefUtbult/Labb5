package Supermarket.eventTime;

import Supermarket.SupermarketState;
import random.UniformRandomStream;

import java.util.Date;

public class EventTime {

    private SupermarketState supermarketState;
    private UniformRandomStream uniformRandomStream;

    public EventTime(SupermarketState supermarketState, UniformRandomStream uniformRandomStream) {
        this.supermarketState = supermarketState;
        this.uniformRandomStream = uniformRandomStream;
    }

    public int next() {
        double next = uniformRandomStream.next();
        long now = new Date().getTime();
        long starttime = supermarketState.getSTARTTIME();
        long supermarketTotalRunningTime = now - starttime;
        return (int) (next + supermarketTotalRunningTime);
    }
}
