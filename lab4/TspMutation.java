package lab3;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.util.Random;

public class TspMutation implements EvolutionaryOperator<TspSolution> {
    public List<TspSolution> apply(List<TspSolution> population, Random random) {
        for (int i = 0; i < population.size(); i++) {

            TspSolution solution = population.get(i);
            int[] borders = solution.generate_borders(random);
            solution.swap(borders[0], borders[1]);
            population.set(i, solution);
        }

        return population;
    }
}
