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
        
           
         BakeryTest i = new BakeryTest();
         try
         {
             
         boolean result = i.testParallel();
             System.out.println("result we got is: " + result);
         }
         catch(Exception ex)
         {
             
         }
    }
    
}
