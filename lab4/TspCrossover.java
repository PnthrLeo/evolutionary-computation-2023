package lab3;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class TspCrossover extends AbstractCrossover<TspSolution> {
    protected TspCrossover() {
        super(1);
    }

    protected List<TspSolution> mate(TspSolution p1, TspSolution p2, int numberOfCrossoverPoints, Random rand) {
        ArrayList<TspSolution> children = new ArrayList<TspSolution>();
        int[] borders = p1.generate_borders(rand);
        TspSolution c1 = get_child(p1, p2, borders);
        TspSolution c2 = get_child(p2, p1, borders);        

        children.add(c1);
        children.add(c2);
        return children;
    }

    private TspSolution get_child(TspSolution p1, TspSolution p2, int[] borders) {
        TspSolution c1 = new TspSolution(p1);
        TspSolution temp2 = new TspSolution(p2);

        for (int i = borders[0]; i < borders[1]; i++) {
            temp2.path.remove(Integer.valueOf(c1.path.get(i)));
        }

        int j = -1;
        int iter = borders[1];
        while (j == -1) {
            j = temp2.path.indexOf(p2.path.get(iter));
            iter++;
            if (iter == p2.path.size()) {
                iter = 0;
            }
        }

        for (int i = borders[1]; i < c1.path.size(); i++) {
            c1.path.set(i, temp2.path.get(j));
            j++;
            if (j == temp2.path.size()) {
                j = 0;
            }
        }

        for (int i = 0; i < borders[0]; i++) {
            c1.path.set(i, temp2.path.get(j));
            j++;
            if (j == temp2.path.size()) {
                j = 0;
            }
        }

        return c1;
    }
}
