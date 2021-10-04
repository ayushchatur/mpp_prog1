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
        int typeoftest = 1;
         
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
            typeoftest = Integer.parseInt(args[2]);
            }
            catch (Exception ex)
            {
                System.err.println("Error parsing arguments please provide them correctly defaulting to 1 (algo choice),4 threads ,1 type of test");
            }
        }
           BakeryTest bake = new BakeryTest(Nthreads, true);
           FilterTest filter  = new FilterTest(Nthreads, true);
           boolean result ;
           
          /***         
                1   Bakery via Custom Lock <default:>
                2   Bakery via Java Concurrent Lock
                3   Filter via Custom Lock
                4   Filter via Java Concurrent Lock
          ***/
        switch (algo)
                {
            case 1: 
                // true, implies to use our lock implmentation 
                bake = new BakeryTest(Nthreads, true);
                result = bake.testParallel_custom();
                break;
            case 2: 
                bake = new BakeryTest(Nthreads, false);
                result = bake.testParallel_custom();
                break;
            case 3: 
                                // true, implies to use our lock implmentation 
                filter = new FilterTest(Nthreads, true);
                result = filter.testParallel();
                break;
            case 4: 
                filter = new FilterTest(Nthreads, false);
                result = filter.testParallel();
                break;
            default : 
                bake = new BakeryTest(Nthreads, true);
                result = bake.testParallel_custom();             
        }
            System.out.println("result we got is: " + result);
           }
         catch(Exception ex)
         {
            System.err.println("Error executing: " + ex.getMessage());
         }
   
    }
}
