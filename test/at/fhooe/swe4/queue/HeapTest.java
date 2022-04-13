package at.fhooe.swe4.queue;

import org.junit.jupiter.api.BeforeEach;

public class HeapTest extends BaseTest {

  @BeforeEach
  @Override
  public void setUp() {
    que = new Heap<>();
  }
}
