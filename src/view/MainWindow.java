package view;

import algorithms.Algorithm;
import algorithms.NsgaAlgorithm;
import dynamic.Counter;
import metrics.Metrics;
import model.Population;
import output.OutputService;
import services.ApplicationConsoleService;
import services.Attributes;
import testFunctions.Function;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    //	private int functionIndex;
    private int populationSize;
    private int generationCount;
    private double crossoverProbability;
    private double mutationProbability;
    private JTextArea txtrTextArea;
    private boolean needPlotting;
    private MainWindow instance;
    private Counter counter;
    private String selectedFunctionName;
    private JTextField textFieldT;
    public MainWindow() {

        instance = this;
        getContentPane().setLayout(null);

        String[] data = {"Schaffer", "Fonseca1", "Bihn & Korn"};
        JList<String> listFunctions = new JList<>(data);
        listFunctions.setVisibleRowCount(4);
//		listFunctions.setModel(new AbstractListModel() {
//			String[] values = new String[] {"Schaffer", "Fonseca1", "Bihn & Korn", "Zdt1", "Zdt2", "Zdt3", "Zdt4", "Zdt6"};
//			public int getSize() {
//				return values.length;
//			}
//			public Object getElementAt(int index) {
//				return values[index];
//			}
//		});

        listFunctions.setSelectedIndex(0);
        listFunctions.setBounds(217, 82, 145, 153);

        getContentPane().add(listFunctions);

        JLabel lblFunctions = new JLabel("Functions");
        lblFunctions.setBounds(217, 60, 56, 16);
        getContentPane().add(lblFunctions);

        JSpinner spinnerGenerations = new JSpinner();
        spinnerGenerations.setModel(new SpinnerNumberModel(new Integer(10), null, null, new Integer(1)));
        spinnerGenerations.setBounds(27, 201, 56, 22);
        getContentPane().add(spinnerGenerations);

        JLabel lblGenerations = new JLabel("Generations");
        lblGenerations.setBounds(27, 181, 82, 16);
        getContentPane().add(lblGenerations);

        JSpinner spinnerPopulationSize = new JSpinner();
        spinnerPopulationSize.setModel(new SpinnerNumberModel(new Integer(200), null, null, new Integer(1)));
        spinnerPopulationSize.setBounds(27, 146, 56, 22);
        getContentPane().add(spinnerPopulationSize);

        JLabel lblPopulationSize = new JLabel("Population Size");
        lblPopulationSize.setBounds(27, 129, 123, 16);
        getContentPane().add(lblPopulationSize);

        JSpinner spinnerCrossover = new JSpinner();
        spinnerCrossover.setModel(new SpinnerNumberModel(50.0, 1.0, 100.0, 5.0));
        spinnerCrossover.setBounds(27, 46, 56, 22);
        getContentPane().add(spinnerCrossover);

        JLabel lblCrossoverProbability = new JLabel("Crossover Probability");
        lblCrossoverProbability.setBounds(27, 27, 133, 16);
        getContentPane().add(lblCrossoverProbability);

        JCheckBox chckbxPlot = new JCheckBox("Plot result");
        chckbxPlot.setBounds(27, 230, 113, 25);
        getContentPane().add(chckbxPlot);

        JSpinner spinnerMutation = new JSpinner();
        spinnerMutation.setModel(new SpinnerNumberModel(10.0, 1.0, 100.0, 5.0));
        spinnerMutation.setBounds(27, 94, 56, 22);
        getContentPane().add(spinnerMutation);

        JButton btnRunButton = new JButton("Run");
        btnRunButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // some saving stuff.. Who know what to do... I can't think of a solution
                generationCount = (int) spinnerGenerations.getValue();
                populationSize = (int) spinnerPopulationSize.getValue();
                //functionIndex = listFunctions.getSelectedIndex();
                crossoverProbability = (double) ((double) spinnerCrossover.getValue()) / 100;
                needPlotting = chckbxPlot.isSelected();
                mutationProbability = (double) spinnerMutation.getValue() / 100;
                selectedFunctionName = listFunctions.getSelectedValue().toString();
                runSimulation();

            }
        });

        btnRunButton.setBounds(691, 278, 167, 81);
        getContentPane().add(btnRunButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(388, 13, 470, 242);
        getContentPane().add(scrollPane);

        txtrTextArea = new JTextArea();
        txtrTextArea.setEditable(false);
        scrollPane.setViewportView(txtrTextArea);
        // set the output object for the service
        ApplicationConsoleService.getInstance().setOutput(txtrTextArea);


        JLabel lblMutationProbability = new JLabel("Mutation Probability");
        lblMutationProbability.setBounds(27, 72, 123, 16);
        getContentPane().add(lblMutationProbability);

        textFieldT = new JTextField();
        textFieldT.setText("0");
        textFieldT.setBounds(27, 278, 82, 22);
        getContentPane().add(textFieldT);
        textFieldT.setColumns(10);

        JLabel lblTVariable = new JLabel("T variable");
        lblTVariable.setBounds(27, 264, 82, 16);
        getContentPane().add(lblTVariable);

        JButton btnStartCounter = new JButton("Start T");
        btnStartCounter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                counter = new Counter(instance);
                String s = textFieldT.getText();
                int value = 0;
                try {
                    value = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                counter.setT(value);
                counter.start();
            }
        });
        btnStartCounter.setBounds(27, 313, 97, 25);
        getContentPane().add(btnStartCounter);

        JButton btnPauseT = new JButton("Pause T");
        btnPauseT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                counter.pauseCounting();
            }
        });
        btnPauseT.setBounds(136, 313, 97, 25);
        getContentPane().add(btnPauseT);

        JButton btnResumeT = new JButton("Resume T");
        btnResumeT.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                counter.continueCounting();
            }
        });
        btnResumeT.setBounds(251, 313, 97, 25);
        getContentPane().add(btnResumeT);


        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(888, 426));
    }

    public void setT(int t) {

        textFieldT.setText(Integer.toString(t));
    }

    private void runSimulation() {

        Attributes.setCrossoverProbability(crossoverProbability);
        Attributes.setCycleCount(generationCount);
        Attributes.setMutationProbability(mutationProbability);
        Attributes.setPopulationSize(populationSize);
        OutputService output = OutputService.getInstance();
        output.setOutputToApplication();

        //System.out.println(selectedFunctionName);
        // could I exchange this to an abstract factory ?
        switch (selectedFunctionName) {
            case "Schaffer": {
                Function.newSchaffer();
                break;
            }
            case "Fonseca1": {
                Function.newFonseca();
                break;
            }
            case "Bihn & Korn": {
                Function.newBihnKorn();
                break;
            }
            case "Zdt1": {
                Function.newZdt1();
                break;
            }
            case "Zdt2": {
                Function.newZdt2();
                break;
            }
            case "Zdt3": {
                Function.newZdt3();
                break;
            }
            case "Zdt4": {
                Function.newZdt4();
                break;
            }
            case "Zdt6": {
                Function.newZdt6();
                break;
            }
            default: {
                Function.newSchaffer();
                break;
            }
        }

        Population p = new Population(Attributes.getPopulationSize());

        Algorithm a = new NsgaAlgorithm();

        Population opt = a.optimize(p, Attributes.getCycleCount());
        output.printByFitness(opt);


        if (needPlotting) {
            Plotter plot = new Plotter();
            plot.showMeTheGraph(opt);
        }

//        Metrics metr = new Metrics();
//        double delta = metr.getAvarageDistanceDelta(opt);
//        double gamma = metr.getDistributionMetricSecond(opt);
//
//        System.out.println("Convergence: " + delta);
//        System.out.println("Distance: " + gamma);

    }
}
