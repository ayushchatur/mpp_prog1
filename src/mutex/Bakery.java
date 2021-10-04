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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Lamport's Bakery lock (simplified)
 * @author Maurice Herlihy
 */
class Bakery implements Lock {
    boolean[] flag;
    int N; 
    int[] label;
    
  public Bakery(int threads) {
  flag = new boolean[threads];
  this.N = threads;
  label = new int[threads];
  
  for(int i=0;i<threads;i++)
  {
      flag[i] = false;label[i]=0;
  }
  }
  
    
  private int get_max(int[]  arr)
  {
      int max = Integer.MIN_VALUE;
      
      for(int i : arr)
      {
          if ( i > max)
              max = i ;
                      
      }
      return max;
  }
  
  public void lock() {
      //get thread id 
      int i = ThreadID.get();
      flag[i] = true;
      
      label[i] = get_max(label) +1;
      
      
      for ( int k =0; k< N ; k ++)
      {
          while( k != i && flag[k] && 
                  ((label[k] < label[i]) || 
                  ((label[i] == label[k]) && (k < i)) )
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
