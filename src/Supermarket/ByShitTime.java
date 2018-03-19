package Supermarket;

import random.UniformRandomStream;

import java.util.Date;

class ByShitTime extends EventTime {

    public ByShitTime(SupermarketState supermarketState) {
        super(supermarketState, new UniformRandomStream(1000, 20000, new Date().getTime()));
    }
}
