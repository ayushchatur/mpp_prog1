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

class FilterSys implements Lock {
  
    int N;
    int[] flags;
    int[] victim;
    
    public FilterSys(int threads ) {
      this.N = threads;
      this.flags = new int[N];
      this.victim = new int[N];
        for (int i = 0; i < N; i++) {
            this.victim[i] = 0;
        }
      
  }
  public void lock() {
   int id = ThreadID.get();
      for (int i = 1  ; i < N; i++) {
          
          this.flags[id] = i;
          this.victim[i]  =  id;
          
      }
   
  }
  // Is there another thread at the same or higher level?
 
  public void unlock() {
      int id = ThreadID.get();
      
      this.victim[id] = 0;

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


