package product;

import logging.DemoLogger;
import view.MainWindow;

public class Main {

    public static void main(String[] args) {

//		// we shall make this man great again
//		OutputService output = OutputService.getInstance();			
//		Function.newSchaffer();			
//		//Function.newBihnKorn();
//		Population p = new Population(Attributes.getPopulationSize());
//		Algorithm a = new NsgaAlgorithm();		
//		
//		Population opt = a.optimize(p, Attributes.getCycleCount());
//		
//		Plotter plot = new Plotter();
//		//output.printById(p);
//		//plot.showMeTheGraph(p);	
//		output.printByFitness(opt);
//		plot.showMeTheGraph(opt);

        MainWindow m = new MainWindow();
        m.setVisible(true);
//         new DemoLogger();
    }
}