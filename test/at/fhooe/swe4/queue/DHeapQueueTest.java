package at.fhooe.swe4.queue;

import org.junit.jupiter.api.BeforeEach;

public class DHeapQueueTest extends BaseTest {

  @BeforeEach
  @Override
  public void setUp() {
    que = new DHeapQueue<>();
  }
}
