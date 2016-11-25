import java.util.PriorityQueue;
import java.util.Comparator;


public class Kruskal {
//	private PriorityQueue<int[]> heap = new PriorityQueue<>();
	 
	public int find(int[] Parent, int v) {
		while(Parent[v] >= 0) {
			v = Parent[v];
		}
		return v;
	}
	
	public void union(int[] Parent, int j, int k) {
		if(Parent[j] >= 0) {
			j = find(Parent, j);
		}
		if(Parent[k] >= 0) {
			k = find(Parent, k);
		}
		int x = Parent[j] + Parent[k];
		if(Parent[j] > Parent[k]) {
			Parent[k] = x;
			Parent[j] = k; 
		}
		else {
			Parent[k] = j;
			Parent[j] = x; 
		}
	}
	
	public int kruskal(int[][] Cost, int[][] T) {
		int i, j, k, mincost = 0, len = Cost.length;
		int[] Parent = new int[len];
		
		PriorityQueue<int[]> heap = new PriorityQueue<int[]>(1, new Comparator<int[]>() {
	        @Override
	        public int compare(int[] o1, int[] o2) {
	            return o1[2] - o2[2];
	        }
	    });
		
		for(i = 0;i < len;i++) {
			for(j = i + 1;j < len;j++) {
				if(Cost[i][j] != Integer.MAX_VALUE) {
					heap.add(new int[]{i, j, Cost[i][j]});
				}
			}
		}

		for(i = 0;i < len;i++) {
			Parent[i] = -1;
		}
		
		for(i = 0;i < len - 1 && heap.size() > 0;) {
			int[] temp = heap.remove();
			j = find(Parent, temp[0]);
			k = find(Parent, temp[1]);
			
			if(j != k) {
				T[i][0] = temp[0];
				T[i][1] = temp[1];
				i++;
				mincost += temp[2];
				union(Parent, j, k);
			}
		}
		
		if(i != len - 1) {
			System.out.println("no spanning tree");
		}
		
		return mincost;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] G = new int[6][6];
		int i, j, len = G.length;
		
		for(i = 0;i < len;i++) {
			for(j = 0;j < len;j++) {
				if(i == j) {
					G[i][j] = 0;
				}
				else {
					G[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
		G[0][1] = 10;
		G[0][3] = 30;
		G[0][4] = 45;
		G[1][0] = 10;
		G[1][2] = 50;
		G[1][4] = 40;
		G[1][5] = 25;
		G[2][1] = 50;
		G[2][4] = 35;
		G[2][5] = 15;
		G[3][0] = 30;
		G[3][5] = 20;
		G[4][0] = 45;
		G[4][1] = 40;
		G[4][2] = 35;
		G[4][5] = 55;
		G[5][1] = 25;
		G[5][2] = 15;
		G[5][3] = 20;
		G[5][4] = 55;
		
		int T[][] = new int[len - 1][2];
		int minCost = new Kruskal().kruskal(G, T);
		
		System.out.println(minCost);
		
		for(i = 0, len = T.length;i < len;i++) {
			System.out.println(T[i][0] + " " + T[i][1]);
		}
	}

}
