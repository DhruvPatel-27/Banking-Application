
package coe528.project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * 
 */
public class CustomerController {

    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;
    
    private Scene scene;
    
    
    @FXML
    private void handleLoginButton(MouseEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        Customer customer = new Customer(username, password, "Customer", 0);

        if (customer.login(username, password)) {
            showAlert("Login Successful", "Welcome, " + username + "!");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerLogin.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            CustomerLoginController controller = loader.getController();
            Customer loggedInCustomer = new Customer(username, password, "Customer", customer.getBalanceFromFile(username));
            controller.setCustomer(loggedInCustomer);
            
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(nextScene);
            
        } else {
            showAlert("Login Failed", "Invalid username or password!");
            passwordField.clear();
            usernameField.clear();
        }
    }
    
    
     private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
            
    @FXML
    private void handleBackButton (MouseEvent event) throws IOException {
        System.out.println("Back Button Pressed");
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(scene);
 
    }
  
}
