package Supermarket.eventTime;

import Supermarket.SupermarketState;
import random.UniformRandomStream;

import java.util.Date;

public class AddPersonTime extends EventTime {

    public AddPersonTime(SupermarketState supermarketState) {
        super(supermarketState, new UniformRandomStream(100, 5000, new Date().getTime()));
    }
}
