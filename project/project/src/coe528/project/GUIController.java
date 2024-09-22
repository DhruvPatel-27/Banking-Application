

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GUIController extends Application  {

    @FXML
    private Button customerButton;
    @FXML
    private Button managerButton;
    
    private Scene scene;
      
    @FXML
    private void handleCustomerButtonAction (MouseEvent event){
         try {
        System.out.println("Customer Button Pressed");
        Parent customerScene = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        Stage stage = (Stage) customerButton.getScene().getWindow();
        stage.setScene(new Scene(customerScene));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace(); 
    }
    }
    
    @FXML
    private void handleManagerButtonAction (MouseEvent event){
            try {
        System.out.println("Manager Button Pressed");
        Parent managerScene = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        Stage stage = (Stage) managerButton.getScene().getWindow();
        stage.setScene(new Scene(managerScene));
        stage.show();
    } catch (IOException e) {
        e.printStackTrace(); 
    }

    }
      
   @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root,600, 400);
        stage.setTitle("Bank Account Application");
        stage.setScene(scene);
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args);
    }

   
}

