package batchmain.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EntranceController implements Initializable {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnSave;

    @FXML
    private TableColumn<SetGetEntranceTable, String> colDate;

    @FXML
    private TableColumn<SetGetEntranceTable, String> colDriver;

    @FXML
    private TableColumn<SetGetEntranceTable, Integer> colInward;

    @FXML
    private TableColumn<SetGetEntranceTable, String> colVehicle;

    @FXML
    private DatePicker dated;

    @FXML
    private TextField driver;

    @FXML
    private TextField inwardNo;

    @FXML
    private TableView<SetGetEntranceTable> tblEntrance;

    @FXML
    private TextField vehicle;

    @Override
    public void initialize(URL Location, ResourceBundle resources) {    	
    	    	colInward.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, Integer>(("ColInward")));
    	colDate.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("ColDate"));
    	colVehicle.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>(("ColVehicle")));
    	colDriver.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("ColDriver"));
//    	tblEntrance.setItems(getRecords());
		//comboChooser.setItems(getdashchoice());		
		testSql();
    	   	
		}
  ObservableList<SetGetEntranceTable> tblentry = FXCollections.observableArrayList();
  
  
//  public ObservableList<SetGetEntranceTable> getRecords() {
//		ObservableList<SetGetEntranceTable> productse = FXCollections
//				.observableArrayList(new SetGetEntranceTable(1, "2020-09-01", "KAA 123", "Test"));
//		return productse;
//	}
  
  public void testSql() {
	  try {
//  	    ObservableList<SetGetEntranceTable> tblentry = FXCollections.observableArrayList(); // Initialize ObservableList

  	    String sqlget = "select * from EntryTable ";
  	    PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
  	    ResultSet rs = psget.executeQuery();

  	    while (rs.next()) {
  	        tblentry.add(new SetGetEntranceTable(rs.getInt("inward_no"), rs.getString("date"), rs.getString("vehicle_no"), rs.getString("driver")));
  	    	
  	    }

  	    colInward.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, Integer>(("ColInward")));
      	colDate.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("ColDate"));
      	colVehicle.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>(("ColVehicle")));
      	colDriver.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("ColDriver"));
  	    
  	    tblEntrance.setItems(tblentry); // Set items to tblEntrance

  	    rs.close(); // Close ResultSet
  	    psget.close(); // Close PreparedStatement

  	} catch (SQLException e) {
  	    e.printStackTrace(); // Handle SQLException
  	}

  
  }
    public  void saveData() {
    	try {
    	String dateds=dated.getValue().toString();
    	String veh=vehicle.getText();
    	String driv=driver.getText();
    	PreparedStatement psSaveAll = DbConnector.getConnection().prepareStatement(
				"insert into EntryTable (date,driver,vehicle_no) values (?,?,?)");
    	
    	psSaveAll.setString(1, dateds);
		psSaveAll.setString(2, driv);
		psSaveAll.setString(3, veh);
		psSaveAll.execute();
		
		System.out.println("saved to db");

		// }
		psSaveAll.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	try {
//    	    ObservableList<SetGetEntranceTable> tblentry = FXCollections.observableArrayList(); // Initialize ObservableList

    	    String sqlget = "select * from EntryTable ";
    	    PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
    	    ResultSet rs = psget.executeQuery();

    	    while (rs.next()) {
    	        tblentry.add(new SetGetEntranceTable(rs.getInt("inward_no"), rs.getString("date"), rs.getString("vehicle_no"), rs.getString("driver")));
    	    	
    	    }

    	    colInward.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, Integer>(("ColInward")));
        	colDate.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("ColDate"));
        	colVehicle.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>(("ColVehicle")));
        	colDriver.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("ColDriver"));
    	    
    	    tblEntrance.setItems(tblentry); // Set items to tblEntrance

    	    rs.close(); // Close ResultSet
    	    psget.close(); // Close PreparedStatement

    	} catch (SQLException e) {
    	    e.printStackTrace(); // Handle SQLException
    	}
    	
}
    
}


    	
    			

