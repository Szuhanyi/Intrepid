package product;

import algorithms.Algorithm;
import algorithms.NsgaAlgorithm;
import model.Population;
import output.OutputService;
import services.Attributes;
import testFunctions.Function;

public class Main {

    public static void main(String[] args) {

		Function.newBihnKorn();
		Population p = new Population(Attributes.getPopulationSize());

 		Algorithm a = new NsgaAlgorithm();

 		Population optimal = a.optimize(p, Attributes.getCycleCount());

		OutputService output = OutputService.getInstance();
		output.setOutputToConsole();
		output.setOutputToFile();
		output.printNoFormat(optimal);

//		Plotter plotter = new Plotter();
//		plotter.showMeTheGraph(optimal);
//		System.out.println(optimal.size());


    }
}