package Supermarket;

import Supermarket.eventTime.AddPersonTime;
import Supermarket.eventTime.ByShitTime;
import Supermarket.eventTime.ServeTime;

class TimeKeeper {
    private AddPersonTime addPersonTime;
    private ByShitTime byShitTime;
    private ServeTime serveTime;

    public TimeKeeper(SupermarketState supermarketState) {
        this.addPersonTime = new AddPersonTime(supermarketState);
        this.byShitTime = new ByShitTime(supermarketState);
        this.serveTime = new ServeTime(supermarketState);
    }

    public AddPersonTime getAddPersonTime() {
        return addPersonTime;
    }

    public ByShitTime getByShitTime() {
        return byShitTime;
    }

    public ServeTime getServeTime() {
        return serveTime;
    }
}
