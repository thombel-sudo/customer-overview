/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package customeroverview;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import customeroverview.Customer;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author User
 */
public class FXMLDocumentController implements Initializable {
    private ObservableList<Customer> CustomersData = FXCollections.observableArrayList();
    @FXML
    private TableView<Customer> tableCustomers;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;
    
    @FXML
    private TableColumn<Customer, String> surnameColumn;
    
    @FXML
    private TableColumn<Customer, String> numberColumn;
    
    @FXML
    private TableColumn<Customer, String> cityColumn;
    
    @FXML
    private TableColumn<Customer, String> addressColumn;
    
    @FXML
    private TableColumn<Customer, String> paymentColumn;
    //@FXML
    //private Label label;
    @FXML
    private ComboBox cbFilter;
    @FXML
    private TextField tbFilter;
    
    Connection co;
    int checkSuccesfull = 1;
    @FXML
    private void menuAboutDevClicked(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Розробник");
        alert.setHeaderText("Розробником даної програми є Чепка Євгеній");
        alert.setContentText("Програма виконана в рамці прохождення виробничої практики");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
            alert.close();
            }
        });
    }
    @FXML
    private void menuAboutProgClicked(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Програма");
        alert.setHeaderText("Програма Customer Overview призначена для обліку клієнтів підприємств.");
        alert.setContentText("*Навігація та керування описані в пункті 'Допомога'");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
            alert.close();
         }
        });
    }
    @FXML
    private void menuHelpClicked(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Допомога");
        alert.setHeaderText("Перегляд всієї бази даних здійснюється на головному вікні. \nДля додавання, видалення та редагування даних клієнтів перейдіть до пункту 'Додатково'");
        alert.setContentText("*Для виходу з програми перейдіть до пункту 'Додатково' та оберіть 'Вихід з програми'");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
           alert.close();
             }
        });
    }
    @FXML
    private void btnExitClicked(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вихід з програми");
        alert.setHeaderText("Бажаєте закрити программу?");
       // alert.setContentText("*Для виходу з програми натисніть відповідну кнопку");
        alert.showAndWait().ifPresent(rs -> {
        if (rs == ButtonType.OK) {
        System.exit(0);
         }else{
            alert.close(); 
        }
        });
       
    }
    @FXML
    private void btnUpdateClicked(ActionEvent event){
        tableCustomers.getItems().clear();
        fillTable();
    }
    @FXML
    private void menuChangeClient(ActionEvent event){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("EditingCustomer.fxml"));
            stage.setTitle("Редагування клієнта");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void menuDeleteClient(ActionEvent event){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("DeletingCustomer.fxml"));
            stage.setTitle("Видалення клієнта");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void menuAddClient(ActionEvent event){
        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("addingCustomer.fxml"));
            stage.setTitle("Додавання клієнта");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            //stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
        }catch(Exception e ){
            System.out.println(e.getMessage());
        }
    }
    @FXML
    private void btnFilterClicked(ActionEvent event){
        try {
            String filter = "";
            checkSuccesfull = 1;
            switch (cbFilter.getValue().toString()) {
                case ("id"):
                    filter = "id";
                    break;
                case ("Імя"):
                    filter = "First_name";
                    break;
                case ("Призвіще"):
                    filter = "Last_name";
                    break;
                case ("Номер телефону"):
                    filter = "Contact_number";
                    break;
                case ("Місто"):
                    filter = "City";
                    break;
                case ("Адреса"):
                    filter = "Address";
                    break;
                case ("Сумма платежу"):
                    filter = "Payment";
                    break;
                default:
                    System.out.println("error");
                    checkSuccesfull = 0;
            }
            if (checkSuccesfull != 0) {
                tableCustomers.getItems().clear();
                CustomersData.removeAll();
                if (tbFilter.getText() != "") {
                    try {
                        Statement statement = co.createStatement();
                        String query
                                = "SELECT * "
                                + "FROM users_info WHERE " + filter + " LIKE '%" + tbFilter.getText() + "%'";
                        System.out.println(query);
                        ResultSet rs = statement.executeQuery(query);
                        while (rs.next()) {
                            CustomersData.add(new Customer(Integer.parseInt(rs.getString("id")),
                                    rs.getString("First_name"),
                                    rs.getString("Last_name"),
                                    rs.getString("Contact_number"),
                                    rs.getString("City"),
                                    rs.getString("Address"),
                                    rs.getString("Payment")));
                        }
                        rs.close();
                        statement.close();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }                    
                    tableCustomers.setItems(CustomersData);
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Помилка!");
            alert.setHeaderText("Помилка у введенні даних. Спробуйте ще раз");
            //alert.setContentText("*Для виходу з програми перейдіть до пункту 'Додатково' та оберіть 'Вихід з програми'");
            alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
            alert.close();
             }
            });
        }
    }
    void fillTable(){
        
        try
		{
			Class.forName("org.sqlite.JDBC");
			co = DriverManager.getConnection ( "jdbc:sqlite:src\\database\\users_database.db" );
			//System.out.println("Connected");
                       
		}
		catch (Exception e)
		{
			System.out.println( e.getMessage());
                       
		}
        
        try
                {
                Statement statement = co.createStatement();
                String query = 
                        "SELECT * "
                        + "FROM users_info "
                        + "ORDER BY id";
                ResultSet rs = statement.executeQuery (query);
                while (rs.next())
                        {
                         CustomersData.add(new Customer(Integer.parseInt(rs.getString("id")),
                                                rs.getString("First_name"),
                                                rs.getString("Last_name"),
                                                rs.getString("Contact_number"),
                                                rs.getString("City"),
                                                rs.getString("Address"),
                                                rs.getString("Payment")));
                        }
                rs.close();
                statement.close();
                }
            catch (Exception e)
		{
		System.out.println( e.getMessage());
		}
        tableCustomers.setItems(CustomersData);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cbFilter.getItems().addAll("id","Імя","Прізвище","Номер телефону","Місто","Адреса","Сумма платежу");
        idColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("surname"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("number"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("city"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("address"));
        paymentColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("payment"));
        fillTable();
    }    
    
}
