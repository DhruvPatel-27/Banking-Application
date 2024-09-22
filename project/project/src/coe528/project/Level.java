
package coe528.project;


/* 
    OVERVIEW:
    This is an abstract Level class which represents the customer's level in the banking system.
    It has methods to get the name of the level and the online purchase fee. This class is immutable.
   
*/


public abstract class Level {
    public abstract int onlinePurchaseFee();
    
    public abstract String levelName();
    
    public int getOnlinePurchaseFee() {
        //EFFECTS: Returns the fee for the online purchase
        return onlinePurchaseFee();
    }
    
    public String getLevelName(){
        //EFFECTS: Returns the name of the level
        return levelName(); 
   }
}
