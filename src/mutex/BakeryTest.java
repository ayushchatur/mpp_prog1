/*
 * BakeryTest.java
 * JUnit based test
 *
 * Created on January 12, 2006, 8:27 PM
 */

package mutex;

import mutex.Bakery;
import mutex.ThreadID;
import java.util.concurrent.locks.Lock;
/**
 * Crude & inadequate test of lock class.
 * @author Maurice Herlihy
 */
public class BakeryTest{
    // default 4 threads 
  private  int THREADS = 4;
  private   int COUNT = 1024 *1024;
  private   int PER_THREAD = COUNT / THREADS;
  Thread[] thread = null;
  int counter = 0;
  private int algo = 0;
  
  Lock instance ;
    BakeryTest(int threads, boolean our_algo)
    {
        // Check of non negative since java dont have unsigned
        if(threads > 0)
        { this.THREADS= threads;
            
        thread = new MyThread[this.THREADS];
        this.PER_THREAD = COUNT / this.THREADS;
        }
    
        // our_algo = true imples custom implementation of lock 
        if( our_algo )
        {
            instance = new Bakery(this.THREADS);
        }
        else 
        {
            instance = new BakerySys(this.THREADS);
        }
    }
    
  
  public boolean testParallel_custom() throws Exception {
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
  
  public void reset_counter() 
  {
      this.counter = 0;
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
//          System.out.println("time(ns) take by thread id: " + this.getName() + " is: " + (timeF - timS));
      }
    }
  }
}
