
public class Prim {
	
	private int[] getMinEdge(int[][] Cost) {
		int min = Integer.MAX_VALUE;
		int i, j, len = Cost.length, x = -1, y = -1;
		for(i = 0;i < len;i++) {
			for(j = 0;j < len;j++) {
				if(i != j && Cost[i][j] < min) {
					min = Cost[i][j];
					x = i;
					y = j;
				}
			}
		}
		int[] out = {x, y}; 
		return out;
	}
	
	private int getMinNear(int[][] Cost, int[] Near) {
		int len = Near.length;
		int i, mint, min = Integer.MAX_VALUE, minIndex = -1;
		for(i = 0;i < len;i++) {
			if(Near[i] != -1 && (mint = Cost[i][Near[i]]) < min) {
				min = mint;
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	public int prim(int[][] Cost, int[][] T) {
		int n = Cost.length;
		int[] Near = new int[n];
		int k, l, i;
		int[] minEdge = getMinEdge(Cost);
		k = minEdge[0];
		l = minEdge[1];
		int minCost = Cost[k][l];
		
		T[0][0] = k;
		T[0][1] = l;
		
		for(i = 0;i < n;i++) {
			if(Cost[i][l] < Cost[i][k]) {
				Near[i] = l;
			}
			else {
				Near[i] = k;
			}
		}
		
		Near[k] = -1;
		Near[l] = -1;
		
		for(i = 1;i < n - 1;i++) {
			int j = getMinNear(Cost, Near);
			T[i][0] = j;
			T[i][1] = Near[j];
			minCost = minCost + Cost[j][Near[j]];
			Near[j] = -1;
			for(k = 0;k < n;k++) {
				if(Near[k] != -1 && Cost[k][Near[k]] > Cost[k][j]) {
					Near[k] = j;
				}
			}
		}
		if(minCost >= Integer.MAX_VALUE) {
			System.out.println("No spanning tree");
			return -1;
		}
		
		return minCost;
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
		int minCost = new Prim().prim(G, T);
		System.out.println(minCost);
		
		for(i = 0, len = T.length;i < len;i++) {
			System.out.println(T[i][0] + " " + T[i][1]);
		}
	}

}
