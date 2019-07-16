package testFunctions;

import model.Individual;

public class FunctionZdt1 extends Function {

    public FunctionZdt1() {

        this.variableCount = 30;
        this.minValue = new double[30];
        this.maxValue = new double[30];
        paretoMin = new double[variableCount];
        paretoMax = new double[variableCount];

        for (int i = 0; i < 30; i++) {
            maxValue[i] = 1;
        }
        paretoMax[0] = 1;
    }

    @Override
    public double[] evaluate(double[] variables) {

        double[] result = new double[2];
        result[0] = variables[0];

        double gx = 0;
        double sum = 0;
        for (int i = 1; i < variableCount; i++) {
            sum += variables[i];
        }
        gx = 1 + 9 * sum / (variableCount - 1);
        result[1] = gx * (1 - Math.sqrt(result[0] / gx));

        return result;
    }

    @Override
    public void evaluate(Individual i) {

        double[] decVar = i.getDecisionVariables();
        double[] objVal = evaluate(decVar);
        i.setObjectiveValues(objVal);
    }

    @Override
    public boolean isAdequate(double[] variables) {

        return true;
    }

}
