
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * 
 */
public class CustomerLoginController  {

    @FXML
    private Button depositButton;

    @FXML
    private Button withdrawalButton;
    
    
    @FXML
    private Button onlinePurchaseButton;

    @FXML
    private Button balanceButton;
    
    @FXML
    private Button logoutButton;
    
    @FXML
    private Label balanceLabel;
    
    @FXML
    private TextField amountField;
    
    @FXML
    private Button okButton;
    
    @FXML 
    private Button backButton;
    
    @FXML
    private Label customerNameLabel;
    
    @FXML 
    private TextField itemName;
    
    private Customer customerUsing;
    private boolean isDepositing;
    private boolean isWithdrawing;
    private boolean isOnlinePurchasing;
    private String username;
    private double balance;
    
    
    
    public void setCustomer(Customer customer) {
    this.customerUsing = customer;
    customerNameLabel.setText("Hello, " + customer.getUsername() + "!");
    customerNameLabel.setFont(Font.font("SansSerif", 18));
    customerNameLabel.setTextFill(Color.BLUE);
    
    }

    
    @FXML
    private void handleDepositButton(MouseEvent event) throws IOException {
        balanceButton.setVisible(false);
        depositButton.setVisible(false);
        withdrawalButton.setVisible(false);
        okButton.setVisible(true);
        onlinePurchaseButton.setVisible(false);
        balanceLabel.setVisible(false);
        amountField.setVisible(true);
        itemName.setVisible(false);
        logoutButton.setVisible(true);
        backButton.setVisible(true);
        isDepositing = true;
       
        
    }

    @FXML
    private void handleWithdrawalButton(MouseEvent event) throws IOException {
        balanceButton.setVisible(false);
        depositButton.setVisible(false);
        withdrawalButton.setVisible(false);
        okButton.setVisible(true);
        onlinePurchaseButton.setVisible(false);
        balanceLabel.setVisible(false);
        amountField.setVisible(true);
        itemName.setVisible(false);
        logoutButton.setVisible(true);
        backButton.setVisible(true);
        isWithdrawing = true;
    }

    @FXML
    private void handleOnlinePurchaseButton(MouseEvent event) throws IOException{
        balanceButton.setVisible(false);
        depositButton.setVisible(false);
        withdrawalButton.setVisible(false);
        okButton.setVisible(true);
        onlinePurchaseButton.setVisible(false);
        balanceLabel.setVisible(false);
        amountField.setVisible(true);
        logoutButton.setVisible(true);
        itemName.setVisible(true);
        backButton.setVisible(true);
        isOnlinePurchasing = true;
    }

    @FXML
    private void handleBalanceButton(MouseEvent event) {
        balanceButton.setVisible(false);
        depositButton.setVisible(false);
        withdrawalButton.setVisible(false);
        okButton.setVisible(false);
        onlinePurchaseButton.setVisible(false);
        balanceLabel.setVisible(true);
        amountField.setVisible(false);
        logoutButton.setVisible(true);
        itemName.setVisible(false);
        backButton.setVisible(true);
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", customerUsing.getBalance()));
        balanceLabel.setFont(Font.font("SansSerif", 24));
        balanceLabel.setTextFill(Color.RED);
    }

    @FXML
    private void handleCustomerLogoutButton(MouseEvent event) throws IOException {
        System.out.println("Logout button clicked");
        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root);
        Stage window = (Stage) logoutButton.getScene().getWindow();
        window.setScene(scene);
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
        balanceButton.setVisible(true);
        depositButton.setVisible(true);
        withdrawalButton.setVisible(true);
        okButton.setVisible(false);
        onlinePurchaseButton.setVisible(true);
        balanceLabel.setVisible(false);
        amountField.setVisible(false);
        logoutButton.setVisible(true);
        backButton.setVisible(false);
        itemName.setVisible(false);
        isOnlinePurchasing = false;
        isDepositing = false;
        isWithdrawing = false;
        amountField.clear();
        itemName.clear();
        
    }

     
    @FXML
    private void handleOkButton(MouseEvent event) throws IOException{
        boolean success = false;
       try {
            double amount = Double.parseDouble(amountField.getText());
            String productName = itemName.getText();
            if (amount < 0) {
            showAlert("Invalid Input", "Please enter a positive number.");
            amountField.clear();
            itemName.clear();
            return; 
            }
            
            if (isDepositing) {
                success = customerUsing.deposit(amount);
                if (success){
                    showAlert("Deposit Successful", "Amount deposited successfully.");
                    amountField.clear();
                    } else {
                    showAlert("Deposit Failed", "Amount not deposited.");
                    amountField.clear();
                }
            } else if (isWithdrawing) {
                success = customerUsing.withdrawal(amount);
                if (success){
                showAlert("Withdrawal Successful", "Amount withdrawn successfully.");
                amountField.clear();
                } else {
                    showAlert("Withdrawal Failed", "Insufficient funds.");
                    amountField.clear();
                }
            } else if (isOnlinePurchasing) {
                if (productName.isEmpty()){
                    showAlert("Invalid Input", "Please enter an item name.");
                    amountField.clear();
                    itemName.clear();
                    return;
                    }
                // checks if item name contains number
                else if (productName.matches((".*\\d.*"))){ 
                    showAlert("Invalid Input", "Please enter a valid item name.");
                    amountField.clear();
                    itemName.clear();
                    return;
                }
                success = customerUsing.onlinePurchase(amount);
                if (success){
                showAlert("Online Purchase Successful", "Amount purchased online successfully.");
                amountField.clear();
                itemName.clear();
                } else { 
                     showAlert("Online Purchase Failed", "Insufficient funds or amount.");
                     amountField.clear();
                     itemName.clear();
                }
            }
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter a valid number.");
            amountField.clear();
        } 
    
    }
    
  
}
