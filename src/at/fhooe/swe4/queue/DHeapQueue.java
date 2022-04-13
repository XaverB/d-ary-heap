package at.fhooe.swe4.queue;

public class DHeapQueue<T extends Comparable<T>> extends BaseHeap<T> {

  private static final int DEFAULT_MAX_SIBLINGS = 3;
  private static int maxSiblings = 3;

  public DHeapQueue() {
    this(DEFAULT_MAX_SIBLINGS);
  }

  public DHeapQueue(int maxSiblings) {
    super();
    this.maxSiblings = maxSiblings;
  }

  private static int parent(int i) {
    return (i - 1) / maxSiblings;
  }

  /**
   * Returns the kth child of node at i.
   *
   * @param i Calling nodes index
   * @param k The kth child you want to get
   * @return computed index of kth child
   */
  private static int kChild(int i, int k) {
    return maxSiblings * i + k;
  }

  @Override
  protected T getParent(int i) {
    return values.get(parent(i));
  }

  @Override
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

  private int largerChild(int i) {
    int indexOfLargerChild = kChild(i, 0);
    for (int k = 1; k <= maxSiblings; k++) {
      if (kChild(i, k) < values.size() &&
              less(values.get(indexOfLargerChild), values.get(kChild(i, k)))) {
        indexOfLargerChild = kChild(i, k);
      }
    }
    return indexOfLargerChild;
  }

  @Override
  protected void downHeap() {
    // we always swap with the BIGGER child
    assert !values.isEmpty();
    int i = 0;
    T x = values.get(0);
    while (kChild(i, 0) < values.size()) { // child available with which we could swap
      int indexOfLargerChild = largerChild(i);
      if (!less(x, values.get(indexOfLargerChild))) // value not less than larger child => stop
        break;
      values.set(i, values.get(indexOfLargerChild)); // else, swap
      i = indexOfLargerChild; // update index, new iteration
    }
    values.set(i, x);
  }
}
