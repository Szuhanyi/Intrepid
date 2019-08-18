package algorithms;

import model.Population;

/**
 * Search algorithms
 * pretty useless in his own..
 * @author Gyorgy
 */

public abstract class Algorithm {

    public abstract Population optimize(Population p, int cycleCount);

}