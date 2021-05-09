package model;

import mutator.Crossover;
import mutator.Mutation;
import output.Output;
import testFunctions.Function;
import testFunctions.TestDataSeeder;

import java.util.LinkedList;
import java.util.List;

/**
 * A population contains all the possible solutions for a problem
 *
 * @author Gyorgy
 */
public class Population implements Output {

    private LinkedList<Individual> individuals;

    public Population() {
        individuals = new LinkedList<Individual>();
    }

    public Population(List<Individual> init) {
        this();
        if (init != null) {
            init.stream().forEach(ind -> individuals.add(new Individual(ind)));
//            for (int i = 0; i < init.size(); i++) {
//                Individual ind = init.get(i);
//                Individual newInd = new Individual(ind);ix
//                list.add(newInd);
//            }
        }
    }


    /**
     * Initialize with length number of elements
     *
     * @param length
     */
    public Population(int length) {
        this();
        initializePopulation(length);
    }


    public void evaluate() {
        Function f = Function.getInstance();
        if (f != null) {
            for (Individual i : individuals) {
                f.evaluate(i);
            }
        } else {
            System.out.println("Evaluation function not instantiated.");
        }
    }


    /**
     * Concatenates the parameter with the instance, and returns a new one
     *
     * @param parentGeneration
     * @return
     */
    public Population append(Population parentGeneration) {
        List<Individual> newList = new LinkedList<>();
        for (int i = 0; i < individuals.size(); i++) {
            newList.add(individuals.get(i));
        }
        for (int i = 0; i < parentGeneration.size(); i++) {
            newList.add(parentGeneration.get(i));
        }
        Population p = new Population(newList);
        return p;
    }

    public void appendToHimself(Population other) {
        if (other != null) {
            for (int i = 0; i < other.size(); i++) {
                individuals.add(other.get(i));
            }
        }
    }

    public int size() {
        return individuals.size();
    }

    public Individual get(int index) {
        if (index >= 0 && index < individuals.size())
            return individuals.get(index);
        else
            return null;
    }

    public Population spawnNewPopulation() {
        // spawn a new population, with genetic operators
        // oh my
        // how long should that be ?

        Population offspring = new Population(this.individuals);

        new Crossover().modify(offspring);
        new Mutation().modify(offspring);

        Function f = Function.getInstance();
        for (int i = 0; i < offspring.size(); i++) {
            f.evaluate(offspring.get(i));
        }

        return offspring;
    }

    public void initializePopulation(int length) {

        //TestDataSeeder tds = TestDataSeeder.getInstance();
        //list = tds.getData(length);
        individuals = TestDataSeeder.getInstance().getData(length);
    }

    @Override
    public void plot2D() {
        // TODO Auto-generated method stub

    }

    @Override
    public void plot3D() {
        // TODO Auto-generated method stub

    }

    @Override
    public void printToConsole() {

        for (int i = 0; i < individuals.size(); i++) {
            String line = individuals.get(i).getId() + " " + individuals.get(i).getFitness();
            for (int j = 0; j < individuals.get(i).getObjectiveValues().length; j++) {
                line += "      " + individuals.get(i).getObjectiveValues()[j];
            }
            System.out.println(line);
        }
    }

    // this does not look that cool.. first do the logging
    public void sortByFitness() {
        for (int i = 0; i < individuals.size() - 1; i++) {
            for (int j = i + 1; j < individuals.size(); j++) {
                if (individuals.get(i).getFitness() > individuals.get(j).getFitness()) {
                    Individual t = individuals.get(i);
                    individuals.set(i, individuals.get(j));
                    individuals.set(j, t);
                }
            }
        }
    }

    public void SortByObjectiveValue(int index) {
        if (individuals.size() > 0 && individuals.get(0).getObjectiveValues().length >= index) {
            for (int i = 0; i < individuals.size() - 1; i++) {
                for (int j = i + 1; j < individuals.size(); j++) {
                    if (individuals.get(i).getObjectiveValues()[index] > individuals.get(j).getObjectiveValues()[index]) {
                        Individual t = individuals.get(i);
                        individuals.set(i, individuals.get(j));
                        individuals.set(j, t);
                    }
                }
            }

        }
    }

    public LinkedList<Individual> getGenom() {
        return individuals;
    }

    public void setGenom(LinkedList<Individual> genom) {
        individuals = genom;
    }

    public void normalizeObjectiveValues() {
    }

}
