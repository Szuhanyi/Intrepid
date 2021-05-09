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
        evaluate(parentGeneration);
        Population newGeneration = null;
        Population allGene = null;
        for (int i = 0; i < cycleCount; i++) {
            newGeneration = new Population(Attributes.getPopulationSize());
            allGene = parentGeneration.append(newGeneration);
            evaluate(allGene);
            parentGeneration = makeSelection(Attributes.getPopulationSize());

            System.out.printf("Parent generation's count is : %s", parentGeneration.size());
            System.out.println();
        }

        return parentGeneration;
    }

    /**
     * assign fitness to every member of the population
     * @param p
     */
    private void evaluate(Population p) {
        nonDominatedSort(p);
        assignCrowdingDistance();
        fitnessAssignment(p);
    }

    private void fitnessAssignment(Population p) {
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
            p.get(0).setDistance(Integer.MAX_VALUE);
            p.get(p.size() - 1).setDistance(Integer.MAX_VALUE);
            for (int i = 1; i < p.size() - 1; i++) {
                p.get(i).setDistance((p.get(i - 1).getDistance() + p.get(i + 1).getDistance()) / 2);
            }
        }
    }


    private void normalSort(Front p, int pozOfObjValue) {
        for (int i = 0; i < p.size() - 1; i++) {
            for (int j = i + 1; j < p.size(); j++) {
                if (p.get(i).getObjectiveValues()[pozOfObjValue] > p.get(j).getObjectiveValues()[pozOfObjValue]) {
                    Individual ind = p.get(i);
                    p.set(i, p.get(j));
                    p.set(j, ind);
                }
            }
        }
    }


    public void nonDominatedSort(Population pop) {
        fronts.clear();
        Front currentFront = new Front();
        int noOfPopsTWhoDontDominateLikeLoosers = 0;
        try {
            // sort the individuals, and then tag them
            for (int i = 0; i < pop.size(); i++) {
                Individual p = pop.get(i);
                for (int j = 0; j < pop.size(); j++) {
                    Individual q = pop.get(j);
                    if (dominates(p, q)) {
                        p.addDominatedSolution(q);
                    } else {
                        if (dominates(q, p)) {
                            p.incrementDominationCount();
                        }
                        else {
                            // then you go to the shadow realm, and that's it!!
                            // return to monke
                            if(p != q)
                                noOfPopsTWhoDontDominateLikeLoosers++;



                        }
                    }
                }
                if (p.getDominationCount() == 0) {
                    currentFront.add(p);
                }
            }
        } catch (SomethingWentWrongException e) {
            e.printStackTrace();
        }
        // build fronts, based on the tags
        fronts.add(currentFront);
        Front nextFront;
        int frontIndex = 0;

        int threshold = 1000;
        while (currentFront.size() > 0 && threshold-- > 0) {
            nextFront = new Front();
            for (Individual p : currentFront) {
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
            if(nextFront.size() > 0) {
                frontIndex++;
                fronts.add(nextFront);
                currentFront = nextFront;
            }
        }
        System.out.printf("No of fronts : %s%n",frontIndex);

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

        // only by fitness.. or by front.. crowding distance is not here at all
        // are the fronts sorted by themselves ?  or how are things represented ?

        int i = 0;
        while (i < count && fronts.size() > 0) {
            Front front = fronts.getFirst();
            for (Individual ind : front) {
                if (i < count) {
                    result.add(ind);
                }
                i++;
            }
            fronts.removeFirst();
        }
        return new Population(result);
    }

    public LinkedList<Front> getRanking() {
        return fronts;
    }

    public LinkedList<Individual> getRankedList() {
        LinkedList<Individual> ranked = new LinkedList<>();
        for (Front l : fronts) {
            for (Individual ind : l) {
                ranked.add(ind);
            }
        }
        return ranked;
    }

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
}
