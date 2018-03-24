package labb5;

import Supermarket.Param;
import Supermarket.SuperMarket;

import java.util.Objects;

public class RunSim {

	public static void main(String[] args){

		if (args.length > 0 && Objects.equals(args[0], "sim1")) {
			runStandard(sim1());
		} else if (args.length > 0 && Objects.equals(args[0], "sim2")) {
			runStandard(sim2());
		}
	}

	public static void runStandard(Param param){

		SuperMarket superMarket = new SuperMarket(param);
	}

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
}
