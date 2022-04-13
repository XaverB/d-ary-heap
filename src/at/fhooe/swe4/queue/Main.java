package at.fhooe.swe4.queue;

import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

public class Main {
  private static final int RUNS = 5;

  public static void main(String args[]) {
    System.out.println("Running performance tests. Output format is: d;size;enqueueAverage;dequeueAverage");
    System.out.println("We will start with d = 3 and increase it <= 20. For each d iteration we will run tests from size 10 to 100_000_0 increasing size *10 each iteration.");
    System.out.println("This may take some take..");

    PrintStream fileStream = null;
    try {
      fileStream = new PrintStream("result.csv");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    System.setOut(fileStream);

    Pair<Double, Double> result = null;

    for(int d = 3; d <= 20; d++) {
      for(int size = 10; size < 100_000_000; size *= 10) {
        result = runPerformanceTest(d, size);
        System.out.println(d + ";" + size + ";" + result.getKey() + ";" + result.getValue());
      }
    }


  }

  private static Pair<Double, Double> runPerformanceTest(int d, int itemCount) {
    PQueue<Integer> que = new DHeapQueue<>(d);
    Random random = new Random();
    ArrayList<Long> runTimesEnqueue = new ArrayList<>();
    ArrayList<Long> runTimesDequeue = new ArrayList<>();

    for (int i = 0; i < RUNS; i++) {
      long start = System.nanoTime();
      for (int j = 0; j < itemCount; j++) {
        que.enqueue(random.nextInt());
      }
      long time = System.nanoTime() - start;
      runTimesEnqueue.add(time);

      start = System.nanoTime();
      while (!que.isEmpty())
        que.dequeue();
      time = System.nanoTime() - start;
      runTimesDequeue.add(time);
    }

    double enqueueAverage = runTimesEnqueue.stream().mapToDouble(x -> x).average().orElse(0.0);
    double dequeueAverage = runTimesDequeue.stream().mapToDouble(x -> x).average().orElse(0.0);

    return new Pair<Double, Double>(enqueueAverage, dequeueAverage);
  }

}
