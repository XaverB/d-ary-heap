package at.fhooe.swe4.queue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Abstract class so we can reuse our test cases.
 * Subclasses must override setUp and initialize their desired PQueue instance.
 */
public abstract class BaseTest {
    protected PQueue<Integer> que;

    @BeforeEach
    public abstract void setUp();

    @AfterEach
    public void tearDown() {
        que = null;
    }

    @Test
    public void testIsEmpty() {
        assertTrue(que.isEmpty());
        que.enqueue(1);
        assertFalse(que.isEmpty());
        que.dequeue();
        assertTrue(que.isEmpty());
    }

    @Test
    public void testPeak() {
        Integer value = que.peek();
        assertNull(value);

        for (int i = 0; i < TestUtil.MANUAL_TEST_COUNT; i++) {
            TestUtil.testPeakUtil(que, i);
        }
    }

    @Test
    public void testPeakRandom() {
        Random random = new Random();
        for (int i = 0; i < TestUtil.RANDOM_TEST_COUNT; i++) {
            int value = random.nextInt();
            TestUtil.testPeakUtil(que, value);
        }
    }

    @Test
    public void testDequeueException() {
        assertThrows(
                NoSuchElementException.class
                , () -> que.dequeue());
    }

    @Test
    public void testEnqueueDequeue() {
        for (int i = 0; i < TestUtil.MANUAL_TEST_COUNT; i++) {
            que.enqueue(i);
        }
        TestUtil.testDequeue(que);

        for (int i = TestUtil.MANUAL_TEST_COUNT; i > 0; i--) {
            que.enqueue(i);
        }
        TestUtil.testDequeue(que);
    }

    @Test
    public void testEnqueueDequeueRandom() {
        Random random = new Random();
        for (int i = 0; i < TestUtil.RANDOM_TEST_COUNT; i++) {
            que.enqueue(random.nextInt());
        }
        TestUtil.testDequeue(que);
    }
}
