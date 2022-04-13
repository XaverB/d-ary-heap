package at.fhooe.swe4.queue;

import at.fhooe.swe4.queue.Heap;
import at.fhooe.swe4.queue.PQueue;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public abstract class BaseHeap<T extends Comparable<T>> implements PQueue<T> {
  protected List<T> values;

  public BaseHeap() {
    values = new ArrayList<>();
  }

  protected boolean less(T a, T b) {
    return a.compareTo(b) < 0;
  }

  public T peek() {
    return values.isEmpty() ? null : values.get(0);
  }

  public boolean isEmpty() {
    return values.isEmpty();
  }

  public void enqueue(T x) {
    assert isHeap();
    values.add(x);
    upHeap();
    assert isHeap();
  }

  public T dequeue() {
    assert isHeap();
    if (values.isEmpty())
      throw new NoSuchElementException("cannot dequeue from empty queue");

    T top = values.get(0);
    int last = values.size() - 1;
    values.set(0, values.get(last));
    values.remove(last);
    if (!values.isEmpty()) downHeap();
    assert isHeap();
    return top;
  }

  protected abstract T getParent(int i);

  protected boolean isHeap() {
    // TODO fix for DHeapQueue
    int i = 1;
    while (i < values.size() &&
            !less(getParent(i), values.get(i))) { // while parent is greater
      i++;
    }
    return i >= values.size(); // all elements match our condition
  }

  protected abstract void upHeap();

  protected abstract void downHeap();

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("heap = [");
    for (int i = 0; i < values.size(); i++) {
      if (i > 0) sb.append(", ");
      sb.append(values.get(i));
    }
    sb.append("]");
    return sb.toString();
  }
}
