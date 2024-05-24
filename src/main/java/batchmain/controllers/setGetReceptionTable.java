package batchmain.controllers;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class setGetReceptionTable {
		
	private final IntegerProperty colInward;
	private final  StringProperty colItem;	
	private final  DoubleProperty colQty;
	private final  StringProperty colUnit;
	private final  StringProperty colSupplier;
	private final  StringProperty colWbt;
	
	
	public setGetReceptionTable(Integer colInward,String colItem, Double colQty, String colUnit, String colSupplier,String colWbt ) {
				this.colInward =new SimpleIntegerProperty(colInward);	
		this.colItem = new SimpleStringProperty(colItem);
		this.colQty = new SimpleDoubleProperty(colQty);
		this.colUnit = new SimpleStringProperty(colUnit);
		this.colSupplier = new SimpleStringProperty(colSupplier);
		this.colWbt = new SimpleStringProperty(colWbt);
		
	}

	public Integer getColInward() {
		return colInward.get();
	}

	public void setColInward(Integer colInward) {
		this.colInward.set(colInward);
	}
	public IntegerProperty ColInwardProperty() {
		return colInward;
	}
	
	public String getColItem() {
		return colItem.get();
	}

	public void setColItem(String colItem) {		
		this.colItem.set(colItem);
	}
	public StringProperty ColItemProperty() {
		return colItem;
	}
	public Double getColQty() {
		return colQty.get();
	}

	public void setColQty(Double colQty) {
		
		this.colQty.set(colQty);
	}
	public DoubleProperty ColQtyProperty() {
		return colQty;
	}
	public String getColUnit() {
		return colUnit.get();
	}

	public void setColUnit(String colUnit) {
		
		this.colUnit.set(colUnit);
	}
	public StringProperty ColUnitProperty() {
		return colUnit;
	}

	public String getColSupplier() {
		return colSupplier.get();
	}

	public void setColSupplier(String colSupplier) {
		
		this.colSupplier.set(colSupplier);
	}
	public StringProperty ColSupplierProperty() {
		return colSupplier;
	}
	
	public String getColWbt() {
		return colWbt.get();
	}

	public void setColWbt(String colWbt) {
		
		this.colWbt.set(colWbt);
	}
	public StringProperty ColWbtProperty() {
		return colWbt;
	}
}
