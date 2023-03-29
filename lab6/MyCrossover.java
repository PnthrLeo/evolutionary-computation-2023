package lab5;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyCrossover extends AbstractCrossover<double[]> {
    protected MyCrossover() {
        super(1);
    }

    protected List<double[]> mate(double[] p1, double[] p2, int numberOfCrossoverPoints, Random rand) {
        ArrayList<double[]> children = new ArrayList<double[]>();
        double[] c1 = p1.clone();
        double[] c2 = p2.clone();
        double a = 0.5;
        double x1, x2, lb, rb;

        for (int i = 0; i < p1.length; i++) {
                x1 = Math.min(p1[i], p2[i]);
                x2 = Math.max(p1[i], p2[i]);
                lb = x1 - a * (x2 - x1);
                rb = x2 + a * (x2 - x1);

                c1[i] = rand.nextDouble() * (rb - lb) - lb;
                c2[i] = rand.nextDouble() * (rb - lb) - lb;

                c1[i] = Math.min(Math.max(-5, p1[i]), 5);
                c2[i] = Math.min(Math.max(-5, p2[i]), 5);
        }

        children.add(c1);
        children.add(c2);
        return children;
    }
}
