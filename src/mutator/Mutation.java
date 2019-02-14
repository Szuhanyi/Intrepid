package mutator;

import java.util.Random;

import model.Individual;
import model.Population;
import services.Attributes;
import testFunctions.Function;

/**
 * this Class should simulate a mutation on a gene
 * @author Gyorgy
 *
 */


public class Mutation implements Mutator {	

	private Random r;
	private Function f;
	public Mutation() {
		r = new Random();
		f = Function.getInstance();
	}
	@Override
	public void modify(Population x) {
		
		double p = Attributes.getMutationProbability();
		
		for (int i = 0; i < x.size(); i++) {
			double c = r.nextDouble();
			if (c < p) {
				//mutate something
				//how do you mutate a double number/value
				Individual ind = x.get(i);
				
				// need to generalize this over the two spaces
				double [] values = new double[ind.getDecisionVariables().length];
				for (int j = 0; j < values.length; j++) {
					values[j] = mutate(ind.getDecisionVariables()[j]); 
				}
				ind.setDecisionVariables(values);
			}			
		}		
	}
	
	private double mutate(double value) {
		//what kinda mutation did I use here ?
		// 
		double v = value + r.nextGaussian();
		if (v < f.getMinValue()[0]) {
			v = f.getMinValue()[0];
		}
		if (v > f.getMaxValue()[0]) {
			v = f.getMaxValue()[0];
		}
		return v;
	}	

}
