"""
Simple LinkedList implemented in python
"""
from __future__ import annotations


class Node:
    def __init__(self, data: int) -> None:
        self.data = data
        self.next: Node = None


class LinkedList:
    def __init__(self) -> None:
        self.head: Node = None

    def append(self, item: int) -> None:
        n = Node(data=item)
        if self.head is None:
            self.head = n
            return
        current: Node = self.head
        while current.next is not None:
            current = current.next
        current.next = n

    def display(self) -> None:
        current = self.head
        while current is not None:
            end = ' -> ' if current.next is not None else ''
            print(current.data, end=end)
            current = current.next
        print("")

    def len(self) -> int:
        counter = 0
        current = self.head
        while current is not None:
            counter += 1
            current = current.next
        return counter

    def clear(self) -> None:
        self.head = None

    def find(self, index: int) -> int:
        counter = 0
        current: Node = self.head
        while current is not None:
            if index == counter:
                return current.data

            current = current.next
            counter += 1

        return None

    def index(self, item: int) -> int:
        fi = -1
        counter = 0
        current = self.head
        while current is not None:
            if current.data == item:
                fi = counter
                break
            counter += 1
            current = current.next
        return fi

    def insert(self, index: int, item: int) -> None:
        if index < 0:
            return
        if index > self.len():
            return
        n = Node(item)
        if index == 0:
            n.next = self.head
            self.head = n
            return
        counter = 0
        current: Node = self.head
        while current is not None:
            if counter == index-1:

                n.next = current.next
                current.next = n
                break

            counter += 1
            current = current.next

    def pop(self) -> None:
        current: Node = self.head
        current_len: int = self.len()
        counter: int = 0

        while current is not None and counter < current_len - 2:
            current = current.next
            counter += 1

        current.next = None

    def remove_at(self, index: int) -> None:
        current: Node = self.head
        current_len: int = self.len()
        counter: int = 0

        if index < 0:
            return

        if index > current_len:
            return

        while current is not None and counter < index - 1:
            current = current.next
            counter += 1

        current.next = current.next.next

    def sort(self) -> None:
        r = self._sort(self)
        self.head = r.head

    def _merge(self, *args: list[LinkedList]) -> LinkedList:
        result: LinkedList = LinkedList()
        for i in args:
            current: Node = i.head
            while current is not None:
                result.append(current.data)
                current = current.next

        return result

    def _sort(self, ll: LinkedList):
        if ll.len() <= 1:
            return ll

        current: Node = ll.head
        current_len: int = ll.len()
        p = ll.find(current_len // 2)
        lll: LinkedList = LinkedList()
        mll: LinkedList = LinkedList()
        rll: LinkedList = LinkedList()

        while current is not None:
            val = current.data
            if val < p:
                lll.append(val)
            elif val == p:
                mll.append(val)
            elif val > p:
                rll.append(val)

            current = current.next

        return self._merge(self._sort(lll), mll, self._sort(rll))


def main() -> None:
    ll: LinkedList = LinkedList()
    ll.append(1)
    ll.append(2)
    ll.append(4)
    ll.append(3)
    ll.insert(2, 40)
    ll.append(30)
    ll.display()
    ll.pop()
    ll.display()
    ll.remove_at(3)
    ll.display()
    ll.append(2)
    ll.append(12)

    ll.display()
    ll.sort()
    ll.display()


if __name__ == "__main__":
    main()
