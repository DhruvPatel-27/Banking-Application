
package coe528.project;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/*
   OVERVIEW: 
   This is a Customer class which represents the customer in the Banking System.
   A customer has a username, password, balance and has a role of "Customer". 
   A customer can login, and after deposit, withdrawal, view balance and online purchase. This class is mutable.

  ABSTRACTION FUNCTION:
  AF(c) = a customer, d, such that 
  d.username = c.username
  d.password = c.password
  d.balance  = c.balance
  "Customer" =  c.role

  REP INVARIANT:
  RI(c) = 
  c.username !=  null
  c.password !=  null
  c.balance >= 0
  c.role == "Customer" 

*/


public class Customer extends User {

    private double balance;
    private FileWriter writeCustomerFile;
    private Level customerLevel;
    
   public Customer(String username, String password, String role, double balance){
       //EFFECTS: Creates a new customer object given a username, password, balance, and role
       super(username, password, "Customer");
       this.balance = balance; 
       updateLevel();
   }
   
   
    public void setBalance(double balance){
        //REQUIRES: balance has to be greater than or equal to 0
        //MODIFIES: balance
        //EFFECTS: Sets the balance of the customer
       this.balance = balance;
   }
    
   
   public double getBalance(){
       //EFFECTS: Returns the balance of the customer 
       return this.balance;
   }
   
    public double getBalanceFromFile(String username) {
        //REQUIRES: username
        //EFFECTS: Reads from the customer's file for the given username to find the balance and returns it
        String filename = username + ".txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            reader.readLine(); 
            reader.readLine();
            String storedBalance = reader.readLine();
            if (storedBalance != null) {
                return Double.parseDouble(storedBalance);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the balance.");
        }
        return 0.0;
    }
   
   @Override
    public boolean login(String username, String password) throws IOException{
        //REQUIRES: Username and password cannot be null 
        //EFFECTS: Verifies if the username and password are correct from the customer's text file
         String filename = username + ".txt";
         try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String storedUsername = reader.readLine();
            String storedPassword = reader.readLine();   
         if (storedUsername != null && storedPassword != null){
              if(storedUsername.equals(username) && storedPassword.equals(password)){
                  return true;
                } 
            }
        } catch (FileNotFoundException e){
           System.out.println(e.getMessage());    
          }
         return false;
    }
   
    
   public boolean deposit(double amount){
       //REQUIRES: amount has to be greater than or equal to 0
       //MODIFIES: balance and customerLevel (if required)
       //EFFECTS: Deposits the given amount into the customer's account (text file) and updates the balance and level
       try{
           if (amount > 0){
            balance += amount;
            updateLevel();
            updateFile();
            return true;
            }
       } catch (IllegalArgumentException e){
           System.out.println(e.getMessage());  
       }
       return false;
   }
   
   
    public boolean withdrawal(double amount){
        //REQUIRES: amount has to be  > 0
        //MODIFIES: balance and customerLevel (if required)
       //EFFECTS: Withdrawal the given amount into the customer's account (text file) and updates the balance and level
    try {
        if (balance >= amount && amount > 0) {
            balance -= amount;
            updateLevel();
            updateFile();
            return true;
        }
        } catch (IllegalArgumentException e){
           System.out.println(e.getMessage());     
         }
            
      return false;
   } 
    
    public boolean onlinePurchase(double amount){
       //REQUIRES: amount has to be >= 50
       //MODIFIES: balance and customerLevel (if required)
       //EFFECTS: Makes an online purchase for the given amount, and deducts the (amount + applicable fee) which the fee depends on the customer's balance then updates the balance and levelmmod
       try {
           double fee = customerLevel.getOnlinePurchaseFee();
        if (balance >= amount + fee && amount >= 50) {
            balance -= (amount + fee);
            updateLevel();
            updateFile();
            return true;
        } else {
            return false;
        }
      } catch (Exception e) {
        System.out.println("Error processing online purchase: " + e.getMessage());
        return false;
       }
    }
    
    
   private void updateLevel(){
       //MODIFIES: customerLevel
       //EFFECTS: Updates the customer's level based on their balance 
       if (getBalance() < 10000){
           customerLevel = new SilverLevel();
       } else if (getBalance() >= 10000 && getBalance() < 20000){
           customerLevel = new GoldLevel();
       } else {
           customerLevel = new PlatinumLevel();
       }
   } 
  
   
   public void updateFile(){
        //MODIFIES: Customer info on the file
        //EFFECTS: Updates the customer info on the file and if the file does not exist, it creates a new file 
        String filename = getUsername() + ".txt";
           try {
           FileWriter writeCustomerFile = new FileWriter(filename, false);
           writeCustomerFile.write(getUsername() + "\n");
           writeCustomerFile.write(getPassword() + "\n");
           writeCustomerFile.write(getBalance() + "\n");
           writeCustomerFile.close();
           System.out.println("Customer information updated on file: " + getUsername() + ".txt");
               
       } catch (IOException e) {
           System.out.println("Error: Customer Information is not updated.");
       }  
   }
   
   public String toString(){
        //EFFECTS: Returns a string that contains the username, password, and role.
        //Implements the abstraction function
       return "Username: " + this.username + " , Password: " + this.password + " , Balance: " + this.balance + " , Role: " + this.role; 
   }
   
   public boolean repOk(){
       //EFFECTS: Checks if the rep invariant holds true
       if((this.password != null) && (this.username != null) && (this.role.equals("Customer") && (this.balance >= 0))){
           return true;
       }
       return false;
    } 
  
}
