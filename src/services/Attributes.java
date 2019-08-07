package services;

/**
 * Contains a few global values
 *
 * @author Gyorgy
 */
public class Attributes {

    private static double mutationProbability = 0.01;
    private static double crossoverProbability = 0.5;
    private static int populationSize = 50;
    private static int cycleCount = 4;
    private static int t = 0;

    public static double getCrossoverProbability() {
        return crossoverProbability;
    }

    public static void setCrossoverProbability(double crossoverProbability) {
        Attributes.crossoverProbability = crossoverProbability;
    }

    public static double getMutationProbability() {
        return mutationProbability;
    }

    public static void setMutationProbability(double mutationProbability) {
        Attributes.mutationProbability = mutationProbability;
    }

    public static int getPopulationSize() {
        return populationSize;
    }

    public static void setPopulationSize(int populationSize) {
        Attributes.populationSize = populationSize;
    }

    public static int getCycleCount() {
        return cycleCount;
    }

    public static void setCycleCount(int cycleCount) {
        Attributes.cycleCount = cycleCount;
    }

    public static int getT() {
        return t;
    }

    public static void setT(int tt) {
        //not this is just an addition, but it could be a random number generation as well
        t = tt;
    }
}
