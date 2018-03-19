package Supermarket;

import random.UniformRandomStream;

import java.util.Date;

class ServeTime extends EventTime {

    public ServeTime(SupermarketState supermarketState) {
        super(supermarketState, new UniformRandomStream(500, 10000, new Date().getTime()));
    }
}
