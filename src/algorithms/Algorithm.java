package algorithms;

import model.Population;

/**
 * Search algorithms
 * @author Gyorgy
 *
 */

public abstract class Algorithm {	
	
	public abstract Population optimize(Population p, int cycleCount);
	
}
