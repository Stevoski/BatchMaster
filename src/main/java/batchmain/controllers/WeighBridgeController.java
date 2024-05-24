package batchmain.controllers;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;

public class WeighBridgeController implements Initializable{

	 @FXML
	    private TableColumn<setGetReceptionTable, Integer> colInward;

	    @FXML
	    private TableColumn<setGetReceptionTable, String> colItem;

	    @FXML
	    private TableColumn<setGetReceptionTable, Double> colQty;
	    
	    @FXML
	    private TableColumn<setGetReceptionTable, String> colUnit;

	    @FXML
	    private TableColumn<setGetReceptionTable, String> colSupplier;
	    
	    @FXML
	    private TableColumn<setGetReceptionTable, String> colWbt;
	    
	    @FXML
	    private TableView<setGetReceptionTable> tblReception;

	    @FXML 
	    private ChoiceBox <ComboValues> mCategorised;
	    
	    @FXML 
	    private ComboBox <String> cmbSupplier;
	    
	       
	    @FXML
	    private TextField inwardNoLoad;
	    
	    @FXML
	    private TextField txtQty;
	    
	    @FXML
	    private TextField lDate;

	    @FXML
	    private TextField lDriver;

	    @FXML
	    private TextField lTruck;

	    @FXML
	    private TextField loadedInward;

	    @FXML
	    private TextField wbtno;
	    
	    @FXML
	    private Label lblUnit;

	    @FXML 
	    private Button btnLoad;
	    
	   
	    @FXML 
	    private TextField txtItem;
	    
	    @FXML 
	    private Button btnRefresh;  
	    
	    @Override
	    public void initialize(URL Location, ResourceBundle resources) {
	    	colInward.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, Integer>("ColInward"));
	    	colItem.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>("ColItem"));
	    	colQty.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, Double>(("ColQty")));
	    	colUnit.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>(("ColUnit")));
	    	colSupplier.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>(("ColSupplier")));
	    	colWbt.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>(("ColWbt")));
//	    	tblReception.setItems(getRecords());
	    	mCategorised.setItems(getcmbCategory());	
//	    	cmbitem.setItems(getItemName());
	    	TextFields.bindAutoCompletion(txtItem, ItemAutocomplete());
			}
	    
	    public ArrayList<String> ItemAutocomplete() {
			ArrayList<String> list = new ArrayList<String>();
			PreparedStatement psffood;
			try {
				psffood = DbConnector.getConnection().prepareStatement("select name from masters");

				ResultSet rs;
				try {
					rs = psffood.executeQuery();

					try {
						while (rs.next()) {
							list.add(rs.getString("name"));

						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch ( SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			return list;
		}
	    
	  ObservableList<setGetReceptionTable> addItemsList = FXCollections.observableArrayList();
	  ObservableList<setGetReceptionTable> reclist = FXCollections.observableArrayList();
	  ObservableList<setGetReceptionTable> addOrder = FXCollections.observableArrayList();
	  ObservableList<setGetReceptionTable> addtempOrder = FXCollections.observableArrayList();
	  
		private void saveOrder() {
			try {
				String inwarded = loadedInward.getText();
				String catg=cmbSupplier.getSelectionModel().getSelectedItem().toString();
				PreparedStatement psSaveAll = DbConnector.getConnection().prepareStatement(
						"insert into reception (inward_no,category,wbt_no,item_name,qty,unit,supplier) values (?,?,?,?,?,?,?)");
				for (int i = 0; i < addOrder.size(); i++) {
					try {
					
						psSaveAll.setInt(1, addOrder.get(i).getColInward().intValue());
						psSaveAll.setString(2, catg);
						psSaveAll.setString(3, addOrder.get(i).getColWbt().toString());
						psSaveAll.setString(4, addOrder.get(i).getColItem().toString());
						psSaveAll.setDouble(5, addOrder.get(i).getColQty().doubleValue());
						psSaveAll.setString(6, addOrder.get(i).getColUnit().toString());
						psSaveAll.setString(7, addOrder.get(i).getColSupplier().toString());

						psSaveAll.executeUpdate();
						System.out.println("saved to db");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				psSaveAll.close();
				try {		
					ObservableList<setGetReceptionTable> allitems, singleitems;
					allitems = tblReception.getItems();
					singleitems = tblReception.getSelectionModel().getSelectedItems();
					// singleitems.forEach(allitems::remove);
					allitems.clear();
					allitems.removeAll();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// psSaveAll.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	  
	  	  
		  @FXML
			public void addData() {
			  getItmUnit();
//			  tblReception.getItems().clear();
			  try {
			  addOrder.add( new setGetReceptionTable(Integer.valueOf(loadedInward.getText()), txtItem.getText(),
						Double.valueOf(txtQty.getText()), lblUnit.getText(), cmbSupplier.getSelectionModel().getSelectedItem().toString(),
						wbtno.getText()));	
			  try {
				  addtempOrder.add( new setGetReceptionTable(Integer.valueOf(loadedInward.getText()), txtItem.getText(),
							Double.valueOf(txtQty.getText()), lblUnit.getText(), cmbSupplier.getSelectionModel().getSelectedItem().toString(),
							wbtno.getText()));	
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
				
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
			  
				tblReception.setItems(addOrder);
				tblReception.refresh();
								ObservableList<setGetReceptionTable> items = tblReception.getItems();
//
//				// Iterate over the items and print their properties
//				for (setGetReceptionTable item : items) {
//				    System.out.println("Inward: " + item.getColInward());
//				    System.out.println("Item: " + item.getColItem());
//				    System.out.println("Quantity: " + item.getColQty());
//				    System.out.println("Unit: " + item.getColUnit());
//				    System.out.println("Supplier: " + item.getColSupplier());
//				    System.out.println("WBT: " + item.getColWbt());
//				    System.out.println(); // Print an empty line for readability
//				}
//				getItmP	rice();
			}
		   public void getItmUnit() {
				String sqlget = "select unit from masters where name=?";
				
				try {
					PreparedStatement sql = DbConnector.getConnection().prepareStatement(sqlget);
					sql.setString(1, txtItem.getText());
//					sql.setString(1, cmbitem.getSelectionModel().getSelectedItem().toString());
					ResultSet raes = sql.executeQuery();
					if (raes.next()) {
						String pri = raes.getString("unit");
						lblUnit.setText(pri);				
					}
					sql.close();
				}
				 catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		   		   
		   @FXML
			public void saveData() {
				// data=FXCollections.observableArrayList();
			   String categ=mCategorised.getSelectionModel().getSelectedItem().toString();
			   
			   switch(categ) {
			   case "Central Stores":
				   try {
						PreparedStatement psSaveAll = DbConnector.getConnection()
								.prepareStatement("insert into reception (inward_no,category,wbt_no,item_name,qty,unit,supplier) values (?,?,?,?,?,?,?)");
						for (int i = 0; i < addOrder.size(); i++) {
							psSaveAll.setString(1, addOrder.get(i).getColInward().toString());
							psSaveAll.setString(2, categ);
							psSaveAll.setString(4, addOrder.get(i).getColItem().toString());
							psSaveAll.setDouble(5, addOrder.get(i).getColQty().doubleValue());
							psSaveAll.setString(6, addOrder.get(i).getColUnit().toString());
							psSaveAll.setString(7, addOrder.get(i).getColSupplier().toString());
							psSaveAll.setString(3, addOrder.get(i).getColWbt().toString());
													
							psSaveAll.executeUpdate();
												}
						
							Alert alert = new Alert(Alert.AlertType.INFORMATION);
					        alert.setTitle("Save Successful!");
					        alert.setHeaderText("OK!");
					        alert.setContentText("Data saved Successfully");

					        // Add buttons to the dialog
					        alert.getButtonTypes().setAll(ButtonType.OK);

					        // Show the dialog and wait for user action
					        alert.showAndWait();
					        System.out.println("saved to db");
					        
					        addOrder.clear();
					        addOrder.removeAll();				        
						
					} catch ( SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
				        alert.setTitle("Save Data Error");
				        alert.setHeaderText("Failed To save data");
				        alert.setContentText("Kindly check if information is okay");

				        // Add buttons to the dialog
				        alert.getButtonTypes().setAll(ButtonType.OK);

				        // Show the dialog and wait for user action
				        alert.showAndWait();
					}

				   break;
				   
 case "Weigh Bridge":
	 try {
			PreparedStatement psSaveAll = DbConnector.getConnection()
					.prepareStatement("insert into reception (inward_no,category,wbt_no,item_name,qty,unit,supplier) values (?,?,?,?,?,?,?)");
			for (int i = 0; i < addOrder.size(); i++) {
				psSaveAll.setString(1, addOrder.get(i).getColInward().toString());
				psSaveAll.setString(2, categ);
				psSaveAll.setString(4, addOrder.get(i).getColItem().toString());
				psSaveAll.setDouble(5, addOrder.get(i).getColQty().doubleValue());
				psSaveAll.setString(6, addOrder.get(i).getColUnit().toString());
				psSaveAll.setString(7, addOrder.get(i).getColSupplier().toString());
				psSaveAll.setString(3, addOrder.get(i).getColWbt().toString());
										
				psSaveAll.executeUpdate();
									}
			
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Save Successful!");
		        alert.setHeaderText("OK!");
		        alert.setContentText("Data saved Successfully");

		        // Add buttons to the dialog
		        alert.getButtonTypes().setAll(ButtonType.OK);

		        // Show the dialog and wait for user action
		        alert.showAndWait();
		        System.out.println("saved to db");
		        
		        addOrder.clear();
		        addOrder.removeAll();				        
			
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Save Data Error");
	        alert.setHeaderText("Failed To save data");
	        alert.setContentText("Kindly check if information is okay");

	        // Add buttons to the dialog
	        alert.getButtonTypes().setAll(ButtonType.OK);

	        // Show the dialog and wait for user action
	        alert.showAndWait();
		}

				   break;
 case "Factory Reception":
	 try {
			PreparedStatement psSaveAll = DbConnector.getConnection()
					.prepareStatement("insert into reception (inward_no,category,wbt_no,item_name,qty,unit,supplier) values (?,?,?,?,?,?,?)");
			for (int i = 0; i < addOrder.size(); i++) {
				psSaveAll.setString(1, addOrder.get(i).getColInward().toString());
				psSaveAll.setString(2, categ);
				psSaveAll.setString(4, addOrder.get(i).getColItem().toString());
				psSaveAll.setDouble(5, addOrder.get(i).getColQty().doubleValue());
				psSaveAll.setString(6, addOrder.get(i).getColUnit().toString());
				psSaveAll.setString(7, addOrder.get(i).getColSupplier().toString());
				psSaveAll.setString(3, addOrder.get(i).getColWbt().toString());
										
				psSaveAll.executeUpdate();
									}
			
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
		        alert.setTitle("Save Successful!");
		        alert.setHeaderText("OK!");
		        alert.setContentText("Data saved Successfully");

		        // Add buttons to the dialog
		        alert.getButtonTypes().setAll(ButtonType.OK);

		        // Show the dialog and wait for user action
		        alert.showAndWait();
		        System.out.println("saved to db");
		        
		        addOrder.clear();
		        addOrder.removeAll();				        
			
		} catch ( SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
	        alert.setTitle("Save Data Error");
	        alert.setHeaderText("Failed To save data");
	        alert.setContentText("Kindly check if information is okay");

	        // Add buttons to the dialog
	        alert.getButtonTypes().setAll(ButtonType.OK);

	        // Show the dialog and wait for user action
	        alert.showAndWait();
		}

	   break;
				  
			   }
				
							}

		   private void loadTable(){
			   reclist.clear();
			   reclist.removeAll();
//				tblReception.setItems(reclist);
			   try {
				   String category=mCategorised.getSelectionModel().getSelectedItem().toString();
					String txtinward=loadedInward.getText();
					String sqlget = "select * from reception where inward_no=? and category=?";
					PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
					psget.setString(1, txtinward);
					psget.setString(2, category);
					ResultSet rs = psget.executeQuery();
					while (rs.next()) {
						
						reclist.add(new setGetReceptionTable(rs.getInt("inward_no"), rs.getString("item_name"),rs.getDouble("qty"),rs.getString("unit"), rs.getString("supplier"), rs.getString("wbt_no")));
					}

					tblReception.setItems(reclist);

				} catch ( SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		   }
		   
	  
//		   public void addData() {
//			   String linward=loadedInward.getText();
//			   String item=cmbitem.getSelectionModel().getSelectedItem().toString();
//	    		String categ=mCategorised.getSelectionModel().getSelectedItem().toString();
//						Double qty=Double.valueOf(txtQty.getText());
//						String unit=lblUnit.getText();
//						String supplier= cmbSupplier.getSelectionModel().getSelectedItem().toString();
//						String wbt=wbtno.getText();
//			    	try {
//			  		PreparedStatement psSaveAll = DbConnector.getConnection().prepareStatement(
//							"insert into reception (inward_no,category,wbt_no,item_name,qty,unit,supplier) values (?,?,?,?,?,?,?)");
//			    	
//			    	psSaveAll.setString(1, linward);
//					psSaveAll.setString(2, categ);
//					psSaveAll.setString(3, wbt);
//					psSaveAll.setString(4, item);
//					psSaveAll.setDouble(5, qty);
//					psSaveAll.setString(6, unit);
//					psSaveAll.setString(7, supplier);
//					
//					psSaveAll.execute();
//					
//					System.out.println("saved to db");
//
//					// }
//					psSaveAll.close();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			    	try {
//			    	    ObservableList<setGetReceptionTable> tblrcp = FXCollections.observableArrayList(); // Initialize ObservableList
//
//			    	    String sqlget = "select * from reception where  inward_no=? ";
//			    	    PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
//			    	    psget.setString(1, linward);
//			    	    ResultSet rs = psget.executeQuery();
//
//			    	    while (rs.next()) {
//			    	    	tblrcp.add(new setGetReceptionTable(rs.getInt("inward_no"), rs.getString("item_name"),rs.getDouble("qty"),rs.getString("unit"), rs.getString("supplier"), rs.getString("wbt_no")));
//			    	    			    	    }
//
//			    	    colInward.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, Integer>("ColInward"));
//				    	colItem.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>("ColItem"));
//				    	colQty.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, Double>(("ColQty")));
//				    	colUnit.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>(("ColUnit")));
//				    	colSupplier.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>(("ColSupplier")));
//				    	colWbt.setCellValueFactory(new PropertyValueFactory<setGetReceptionTable, String>(("ColWbt")));
//				    	tblReception.setItems(tblrcp);
//				    	
//				    	ObservableList<setGetReceptionTable> items = tblReception.getItems();
//				    	
//				    	for (setGetReceptionTable item1 : items) {
//						    System.out.println("Inward: " + item1.getColInward());
//						    System.out.println("Item: " + item1.getColItem());
//						    System.out.println("Quantity: " + item1.getColQty());
//						    System.out.println("Unit: " + item1.getColUnit());
//						    System.out.println("Supplier: " + item1.getColSupplier());
//						    System.out.println("WBT: " + item1.getColWbt());
//						    System.out.println(); // Print an empty line for readability
//						}				    	
//
//			    	    rs.close(); // Close ResultSet
//			    	    psget.close(); // Close PreparedStatement
//			    	    
//			    	     // Set items to tblEntrance
//			    	} catch (SQLException e) {
//			    	    e.printStackTrace(); // Handle SQLException
//			    	}
//			
		   
//		   }
	public void refreshdata() {
		loadedInward.setText(null);
		lDate.setText(null);
		lDriver.setText(null);
		lTruck.setText(null);
		inwardNoLoad.setDisable(false);
	  	  mCategorised.setDisable(false);
	  	ObservableList<setGetReceptionTable> allitems, singleitems;
		allitems = tblReception.getItems();
		singleitems = tblReception.getSelectionModel().getSelectedItems();
		// singleitems.forEach(allitems::remove);
		allitems.clear();
		allitems.removeAll();
	  	  
	  	try {		
reclist=tblReception.getItems();
	  		reclist.clear();
	  		tblReception.getSelectionModel().clearSelection();
	  		tblReception.setItems(reclist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  	  			}
	  
	  public void loadData() {
		  String loaded=inwardNoLoad.getText();
		 
		  try {
//	  	    ObservableList<SetGetEntranceTable> tblentry = FXCollections.observableArrayList(); // Initialize ObservableList

	  	    String sqlget = "select * from EntryTable where inward_no=? ";
	  	    PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
	  	    psget.setInt(1, Integer.valueOf(loaded));
	  	   
	  	    ResultSet rs = psget.executeQuery();

	  	    while (rs.next()) {
	  	    	loadedInward.setText(String.valueOf(rs.getInt("inward_no")));
	  	    	lDate.setText(rs.getString("date"));
	  	    	lDriver.setText(rs.getString("driver"));
	  	    	lTruck.setText(rs.getString("vehicle_no"));	  	    	 	
	  	    }

	  	    rs.close(); // Close ResultSet
	  	    psget.close(); // Close PreparedStatement
	  	  loadTable();
	  	  inwardNoLoad.setDisable(true);
	  	  mCategorised.setDisable(true);
	  	} catch (SQLException e) {
	  	    e.printStackTrace(); // Handle SQLException
	  	}
		  
	  }
	  
	  public ObservableList<ComboValues> getcmbCategory() {
			ObservableList<ComboValues> foode = FXCollections.observableArrayList(new ComboValues("Central Stores"),
					new ComboValues("Weigh Bridge"),new ComboValues("Factory Reception"));
			return foode;
			// setSale_id(getSales_id());
		}
	  public ObservableList<ComboValues> getcmbItem() {
			ObservableList<ComboValues> foode = FXCollections.observableArrayList(new ComboValues("(Verona) Pure Black Pepper 50g"),
					new ComboValues("2M PREMIUM DARK COMPOUND (CP-17) 500G"));
			return foode;
			// setSale_id(getSales_id());
		}
	  
	  public ObservableList<ComboValues> getItemName() {
			ObservableList<ComboValues> wid = FXCollections.observableArrayList();
			PreparedStatement pswaiterid;
			try {
				pswaiterid = DbConnector.getConnection().prepareStatement("select name from masters ");

				ResultSet rs;
				try {
					rs = pswaiterid.executeQuery();

					try {
						while (rs.next()) {
							wid.add(new ComboValues(rs.getString("name")));
						}

						// foodcmbauto.bindAutocompletion(foodcmbauto, list);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch ( SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			return wid;
		}
	    
	}
