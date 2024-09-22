
package coe528.project;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class ManagerLoginController  {
    
    
    @FXML
    private Button addCustomerButton;

    @FXML
    private Button deleteCustomerButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private TextField passwordField;
    
    @FXML
    private Button okButton;
    
    @FXML 
    private Button backButton;
    
    private boolean isAddingCustomer;
    private boolean isDeletingCustomer;
    private Manager manager = new Manager("admin", "password", "Manager");



    @FXML
    private void handleAddCustomer(MouseEvent event) {
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        logoutButton.setVisible(true);
        okButton.setVisible(true);
        addCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        backButton.setVisible(true);
        isAddingCustomer = true;
        
    }

    @FXML
    private void handleDeleteCustomer(MouseEvent event) {
        usernameField.setVisible(true);
        passwordField.setVisible(false);
        logoutButton.setVisible(true);
        okButton.setVisible(true);
        addCustomerButton.setVisible(false);
        deleteCustomerButton.setVisible(false);
        backButton.setVisible(true);
        isDeletingCustomer = true;
        
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void handleBackButton(MouseEvent event) {
        usernameField.setVisible(false);
        passwordField.setVisible(false);
        logoutButton.setVisible(true);
        okButton.setVisible(false);
        addCustomerButton.setVisible(true);
        deleteCustomerButton.setVisible(true);
        backButton.setVisible(false);
        isDeletingCustomer = false;
        isAddingCustomer = false;
        usernameField.clear();
        passwordField.clear();
        
    }
    
    
    @FXML
    private void handleManagerLogoutButton(MouseEvent event) throws IOException {
        System.out.println("Logout button clicked");
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(scene);
        }

    @FXML
    private void handleOkButton(MouseEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        if(isAddingCustomer){
            if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty.");
            return;
        } else if ("admin".equals(username)){
            showAlert("Error", "Username cannot be admin.");
            usernameField.clear();
            passwordField.clear();
            return;
        }
            try {
            manager.addCustomer(username, password);
            showAlert("Add Customer", "Customer added successfully.");
            usernameField.clear();
            passwordField.clear();
           } catch (IOException e) {
            showAlert("Error", "Failed to add customer: " + e.getMessage());
            usernameField.clear();
            passwordField.clear();
            }
        } else if(isDeletingCustomer){
            if (username.isEmpty()) {
            showAlert("Error", "Username cannot be empty.");
            return;
            }
            boolean customerDelete = manager.deleteCustomer(username);
            if (customerDelete){
            showAlert("Delete Customer", "Customer deleted successfully.");
            usernameField.clear();
            } else{
                showAlert("Error", "Failed to delete customer: " + username + ", since it does not exist.");
                usernameField.clear();
            }
        }
    }
    
    
    }
    
