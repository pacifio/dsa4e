class Node {
    public int data;
    public Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }

    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

class SingleLinkedList {
    public Node head = null;

    public void append(int data) {
        final Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
            return;
        }
        Node current = this.head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = node;
    }

    public void display() {
        Node current = this.head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public int len() {
        int counter = 0;
        Node current = this.head;
        while (current != null) {
            counter++;
            current = current.next;
        }

        return counter;
    }

    public void clear() {
        this.head = null;
    }

    public int find(int index) {
        int counter = 0;
        Node current = this.head;

        while (current != null) {
            if (index == counter) {
                return current.data;
            }

            current = current.next;
            counter++;
        }

        return -1;
    }

    public int index(int item) {
        int counter = 0;
        Node current = this.head;

        while (current != null) {
            if (current.data == item) {
                return counter;
            }

            counter += 1;
            current = current.next;
        }

        return -1;
    }

    public void insert(int index, int item) {
        if (index < 0) {
            return;
        }

        if (index > this.len()) {
            return;
        }

        Node n = new Node(item);
        if (index == 0) {
            n.next = this.head;
            this.head = n;
            return;
        }

        int counter = 0;
        Node current = this.head;
        while (current != null) {
            if (counter == index - 1) {
                n.next = current.next;
                current.next = n;
                break;
            }
            counter++;
            current = current.next;
        }
    }

    public void pop() {
        Node current = this.head;
        int counter = 0;
        while (current != null && counter < this.len() - 2) {
            current = current.next;
            counter++;
        }
        if (current != null) {
            current.next = null;
        }
    }

    public void remove_at(int index) {
        if (index < 0 || index > this.len()) {
            return;
        }

        Node current = this.head;
        int counter = 0;

        while (current != null && counter < index - 1) {
            counter++;
            current = current.next;
        }

        if (current != null) {
            current.next = current.next.next;
        }
    }

    public void sort() {
        this.head = this._sort(this).head;
    }

    private SingleLinkedList _merge(SingleLinkedList[] linkedLists) {
        SingleLinkedList result = new SingleLinkedList();

        for (SingleLinkedList ll : linkedLists) {
            Node current = ll.head;
            while (current != null) {
                result.append(current.data);
                current = current.next;
            }
        }

        return result;
    }

    private SingleLinkedList _sort(SingleLinkedList ll) {
        if (ll.len() <= 1) {
            return ll;
        }

        Node current = ll.head;
        int current_len = ll.len();
        int pivot = ll.find((int) current_len / 2);
        SingleLinkedList lll = new SingleLinkedList();
        SingleLinkedList mll = new SingleLinkedList();
        SingleLinkedList rll = new SingleLinkedList();

        while (current != null) {
            int value = current.data;

            if (value < pivot) {
                lll.append(value);
            } else if (value == pivot) {
                mll.append(value);
            } else if (value > pivot) {
                rll.append(value);
            }

            current = current.next;
        }


        SingleLinkedList[] merger = {lll, mll, rll};
        return this._merge(merger);
    }

    public static void main(String[] args) {
        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.append(1);
        linkedList.append(4);
        linkedList.append(2);
        linkedList.display();
        linkedList.sort();
        linkedList.display();
    }
}