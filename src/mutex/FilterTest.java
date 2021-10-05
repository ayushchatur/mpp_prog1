/*
 * FilterTest.java
 * JUnit based test
 *
 * Created on January 12, 2006, 8:27 PM
 */

package mutex;

import mutex.Filter;
import mutex.ThreadID;
import java.util.concurrent.locks.Lock;
/**
 * Crude & inadequate test of lock class.
 * @author Maurice Herlihy
 */
public class FilterTest {
    // default 4 threads 
  private  int THREADS ;
  private   int COUNT = 107520    ;
  private   int PER_THREAD ;
  Thread[] thread = null ;
  int counter = 0;
  
  Lock instance = null;
  
  public FilterTest(int threads) {
     // Check of non negative since java dont have unsigned
         if(threads > 0)
        { this.THREADS= threads;
            
        thread = new MyThread[this.THREADS];
        this.PER_THREAD = COUNT / this.THREADS;
          instance = new Filter(this.THREADS);
        }
        
  }
  
  public void reset_counter()
  {
      this.counter = 0;
  }
  
  public boolean testParallel() throws Exception {
    ThreadID.reset();
    this.reset_counter();
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
    return (counter == COUNT);
    
  }
  
  class MyThread extends Thread {
    public void run() {
      for (int i = 0; i < PER_THREAD; i++) {
          long timS = System.nanoTime();
        instance.lock();
        try {
          counter = counter + 1;
        } finally {
          instance.unlock();
        }
        long timeF = System.nanoTime();
      System.out.println("time(ns) take by thread id: " + this.getName() + " is: " + (timeF - timS));
      }
    }
  }
}
