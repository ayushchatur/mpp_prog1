/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutex;

import mutex.*;

/**
 *
 * @author ayush
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
        int algo = 1 ;
        int Nthreads = 4;

        int iterations = 1;
        int success=0;
         
        if (args.length < 3)
        {
            System.err.println("Error parsing arguments please provide them correctly defaulting to 1 (algo choice),4 threads ,1 type of test");
            return;
        }
        else
        {
            try
            {
            algo = Integer.parseInt(args[0]);
            Nthreads = Integer.parseInt(args[1]);
            iterations = Integer.parseInt(args[2]);
            }
            catch (Exception ex)
            {
                System.err.println("Error parsing arguments please provide them correctly defaulting to 1 (algo choice),4 threads ,1 type of test");
            }
        }
           BakeryTest bake = null;
           FilterTest filter  = null;
           boolean result ;
           
          /***         
                1   Bakery via Custom Lock <default:>
                2   Bakery via Java Concurrent Lock
                3   Filter via Custom Lock
                4   Filter via Java Concurrent Lock
          ***/
          
        System.out.format("Initating choice : %d with %d threads and %d iterations\n", algo,Nthreads,iterations);
        
        switch (algo)
                {
            
          
            case 1: 
                // true, implies to use our lock implmentation 
                bake = new BakeryTest(Nthreads, true);
                for ( int j = 0; j < iterations ; j++)
                {result = bake.testParallel_custom(); if (result) success++; System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result); bake.reset_counter();}
                break;
            case 2: 
                bake = new BakeryTest(Nthreads, false);
                 for ( int j = 0; j < iterations ; j++)
                {result = bake.testParallel_custom(); if (result) success++; System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result);}
                break;
            case 3: 
                                // true, implies to use our lock implmentation 
                filter = new FilterTest(Nthreads, true);
                 for ( int j = 0; j < iterations ; j++)
                {result = filter.testParallel(); if (result) success++; System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result); filter.reset_counter();}
//                result = filter.testParallel();
                break;
            case 4: 
                filter = new FilterTest(Nthreads, false);
                 for ( int j = 0; j < iterations ; j++)
                {result = filter.testParallel(); if (result) success++; System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result);}

                break;
            default : 
                bake = new BakeryTest(Nthreads, true);
                 for ( int j = 0; j < iterations ; j++)
                    {result = bake.testParallel_custom(); if (result) success++;System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result);}           
                }
            System.out.println("Passed " + success +" iterations out of: " + iterations);
         }  
         catch(Exception ex)
         {
            System.err.println("Error executing: " + ex.getMessage());
         }
   
    }
}
