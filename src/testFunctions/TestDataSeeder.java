package testFunctions;

import java.util.LinkedList;
import java.util.Random;

import model.Individual;

public class TestDataSeeder {

	private static TestDataSeeder instance;
	
	
	private TestDataSeeder() {
	
	}
	public static TestDataSeeder getInstance() {

		if (instance == null)
			instance = new TestDataSeeder();

		return instance;
	}

	public LinkedList<Individual> getData(int length) {
		
		LinkedList<Individual> list = new LinkedList<>();
		Function f = Function.getInstance();
		double[] min = f.getMinValue();
		double[] max = f.getMaxValue();
		int varCount = f.getVariableCount();
		for (int i = 0; i < length; i++) {
			Individual ind = new Individual();
			// do something more.. initialize with real data
			double [] decVariable = genURand(varCount, min, max);
			double [] objValue = f.evaluate(decVariable);
			ind.setObjectiveValues(objValue);
			ind.setDecisionVariables(decVariable);		
			
			if (f.isAdequate(decVariable)) {
				list.add(ind);
			}
		}

		return list;
	}	
	
	private double[] genURand(int length, double[] min, double[] max) {

		double[] result = new double[length];
		
		// I hope this is a uniform generator
		Random rand = new Random();
		for (int i = 0 ; i < length ; i++ ) {
			result[i] = rand.nextDouble() * (max[i] - min[i]) + min[i];
		}
		
		return result;
	}

}

