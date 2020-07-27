package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

//this class controls Main.fxml file
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;



public class MainController{
	
	public static int muslukSayisi;
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField txtMuslukSayisi;
	
	
	public void TakeMuslukSayisi(ActionEvent event) {
		if( txtMuslukSayisi.getText().equals("")) {
			lblStatus.setText("Lutfen Musluk Sayisini Giriniz!");
			System.out.println("BOS");
		}else {
			muslukSayisi=Integer.valueOf(txtMuslukSayisi.getText());
			System.out.println( "Musluk Sayisi: "+txtMuslukSayisi.getText());
			lblStatus.setText("Generating Graph..");
			
			Graph graph = new SingleGraph("You are viewing the nodes");

	        graph.setStrict(false);
	        graph.setAutoCreate(true);

	        
			for(int i=0;i<muslukSayisi;i++) {
				graph.addNode(i+"");
			}
			
			  for (Node node : graph) {
			        node.addAttribute("ui.label", node.getId());
			    }
			  
			graph.display();
			
			
			Main.set_Pane(1);
		}
	}
}
