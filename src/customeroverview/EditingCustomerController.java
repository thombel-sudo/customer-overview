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
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class EditingCustomerController implements Initializable {
    ArrayList<Integer> id ;
    @FXML
    private ComboBox cbID;
    @FXML
    private TextField tbName;
    @FXML
    private TextField tbSurname;
    @FXML
    private TextField tbNumber;
    @FXML
    private TextField tbCity;
    @FXML
    private TextField tbAddress;
    @FXML
    private TextField tbPayment;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblID;
    @FXML
    private void cbIDChanged(ActionEvent event){
        try
                {
                Statement statement = co.createStatement();
                String query = 
                        "SELECT * "
                        + "FROM users_info WHERE id="+cbID.getValue().toString();
                System.out.println(query);
                ResultSet rs = statement.executeQuery (query);
                while (rs.next())
                        {
                         lblID.setText(rs.getString("id"));
                         tbName.setText(rs.getString("First_name"));
                         tbSurname.setText(rs.getString("Last_name"));
                         tbNumber.setText(rs.getString("Contact_number"));
                         tbCity.setText(rs.getString("City"));
                         tbAddress.setText(rs.getString("Address"));
                         tbPayment.setText(rs.getString("Payment"));
                        }
                rs.close();
                statement.close();
                }
                catch (Exception e)
		{
		System.out.println( e.getMessage());
		} 
    }
    @FXML
    private void btnBackClicked(ActionEvent event){
        Stage stage = (Stage) btnBack.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void btnSaveClicked(ActionEvent event){
        try
		{
                //String text = txtQuestion.getText();
                
		String query = 
		"UPDATE users_info SET First_name = ? , Last_name = ? , Contact_number = ? , "+
                        "City = ? , Address = ? , Payment = ? WHERE id=" + lblID.getText();
		PreparedStatement statement = co.prepareStatement(query);
                    statement.setString(1, tbName.getText());
                    statement.setString(2, tbSurname.getText());
                    statement.setString(3, tbNumber.getText());
                    statement.setString(4, tbCity.getText());
                    statement.setString(5, tbAddress.getText());
                    statement.setString(6, tbPayment.getText());
                    
                
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
        id = new ArrayList<Integer>();    
                try
                {
                Statement statement = co.createStatement();
                String query = 
                        "SELECT id "
                        + "FROM users_info" ;
                System.out.println(query);
                ResultSet rs = statement.executeQuery (query);
                while (rs.next())
                        {
                         id.add(Integer.parseInt(rs.getString("id")));
                        }
                rs.close();
                statement.close();
                }
                catch (Exception e)
		{
		System.out.println( e.getMessage());
		} 
                cbID.getItems().addAll(id);
    }    
    
}
