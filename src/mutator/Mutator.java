package mutator;

import model.Population;

/**
 * Execute modifications on Individuals, or populations
 */
public interface Mutator {
    public void modify(Population x);
}
