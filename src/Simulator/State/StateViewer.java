package Simulator.State;

import Simulator.Event.Event;
import Simulator.State.State;

import java.io.*;
import java.nio.file.Files;
import java.util.Observable;
import java.util.Observer;

/**
 * Observs the state object and stores the event in a string to be printed upon termination
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */

public abstract class StateViewer implements Observer {

	protected State state;
	protected PrintWriter output;
	protected String printOutput;

	/**
	 * Generates a stateviewer and observs the state
	 * @param state
	 */
	public StateViewer(State state){
		this.state = state;
		state.addObserver(this);
		this.printOutput = "";

		try {
			output = new PrintWriter("output.txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * is to be overridden
	 * @param event
	 */
	protected void printScreen(Event event){
		this.printOutput += String.format("Time: %.2f,	Event: %s\n", state.getTime(), event.toString());
	}

	/**
	 * Prints out the output to the terminal and an output file
	 */
	protected void print(){
		System.out.println(this.printOutput);
		this.output.println(this.printOutput);
		this.output.close();
	}
}
