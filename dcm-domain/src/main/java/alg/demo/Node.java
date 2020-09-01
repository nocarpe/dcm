package alg.demo;

import alg.demo2.Point;
import java.util.ArrayList;

public class Node {
    private double j;
    private double w;
    private String spot;

    public double dicToNode(Node n) {
        double x, y, out;
        double PI = Math.PI;
        double R = 6.371229 * 1e6;
        x = (n.getJ() - this.getJ()) * PI * R * Math.cos(((n.getW() + this.getW()) / 2) * PI / 180) / 180;
        y = (n.getW() - this.getW()) * PI * R / 180;
        out = Math.sqrt(x * x + y * y);
        return (int) (out / 1000);
    }



    public Node(double j, double w, String spot) {
        this.j = j;
        this.w = w;
        this.spot = spot;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
    }

    public String getSpot() {
        return spot;
    }

    public void setSpot(String spot) {
        this.spot = spot;
    }
}
