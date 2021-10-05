
/*
 * Filter.java
 *
 * Created on January 21, 2006, 9:35 AM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

package mutex;

/**
 * Peterson lock for multiple threads
 * @author Maurice Herlihy
 */
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

class Filter implements Lock {
  
    int N;
    volatile AtomicInteger[] levels;
     volatile AtomicInteger[] victim;
    
    public Filter(int threads ) {
      this.N = threads;
      this.levels = new AtomicInteger[N];
      this.victim = new AtomicInteger[N];
        for (int i = 0; i < N; i++) {
            this.victim[i] = new AtomicInteger();
            this.levels[i] = new AtomicInteger();
        }
      
  }
  public void lock() {
   int id = ThreadID.get();
  for (int i = 1; i < N; i++) {
        levels[id].set(i);
        victim[i].set(id);

            for (int k = 0; k < N; k++) {
                while ( (k != id) && ((levels[k].get() >= i) && (victim[i].get() == id))) {                
                  // spin wait
                }
            
        }
    }
   
  }
  // Is there another thread at the same or higher level?
 
  public void unlock() {
      int id = ThreadID.get();
      
      this.levels[id].set(0);

  }
  
  // Any class implementing Lock must provide these methods
  public Condition newCondition() {
    throw new java.lang.UnsupportedOperationException();
  }
  public boolean tryLock(long time,
      TimeUnit unit)
      throws InterruptedException {
    throw new java.lang.UnsupportedOperationException();
  }
  public boolean tryLock() {
    throw new java.lang.UnsupportedOperationException();
  }
  public void lockInterruptibly() throws InterruptedException {
    throw new java.lang.UnsupportedOperationException();
  }
  
}
