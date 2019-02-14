package testFunctions;

import model.Individual;

/**
 * Returns selected test function, if not initialized then return Fonseca(-4,4)
 * @author Gyorgy
 *
 */
public abstract class Function {
	
	protected static Function instance;
	protected double[] minValue;
	protected double[] maxValue;
	protected int variableCount;
	protected double [] paretoMin;
	protected double[] paretoMax;
	protected double [] boundaryParetoSolutions;
	
	public static Function getInstance() {
		
		if (instance == null) {
			instance = new FunctionFonseca(-4, 4);
		}
		return instance;
	}
	
	public double[] getMinValue() {
		
		return minValue;
	}
	
	public double[] getMaxValue() {
		
		return maxValue;
	}
	
	public int getVariableCount() {
		
		return variableCount;
	}
	
	public static void newFonseca() {
		
		
		instance = new FunctionFonseca(-4, 4);
		
	}
	
	public static void newSchaffer() {
		
		double a = 7;
		instance = new FunctionSchaffer(-a, a);		
	}
	
	public static void newBihnKorn() {
		
		instance = new FunctionBihnKorn();
	}
	
	public static void newZdt1() {
		
		instance = new FunctionZdt1();
	}
	
	public static void newZdt2() {
		
		instance = new FunctionZdt2();
	}
	
	public static void newZdt3() {
		
		instance = new FunctionZdt3();
	}
	
	public static void newZdt4() {
		
		instance = new FunctionZdt4();
	}
	
	public static void newZdt6() {
		
		instance = new FunctionZdt6();
	}
	
	public abstract double[] evaluate(double [] variables);
	
	public  void evaluate(Individual i) {
		if (i != null) {
			double[] a = evaluate(i.getDecisionVariables());
			i.setObjectiveValues(a);
		}
	}
	
	/**
	 * this function is used when constraints are handled
	 * @param variables
	 * @return
	 */
	public abstract boolean isAdequate(double [] variables);

	public double[] getParetoMinValue() {
		
		return paretoMin;
	}

	public double[] getParetoMaxValue() {
		
		return paretoMax;
	}

	public void setBoundarySolutions(double[] xf, double[] xl) {

		boundaryParetoSolutions = new double[4];
		boundaryParetoSolutions[0] = xf[0];
		boundaryParetoSolutions[1] = xf[1];
		boundaryParetoSolutions[2] = xl[0];
		boundaryParetoSolutions[3] = xl[1];
	}
	
	public double[] getBoundarySolutions() {
		
		return boundaryParetoSolutions;
	}
}
