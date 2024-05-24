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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class EntranceController {

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

    public void initialize(URL Location, ResourceBundle resources) {
    	colDate.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("colDate"));
    	colDriver.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>("colDriver"));
    	colInward.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, Integer>(("colInward")));
    	colVehicle.setCellValueFactory(new PropertyValueFactory<SetGetEntranceTable, String>(("colVehicle")));
    	
		//comboChooser.setItems(getdashchoice());		
		//batchTabledata();
		}
  ObservableList<SetGetEntranceTable> tblentry = FXCollections.observableArrayList();
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
		
		System.out.println("saved to db");

		// }
		psSaveAll.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	try {
//			
			ObservableList<SetGetEntranceTable> allitems, singleitems;
			allitems = tblEntrance.getItems();
			singleitems = tblEntrance.getSelectionModel().getSelectedItems();
			// singleitems.forEach(allitems::remove);
			allitems.clear();
			allitems.removeAll();
			String sqlget="select * from EntryTable ";
			PreparedStatement psget=DbConnector.getConnection().prepareStatement(sqlget);
			//psget.setString(1,saleid);
			ResultSet rs=psget.executeQuery();
			while(rs.next()) {					
				tblentry.add(new SetGetEntranceTable(rs.getInt("inward_no"),rs.getString("date"),rs.getString("vehicle_no"),rs.getString("driver")));						

			}

			tblEntrance.setItems(tblentry);			
//
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
}
    
}


    	
    			

