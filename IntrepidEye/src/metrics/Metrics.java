package metrics;

import model.Individual;
import model.Population;
import testFunctions.Function;

import java.util.LinkedList;

public class Metrics {

    private double metricSpread(double df, double dl, double deltaD, double[] d) {

        double sum = 0;
        for (int i = 0; i < d.length; i++) {
            sum += Math.abs(d[i] - deltaD);
        }
        double result = (df + dl + sum) / (df + dl + (d.length - 1) * deltaD);

        return result;
    }

    public double getDistributionMetricSecond(Population p) {

        //LinkedList<Individual> test = new LinkedList<>();
        // calculate first the avg distance
        // 1. sort the based on one value

        p.SortByObjectiveValue(1);
        double[] d = new double[p.size() - 1];
        double sum = 0;

        for (int i = 0; i < p.size() - 1; i++) {
            Individual ind1 = p.get(i);
            Individual ind2 = p.get(i + 1);
            d[i] = this.eucDistance(ind1.getObjectiveValues(), ind2.getObjectiveValues());
            sum += d[i];
        }
        double deltaD = sum / d.length;

        Function f = Function.getInstance();

        //get boundary values, how do I do that ?
        Individual indf = new Individual();
        double[] xf = new double[2];
        Individual indl = new Individual();
        double[] xl = new double[2];

        double[] bound = f.getBoundarySolutions();
        xf[0] = bound[0];
        xf[1] = bound[1];
        xl[0] = bound[0];
        xl[1] = bound[1];


//		for (int i = 0; i < xf.length; i++) {
//			xf[i] = f.getParetoMinValue()[i];
//			xl[i] = f.getParetoMaxValue()[i];
//		}
        //indf.setDecisionVariables(xf);
        //	indf.setObjectiveValues(f.evaluate(xf));
        indf.setObjectiveValues(xf);

        //indl.setDecisionVariables(xl);
        //	indl.setObjectiveValues(f.evaluate(xl));
        indl.setObjectiveValues(xl);
        //distance between boundary and extreme solutions
        double df = this.eucDistance(indf.getObjectiveValues(), p.get(0).getObjectiveValues());
        double dl = this.eucDistance(indl.getObjectiveValues(), p.get(p.size() - 1).getObjectiveValues());
        //apply formula
        double result = metricSpread(df, dl, deltaD, d);

        return result;
    }


    public double getAvarageDistanceDelta(Population p) {

        LinkedList<Individual> test = new LinkedList<Individual>();
        LinkedList<Double[]> decVariables = generateSplitValues();
        for (int i = 0; i < decVariables.size(); i++) {
            Individual ind = new Individual();
            double[] decVar = new double[decVariables.getFirst().length];
            for (int j = 0; j < decVar.length; j++) {
                decVar[j] = decVariables.get(i)[j];
            }
            ind.setDecisionVariables(decVar);
            ind.setObjectiveValues(Function.getInstance().evaluate(decVar));
            test.add(ind);
        }

        //set boundary points, because ,here the population representing the Pareto front already exists
        Function f = Function.getInstance();

        Population p2 = new Population(test);
        p2.SortByObjectiveValue(1);
        double[] xf = new double[2];
        double[] xl = new double[2];
        Individual ind = p2.get(0);
        xf[0] = ind.getObjectiveValues()[0];
        xf[1] = ind.getObjectiveValues()[1];
        ind = p2.get(p.size() - 1);
        xl[0] = ind.getObjectiveValues()[0];
        xl[1] = ind.getObjectiveValues()[1];

        f.setBoundarySolutions(xf, xl);


        double result = getMetric(p, test);

        return result;
    }

    private LinkedList<Double[]> generateSplitValues() {

        Function f = Function.getInstance();
        double[] min = f.getParetoMinValue();
        double[] max = f.getParetoMaxValue();
        int h = 100;

        LinkedList<Double[]> result = new LinkedList<>();

        double[] step = new double[min.length];

        for (int i = 0; i < step.length; i++) {
            step[i] = (max[i] - min[i]) / h;
        }
        //generate h equally distributed points on the Pareto front

        double[] walk = new double[f.getVariableCount()];
        for (int i = 0; i < walk.length; i++) {
            walk[i] = min[i];
        }

        for (int i = 0; i < h; i++) {
            Double[] x = new Double[f.getVariableCount()];
            for (int j = 0; j < x.length; j++) {
                x[j] = walk[j];
            }
            result.add(x);

            for (int j = 0; j < walk.length; j++) {
                walk[j] += step[j];
            }
        }

        return result;
    }

    /**
     * Distane metric calculation  test function
     *
     * @param p
     * @return
     */
    private double getMetric(Population p, LinkedList<Individual> test) {

        LinkedList<Double> distance = new LinkedList<>();
        for (int i = 0; i < p.size(); i++) {
            Individual ind = p.get(i);
            if (ind.getRank() == 0) {
                double m = 1000;
                for (int j = 0; j < test.size(); j++) {
                    double value = eucDistance(ind.getObjectiveValues(), test.get(j).getObjectiveValues());
                    if (m > value) {
                        m = value;
                    }
                }
                distance.add(m);
            }
        }
        //calculate the average of the m values
        double sum = 0;
        for (int i = 0; i < distance.size(); i++) {
            sum += distance.get(i);
        }
        double result = sum / distance.size();

        return result;
    }

    private double eucDistance(double[] a, double[] b) {

        double result = 0;
        result = Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));

        return result;
    }

}
