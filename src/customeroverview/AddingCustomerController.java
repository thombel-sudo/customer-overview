/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customeroverview;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddingCustomerController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfSurname ;
    @FXML
    private TextField tfNumber ;
    @FXML
    private TextField tfCity;
    @FXML
    private TextField tfAddress; 
    @FXML
    private TextField tfPayment;
    @FXML
    private Button btnReturn;
    @FXML
    private void btnReturn(ActionEvent event){
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void btnAddCustomerClicked(ActionEvent event){
        //System.out.println("well i'm started at least");
        if(isEverithingFilled() == 1){
            
            try
		{
                //String text = txtQuestion.getText();
                
		String query = 
		"INSERT INTO users_info(First_name,Last_name,Contact_number,City,Address,Payment) " +
		"VALUES(?,?,?,?,?,?)";
		PreparedStatement statement = co.prepareStatement(query);
                    statement.setString(1, tfName.getText());
                    statement.setString(2, tfSurname.getText());
                    statement.setString(3, tfNumber.getText());
                    statement.setString(4, tfCity.getText());
                    statement.setString(5, tfAddress.getText());
                    statement.setString(6, tfPayment.getText());
                    
                
		statement.executeUpdate();
                statement.close();
                System.out.println("Success");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Результат дії");
                alert.setHeaderText(null);
                alert.setContentText("Запит виконано!");
                alert.showAndWait();
		}
		catch (Exception e)
		{
		System.out.println( e.getMessage());
		}
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Помилка");
                alert.setHeaderText(null);
                alert.setContentText("Заповніть всі поля!");
                alert.showAndWait();
        }
    }
    int isEverithingFilled(){
        if(tfName.getText().isEmpty()|| tfSurname.getText().isEmpty()||tfNumber.getText().isEmpty()||tfCity.getText().isEmpty()||tfAddress.getText().isEmpty()||tfPayment.getText().isEmpty() ){
            return 0;
        }
        return 1;
    }
    /**
     * Initializes the controller class.
     */
    Connection co;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try
		{
			Class.forName("org.sqlite.JDBC");
			co = DriverManager.getConnection ( "jdbc:sqlite:src\\database\\users_database.db" );
			System.out.println("Connected");
                       
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage());
                       
		}
    }    
    
}
