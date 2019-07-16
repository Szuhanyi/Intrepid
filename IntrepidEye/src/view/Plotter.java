package view;

import model.Population;

public class Plotter {

    private MyFrame frame;


    public Plotter() {

        frame = new MyFrame();
    }

    public void showMeTheGraph(Population p) {

        for (int i = 0; i < p.size(); i++) {
            double[] obj = p.get(i).getObjectiveValues();
            frame.addToPlot(obj[0], obj[1]);
        }
        frame.startPlotting();
    }

}
