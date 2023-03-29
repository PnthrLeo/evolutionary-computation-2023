package lab5;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.islands.IslandEvolution;
import org.uncommons.watchmaker.framework.islands.IslandEvolutionObserver;
import org.uncommons.watchmaker.framework.islands.Migration;
import org.uncommons.watchmaker.framework.islands.RingMigration;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;


public class IslandsAlg {

    public static void main(String[] args) {
        int number_of_experiments = 1;
        
        double avg_best_fitness = 0;
        double avg_time = 0;

        for (int i = 0; i < number_of_experiments; i++) {
            Instant start = Instant.now();
            avg_best_fitness += run_experiment();
            Instant finish = Instant.now();
            avg_time += Duration.between(start, finish).toMillis();
        }
        
        System.out.println("Best fitness: " + avg_best_fitness / number_of_experiments + " ms");
        System.out.println("Time elapsed: " + avg_time / number_of_experiments + " ms");
    }

    static double run_experiment() {
        int dimension = 50; // dimension of problem
        int complexity = 5; // fitness estimation time multiplicator
        int populationSize = 100; // size of population
        int generations = 10; // number of generations

        Random random = new Random(); // random

        CandidateFactory<double[]> factory = new MyFactory(dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<double[]>> operators = new ArrayList<EvolutionaryOperator<double[]>>();
        operators.add(new MyCrossover()); // Crossover
        operators.add(new MyMutation()); // Mutation
        EvolutionPipeline<double[]> pipeline = new EvolutionPipeline<double[]>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<double[]> evaluator = new MultiFitnessFunction(dimension, complexity); // Fitness function

        Migration migration = new RingMigration(); // Migration operator

        IslandEvolution<double[]> island_model = new IslandEvolution(1000000000, migration, factory, pipeline, evaluator, selection, random);

        island_model.addEvolutionObserver(new IslandEvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Epoch " + populationData.getGenerationNumber() + ": " + bestFit);
                System.out.println("\tEpoch best solution = " + Arrays.toString((double[])populationData.getBestCandidate()));
            }

            public void islandPopulationUpdate(int i, PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Island " + i);
                System.out.println("\tGeneration " + populationData.getGenerationNumber() + ": " + bestFit);
                System.out.println("\tBest solution = " + Arrays.toString((double[])populationData.getBestCandidate()));
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        double[] best_solution = island_model.evolve(populationSize, 1, 50, 2, terminate);

        double best_fitness = evaluator.getFitness(best_solution, null);
        return best_fitness;
    }
}
