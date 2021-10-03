/*
 * BakeryTest.java
 * JUnit based test
 *
 * Created on January 12, 2006, 8:27 PM
 */

package mutex;

import junit.framework.*;

/**
 * Crude & inadequate test of lock class.
 * @author Maurice Herlihy
 */
public class BakeryTest extends TestCase {
  private final static int THREADS = 16;
  private final static int COUNT = 1024;
  private final static int PER_THREAD = COUNT / THREADS;
  Thread[] thread = new Thread[THREADS];
  int counter = 0;
  
  Bakery instance = new Bakery(THREADS);
  
  public BakeryTest(String testName) {
    super(testName);
  }
  
  public static Test suite() {
    TestSuite suite = new TestSuite(BakeryTest.class);
    
    return suite;
  }
  
  public void testParallel() throws Exception {
    ThreadID.reset();
    for (int i = 0; i < THREADS; i++) {
      thread[i] = new MyThread();
    }
    for (int i = 0; i < THREADS; i++) {
      thread[i].start();
    }
    for (int i = 0; i < THREADS; i++) {
      thread[i].join();
    }
      System.out.println(counter);
    assertEquals(counter, COUNT);
  }
  
  class MyThread extends Thread {
    public void run() {
      for (int i = 0; i < PER_THREAD; i++) {
        long timS = System.nanoTime();
          instance.lock();
        
        try {
          counter = counter + 1;
        }
        finally {
          instance.unlock();
        }
        long timeF = System.nanoTime();
          System.out.println("time take by thread id: "  + "is: " + (timeF - timS));
      }
    }
  }
}
