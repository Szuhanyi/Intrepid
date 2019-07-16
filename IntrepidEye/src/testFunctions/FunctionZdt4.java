package testFunctions;

public class FunctionZdt4 extends Function {

    public FunctionZdt4() {

        this.variableCount = 30;
        this.minValue = new double[30];
        this.maxValue = new double[30];
        paretoMin = new double[variableCount];
        paretoMax = new double[variableCount];
        paretoMax[0] = 1;
        for (int i = 0; i < 30; i++) {
            maxValue[i] = 1;
        }
    }

    @Override
    public double[] evaluate(double[] variables) {

        double[] result = new double[2];
        result[0] = variables[0];

        double gx = 0;
        double sum = 0;
        for (int i = 1; i < variableCount; i++) {
            sum += Math.pow(variables[i], 2) - 10 * Math.cos(4 * Math.PI * variables[i]);
        }
        gx = 1 + 10 * (variableCount - 1) + sum;
        result[1] = gx * (1 - Math.sqrt(result[0] / gx));

        return result;
    }

    @Override
    public boolean isAdequate(double[] variables) {

        return true;
    }

}
