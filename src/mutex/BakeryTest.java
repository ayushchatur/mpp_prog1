/*
 * BakeryTest.java
 * JUnit based test
 *
 * Created on January 12, 2006, 8:27 PM
 */

package mutex;

import mutex.Bakery;
import mutex.ThreadID;

/**
 * Crude & inadequate test of lock class.
 * @author Maurice Herlihy
 */
public class BakeryTest {
  private final static int THREADS = 16;
  private final static int COUNT = 1024;
  private final static int PER_THREAD = COUNT / THREADS;
  Thread[] thread = new MyThread[THREADS];
  int counter = 0;
  
 Bakery instance = new Bakery(THREADS);
    
    
  
  public boolean testParallel() throws Exception {
    ThreadID.reset();
    for (int i = 0; i < THREADS; i++) {
      thread[i] = new MyThread();
      StringBuilder s = new StringBuilder().append(i);
      thread[i].setName(s.toString());
    }
    for (int i = 0; i < THREADS; i++) {
      thread[i].start();
    }
    for (int i = 0; i < THREADS; i++) {
      thread[i].join();
    }
      System.out.println(counter);
//    assertEquals(counter, COUNT);
      
    return (counter == COUNT);
      
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
          System.out.println("time take by thread id: " + this.getName() + " is: " + (timeF - timS));
      }
    }
  }
}
