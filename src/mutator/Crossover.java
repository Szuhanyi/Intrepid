package mutator;

import model.Individual;
import model.Population;
import services.Attributes;
import testFunctions.Function;

import java.util.LinkedList;
import java.util.Random;

public class Crossover implements Mutator {

    private Function f;

    public Crossover() {
        f = Function.getInstance();
    }

    //somehow the individuals are leaking. Each generation 2-3 just are just out
    // is this issue still open ?
    @Override
    public void modify(Population x) {
        x.sortByFitness(); // is this really necessary ?
        LinkedList<Individual> parent = x.getGenom();
        LinkedList<Individual> offspring = new LinkedList<>();
        Individual ind1 = null;
        Individual ind2 = null;
        Individual newInd = null;
        Random rand = new Random();
        Function f = Function.getInstance();
        for (int i = 0; i < parent.size() - 1; i += 2) {
            ind1 = parent.get(i);
            ind2 = parent.get(i + 1);
            double[] child = ind1.getDecisionVariables();
            if ((Attributes.getCrossoverProbability() + 1 / ind1.getFitness()) > rand.nextDouble()) {
                //System.out.println(i);
                // how do we implement the crossover variation operator ?
                // we breed them with each other, until we get enough
                child = getEuclideanDistance(ind1.getDecisionVariables(), ind2.getDecisionVariables());
                //double[] child = getEuclideanDistance(ind1.getObjectiveValues(), ind2.getObjectiveValues());

            }
            newInd = new Individual();
            newInd.setDecisionVariables(child);
            if (f.isAdequate(child)) { // there is a possibility, that there are not going to be any solutions
                // that are fine in the constraints
                offspring.add(newInd);
            }
        }

        // one more to create
        newInd = new Individual();
//		newInd.setObjectiveValues(
//				getEuclideanDistance(parent.getLast().getObjectiveValues(), parent.getFirst().getObjectiveValues()));
        newInd.setDecisionVariables(
                getEuclideanDistance(parent.getLast().getDecisionVariables(), parent.getFirst().getDecisionVariables()));

        offspring.add(newInd);

        x.setGenom(offspring); // this might not work
    }

    private double[] getEuclideanDistance(double[] v1, double[] v2) {
        double[] v = new double[v1.length];
        for (int j = 0; j < v2.length; j++) {
            v[j] = (v1[j] + v2[j]) / 2;
            if (v[j] < f.getMinValue()[0]) {
                v[j] = f.getMinValue()[0];
            }
            if (v[j] > f.getMaxValue()[0]) {
                v[j] = f.getMaxValue()[0];
            }
        }
        return v;
    }

    private double[] getHeuristicDistance(double ratio, double[] v1, double[] v2) {
        double[] newOne = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            newOne[i] = v2[i] + ratio * (v1[i] - v2[i]);
        }
        return newOne;
    }

    private double[] getArithmeticCrossover(double alpha, double[] v1, double[] v2) {
        double[] newOne = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            newOne[i] = alpha * v1[i] + (1 - alpha) * v2[i];
        }
        return newOne;
    }

    private double[] getIntermediateCrossover(double ratio, double[] v1, double[] v2) {
        Random r = new Random();
        double[] newOne = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            newOne[i] = v1[i] + r.nextDouble() * ratio * (v2[i] - v1[i]);
        }
        return newOne;
    }

    private double[] getDynamicDistance(double[] v1, double[] v2) {
        Random r = new Random();
        double alpha = r.nextDouble();
        double[] newOne = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            newOne[i] = alpha * v1[i] + (1 - alpha) * v2[i];
        }
        return newOne;
    }
}
