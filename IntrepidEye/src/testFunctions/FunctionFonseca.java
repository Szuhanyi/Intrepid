package testFunctions;

import services.Attributes;

public class FunctionFonseca extends Function {

    //n is the length of the sum in the evaluating functions
    private int n = 2;

    public FunctionFonseca(double min, double max) {

        variableCount = n;
        minValue = new double[variableCount];
        maxValue = new double[variableCount];
        paretoMin = new double[variableCount];
        paretoMax = new double[variableCount];
        for (int i = 0; i < variableCount; i++) {
            minValue[i] = min;
            maxValue[i] = max;
            paretoMin[i] = -1 / Math.sqrt(3);
            paretoMax[i] = 1 / Math.sqrt(3);
        }
    }

    /**
     * [min,max] , n is for the sum length
     *
     * @param min
     * @param max
     * @param n
     */
    public FunctionFonseca(double min, double max, int n) {

        this(min, max);
        this.n = n;
    }

    @Override
    public double[] evaluate(double[] variables) {

        double[] result = new double[2];

        double t = Attributes.getT();

        double sum = 0;
        for (int j = 0; j < n; j++) {
            sum += Math.pow(variables[j] - (1 / Math.sqrt(n)), 2) + t;
        }
        result[0] = 1 - Math.exp(-sum);

        sum = 0;
        for (int j = 0; j < n; j++) {
            sum += Math.pow(variables[j] + (1 / Math.sqrt(n)), 2) + t;
        }
        result[1] = 1 - Math.exp(-sum);


        return result;
    }

    @Override
    public boolean isAdequate(double[] variables) {

        return true;
    }

}
