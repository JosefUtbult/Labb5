package Supermarket;

import random.UniformRandomStream;

import java.util.Date;

class AddPersonTime extends EventTime {

    public AddPersonTime(SupermarketState supermarketState) {
        super(supermarketState, new UniformRandomStream(100, 5000, new Date().getTime()));
    }
}
