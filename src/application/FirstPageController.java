package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

//this class controls Main.fxml file
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.flow.FordFulkersonAlgorithm;
public class FirstPageController implements Initializable{
	
	public static int firstNd,secondNd,startNd,finishNd,capacity;
	public static ArrayList<EdgeInfo> edgeList=new ArrayList<EdgeInfo>();
	public static int graphMatrix[][] = new int[20][20];
	public static int graphFinal[][] = new int[20][20];
    MaxFlow m = new MaxFlow(); //maxFlow classindaki ford Fulkersin metodunu cagirmak icin nesne olusturduk
    MinCut min=new MinCut();
    

    public static Graph graph =  new SingleGraph("You are viewing the graph");
	FordFulkersonAlgorithm ff = new FordFulkersonAlgorithm();
	final ObservableList<EdgeInfo> data=FXCollections.observableArrayList();
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private TextField finishNode;
	
	@FXML
	private TextField startNode;
	
	@FXML
	private TextField firstNode;
	
	@FXML
	private TextField secondNode;
	
	@FXML
	private TextField txtCapacity;
     
     @FXML
     private TableView EdgeInfoTable;
     
     
    public Graph generateNodes() {
    	Graph graph = new SingleGraph("You are viewing the nodes");
    	
    	 graph.setStrict(false);
         graph.setAutoCreate(true);
         
 		for(int i=0;i< MainController.muslukSayisi;i++) {
 			graph.addNode(i+"");
 		}			
 		 for (Node node : graph) {
 			node.addAttribute("ui.label", node.getId());
 			if(node.getId().equalsIgnoreCase(String.valueOf(startNd) ) || node.getId().equalsIgnoreCase(String.valueOf(finishNd))) {
 				node.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
 				
 			}
 		 }  
 		
    	return graph;
    }
     
	public void saveNodeInfos(ActionEvent event) {
		
		startNd=Integer.valueOf(startNode.getText());
		finishNd=Integer.valueOf(finishNode.getText());
		
		System.out.println("Baslangic Dugumu: "+startNd);
		System.out.println("Bitis Dugumu: "+finishNd);
		
//		Graph graph = generateNodes();

        graph.setStrict(false);
        graph.setAutoCreate(true);
        
		for(int i=0;i< MainController.muslukSayisi;i++) {
			graph.addNode(i+"");
		}			
		 for (Node node : graph) {
			node.addAttribute("ui.label", node.getId());
			if(node.getId().equalsIgnoreCase(String.valueOf(startNd) ) || node.getId().equalsIgnoreCase(String.valueOf(finishNd))) {
				node.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
				
			}
		 }  
		
		 graph.display();
	}
	
	public void setGraph(ActionEvent event) {
	
		firstNd=Integer.valueOf(firstNode.getText());
		secondNd=Integer.valueOf(secondNode.getText());
		capacity=Integer.valueOf(txtCapacity.getText());

		
		data.add(
			new EdgeInfo(
				String.valueOf(firstNode.getText()),
				String.valueOf(secondNode.getText()),
				String.valueOf(txtCapacity.getText())
			)
		);
		
		
		edgeList.add(
			new EdgeInfo(
				String.valueOf(firstNode.getText()),
				String.valueOf(secondNode.getText()),
				String.valueOf(txtCapacity.getText())
			)
		);
		
		graphMatrix[firstNd][secondNd] = capacity;
		firstNode.clear();
		secondNode.clear();
		txtCapacity.clear();
				
				
	}
	
	/*Dugum bilgilerinin tutuldugu edgeListteki degerlere gore graf cizdirmek icin matrisi olusturur*/
	public void dynamicGraphMatrix ()
	{
		int n = graph.getNodeCount();
		System.out.println("Olusturulan Grafin Matrisi:\n");
		 for(int i=0;i<n;i++){
			 for(int j=0;j<n;j++) {
			    graphFinal[i][j] += graphMatrix[i][j];
				System.out.print(graphMatrix[i][j]+"  ");
			}
			 System.out.println("\n");
		 }
		
	}
	
	public void showGraph(ActionEvent event) {
		
		dynamicGraphMatrix();  //dinamik grafin matrisini olusturan metodu cagirdik
		
		graph.setStrict(false);
		graph.setAutoCreate(true);
        
		for(int i=0;i< MainController.muslukSayisi;i++) {
			graph.addNode(i+"");
		}			

		
		for(int i=0;i< edgeList.size() ;i++) {
			String tmp = edgeList.get(i).getFirstNd() + "" + edgeList.get(i).getSecondNd();
//			System.outf.println("tmp: "+tmp);
			graph.addEdge( tmp , edgeList.get(i).getFirstNd() , edgeList.get(i).getSecondNd(),true);
		}	
		 for (Node node : graph) {
			 
			node.addAttribute("ui.label", node.getId());
			if(node.getId().equalsIgnoreCase(String.valueOf(startNd) ) || node.getId().equalsIgnoreCase(String.valueOf(finishNd))) {
				node.addAttribute("ui.style", "fill-color: rgb(0,100,255);");
				
			}
		 }  
		 
		//Add label to edges : capasity
	     Iterator<Edge> k = graph.getEdgeIterator();

	     while (k.hasNext()) {
	    	 Edge next = k.next();
	    	 int id = next.getIndex();
	    	 next.setAttribute("ui.style", "fill-color: rgb(255, 242, 0);");
	         next.setAttribute("ui.label", edgeList.get(id).getCapacity());
	     }

				
	}
	
    public void minCut(ActionEvent event) {

        System.out.println("\nMin Cut");

         min.minCutMethod(graphMatrix, startNd, finishNd);

    }
 
	
	public void maxFlow(ActionEvent event) {
//		ff.init(graph,String.valueOf(startNd), String.valueOf(finishNd));
//		Iterator itr = edgeList.iterator();
//		  while (itr.hasNext()) {
//			  EdgeInfo next = (EdgeInfo) itr.next();
//			  int uid = Integer.parseInt(next.getFirstNd());
//			  Node u = graph.getNode(Integer.parseInt(next.getFirstNd()));
//			  int vid = Integer.parseInt(next.getSecondNd());
//			  Node v = graph.getNode(Integer.parseInt(next.getSecondNd()));
//			  double capacity =Integer.parseInt(next.getCapacity());
////			  System.out.println("uid : " + uid + " vid : " + vid +" capacity "+capacity);
////			  System.out.println("u : " + u.getId() + " v : " + v.getId() +" capacity "+capacity);
//			  ff.setCapacity(u,v,capacity);
//			  System.out.println(uid + " " + vid + " " +ff.getCapacity(u,v));
//		  }
//		  System.out.println("get source sink"+ff.getFlowSourceId());
//		  System.out.println("getflowsink"+ff.getFlowSinkId());
////		  System.out.println("getflow"+ff.getFlow(startNd, finishNd));
//	 //   System.out.println("max fl :"+ff.getMaximumFlow());

		System.out.println("Max Flow: "+m.fordFulkerson(graphMatrix, startNd, finishNd,graph.getNodeCount()));
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
	
		TableColumn firstColumn=new TableColumn("Birinci Dugum");
		TableColumn secondColumn=new TableColumn("Ikinci Dugum");
		TableColumn thirdColumn=new TableColumn("Kapasite");
		EdgeInfoTable.getColumns().addAll(firstColumn,secondColumn,thirdColumn);
	
		
		firstColumn.setCellValueFactory(new PropertyValueFactory<EdgeInfo,String>("firstNd"));
		secondColumn.setCellValueFactory(new PropertyValueFactory<EdgeInfo,String>("secondNd"));
		thirdColumn.setCellValueFactory(new PropertyValueFactory<EdgeInfo,String>("capacity"));
		
		EdgeInfoTable.setItems(data);
		
	}
	
	
	

}
