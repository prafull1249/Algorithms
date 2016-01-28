import sys

INFINITY = sys.maxint

__author__ = 'Prafull'
import sys
import numpy as np

INFINITY = sys.maxint
flag = False


def build_cost(str, limit):
    word_length = str.__len__()
    cost = np.zeros((word_length + 1, word_length + 1))

    for i in range(1, word_length + 1):
        sum_char = 0
        skip = False
        for j in range(i, word_length + 1):
            if skip is False:
                sum_char = sum_char + str[j - 1].__len__()
                total_char = j - i + sum_char
                if total_char > limit:
                    cost[i][j] = INFINITY
                    skip = True
                elif j == word_length and total_char <= limit:
                    cost[i][j] = 0
                else:
                    cost[i][j] = (limit - total_char) ** 3
            else:
                cost[i][j] = INFINITY
    return cost


def build_text(str_text, cost, parent):
    j = parent[-1] - 1
    k = parent.__len__() - 1
    h = j + 1
    text = []
    text.append(str_text[int(h - 1):k])
    while (j >= 1):
        text.append(str_text[int(parent[j] - 1):int(j)])
        j = parent[j] - 1
    text_final = ""
    for i in range(text.__len__() - 1, -1, -1):
        text_final = text_final + " ".join(str(j) for j in text[i])
        text_final = text_final + "\n"
    text_final = text_final[:-1]
    return text_final, cost


def compute_optimal_cost(cost, str):
    opt_cost = np.zeros((str.__len__() + 1))
    parent = np.zeros((str.__len__() + 1))
    for j in range(1, str.__len__() + 1):
        min = INFINITY
        for i in range(1, j + 1):
            cur_val = opt_cost[i - 1] + cost[i][j]
            if min > cur_val:
                index = i
                min = cur_val
        opt_cost[j] = min
        parent[j] = index
    return opt_cost, parent


def print_neatly(words, m):
    """ Print text neatly.

    Parameters
    ----------
    words: list of str
        Each string in the list is a word from the file.
    M: int
        The max number of characters per line including spaces

    Returns
    -------
    cost: number
        The optimal value as described in the textbook.
    text: str
        The entire text as one string with newline characters.
        It should not end with a blank line.

    Details
    -------
        Look at print_neatly_test for some code to test the solution.
    >>> str = "kim is so brave"
    >>> print_neatly(str,6)
    (array([   0.,  125.,   27.,    1.,   54.,   28.,    2.,   55.,   29.,
              3.,   56.,   30.,    4.,   57.,   31.,    4.]), 'k i m\\n  i s\\n  s o\\n  b r\\na v e')
    """
    cost = build_cost(words, m)
    opt_cost, parent = compute_optimal_cost(cost, words)
    text, final_cost = build_text(words, opt_cost, parent)
    return final_cost, text
