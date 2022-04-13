package at.fhooe.swe4.queue;

import java.util.Random;

public class Heap<T extends Comparable<T>> extends BaseHeap<T> {

    public Heap() {
        super();
    }

    @Override
    protected T getParent(int i) {
        return values.get(parent(i));
    }

    protected void upHeap() {
        // always swap with parent if we are bigger
        int i = values.size() - 1;
        T x = values.get(i);
        while (i != 0 && less(getParent(i), x)) {
            values.set(i, getParent(i)); // values[i] = values[parent(i)]
            i = parent(i);
        }
        values.set(i, x);
    }

    protected void downHeap() {
        // we always swap with the BIGGER child
        assert !values.isEmpty();
        int i = 0;
        T x = values.get(0);
        while (left(i) < values.size()) { // child available with which we could swap
            int indexOfLargerChild = largerChild(i);
            if (!less(x, values.get(indexOfLargerChild))) // value not less than larger child => stop
                break;
            values.set(i, values.get(indexOfLargerChild)); // else, swap
            i = indexOfLargerChild; // update index, new iteration
        }
        values.set(i, x);
    }

    protected static int parent(int i) {
        return (i - 1) / 2; // (i+1)/2-1
    }

    private static int left(int i) {
        return i * 2 + 1; // (i+1)*2-1
    }

    private static int right(int i) {
        return i * 2 + 2; // (i+1)*2+1-1
    }

    private int largerChild(int i) {
        int indexOfLargerChild = left(i);
        if (right(i) < values.size() && // there is a right child
                less(values.get(indexOfLargerChild), values.get(right(i)))) {
            indexOfLargerChild = right(i);
        }
        return indexOfLargerChild;
    }

    public static void main(String[] args) {
        Heap<Integer> h = new Heap<>();
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            h.enqueue(r.nextInt(100));
        }
        while (!h.isEmpty()) {
            System.out.println(h.dequeue());
        }
    }
}
