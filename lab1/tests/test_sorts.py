import numpy as np
from src.bubble_sort import bubble_sort
from src.counting_sort import counting_sort, optimized_counting_sort


def run_experiment(algo, min_int, max_int, arr_size=1000, trials=100):
    for _ in range(trials):
        arr = np.random.randint(min_int, max_int, size=arr_size)
        gt_sort = np.sort(arr)
        calc_sort = algo(arr)
        assert np.array_equal(gt_sort, calc_sort)

def test_positive_int():
    algos = [bubble_sort, counting_sort, optimized_counting_sort]
    for algo in algos:
        run_experiment(algo, 0, 100000)

def test_negative_plus_positive_int():
    algos = [bubble_sort, counting_sort, optimized_counting_sort]
    for algo in algos:
        run_experiment(algo, -100000, 100000)
