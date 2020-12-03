package model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * Some sort of holding class, for representing a tier of solutions.
 *
 */
public class Front implements Iterable<Individual>  {

    private List<Individual>  solutions;

    public Front() {
        solutions = new ArrayList<Individual>();
    }

    public Front(List<Individual> list) {

        // create a new front with these exact individuals

        this(); // isn't this implicitly called ? do I need to be explicit ...

        for(Individual i : list) {
            solutions.add(i);
        }
    }

    public void add(Individual i) {
        solutions.add(i);
    }


    public void remove(Individual i) {
        solutions.remove(i);
    }


    public Individual get(int index) {
        return solutions.get(index);
    }


    public int size() {
        return solutions.size();
    }


    public void set(int index, Individual object) {
        solutions.set(index, object);
    }


    @Override
    public Iterator<Individual> iterator() {
        return solutions.iterator();
    }

}

