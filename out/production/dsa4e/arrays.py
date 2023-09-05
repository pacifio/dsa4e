"""
Simple array implementation of Array in Python
It still uses Python lists to hold data
But you will find some additional helpful functions written from scratch to really understand how arrays work
"""

from typing import List
from collections import defaultdict


class MyArray:
    def __init__(self, *args: List[any]) -> None:
        self.data: List[any] = args

    def push(self, item: any) -> List[any]:
        if item is not None:
            self.data.append(item)
        return self.data

    def iterate(self, callback) -> None:
        if callback is not None:
            [callback(i) for i in self.data]

    def group(self) -> tuple[list]:
        """Groups types to their own seperate tuples based on types"""
        result = defaultdict(list)
        for i in self.data:
            result[type(i)].append(i)
        return tuple(result.values())

    def sort(self) -> List[any]:
        return self._sort(self.data)

    def _sort(self, arr) -> List[any]:
        """Quicksort implementation"""
        if len(arr) <= 1:
            return arr
        p = arr[len(arr) // 2]
        l = [x for x in arr if x < p]
        m = [x for x in arr if x == p]
        r = [x for x in arr if x > p]

        return self._sort(l) + m + self._sort(r)

    def default_sort(self) -> List[any]:
        return sorted(self.data)

    def index(self, index: int) -> any:
        try:
            return self.data[index]
        except IndexError:
            return None

    def find_index(self, item: any) -> int:
        for v, i in enumerate(self.data):
            if v == item:
                return i
        return -1

    def remove(self, item: any) -> bool:
        found_item = self.find(item)
        if found_item is None:
            return False
        self.data.remove(found_item)
        return True

    def remove_at(self, index: int) -> bool:
        try:
            return self.remove(self.data[index])
        except IndexError:
            return False

    def find_where(self, callback: bool) -> tuple[int]:
        r = []
        for i in self.data:
            if callback is not None and callback(i) == True:
                r.append(i)
        return tuple(r)

    def find_index_where(self, callback: bool) -> tuple[any]:
        r = []
        for i, v in enumerate(self.data):
            if callback is not None and callback(v) == True:
                r.append(i)
        return tuple(r)

    def map_type(self) -> List[type]:
        return [type(i) for i in self.data if i is not None]

    def is_int_list(self) -> bool:
        for i in self.data:
            if type(i) is not int:
                return False
        return True

    def sum(self) -> int:
        if self.is_int_list():
            r = 0
            for i in self.data:
                r += i
            return r
        return 0

    def efficient_sum(self) -> int:
        """Adds possible int to the sum"""
        r = 0
        for i in self.data:
            if type(i) is not int:
                continue
            r += i
        return r


def main() -> None:
    arr: MyArray = MyArray(1, 2, 34, 4)
    print(arr.sort())
    print(arr.find_where(lambda x: x > 1))
    print(arr.find_index_where(lambda x: x > 1))


if __name__ == "__main__":
    main()
