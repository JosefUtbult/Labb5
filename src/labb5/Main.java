package labb5;

import Supermarket.OutputParam;
import Supermarket.Param;
import Supermarket.SuperMarket;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Runs the simulation in different modes according to the parameters in args.
 * PARAMETERS:
 *  -standard
 *      sim1
 *      sim2
 *
 *  -optimize
 *
 * @author Oscar Lundberg, Oscar Rosberg, Isak Sundell, Josef Utbult
 */
public class Main {

    public static void main(String[] args) {

        if(args.length > 0){

            if(Objects.equals(args[0], "-standard")){

                if(args.length > 1 && Objects.equals(args[1], "sim1")){
                    runStandard(sim1());
                }
                else if(args.length > 1 && Objects.equals(args[1], "sim2")){
                    runStandard(sim2());
                }
            }
            else if (Objects.equals(args[0], "-optimize")){


                System.out.format(  "\n\nOptimal nr of registers: %d\n" +
                                    "Parameters:\n" +
                                    "   Max nr of people in the store: %d\n",  optimize(), OptimizeParam(0, 0).maxNumOfPpl);
            }
        }

    }

    /**
     * Tries different seeds for a simulation with all parameters except nr of Registers.
     * @return The optimal nr of registers for the given parameters
     */
    public static int optimize(){

        int highestOptimal = 0;
        int counter = 0;

        int dotsPrinted = 0;
        System.out.print("Running.");

        while (counter < 100){

            int currentOptimal = runOptimizedWithSeed((int) new Date().getTime());

            if(highestOptimal < currentOptimal){
                counter = 0;
                highestOptimal = currentOptimal;
            }
            else {
                counter ++;
            }

            System.out.print(".");
            dotsPrinted ++;

            if(dotsPrinted >= 100){
                dotsPrinted = 0;
                System.out.print("\n");
            }
        }

        return highestOptimal;

    }

    //Runs a number of simulations equal to the max number of people that can be in the store.
    //Tries different nr of registers and returns the minimal nr of registers that wont let
    //people not be able to enter the store.

    private static int runOptimizedWithSeed(int seed){

        int optimal = 0; //antal kassor
        int optimalOutput = Integer.MAX_VALUE; //antalet missade kunder

        for(int i = 1; i <= OptimizeParam(0, 0).maxNumOfPpl; i++ ){
            int output = runOptimized(i, seed);

            if(optimalOutput > output){
                optimalOutput = output;
                optimal = i;
            }
        }

        return optimal;
    }

    //Runs the simulation with a set of static parameters except for the nr of registers and the seed.
    private static int runOptimized(int nrOfRegisters, int seed){

        SuperMarket superMarket = new SuperMarket(OptimizeParam(nrOfRegisters, seed));

        return superMarket.getOutput().nrNotEnter;
    }

    /**
     *Runs a standard simulation with a view
     * acording to the parameters
     * @param param an object of the type Param that sets the different values for the simulation.
     */
    public static void runStandard(Param param){

        SuperMarket superMarket = new SuperMarket(param);
    }

    //Different standard parameters

    private static Param sim1(){

        Param param = new Param();
        param.openTime = 10;
        param.maxNumOfPpl = 5;
        param.numOfReg = 2;
        param.ankomst = 1;
        param.plock = new double[] {0.5, 1};
        param.scann = new double[] {2, 3};
        param.seed = 1234;
        param.view = true;

        return param;
    }

    private static Param sim2() {

        Param param = new Param();
        param.openTime = 8;
        param.maxNumOfPpl = 7;
        param.numOfReg = 2;
        param.ankomst = 3;
        param.plock = new double[] {0.6, 0.9};
        param.scann = new double[] {0.35, 0.6};
        param.seed = 13;
        param.view = true;

        return param;
    }

    private static Param OptimizeParam(int nrOfRegisters, int seed){

        Param param = new Param();
        param.openTime = 8;
        param.maxNumOfPpl = 7;
        param.numOfReg = nrOfRegisters;
        param.ankomst = 3;     //Ca antalet ankomster per tidsenet. Bör vara större om maxNrOfPpl är större
        param.plock = new double[] {0.6, 0.9};
        param.scann = new double[] {0.35, 0.6};
        param.seed = seed;
        param.view = false;

        return param;
    }
}
