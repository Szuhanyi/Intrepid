package testFunctions;

import model.Individual;
import services.Attributes;

public class FunctionSchaffer extends Function {	
	
	public FunctionSchaffer(double min, double max) {
		
		minValue = new double[1];
		maxValue = new double[1];
		this.minValue[0] = min;
		this.maxValue[0] = max;
		this.variableCount = 1;
		paretoMin = new double[1];
		paretoMax = new double[1];
		paretoMin[0] = 0;
		paretoMax[0] = 2;	
	}
	
	@Override
	public double[] evaluate(double[] variables) {
		
		double t = Attributes.getT();
		double x = variables[0];
		double[] result = new double[2];
		
		result[0] = Math.pow(x, 2) + t;
		result[1] = Math.pow(x - 2, 2) + t;		
		
		return result;		
	}

	@Override
	public void evaluate(Individual i) {
		
		double [] decVar = i.getDecisionVariables();
		double [] objVal = evaluate(decVar);
		i.setObjectiveValues(objVal);
	}

	@Override
	public boolean isAdequate(double[] variables) {
		
		return true;
	}
	
}
