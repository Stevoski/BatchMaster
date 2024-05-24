package batchmain.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;

public class SetGetBatchData {
	@FXML private SimpleIntegerProperty ColInward;
	@FXML private SimpleStringProperty colDate;
	@FXML private SimpleStringProperty ColTime;
	@FXML private SimpleStringProperty colDriver;
	@FXML private SimpleStringProperty ColVehicle;
	
	public SetGetBatchData (Integer ColInward, String colDate,String ColTime,String colDriver,
			String ColVehicle) {
		this.ColInward = new SimpleIntegerProperty(ColInward);
		this.colDate = new SimpleStringProperty(colDate);
		this.ColTime = new SimpleStringProperty(ColTime);
		this.colDriver = new SimpleStringProperty(colDriver);
		this.ColVehicle = new SimpleStringProperty(ColVehicle);
		
		
	}
	
	public String getColDate() {
		return colDate.get();
	}
	public void setColDate(SimpleStringProperty colDate) {
		this.colDate = colDate;
	}
	public Integer getColInward() {
		return ColInward.get();
	}
	public void setColTime(SimpleIntegerProperty colInward) {
		this.ColInward = colInward;
	}
	public String getColTime() {
		return ColTime.get();
	}
	public void setColTime(SimpleStringProperty ColTime) {
		this.ColTime = ColTime;
	}
	public String getColDriver() {
		return colDriver.get();
	}
	public void setColDriver(SimpleStringProperty colDriver) {
		this.colDriver = colDriver;
	}
	public String getColVehicle() {
		return ColVehicle.get();
	}
	public void setColVehicle(SimpleStringProperty ColVehicle) {
		this.ColVehicle = ColVehicle;
}
}