package application;

import java.util.Iterator;
import java.util.LinkedList;

import org.graphstream.graph.Edge;

public class MaxFlow 
{ 
  
/*https://www.geeksforgeeks.org/ford-fulkerson-algorithm-for-maximum-flow-problem/ 
 * adli siteden max flow akis algoritmasindan yararlanilmistir  */
   
	public static int resultGraph[][] = new int [20][20];
    
    /*Maksimum akis bulunurken startNd den baslayip finishNd ye giden bir yolun olup olmadigini
     * kontrol eder, var ise true dondurur. parent adli dizimizde yollari tutar*/
    boolean bfs(int rGraph[][], int s, int t, int parent[],int V) 
    { 
     
        boolean visited[] = new boolean[V]; 
        for(int i=0; i<V; ++i) 
            visited[i]=false; 
        
        
  
        // Create a queue, enqueue source vertex and mark 
        // source vertex as visited 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
        queue.add(s); 
        visited[s] = true; 
        parent[s]=-1; 
  
        // Standard BFS Loop 
        while (queue.size()!=0) 
        { 
            int u = queue.poll(); 
  
            for (int v=0; v<V; v++) 
            { 
                if (visited[v]==false && rGraph[u][v] > 0) 
                { 
                    queue.add(v); 
                    parent[v] = u; 
                    visited[v] = true; 
                } 
            } 
        } 
  
        // If we reached sink in BFS starting from source, then 
        // return true, else false 
        return (visited[t] == true); 
    } 
    
    /*Maksimum akisi bulmak icin yazilmis olan metoddur. Parametre olarak grafin matrisini, baslangic dugumunu
     * bitis dugumunu ve graftaki node sayisini alir*/
    public int fordFulkerson(int graph[][], int s, int t,int V) 
    { 
        int u, v; 
  
        // Create a residual graph and fill the residual graph 
        // with given capacities in the original graph as 
        // residual capacities in residual graph 
  
        // Residual graph where rGraph[i][j] indicates 
        // residual capacity of edge from i to j (if there 
        // is an edge. If rGraph[i][j] is 0, then there is 
        // not) 
        int rGraph[][] = new int[V][V]; 
  
        for (u = 0; u < V; u++) 
            for (v = 0; v < V; v++) 
                rGraph[u][v] = graph[u][v]; 
  
        // This array is filled by BFS and to store path 
        int parent[] = new int[V]; 
  
        int max_flow = 0;  // There is no flow initially 
  
        // Augment the flow while tere is path from source 
        // to sink 
        while (bfs(rGraph, s, t, parent,MainController.muslukSayisi)) 
        { 
            // Find minimum residual capacity of the edhes 
            // along the path filled by BFS. Or we can say 
            // find the maximum flow through the path found. 
            int path_flow = Integer.MAX_VALUE; 
            for (v=t; v!=s; v=parent[v]) 
            { 
                u = parent[v]; 
                path_flow = Math.min(path_flow, rGraph[u][v]);
                
            } 
  
            // update residual capacities of the edges and 
            // reverse edges along the path 
            for (v=t; v != s; v=parent[v]) 
            { 
                u = parent[v]; 
                rGraph[u][v] -= path_flow; 
                rGraph[v][u] += path_flow; 
            } 
  
            System.out.println("path_flow:"+path_flow +" " +  u);
            // Add path flow to overall flow 
            max_flow += path_flow; 
        } 
        
        resultGraph = rGraph;
        
        //print capasitys 
        for(int i = 0; i < FirstPageController.graph.getNodeCount() ; i++) {
        	for(int j = 0; j < FirstPageController.graph.getNodeCount(); j++ ) {
        		if(FirstPageController.graphFinal[i][j] != 0) {
        			String newLabel = Integer.toString(resultGraph[i][j]) +  " / " +  Integer.toString(FirstPageController.graphFinal[i][j]);
        			System.out.println("burasi :" + Integer.toString(resultGraph[i][j]) +  " / " +  Integer.toString(FirstPageController.graphFinal[i][j]));
	
        		     Iterator<Edge> k = FirstPageController.graph.getEdgeIterator();
        		     while (k.hasNext()) {
        		    	Edge next = k.next();
        		    	int id = next.getIndex();
                	    if(Integer.parseInt(FirstPageController.edgeList.get(id).getFirstNd()) == i  && Integer.parseInt(FirstPageController.edgeList.get(id).getSecondNd()) == j ) {
                	    	FirstPageController.edgeList.get(id).setCapacity(newLabel);
                	    }
        		    	 next.setAttribute("ui.style", "fill-color: rgb(255, 242, 0);");
        		         next.setAttribute("ui.label", FirstPageController.edgeList.get(id).getCapacity());
        		     }        		     
        		}
        	}
        }
        
        for(int i = 0; i < FirstPageController.graph.getNodeCount() ; i++) {
        	for(int j = 0; j < FirstPageController.graph.getNodeCount(); j++ ) {
        		if(FirstPageController.graphFinal[i][j] != resultGraph[i][j]) {
       		 
        			Iterator<Edge> k = FirstPageController.graph.getEdgeIterator();
       		     	while (k.hasNext()) {
       		     		Edge next = k.next();
       		     		int id = next.getIndex();
       		     		if(Integer.parseInt(FirstPageController.edgeList.get(id).getFirstNd()) == i  && Integer.parseInt(FirstPageController.edgeList.get(id).getSecondNd()) == j ) {
       		     			next.setAttribute("ui.style", "fill-color: rgb(255,0,0);");
       		     		}
       		     	}      			
        			}
        		}
        }
        
        // Return the overall flow 
        return max_flow; 
    } 
}