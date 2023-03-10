package lab2;

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

    // ##########################################################################
    //                           OTHER IMPLEMENTATIONS
    // ##########################################################################
    //
    // public List<double[]> apply(List<double[]> population, Random rand) {
    //     int j, k, l;
    //     int count_mutations, idx;

    //     for (int i = 0; i < population.size(); i++) {
    //         j = i; k = i; l = i;
    //         while (j != i) {
    //             j = (int) Math.floor(rand.nextDouble() * population.size());
    //         }
    //         while (k != i && k != j) {
    //             k = (int) Math.floor(rand.nextDouble() * population.size());
    //         }
    //         while (l != i && l != j && l != k) {
    //             k = (int) Math.floor(rand.nextDouble() * population.size());
    //         }
    //         double[] p = population.get(i);
    //         double[] p1 = population.get(j);
    //         double[] p2 = population.get(k);
    //         double[] p3 = population.get(l);

    //         count_mutations = 0;
    //         while (count_mutations < 1) {
    //             idx = (int) Math.floor(rand.nextDouble() * p.length);
    //             p[idx] = p1[idx] + rand.nextDouble() * (p2[idx] - p3[idx]);
    //             p[idx] = Math.min(Math.max(-5, p[idx]), 5);
    //             count_mutations += 1;
    //         }
    //         population.set(i, p);
    //     }

    //     return population;
    // }

    // public List<double[]> apply(List<double[]> population, Random rand) {
    //     int idx, count_mutations;

    //     for (int i = 0; i < population.size(); i++) {
    //         if (rand.nextDouble() < 0.9) {
    //             double[] p = population.get(i);
    //             count_mutations = 0;
    //             while (count_mutations < p.length*p.length) {
    //                 idx = (int) Math.floor(rand.nextDouble() * p.length);
    //                 p[idx] += rand.nextGaussian()*0.00001;
    //                 p[idx] = Math.min(Math.max(-5, p[idx]), 5);
    //                 count_mutations += 1;
    //             }

    //             population.set(i, p);
    //         }
    //     }
    //     return population;
    // }


    // private int generations_quantity;
    // private int count_generations;

    // public MyMutation(int generations_quantity) {
    //     this.generations_quantity = generations_quantity;
    //     this.count_generations = 0;
    // }

    // public List<double[]> apply(List<double[]> population, Random rand) {

    //     this.count_generations += 1;
    //     double prob = (generations_quantity - count_generations) / generations_quantity;
    //     int idx, count_mutations;

    //     for (int i = 0; i < population.size(); i++) {
    //         double[] p = population.get(i);
    //         count_mutations = 0;
    //         while (count_mutations < p.length / 3) {
    //             idx = (int) Math.floor(rand.nextDouble() * p.length);
    //             p[idx] += (rand.nextGaussian() * prob);
    //             count_mutations += 1;
    //         }

    //         population.set(i, p);
    //     }
    //     return population;
    // }
}
