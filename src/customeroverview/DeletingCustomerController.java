/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customeroverview;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class DeletingCustomerController implements Initializable {
    @FXML
    private TextField tfDelete;
    
    @FXML
    private void btnDeleteClicked(ActionEvent event){
        try{

		String query = 
		"DELETE FROM users_info"+ 
		" WHERE id= " + Integer.parseInt(tfDelete.getText()) +  ";";
		Statement statement = co.createStatement();
		statement.executeUpdate(query);
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
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Результат дії");
                alert.setHeaderText(null);
                alert.setContentText("Несправний ID користувача!");
                alert.showAndWait();
		System.out.println( e.getMessage());
		}
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
