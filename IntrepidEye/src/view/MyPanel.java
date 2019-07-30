package view;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class MyPanel extends JPanel {


    /**
     * why do I get a warning message about this ? why is this the default ?
     */

    private static final long serialVersionUID = 1L;

    private LinkedList<Point> mesh;
    private double unit;

    public MyPanel() {

        super();
        //show the ordinate and the coordinate axes
        mesh = new LinkedList<>();
    }

    public void paintMesh() {

        double max = getMax();
        Dimension d = this.getSize();
        unit = d.getWidth() / max / 4;
        ///unit = unit;
//		System.out.println(max);
//		System.out.println(unit);
        //plot the mesh onto the panel
        this.setVisible(true);
        this.repaint();
    }

    public void paintPoint(double x, double y) {

        mesh.add(new Point(x, y));
        //paintMesh();
    }


    @Override
    public void paintComponent(Graphics g) {

        Graphics2D gg = (Graphics2D) g;
        super.paintComponent(gg);
        double width = this.getSize().getWidth();
        double height = this.getSize().getHeight();
        //g.setColor(Color.RED);
        double rad = Math.PI / 4;
        //g.rotate(-rad);
        double offset = height / 2;

        gg.translate(offset, height - offset);
        gg.scale(1.0, -1.0);
        for (Point p : mesh) {
            gg.drawOval((int) (unit * p.getY()), (int) (unit * p.getX()), 5, 5);
            //g.drawOval((int)(unit * p.getX()), (int)(width - unit *  p.getY()), 5, 5);
        }

    }

    private double getMax() {

        double result = 0.0;
        for (int i = 0; i < mesh.size(); i++) {
            Point p = mesh.get(i);
            if (result < Math.abs(p.getX())) {
                result = Math.abs(p.getX());
            }
            if (result < Math.abs(p.getY())) {
                result = Math.abs(p.getY());
            }
        }

        return result;
    }


    private class Point {

        double x;
        double y;
        public Point(double x2, double y2) {

            x = x2;
            y = y2;
        }

        public double getX() {

            return x;
        }

        public double getY() {

            return y;
        }
    }

}