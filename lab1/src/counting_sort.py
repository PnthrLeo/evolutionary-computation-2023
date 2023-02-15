import numpy as np
from numba import jit


def counting_sort(arr: np.array) -> np.array:
    min_num = np.min(arr)
    if min_num < 0:
        arr -= min_num
    
    max_num = np.max(arr)
    counts_arr = np.zeros(max_num + 1)
    
    for i in np.arange(len(arr)):
        counts_arr[arr[i]] += 1
    
    pos = 0
    for num in np.arange(len(counts_arr)):
        for i in np.arange(counts_arr[num]):
            arr[pos] = num
            pos += 1
    
    if min_num < 0:
        arr += min_num
    
    return arr


@jit(nopython=True)
def jit_counting_sort(arr: np.array) -> np.array:
    min_num = np.min(arr)
    if min_num < 0:
        arr -= min_num
    
    max_num = np.max(arr)
    counts_arr = np.zeros(max_num + 1)
    
    for i in np.arange(len(arr)):
        counts_arr[arr[i]] += 1
    
    pos = 0
    for num in np.arange(len(counts_arr)):
        for i in np.arange(counts_arr[num]):
            arr[pos] = num
            pos += 1
    
    if min_num < 0:
        arr += min_num
    
    return arr


def optimized_counting_sort(arr: np.array) -> np.array:
    min_num = np.min(arr)
    if min_num < 0:
        arr -= min_num
    
    max_num = np.max(arr)
    counts_arr = np.zeros(max_num + 1, dtype=np.uint32)
    
    for i in np.arange(len(arr)):
        counts_arr[arr[i]] += 1
    
    pos = 0
    for num in np.arange(len(counts_arr)):
        arr[pos: pos + counts_arr[num]] = num
        pos += counts_arr[num]
    
    if min_num < 0:
        arr += min_num
    
    return arr


@jit(nopython=True)
def jit_optimized_counting_sort(arr: np.array) -> np.array:
    min_num = np.min(arr)
    if min_num < 0:
        arr -= min_num
    
    max_num = np.max(arr)
    counts_arr = np.zeros(max_num + 1, dtype=np.uint32)
    
    for i in np.arange(len(arr)):
        counts_arr[arr[i]] += 1
    
    pos = 0
    for num in np.arange(len(counts_arr)):
        arr[pos: pos + counts_arr[num]] = num
        pos += counts_arr[num]
    
    if min_num < 0:
        arr += min_num
    
    return arr
