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

        int iterations = 10;
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
           RunClass bake = null;
           FilterTest filter  = null;
           boolean result ;
           
          /***         
                1   Bakery via Custom Lock <default:>
                2   Filter Custom Lock
                3   Re-entrant Lock 
                4   Bakery via non atomic variables
          ***/
          
        System.out.format("Initating choice : %d with %d threads and %d iterations\n", algo,Nthreads,iterations);
        
        switch (algo)
                {
            
          
            case 1: 
                // true, implies to use our lock implmentation 
                bake = new RunClass(Nthreads, true);
                System.out.println("Running bakery algo with custom lock");
                for ( int j = 0; j < iterations ; j++)
                {
                    result = bake.testParallel_custom();
                    if (result) success++; 
                    System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result);
                    bake.reset_counter();
                }
                break;
            case 2: 
                 System.out.println("Running Filter algo with custom lock");
                filter = new FilterTest(Nthreads);
                 for ( int j = 0; j < iterations ; j++)
                {result = filter.testParallel();
                if (result) success++;
                System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result);
                filter.reset_counter();
                }

                break;
            case 3: 
                               
                System.out.println("Running Lock implementation with reentrant lock");

                bake = new RunClass(Nthreads);
                 for ( int j = 0; j < iterations ; j++)
                {result = bake.testParallel_custom(); if (result) success++; System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result); filter.reset_counter();}

                break;
            case 4: 
                 System.out.println("Running bakery algo with Native int[]");

                bake = new RunClass(Nthreads, false);
                 for ( int j = 0; j < iterations ; j++)
                {result = bake.testParallel_custom(); 
                if (result) success++; System.out.println("~~~~~~~~~~~~~~~~iteration: " + j + " result correctness: " + result);bake.reset_counter();}
                break;
                
            default : 
                bake = new RunClass(Nthreads, true);
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
