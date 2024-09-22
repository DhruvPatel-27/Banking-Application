
package coe528.project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 *
 * 
 */

/*
   OVERVIEW: 
   This is a Manager class which represents the manager in the Banking System.
   A manager has a username, password and has a role of "Manager". 
   A manager can login, and after add or delete customers

   ABSTRACTION FUNCTION:
   AF(c) = a manager, m, such that 
   m.username =  c.username
   m.password =  c.password
   "Manager" =  c.role

  REP INVARIANT:
  RI(c) = 
  c.username !=  null
  c.password !=  null
  c.role == "Manager" 

*/

public class Manager extends User {
    
    public Manager(String username, String password, String role){
        //EFFECTS: Creates a new manager object given a username, password, and role
        super(username, password, "Manager");
    }
    
    @Override
    public boolean login(String username, String password) throws IOException{
        //REQUIRES: Username and password cannot be null
        //EFFECTS: Verifies if the username and password are correct
        return this.username.equals(username) && this.password.equals(password);
    }
    
     public void saveToFile(Customer customer) throws IOException{
        //EFFECTS: Creates a text file with the customer's information (username, password, and balance) and saves the info and also check if a file exists for the username
        String filename = customer.getUsername() + ".txt";
        File file = new File(filename);
        
         if (file.exists()) {
             throw new IOException("A file with the name " + customer.getUsername() + " already exists.");
            }
       try {
           FileWriter writeCustomerFile = new FileWriter(customer.getUsername() + ".txt");
           writeCustomerFile.write(customer.getUsername() + "\n");
           writeCustomerFile.write(customer.getPassword() + "\n");
           writeCustomerFile.write(customer.getBalance() + "\n");
           writeCustomerFile.close();
           System.out.println("Customer information saved to file: " + customer.getUsername() + ".txt");
               
       } catch (IOException e) {
           System.out.println("Error: Customer Information is not saved.");
       }
       
   }
    
    public void addCustomer(String username, String password) throws IOException{
       //REQUIRES: Username and password cannot be null
       //EFFECTS: Creates a new customer object and then saves it to a file
      Customer newCustomer = new Customer(username, password, "Customer", 100);
      saveToFile(newCustomer);
    }
    
    
    public boolean deleteCustomer(String username){
       //REQUIRES: Username cannot be null
       //EFFECTS: Deletes the customer's file if the file exists for the given username
        File file = new File(username + ".txt");
            if(file.exists()){
                if (file.delete()) {
                    System.out.println("File Deleted for " + username + " successfully." );
                   return true;
                   } else {
                      System.out.println("Failed to delete file for " + username + "!");
                      return false;
                      }
            } else {
                    System.out.println("File doesn't exist " + username + "!");
                    return false;
                    }
                }  
    

   public String toString(){
        //EFFECTS: Returns a string that contains the username, password, and role.
        // Implements the abstraction function
       return "Username: " + this.username + " , Password: " + this.password +  " , Role: " + this.role;
   } 
   
  public boolean repOk(){
        //EFFECTS: Checks if the rep invariant holds true
       if((this.password != null) && (this.username != null) && (this.role.equals("Manager"))){
           return true;
       }
       return false;
    }
    
}
