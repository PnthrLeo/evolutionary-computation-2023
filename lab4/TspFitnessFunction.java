package lab3;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

public class TspFitnessFunction implements FitnessEvaluator<TspSolution> {

    int dimension;
    ArrayList<Integer[]> nodeCoords;

    public TspFitnessFunction(TspData data) {
        this.dimension = data.dimension;
        this.nodeCoords = data.nodeCoords;
    }

    public double getFitness(TspSolution solution, List<? extends TspSolution> list) {
        double sum = 0.0;

        int idx1, idx2;
        Integer[] pos1, pos2;
        for (int i = 1; i < solution.path.size(); i++) {
            idx1 = solution.path.get(i-1);
            idx2 = solution.path.get(i);
            pos1 = nodeCoords.get(idx1);
            pos2 = nodeCoords.get(idx2);
            sum += (Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]));
        }
        idx1 = solution.path.get(solution.path.size()-1);
        idx2 = solution.path.get(0);
        pos1 = nodeCoords.get(idx1);
        pos2 = nodeCoords.get(idx2);
        sum += (Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]));
        return sum;
    }

    public boolean isNatural() {
        return false;
    }
}
