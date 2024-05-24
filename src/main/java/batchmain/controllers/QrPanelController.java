package batchmain.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.stage.Screen;

public class QrPanelController implements Initializable{

	
	@FXML
    private MenuBar MainMenu;

    @FXML
    private MenuItem menitem_requestItem;

       @FXML
    private Menu menuFile;
    
    @FXML
    private MenuItem securityin;

        @FXML
    private MenuItem weighbridgein;
    
    @FXML
    private MenuItem menuItem_listRequestitem;

    @FXML
    private Menu menuTransaction;
    
    @FXML
    private MenuItem mentestcon;

    @FXML
    private Menu minMenuMatIssue;
//Old Lot Starts from here

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtDateofProduction;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtBatchNo;

    @FXML
    private TextField txtExpiry;

    @FXML
    private Button btnGenerateQrCode;
    
    @FXML
    private Button btnCancel;
    
    @FXML
    private ImageView imgviewQr;
    
    @FXML
    private TableColumn<SetGetBatchData, Integer> ColInward;

    @FXML
    private TableColumn<SetGetBatchData, String> ColDate;
    
    @FXML
    private TableColumn<SetGetBatchData, String> ColTime;
    
    @FXML
    private TableColumn<SetGetBatchData, String> colDriver;
    
    @FXML
    private TableColumn<SetGetBatchData, String> ColVehicle;
    
    @FXML
	private TableView<SetGetBatchData> pendingTable;
    
    @FXML
    private BorderPane MainDashboard;
    
    @FXML 
    private Label labelTrucks;
    
    @FXML private AnchorPane mainAnchor;
    
    @Override
    public void initialize(URL Location, ResourceBundle resources) {
    	ColInward.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, Integer>("ColInward"));
    	ColDate.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>("ColDate"));
    	ColTime.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>(("ColTime")));
    	colDriver.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>(("ColDriver")));
    	ColVehicle.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>(("ColVehicle")));
    	
//		mCategory.setItems(getcmbCategory());		
		//batchTabledata();
		testSql();
			}
    
    ObservableList<SetGetBatchData> homeTbl = FXCollections.observableArrayList(); // Initialize ObservableList
    
    public void testSql() {
    	 try {
 		

 		    String sqlget = "select * from EntryTable ";
 		    PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
 		    ResultSet rs = psget.executeQuery();

 		    while (rs.next()) {
 		    	homeTbl.add(new SetGetBatchData(rs.getInt("inward_no"), rs.getString("date"),rs.getString("date"), rs.getString("vehicle_no"), rs.getString("driver")));
 		    }

 		   ColInward.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, Integer>("ColInward"));
 	    	ColDate.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>("ColDate"));
 	    	ColTime.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>(("ColTime")));
 	    	colDriver.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>(("ColDriver")));
 	    	ColVehicle.setCellValueFactory(new PropertyValueFactory<SetGetBatchData, String>(("ColVehicle")));
 	    	
 	    	
 		   pendingTable.setItems(homeTbl); // Set items to tblEntrance

 		    rs.close(); // Close ResultSet
 		    psget.close(); // Close PreparedStatement

 		} catch (SQLException e) {
 		    e.printStackTrace(); // Handle SQLException
 		}
    }
    
    public void loadHome() throws IOException {
    	
      try {
    	  MainDashboard.getChildren().clear();
//    	  
//    	  FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MainStockMaster.fxml"));
          Parent procurementPane = MainDashboard.getParent();
//
          // Set preferred size of procurementPane to match the center pane size
          BorderPane borderPane = (BorderPane) MainDashboard.getScene().getRoot();
          double centerWidth = borderPane.getCenter().getBoundsInLocal().getWidth();
          double centerHeight = borderPane.getCenter().getBoundsInLocal().getHeight();
          ((Region) procurementPane).setPrefSize(centerWidth, centerHeight);

          // Replace the content of the center pane with the procurementPane
          borderPane.setCenter(procurementPane);
      
//
//        // Load ProcurementPane.fxml
//        Parent procurementPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/MainStockMaster.fxml"));
//        //StackPaneMain.getChildren().add(FXMLLoader.load(getClass().getResource("/fxml/ProductionSummaryDashboard.fxml")));
//        // Set preferred size of procurementPane to match the screen size
//        double screenWidth = Screen.getPrimary().getBounds().getWidth();
//        double screenHeight = Screen.getPrimary().getBounds().getHeight();
//        ((Region) procurementPane).setPrefSize(screenWidth, screenHeight);
//
//        // Add procurementPane to MainDashboard
//        MainDashboard.getChildren().add(procurementPane);
////        testSql() ;
//        // Position procurementPane at the top-left corner of MainDashboard
//        MainDashboard.setLayoutX(0);
//        MainDashboard.setLayoutY(0);
      }catch(Exception e) {
    	  e.printStackTrace();
      }
    }
    
    public void loadWeighbridge() throws IOException {
//    	MainDashboard.getChildren().clear(); 
    	
    	// Load ProcurementPane.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/weighbridge.fxml"));
        Parent procurementPane = loader.load();

        // Set preferred size of procurementPane to match the center pane size
        BorderPane borderPane = (BorderPane) MainDashboard.getScene().getRoot();
        double centerWidth = borderPane.getCenter().getBoundsInLocal().getWidth();
        double centerHeight = borderPane.getCenter().getBoundsInLocal().getHeight();
        ((Region) procurementPane).setPrefSize(centerWidth, centerHeight);

        // Replace the content of the center pane with the procurementPane
        borderPane.setCenter(procurementPane);
    }
    
    public void loadSecurity() throws IOException {
    	 FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/EntranceRecording.fxml"));
         Parent procurementPane = loader.load();

         // Set preferred size of procurementPane to match the center pane size
         BorderPane borderPane = (BorderPane) MainDashboard.getScene().getRoot();
         double centerWidth = borderPane.getCenter().getBoundsInLocal().getWidth();
         double centerHeight = borderPane.getCenter().getBoundsInLocal().getHeight();
         ((Region) procurementPane).setPrefSize(centerWidth, centerHeight);

         // Replace the content of the center pane with the procurementPane
         borderPane.setCenter(procurementPane);
    }
    
    public void loadProcurement() throws IOException {
        // Load ProcurementPane.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ProcurementPane.fxml"));
        Parent procurementPane = loader.load();

        // Set preferred size of procurementPane to match the center pane size
        BorderPane borderPane = (BorderPane) MainDashboard.getScene().getRoot();
        double centerWidth = borderPane.getCenter().getBoundsInLocal().getWidth();
        double centerHeight = borderPane.getCenter().getBoundsInLocal().getHeight();
        ((Region) procurementPane).setPrefSize(centerWidth, centerHeight);

        // Replace the content of the center pane with the procurementPane
        borderPane.setCenter(procurementPane);
    }
    
//    public void loadProcurement() throws IOException {
//        MainDashboard.getChildren().clear();
//
//        // Load ProcurementPane.fxml
//        Parent procurementPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ProcurementPane.fxml"));
//
//        // Set preferred size of procurementPane to match the screen size
//        double screenWidth = Screen.getPrimary().getBounds().getWidth();
//        double screenHeight = Screen.getPrimary().getBounds().getHeight();
//        ((Region) procurementPane).setPrefSize(screenWidth, screenHeight);
//
//        // Add procurementPane to MainDashboard
//        MainDashboard.getChildren().add(procurementPane);
//
//        // Position procurementPane at the top-left corner of MainDashboard
//        MainDashboard.setLayoutX(0);
//        MainDashboard.setLayoutY(0);
//    }
//    
    public void loadFactories() throws IOException {
    	MainDashboard.getChildren().clear();
    	
    	 // Load ProcurementPane.fxml
        Parent procurementPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FactorySection.fxml"));

        // Set preferred size of procurementPane to match the screen size
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        ((Region) procurementPane).setPrefSize(screenWidth, screenHeight);

        // Add procurementPane to MainDashboard
        MainDashboard.getChildren().add(procurementPane);
   
    	MainDashboard.setLayoutX(0);
    	MainDashboard.setLayoutY(0);    	
    }
    
      
    public void loadRequisition() throws IOException {
    	MainDashboard.getChildren().clear();
    	Parent procurementPane = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/RequisitionPanel.fxml"));

        // Set preferred size of procurementPane to match the screen size
        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
        ((Region) procurementPane).setPrefSize(screenWidth, screenHeight);

        // Add procurementPane to MainDashboard
        MainDashboard.getChildren().add(procurementPane);
    
    	MainDashboard.setLayoutX(0);
    	MainDashboard.setLayoutY(0);    	
    }
    
    public static final LocalDate NOW_LOCAL_DATE() {
		String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(date, formatter);
		return localDate;
	}
    
//    @FXML
//    public void batchTabledata() {
//		ObservableList<SetGetBatchData> allitems, singleitems;
//		allitems = pendingTable.getItems();
//		singleitems = pendingTable.getSelectionModel().getSelectedItems();
//		// singleitems.forEach(allitems::remove);
//		allitems.clear();
//		allitems.removeAll();
//		try {
//			String weekdate = NOW_LOCAL_DATE().toString();
//			String sqlget = "select * from dbo.BatchwiseStock";
//			PreparedStatement psget = DbConnector.getConnection().prepareStatement(sqlget);
//			//psget.setString(1, weekdate);
//			ResultSet rs = psget.executeQuery();
//			while (rs.next()) {
//				mainbatchtabledata.add(new SetGetBatchData(rs.getString("vchCode"), rs.getString("date"),
//						rs.getString("no"), rs.getString("name"), rs.getString("factory"),
//						rs.getDouble("qtyProcessed"), rs.getDouble("batchStock")));
//			}
//			pendingTable.setItems(mainbatchtabledata);
//			psget.close();
//			// tblupstocks.setItems(null);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
    @FXML
    private void generateQRCode() {
        String text = txtBatchNo.getText();
 
        try {
            // Set up the QR code writer
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            
            // Create a hint for the encoding
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            
            // Generate the QR code matrix
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200, hints);

            // Convert the BitMatrix to a BufferedImage
            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // Convert BufferedImage to JavaFX Image
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);

            // Set the generated QR code image to the ImageView
            imgviewQr.setImage(image);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
    

}
