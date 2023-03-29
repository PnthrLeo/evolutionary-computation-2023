package lab5;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

public class MasterSlaveAlg {

    public static void main(String[] args) {
        int number_of_experiments = 10;
        
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
        int complexity = 2; // fitness estimation time multiplicator
        int populationSize = 1200; // size of population
        int generations = 500; // number of generations

        Random random = new Random(); // random

        CandidateFactory<double[]> factory = new MyFactory(dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<double[]>> operators = new ArrayList<EvolutionaryOperator<double[]>>();
        operators.add(new MyCrossover()); // Crossover
        operators.add(new MyMutation()); // Mutation
        EvolutionPipeline<double[]> pipeline = new EvolutionPipeline<double[]>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<double[]> evaluator = new MultiFitnessFunction(dimension, complexity); // Fitness function

        AbstractEvolutionEngine<double[]> algorithm = new SteadyStateEvolutionEngine<double[]>(
                factory, pipeline, evaluator, selection, populationSize, false, random);

        algorithm.setSingleThreaded(true);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                System.out.println("\tBest solution = " + Arrays.toString((double[])populationData.getBestCandidate()));
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        double[] best_solution = algorithm.evolve(populationSize, 1, terminate);
        
        double best_fitness = evaluator.getFitness(best_solution, null);
        return best_fitness;
    }
}
