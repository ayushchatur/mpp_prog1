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
  private  int THREADS = 4;
  private final  int COUNT = 1024 * 1024;
  private final  int PER_THREAD = COUNT / THREADS;
  Thread[] thread = new Thread[THREADS];
  int counter = 0;
  
  Lock instance = new Filter(THREADS);
  
  public FilterTest(int threads, boolean our_algo) {
     // Check of non negative since java dont have unsigned
        if(threads > 0)
        {    this.THREADS= threads;
         thread = new MyThread[this.THREADS];
        }
        
    
        // our_algo = true imples custom implementation of lock 
        if( our_algo )
        {
            instance = new Filter(this.THREADS);
        }
        else 
        {
            instance = new FilterSys(this.THREADS);
        }
  }
  

  
  public boolean testParallel() throws Exception {
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
    
    return (counter == COUNT);
    
  }
  
  class MyThread extends Thread {
    public void run() {
      for (int i = 0; i < PER_THREAD; i++) {
        instance.lock();
        try {
          counter = counter + 1;
        } finally {
          instance.unlock();
        }
      }
    }
  }
}
