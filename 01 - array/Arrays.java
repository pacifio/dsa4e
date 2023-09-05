import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

abstract class MyArray {
    abstract Object[] show();

    abstract void push(Object item);

    abstract void iterate(Consumer<Object> callback);

    abstract HashMap<String, Object[]> group();

    abstract Object[] sort();

    abstract Object index(int index);

    abstract int find_index(Object item);

    abstract Object[] remove(Object item);

    abstract Object[] remove_at(int index);

    abstract String[] map_type();

    abstract boolean is_int_list();

    abstract int sum();
}

class Arrays extends MyArray {
    private Object[] data = {};

    public Arrays(Object[] init) {
        this.data = init;
    }

    public static void main(String[] args) {
        Object[] data = {1, 23};
        Arrays arrays = new Arrays(data);

        arrays.push(2);
        arrays.push(48);
        arrays.printList(arrays.sort());
        arrays.remove(2);
        arrays.printList(arrays.show());
    }

    public void printList(Object[] items) {
        for (Object i : items) {
            System.out.println(i);
        }
    }

    @Override
    public Object[] show() {
        return this.data;
    }

    @Override
    void push(Object item) {
        Object[] newArr = new Object[this.data.length + 1];
        for (int i = 0; i < this.data.length; i++) {
            newArr[i] = this.data[i];
        }

        newArr[this.data.length] = item;
        data = newArr;
    }

    @Override
    void iterate(Consumer<Object> callback) {
        if (callback != null) {
            for (Object x : this.data) {
                callback.accept(x);
            }
        }
    }

    @Override
    HashMap<String, Object[]> group() {
        HashMap<String, Object[]> dict = new HashMap<String, Object[]>();
        for (Object i : this.data) {
            final String key = i.getClass().toString();
            if (dict.containsKey(key)) {
                Object[] existingG = dict.get(key);
                Object[] newArr = new Object[existingG.length + 1];
                System.arraycopy(existingG, 0, newArr, 0, existingG.length);
                newArr[existingG.length] = i;
                dict.put(key, newArr);
            } else {
                Object[] newG = {i};
                dict.put(key, newG);
            }
        }

        return dict;
    }

    public void printGroup(HashMap<String, Object[]> input) {
        for (Map.Entry<String, Object[]> entry : input.entrySet()) {
            String key = entry.getKey();
            Object[] value = entry.getValue();
            System.out.println(key);
            for (Object i : value) {
                System.out.println(i);
            }
        }
    }

    @Override
    Object[] sort() {
        return this._sort(this.data);
    }

    private Object[] _sort(Object[] items) {
        final Object[] e = {};

        if (!this.is_int_list_internal(items)) {
            return e;
        }

        if (items.length <= 1) {
            return items;
        }

        int pivot = (int) items[(int) items.length / 2];


        List<Integer> left = new ArrayList<>();
        List<Integer> middle = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (Object i : items) {
            final int item = (int) i;
            if (item < pivot) {
                left.add(item);
            } else if (item == pivot) {
                middle.add(item);
            } else if (item > pivot) {
                right.add(item);
            }
        }

        left.addAll(middle);
        left.addAll(right);


        return left.toArray();
    }


    @Override
    Object index(int index) {
        try {
            return this.data[index];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    int find_index(Object item) {
        for (int i = 0; i < this.data.length; i++) {
            if (item == this.data[i]) {
                return i;
            }
        }

        return -1;
    }

    @Override
    Object[] remove(Object item) {
        Object[] newArr = new Object[this.data.length - 1];
        int newIndex = 0;

        for (int i = 0; i < this.data.length; i++) {
            if (item != this.data[i]) {
                newArr[newIndex] = this.data[i];
                newIndex++;
            }
        }

        this.data = newArr;
        return this.data;
    }

    @Override
    Object[] remove_at(int index) {
        if (index > this.data.length || index < 0) {
            return null;
        }

        Object[] newArr = new Object[this.data.length - 1];
        int newIndex = 0;

        for (int i = 0; i < this.data.length; i++) {
            if (index != i) {
                newArr[newIndex] = this.data[i];
                newIndex++;
            }
        }

        this.data = newArr;
        return this.data;
    }

    @Override
    String[] map_type() {
        String[] result = new String[this.data.length];
        for (int i = 0; i < this.data.length; i++) {
            result[i] = this.data[i].getClass().toString();
        }
        return result;
    }

    @Override
    boolean is_int_list() {
        for (Object i : this.data) {
            if (!(i instanceof Integer)) {
                return false;
            }
        }

        return true;
    }

    private boolean is_int_list_internal(Object[] items) {
        for (Object i : items) {
            if (!(i instanceof Integer)) {
                return false;
            }
        }

        return true;
    }

    @Override
    int sum() {
        if (this.is_int_list()) {
            return 0;
        }

        int total = 0;
        for (Object i : this.data) {
            total += (int) i;
        }

        return total;
    }
}