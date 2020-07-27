package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;


public class Main extends Application {
	
	static AnchorPane root;
	private static int cursor=0;
	
	static List<GridPane> grid=new ArrayList<GridPane>();
	
	public static int muslukSayisi = 0;
	
	public static void main(String[] args) {
		
		launch(args);		
		//nodeInformation.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	try {
			root=(AnchorPane)FXMLLoader.load(getClass().getResource("/application/anchor.fxml"));
			
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("/application/Main.fxml")));
			grid.add((GridPane)FXMLLoader.load(getClass().getResource("/application/First.fxml")));
		
			root.getChildren().add(grid.get(0));
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Havuz Problemi");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}catch(Exception e){
		}
		
	}

public static void set_Pane(int idx) {
		
		root.getChildren().remove(grid.get(cursor));
		root.getChildren().add(grid.get(idx));
		cursor=idx;	
	}
	
	public static GridPane GetPane(int idx) {
		return grid.get(idx);
	}
	
}
	
