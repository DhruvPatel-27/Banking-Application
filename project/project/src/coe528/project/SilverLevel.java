
package coe528.project;

/**
 *
 * 
 */

/*
    OVERVIEW:
    This is a Silver Level class which represents the customer's silver level of the banking system.
    It has methods to get the name of the level and the online purchase fee. This class is immutable.
   
*/


public class SilverLevel extends Level {
    
    @Override
    public int onlinePurchaseFee(){
        //EFFECTS: Returns the fee for the online purchase (20)
        return 20;
    }
    
    @Override
    public String levelName(){
        //EFFECTS: Returns the name of the level (Silver)
        return "Silver";
    }
    
    
}
