import random

from deap import algorithms
from deap import base
from deap import creator
from deap import tools


NUMBER_OF_RUNS = 1


NUMBER_OF_QUEENS = 100
NUMBER_OF_GENERATIONS=100000
POPULATION_SIZE = 10
CROSSOVER_PROBABILITY = 0.0
MUTATION_PROBABILITY = 1.0
ATTRIBUTE_MUTATION_PROBABILITY = 1.4 / NUMBER_OF_QUEENS


def evaluate(individual: list[int]) -> list[int]:
    """Evaluates the fitness of an individual. The individual is a list of 
    permutations of the numbers 0 to PROBLEM_SIZE. The fitness is the number of conflicts 
    between pairs of queens. A conflict occurs when two queens are in the same 
    row, column, or diagonal. The fitness is the number of conflicts, so 
    the lower the fitness the better the solution.

    Args:
        individual (list[int]): list of permutations of the numbers 0 to PROBLEM_SIZE

    Returns:
        list[int]: fitness value [number of conflicts]
    """
    global count_fitness_evaluations
    global fitness_eval_to_1st_solution
    global fitness_eval_to_last_solution
    global solutions
    
    count_errors = 0
    for i in range(1, NUMBER_OF_QUEENS):
        for j in range(0, i):
            dist = abs(i - j)
            diff = abs(individual[i] - individual[j])
            if dist == diff:
                count_errors += 1
    
    # calculate experiment statistics
    count_fitness_evaluations += 1
    if count_errors == 0 and individual not in solutions:
        if len(solutions) == 0:
            fitness_eval_to_1st_solution = count_fitness_evaluations
        fitness_eval_to_last_solution = count_fitness_evaluations
        solutions.append(individual)
    
    return [count_errors]


def run_experiment() -> None:
    """Run one experiment for queens problem.
    """
    global count_fitness_evaluations
    global fitness_eval_to_1st_solution
    global fitness_eval_to_last_solution
    global solutions
    
    count_fitness_evaluations = 0
    fitness_eval_to_1st_solution = 0
    fitness_eval_to_last_solution = 0 
    solutions = []
    
    pop = toolbox.population(n=NUMBER_OF_QUEENS)
    
    algorithms.eaMuPlusLambda(pop, toolbox, mu=POPULATION_SIZE, lambda_=7*POPULATION_SIZE, cxpb=CROSSOVER_PROBABILITY, 
                              mutpb=MUTATION_PROBABILITY, ngen=NUMBER_OF_GENERATIONS, verbose=True)


if __name__ == "__main__":
    creator.create("FitnessMin", base.Fitness, weights=[-1.0])
    creator.create("Individual", list, fitness=creator.FitnessMin)

    toolbox = base.Toolbox()
    toolbox.register("indices", random.sample, range(NUMBER_OF_QUEENS), NUMBER_OF_QUEENS)
    toolbox.register("individual", tools.initIterate, creator.Individual,
                    toolbox.indices)
    toolbox.register("population", tools.initRepeat, list, toolbox.individual)
    
    toolbox.register("mate", tools.cxOrdered)
    toolbox.register("mutate", tools.mutShuffleIndexes, indpb=ATTRIBUTE_MUTATION_PROBABILITY)
    toolbox.register("select", tools.selTournament, tournsize=3)
    toolbox.register("evaluate", evaluate)
    
    avg_solutions_number = 0
    avg_count_fitness_evaluations = 0
    avg_fitness_eval_to_1st_solution = 0
    avg_fitness_eval_to_last_solution = 0
    
    for _ in range(NUMBER_OF_RUNS):
        run_experiment()
        avg_solutions_number += len(solutions)
        avg_count_fitness_evaluations += count_fitness_evaluations
        avg_fitness_eval_to_1st_solution += fitness_eval_to_1st_solution
        avg_fitness_eval_to_last_solution += fitness_eval_to_last_solution

    print(f"Avg [{NUMBER_OF_RUNS} runs] Number of founded solutions:", avg_solutions_number / NUMBER_OF_RUNS)
    print(f"Avg [{NUMBER_OF_RUNS} runs] Number of evaluations:", avg_count_fitness_evaluations / NUMBER_OF_RUNS)
    print(f"Avg [{NUMBER_OF_RUNS} runs] Fitness evaluations to 1st solution:", avg_fitness_eval_to_1st_solution / NUMBER_OF_RUNS)
    print(f"Avg [{NUMBER_OF_RUNS} runs] Fitness evaluations to last solution:", avg_fitness_eval_to_last_solution / NUMBER_OF_RUNS)
