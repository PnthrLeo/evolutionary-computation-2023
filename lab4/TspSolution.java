package lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TspSolution {
    ArrayList<Integer> path;

    TspSolution(int dimension) {
        this.path = new ArrayList<Integer>();

        for (int i = 0; i < dimension; i++) {
            this.path.add(i);
        }
    }

    TspSolution(TspSolution solution) {
        this.path = new ArrayList<Integer>();

        for (int i = 0; i < solution.path.size(); i++) {
            this.path.add(solution.path.get(i));
        }
    }

    public void swap(int i, int j) {
        Collections.swap(path, i, j);
    }

    public int[] generate_borders(Random rand) {
        int a, b;

        a = (int) Math.floor(rand.nextDouble() * path.size());
        b = a;
        while (b == a) {
            b = (int) Math.floor(rand.nextDouble() * path.size());
        }

        if (a > b) {
            int temp = a;
            a = b;
            b = temp;
        }

        return new int[] {a, b};
    }
}
