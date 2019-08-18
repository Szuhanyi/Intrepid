package output;

import model.Individual;
import model.Population;

/**
 * General tool for printing data
 *
 * @author Gyorgy
 */

public class OutputService {

    private static OutputService instance;
    private OutputDestination output;

    private OutputService() {

        output = new OutputConsole();
    }

    public static OutputService getInstance() {
        if (instance == null) {
            instance = new OutputService();
        }
        return instance;
    }

    public void printById(Population p) {
        String text = getValues(p);
        output.print(text);
    }

    /**
     * tag.. you're it
     * @param p
     */
    public void printByNonDominatedRank(Population p) {
        // what is this for ?

        // where
    }

    public void printByFitness(Population p) {
        //sort it ? and then print it out
        p.sortByFitness();

        output.print(getValues(p));
    }

    private String getValues(Population p) {
        String out = "";
        for (int i = 0; i < p.size(); i++) {
            Individual ind = p.get(i);
            out += "i="+i;
          //  out += "   id=" + ind.getId();
            out += " fitness=" + (float) ind.getFitness();
            out += "  " + " rank = " + ind.getRank();

            double[] decVar = ind.getDecisionVariables();
            out += " x = " + (float) decVar[0];
            if (decVar.length > 1) {
                for (int j = 1; j < decVar.length; j++) {
                    out += " x" + j + " = " + (float) decVar[j];
                }
            }

            for (int j = 0; j < ind.getObjectiveValues().length; j++) {
                out += "   y" + j + "=" + (float) ind.getObjectiveValues()[j];
            }
            out += "\r\n";
        }

        return out;
    }

    public void setOutputToConsole() {
        output = new OutputConsole();
    }

    public void setOutputToFile(String folderPath) {
        output = new OutputFile(folderPath);
    }

    /**
     * print data do the app
     */
    public void setOutputToApplication() {
        output = new OutputApplication();
    }
}