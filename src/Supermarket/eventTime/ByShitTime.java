package Supermarket.eventTime;

import Supermarket.SupermarketState;
import random.UniformRandomStream;

import java.util.Date;

public class ByShitTime extends EventTime {

    public ByShitTime(SupermarketState supermarketState) {
        super(supermarketState, new UniformRandomStream(1000, 20000, new Date().getTime()));
    }
}
