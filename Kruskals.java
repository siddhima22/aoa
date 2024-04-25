kruskal
import java.util.*;
class Edge implements Comparable<Edge> {
    int weight, source, destination;
    Edge(int weight, int source, int destination) {
        this.weight = weight;
        this.source = source;
        this.destination = destination;}
    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }}
public class Kruskals {
    static int V;
    static int[] parent;
    static List<Edge> edges;
    static int find(int i) {
        while (parent[i] >= 0) { i = parent[i];}
        return i;}
    static int uni(int i, int j) {
        if (i != j) {parent[j] = i;return 1;}
        return 0;}
    static void kruskals() {
        int cost = 0;
        Collections.sort(edges);
        for (Edge edge : edges) {
            int u = find(edge.source);
            int v = find(edge.destination);
            if (uni(u, v) == 1) {
                System.out.println(edge.source + "->" + edge.destination + "\t" + edge.weight);
                cost += edge.weight;}}
        System.out.println("Minimum cost: " + cost);}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        V = scanner.nextInt();
        parent = new int[V];
        Arrays.fill(parent, -1);
        System.out.println("Enter the number of edges:");
        int E = scanner.nextInt();
        edges = new ArrayList<>();
        System.out.println("Enter the edges (weight, source, destination):");
        for (int i = 0; i < E; i++) {
            int weight = scanner.nextInt();
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            edges.add(new Edge(weight, source, destination));
        }
        kruskals();}}


KMP
  import java.util.Scanner;
public class KMP {
    static void PSA(char[] pat, int M, int[] lps) {
        int len = 0;int i = 1;lps[0] = 0;
        while (i < M) {if (pat[i] == pat[len]) {
                len++;
                lps[i] = len;
                i++; } else {
                if (len != 0) {
                    len = lps[len - 1]; } else {
                    lps[i] = 0;
                    i++;}}} }
    static void KMP(char[] txt, char[] pat) {
        int M = pat.length;
        int N = txt.length;
        int[] lps = new int[M];
        PSA(pat, M, lps);
        int i = 0, j = 0;
        while (i < N) {
            if (pat[j] == txt[i]) {
                j++;
                i++;
            }
            if (j == M) {
                System.out.println("Found at index " + (i - j));
                j = lps[j - 1];
            } else if (i < N && pat[j] != txt[i]) {
                if (j != 0) { j = lps[j - 1];} else {i++;}}}  }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text:");
        String text = scanner.nextLine();
        System.out.println("Enter pattern:");
        String pattern = scanner.nextLine();
        KMP(text.toCharArray(), pattern.toCharArray());}}


SumofSubset
  import java.util.Scanner;
public class SubsetSum {
    static int[] arr;
    static int n;
    static int sum;
    static int count = 0;
    static void sumSubset(int k, int s, int[] subset) {
        count++;
        if (s == sum) {printSubset(subset);return;}
        if (k == n || s > sum) {return; }
        subset[k] = arr[k]; 
        sumSubset(k + 1, s + arr[k], subset);
        subset[k] = 0; sumSubset(k + 1, s, subset);}
    static void printSubset(int[] subset) {
        System.out.print("Subset found: ");
        for (int i = 0; i < subset.length; i++) {
            if (subset[i] != 0) {
                System.out.print(subset[i] + " ");}}
        System.out.println();}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of elements:");
        n = scanner.nextInt();
        arr = new int[n];
        int[] subset = new int[n];
        System.out.println("Enter array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();}
        System.out.println("Enter sum:");
        sum = scanner.nextInt();
        sumSubset(0, 0, subset);
        if (count == 0) {
            System.out.println("Subset does not exist.");}
        System.out.println("Count = " + count);}}

RabinKarp
  import java.util.Scanner;

public class RabinKarp
   {public final static int d = 256;
    static void search(String pat, String txt, int q) 
      { int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0;        
        int t = 0; 
        int h = 1;
        for (i = 0; i < M - 1; i++)
            h = (h * d) % q;
        for (i = 0; i < M; i++) 
        {p = (d * p + pat.charAt(i)) % q;
         t = (d * t + txt.charAt(i)) % q;}
        for (i = 0; i <= N - M; i++) 
          {if (p == t) 
             {for (j = 0; j < M; j++) 
                  {if (txt.charAt(i + j) != pat.charAt(j))
                        break;}
                if (j == M)
                    System.out.println("Pattern found at index " + i);}
            if (i < N - M) 
              {t = (d * (t - txt.charAt(i) * h) + txt.charAt(i + M)) % q;
                if (t < 0)
                    t = (t + q);}}}
    public static void main(String[] args)
    {   Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text: ");
        String txt = scanner.nextLine();
        System.out.print("Enter the pattern: ");
        String pat = scanner.nextLine();
        int q = 101;
        search(pat, txt, q);}}
import java.util.Scanner;
public class NQueen {
    static final int SIZE = 30;
    static int[] position = new int[SIZE];
    static int count = 0;
    static boolean solutionFound = false;
    static boolean place(int k, int i) {
        for (int j = 1; j < k; j++) {
            if (position[j] == i || Math.abs(position[j] - i) == Math.abs(j - k)) {
                return false;}}
        return true;}
    static void printSolution(int n) {
        System.out.println("\n\n The solution: \n");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (position[i] == j) {
                    System.out.print("Q ");} else {System.out.print("* ");}}System.out.println();}}

    static void queen(int k, int n) {
        for (int i = 1; i <= n; i++) {
            if (place(k, i)) {
                position[k] = i;
                if (k == n && !solutionFound) {
                    printSolution(n);
                    solutionFound = true;}
          else { queen(k + 1, n);}}}
        count++;}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of Queens\n");
        int n = scanner.nextInt();
        queen(1, n);
        System.out.println("\ncount = " + count);}}
import java.util.*;
public class Dijkstra {
    static final int INF = 99999;
    static void printPath(int parent[], int j) {
        if (parent[j] == -1)
            System.out.print(j);
        else { printPath(parent, parent[j]); System.out.print(" -> " + j);}}
    static void printSolution(int dist[], int parent[]) {
        System.out.println("Vertex \t\t Distance from Source \t\t Path");
        for (int i = 0; i < dist.length; i++) {
            System.out.print(i + " \t\t " + dist[i] + " \t\t\t\t ");
            printPath(parent, i);
            System.out.println();}}
    static int minDistance(int dist[], boolean sptSet[]) {
        int min = INF, min_index = -1;
        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;}}
        return min_index;}
    static void dijkstra(int[][] graph, int src) {
        int V = graph.length;
        int dist[] = new int[V];
        boolean sptSet[] = new boolean[V];
        int parent[] = new int[V];
        for (int i = 0; i < V; i++) {
            dist[i] = INF;
            sptSet[i] = false;
            parent[i] = -1; }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    parent[v] = u;}}}
        printSolution(dist, parent);}
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter the adjacency matrix of the graph:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();}}
        System.out.print("Enter the source vertex: ");
        int src = scanner.nextInt();
        dijkstra(graph, src);}}

import java.util.*;

public class BellmanFord {
    public void bellmanFord(int V, int[][] graph, int src) {
        int[] dist = new int[V];
        int[] pred = new int[V];        
        Arrays.fill(pred, -1);         
         Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 1; i < V; ++i) {
            relaxAllEdges(V, graph, dist, pred);}
        for (int u = 0; u < V; ++u) {
            for (int v = 0; v < V; ++v) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
        }

   
        for (int i = 0; i < V; ++i) {
            if (i != src) {
                System.out.print("Path from " + src + " to " + i + ": ");
                printPath(pred, i);
                System.out.println(" (Distance: " + dist[i] + ")");
            }
        }
    }

    void relaxAllEdges(int V, int[][] graph, int[] dist, int[] pred) {
        for (int u = 0; u < V; ++u) {
            for (int v = 0; v < V; ++v) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pred[v] = u; 
                }
            }
        }
    }

    void printPath(int[] pred, int v) {
        if (v == -1) {
            return;
        }
        printPath(pred, pred[v]);
        System.out.print(v + " ");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.println("Enter the adjacency matrix of the graph:");
        int[][] adjacencyMatrix = new int[V][V];
        for (int i = 0; i < V; ++i) {
            for (int j = 0; j < V; ++j) {
                adjacencyMatrix[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Enter the source vertex: ");
        int src = scanner.nextInt();

        BellmanFord bellmanFord = new BellmanFord();
        bellmanFord.bellmanFord(V, adjacencyMatrix, src);
    }
}
import java.util.Scanner;

public class FloydWarshall {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int vertices = sc.nextInt();

        int[][] graph = new int[vertices][vertices];

        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        floydWarshall(graph, vertices);
    }

    public static void floydWarshall(int[][] graph, int vertices) {
        int[][] dist = new int[vertices][vertices];
        int[][] pred = new int[vertices][vertices];

       
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                pred[i][j] = -1;
            }
        }

      
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                dist[i][j] = graph[i][j];
                if (graph[i][j] != 999 && i != j) {
                    pred[i][j] = i;                 }
            }
        }

      
        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (dist[i][k] != 999 && dist[k][j] != 999 && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pred[i][j] = pred[k][j];
                    }
                }
            }
            printShortestDistances(dist, vertices);
            printPredecessor(pred, vertices);
        }
    }

    public static void printShortestDistances(int[][] dist, int vertices) {
        System.out.println("The shortest distance matrix is:");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (dist[i][j] == 999) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printPredecessor(int[][] pred, int vertices) {
        System.out.println("The predecessor matrix is:");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(pred[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

import java.util.*;

class Lcs{
 void lcs(char X[],char Y[],int m,int n)
 {
 	int i, j;
			
		char b[][]=new char[m+1][n+1];
		int c[][]=new int[m+1][n+1];
		
		for(i = 0; i <= m; i++)
		{
			c[i][0] = 0;
		}
			
		for(j = 0; j <= n; j++)
		{
			c[0][j] = 0;
		}
			
		for(i = 1; i <= m; i++)
		{
				for(j = 1; j <= n; j++)
				{
						if(X[i - 1] == Y[j - 1])
						{
								c[i][j] = c[i - 1][j - 1] + 1;
								b[i][j] = 'D';
						}
						
						else if(c[i - 1][j] >= c[i][j - 1])
						{
								c[i][j] = c[i - 1][j];
								b[i][j] = 'T';
						}
						
						else
						{
								c[i][j] = c[i][j - 1];
								b[i][j] = 'L';
						}
				}
		}
		
		System.out.println("Length of LCS is : "+c[m][n]+"\n");
		
		printLCS(b, c, m, n,X,Y);
 	
 }
 void printLCS(char b[][], int c[][], int m, int n,char X[],char Y[])
{
	if(m == 0 || n == 0)
		return;
		
	if(b[m][n] == 'D')
	{
			printLCS(b, c, m - 1, n - 1,X,Y);
			System.out.print( X[m - 1]);
	}
	
	else if(b[m][n] == 'T')
	{
			printLCS(b, c, m - 1, n,X,Y);
	}
	
	else
		printLCS(b, c, m, n - 1,X,Y);
}
 public static void main(String args[])
 {
 	String s1,s2;
 	Lcs obj=new Lcs();
 	Scanner sc=new Scanner(System.in);
 	System.out.println("enter first string");
 	s1=sc.next();
 	System.out.println("enter second string");
 	s2=sc.next();
 	char a[]=s1.toCharArray();
 	char b[]=s2.toCharArray();
 	int m=a.length;
 	int n=b.length;
 	obj.lcs(a,b,m,n);
 	
 }
}

import java.util.Scanner;

public class knapsack {

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static void knapsack_01(int n, int cap, int[] w, int[] p) {
        int[][] v = new int[n + 1][cap + 1];

               for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= cap; j++) {
                if (i == 0 || j == 0)
                    v[i][j] = 0;
                else if (w[i - 1] <= j)
                    v[i][j] = max(p[i - 1] + v[i - 1][j - w[i - 1]], v[i - 1][j]);
                else
                    v[i][j] = v[i - 1][j];
            }
        }


        int[] selected = new int[n];
        for (int i = 0; i < n; i++) {
            selected[i] = 0;
        }

      
        int j = cap;
        for (int i = n; i > 0 && v[i][j] > 0; i--) {
            if (v[i][j] != v[i - 1][j]) {
                selected[i - 1] = 1;
                j -= w[i - 1];
            }
        }


        int totalProfit = 0;
        System.out.print("Solution vector: ");
        for (int i = 0; i < n; i++) {
            System.out.print(selected[i] + " ");
            if (selected[i] == 1) {
                totalProfit += p[i];
            }
        }
        System.out.println();
        System.out.println("Total profit earned: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of objects: ");
        int n = scanner.nextInt();

        int[] w = new int[n];
        int[] p = new int[n];

        System.out.println("Enter the weight and profit of each object:");
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
            p[i] = scanner.nextInt();
        }

        System.out.print("Enter the capacity of knapsack: ");
        int cap = scanner.nextInt();

        knapsack_01(n, cap, w, p);
    }
}
import java.util.Scanner;

public class fracknap{

    static void knapsack(int num, float[] weight, float[] profit, float capacity) {
        float[] x = new float[num];
        float tp = 0;
        float u = capacity;
          int i;
        for ( i = 0; i < num; i++) {
            x[i] = 0;
        }


        for ( i = 0; i < num; i++) {
            if (weight[i] > u) {
                break;
            } else {
                x[i] = 1;
                tp += profit[i];
                u -= weight[i];
            }
        }

        if (i < num) {
            x[i] = u / weight[i];
        }
        tp += (x[i] * profit[i]);

        System.out.println("Result:");
        for ( i = 0; i < num; i++) {
            System.out.print(x[i] + "\t");
        }

        System.out.println("\nProfit is: " + tp);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of objects: ");
        int num = scanner.nextInt();

        float[] ratio = new float[num];
        float[] weight = new float[num];
        float[] profit = new float[num];
        float capacity, temp;

        System.out.println("Enter the weight and profit of each object:");
        for (int i = 0; i < num; i++) {
            weight[i] = scanner.nextFloat();
            profit[i] = scanner.nextFloat();
        }

        System.out.print("Enter the capacity of knapsack: ");
        capacity = scanner.nextFloat();

        for (int i = 0; i < num; i++) {
            ratio[i] = profit[i] / weight[i];
        }

        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {
                if (ratio[i] < ratio[j]) {
                    temp = ratio[i];
                    ratio[i] = ratio[j];
                    ratio[j] = temp;

                    temp = weight[i];
                    weight[i] = weight[j];
                    weight[j] = temp;

                    temp = profit[i];
                    profit[i] = profit[j];
                    profit[j] = temp;
                }
            }
        }

        knapsack(num, weight, profit, capacity);
    }
}
import java.util.Scanner;

public class Prims {
    int V;
    int[] parent; 

    int minKey(int[] key, boolean[] mstSet) {
        int min = Integer.MAX_VALUE, min_index = -1;
        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }

    void printMST(int[][] graph, int[] parent) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }

    void primMST(int[][] graph) {
        parent = new int[V];       
        int[] key = new int[V];
        boolean[] mstSet = new boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }
        key[0] = 0;
        parent[0] = -1;
        for (int count = 0; count < V - 1; count++) {
            int u = minKey(key, mstSet);
            mstSet[u] = true;
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }
        int totalWeight = 0;       
        for (int i = 1; i < V; i++) {
            totalWeight += graph[i][parent[i]];
        }
        printMST(graph, parent);
        System.out.println("Total weight of MST: " + totalWeight);   
    }

    public static void main(String[] args) {
        Prims prims = new Prims();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        prims.V = scanner.nextInt();

        int[][] graph = new int[prims.V][prims.V];
        System.out.println("Enter the adjacency matrix of the graph:");
        for (int i = 0; i < prims.V; i++) {
            for (int j = 0; j < prims.V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        prims.primMST(graph);
    }
}

import java.util.Scanner;
class MergeSort {


    static void printArray(int a[]) {
        int n = a.length;
        for (int i = 0; i < n; ++i)
            System.out.print(a[i] + " ");
        System.out.println();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int N = scanner.nextInt();
        int a[] = new int[N];
        System.out.println("Enter the elements:");
        for (int i = 0; i < N; i++) {
            a[i] = scanner.nextInt();
        }
        System.out.println("Given array is:");
        printArray(a);

         Divide(a,0,N-1);
      
        System.out.println("\nSorted array is:");
        printArray(a);
    }
  
       public static void Divide(int a[],int si,int ei)
         {
		if(si>=ei)
		return;

		int mid=(si+ei)/2;
		Divide(a,si,mid);
		Divide(a,mid+1,ei);
                conquer(a,si,mid,ei);
         }



	public static void conquer(int a[],int si, int mid , int ei)
	{
		int merge[]=new int[ei-si+1];
		int idx1=si;
		int idx2=mid+1;
  		int x=0;
 		while(idx1 <= mid && idx2 <= ei)
		{
			if(a[idx1] <= a[idx2])
			{
				merge[x++] =a[idx1++];
			}
			else
			{
			       
				merge[x++] =a[idx2++];
	
			}
		}
		
		while(idx1<=mid)
		{ merge[x++] =a[idx1++];}

                
		while(idx2<=ei)
		{ merge[x++] =a[idx2++];}
		

	 for (int i = 0, j = si; i < merge.length; i++, j++) 
      {
        a[j] = merge[i];
    }

 }
}

import java.util.*;

class QuickSort1 {
    static int quickSortCalls = 0;

    public static void main(String arg[]) {
        int i, j, k, temp;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of elements in the array");
        int n = sc.nextInt();

        int a[] = new int[n];
        for (i = 0; i < n; i++) {
            System.out.println("Enter element " + (i + 1) + " of array");
            a[i] = sc.nextInt();
        }

        System.out.println("\nOriginal array ");
        for (i = 0; i < n; i++) {
            System.out.print(a[i] + "  ");
        }

        Quick(a, 0, n - 1);

        System.out.println("\nFinal array ");
        for (i = 0; i < n; i++) {
            System.out.print(a[i] + "  ");
        }
        System.out.println("\nNumber of calls to Quick sort function: " + quickSortCalls + "\n");
    }
    public static void Quick(int a[],int low,int h)
     {
        if(low<h)
       {
	 int q=Part(a,low,h);
         Quick(a,low,q-1);
          Quick(a,q+1,h);
      }
     }

   public static int Part(int a[],int low,int h)
     {
	 int pivot= a[h];
         int i=low-1;
      int temp;
          for(int j=low;j<h;j++)
          {
                if(a[j]<pivot)
                  {
                    i++;
                    temp= a[i];
                     a[i]=a[j];
                     a[j]=temp;     
                    }
          }
                     temp= a[i+1];
                     a[i+1]=a[h];
                     a[h]=temp;  

     return i+1;
     }

}

import java.util.*;
class SelectionSort
{
   public static void main(String arg[])
   { int i,j,k,temp;
     Scanner sc= new Scanner(System.in);
     
     System.out.println("Enter number of elements in the array");
     int n= sc.nextInt();
     
     int a[]=new int[n+1];
     for(i=1;i<=n;i++)
     {
        System.out.println("Enter element "+ (i)+" of array");
        a[i]=sc.nextInt();
     }  
     
      System.out.println("\norigional array ");
      for(i=1;i<=n;i++)
       {
        System.out.print(a[i]+ "  ");      
       }
      System.out.println("\n");
     
          for(i=1;i<=n;i++)
           {
		j=i;
              for(k=i+1;k<=n;k++)
               {
                  if(a[j]>a[k])
                   j=k;

                  } 
            temp=a[i];
            a[i]=a[j];
            a[j]=temp;       
               System.out.println( "iteration  "+(i+1));     
            for(k=1;k<=n;k++)
            {
               System.out.print(a[k]+ "   ");      
            }    
            System.out.println("");
	  } 

      System.out.println("\nFinal array ");
     for(i=1;i<=n;i++)
     {
        System.out.print(a[i]+ "  ");
     
     
     }
        System.out.println("\n");
     
  }

}

import java.util.Scanner;

class InsertionSort
{
	public static void main(String arg[])
          {
             int i,j,key,k;
              Scanner sc= new Scanner(System.in);
              System.out.println("enter number of elements");
              int n=sc.nextInt();

              int a[]=new int [n+1];
              
              for ( i=1;i<=n;i++)
                 {      System.out.println("enter elements");
                       a[i]=sc.nextInt();

                     }
		
		for (j=2;j<=n;j++)
                {
                     key =a[j];
                     i=j-1;

                      while(i>0 && a[i]>key)
                        {
                             a[i+1]=a[i];
                              i=i-1;
                           }
                        a[i+1]=key;

                         System.out.println("ITERATION"+(j-1));
                      for ( k=1;k<=n;k++)
                       { System.out.print(a[k]+"   ");

                         }
 			System.out.println("");
                    
                }

               System.out.println("FINAL ARRAY");
                         for ( k=1;k<=n;k++)
                       { System.out.print(a[k]+"   ");

                         }
       }

}
