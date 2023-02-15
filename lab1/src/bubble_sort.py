import numpy as np
from numba import jit


def bubble_sort(arr: np.array) -> np.array:
    for i in range(len(arr)):
        is_swapped = False
    
        for j in range(1, len(arr) - i):
            if arr[j - 1] > arr[j]:
                temp = arr[j - 1]
                arr[j - 1] = arr[j]
                arr[j] = temp

                is_swapped = True

        if not is_swapped:
            break

    return arr


@jit(nopython=True)
def jit_bubble_sort(arr: np.array) -> np.array:
    for i in range(len(arr)):
        is_swapped = False
    
        for j in range(1, len(arr) - i):
            if arr[j - 1] > arr[j]:
                temp = arr[j - 1]
                arr[j - 1] = arr[j]
                arr[j] = temp

                is_swapped = True

        if not is_swapped:
            break

    return arr
