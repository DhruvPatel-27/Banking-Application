
package coe528.project;

import java.io.IOException;

/**
 *
 *
 */

/* 
   OVERVIEW: 
   This is an abstract class which represents the user in the Banking System.
   An user has a username, password and a role. This class is mutable.

   ABSTRACTION FUNCTION:
   AF(c) = a user, u, such that 
   u.username =  c.username
   u.password =  c.password
   u.role =  c.role

  REP INVARIANT:
  RI(c) = 
  c.username !=  null
  c.password !=  null
  c.role !=  null 

*/


public abstract class User {
    // the rep
    protected String username;
    protected String password;
    protected String role;
    
    
    public User(String username, String password, String role){
        //EFFECTS: Creates a new user object given a username, password, and role
        this.username = username;
        this.password = password;
        this.role = role;      
    }
    
    
     public void setUsername(String username){
        //REQUIRES: username cannot be null
        //MODIFIES: username
        //EFFECTS: Sets the username of the user
       this.username = username;
   }
   
    public void setPassword(String password){
        //REQUIRES: password cannot be null
        //MODIFIES: password
        //EFFECTS: Sets the password of the user
       this.password = password;
   }
    
    public void setRole(String role){
        //REQUIRES: role cannot be null
        //MODIFIES: role
        //EFFECTS: Sets the role of the user
       this.role = role;
   }
    
    public String getUsername(){
        //EFFECTS: Returns the username of the user 
        return this.username;
    }
    
    
    public String getPassword(){
        //EFFECTS: Returns the password of the user 
        return this.password;
    }
     
    public String getRole(){
      //EFFECTS: Returns the role of the user 
      return this.role;
      }
    
    public abstract boolean login(String username, String password) throws IOException;
    
    
    public String toString(){
        //EFFECTS: Returns a string that contains the username, password, and role.
        // Implements the abstraction function
        return "Username: " + this.username + " , Password: " + this.password +  " , Role: " + this.role;
    }
    
    public boolean repOk(){
        //EFFECTS: Checks if the rep invariant holds true
       if((this.password != null) && (this.username != null) && (this.role != null) ){
           return true;
       }
       return false;
    }
   
}
