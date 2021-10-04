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
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Lamport's Bakery lock (simplified)
 * @author Maurice Herlihy
 */
class Bakery_atmc implements Lock {
    AtomicBoolean[] flag;
    int N; 
    AtomicInteger[] label;
    
  public Bakery_atmc(int threads) {
  flag = new AtomicBoolean[threads];
  this.N = threads;
  label = new AtomicInteger[threads];
  
  for(int i=0;i<threads;i++)
  {
      flag[i] = new AtomicBoolean();label[i]= new AtomicInteger();
  }
  }
  
  private int get_max(AtomicInteger[]  arr)
  {
      int max = Integer.MIN_VALUE;
      
      for(AtomicInteger i : arr)
      {
          if ( i.get() > max)
              max = i.get();
                      
      }
      return max;
  }
  public void lock() {
      //get thread id 
      int i = ThreadID.get();
      flag[i].set(true);
      
//      int p =-1;
//      for ( int j=0;j<N;j++)
//          p = Math.max(p,label[j].get());
      label[i].set( get_max(label) + 1);
      
      
      for ( int k =0; k< N ; k ++)
      {
         while ((k != i) && flag[k].get() && ((label[k].get() < label[i].get()) || ((label[k].get() == label[i].get()) && k < i))) {
                //spin wait
            }
      }
  } 
  public void unlock() {
  
        flag[ThreadID.get()].set(false);
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
