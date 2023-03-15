package lab3;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;
import java.util.Collections;
import java.util.Random;

public class TspFactory extends AbstractCandidateFactory<TspSolution> {

    int dimension;

    TspFactory(int dimension) {
        this.dimension = dimension;
    }

    public TspSolution generateRandomCandidate(Random rand) {
        TspSolution solution = new TspSolution(dimension);
        Collections.shuffle(solution.path, rand);
        return solution;
    }
}

