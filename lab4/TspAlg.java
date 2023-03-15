package lab3;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RankSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.util.ArrayList;
import java.util.Random;

public class TspAlg {

    public static void main(String[] args) {
        String problem = "./data/xqf131.tsp"; // name of problem or path to input file
        TspData data = new TspData(problem);

        int populationSize = 1500; // size of population
        int generations = 2000; // number of generations

        Random random = new Random(); // random

        CandidateFactory<TspSolution> factory = new TspFactory(data.dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<TspSolution>> operators = new ArrayList<EvolutionaryOperator<TspSolution>>();
        operators.add(new TspCrossover()); // Crossover
        operators.add(new TspMutation()); // Mutation
        EvolutionPipeline<TspSolution> pipeline = new EvolutionPipeline<TspSolution>(operators);

        // SelectionStrategy<Object> selection = new RankSelection(); // Selection operator

        FitnessEvaluator<TspSolution> evaluator = new TspFitnessFunction(data); // Fitness function

        // EvolutionEngine<TspSolution> algorithm = new SteadyStateEvolutionEngine<TspSolution>(
        //         factory, pipeline, evaluator, selection, populationSize, false, random);
        
        // GenerationalEvolutionEngine<TspSolution> algorithm = new GenerationalEvolutionEngine<TspSolution>(
        //         factory, pipeline, evaluator, selection, random);

        EvolutionStrategyEngine<TspSolution> algorithm = new EvolutionStrategyEngine<TspSolution>(
                factory, pipeline, evaluator, true, 7, random);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                TspSolution best = (TspSolution)populationData.getBestCandidate();
                System.out.println("\tBest solution = " + best.path.toString());
            }
        });

        TerminationCondition terminate = new GenerationCount(generations);
        algorithm.evolve(populationSize, 500, terminate);
    }
}
