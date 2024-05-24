package batchmain.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class SetGetEntranceTable {
	@FXML private SimpleIntegerProperty colInward;
	@FXML private SimpleStringProperty colDate;
	@FXML private SimpleStringProperty colVehicle;
	@FXML private SimpleStringProperty colDriver;
	
		public SetGetEntranceTable (Integer colInward, String colDate,String colVehicle,String colDriver) {
		this.colInward = new SimpleIntegerProperty(colInward);
		this.colDate = new SimpleStringProperty(colDate);
		this.colVehicle = new SimpleStringProperty(colVehicle);
		this.colDriver = new SimpleStringProperty(colDriver);
				
	}
	
	public String getColDate() {
		return colDate.get();
	}
	public void setColDate(SimpleStringProperty colDate) {
		this.colDate = colDate;
	}
	public Integer getColInward() {
		return colInward.get();
	}
	public void setColInward(SimpleIntegerProperty colInward) {
		this.colInward = colInward;
	}
	public String getColVehicle() {
		return colVehicle.get();
	}
	public void setColVehicle(SimpleStringProperty colVehicle) {
		this.colVehicle = colVehicle;
	}
	public String getColDriver() {
		return colDriver.get();
	}
	public void setColDriver(SimpleStringProperty colDriver) {
		this.colDriver = colDriver;
	}
}
