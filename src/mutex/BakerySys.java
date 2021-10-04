/*
 * Bakery.java
 *
 * Created on January 21, 2006, 1:20 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 *
 */

package mutex;

import java.util.concurrent.TimeUnit;
import java.math.*;
import java.util.concurrent.ThreadLocalRandom;   
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Lamport's Bakery lock (simplified)
 * @author Maurice Herlihy
 */
class BakerySys implements Lock {
    boolean[] flag;
    int N; 
    int[] label;
    
  public BakerySys(int threads) {
  flag = new boolean[threads];
  this.N = threads;
  label = new int[threads];
  
  for(int i=0;i<threads;i++)
  {
      flag[i] = false;label[i]=0;
  }
  }
  public void lock() {
      //get thread id 
      int i = ThreadID.get();
      flag[i] = true;
      
      int p =-1;
      for ( int j=0;j<N;j++)
          p = Math.max(p,label[j]);
      label[i] = p +1;
      
      
      for ( int k =0; k< N ; k ++)
      {
          while( k != i && flag[k] && 
                  ((label[i] < label[k]) || 
                  ((label[i] == label[k]) && (i < k)) )
                  )
                  {
                      // spin wait
                  }
      }
  } 
  public void unlock() {
  
        flag[ThreadID.get()] = false;
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
