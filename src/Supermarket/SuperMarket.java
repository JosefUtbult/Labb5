package Supermarket;

import Simulator.Simulator;

/**
 * A class to simulate a super market.
 */
public class SuperMarket {
    private Simulator simulator;
    private int maxPersonsInSuperMarket = 10;
    private int personsInSuperMarket = 0;
    private Register register;

    public SuperMarket() {
        SupermarketState state = new SupermarketState();
        this.simulator = new Simulator(state);
        this.register = new Register(this, this.simulator);
    }

    public void runSimulation() {
        SuperStart event = new SuperStart(this, this.simulator);
        this.simulator.addEvent(event);
        this.simulator.run();
    }

    public boolean enterStore(int personNumber) {
        if (this.personsInSuperMarket < this.maxPersonsInSuperMarket) {
            this.personsInSuperMarket++;
            return true;
        }
        return false;
    }

    public SupermarketState getState() {
        return (SupermarketState) this.simulator.getState();
    }

    public Register getRegister() {
        return this.register;
    }

    public int getMaxPersonsInSuperMarket() {
        return this.maxPersonsInSuperMarket;
    }

    public int getPersonsInSuperMarket() {
        return this.personsInSuperMarket;
    }

    public Simulator getSimulator() {
        return this.simulator;
    }

    public void setPersonsInSuperMarket(int personsInSuperMarket) {
        this.personsInSuperMarket = personsInSuperMarket;
    }
}
