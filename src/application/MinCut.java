package application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.graphstream.graph.Edge;

public class MinCut { 
	
	public static ArrayList<EdgeInfo> minCutPathList=new ArrayList<EdgeInfo>();
	//https://www.geeksforgeeks.org/minimum-cut-in-a-directed-graph/ adli siteden yararlanilmistir
    
	//Min Cut Probleminde minimum kapasiteye sahip yollari bulup onlari kapatmaktir
	
    //Boolean bfs metodunda startNd ve finishNd arasinda yol varsa true doner
    // Bu islemler yapilirken parent adli integer dizide yollari tutar
    private static boolean bfs(int[][] rGraph, int s, 
                                int t, int[] parent) { 
          
        // Ziyaret edilen noktalar icin true gezilmeyen yerler icin false degerini tutan dizi  
        boolean[] visited = new boolean[rGraph.length]; 
           
        
        // Bir kuyruk oluþturulup, baslangic noktasi kuyruga eklendi
        Queue<Integer> q = new LinkedList<Integer>(); 
        q.add(s); 
        visited[s] = true; 
        parent[s] = -1; 
          
        // BFS dongusu baslatiliyor     
        while (!q.isEmpty()) { 
            int v = q.poll(); 
            for (int i = 0; i < rGraph.length; i++) { 
                if (rGraph[v][i] > 0 && !visited[i]) { 
                    q.offer(i); 
                    visited[i] = true; 
                    parent[i] = v; 
                } 
            } 
        } 
          
     // Eger olusturulan dinamik grafta baslangic noktasindan bitis noktasina varilabiliyorsa
     // true degeri dondurulur
        return (visited[t] == true); 
    } 
      
    // A DFS based function to find all reachable  
    // vertices from s. The function marks visited[i]  
    // as true if i is reachable from s. The initial  
    // values in visited[] must be false. We can also  
    // use BFS to find reachable vertices 
    private static void dfs(int[][] rGraph, int s, 
                                boolean[] visited) { 
        visited[s] = true; 
        for (int i = 0; i < rGraph.length; i++) { 
                if (rGraph[s][i] > 0 && !visited[i]) { 
                    dfs(rGraph, i, visited); 
                } 
        } 
    }
    
    public void minCutMethod(int[][] graph, int s, int t) { 
        int u,v; 
          
        // Create a residual graph and fill the residual  
        // graph with given capacities in the original  
        // graph as residual capacities in residual graph 
        // rGraph[i][j] indicates residual capacity of edge i-j 
        int[][] rGraph = new int[graph.length][graph.length];  
        for (int i = 0; i < graph.length; i++) { 
            for (int j = 0; j < graph.length; j++) { 
                rGraph[i][j] = graph[i][j]; 
            } 
        } 
  
        // This array is filled by BFS and to store path 
        int[] parent = new int[graph.length];  
          
        // Augment the flow while tere is path from source to sink      
        while (bfs(rGraph, s, t, parent)) { 
              
            // Find minimum residual capacity of the edhes  
            // along the path filled by BFS. Or we can say  
            // find the maximum flow through the path found. 
            int pathFlow = Integer.MAX_VALUE;          
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v]; 
                pathFlow = Math.min(pathFlow, rGraph[u][v]); 
            } 
              
            // update residual capacities of the edges and  
            // reverse edges along the path 
            for (v = t; v != s; v = parent[v]) { 
                u = parent[v]; 
                rGraph[u][v] = rGraph[u][v] - pathFlow; 
                rGraph[v][u] = rGraph[v][u] + pathFlow; 
            } 
        } 
          
        // Flow is maximum now, find vertices reachable from s      
        boolean[] isVisited = new boolean[graph.length];      
        dfs(rGraph, s, isVisited); 
          
        // Print all edges that are from a reachable vertex to 
        // non-reachable vertex in the original graph 
        int count=0;
        for (int i = 0; i < graph.length; i++) { 
        	
            for (int j = 0; j < graph.length; j++) { 
                if (graph[i][j] > 0 && isVisited[i] && !isVisited[j]) { 
                    System.out.println(i + " - " + j);
                    Iterator<Edge> k = FirstPageController.graph.getEdgeIterator();
                   
                    while (k.hasNext()) {
           	    	 Edge next = k.next();
           	    	 int id = next.getIndex();
           	    	if(Integer.parseInt(FirstPageController.edgeList.get(id).getFirstNd()) == i  && Integer.parseInt(FirstPageController.edgeList.get(id).getSecondNd()) == j ) {
              	    	 next.setAttribute("ui.style", "fill-color: rgb(143, 99, 149);");
               	         next.setAttribute("ui.label", FirstPageController.edgeList.get(id).getCapacity());
//               	      System.out.println("test" + i + " - " + j);
           	    	}

           	     }
                } 
            } 
        } 
    } 
    
}
