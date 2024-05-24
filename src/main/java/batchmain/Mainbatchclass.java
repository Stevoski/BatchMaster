package batchmain;

import java.io.IOException;
import java.util.Objects;

//import application.Main;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Mainbatchclass extends Application {
//	@FXML
//	StackPane StackPaneMain;
	private Stage primaryStage;
	public static BorderPane mainLayout;
	Parent root;
	Stage stage;

		@Override
		public  void start(Stage primaryStage) throws IOException {
			// root=FXMLLoader.load(getClass().getResource("src/main/resources/BatchMainController.fxml"));
			 root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("fxml/MainStockMaster.fxml")));
			this.primaryStage=primaryStage;
			this.primaryStage.setTitle("Njoro Canning Factory");
			primaryStage.setMaximized(true);
			showLoginView();
//			LoadDashboardFXML();
		}
		private void showLoginView() throws IOException {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(Mainbatchclass.class.getResource("/fxml/MainStockMaster.fxml"));
			mainLayout=loader.load();
			Scene scene=new Scene(mainLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
//			System.out.println(System.getProperties());
			}
//		public void LoadDashboardFXML() throws IOException
//		{
//		    StackPaneMain.getChildren().clear();
//		    StackPaneMain.getChildren().add(FXMLLoader.load(getClass().getResource("/fxml/ProductionSummaryDashboard.fxml")));
//		    StackPaneMain.setLayoutX(0);
//		    StackPaneMain.setLayoutY(0);
//		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
