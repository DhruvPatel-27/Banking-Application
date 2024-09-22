
package coe528.project;

/**
 *
 * 
 */

/*
    OVERVIEW:
    This is a Platinum Level class which represents the customer's platinum level of the banking system.
    It has methods to get the name of the level and the online purchase fee. This class is immutable.
*/


public class PlatinumLevel extends Level {
    
     
    @Override
    public int onlinePurchaseFee(){
        //EFFECTS: Returns the fee for the online purchase (0)
        return 0;
    }
    
    @Override
    public String levelName(){
        //EFFECTS: Returns the name of the level (Platinum)
        return "Platinum";
    }  
    
}
