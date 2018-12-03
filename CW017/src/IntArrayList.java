import java.util.Arrays;

public class IntArrayList implements Intlist {
    private int[] elements = new int[10];
    private static final int DEFAULT_CAPACITY = 10;
    private static final double EXTENSION_COEF = 1.5D;
    private int count = 0;

    public IntArrayList() {
    }

    public void add(int element) {
        if (this.count >= this.elements.length) {
            this.grow();
        }

        this.elements[this.count++] = element;
    }

    public void add(int element, int position) {
        int from = position;
        if (position >= this.count) {
            from = this.size();
        }

        int[] lastElements = Arrays.copyOfRange(this.elements, from, this.count);

        while (position > this.elements.length) {
            this.grow();
        }

        this.count = position;
        this.add(element);

        for (int i = 0; i < lastElements.length; ++i) {
            this.add(lastElements[i]);
        }

    }

    private void grow() {
        int oldCapacity = this.elements.length;
        int newCapacity = (int) ((double) oldCapacity * 1.5D);
        this.elements = Arrays.copyOf(this.elements, newCapacity);
    }

    public int get(int index) {
        if (index < this.count && index >= 0) {
            return this.elements[index];
        } else {
            throw new IllegalArgumentException("Index invalid");
        }
    }

    public int size() {
        return this.count;
    }

    public boolean contains(int value) {
        boolean isFind = false;

        for (int i = 0; i < this.count; ++i) {
            if (this.get(i) == value) {
                isFind = true;
            }
        }

        return isFind;
    }

    public void remove(int index) {
        int[] lastElements = Arrays.copyOfRange(this.elements, index + 1, this.count);
        this.elements = Arrays.copyOfRange(this.elements, 0, this.size() - 1);
        this.count = index;

        for (int i = 0; i < lastElements.length; ++i) {
            this.add(lastElements[i]);
        }

    }

    public void sort() {
        for (int i = 0; i < this.size(); ++i) {
            int min = this.elements[i];

            for (int j = 0; j < this.size(); ++j) {
                if (min > this.elements[j]) {
                    int a = this.elements[i];
                    this.elements[i] = this.elements[j];
                    this.elements[j] = a;
                    min = a;
                }
            }
        }

    }

    public int[] toArray() {
        return Arrays.copyOf(this.elements, this.size());
    }

    public void addAll(Intlist list) {
        for (int i = 0; i < list.size(); ++i) {
            this.add(list.get(i));
        }

    }

    public void addAll(Intlist list, int index) {
        for (int i = 0; i < list.size(); ++i) {
            this.add(list.get(i), index + i);
        }

    }
}