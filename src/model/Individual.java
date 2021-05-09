package model;

import exception.SomethingWentWrongException;
import services.Attributes;

import java.util.LinkedList;

public class Individual {

    // What is individual. It's a possible solution to our problem.
    // In our problems we look for Pareto-optimal solutions, so.. just like that

    //static int idNumerator ; I don't want this ID generator. Just get it from a stub or some test case. I don't care.

    private int id;
    private double fitness;
    private int rank;

    private double[] decisionVariables;
    private double[] objectiveValues;
    private LinkedList<Individual> dominatedSet;
    private int dominationCount;
    private double distance;
    private static int idNumerator = 0;

    /**
     *  Placeholder for a solution.
     *  A solution is a set of value pairs, which represent a solution for a given function.
     *  f(x) = y. where x  = 1,n, y = 1,m, values..
     *  but how van they differ ? when the are in the same function ???
     *
     *  Pls do something or kill yourself..
     *
     */
    public Individual() {
        id = idNumerator++;
        dominatedSet = new LinkedList<>();
        objectiveValues = new double[0];
        decisionVariables = new double[0];
    }

    public Individual(Individual ind) {
        this();
        this.distance = ind.distance;
        dominationCount = ind.getDominationCount();
        this.fitness = ind.fitness;
        //dominated Set ?
        // I copy all values
        double[] rDecVar = ind.getDecisionVariables();
        double[] decVar = new double[rDecVar.length];
        for (int i = 0; i < rDecVar.length; i++) {
            decVar[i] = rDecVar[i];
        }
        setDecisionVariables(decVar);
        double[] rObjVal = ind.getObjectiveValues();
        double[] objVal = new double[rObjVal.length];
        for (int i = 0; i < rObjVal.length; i++) {
            objVal[i] = rObjVal[i];
        }
        objectiveValues = objVal;
    }

    /**
     * less then operator: 'p < q'
     *
     * @param q
     * @return
     */
    public boolean lessThen(Individual q) {
        boolean result = false;
        int counter = 0;
        double sigma = 0.001;
        double[] qObjValues = q.getObjectiveValues();
        for (int i = 0; i < objectiveValues.length; i++) {
            // Do i need here a sigma interval [p-q > sigma], in this case the solutions would be clustered a little bit better.
            // it may result in a better spread of solutions
            // TODO: investigate if using a turelmi interval would increase the spread of solutions
            //

            // q >= p + sigma
            if (qObjValues[i] - objectiveValues[i]  >= sigma) { // 0.09 - 0.11 > -0.001

                counter++;
            }
        }
        //at least one of them must be less then then other
        if (counter == q.getObjectiveValues().length) {
            for (int i = 0; i < q.getObjectiveValues().length; i++) {
                if (qObjValues[i] - objectiveValues[i]  > sigma) {
                    result = true;
                }
            }
        }
        return result;
    }

    public int getDominatedSetSize() {
        if (dominatedSet == null) {
            dominatedSet = new LinkedList<>();
        }
        return dominatedSet.size();
    }

    public void addDominatedSolution(Individual p) {
        if (dominatedSet == null)
            dominatedSet = new LinkedList<Individual>();
        dominatedSet.add(p);
    }

    public void removeDominatedSolution(Individual p) {
        if (dominatedSet != null)
            dominatedSet.remove(p);
    }

    public Individual getDominatedSolution(int index) {
        Individual result = null;
        if (index >= 0 && index < dominatedSet.size()) {
            result = dominatedSet.get(index);
        }

        return result;
    }

    public void incrementDominationCount() throws SomethingWentWrongException {
        // integer has 0 as default domination count
        if (dominationCount == 3 * Attributes.getPopulationSize() - 1) {
            throw new SomethingWentWrongException(this);
        }
        dominationCount++;
    }

    public void decrementDominationCount() {
        if (dominationCount > 0)
            dominationCount--;
    }

    public int getDominationCount() {
        return dominationCount;
    }

    // attribute encapsulation

    public int getId() {
        return id;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public double[] getDecisionVariables() {
        return decisionVariables;
    }

    public void setDecisionVariables(double[] _decisionVariables) {
        this.decisionVariables = _decisionVariables;
    }

    public double[] getObjectiveValues() {
        return objectiveValues;
    }

    public void setObjectiveValues(double[] _objectiveValues) {
        this.objectiveValues = _objectiveValues;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}

