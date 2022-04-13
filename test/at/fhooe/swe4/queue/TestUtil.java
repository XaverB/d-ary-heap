package at.fhooe.swe4.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Util class for testing objects which implemented PQueue
 */
class TestUtil {
  public static final int RANDOM_TEST_COUNT = 1000;
  public static final int MANUAL_TEST_COUNT = 10;

  /**
   * Util method to be used in Test methods.
   * Dequeues until de que is empty and asserts that previous elements are bigger or equal than current dequeued.
   */
  static void testDequeue(PQueue<Integer> que) {
    int lastValue = Integer.MAX_VALUE;
    while (!que.isEmpty()) {
       int currentValue = que.dequeue();
      assertTrue(currentValue <= lastValue);
      lastValue = currentValue;
    }
  }

  /**
   * Util method to be used in Test methods.
   * Peeks until de que is empty and asserts that previous elements are bigger or equal than current dequeued.
   * @param value
   */
   static void testPeakUtil(PQueue<Integer> que, int value) {
    que.enqueue(value);
    assertEquals(value, que.peek());
    que.dequeue();
  }
}
