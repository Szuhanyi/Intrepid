package view;

import model.Population;

/**
 * This plotter sucks. Can plot basic stuff.. don't expect much.
 */
public class Plotter {

    private MyFrame frame;


    public Plotter() {
        frame = new MyFrame();

    }

    public void showMeTheGraph(Population p) {
        for (int i = 0; i < p.size(); i++) {
            double[] obj = p.get(i).getObjectiveValues();
            frame.addToPlot(obj[0], obj[1], p.get(i).getRank());
        }
        frame.startPlotting();
    }

    public void showMeObjectiveSpace(Population p) {
        if(p.get(0).getDecisionVariables().length > 1) {
            for (int i = 0; i < p.size(); i++) {
                double[] obj = p.get(i).getDecisionVariables();
                frame.addToPlot(obj[0], obj[1], p.get(i).getRank());
            }
        }
        else {
            for (int i = 0; i < p.size(); i++) {
                double[] obj = p.get(i).getDecisionVariables();
                frame.addToPlot(obj[0] * 50, (float)(i / 10) , p.get(i).getRank());
            }

        }
        frame.startPlotting();
    }

}
