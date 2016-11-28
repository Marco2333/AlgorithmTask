
public class OBST {
	
	public int[][] obst(int[] P, int[] Q) {
		int i, j, m, k, l, min, len = Q.length, n = len - 1; 
		int[][] W = new int[len][len];
		int[][] C = new int[len][len];
		int[][] R = new int[len][len];
		
		for(i = 0;i < n;i++) {
			W[i][i] = Q[i];
			R[i][i] = 0;
			C[i][i] = 0;
			W[i][i + 1] = Q[i] + Q[i + 1] + P[i];
			R[i][i + 1] = i + 1;
			C[i][i + 1] = Q[i] + Q[i + 1] + P[i];
		}
		
		W[n][n] = Q[n];
		R[n][n] = 0;
		C[n][n] = 0; 
		
		for(m = 2;m < len;m++) {
			for(i = 0;i <= n - m;i++) {
				j = i + m;
				W[i][j] = W[i][j - 1] + P[j - 1] + Q[j];
				k = -1;
				min = Integer.MAX_VALUE;
				for(l = i + 1;l < j;l++) {
					int temp = C[i][l - 1] + C[l][j];
					if(temp < min) {
						k = l;
						min = temp;
					}
				}
				C[i][j] = W[i][j] + C[i][k - 1] + C[k][j];
				R[i][j] = k;
			}
		}
		return R;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] P = new int[]{1, 1, 1};
		int[] Q = new int[]{1, 1, 1, 1};
		int[][] R = new OBST().obst(P, Q);
		
		for(int i = 0;i < R.length;i++) {
			for(int j = 0;j < R.length;j++) {
				System.out.print(R[i][j] + " ");
			}
			System.out.println();
		}
	}

}
