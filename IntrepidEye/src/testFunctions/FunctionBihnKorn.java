package testFunctions;

import model.Individual;
import services.Attributes;

public class FunctionBihnKorn extends Function {

    public FunctionBihnKorn() {

        minValue = new double[2];
        maxValue = new double[2];
        this.variableCount = 2;
        minValue[0] = 0;
        minValue[1] = 0;
        maxValue[0] = 5;
        maxValue[1] = 3;
        //actually we dont know the true pareto front yet
        paretoMin = new double[2];
        paretoMax = new double[2];

        paretoMin[0] = 0;
        paretoMax[0] = 2;
        paretoMin[1] = 0;
        paretoMax[1] = 2;

    }

    @Override
    public double[] evaluate(double[] variables) {

        double t = Attributes.getT();
        double x = variables[0];
        double y = variables[1];
        double[] objVal = new double[2];

        objVal[0] = 4 * x * x + 4 * y * y + t;
        objVal[1] = (x - 5) * (x - 5) + (y - 5) * (y - 5) + t;

        return objVal;
    }

    @Override
    public void evaluate(Individual i) {

        i.setObjectiveValues(evaluate(i.getDecisionVariables()));
    }

    @Override
    public boolean isAdequate(double[] variables) {

        double x = variables[0];
        double y = variables[1];
        boolean ok = false;

        double gx1 = (x - 5) * (x - 5) + y * y;
        double gx2 = (x - 8) * (x - 8) + (y + 3) * (y + 3);

        if (gx1 <= 25 && gx2 >= 7.7) {
            ok = true;
        }

        return ok;
    }
}
