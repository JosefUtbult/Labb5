package Supermarket.eventTime;

import Supermarket.SupermarketState;
import Supermarket.eventTime.EventTime;
import random.UniformRandomStream;

import java.util.Date;

public class ServeTime extends EventTime {

    public ServeTime(SupermarketState supermarketState) {
        super(supermarketState, new UniformRandomStream(500, 10000, new Date().getTime()));
    }
}
