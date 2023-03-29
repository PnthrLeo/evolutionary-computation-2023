package lab5;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.List;
import java.lang.Math;
import java.util.Random;

public class MyMutation implements EvolutionaryOperator<double[]> {

    public List<double[]> apply(List<double[]> population, Random rand) {
        int count_mutations, idx;

        for (int i = 0; i < population.size(); i++) {
            double[] p = population.get(i);

            count_mutations = 0;
            while (count_mutations < 1) {
                idx = (int) Math.floor(rand.nextDouble() * p.length);
                p[idx] += rand.nextDouble() * rand.nextGaussian();
                p[idx] = Math.min(Math.max(-5, p[idx]), 5);
                count_mutations += 1;
            }
            population.set(i, p);
        }

        return population;
    }
}
