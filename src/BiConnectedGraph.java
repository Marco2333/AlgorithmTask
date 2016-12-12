import java.util.LinkedList;


public class BiConnectedGraph {
	private int[][] edges;
	private int[] DFN;
	private int[] L;
	private int num;
	private LinkedList<int[]> S;
	
	public BiConnectedGraph(int n, int[][] e) {
		this.edges = new int[n + 1][n + 1];
		this.DFN = new int[n + 1];
		this.L = new int[n + 1];
		
		int i, j;
		for(i = 1;i <= n;i++) {
			for(j = 1;j <= n;j++) {
				if(i == j) {
					edges[i][j] = 0;
				}
				else {
					edges[i][j] = -1;
				}
			}
			DFN[i] = 0;
		}
		for(i = 0;i < e.length;i++) {
			edges[e[i][0]][e[i][1]] = e[i][2];
			edges[e[i][1]][e[i][0]] = e[i][2];
		}
		
		this.num = 1;
		this.S = new LinkedList<>();
	}
	
	public void ART(int u, int v) {
		DFN[u] = L[u] = num;
		++num;
	
		for(int i = 1;i < edges.length;i++) {
			if(edges[u][i] != -1) {
				if(v != i && DFN[i] < DFN[u]) {
					S.push(new int[]{u, i});
				}
				if(DFN[i] == 0) {
					ART(i, u);
					if(L[i] >= DFN[u]) {
						System.out.println("new biconnected component");
						int x, y;
						do {
							int[] edge = S.pop();
							x = edge[0];
							y = edge[1];
							System.out.println("(" + x + "," + y + ")");
						} while ((x != u || y != i) && (x != i || y != u));
					}
					L[u] = Math.min(L[u], L[i]);
				} else if(i != v) {
					L[u] = Math.min(L[u], DFN[i]);
				}
			}
		}
	}
	
	 private void printLAndDFN() {
		 int n = edges.length;
		 System.out.println("L:");
         for (int i = 1; i < n; i++) {
              System.out.print(L[i] + " ");
         }
         	System.out.println();
         	System.out.println("DNF:");
         for (int i = 1; i < n; i++) {
            System.out.print(DFN[i] + " ");
         }
     }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BiConnectedGraph bg = new BiConnectedGraph(10, new int[][]{{1, 4, 1}, {1, 2, 1}, 
				{4, 3, 1}, {2, 3, 1}, {3, 10, 1}, {3, 9, 1}, {2, 5, 1}, {2, 7, 1}, 
				{2, 8, 1}, {5, 8, 1}, {5, 7, 1}, {7, 8, 1}, {5, 6, 1}});
		bg.ART(1, 0);
        bg.printLAndDFN();
	}

}
