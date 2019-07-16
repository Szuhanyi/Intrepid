package algorithms;

import exception.SomethingWentWrongException;
import model.Front;
import model.Individual;
import model.Population;
import services.Attributes;

import java.util.LinkedList;

public class NsgaAlgorithm extends Algorithm {

    private LinkedList<Front> fronts;

    public NsgaAlgorithm() {
        fronts = new LinkedList<Front>();
    }


    @Override
    public Population optimize(Population parentGeneration, int cycleCount) {
        // Do the evolutionary thing, procedure
      //  Population parentGeneration = p;
        evaluate(parentGeneration);
        int size = parentGeneration.size();
        for (int i = 0; i < cycleCount; i++) {
            Population newGeneration = parentGeneration.spawnNewPopulation();
            Population allGene = parentGeneration.append(newGeneration);
            evaluate(allGene);
            //Select the best
            parentGeneration = makeSelection(Attributes.getPopulationSize());
        }
        // I am not sure about this value transfer
        return parentGeneration;
    }


    private void evaluate(Population p) {
        // this method should assign the fitness values to the individuals.
        // actually this method executes only three methods
        nonDominatedSort(p);

        assignCrowdingDistance();

        // I am not sure if this algorithm uses a fitness function
        fitnessAssignment(p);
    }

    private void fitnessAssignment(Population p) {
        // Do I need you right now ? Tell me truly !
        for (int i = 0; i < p.size(); i++) {
            Individual ind = p.get(i);
            double fitness = 0;
            for (int j = 0; j < ind.getObjectiveValues().length; j++) {
                fitness += ind.getObjectiveValues()[j];
            }
            ind.setFitness(fitness);
        }
    }

    private void assignCrowdingDistance() {

        // iterate through the fronts (ranking variable)

        for (Front front : fronts) {
            crowdingDistanceAssignment(front);
        }

    }

    private void crowdingDistanceAssignment(Front p) {

        if (p.size() == 0) {
            return;
        }
        int length = p.get(0).getObjectiveValues().length;
        for (int j = 0; j < length; j++) {
            normalSort(p, j);
            // threshold should be set to infinite
            p.get(0).setDistance(Integer.MAX_VALUE);
            p.get(p.size() - 1).setDistance(Integer.MAX_VALUE);
            for (int i = 1; i < p.size() - 1; i++) {
                p.get(i).setDistance((p.get(i - 1).getDistance() + p.get(i + 1).getDistance()) / 2);
            }
        }
    }

    private void normalSort(Front p, int poz) {
        // sort

        for (int i = 0; i < p.size() - 1; i++) {
            for (int j = i + 1; j < p.size(); j++) {
                if (p.get(i).getObjectiveValues()[poz] > p.get(j).getObjectiveValues()[poz]) {
                    Individual ind = p.get(i);
                    p.set(i, p.get(j));
                    p.set(j, ind);
                }
            }
        }
    }

    // this is bullshit

    // take them notes down

    // the happy hobb ?

    public void nonDominatedSort(Population pop) {
        // this is not good.. this is bad..
        // it think this one stinks like a  big fcking dump
        // would you like to eat dump for 200 souls ?
        // hell yeah..
        fronts.clear();
        Front currentFront = new Front();

        // 1.  Mark every individual, by there domination

        try {
            for (int i = 0; i < pop.size(); i++) {
                Individual p = pop.get(i);
                for (int j = 0; j < pop.size(); j++) {
                    Individual q = pop.get(j);
//					if (!p.equals(q)) {
                    if (dominates(p, q)) {
                        p.addDominatedSolution(q);
                    } else {
                        if (dominates(q, p)) {
                            p.incrementDominationCount();
                        }
                    }
                }
//				}
                if (p.getDominationCount() == 0) {
                    // no other solution dominates p
                    currentFront.add(p);
                }
            }
        } catch (SomethingWentWrongException e) {
            e.printStackTrace();
        }

        // build fronts, based on domination count

        fronts.add(currentFront);
        Front nextFront;
        int frontIndex = 0;

        while (currentFront.size() > 0) {
            //	currentFront = ranking.get(frontIndex);
            nextFront = new Front();
            for (Individual p : currentFront) {
                // iterate through
                for (int j = 0; j < p.getDominatedSetSize(); j++) {
                    Individual q = p.getDominatedSolution(j);
                    q.decrementDominationCount();
                    if (q.getDominationCount() == 0) {
                        nextFront.add(q);
                        q.setFitness(frontIndex + 1);
                        q.setRank(frontIndex + 1);
                    }
                }
            }
            frontIndex++;
            fronts.add(nextFront);
            currentFront = nextFront;
        }
        //ranking.removeLast();
    }

    private boolean dominates(Individual p, Individual q) {
        return p.lessThen(q);
    }

    /**
     * Selects the best @param count genes
     *
     * @param count
     * @return
     */
    private Population makeSelection(int count) {
        // merge the two population by their fitness and crowding distance, and then
        // select then return the first half
        // we make the selection based on the non dominated sort ranking
        LinkedList<Individual> result = new LinkedList<>();

        int i = 0;
        //int frontIndex = 0;
        while (i < count && fronts.size() > 0) {
            Front front = fronts.getFirst();
            for (Individual ind : front) {
                if (i < count) {
                    result.add(ind);
                }
                i++;
            }
            //frontIndex++;
            fronts.removeFirst();
        }

        return new Population(result);
    }

    public LinkedList<Front> getRanking() {

        return fronts;
    }

    // for now, only for testing purposes
    public LinkedList<Individual> getRankedList() {

        LinkedList<Individual> ranked = new LinkedList<>();

        for (Front l : fronts) {
            for (Individual ind : l) {
                ranked.add(ind);
            }
        }
        return ranked;
    }

    // Returns the first front, if possible
    public Front getFirstFront() {

        Front result;
        if (fronts == null || fronts.size() < 1) {
            result = null;
        } else {
            result = fronts.getFirst();
        }
        return result;
    }

    public int getFrontCount() {

        int result = 0;
        if (fronts != null) {
            result = fronts.size();
        }
        return result;
    }
    // this is just a piece of shit.. don't worry love.. no one will notice this..

}
