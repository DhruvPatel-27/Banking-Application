
package coe528.project;

/**
 *
 * 
 */

/*
    OVERVIEW:
    This is a Gold Level class which represents the customer's gold level of the banking system.
    It has methods to get the name of the level and the online purchase fee. This class is immutable.
*/


public class GoldLevel extends Level {
 
     
    @Override
    public int onlinePurchaseFee(){
        //EFFECTS: Returns the fee for the online purchase (10)
        return 10;
    }
    
    @Override
    public String levelName(){
        //EFFECTS: Returns the name of the level (Gold)
        return "Gold";
    }  
    
}
