package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class MyFrame extends JFrame {
	
	/**
	 * What is this id about
	 *
	 */	
	
	private static final long serialVersionUID = 1L;
	
	private MyPanel panelPlotter;	
	
	private static int locationX = 50;
	private static int locationY = 430;	
	
	public MyFrame() {
		
		super("Plot somehting");
		panelPlotter = new MyPanel();				
		getContentPane().add(panelPlotter, BorderLayout.CENTER);
		panelPlotter.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		int offset = 250;
		
		this.setLocation(locationX, locationY);
	
		//System.out.println(offset + " , " + locationX + " , " + locationY);
		locationX += offset;
		
		this.setSize(offset, offset);		
	
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double hheight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		if(locationX + offset > width) {
			locationX = 50;
			locationY += offset;
		}
		
		if(locationY + offset > hheight) {
			locationY = 10;
			locationX = 901; 
		}
		
		setVisible(true);		
	}
	
	public void addToPlot(double x, double y) {
		
		panelPlotter.paintPoint(x,y);
	}
	
	public void startPlotting() {
		
		panelPlotter.paintMesh();
	}
	
}
