
package coe528.project;


import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class ManagerController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backButton;

    @FXML
    private Button loginButton;
    
    
    
    /**
     * Initializes the controller class.
     */
    
    @FXML
      private void handleManagerLoginButton(MouseEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        
        Manager manager = new Manager("admin", "admin", "Manager");

        if (manager.login(username, password)) {  
            showAlert("Login Successful", "Welcome, " + username + "!");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ManagerLogin.fxml"));
            Parent root = loader.load();
            Scene nextScene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(nextScene);
            
        } else {
            showAlert("Login Failed", "Invalid username or password!");
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
    private void handleManagerBackButton (MouseEvent event) throws IOException {
        System.out.println("Back Button Pressed");
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) backButton.getScene().getWindow();
        window.setScene(scene);
    }
         
    
}
